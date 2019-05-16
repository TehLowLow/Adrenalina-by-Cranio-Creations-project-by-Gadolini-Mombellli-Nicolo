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

public class RedRoomBuilder {

    /*
     * ----------- FIELDS ----------
     * There is just a field for the room that will be returned.
     */

    /**
     * The room that will be built.
     */

    private Room redRoom = new Room();

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an CopyOnWriteArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */

    public Room build(){

        CopyOnWriteArrayList<Cell> cells = new CopyOnWriteArrayList<Cell>();
        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setName("spawnCell");
        spawnCell.setColour(Colour.RED);
        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setName("lootCell1");
        lootCell1.setColour(Colour.RED);
        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setColour(Colour.RED);
        lootCell2.setName("lootCell2");


        //Spawn cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.DOOR);


        //Down connection

        spawnCell.getDownConnection().setType(Connection.EDGE);


        //Left connection

        spawnCell.getLeftConnection().setType(Connection.EDGE);


        //Right connection

        spawnCell.getRightConnection().setType(Connection.FREE);
        spawnCell.getRightConnection().setConnectedCell(lootCell1);



        //Loot cell 1

        //Up connection

        lootCell1.getUpConnection().setType(Connection.WALL);


        //Down connection

        lootCell1.getDownConnection().setType(Connection.DOOR);


        //Left connection

        lootCell1.getLeftConnection().setType(Connection.FREE);
        lootCell1.getLeftConnection().setConnectedCell(spawnCell);

        //Right connection

        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);



        //Loot cell 2

        //Up connection

        lootCell2.getUpConnection().setType(Connection.DOOR);


        //Down connection

        lootCell2.getDownConnection().setType(Connection.WALL);


        //Left connection

        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection

        lootCell2.getRightConnection().setType(Connection.DOOR);



        cells.add(spawnCell);
        cells.add(lootCell1);
        cells.add(lootCell2);

        redRoom.setCells(cells);
        redRoom.setColour(Colour.RED);
        return redRoom;

    }

}
