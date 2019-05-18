package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;
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
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an CopyOnWriteArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */



    public Room build(){

        it.polimi.se2019.Controller.Data.RoomBuilders.Map3Rooms.BlueRoomBuilder brB = new it.polimi.se2019.Controller.Data.RoomBuilders.Map3Rooms.BlueRoomBuilder();
        Room blueRoom = brB.build();

        for(Cell cell : blueRoom.getCells()){
            if(cell.getName().equalsIgnoreCase("spawnCell")){
                cell.getRightConnection().setType(Connection.DOOR);
            }
        }

        return blueRoom;
    }

}

