package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Adrenalina.Exceptions.EmptyDeckException;
import it.polimi.se2019.Controller.Adrenalina.Exceptions.LimitPowerUpException;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class represents the player's turn in the match
 */

public class Turn extends Thread{

    /*
    ------------FIELDS-------------------------
     */

    /**
     * drewPowerUp is used to save the PowerUp that the player has drawn
     */

    private CopyOnWriteArrayList<Powerup> drewPowerUp;

    private Board board = new Board();

    public boolean terminatorKilled = false;

    private boolean interrupt = false;

    private Object threadKiller = new Object();

    /**
     * At the end of the turn, those players must be respawned.
     */

    private CopyOnWriteArrayList<Player> killedPlayers;
    /**
     * switchedWeapon is used to save the weapon before checking it
     */

    private Weapon switchedWeapon;


    /*
    -----------------METHODS--------------------
     */


    /*
    ------------------GETTERS--------------------
     */

    public CopyOnWriteArrayList<Powerup> getDrewPowerUp() {
        return drewPowerUp;
    }

    public Weapon getSwitchedWeapon() {
        return switchedWeapon;
    }

    /*
    -----------------SETTERS-----------------------
     */

    public void setDrewPowerUp(CopyOnWriteArrayList<Powerup> drewPowerUp) {
        this.drewPowerUp = drewPowerUp;
    }

    public void setSwitchedWeapon(Weapon switchedWeapon) {
        this.switchedWeapon = switchedWeapon;
    }

    /*
    ----------------OTHER METHODS-------------------
    */





    /**
     * this method allows the player to play the first turn
     *
     * @param player is the player who has to play the first turn
     */
    public void first(Player player) {
        firstSpawn(player, false);
        standard(player, false);
    }

    /**
     * Performs the first terminator turn.
     *
     * @param player is the first player.
     */

    public void firstTerminator(Player player) {

        if (player.isFirstPlayer()) {

            firstSpawn(player, true);
            standard(player, false);

        }

        if (!player.isFirstPlayer()) {

            firstSpawn(player, false);
            standard(player, true);

        }
    }


    /**
     * this method allows the player to play a standard turn
     *
     * @param player is the player who has to play the turn
     */
    public void standard(Player player, boolean terminator) {

        boolean usedPowerUp;
        boolean terminatorPerformed = false;




        if (terminator && terminatorKilled) {


            String spawnTerminator = Server.updateWithAnswer(player, "Scegli un colore tra rosso, blu e giallo per far rinascere il Terminator nella corrispondente Spawn Cell");


            boolean spawned = false;

            if (spawnTerminator.equalsIgnoreCase("rosso") || spawnTerminator.equalsIgnoreCase("blu") || spawnTerminator.equalsIgnoreCase("giallo")) {

                spawned = true;

            }


            while (!spawned) {

                Server.update(player, Message.inputError());
                spawnTerminator = Server.updateWithAnswer(player, "Scegli un colore tra rosso, blu e giallo per far rinascere il Terminator nella corrispondente Spawn Cell");

                if (spawnTerminator.equalsIgnoreCase("rosso") || spawnTerminator.equalsIgnoreCase("blu") || spawnTerminator.equalsIgnoreCase("giallo")) {

                    spawned = true;

                }
            }

            if (spawnTerminator.equalsIgnoreCase("rosso")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getRedRoom().getCells().get(0));
                Server.updateAll("Il Terminator è respawnato nella Spawn Cell rossa!");


            }


            if (spawnTerminator.equalsIgnoreCase("blu")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getBlueRoom().getCells().get(0));
                Server.updateAll("Il Terminator è respawnato nella Spawn Cell blu!");


            }

            if (spawnTerminator.equalsIgnoreCase("giallo")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getYellowRoom().getCells().get(0));
                Server.updateAll("Il Terminator è respawnato nella Spawn Cell gialla!");


            }


            terminatorKilled = false;


        }

        for (int i = 0; i < 3; i++) {

            usedPowerUp = Action.usePowerUp(player);

            if (!usedPowerUp) {
                break;
            }
        }

        if (terminator) {

            if (!terminatorPerformed) {

                terminatorPerformed = Action.performTerminator(player, false);
            }
        }


        Action.perform(player);


        for (int i = 0; i < 3; i++) {

            usedPowerUp = Action.usePowerUp(player);

            if (!usedPowerUp) {
                break;
            }
        }

        if (terminator) {

            if (!terminatorPerformed) {

                terminatorPerformed = Action.performTerminator(player, false);
            }
        }


        Action.perform(player);

        for (int i = 0; i < 3; i++) {

            usedPowerUp = Action.usePowerUp(player);

            if (!usedPowerUp) {
                break;
            }
        }
        if (terminator) {

            if (!terminatorPerformed) {

                terminatorPerformed = Action.performTerminator(player, true);
            }
        }


        Action.reload(player);

        //Risolvo la board e respawno i morti

        CopyOnWriteArrayList<Player> deadPlayers = new CopyOnWriteArrayList<>();

        for (Player dead : Server.connectedPlayers) {

            if (Check.death(dead) == 0) {
                continue;
            } else {

                Server.updateAll(dead.getNickname() + " è stato ucciso da " + player.getNickname());
                deadPlayers.add(dead);
            }


        }

        //DOUBLE KILL

        if (deadPlayers.size() > 1) {

            Server.updateAll(player.getNickname() + " ha appena fatto una doppia uccisione!");
            player.setScore(player.getScore() + 1);

        }

        for (Player dead : deadPlayers) {


            boolean overkill = false;

            if (Check.death(dead) == 2) {

                Server.updateAll(player.getNickname() + " ha infierito su " + dead.getNickname());
                overkill = true;
            }

            Check.resolveBoard(dead, overkill);

            if (terminator) {

                if (dead.getNickname().equalsIgnoreCase("terminator")) {

                    Server.updateAll("Il Terminator è stato ucciso da " + player.getNickname());
                    deadPlayers.remove(dead);
                    terminatorKilled = true;
                    continue;

                }

            }


            respawn(dead);

        }

        Server.update(player, "Hai finito il tuo turno!");
        Server.updateAll("Il turno di " + player.getNickname() + " è finito!");


    }


    /**
     * allows the player to spawn in the map for the first time
     *
     * @param player is the player that has to spawn
     */

    private void firstSpawn(Player player, boolean terminator) {

        try {
            Interaction.drawPowerUp(player);
            Interaction.drawPowerUp(player);
        } catch (Exception e) {
            System.out.println("Errore: mazzi non inizializzati");
        }

        if (terminator) {

            String spawnTerminator = Server.updateWithAnswer(player, "Hai pescato " + player.getPlayerboard().getPowerups().get(0).getName() + " e " + player.getPlayerboard().getPowerups().get(1).getName() + ".\nOra scegli un colore tra rosso, blu e giallo per posizionare il Terminator nella Spawn Cell di quel colore.");

            boolean spawned = false;

            if (spawnTerminator.equalsIgnoreCase("rosso") || spawnTerminator.equalsIgnoreCase("blu") || spawnTerminator.equalsIgnoreCase("giallo")) {

                spawned = true;

            }


            while (!spawned) {

                Server.update(player, Message.inputError());
                spawnTerminator = Server.updateWithAnswer(player, "Hai pescato " + player.getPlayerboard().getPowerups().get(0).getName() + " e " + player.getPlayerboard().getPowerups().get(1).getName() + ".\nOra scegli un colore tra rosso, blu e giallo per posizionare il Terminator nella Spawn Cell di quel colore.");

                if (spawnTerminator.equalsIgnoreCase("rosso") || spawnTerminator.equalsIgnoreCase("blu") || spawnTerminator.equalsIgnoreCase("giallo")) {

                    spawned = true;

                }
            }

            if (spawnTerminator.equalsIgnoreCase("rosso")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getRedRoom().getCells().get(0));
                Server.updateAll("Il terminator è spawnato nella Spawn Cell rossa!");

            }


            if (spawnTerminator.equalsIgnoreCase("blu")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getBlueRoom().getCells().get(0));
                Server.updateAll("Il terminator è spawnato nella Spawn Cell blu!");

            }

            if (spawnTerminator.equalsIgnoreCase("giallo")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getYellowRoom().getCells().get(0));
                Server.updateAll("Il terminator è spawnato nella Spawn Cell gialla!");


            }


        }


        boolean chosen = false;

        while (!chosen) {

            String powerUp = Server.updateWithAnswer(player, Message.scegliPowerUpSpawn(player.getPlayerboard().getPowerups()));

            try {

                int index = InputCheck.numberCheck(powerUp);

                if (index >= 0 && index <= 1) {

                    pUpSelector(player, index);
                    chosen = true;

                } else {
                    Server.update(player, Message.inputError());
                }

            } catch (NumberFormatException e) {
                Server.update(player, Message.inputError());
            }

        }

    }


    /**
     * it allows the player to respawn in the map after his death
     *
     * @param player is the player that has to respawn
     */

    public void respawn(Player player) {


        try {

            Interaction.drawPowerUp(player);
        } catch (EmptyDeckException e) {
            Interaction.shuffleAndDraw(player);
        } catch (LimitPowerUpException e) {

            //Anche se il giocatore ne ha già 3, deve comunque poterlo pescare.
            CopyOnWriteArrayList<Powerup> powerUps = player.getPlayerboard().getPowerups();
            Powerup drawnPowerUp = Board.getPowerUpDeck().get(0);
            Board.getPowerUpDeck().remove(drawnPowerUp);
            powerUps.add(drawnPowerUp);
            player.getPlayerboard().setPowerups(powerUps);

        }

        boolean chosen = false;

        while (!chosen) {

            String powerUp = Server.updateWithAnswer(player, Message.scegliPowerUpSpawn(player.getPlayerboard().getPowerups()));

            try {

                int index = InputCheck.numberCheck(powerUp);

                if (index >= 0 && index <= player.getPlayerboard().getPowerups().size() - 1) {

                    chosen = true;
                    pUpSelector(player, index);

                }

                Server.update(player, Message.inputError());
            } catch (NumberFormatException e) {
                Server.update(player, Message.inputError());
            }

        }


    }


    /**
     * it allows the player to play his FinalFrenzy turn
     *
     * @param player is who has to play the FinalFrenzy turn
     */

    public void frenzy(Player player, boolean afterFirstPlayer, boolean terminator) {

        CopyOnWriteArrayList<Player> deadPlayers;


        boolean usedPowerUp;
        boolean terminatorPerformed = false;

        if (terminator && terminatorKilled) {


            String spawnTerminator = Server.updateWithAnswer(player, "Scegli un colore tra rosso, blu e giallo per far rinascere il Terminator nella corrispondente Spawn Cell");


            boolean spawned = false;

            if (spawnTerminator.equalsIgnoreCase("rosso") || spawnTerminator.equalsIgnoreCase("blu") || spawnTerminator.equalsIgnoreCase("giallo")) {

                spawned = true;

            }


            while (!spawned) {

                Server.update(player, Message.inputError());
                spawnTerminator = Server.updateWithAnswer(player, "Scegli un colore tra rosso, blu e giallo per far rinascere il Terminator nella corrispondente Spawn Cell");

                if (spawnTerminator.equalsIgnoreCase("rosso") || spawnTerminator.equalsIgnoreCase("blu") || spawnTerminator.equalsIgnoreCase("giallo")) {

                    spawned = true;

                }
            }

            if (spawnTerminator.equalsIgnoreCase("rosso")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getRedRoom().getCells().get(0));
                Server.updateAll("Il Terminator è respawnato nella Spawn Cell rossa!");


            }


            if (spawnTerminator.equalsIgnoreCase("blu")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getBlueRoom().getCells().get(0));
                Server.updateAll("Il Terminator è respawnato nella Spawn Cell blu!");


            }

            if (spawnTerminator.equalsIgnoreCase("giallo")) {

                Server.connectedPlayers.get(Server.connectedPlayers.size() - 1).setPosition(Board.getMap().getYellowRoom().getCells().get(0));
                Server.updateAll("Il Terminator è respawnato nella Spawn Cell gialla!");


            }


            terminatorKilled = false;


        }




        for (int i = 0; i < 3; i++) {

            usedPowerUp = Action.usePowerUp(player);

            if (!usedPowerUp) {
                break;
            }
        }

        if (terminator) {

            if (!terminatorPerformed) {
                terminatorPerformed = Action.performTerminator(player, false);
            }
        }


        Action.performFrenzy(player, afterFirstPlayer);


        for (int i = 0; i < 3; i++) {

            usedPowerUp = Action.usePowerUp(player);

            if (!usedPowerUp) {
                break;
            }
        }

        if (terminator) {

            if (!terminatorPerformed) {
                terminatorPerformed = Action.performTerminator(player, false);
            }
        }

        Action.performFrenzy(player, afterFirstPlayer);


        for (int i = 0; i < 3; i++) {

            usedPowerUp = Action.usePowerUp(player);

            if (!usedPowerUp) {
                break;
            }
        }

        if (terminator) {

            if (!terminatorPerformed) {
                terminatorPerformed = Action.performTerminator(player, true);
            }
        }


        Action.reload(player);


        //Risolvo la board e respawno i morti

        deadPlayers = respawner(player);


        //DOUBLE KILL

        if (deadPlayers.size() > 1) {

            Server.updateAll(player.getNickname() + " ha fatto una doppia uccisione!");
            player.setScore(player.getScore() + 1);

        }


        for (Player dead : deadPlayers) {

            boolean overkill = false;

            if (Check.death(dead) == 2) {
                Server.updateAll(player.getNickname() + " ha infierito su " + dead.getNickname());
                overkill = true;
            }


            Check.resolveFrenzyboard(dead, overkill);

            if (terminator) {

                if (deadPlayers.contains(Server.connectedPlayers.get(Server.connectedPlayers.size() - 1))) {

                    Server.updateAll("Il terminator è stato ucciso da " + player.getNickname());
                    terminatorKilled = true;
                    deadPlayers.remove(Server.connectedPlayers.get(Server.connectedPlayers.size() - 1));
                    continue;

                }

            }


            respawn(dead);
            Interaction.turnPlayerboard(dead);

        }

        Server.update(player, "Hai finito il tuo turno!");
    }


    /**
     * Asks a player to choose a powerup for the spawn action.
     *
     * @param player is the player spwaning.
     * @param index  is the index of the powerups array, containing the position of the topdeck card
     */

    private static void pUpSelector(Player player, int index) {

        Powerup chosenPU = player.getPlayerboard().getPowerups().get(index);
        Server.updateAll(Message.powerUpSpawnScelto(player, chosenPU));
        Interaction.discardPowerUp(player, chosenPU);

        if (chosenPU.getColour() == Colour.RED) {

            CopyOnWriteArrayList<Cell> spawnRoom = Board.getMap().getRedRoom().getCells();

            for (Cell cell : spawnRoom) {
                if (cell.getName().equalsIgnoreCase("SpawnCell")) {
                    player.setPosition(cell);
                    Server.updateAll(Message.spawn(player, chosenPU.getColour()));
                }
            }


        }

        if (chosenPU.getColour() == Colour.BLUE) {

            CopyOnWriteArrayList<Cell> spawnRoom = Board.getMap().getBlueRoom().getCells();

            for (Cell cell : spawnRoom) {
                if (cell.getName().equalsIgnoreCase("SpawnCell")) {
                    player.setPosition(cell);
                    Server.updateAll(Message.spawn(player, chosenPU.getColour()));
                }
            }


        }

        if (chosenPU.getColour() == Colour.YELLOW) {

            CopyOnWriteArrayList<Cell> spawnRoom = Board.getMap().getYellowRoom().getCells();

            for (Cell cell : spawnRoom) {
                if (cell.getName().equalsIgnoreCase("SpawnCell")) {
                    player.setPosition(cell);
                    Server.updateAll(Message.spawn(player, chosenPU.getColour()));
                }
            }


        }

    }

    /**
     * Looks for all the dead player during the turn.
     *
     * @param killer is the player that killed the respawning champion.
     * @return all the dead players.
     */
    private static CopyOnWriteArrayList<Player> respawner(Player killer) {

        CopyOnWriteArrayList<Player> deadPlayers = new CopyOnWriteArrayList<>();

        for (Player dead : Server.connectedPlayers) {

            if (Check.death(dead) == 0) {
                continue;
            } else {
                deadPlayers.add(dead);
                Server.updateAll(dead.getNickname() + " è stato ucciso da " + killer.getNickname());
            }

        }

        return deadPlayers;

    }


    @Override
    public void run() {

        if (Match.first){

            first(Match.executor);
            Match.first = false;

            synchronized (Match.lock) {
                Match.lock.notify();
            }

        }


        if (Match.firstTerminator){

            firstTerminator(Match.executor);
            Match.firstTerminator = false;

            synchronized (Match.lock) {
                Match.lock.notify();
            }

        }


        if (Match.frenzy){

            frenzy(Match.executor, Match.afterFirstPlayer, Match.terminator);
            Match.frenzy = false;
            synchronized (Match.lock) {
                Match.lock.notify();
            }

        }


        if (Match.standard){

            standard(Match.executor, Match.terminator);
            Match.standard = false;
            synchronized (Match.lock) {
                Match.lock.notify();
            }


        }



    }
}