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
    private Cell downDirection;
    private Cell leftDirection;
    private Cell rightDirection;

    /**
     *  ---------------------- METHODS  ----------------------
     */

    /**
     *  ---------------------- GETTERS
     */

    public Cell getUpConnection() {
        return upConnection;
    }

    public Cell getDownDirection() {
        return downDirection;
    }

    public Cell getLeftDirection() {
        return leftDirection;
    }


    public Cell getRightDirection() {
        return rightDirection;
    }


    /**
     * ---------------------- SETTERS
     */

    public void setUpConnection(Cell upConnection) {
        this.upConnection = upConnection;
    }

    public void setDownDirection(Cell downDirection) {
        this.downDirection = downDirection;
    }


    public void setLeftDirection(Cell leftDirection) {
        this.leftDirection = leftDirection;
    }


    public void setRightDirection(Cell rightDirection) {
        this.rightDirection = rightDirection;
    }
}
