package it.polimi.se2019.Model;

/**
 * This abstract class represents a single square, with four connections pointing at other squares.
 */

public abstract class Cell implements java.io.Serializable{

    /*
     *  ---------------------- FIELDS  ----------------------
     *  There are four connections that points to other squares. Up, down, left and right are defined by
     *  looking at the game board having the Killshot track on the top left corner.
     */

    /*
     * Connections on the four directions, stored in a Connection Object.
     */

    /**
     * Stores the type of upper connection of a cell.
     */
    private Connection upConnection;

    /**
     * Stores the type of lower connection of a cell.
     */
    private Connection downConnection;

    /**
     * Stores the type of left connection of a cell.
     */
    private Connection leftConnection;

    /**
     * Stores the type of right connection of a cell.
     */
    private Connection rightConnection;

    /**
     * Identifier name of a cell.
     */
    private String name;

    /**
     * Color of the cell.
     */

    private int colour;

    /*
     *  ---------------------- METHODS  ----------------------
     */

    /*
     *  ---------------------- GETTERS
     */

    /**
     * Returns the Connection object that stores the upper object of the room
     * @return the connection object.
     */
    public Connection getUpConnection() {
        return upConnection;
    }

    /**
     * Returns the Connection object that stores the lower object of the room
     * @return the connection object.
     */
    public Connection getDownConnection() {
        return downConnection;
    }
    /**
     * Returns the Connection object that stores the left object of the room
     * @return the connection object.
     */
    public Connection getLeftConnection() {
        return leftConnection;
    }
    /**
     * Returns the Connection object that stores the right object of the room
     * @return the connection object.
     */
    public Connection getRightConnection() {
        return rightConnection;
    }

    /**
     * Getter for the cell's name.
     * @return the name of the cell.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the cell's colour.
     */

    public int getColour(){
        return this.colour;
    }

    /*
     * ---------------------- SETTERS
     */

    /**
     * Sets the name of the cell.
     * @param name the name of the cell.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the colour of the cell. It is needed by the view.
     * @param colour: the color that must be assigned.
     */
    public void setColour(int colour){
        this.colour = colour;
    }

    /**
     * Sets the upper connection of a room
     * @param upConnection stores the type of connection.
     */

    public void setUpConnection(Connection upConnection) {
        this.upConnection = upConnection;
    }

    /**
     * Sets the lower connection of a room
     * @param downConnection stores the type of connection
     */

    public void setDownConnection(Connection downConnection) {
        this.downConnection = downConnection;
    }


    /**
     * Sets the left connection of a room
     * @param leftConnection stores the type of connection
     */

    public void setLeftConnection(Connection leftConnection) {
        this.leftConnection = leftConnection;
    }

    /**
     * Sets the right connection of a room
     * @param rightConnection stores the type of connection
     */

    public void setRightConnection(Connection rightConnection) {
        this.rightConnection = rightConnection;
    }

}
