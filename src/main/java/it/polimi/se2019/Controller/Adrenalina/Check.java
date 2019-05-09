package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Adrenalina.Interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static it.polimi.se2019.Model.Connection.*;
import static it.polimi.se2019.Network.Server.connectedPlayers;
import static it.polimi.se2019.Network.Server.update;

import it.polimi.se2019.Model.Board.*;

/**
 * This class contains all the methods used to check if an interaction during the game is legit.
 */
public class Check {


    /**
     * Checks if a player has sustained enough damage to be declared dead.
     *
     * @param damaged is the player that receives damage.
     * @return an int value. 0 if the player hasn't been damaged enough to die, 1 if the player receives damage only to
     * die, 2 if the attacker overkills.
     */
    public static int death(Player damaged) {

        /*
        saving the number of the damages on the playerboard
         */

        int damage = damaged.getPlayerboard().getDamage().size();

        /*
        checking the damage
         */


        if (damage == 11) {
            return 1;
        }

        if (damage > 11) {
            return 2;
        } else {
            return 0;
        }

    }


    /**
     * When a player kills another player, this method resolves the board and assigns the players
     * their scores.
     *
     * @param killed the defeated player whose board needs to be resolved.
     */
    public void resolveBoard(Player killed, Board board, Player killer, boolean overkill) {

        ArrayList<Token> damages = new ArrayList<Token>();
        damages = killed.getPlayerboard().getDamage();

        /*
        First blood
         */

        for (Player player1 : connectedPlayers) {
            if (player1.getPlayerboard().getChampionName().equals(damages.get(0).getChampionName())) {
                /*
                aggiungo un punto a chi ha fatto il primo sangue
                 */
                player1.setScore(player1.getScore() + 1);
            }
        }

        /*
        assigning the points to the killers
         */

        class PlayerWithScore {


            Player player;
            int score;


        }

        ArrayList<PlayerWithScore> playersWithScore = new ArrayList<>();

        for (Player player : connectedPlayers) {

            PlayerWithScore playerScore = new PlayerWithScore();

            playerScore.player = player;

            playerScore.score = 0;

            for (Token damage : damages) {

                if (damage.getChampionName().equals(player.getPlayerboard().getChampionName())) {

                    playerScore.score++;

                }

            }

            playersWithScore.add(playerScore);

        }

        /*
        creo un array ordinato per ordine temporale di sparo
         */

        ArrayList<Player> whoShotFirst = new ArrayList<>();

        for (Token damage : damages) {

            for (Player player : connectedPlayers) {

                if (damage.getChampionName().equals(player.getPlayerboard().getChampionName())) {

                    if (!whoShotFirst.contains(player)) {

                        whoShotFirst.add(player);

                    }

                }
            }
        }


        Comparator<PlayerWithScore> comparator = new Comparator<PlayerWithScore>() {
            @Override
            public int compare(PlayerWithScore o1, PlayerWithScore o2) {

                if (o1.score > o2.score) {

                    return 1;

                }

                if (o1.score == o2.score) {

                    if (whoShotFirst.indexOf(o1.player) < whoShotFirst.indexOf(o2.player)) {
                        return 1;
                    }

                    if (whoShotFirst.indexOf(o1.player) > whoShotFirst.indexOf(o2.player)) {
                        return -1;
                    }


                }

                if (o1.score < o2.score) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(playersWithScore, comparator);

        int playerboardCounter = 0;

        for (PlayerWithScore attacker : playersWithScore) {

            if (attacker.score != 0) {

                attacker.player.setScore(killer.getScore() + killed.getPlayerboard().getPlayerboardValue().get(playerboardCounter));

                playerboardCounter++;
            }

        }

        /*
        placing the kill and overkill tokens on the MortalBlow track
         */

        Interaction interaction = new Interaction();

        interaction.claimSkull(board, killer, killed, overkill);



        /*
        changing the value of the playerboard
         */

        killed.getPlayerboard().getPlayerboardValue().remove(0);


        /*
        clearing the damages
         */

        for (Token damage : damages) {
            damages.remove(damage);
        }


    }

    /**
     * Checks if the player that is being attacked has markers to add to the total damage.
     *
     * @param active  is the attacker, used to look for attacker's markers.
     * @param passive is the player being attacked that needs te markers check to resolve total damage.
     */
    public void markers(Player active, Player passive) {


        /*
        counter
        */

        int counter = 0;

        for (Token marker : passive.getPlayerboard().getMarker()) {


            if (marker.getChampionName().equals(active.getPlayerboard().getChampionName())) {

                /*
                aggiunge il marker ai danni
                 */

                passive.getPlayerboard().getDamage().add(counter, marker);

                /*
                lo rimuove dai marker
                 */

                passive.getPlayerboard().getMarker().remove(counter);

            }


            counter++;


        }

    }

    /**
     * Checks the amount of damages dealt to a player by another player.
     *
     * @param attacker player that has dealt the damages.
     * @param defender player that has taken the damages.
     * @return the amount of damage received by the attacker.
     */
    private int damages(Player attacker, Player defender) {

        int damagesAmount = 0;

        ArrayList<Token> damages = defender.getPlayerboard().getDamage();

        for (Token damage : damages) {
            if (damage.getChampionName().equals(attacker.getPlayerboard().getChampionName())) {
                damagesAmount++;
            }
        }

        return damagesAmount;
    }

    /**
     * Checks if a buyer can afford a weapon found in the spawn room. If not, returns false.
     *
     * @param buyer  the buyer of the weapon.
     * @param weapon the weapon to be checked.
     * @return true if a player can afford a weapon.
     */
    public boolean affordableWeapon(Player buyer, Weapon weapon) {

        if (buyer.getPlayerboard().getAmmoCubes().getBlue() >= weapon.getPrice().getBlue() &&
                buyer.getPlayerboard().getAmmoCubes().getRed() >= weapon.getPrice().getRed() &&
                buyer.getPlayerboard().getAmmoCubes().getYellow() >= weapon.getPrice().getYellow()) {

            return true;

        } else {
            return false;
        }
    }

    /**
     * Checks if a player can afford to reload a weapon that is holding.
     *
     * @param player the player that wants to reload.
     * @param weapon the weapon to reload.
     * @return true if the player can afford to reload.
     */
    public static boolean affordableReload(Player player, Weapon weapon) {

        if (player.getPlayerboard().getAmmoCubes().getBlue() >= weapon.getRechargeCost().getBlue() &&
                player.getPlayerboard().getAmmoCubes().getRed() >= weapon.getRechargeCost().getRed() &&
                player.getPlayerboard().getAmmoCubes().getYellow() >= weapon.getRechargeCost().getYellow()) {

            return true;

        } else {
            return false;
        }

    }


    /**
     * Generic check: if the player can afford the cost,returns true.
     *
     * @param player the buyer
     * @param cost   the cost
     * @return true if the player can afford the cost
     */
    public static boolean affordable(Player player, Rybamount cost) {

        int blueAvailable = player.getPlayerboard().getAmmoCubes().getBlue();
        int yellowAvailable = player.getPlayerboard().getAmmoCubes().getYellow();
        int redAvailable = player.getPlayerboard().getAmmoCubes().getRed();

        ArrayList<Powerup> playerPowerups = player.getPlayerboard().getPowerups();

        for (Powerup powerup : playerPowerups) {

            blueAvailable = blueAvailable + powerup.getTradeValue().getBlue();
            yellowAvailable = yellowAvailable + powerup.getTradeValue().getYellow();
            redAvailable = redAvailable + powerup.getTradeValue().getYellow();

        }

        int yellowCost = cost.getYellow();
        int blueCost = cost.getBlue();
        int redCost = cost.getRed();

        if (yellowCost > yellowAvailable || redCost > yellowAvailable || blueCost > blueAvailable) {
            return false;
        }

        return true;

    }

    /**
     * Checks if a player has sustained enough damage to use enhanced pickup.
     *
     * @param player is the player to be checked.
     * @return true if the player has sustained enough damage.
     */
    public boolean availableEnhancedPickUp(Player player) {

        if (player.getPlayerboard().getDamage().size() >= 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a player has sustained enough damage to use enhanced shoot.
     *
     * @param player is the player to be checked.
     * @return true if the player has sustained enough damage.
     */
    public boolean availableEnhancedShoot(Player player) {

        if (player.getPlayerboard().getDamage().size() >= 6) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Runs to limit the total amount of ammo per color to 3 ammos. For example, if a player has more than 3 red
     * ammo cubes this method will return the amount of cubes to 3.
     *
     * @param player is the player to be checked.
     */
    public static void limitAmmoCubes(Player player) {

        if (player.getPlayerboard().getAmmoCubes().getYellow() > 3) {
            player.getPlayerboard().getAmmoCubes().setYellowCubes(3);
        }

        if (player.getPlayerboard().getAmmoCubes().getRed() > 3) {
            player.getPlayerboard().getAmmoCubes().setRedCubes(3);
        }

        if (player.getPlayerboard().getAmmoCubes().getBlue() > 3) {
            player.getPlayerboard().getAmmoCubes().setBlueCubes(3);
        }

    }

    /**
     * Checks if a player has reached the limit of weapons he can keep.
     *
     * @param player is the player to be checked.
     * @return true if the player already has got 3 weapons
     */
    public boolean limitWeapon(Player player) {

        if (player.getPlayerboard().getWeapons().size() >= 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a player has reached the limit of powerUps he can keep
     *
     * @param player is the player to be checked.
     */
    public static boolean limitPowerUp(Player player) {

        if (player.getPlayerboard().getPowerups().size() >= 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A player can place up to 3 markers on each enemy playerboard, and the player can have up to 3 marker from each
     * other player. This method runs to check if the number of markers has reached limit and fix it.
     *
     * @param defender is who has the markers to check
     * @param attacker is who gives the markers
     */
    public static void limitMarkers(Player defender, Player attacker) {

        ArrayList<Token> markers = defender.getPlayerboard().getMarker();

        int attackerMarkers = 0;


        for (Token marker : markers) {

            if (marker.getChampionName().equals(attacker.getPlayerboard().getChampionName())) {
                attackerMarkers++;
            }
        }

        if (attackerMarkers > 3) {
            attackerMarkers = 3;
        }


    }

    /**
     * Returns an arraylist containing all the players in the field of view of a player.
     *
     * @param player is the player that needs the fov check.
     * @return all the others players that are in the fov.
     */
    public static ArrayList<Player> visiblePlayers(Player player) {

        ArrayList<Player> visiblePlayers = new ArrayList<Player>();

        for (Player player1 : connectedPlayers) {


            /*
            tra i visibili non ci deve essere lui stesso
             */
            if (!player1.equals(player)) {


                if (player.getPosition().getColour() == player1.getPosition().getColour()) {
                    if (!visiblePlayers.contains(player1)) {
                        visiblePlayers.add(player1);
                    }
                }

                if (player.getPosition().getUpConnection().getType().equals(DOOR)) {
                    if (player1.getPosition().getColour() == player.getPosition().getUpConnection().getConnectedCell().getColour()) {
                        if (!visiblePlayers.contains(player1)) {
                            visiblePlayers.add(player1);
                        }
                    }
                }

                if (player.getPosition().getRightConnection().getType().equals(DOOR)) {
                    if (player1.getPosition().getColour() == player.getPosition().getRightConnection().getConnectedCell().getColour()) {
                        if (!visiblePlayers.contains(player1)) {
                            visiblePlayers.add(player1);
                        }
                    }
                }

                if (player.getPosition().getLeftConnection().getType().equals(DOOR)) {
                    if (player1.getPosition().getColour() == player.getPosition().getLeftConnection().getConnectedCell().getColour()) {
                        if (!visiblePlayers.contains(player1)) {
                            visiblePlayers.add(player1);
                        }
                    }
                }

                if (player.getPosition().getDownConnection().getType().equals(DOOR)) {
                    if (player1.getPosition().getColour() == player.getPosition().getDownConnection().getConnectedCell().getColour()) {
                        if (!visiblePlayers.contains(player1)) {
                            visiblePlayers.add(player1);
                        }
                    }
                }
            }


        }

        return visiblePlayers;

    }

    /**
     * Returns all the cells that are a maximum of n steps away from player.
     *
     * @param player is the player that wants the check to move.
     * @param steps  is the number of steps to do.
     * @return an arraylist of reachable cells.
     */
    public static ArrayList<Cell> reachableCells(Player player, int steps) {

        ArrayList<Cell> reachableCells = new ArrayList<>();

        Cell position = player.getPosition();

        if (steps == 1) {

            /*
            controllo solo le celle raggiungibili dalla posizione del player
             */

            if (position.getUpConnection().getType().equals(DOOR) || position.getUpConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getRightConnection().getType().equals(DOOR) || position.getRightConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getLeftConnection().getType().equals(DOOR) || position.getLeftConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getDownConnection().getType().equals(DOOR) || position.getDownConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

        }


        if (steps > 1) {

            /*
            prima controllo la mia posizione
             */

            if (position.getUpConnection().getType().equals(DOOR) || position.getUpConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getRightConnection().getType().equals(DOOR) || position.getRightConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getLeftConnection().getType().equals(DOOR) || position.getLeftConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getDownConnection().getType().equals(DOOR) || position.getDownConnection().getType().equals(FREE)) {

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            /*
            poi controllo le celle raggiungibili da quelle arrivabili dopo il primo step
             */


            for (int i = 1; i < steps - 1; i++) {

                ArrayList<Cell> copiesToCheck = new ArrayList<>();

                for (Cell cell : reachableCells) {
                    copiesToCheck.add(cell);
                }

                for (Cell cell : copiesToCheck) {

                    if (cell.getUpConnection().getType().equals(DOOR) || cell.getUpConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getUpConnection().getConnectedCell())) {

                            reachableCells.add(cell.getUpConnection().getConnectedCell());

                        }

                    }

                    if (cell.getRightConnection().getType().equals(DOOR) || cell.getRightConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getRightConnection().getConnectedCell())) {

                            reachableCells.add(cell.getRightConnection().getConnectedCell());

                        }

                    }

                    if (cell.getLeftConnection().getType().equals(DOOR) || cell.getLeftConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getLeftConnection().getConnectedCell())) {

                            reachableCells.add(cell.getLeftConnection().getConnectedCell());

                        }

                    }

                    if (cell.getDownConnection().getType().equals(DOOR) || cell.getDownConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getDownConnection().getConnectedCell())) {

                            reachableCells.add(cell.getDownConnection().getConnectedCell());

                        }

                    }


                }

            }

        }

        return reachableCells;

    }

    public static boolean checkFrenzy(){

        ArrayList<MortalBlow> mortalBlows = Board.getMortalBlowTrack();
        int mbCounter = 0;

        for (MortalBlow mortalBlow:mortalBlows){

            if(mortalBlow.isSkull()){

                mbCounter++;

            }


        }

        if (mbCounter == 0){
            return true;
        }


        return false;


    }



    public void resolveFrenzyboard(Player killed, Player killer, Board board, boolean overkill) {


        ArrayList<Token> damages = new ArrayList<Token>();
        damages = killed.getPlayerboard().getDamage();




        /*
        assigning the points to the killers
         */

        class PlayerWithScore {


            Player player;
            int score;


        }

        ArrayList<PlayerWithScore> playersWithScore = new ArrayList<>();

        for (Player player : connectedPlayers) {

            PlayerWithScore playerScore = new PlayerWithScore();

            playerScore.player = player;

            playerScore.score = 0;

            for (Token damage : damages) {

                if (damage.getChampionName().equals(player.getPlayerboard().getChampionName())) {

                    playerScore.score++;

                }

            }

            playersWithScore.add(playerScore);

        }

        /*
        creo un array ordinato per ordine temporale di sparo
         */

        ArrayList<Player> whoShotFirst = new ArrayList<>();

        for (Token damage : damages) {

            for (Player player : connectedPlayers) {

                if (damage.getChampionName().equals(player.getPlayerboard().getChampionName())) {

                    if (!whoShotFirst.contains(player)) {

                        whoShotFirst.add(player);

                    }

                }
            }
        }


        Comparator<PlayerWithScore> comparator = new Comparator<PlayerWithScore>() {
            @Override
            public int compare(PlayerWithScore o1, PlayerWithScore o2) {

                if (o1.score > o2.score) {

                    return 1;

                }

                if (o1.score == o2.score) {

                    if (whoShotFirst.indexOf(o1.player) < whoShotFirst.indexOf(o2.player)) {
                        return 1;
                    }

                    if (whoShotFirst.indexOf(o1.player) > whoShotFirst.indexOf(o2.player)) {
                        return -1;
                    }


                }

                if (o1.score < o2.score) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(playersWithScore, comparator);

        int playerboardCounter = 0;

        for (PlayerWithScore attacker : playersWithScore) {

            if (attacker.score != 0) {

                attacker.player.setScore(killer.getScore() + killed.getPlayerboard().getPlayerboardValue().get(playerboardCounter));

                playerboardCounter++;
            }

        }


        /*
        aggiungo i token kill e overkill al tracciato colpo mortale
         */

        MortalBlow kill = new MortalBlow();
        kill.setKiller(killer);
        kill.setSkull(false);
        kill.setOverkill(overkill);

        board.getMortalBlowTrack().add(kill);



         /*
        clearing the damages
         */

        for (Token damage : damages) {
            damages.remove(damage);
        }

    }

    /**
     * This method runs after the final frenzy, and checks (/resolve) all the last points remaining on the board.
     * Then it proceeds to sum up the points of each player and declare a winner
     * (or a chart with the points of all the players).
     *
     * @param board is the board to solve.
     * @return the winner.
     */
    public void winner(Board board) {

        /*
        risolvo le finalfrenzy playerboard
         */

        for (Player player : connectedPlayers) {

            ArrayList<Token> damages = new ArrayList<Token>();
            damages = player.getPlayerboard().getDamage();


            /*
            primo sangue se la board non è frenzy
             */

            if (player.getPlayerboard().isFrenzyboard() == false) {

                for (Player attacker : connectedPlayers) {
                    if (attacker.getPlayerboard().getChampionName().equals(damages.get(0).getChampionName())) {
                /*
                aggiungo un punto a chi ha fatto il primo sangue
                 */
                        attacker.setScore(attacker.getScore() + 1);
                    }
                }

            }




        /*
        assigning the points to the killers
         */

            class PlayerWithScore {


                Player player;
                int score;


            }

            ArrayList<PlayerWithScore> playersWithScore = new ArrayList<>();

            for (Player attacker : connectedPlayers) {

                PlayerWithScore playerScore = new PlayerWithScore();

                playerScore.player = attacker;

                playerScore.score = 0;

                for (Token damage : damages) {

                    if (damage.getChampionName().equals(player.getPlayerboard().getChampionName())) {

                        playerScore.score++;

                    }

                }

                playersWithScore.add(playerScore);

            }

        /*
        creo un array ordinato per ordine temporale di sparo
         */

            ArrayList<Player> whoShotFirst = new ArrayList<>();

            for (Token damage : damages) {

                for (Player player1 : connectedPlayers) {

                    if (damage.getChampionName().equals(player1.getPlayerboard().getChampionName())) {

                        if (!whoShotFirst.contains(player1)) {

                            whoShotFirst.add(player1);

                        }

                    }
                }
            }


            Comparator<PlayerWithScore> comparator = new Comparator<PlayerWithScore>() {
                @Override
                public int compare(PlayerWithScore o1, PlayerWithScore o2) {

                    if (o1.score > o2.score) {

                        return 1;

                    }

                    if (o1.score == o2.score) {

                        if (whoShotFirst.indexOf(o1.player) < whoShotFirst.indexOf(o2.player)) {
                            return 1;
                        }

                        if (whoShotFirst.indexOf(o1.player) > whoShotFirst.indexOf(o2.player)) {
                            return -1;
                        }


                    }

                    if (o1.score < o2.score) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            };

            Collections.sort(playersWithScore, comparator);

            int playerboardCounter = 0;

            for (PlayerWithScore attacker : playersWithScore) {

                if (attacker.score != 0) {

                    attacker.player.setScore(attacker.player.getScore() + player.getPlayerboard().getPlayerboardValue().get(playerboardCounter));

                    playerboardCounter++;
                }

            }

        }



        /*
        risoluzione tracciato colpo mortale
         */

        class PlayerWithKills {


            Player player;
            int kills;


        }

        ArrayList<PlayerWithKills> playersWithKills = new ArrayList<>();

        for (Player killer : connectedPlayers) {

            PlayerWithKills playerKills = new PlayerWithKills();

            playerKills.player = killer;

            playerKills.kills = 0;

            for (MortalBlow mortalBlow : board.getMortalBlowTrack()) {

                if (mortalBlow.getKiller().equals(killer)) {

                    playerKills.kills++;

                    if (mortalBlow.isOverkill() == true) {
                        playerKills.kills++;
                    }

                }

            }

            playersWithKills.add(playerKills);

        }


        /*
        creo un array ordinato per ordine temporale di uccisioni
         */

        ArrayList<Player> whoKilledFirst = new ArrayList<>();

        for (MortalBlow mortalBlow : board.getMortalBlowTrack()) {

            for (Player killer : connectedPlayers) {

                if (mortalBlow.getKiller().equals(killer)) {

                    if (!whoKilledFirst.contains(killer)) {

                        whoKilledFirst.add(killer);

                    }

                }
            }
        }

        /*
        creo il comparatore per le parità
         */

        Comparator<PlayerWithKills> comparator1 = new Comparator<PlayerWithKills>() {
            @Override
            public int compare(PlayerWithKills o1, PlayerWithKills o2) {

                if (o1.kills > o2.kills) {

                    return 1;

                }

                if (o1.kills == o2.kills) {

                    if (whoKilledFirst.indexOf(o1.player) < whoKilledFirst.indexOf(o2.player)) {
                        return 1;
                    }

                    if (whoKilledFirst.indexOf(o1.player) > whoKilledFirst.indexOf(o2.player)) {
                        return -1;
                    }


                }

                if (o1.kills < o2.kills) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(playersWithKills, comparator1);

        int mortalBlowCounter = 0;

        for (PlayerWithKills killer : playersWithKills) {

            if (killer.kills != 0) {

                killer.player.setScore(killer.player.getScore() + board.getMortalBlowTrackValue().get(mortalBlowCounter));

                mortalBlowCounter++;
            }

        }



        /*
        controllo dei punti, risoluzione parità e calcolo del vincitore
         */

        class PotentialWinner {

            Player player;

            int points;


            int scoreOnMortalBlowTrack;

            boolean tie;

        }

        /*
        ora viene creato un array dei possibili vincitori che contiene in ogni posto un giocatore,
        i suoi punti totali e i suoi punti ottenuti nel tracciato colpo mortale(questi ultimi servono a gestire
        un'eventuale situazione di parità)
         */

        ArrayList<PotentialWinner> potentialWinners = new ArrayList<>();

        for (Player player : connectedPlayers) {

            PotentialWinner potentialWinner = new PotentialWinner();

            potentialWinner.points = player.getScore();

            potentialWinner.player = player;

            /*
            inizialmente setto a false il flag parità di ogni potenziale vincitore
             */

            potentialWinner.tie = false;


            /*
            all'inizio deve valere zero perchè se non lo modifico dentro il for poi si fotte
             */
            potentialWinner.scoreOnMortalBlowTrack = 0;

            mortalBlowCounter = 0;

            /*
            qui viene assegnato ad ogni player il suo punteggio nel tracciato colpo mortale
             */

            for (PlayerWithKills killer : playersWithKills) {

                if (killer.kills != 0) {

                    if (killer.player.equals(player)) {

                        potentialWinner.scoreOnMortalBlowTrack = board.getMortalBlowTrackValue().get(mortalBlowCounter);

                    }

                    mortalBlowCounter++;
                }

            }

            potentialWinners.add(potentialWinner);

        }



        /*
        comparatore per poter ordinare Potential Winners prima in base ai punti totali e poi in base a
        quelli ottenuti nel tracciato colpo mortale
         */


        Comparator<PotentialWinner> potentialWinnerComparator = new Comparator<PotentialWinner>() {
            @Override
            public int compare(PotentialWinner o1, PotentialWinner o2) {

                if (o1.points > o2.points) {
                    return 1;
                }

                if (o1.points == o2.points) {

                    if (o1.scoreOnMortalBlowTrack > o2.scoreOnMortalBlowTrack) {

                        return 1;

                    }

                    if (o1.scoreOnMortalBlowTrack == o2.scoreOnMortalBlowTrack) {

                        /*
                        mi segno che i 2 player sono in parità tra di loro
                         */
                        o1.tie = true;
                        o2.tie = true;

                        return 0;

                    } else {
                        return -1;
                    }


                }

                if (o1.points < o2.points) {
                    return -1;
                }

                return 0;
            }
        };

        /*
        ora l'ordinamento in base al comparator appena creato
         */

        Collections.sort(potentialWinners, potentialWinnerComparator);


        /*
        adesso viene stabilito il vincitore
         */

        Player winner = new Player();

        /*
        caso di un solo vincitore
         */

        if (potentialWinners.get(0).tie == false) {

            winner = potentialWinners.get(0).player;

            /*
            notifico a tutti il nome del vincitore
             */

            for (Player player : connectedPlayers) {

                update(player, "Il vincitore è" + winner.getNickname());

            }


        }

        /*
        caso di parità
         */


        if (potentialWinners.get(0).tie == true) {

            winner = null;

            /*
            notifico ai giocatori chi sono i vincitori
             */

            PotentialWinner firstWinner = new PotentialWinner();

            firstWinner = potentialWinners.get(0);

            for (Player player : connectedPlayers) {

                update(player, "I vincitori sono:");

                /*
                stampo primo vincitore
                 */

                update(player, "" + firstWinner.player.getNickname());

                /*
                stampo gli altri
                 */

                for (PotentialWinner potentialWinner : potentialWinners) {
                    if (potentialWinner.points == firstWinner.points && potentialWinner.scoreOnMortalBlowTrack == firstWinner.scoreOnMortalBlowTrack) {

                        update(player, "" + potentialWinner.player.getNickname());

                    }

                }

            }

        }


        /*
        notifico a tutti i giocatori la classifica finale
         */

        for (Player player : connectedPlayers) {

            update(player, "Classifica Finale:");

            for (PotentialWinner potentialWinner : potentialWinners) {

                /*
                faccio vedere per ogni giocatore il nome, il punteggio totale e quello sul tracciato colpo mortale
                 */

                update(player, "Nome: " + potentialWinner.player.getNickname() + " Punteggio Totale: " + potentialWinner.points + " Punti tracciato colpo mortale " + potentialWinner.scoreOnMortalBlowTrack);

            }
        }


    }


    /**
     * Verifies if a cell is a spawn or not
     *
     * @param cell is the cell to check
     * @return true if it's a spawn, false otherwise
     */


    public boolean isSpawn(Cell cell) {

        if (cell.getName().equals("spawnCell")) {
            return true;
        }

        return false;

    }

    public static ArrayList<Cell> visibleSquares(Player user) {

        ArrayList<Cell> visibleSquares = new ArrayList<>();

        Cell position = user.getPosition();

        int room = position.getColour();


        addVisibleCells(visibleSquares, room);

        if (position.getUpConnection().getType().equalsIgnoreCase(DOOR)) {

            room = position.getUpConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);

        }

        if (position.getDownConnection().getType().equalsIgnoreCase(DOOR)) {

            room = position.getDownConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);

        }

        if (position.getLeftConnection().getType().equalsIgnoreCase(DOOR)) {
            room = position.getLeftConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);
        }

        if (position.getRightConnection().getType().equalsIgnoreCase(DOOR)) {
            room = position.getRightConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);
        }

        return visibleSquares;

    }

    private static void addVisibleCells(ArrayList<Cell> visibleSquares, int room) {

        if (room == Colour.RED) {
            visibleSquares.addAll(Board.getMap().getRedRoom().getCells());
        }

        if (room == Colour.YELLOW) {
            visibleSquares.addAll(Board.getMap().getYellowRoom().getCells());
        }

        if (room == Colour.BLUE) {
            visibleSquares.addAll(Board.getMap().getBlueRoom().getCells());
        }

        if (room == Colour.WHITE) {
            visibleSquares.addAll(Board.getMap().getWhiteRoom().getCells());
        }

        if (room == Colour.GREEN) {
            visibleSquares.addAll(Board.getMap().getGreenRoom().getCells());
        }

        if (room == Colour.PURPLE) {
            visibleSquares.addAll(Board.getMap().getPurpleRoom().getCells());
        }

    }

    public static boolean canShot(Player player){

        ArrayList <Weapon> playerWeapons = player.getPlayerboard().getWeapons();

        for(Weapon weapon :playerWeapons){

            if(weapon.getBaseEffect().hasTargets(player) || (!weapon.getAlternativeEffect().equals(null) && weapon.getAlternativeEffect().hasTargets(player))){

                if(weapon.isLoaded()) {
                    return true;
                }

            }
        }



        return false;

    }

    public static boolean canShotEnhanced(Player player){

        Player fakePlayer = new Player();
        fakePlayer.setPlayerboard(new Playerboard());
        fakePlayer.setPosition(player.getPosition());
        fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

        ArrayList <Cell> reachableCells = Check.reachableCells(player, 1);

        for(Cell cell : reachableCells){

            fakePlayer.setPosition(cell);

            for(Weapon weapon : fakePlayer.getPlayerboard().getWeapons()){

                if(weapon.getBaseEffect().hasTargets(player) || (!weapon.getAlternativeEffect().equals(null) && weapon.getAlternativeEffect().hasTargets(player))){

                    if(weapon.isLoaded()) {
                        return true;
                    }

                }
            }

        }

        return false;

    }


}
