package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;
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
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an ArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */


    public Room build() {

        ArrayList<Cell> cells = new ArrayList<Cell>();
        Room redRoom = new Room();

        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setColour(Colour.RED);
        spawnCell.setName("spawnCell");

        Cell lootCell = lootCellBuilder.build();
        lootCell.setColour(Colour.RED);
        lootCell.setName("lootCell");

        //Spawn Cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.DOOR);

        //Down connection

        spawnCell.getDownConnection().setType(Connection.EDGE);

        //Left connection

        spawnCell.getLeftConnection().setType(Connection.EDGE);

        //Right connection

        spawnCell.getRightConnection().setType(Connection.FREE);
        spawnCell.getRightConnection().setConnectedCell(lootCell);


        //Loot cell

        //Up connection

        lootCell.getUpConnection().setType(Connection.WALL);

        //Down connection

        lootCell.getDownConnection().setType(Connection.DOOR);

        //Left connection

        lootCell.getLeftConnection().setType(Connection.FREE);
        lootCell.getLeftConnection().setConnectedCell(spawnCell);

        //Right connection

        lootCell.getRightConnection().setType(Connection.WALL);


        cells.add(spawnCell);
        cells.add(lootCell);

        redRoom.setCells(cells);
        redRoom.setColour(Colour.RED);

        return redRoom;

    }

}