package it.polimi.se2019.Model;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class represents a Room.
 * A room is part of a map. It's made by a few cells and
 * it has a colour.
 */


public class Room implements java.io.Serializable {


    /*
    --------------------FIELDS-----------------------------
    Here there are represented the cells and the colour.
     */

    /**
     * This CopyOnWriteArrayList represents the cells the Room is made of.
     */
    private CopyOnWriteArrayList<Cell> cells;

    /**
     * It represents the Room's colour.
     */
    private Integer colour;



    /*
    ---------------------METHODS---------------------------
     */



    /*
    --------------------GETTERS-----------------------------
     */

    /**
     * Getter for the Room's colour.
     *
     * @return the Room's colour.
     */


    public Integer getColour() {
        return colour;
    }


    /**
     * Getter for the Room's cells.
     *
     * @return the array of the Room's cells.
     */

    public CopyOnWriteArrayList<Cell> getCells() {
        return cells;
    }



    /*
    ---------------------SETTERS----------------------------
     */

    /**
     * Setter for the Room's colour.
     *
     * @param colour is the colour of the Room
     */

    public void setColour(Integer colour) {
        this.colour = colour;
    }

    /**
     * Setter for the Room's cells.
     *
     * @param cells is an array representing the Room's cells.
     */


    public void setCells(CopyOnWriteArrayList<Cell> cells) {
        this.cells = cells;
    }

}
