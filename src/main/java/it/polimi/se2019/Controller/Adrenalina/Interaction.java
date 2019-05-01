package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
     * @param player is who has to draw the powerUp
     * @param board is "Adrenalina" main board
     */

    public void drawPowerUp(Player player, Board board){

        ArrayList <Powerup> powerUpDeck = board.getPowerUpDeck();

        if(powerUpDeck.size()>0){

            if(/*check.limitPowerUp(player)*/true){

                //Lancio eccezione

            }

            /*if(!check.limitPowerUp(player)){

                ArrayList <Powerup> powerUps = player.getPlayerboard().getPowerups();
                Powerup drawnPowerUp = board.getPowerUpDeck().get(0);
                board.getPowerUpDeck().remove(drawnPowerUp);
                powerUps.add(drawnPowerUp);
                player.getPlayerboard().setPowerups(powerUps);

            }*/

        }

        if (powerUpDeck.size()==0){

            //Lancio eccezione
        }

    }


    /**
     * this method is used to allow the player to discard a powerUp
     * @param player is who has to discard the powerUp
     * @param board is "Adrenalina" main board
     * @param powerUp is the power up that must be discarded
     */

    public void discardPowerUp(Player player, Powerup powerUp, Board board){

        ArrayList <Powerup> discardedPowerUps = board.getDiscardedPowerUps();
        discardedPowerUps.add(powerUp);
        player.getPlayerboard().getPowerups().remove(powerUp);

    }


    /**
     * this method draws a weapon from the deck and places
     * it on the board. It refills every empty weapon slot left.
     * @param board is "Adrenalina" main board
     */

    public void placeWeapons(Board board){

        Map map = board.getMap();
        ArrayList <Weapon> weaponDeck = board.getWeaponDeck();

        ArrayList <Room> spawnRooms = new ArrayList<Room>();
        ArrayList <SpawnCell> spawnCells = new ArrayList <SpawnCell>();
        spawnRooms.add(map.getBlueRoom());
        spawnRooms.add(map.getYellowRoom());
        spawnRooms.add(map.getRedRoom());

        for(Room room : spawnRooms){

            ArrayList <Cell> cells = room.getCells();

            for(Cell cell : cells){

                if(check.isSpawn(cell)){

                    //If the current cell is a spawn cell, it is casted to spawncell and added
                    //to the arraylist of spawncells.
                    spawnCells.add((SpawnCell)cell);

                }

            }

        }

        for(SpawnCell spawnCell : spawnCells){

            ArrayList availableWeapons = spawnCell.getAvailableWeapons();

            if(availableWeapons.size()<3){

                Weapon drawnWeapon = board.getWeaponDeck().get(0);
                availableWeapons.add(drawnWeapon);
                board.getWeaponDeck().remove(drawnWeapon);
                spawnCell.setAvailableWeapons(availableWeapons);
            }


        }


    }




    /**
     * this method is used to recharge a unloaded weapon
     * @param weapon is the weapon to reload
     */

    public void reloadWeapon(Weapon weapon){

        weapon.setLoaded(true);

    }


    /**
     * this method allows the player to get a weapon
     * from the available weapons in the spawn point
     * @param player is the player who gets the weapon
     * @param weapon is the selected weapon
     */

    public void getWeapon(Player player, SpawnCell spawnCell, Weapon weapon){

        player.getPlayerboard().getWeapons().add(weapon);
        spawnCell.getAvailableWeapons().remove(weapon);

    }

    /**
     * this method replaces a player's weapon with a new
     * weapon taken from the spawn cell.
     * the old player's weapon is placed instead of the
     * new one.
     * @param oldWeapon is the weapon that is placed on the board
     * @param newWeapon is the weapon taken by the player
     */

    public void switchWeapon(Player player, Weapon oldWeapon, Weapon newWeapon, SpawnCell spawnCell){

       player.getPlayerboard().getWeapons().add(newWeapon);
       spawnCell.getAvailableWeapons().remove(newWeapon);

       player.getPlayerboard().getWeapons().remove(oldWeapon);
       spawnCell.getAvailableWeapons().add(oldWeapon);

    }

    /**
     * this method reloads on the board the Loots that
     * have been picked up during the turn
     * @param board is the main board
     */

    public void placeLoot(Board board){

        ArrayList <Room> rooms = new ArrayList<Room>();
        ArrayList <LootCell> lootCells = new ArrayList<LootCell>();

        Map map = board.getMap();

        rooms.add(map.getRedRoom());
        rooms.add(map.getYellowRoom());
        rooms.add(map.getBlueRoom());

        if(map.getWhiteRoom()!=null){
            rooms.add(map.getWhiteRoom());
        }

        if(map.getPurpleRoom()!=null){
            rooms.add(map.getPurpleRoom());
        }

        if(map.getGreenRoom()!=null){
            rooms.add(map.getGreenRoom());
        }

        for(Room room : rooms){

            for(Cell cell : room.getCells()){

                if(!check.isSpawn(cell)){
                    lootCells.add((LootCell)cell);
                }

            }


        }

        for(LootCell lootCell : lootCells){

            if(lootCell.getLoot()==null){

                if(board.getLootDeck().size()>0){
                    lootCell.setLoot(board.getLootDeck().get(0));
                    board.getLootDeck().remove(0);
                }

                if(board.getLootDeck().size()==0){

                    this.recoverLoots(board);
                    lootCell.setLoot(board.getLootDeck().get(0));
                    board.getLootDeck().remove(0);

                }

            }

        }

    }


    /**
     * this method discards a loot picked up in the turn
     * @param cell is the cell where the loot has been picked up
     * @param board is the main board
     */

    public void discardLoot(LootCell cell, Board board){

        board.getLootDeck().add(cell.getLoot());
        cell.setLoot(null);

    }


    /**
     * this method manages a player's rybamount payment
     * @param player is who has to pay the cubes
     * @param billRybamount are the cubes to pay
     */

    public void pay(Player player, Rybamount billRybamount){

        Rybamount playerRybAmount = player.getPlayerboard().getAmmoCubes();
        playerRybAmount.setYellowCubes(playerRybAmount.getYellow() - billRybamount.getYellow());
        playerRybAmount.setRedCubes(playerRybAmount.getRed() - billRybamount.getRed());
        playerRybAmount.setBlueCubes(playerRybAmount.getBlue() - billRybamount.getBlue());

    }

    /**
     * this method replaces a skull on the MortalBlow track
     * with the killer's Token
     * @param board is the main board
     * @param killer is the player who has to place his Token
     * @param victim is the killed player
     * @param overkill is a boolean that is 'true' in case of overkill,
     *                 'false' otherwise
     */

    public void claimSkull(Board board, Player killer, Player victim, boolean overkill){

        ArrayList <MortalBlow> mortalBlowTrack = board.getMortalBlowTrack();

        int lenght = mortalBlowTrack.size();

        for(int i = 0; i<lenght; i++){

            MortalBlow currentBlow = mortalBlowTrack.get(i);

            if(currentBlow.isSkull()){

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
     * @param player is the player linked to the playerboard
     *               to turn
     */

    public void turnPlayerboard(Player player){

        Playerboard frenzyboard = new Playerboard();

        Playerboard oldBoard = player.getPlayerboard();

        frenzyboard.setAmmoCubes(oldBoard.getAmmoCubes());
        frenzyboard.setChampionName(oldBoard.getChampionName());
        frenzyboard.setWeapons(oldBoard.getWeapons());
        frenzyboard.setPowerups(oldBoard.getPowerups());
        frenzyboard.setMarker(oldBoard.getMarker());
        frenzyboard.setDamage(new ArrayList<Token>());

        ArrayList<Integer> frenzyValue = new ArrayList<Integer>();
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
     * @param player is the player who receives the cubes
     * @param loot is the loot picked up.
     */

    public void giveRybamount(Player player, Loot loot){

        Rybamount oldRybamount = player.getPlayerboard().getAmmoCubes();

        oldRybamount.setBlueCubes(loot.getRewardValue().getBlue() + oldRybamount.getBlue());
        oldRybamount.setYellowCubes(loot.getRewardValue().getYellow() + oldRybamount.getYellow());
        oldRybamount.setRedCubes(loot.getRewardValue().getRed() + oldRybamount.getRed());

        check.limitAmmoCubes(player);

    }


    /**
     * If the lootdeck is empty, this method recovers the discarded loots and puts them on the deck once again.
     * @param board the board.
     */
    public void recoverLoots(Board board){

        ArrayList <Loot> newDeck = board.getDiscardedLoot();
        board.setLootDeck(newDeck);
        board.shuffleLootDeck();

    }

}
