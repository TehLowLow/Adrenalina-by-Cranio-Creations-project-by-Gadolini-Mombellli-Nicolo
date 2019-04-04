package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class Interaction {
    /**
     * this class contains the methods to manage the links
     * with all the game's elements, such as drawing, discarding...
     */

    /*
    ----------------------METHODS--------------------------------
     */


    /**
     * this method is used to allow the player to draw a powerUp from
     * the board
     * @param player is who has to draw the powerUp
     * @param board is "Adrenalina" main board
     */

    public void drawPowerUp(Player player, Board board){}


    /**
     * this method is used to allow the player to discard a powerUp
     * @param player is who has to discard the powerUp
     * @param board is "Adrenalina" main board
     */

    public void discardPowerUp(Player player, Board board){}


    /**
     * this method draws a weapon from the deck and places
     * it on the board, making it reachable from the spawn
     * cell
     * @param board is "Adrenalina" main board
     */

    public void placeWeapon(Board board){}


    /**
     * this method is used to recharge a unloaded weapon
     * @param weapon is the weapon to reload
     */

    public void reloadWeapon(Weapon weapon){}


    /**
     * this method allows the player to get a weapon
     * from the available weapons in the spawn point
     * @param player is the player who gets the weapon
     * @param weapon is the selected weapon
     */

    public void getWeapon(Player player, Weapon weapon){}

    /**
     * this method replaces a player's weapon with a new
     * weapon taken from the spawn cell.
     * the old player's weapon is placed instead of the
     * new one.
     * @param oldWeapon is the weapon that is placed on the board
     * @param newWeapon is the weapon taken by the player
     */

    public void switchWeapon(Weapon oldWeapon, Weapon newWeapon){}

    /**
     * this method reloads on the board the Loots that
     * have been picked up during the turn
     * @param board is the main board
     */

    public void placeLoot(Board board){}


    /**
     * this method discards a loot picked up in the turn
     * @param cell is the cell where the loot has been picked up
     * @param board is the main board
     */

    public void discardLoot(LootCell cell, Board board){}


    /**
     * this method manages a player's rybamount payment
     * @param player is who has to pay the cubes
     * @param rybamount are the cubes to pay
     */

    public void pay(Player player, Rybamount rybamount){}

    /**
     * this method replaces a skull on the MortalBlow track
     * with the killer's Token
     * @param board is the main board
     * @param killer is the player who has to place his Token
     * @param victim is the killed player
     * @param overkill is a boolean that is '1' in case of overkill,
     *                 '0' otherwise
     */


    public void claimSkull(Board board, Player killer, Player victim, boolean overkill){}

    /**
     * this method turns the playerboard at the beginning
     * of the FinalFrenzy mode
     * @param player is the player linked to the playerboard
     *               to turn
     */

    public void turnPlayerboard(Player player){}

    /**
     * this method turns the action tile for the FinalFrenzy mode
     * @param player is linked to the tile to turn
     */

    public void turnActionTile(Player player){}


    /**
     * this method gives to a player the cubes corresponding
     * to the picked up loot
     * @param player is the player who receives the cubes
     */

    public void giveRybamount(Player player){}



}
