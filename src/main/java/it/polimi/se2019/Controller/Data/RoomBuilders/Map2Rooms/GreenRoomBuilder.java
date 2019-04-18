package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an arraylist of
 * cells, and it has a colour as an identifier.
 */

public class GreenRoomBuilder {

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
        Room greenRoom = new Room();

        LootCellBuilder lootCellBuilder = new LootCellBuilder();
        Cell lootCell = lootCellBuilder.build();
        lootCell.setName("lootCell");
        lootCell.setColour(Colour.GREEN);

        //Loot Cell

        //Up connection

        lootCell.getUpConnection().setType(Connection.EDGE);

        //Down connection

        lootCell.getDownConnection().setType(Connection.DOOR);

        //Left connection

        lootCell.getLeftConnection().setType(Connection.DOOR);

        //Right connection

        lootCell.getRightConnection().setType(Connection.EDGE);

        cells.add(lootCell);

        greenRoom.setCells(cells);
        greenRoom.setColour(Colour.GREEN);
        return greenRoom;
    }

}
