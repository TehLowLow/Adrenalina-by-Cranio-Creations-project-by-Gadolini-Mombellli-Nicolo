package it.polimi.se2019.Model;

import java.util.ArrayList;

/**
 * This class represents the player board.
 */
public class Playerboard {

    /**
    ----------------------------FIELDS-----------------------------
    Playerboard contains the champion's name, his weapons, his ammo cubes and powerups.
    It tracks also the damage tokens and the markers that other players have placed.
     */

    private String championName;

    private ArrayList<Token> damage;

    private ArrayList<Token> marker;

    private Rybamount ammoCubes;

    private ArrayList<Powerup> powerups;

    private ArrayList<Weapon> weapons;



    /**
    -----------------------------METHODS---------------------
     */





    /**
    ----------------------------GETTERS----------------------
     */

    public String getChampionName() {return championName;}

    public ArrayList<Token> getDamage() {return damage;}

    public ArrayList<Token> getMarker() {return marker;}

    public Rybamount getAmmoCubes() {return ammoCubes;}

    public ArrayList<Powerup> getPowerups() {return powerups;}

    public ArrayList<Weapon> getWeapons() {return weapons;}




    /**
    -----------------------SETTERS----------------------------
     */



    public void setChampionName(String championName){this.championName = championName;}

    public void setDamage(ArrayList<Token> damage) {this.damage = damage;}

    public void setMarker(ArrayList<Token> marker) {this.marker = marker;}

    public void setAmmoCubes(Rybamount ammoCubes) {this.ammoCubes = ammoCubes;}

    public void setPowerups(ArrayList<Powerup> powerups) {this.powerups = powerups;}

    public void setWeapons(ArrayList<Weapon> weapons) {this.weapons = weapons;}


}

