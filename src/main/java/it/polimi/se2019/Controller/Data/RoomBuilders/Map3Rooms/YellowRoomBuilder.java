package it.polimi.se2019.Controller.Data.RoomBuilders.Map3Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an CopyOnWriteArrayList of
 * cells, and it has a colour as an identifier.
 */

public class YellowRoomBuilder {

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an CopyOnWriteArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */

    public Room build(){

        Room yellowRoom = new Room();
        CopyOnWriteArrayList <Cell> cells = new CopyOnWriteArrayList<Cell>();

        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setColour(Colour.YELLOW);
        spawnCell.setName("spawnCell");

        Cell lootCell = lootCellBuilder.build();
        lootCell.setColour(Colour.YELLOW);
        lootCell.setName("lootCell");


        //spawnCell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.FREE);
        spawnCell.getUpConnection().setConnectedCell(lootCell);

        //Down connection
        spawnCell.getDownConnection().setType(Connection.EDGE);

        //Left connection
        spawnCell.getLeftConnection().setType(Connection.DOOR);

        //Right connection
        spawnCell.getRightConnection().setType(Connection.EDGE);


        //lootCell

        //Up connection
        lootCell.getUpConnection().setType(Connection.EDGE);

        //Down connection
        lootCell.getDownConnection().setType(Connection.FREE);
        lootCell.getDownConnection().setConnectedCell(spawnCell);


        //Left connection
        lootCell.getLeftConnection().setType(Connection.DOOR);

        //Right connection
        lootCell.getRightConnection().setType(Connection.EDGE);


        cells.add(spawnCell);
        cells.add(lootCell);

        yellowRoom.setCells(cells);
        yellowRoom.setColour(Colour.YELLOW);
        return yellowRoom;

    }
}
