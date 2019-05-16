package it.polimi.se2019.Model;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class represents the player board.
 */
public class Playerboard {

    /*
    ----------------------------FIELDS-----------------------------
    Playerboard contains the champion's name, his weapons, his ammo cubes and powerups.
    It tracks also the damage tokens and the markers that other players have placed.
     */

    /**
     * The name of the champion is represented by a String. This determines
     * the graphics showed to the user.
     */
    private String championName;

    /**
     * CopyOnWriteArrayList of tokens. Every token represents one point of damage made by another player.
     */
    private CopyOnWriteArrayList<Token> damage;

    /**
     * CopyOnWriteArrayList of tokens. Every token represents one marker made by another player.
     */
    private CopyOnWriteArrayList<Token> marker;

    /**
     * Amount of ammo cubes held by the Player.
     */
    private Rybamount ammoCubes;

    /**
     * Powerups owned by the Player. They can be three or less.
     */
    private CopyOnWriteArrayList<Powerup> powerups;

    /**
     * Weapons owned by the player. They can be three or less.
     */
    private CopyOnWriteArrayList<Weapon> weapons;


    /**
     * It represents the points the player take when they kill this playerboard's owner.
     */

    private CopyOnWriteArrayList<Integer> playerboardValue;


    /**
     * It's true if the board is frenzyboard, false otherwise
     */

    private boolean frenzyboard;

    /*
    -----------------------------METHODS---------------------
     */





    /*
    ----------------------------GETTERS----------------------
     */

    /**
     * Getter for the championName field.
     * @return a String that contains the name of the champion chosen by the player.
     */
    public String getChampionName() {return championName;}

    /**
     * Getter for the Damage field.
     * @return an CopyOnWriteArrayList of token representing the damages taken by the player.
     */
    public CopyOnWriteArrayList<Token> getDamage() {return damage;}

    /**
     * Getter for the Marker field.
     * @return an CopyOnWriteArrayList of token representing the markers taken by the player.
     */
    public CopyOnWriteArrayList<Token> getMarker() {return marker;}

    /**
     * Getter for the ammoCubes field.
     * @return the object that represents the amount of ammo cubes held by the player.
     */
    public Rybamount getAmmoCubes() {return ammoCubes;}

    /**
     * Getter for the powerUps field.
     * @return an CopyOnWriteArrayList that contains the power ups of the Player.
     */
    public CopyOnWriteArrayList<Powerup> getPowerups() {return powerups;}

    /**
     * Getter for the Weapon field.
     * @return an CopyOnWriteArrayList that contains the weapon of the Player.
     */
    public CopyOnWriteArrayList<Weapon> getWeapons() {return weapons;}


    /**
     * Getter for the Playerboard value
     * @return an CopyOnWriteArrayList that contains the points corresponding to the value.
     */
    public CopyOnWriteArrayList<Integer> getPlayerboardValue() {return playerboardValue;}

    /**
     * Getter for the Frenzyboard
     * @return true if the playerboard has turned in frenzyboard, false otherwise
     */

    public boolean isFrenzyboard() {
        return frenzyboard;
    }


    /*
    -----------------------SETTERS----------------------------
     */


    /**
     * Setter for the championName field
     * @param championName the name of the Champion.
     */
    public void setChampionName(String championName){this.championName = championName;}

    /**
     * Setter for the damage field.
     * @param damage CopyOnWriteArrayList of tokens that represent the damages.
     */
    public void setDamage(CopyOnWriteArrayList<Token> damage) {this.damage = damage;}

    /**
     * Setter for the marker field.
     * @param marker CopyOnWriteArrayList of tokens that represent the markers.
     */
    public void setMarker(CopyOnWriteArrayList<Token> marker) {this.marker = marker;}

    /**
     * Setter for the ammoCubes field.
     * @param ammoCubes object that represents the amount of ammo cubes held by the player.
     */
    public void setAmmoCubes(Rybamount ammoCubes) {this.ammoCubes = ammoCubes;}

    /**
     * Setter for the powerUps field.
     * @param powerups an CopyOnWriteArrayList of powerUps.
     */
    public void setPowerups(CopyOnWriteArrayList<Powerup> powerups) {this.powerups = powerups;}

    /**
     * Setter for the weapons field.
     * @param weapons an CopyOnWriteArrayList of Weapons.
     */
    public void setWeapons(CopyOnWriteArrayList<Weapon> weapons) {this.weapons = weapons;}

    /**
     * Setter for the playerboard value
     * @param playerboardValue is an CopyOnWriteArrayList of values.
     */
    public void setPlayerboardValue(CopyOnWriteArrayList<Integer> playerboardValue) {this.playerboardValue = playerboardValue;}


    /**
     * Setter for the frenzyboard.
     * @param frenzyboard is a boolean representing the frenzyboard
     */

    public void setFrenzyboard(boolean frenzyboard){this.frenzyboard = frenzyboard;}

}

