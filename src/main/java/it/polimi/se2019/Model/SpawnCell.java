package it.polimi.se2019.Model;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is the extension of the class Cell, it is responsible for holding memory of the player spawn
 * point and the weapon shop.
 */

public class SpawnCell extends Cell{

    /*
     *  ---------------------- FIELDS ----------------------
     */

    /**
     * this CopyOnWriteArrayList represents the available weapons in the spawn cell
     */

    private CopyOnWriteArrayList<Weapon> availableWeapons = new CopyOnWriteArrayList<>();

    /*
     *  ---------------------- METHODS ----------------------
     */


    /*
     *  ---------------------- GETTERS
     */

    /**
     * @return the CopyOnWriteArrayList that holds memory of the three weapon in the spawn cell.
     */
    public CopyOnWriteArrayList<Weapon> getAvailableWeapons(){return availableWeapons;}



    /*
     *  ---------------------- SETTERS
     */


    /**
     * Sets the three  weapons in the spawn cell during the init of the game.
     * @param weapons is the CopyOnWriteArrayList of weapons loaded in the room
     */
    public void setAvailableWeapons(CopyOnWriteArrayList<Weapon> weapons){
        this.availableWeapons = weapons;
    }

    }
