package it.polimi.se2019.Model;

/**
 * This class represents the squares that contain an ammo tile.
 */

public class LootCell extends Cell {

    /**
     * ---------------------- FIELDS ----------------------
     * The connections are all inherited from Cell class.
    */

    /**
     * This is the ammo tile available in this square.
     */
    private Loot lootAvailable;



    /**
     * ---------------------- METHODS ----------------------
    */

    /**
     * ---------------------- GETTERS AND SETTERS
     */

    public Loot getLoot() {
        return lootAvailable;
    }

    public void setLoot(Loot lootAvailable) {
        this.lootAvailable = lootAvailable;
    }


}
