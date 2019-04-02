package it.polimi.se2019.Model;

/**
 * Every instance of this class represents an amount of ammo cubes.
 */
public class Rybamount {

    /*
     * ---------------------- FIELDS ----------------------
     * The amount of cubes of each colour is represented with an int.
     */

    /**
     * int that represents the amount of redCubes.
     */

    private int redCubes;

    /**
     * int that represents the amount of yellowCubes.
     */

    private int yellowCubes;

    /**
     * int that represents the amount of blueCubes.
     */

    private int blueCubes;

    /*
     *   ---------------------- METHODS ----------------------
     */

    /*
     *  ---------------------- GETTERS
     */

    /**
     * Getter for redCubes
     * @return the amount of redCubes.
     */

    public int getRed() {
        return redCubes;
    }

    /**
     * Getter for yellowCubes
     * @return the amount of yellowCubes.
     */

    public int getYellow() {
        return yellowCubes;
    }

    /**
     * Getter for blueCubes
     * @return the amount of blueCubes.
     */

    public int getBlue() {
        return blueCubes;
    }

    /*
     *  ---------------------- SETTERS
     */

    /**
     * Setter for redCubes
     * @param redCubes is the amount of redCubes
     */

    public void setRedCubes(int redCubes) {
        this.redCubes = redCubes;
    }

    /**
     * Setter for yellowCubes
     * @param yellowCubes is the amount of yellowCubes
     */

    public void setYellowCubes(int yellowCubes) {
        this.yellowCubes = yellowCubes;
    }

    /**
     * Setter for blueCubes
     * @param blueCubes is the amount of blueCubes
     */

    public void setBlueCubes(int blueCubes) {
        this.blueCubes = blueCubes;
    }
}
