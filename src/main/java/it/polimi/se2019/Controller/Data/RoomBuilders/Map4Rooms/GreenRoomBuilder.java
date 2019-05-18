package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an CopyOnWriteArrayList of
 * cells, and it has a colour as an identifier.
 */

public class GreenRoomBuilder {

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an CopyOnWriteArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */

    public Room build(){

        it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms.GreenRoomBuilder grB = new it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms.GreenRoomBuilder();
        return grB.build();
    }

}
