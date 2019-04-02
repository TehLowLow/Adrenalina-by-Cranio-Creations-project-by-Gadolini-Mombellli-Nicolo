package it.polimi.se2019.Model;

import java.util.ArrayList;

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
     * ArrayList of tokens. Every token represents one point of damage made by another player.
     */
    private ArrayList<Token> damage;

    /**
     * ArrayList of tokens. Every token represents one marker made by another player.
     */
    private ArrayList<Token> marker;

    /**
     * Amount of ammo cubes held by the Player.
     */
    private Rybamount ammoCubes;

    /**
     * Powerups owned by the Player. They can be three or less.
     */
    private ArrayList<Powerup> powerups;

    /**
     * Weapons owned by the player. They can be three or less.
     */
    private ArrayList<Weapon> weapons;



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
     * @return an ArrayList of token representing the damages taken by the player.
     */
    public ArrayList<Token> getDamage() {return damage;}

    /**
     * Getter for the Marker field.
     * @return an ArrayList of token representing the markers taken by the player.
     */
    public ArrayList<Token> getMarker() {return marker;}

    /**
     * Getter for the ammoCubes field.
     * @return the object that represents the amount of ammo cubes held by the player.
     */
    public Rybamount getAmmoCubes() {return ammoCubes;}

    /**
     * Getter for the powerUps field.
     * @return an ArrayList that contains the power ups of the Player.
     */
    public ArrayList<Powerup> getPowerups() {return powerups;}

    /**
     * Getter for the Weapon field.
     * @return an ArrayList that contains the weapon of the Player.
     */
    public ArrayList<Weapon> getWeapons() {return weapons;}




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
     * @param damage ArrayList of tokens that represent the damages.
     */
    public void setDamage(ArrayList<Token> damage) {this.damage = damage;}

    /**
     * Setter for the marker field.
     * @param marker ArrayList of tokens that represent the markers.
     */
    public void setMarker(ArrayList<Token> marker) {this.marker = marker;}

    /**
     * Setter for the ammoCubes field.
     * @param ammoCubes object that represents the amount of ammo cubes held by the player.
     */
    public void setAmmoCubes(Rybamount ammoCubes) {this.ammoCubes = ammoCubes;}

    /**
     * Setter for the powerUps field.
     * @param powerups an ArrayList of powerUps.
     */
    public void setPowerups(ArrayList<Powerup> powerups) {this.powerups = powerups;}

    /**
     * Setter for the weapons field.
     * @param weapons an ArrayList of Weapons.
     */
    public void setWeapons(ArrayList<Weapon> weapons) {this.weapons = weapons;}


}

