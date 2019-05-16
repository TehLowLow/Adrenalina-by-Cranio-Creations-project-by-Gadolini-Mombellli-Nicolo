package it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an CopyOnWriteArrayList of
 * cells, and it has a colour as an identifier.
 */

public class BlueRoomBuilder {

    /*
     * ----------- FIELDS ----------
     * There is just a field for the room that will be returned.
     */

    /**
     * The room that will be built.
     */

    private Room blueRoom = new Room();

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an CopyOnWriteArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */

    public Room build(){

        CopyOnWriteArrayList<Cell> cells = new CopyOnWriteArrayList <Cell>();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();
        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();

        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setName("lootCell1");
        lootCell1.setColour(Colour.BLUE);

        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setName("lootCell2");
        lootCell2.setColour(Colour.BLUE);

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setName("spawnCell");
        spawnCell.setColour(Colour.BLUE);

        //Loot Cell 1

        //Up connection

        lootCell1.getUpConnection().setType(Connection.EDGE);


        //Down connection

        lootCell1.getDownConnection().setType(Connection.DOOR);


        //Left connection

        lootCell1.getLeftConnection().setType(Connection.EDGE);


        //Right connection

        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);

        //Loot Cell 2

        //Up connection

        lootCell2.getUpConnection().setType(Connection.EDGE);


        //Down connection

        lootCell2.getDownConnection().setType(Connection.WALL);


        //Left connection

        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection

        lootCell2.getRightConnection().setType(Connection.FREE);
        lootCell2.getRightConnection().setConnectedCell(spawnCell);

        //Spawn cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.EDGE);


        //Down connection

        spawnCell.getDownConnection().setType(Connection.DOOR);


        //Left connection

        spawnCell.getLeftConnection().setType(Connection.FREE);
        spawnCell.getLeftConnection().setConnectedCell(lootCell2);

        //Right connection

        spawnCell.getRightConnection().setType(Connection.EDGE);



        cells.add(spawnCell);
        cells.add(lootCell1);
        cells.add(lootCell2);

        blueRoom.setCells(cells);
        blueRoom.setColour(Colour.BLUE);
        return blueRoom;
    }

}
