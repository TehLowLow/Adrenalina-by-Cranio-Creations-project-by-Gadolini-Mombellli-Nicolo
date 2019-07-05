package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Adrenalina.Exceptions.EmptyDeckException;
import it.polimi.se2019.Controller.Adrenalina.Exceptions.LimitPowerUpException;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * this class contains the methods to manage the links
 * with all the game's elements, such as drawing, discarding...
 */
public class Interaction {


    /*
    ----------------------METHODS--------------------------------
     */


    /**
     * this method is used to allow the player to draw a powerUp from
     * the board
     *
     * @param player is who has to draw the powerUp
     * @throws EmptyDeckException    when there are no more powerUps on the deck.
     * @throws LimitPowerUpException if the player has already 3 power ups
     */

    public static void drawPowerUp(Player player) throws EmptyDeckException, LimitPowerUpException {

        CopyOnWriteArrayList<Powerup> powerUpDeck = Board.getPowerUpDeck();

        if (powerUpDeck.size() > 0) {

            if (Check.limitPowerUp(player)) {

                LimitPowerUpException e = new LimitPowerUpException();
                throw (e);

            } else {

                CopyOnWriteArrayList<Powerup> powerUps = player.getPlayerboard().getPowerups();
                Powerup drawnPowerUp = Board.getPowerUpDeck().get(0);
                Board.getPowerUpDeck().remove(drawnPowerUp);
                powerUps.add(drawnPowerUp);
                player.getPlayerboard().setPowerups(powerUps);

            }

        }

        if (powerUpDeck.size() == 0) {

            EmptyDeckException e = new EmptyDeckException();
            throw (e);
        }

    }


    /**
     * this method is used to allow the player to discard a powerUp
     *
     * @param player  is who has to discard the powerUp
     * @param powerUp is the power up that must be discarded
     */

    public static void discardPowerUp(Player player, Powerup powerUp) {

        CopyOnWriteArrayList<Powerup> discardedPowerUps = Board.getDiscardedPowerUps();
        discardedPowerUps.add(powerUp);
        player.getPlayerboard().getPowerups().remove(powerUp);

    }


    /**
     * this method draws a weapon from the deck and places
     * it on the board. It refills every empty weapon slot left.
     */

    public static void placeWeapons() {

        Map map = Board.getMap();

        CopyOnWriteArrayList<Room> spawnRooms = new CopyOnWriteArrayList<Room>();
        CopyOnWriteArrayList<SpawnCell> spawnCells = new CopyOnWriteArrayList<SpawnCell>();
        spawnRooms.add(map.getBlueRoom());
        spawnRooms.add(map.getYellowRoom());
        spawnRooms.add(map.getRedRoom());

        for (Room room : spawnRooms) {

            CopyOnWriteArrayList<Cell> cells = room.getCells();

            for (Cell cell : cells) {

                if (Check.isSpawn(cell)) {

                    //If the current cell is a spawn cell, it is casted to spawncell and added
                    //to the CopyOnWriteArrayList of spawncells.
                    spawnCells.add((SpawnCell) cell);

                }

            }

        }

        for (SpawnCell spawnCell : spawnCells) {

            CopyOnWriteArrayList availableWeapons = spawnCell.getAvailableWeapons();

            while (availableWeapons.size() < 3) {

                Weapon drawnWeapon = Board.getWeaponDeck().get(0);
                availableWeapons.add(drawnWeapon);
                Board.getWeaponDeck().remove(drawnWeapon);
                spawnCell.setAvailableWeapons(availableWeapons);
            }
        }
    }


    /**
     * this method reloads on the board the Loots that
     * have been picked up during the turn
     */

    public static void placeLoot() {

        CopyOnWriteArrayList<Room> rooms = new CopyOnWriteArrayList<Room>();
        CopyOnWriteArrayList<LootCell> lootCells = new CopyOnWriteArrayList<LootCell>();

        Map map = Board.getMap();

        rooms.add(map.getRedRoom());
        rooms.add(map.getYellowRoom());
        rooms.add(map.getBlueRoom());

        if (map.getWhiteRoom() != null) {
            rooms.add(map.getWhiteRoom());
        }

        if (map.getPurpleRoom() != null) {
            rooms.add(map.getPurpleRoom());
        }

        if (map.getGreenRoom() != null) {
            rooms.add(map.getGreenRoom());
        }

        for (Room room : rooms) {

            for (Cell cell : room.getCells()) {

                if (!Check.isSpawn(cell)) {
                    lootCells.add((LootCell) cell);
                }

            }


        }

        for (LootCell lootCell : lootCells) {

            if (lootCell.getLoot() == null) {

                if (Board.getLootDeck().size() > 0) {
                    lootCell.setLoot(Board.getLootDeck().get(0));
                    Board.getLootDeck().remove(0);
                }

                if (Board.getLootDeck().size() == 0) {

                    recoverLoots();
                    lootCell.setLoot(Board.getLootDeck().get(0));
                    Board.getLootDeck().remove(0);

                }

            }

        }

    }


    /**
     * this method manages a player's rybamount payment
     *
     * @param player        is who has to pay the cubes
     * @param billRybamount are the cubes to pay
     */

    public static void pay(Player player, Rybamount billRybamount) {

        Rybamount playerRybAmount = player.getPlayerboard().getAmmoCubes();

        int yellowCost = billRybamount.getYellow();
        int blueCost = billRybamount.getBlue();
        int redCost = billRybamount.getRed();

        CopyOnWriteArrayList<Powerup> playerPowerUp = player.getPlayerboard().getPowerups();

        int redPU = 0;
        int yellowPU = 0;
        int bluePU = 0;

        int yellowAvailable = playerRybAmount.getYellow();
        int redAvailable = playerRybAmount.getRed();
        int blueAvailable = playerRybAmount.getBlue();

        if (yellowAvailable < yellowCost) {
            forcePayment(player, billRybamount, Colour.YELLOW);
        }

        if (redAvailable < redCost) {
            forcePayment(player, billRybamount, Colour.RED);
        }

        if (blueAvailable < blueCost) {
            forcePayment(player, billRybamount, Colour.BLUE);
        }

        for (Powerup powerup : playerPowerUp) {
            if (powerup.getColour() == Colour.RED) {
                redPU++;
            }
            if (powerup.getColour() == Colour.YELLOW) {
                yellowPU++;
            }
            if (powerup.getColour() == Colour.BLUE) {
                bluePU++;
            }
        }

        if ((redCost != 0 && redCost <= redPU) || (blueCost != 0 && blueCost <= bluePU) || (yellowCost != 0 && yellowCost <= yellowPU)) {
            payWithPowerUp(player, playerPowerUp, billRybamount);
        }

        yellowAvailable = playerRybAmount.getYellow();
        redAvailable = playerRybAmount.getRed();
        blueAvailable = playerRybAmount.getBlue();

        playerRybAmount.setYellowCubes(yellowAvailable - yellowCost);
        playerRybAmount.setRedCubes(redAvailable - redCost);
        playerRybAmount.setBlueCubes(blueAvailable - blueCost);

    }

    /**
     * Checks if a player wants to pay using his powerups instead of the cubes.
     *
     * @param player        is the player that is playing.
     * @param playerPowerUp is the list of powerups of the players.
     * @param billRybamount is the price of the purchase.
     */

    private static void payWithPowerUp(Player player, CopyOnWriteArrayList<Powerup> playerPowerUp, Rybamount billRybamount) {


        Rybamount playerRybAmount = player.getPlayerboard().getAmmoCubes();

        int yellowAvailable = playerRybAmount.getYellow();
        int redAvailable = playerRybAmount.getRed();
        int blueAvailable = playerRybAmount.getBlue();

        boolean hasChosen = false;
        String answer = "";

        while (!hasChosen) {

            if (player.getPlayerboard().getPowerups().size() == 0) {

                Server.update(player, Message.powerupTerminati());
                hasChosen = true;
                continue;

            }

            answer = Server.updateWithAnswer(player, Message.vuoiPagareConPU());

            if (!InputCheck.correctYesNo(answer)) {
                Server.update(player, Message.inputError());
                continue;
            }

            if (InputCheck.yesOrNo(answer)) {

                int index = 0;
                boolean hasChosenPU = false;

                while (!hasChosenPU) {

                    String chosenPUindex = Server.updateWithAnswer(player, Message.payWithPowerUp(playerPowerUp));

                    try {
                        index = InputCheck.numberCheck(chosenPUindex);
                        if (index < 0 || index > playerPowerUp.size() - 1) {
                            Server.update(player, Message.inputError());
                            continue;
                        }
                        hasChosenPU = true;
                    } catch (NumberFormatException e) {
                        Server.update(player, Message.inputError());
                        continue;
                    }

                    Powerup chosenPU = playerPowerUp.get(index);

                    if (chosenPU.getColour() == Colour.YELLOW) {

                        yellowAvailable++;
                        playerPowerUp.remove(chosenPU);
                        Board.getDiscardedPowerUps().add(chosenPU);

                    }

                    if (chosenPU.getColour() == Colour.RED) {

                        redAvailable++;
                        playerPowerUp.remove(chosenPU);
                        Board.getDiscardedPowerUps().add(chosenPU);

                    }

                    if (chosenPU.getColour() == Colour.BLUE) {

                        blueAvailable++;
                        playerPowerUp.remove(chosenPU);
                        Board.getDiscardedPowerUps().add(chosenPU);

                    }


                }


            }

            if (!InputCheck.yesOrNo(answer)) {
                hasChosen = true;
            }

        }

        player.getPlayerboard().getAmmoCubes().setYellowCubes(yellowAvailable);
        player.getPlayerboard().getAmmoCubes().setRedCubes(redAvailable);
        player.getPlayerboard().getAmmoCubes().setBlueCubes(blueAvailable);

    }

    /**
     * If a player has chosen to pay for an effect, and has no cubes, is forced to use a powerup to pay for it.
     *
     * @param player        is the player that is playing.
     * @param billRybamount ids the price for the purchase.
     * @param colour        is the colour of the powerup used to pay.
     */

    private static void forcePayment(Player player, Rybamount billRybamount, int colour) {

        CopyOnWriteArrayList<Powerup> discardables;
        int amountToPay = 0;
        int available = 0;

        if (colour == Colour.YELLOW) {
            amountToPay = billRybamount.getYellow();
            available = player.getPlayerboard().getAmmoCubes().getYellow();
        }

        if (colour == Colour.RED) {
            amountToPay = billRybamount.getRed();
            available = player.getPlayerboard().getAmmoCubes().getRed();
        }

        if (colour == Colour.BLUE) {
            amountToPay = billRybamount.getBlue();
            available = player.getPlayerboard().getAmmoCubes().getBlue();
        }

        while (available < amountToPay) {

            discardables = new CopyOnWriteArrayList<>();

            for (Powerup powerup : player.getPlayerboard().getPowerups()) {

                if (powerup.getColour() == colour) {
                    discardables.add(powerup);
                }
            }

            available = available + convertPowerUp(player, discardables);

        }

        if (colour == Colour.YELLOW) {
            player.getPlayerboard().getAmmoCubes().setYellowCubes(available);
        }

        if (colour == Colour.RED) {
            player.getPlayerboard().getAmmoCubes().setRedCubes(available);
        }

        if (colour == Colour.BLUE) {
            player.getPlayerboard().getAmmoCubes().setBlueCubes(available);
        }


    }

    /**
     * Converts the value of the powerups into cubes, for an easy calculation ofthe purchases.
     *
     * @param player            is the player playing the turn.
     * @param availablePowerups are all the powerups that the player has in the hand.
     * @return the value of the powerup
     */

    private static int convertPowerUp(Player player, CopyOnWriteArrayList<Powerup> availablePowerups) {


        boolean chosen = false;
        int index = 0;
        while (!chosen) {

            String chosenPU = Server.updateWithAnswer(player, Message.payWithPowerUp(availablePowerups));

            try {
                index = InputCheck.numberCheck(chosenPU);
                if (index < 0 || index > availablePowerups.size() - 1) {
                    Server.update(player, Message.inputError());
                    continue;
                }
                chosen = true;
            } catch (NumberFormatException e) {
                Server.update(player, Message.inputError());
                continue;
            }


        }

        Powerup toRemove = availablePowerups.get(index);
        player.getPlayerboard().getPowerups().remove(toRemove);
        Board.getDiscardedPowerUps().add(toRemove);

        return 1;
    }

    /**
     * this method replaces a skull on the MortalBlow track
     * with the killer's Token
     *
     * @param killer   is the player who has to place his Token
     * @param victim   is the killed player
     * @param overkill is a boolean that is 'true' in case of overkill,
     *                 'false' otherwise
     */

    public void claimSkull(Player killer, Player victim, boolean overkill) {

        CopyOnWriteArrayList<MortalBlow> mortalBlowTrack = Board.getMortalBlowTrack();

        int lenght = mortalBlowTrack.size();

        for (int i = 0; i < lenght; i++) {

            MortalBlow currentBlow = mortalBlowTrack.get(i);

            if (currentBlow.isSkull()) {

                currentBlow.setKiller(killer);
                currentBlow.setOverkill(overkill);
                currentBlow.setSkull(false);
                break;

            }

        }

    }

    /**
     * this method turns the playerboard at the beginning
     * of the FinalFrenzy mode
     *
     * @param player is the player linked to the playerboard
     *               to turn
     */

    public static void turnPlayerboard(Player player) {

        Playerboard frenzyboard = new Playerboard();

        Playerboard oldBoard = player.getPlayerboard();

        frenzyboard.setAmmoCubes(oldBoard.getAmmoCubes());
        frenzyboard.setChampionName(oldBoard.getChampionName());
        frenzyboard.setWeapons(oldBoard.getWeapons());
        frenzyboard.setPowerups(oldBoard.getPowerups());
        frenzyboard.setMarker(oldBoard.getMarker());
        frenzyboard.setDamage(new CopyOnWriteArrayList<Token>());
        frenzyboard.setFrenzyboard(true);

        CopyOnWriteArrayList<Integer> frenzyValue = new CopyOnWriteArrayList<Integer>();
        frenzyValue.add(2);
        frenzyValue.add(1);
        frenzyValue.add(1);
        frenzyValue.add(1);

        frenzyboard.setPlayerboardValue(frenzyValue);


        player.setPlayerboard(frenzyboard);


    }


    /**
     * this method gives to a player the cubes corresponding
     * to the picked up loot
     *
     * @param player is the player who receives the cubes
     * @param loot   is the loot picked up.
     */

    public static void giveRybamount(Player player, Loot loot) {

        Rybamount oldRybamount = player.getPlayerboard().getAmmoCubes();

        oldRybamount.setBlueCubes(loot.getRewardValue().getBlue() + oldRybamount.getBlue());
        oldRybamount.setYellowCubes(loot.getRewardValue().getYellow() + oldRybamount.getYellow());
        oldRybamount.setRedCubes(loot.getRewardValue().getRed() + oldRybamount.getRed());

        Check.limitAmmoCubes(player);

    }


    /**
     * If the lootdeck is empty, this method recovers the discarded loots and puts them on the deck once again.
     */
    public static void recoverLoots() {

        CopyOnWriteArrayList<Loot> newDeck = Board.getDiscardedLoot();
        Board.setLootDeck(newDeck);
        Board.shuffleLootDeck();
        Board.setDiscardedLoot(new CopyOnWriteArrayList<Loot>());

    }

    /**
     * If the PowerUp deck is empty, this method recovers the discarded PowerUps and puts them on the deck once again.
     */
    public static void recoverPowerUps() {

        CopyOnWriteArrayList<Powerup> newDeck = Board.getDiscardedPowerUps();
        Board.setPowerUpDeck(newDeck);
        Board.shuffleLootDeck();
        Board.setDiscardedPowerUps(new CopyOnWriteArrayList<Powerup>());


    }


    /**
     * In the event of drawing all the powerups, thediscarded ones are shuffled into a new deck and the player that needed to
     * draw now can.
     *
     * @param player is the player that is playing the turn.
     */

    public static void shuffleAndDraw(Player player) {

        Interaction.recoverPowerUps();
        try {
            drawPowerUp(player);
        } catch (Exception e) {
            Server.update(player, Message.limitePowerup());
        }

    }


}
