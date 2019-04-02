package it.polimi.se2019.Model;

import java.util.ArrayList;

/**
 * This class is the implementation of the class Cell, it is responsible for holding memory of the player spawn
 * point and the weapon shop.
 */

public class SpawnCell extends Cell{

    /*
     *  ---------------------- FIELDS ----------------------
     */
    private ArrayList<Weapon> availableWeapons;

    /*
     *  ---------------------- METHODS ----------------------
     */


    /*
     *  ---------------------- GETTERS
     */

    /*
     * @return the arraylist that holds memory of the three weapon in the spawn cell.
     */
    public ArrayList<Weapon> getAvailableWeapons(){return availableWeapons;}



    /*
     *  ---------------------- SETTERS
     */


    /**
     * Sets the three  weapons in the spawn cell during the init of the game.
     * @param weapons is the arraylist of weapons loaded in the room
     */
    public void setAvailableWeapons(ArrayList<Weapon> weapons){}

    }
