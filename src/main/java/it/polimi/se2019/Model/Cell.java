package it.polimi.se2019.Model;

/**
 * This abstract class represents a single square, with four connections pointing at other squares.
 */

public abstract class Cell {

    /**
     *  ---------------------- FIELDS  ----------------------
     *  There are four connections that points to other squares. Up, down, left and right are defined by
     *  looking at the game board having the Killshot track on the top left corner.
     */

    /**
     * Connections on the four directions.
     */
    private Cell upConnection;
    private Cell downConnection;
    private Cell leftConnection;
    private Cell rightConnection;

    /**
     *  ---------------------- METHODS  ----------------------
     */

    /**
     *  ---------------------- GETTERS
     */

    public Cell getUpConnection() {
        return upConnection;
    }

    public Cell getDownConnection() {
        return downConnection;
    }

    public Cell getLeftConnection() {
        return leftConnection;
    }

    public Cell getRightConnection() {
        return rightConnection;
    }


    /**
     * ---------------------- SETTERS
     */

    public void setUpConnection(Cell upConnection) {
        this.upConnection = upConnection;
    }


    public void setDownConnection(Cell downConnection) {
        this.downConnection = downConnection;
    }


    public void setLeftConnection(Cell leftConnection) {
        this.leftConnection = leftConnection;
    }


    public void setRightConnection(Cell rightConnection) {
        this.rightConnection = rightConnection;
    }


}
