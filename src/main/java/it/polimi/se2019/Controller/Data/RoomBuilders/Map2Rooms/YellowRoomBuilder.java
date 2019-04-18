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

public class YellowRoomBuilder {

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an ArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */


    public Room build(){

        ArrayList<Cell> cells = new ArrayList<Cell>();
        Room yellowRoom = new Room();

        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setColour(Colour.YELLOW);
        lootCell1.setName("lootCell1");

        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setColour(Colour.YELLOW);
        lootCell2.setName("lootCell2");

        Cell lootCell3 = lootCellBuilder.build();
        lootCell3.setColour(Colour.YELLOW);
        lootCell3.setName("lootCell3");

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setColour(Colour.YELLOW);
        spawnCell.setName("spawnCell");

        //Spawn Cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.FREE);
        spawnCell.getUpConnection().setConnectedCell(lootCell2);

        //Down connection

        spawnCell.getDownConnection().setType(Connection.EDGE);


        //Left connection

        spawnCell.getLeftConnection().setType(Connection.FREE);
        spawnCell.getLeftConnection().setConnectedCell(lootCell3);

        //Right connection

        spawnCell.getRightConnection().setType(Connection.EDGE);



        //Loot Cell 1

        //Up connection

        lootCell1.getUpConnection().setType(Connection.DOOR);


        //Down connection

        lootCell1.getDownConnection().setType(Connection.FREE);
        lootCell1.getDownConnection().setConnectedCell(lootCell3);

        //Left connection

        lootCell1.getLeftConnection().setType(Connection.WALL);


        //Right connection

        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);



        //Loot Cell 2

        //Up connection
        lootCell2.getUpConnection().setType(Connection.DOOR);


        //Down connection

        lootCell2.getDownConnection().setType(Connection.FREE);
        lootCell2.getDownConnection().setConnectedCell(spawnCell);

        //Left connection

        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection

        lootCell2.getRightConnection().setType(Connection.EDGE);



        //Loot Cell 3

        //Up connection

        lootCell3.getUpConnection().setType(Connection.FREE);
        lootCell3.getUpConnection().setConnectedCell(lootCell1);

        //Down connection

        lootCell3.getDownConnection().setType(Connection.EDGE);


        //Left connection

        lootCell3.getLeftConnection().setType(Connection.DOOR);

        //Right connection

        lootCell3.getRightConnection().setType(Connection.FREE);
        lootCell3.getRightConnection().setConnectedCell(spawnCell);


        cells.add(spawnCell);
        cells.add(lootCell1);
        cells.add(lootCell2);
        cells.add(lootCell3);

        yellowRoom.setCells(cells);
        yellowRoom.setColour(Colour.YELLOW);
        return yellowRoom;

    }
}
