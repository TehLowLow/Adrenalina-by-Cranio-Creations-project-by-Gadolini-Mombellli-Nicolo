package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Adrenalina.Exceptions.*;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;


import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

/**
 * this class contains the methods to manage the links
 * with all the game's elements, such as drawing, discarding...
 */
public class Interaction {

    private Check check = new Check();

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
        CopyOnWriteArrayList<Weapon> weaponDeck = Board.getWeaponDeck();

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
     * this method is used to recharge a unloaded weapon
     *
     * @param weapon is the weapon to reload
     */

    public void reloadWeapon(Weapon weapon) {

        weapon.setLoaded(true);

    }


    /**
     * this method allows the player to get a weapon
     * from the available weapons in the spawn point
     *
     * @param player is the player who gets the weapon
     * @param weapon is the selected weapon
     */

    public void getWeapon(Player player, SpawnCell spawnCell, Weapon weapon) {

        player.getPlayerboard().getWeapons().add(weapon);
        spawnCell.getAvailableWeapons().remove(weapon);

    }

    /**
     * this method replaces a player's weapon with a new
     * weapon taken from the spawn cell.
     * the old player's weapon is placed instead of the
     * new one.
     *
     * @param oldWeapon is the weapon that is placed on the board
     * @param newWeapon is the weapon taken by the player
     */

    public void switchWeapon(Player player, Weapon oldWeapon, Weapon newWeapon, SpawnCell spawnCell) {

        player.getPlayerboard().getWeapons().add(newWeapon);
        spawnCell.getAvailableWeapons().remove(newWeapon);

        player.getPlayerboard().getWeapons().remove(oldWeapon);
        spawnCell.getAvailableWeapons().add(oldWeapon);

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
     * this method discards a loot picked up in the turn
     *
     * @param cell  is the cell where the loot has been picked up
     * @param board is the main board
     */

    public void discardLoot(LootCell cell, Board board) {

        board.getLootDeck().add(cell.getLoot());
        cell.setLoot(null);

    }


    /**
     * this method manages a player's rybamount payment
     *
     * @param player        is who has to pay the cubes
     * @param billRybamount are the cubes to pay
     */

    public static void pay(Player player, Rybamount billRybamount) {

        Rybamount playerRybAmount = player.getPlayerboard().getAmmoCubes();
        int yellowAvailable = playerRybAmount.getYellow();
        int redAvailable = playerRybAmount.getRed();
        int blueAvailable = playerRybAmount.getBlue();

        int yellowCost = billRybamount.getYellow();
        int blueCost = billRybamount.getBlue();
        int redCost = billRybamount.getRed();

        while (yellowAvailable < yellowCost) {
            yellowAvailable = yellowAvailable + convertPowerUp(player, Colour.YELLOW);
        }

        while (redAvailable < redCost) {
            redAvailable = redAvailable + convertPowerUp(player, Colour.RED);
        }

        while (blueAvailable < blueCost) {
            blueAvailable = blueAvailable + convertPowerUp(player, Colour.BLUE);
        }

        playerRybAmount.setYellowCubes(yellowAvailable - yellowCost);
        playerRybAmount.setRedCubes(redAvailable - redCost);
        playerRybAmount.setBlueCubes(blueAvailable - blueCost);

    }

    private static int convertPowerUp(Player player, int colour) {

        CopyOnWriteArrayList<Powerup> discardable = new CopyOnWriteArrayList<Powerup>();
        CopyOnWriteArrayList<Powerup> availablePowerups = player.getPlayerboard().getPowerups();


        for (Powerup powerup : availablePowerups) {

            if (colour == Colour.RED) {
                if (powerup.getTradeValue().getRed() == 1) {
                    discardable.add(powerup);
                }
            }
            if (colour == Colour.YELLOW) {
                if (powerup.getTradeValue().getYellow() == 1) {
                    discardable.add(powerup);
                }
            }
            if (colour == Colour.BLUE) {
                if (powerup.getTradeValue().getBlue() == 1) {
                    discardable.add(powerup);
                }
            }


        }

        boolean chosen = false;
        int index = 0;
        while (!chosen) {

            String chosenPU = Server.updateWithAnswer(player, Message.payWithPowerUp(discardable));

            try {
                index = InputCheck.numberCheck(chosenPU);
                if (index < 0 || index > discardable.size() - 1) {
                    Server.update(player, Message.inputError());
                    continue;
                }
                chosen = true;
            } catch (NumberFormatException e) {
                Server.update(player, Message.inputError());
                continue;
            }


        }

        Powerup toRemove = discardable.get(index);
        player.getPlayerboard().getPowerups().remove(toRemove);

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
     * this method turns the action tile for the FinalFrenzy mode
     * @param player is linked to the tile to turn
     */


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

    }


    public static void selectShotMove(Player player) {

        String sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        while (!InputCheck.correctMoveEnhancedShot(sceltaMovimento)) {

            update(player, Message.inputError());
            sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        }

        boolean canShot = false;

        while (!canShot) {

            if (sceltaMovimento.equalsIgnoreCase("stop")) {

                for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("su")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getUpConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {


                        player.setPosition(fakePlayer.getPosition());
                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }


            if (sceltaMovimento.equalsIgnoreCase("giu")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getDownConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {

                        canShot = true;
                        player.setPosition(fakePlayer.getPosition());

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }


            if (sceltaMovimento.equalsIgnoreCase("destra")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getRightConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {


                        player.setPosition(fakePlayer.getPosition());
                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("sinistra")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getLeftConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {

                        canShot = true;
                        player.setPosition(fakePlayer.getPosition());

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }


        }

    }

    public static void shuffleAndDraw(Player player) {

        CopyOnWriteArrayList<Loot> discarded = Board.getDiscardedLoot();
        Collections.shuffle(discarded);
        Board.setLootDeck(discarded);
        Board.setDiscardedLoot(new CopyOnWriteArrayList<Loot>());

        CopyOnWriteArrayList<Powerup> powerUps = player.getPlayerboard().getPowerups();
        Powerup drawnPowerUp = Board.getPowerUpDeck().get(0);
        Board.getPowerUpDeck().remove(drawnPowerUp);
        powerUps.add(drawnPowerUp);
        player.getPlayerboard().setPowerups(powerUps);


    }


}
