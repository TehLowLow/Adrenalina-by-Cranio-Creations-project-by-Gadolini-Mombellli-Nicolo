package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an CopyOnWriteArrayList of
 * cells, and it has a colour as an identifier.
 */

public class PurpleRoomBuilder {

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an CopyOnWriteArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */

    public Room build(){


        Room purpleRoom = new Room();
        CopyOnWriteArrayList <Cell> cells = new CopyOnWriteArrayList<Cell>();

        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell lootCell = lootCellBuilder.build();
        lootCell.setColour(Colour.PURPLE);
        lootCell.setName("lootCell");



        //lootCell

        //Up connection
        lootCell.getUpConnection().setType(Connection.DOOR);

        //Down connection
        lootCell.getDownConnection().setType(Connection.DOOR);


        //Left connection
        lootCell.getLeftConnection().setType(Connection.WALL);

        //Right connection
        lootCell.getRightConnection().setType(Connection.WALL);



        cells.add(lootCell);
        purpleRoom.setCells(cells);
        purpleRoom.setColour(Colour.PURPLE);
        return purpleRoom;

    }


}
