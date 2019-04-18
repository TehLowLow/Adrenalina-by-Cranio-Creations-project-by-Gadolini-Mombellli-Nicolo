package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an arraylist of
 * cells, and it has a colour as an identifier.
 */

public class RedRoomBuilder {

    /*
     * ----------- FIELDS ----------
     * There is just a field for the room that will be returned.
     */

    /**
     * The room that will be built.
     */

    private Room redRoom;

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an ArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */

    public Room build(){

        redRoom = new Room();
        ArrayList<Cell> cells = new ArrayList<Cell>();

        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setColour(Colour.RED);
        spawnCell.setName("spawnCell");

        Cell lootCell = lootCellBuilder.build();
        lootCell.setColour(Colour.RED);
        lootCell.setName("lootCell");


        //spawnCell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.FREE);
        spawnCell.getUpConnection().setConnectedCell(lootCell);

        //Down connection
        spawnCell.getDownConnection().setType(Connection.DOOR);

        //Left connection
        spawnCell.getLeftConnection().setType(Connection.EDGE);

        //Right connection
        spawnCell.getRightConnection().setType(Connection.WALL);

        //lootCell

        //Up connection
        lootCell.getUpConnection().setType(Connection.EDGE);

        //Down connection
        lootCell.getDownConnection().setType(Connection.FREE);
        lootCell.getDownConnection().setConnectedCell(spawnCell);

        //Left connection
        lootCell.getLeftConnection().setType(Connection.EDGE);

        //Right connection
        lootCell.getRightConnection().setType(Connection.DOOR);

        cells.add(spawnCell);
        cells.add(lootCell);

        redRoom.setCells(cells);
        redRoom.setColour(Colour.RED);
        return redRoom;
    }

}
