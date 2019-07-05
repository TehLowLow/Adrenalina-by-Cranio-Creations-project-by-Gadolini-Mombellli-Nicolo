package it.polimi.se2019.Model;

/**
 * This class represents the squares that contain an ammo tile.
 */

public class LootCell extends Cell {

    /*
     * ---------------------- FIELDS ----------------------
     * The connections are all inherited from Cell class.
    */

    /**
     * This is the ammo tile available in this square.
     */
    private Loot lootAvailable;



    /*
     * ---------------------- METHODS ----------------------
    */

    /*
     * ---------------------- GETTERS AND SETTERS
     */

    /**
     * Returns the loot available in a cell
     * @return the loot card
     */
    public Loot getLoot() {
        return lootAvailable;
    }

    /**
     * Checks if a loot has been taken or not, and proceeds to refill the cell
     * @param lootAvailable is the status of the loot
     */
    public void setLoot(Loot lootAvailable) {
        this.lootAvailable = lootAvailable;
    }


}
