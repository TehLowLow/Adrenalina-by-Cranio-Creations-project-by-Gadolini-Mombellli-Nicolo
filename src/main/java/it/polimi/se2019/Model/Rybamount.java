package it.polimi.se2019.Model;

/**
 * Every instance of this class represents an amount of ammo cubes.
 */
public class Rybamount {

    /**
     * ---------------------- FIELDS ----------------------
     * The amount of cubes of each colour is represented with an int.
     */
    private int redCubes;
    private int yellowCubes;
    private int blueCubes;

    /**
     *   ---------------------- METHODS ----------------------
     */

    /**
     *  ---------------------- GETTERS
     */

    public int getRed() {
        return redCubes;
    }

    public int getYellow() {
        return yellowCubes;
    }

    public int getBlue() {
        return blueCubes;
    }

    /**
     *  ---------------------- SETTERS
     */

    public void setRedCubes(int redCubes) {
        this.redCubes = redCubes;
    }

    public void setYellowCubes(int yellowCubes) {
        this.yellowCubes = yellowCubes;
    }

    public void setBlueCubes(int blueCubes) {
        this.blueCubes = blueCubes;
    }
}
