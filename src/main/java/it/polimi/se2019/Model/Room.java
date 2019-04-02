package it.polimi.se2019.Model;

import java.util.ArrayList;

/**
 This class represents a Room.
 A room is part of a map. It's made by a few cells and
 it has a colour.
 */
public class Room {


    /*
    --------------------FIELDS-----------------------------
    Here there are represented the cells and the colour.
     */

    private ArrayList<Cell> cells;

    private Integer colour;



    /*
    ---------------------METHODS---------------------------
     */



    /*
    --------------------GETTERS-----------------------------
     */

    public Integer getColour() {return colour;}

    public ArrayList<Cell> getCells() {return cells;}



    /*
    ---------------------SETTERS----------------------------
     */


    public void setColour(Integer colour) {this.colour = colour;}

    public void setCells(ArrayList<Cell> cells) {this.cells = cells;}

}
