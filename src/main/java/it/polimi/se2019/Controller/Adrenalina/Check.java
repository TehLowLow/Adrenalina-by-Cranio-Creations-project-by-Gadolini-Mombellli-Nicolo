package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * This class contains all the methods used to check if an interaction during the game is legit.
 */
public class Check {


    /**
     * Checks if a player has sustained enough damage to be declared dead.
     * @param damaged is the player that receives damage.
     * @return an int value. 0 if the player hasn't been damaged enough to die, 1 if the player receives damage only to
     * die, 2 if the attacker overkills.
     */
    public int death(Player damaged){}


    /**
     * When a player kills another player, this method resolves the board and assigns the players
     * their scores.
     * @param killed the defeated player whose board needs to be resolved.
     */
    public void resolveBoard(Player killed, ArrayList <Player> players){}

    /**
     * Checks if the player that is being attacked has markers to add to the total damage.
     * @param active is the attacker, used to look for attacker's markers.
     * @param passive is the player being attacked that needs te markers check to resolve total damage.
     */
    public void markers(Player active, Player passive){}

    /**
     * Checks the amount of damages dealt to a player by another player.
     * @param attacker player that has dealt the damages.
     * @param defender player that has taken the damages.
     */
    private void damages(Player attacker, Player defender){}

    /**
     * Checks if a buyer can afford a weapon found in the spawn room. If not, returns false.
     * @param buyer the buyer of the weapon.
     * @param weapon the weapon to be checked.
     * @return true if a player can afofrd a weapon.
     */
    public boolean affordableWeapon(Player buyer, Weapon weapon){}

    /**
     * Checks if a player can afford to reload a weapon that is holding.
     * @param player the player that wants to reload.
     * @param weapon the weapon to reload.
     * @return true if the player can afford to reload.
     */
    public boolean affordableReload(Player player, Weapon weapon){}

    /**
     * Checks the total amount of ammo of a player
     * @param player plyer to be checked
     * @return the amount of ammos
     */
    public Rybamount availableRybamount(Player player){}

    /**
     * Checks if a player has sustained enough damage to use enhanced pickup.
     * @param player is the player to be checked.
     * @return true if the player has sustained enough damage.
     */
    public boolean availablleEnhancedPickUp(Player player){}

    /**
     * Checks if a player has sustained enough damage to use enhanced shoot.
     * @param player is the player to be checked.
     * @return true if the player has sustained enough damage.
     */
    public boolean availableEnhancedShoot(Player player){}

    /**
     * Runs to limit the total amount of ammo per color to 3 ammos.
     * @param player is the player to be checked.
     */
    public void limitAmmoCubes(Player player){}

    /**
     * Runs to limit the total amount of weapon a player is holding to 3.
     * @param player is the player to be checked.
     */
    public void limitWeapon(Player player){}

    /**
     * Runs to limit the total amount of powerup a player is holding to 3.
     * @param player is the player to be checked.
     */
    public void limitPowerUp(Player player){}

    /**
     * A player can place up to 3 markers on each enemy playerboard, and the player can have up to 3 marker from each
     * other player. This method runs to check if the number of markers has reached limit and fix it.
     * @param player is the player to be checked.
     */
    public void limitMarkers(Player player){}

    /**
     * Returns an arraylist containing all the players in the field of view of a player.
     * @param player is the player that needs the fow check.
     * @return all the others players that are in the fov.
     */
    public ArrayList <Player> visiblePlayers (Player player){}

    /**
     * Returns all the cells that are a maximum of n steps away from player.
     * @param player is the player that wants the check to move.
     * @return an arraylist of reachable cells.
     */
    public ArrayList <Cell> reachableCells (Player player){}

    /**
     * This method runs after the final frenzy, and checks (/resolve) all the last points remaining on the board.
     * Then it proceeds to sum up the points of each player and declare a winner
     * (or a chart with the points of all the players).
     * @return the winner.
     */
    public Player winner(){ }




}
