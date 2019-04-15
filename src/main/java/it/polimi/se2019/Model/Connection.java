package it.polimi.se2019.Model;



/**
 * This class is used to store the type of connection a singe cell has on its 4 cardinal directions. A cell can either
 * be connected to another adjacent cell via one door or by being in the same room. Diagonal connection isn't allowed.
 * If two rooms aren't connected, Connection stores the obstacles between them, like walls.
 * Connection also stores the information of a cell being on the edge of the map, meaning that the direction marked
 * as edge has no more rooms.
 */

public class Connection {

     /**
     * Type stores the information of a connection.
     * The values can be:
     * Free: the connection has no obstacles, the two cells are in the same room;
     * Door: two rooms are connected on a cardinal direction, but there's a door between them;
     * Wall: in this direction a player encounters a wall;
     * Edge: The room has a direction that points out the edge of the map.
     */

     public static final String FREE = "free";
     public static final String DOOR = "door";
     public static final String WALL = "wall";
     public static final String EDGE = "edge";

    private String type;

    /**
     * Stores the cell that is on the other side of the obstacle
     */
    private Cell connectedCell;


    /**
     * Returns the type of the obstacle between two cells
     * @return a string containing the type of the obstacle
     */
    public  String getType(){return type;}

    /**
     * Sets the type of obstacle between rooms
     * @param tp contains the type
     */
    public void setType(String tp){this.type = tp;}

    /**
     * Returns the cell on the other side of the obstacle
     * @return a cell object
     */
    public Cell getConnectedCell(){return connectedCell;}

    /**
     * Sets the connected cell on the other side of the obstacle
     * @param connect stores the cell to be set.
     */
    public void setConnectedCell(Cell connect){this.connectedCell = connect;}

}
