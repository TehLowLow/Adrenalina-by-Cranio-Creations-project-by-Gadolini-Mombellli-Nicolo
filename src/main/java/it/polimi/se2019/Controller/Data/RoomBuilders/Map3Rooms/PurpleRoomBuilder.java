package it.polimi.se2019.Controller.Data.RoomBuilders.Map3Rooms;
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

        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setColour(Colour.PURPLE);
        lootCell1.setName("lootCell1");

        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setColour(Colour.PURPLE);
        lootCell2.setName("lootCell2");


        //lootCell 1

        //Up connection
        lootCell1.getUpConnection().setType(Connection.DOOR);

        //Down connection
        lootCell1.getDownConnection().setType(Connection.DOOR);


        //Left connection
        lootCell1.getLeftConnection().setType(Connection.WALL);

        //Right connection
        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);


        //lootCell 2

        //Up connection
        lootCell2.getUpConnection().setType(Connection.DOOR);

        //Down connection
        lootCell2.getDownConnection().setType(Connection.WALL);

        //Left connection
        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection
        lootCell2.getRightConnection().setType(Connection.DOOR);

        cells.add(lootCell1);
        cells.add(lootCell2);

        purpleRoom.setCells(cells);
        purpleRoom.setColour(Colour.PURPLE);
        return purpleRoom;

    }


}
