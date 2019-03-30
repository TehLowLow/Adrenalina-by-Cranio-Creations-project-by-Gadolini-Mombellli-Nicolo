package it.polimi.se2019.Model;

import java.util.ArrayList;

public class SpawnCell extends Cell{

    /**
     * This class is the implementation of the class Cell, it is responsible for holding memory of the player spawn
     * point and the weapon shop.
     */

    private ArrayList<Weapon> availableWeapons;

    /**
     * Sets the three  weapons in the spawn cell during the init of the game.
     * @param weapons is the arraylist of weapons loaded in the room
     */
    public void setAvailableWeapons(ArrayList<Weapon> weapons){};

    /**
     * @return the arraylist that holds memory of the three weapon in the spawn cell.
     */
    public ArrayList<Weapon> getAvailableWeapons(){return availableWeapons;}

}
