package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an CopyOnWriteArrayList of
 * cells, and it has a colour as an identifier.
 */

public class WhiteRoomBuilder {

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
        Room whiteRoom = new Room();

        LootCellBuilder lootCellBuilder = new LootCellBuilder();
        Cell lootCell = lootCellBuilder.build();
        lootCell.setName("lootCell");
        lootCell.setColour(Colour.WHITE);

        //Loot Cell

        //Up connection

        lootCell.getUpConnection().setType(Connection.DOOR);

        //Down connection

        lootCell.getDownConnection().setType(Connection.EDGE);

        //Left connection

        lootCell.getLeftConnection().setType(Connection.EDGE);

        //Right connection

        lootCell.getRightConnection().setType(Connection.DOOR);

        cells.add(lootCell);
        whiteRoom.setCells(cells);
        whiteRoom.setColour(Colour.WHITE);

        return whiteRoom;

    }



}
