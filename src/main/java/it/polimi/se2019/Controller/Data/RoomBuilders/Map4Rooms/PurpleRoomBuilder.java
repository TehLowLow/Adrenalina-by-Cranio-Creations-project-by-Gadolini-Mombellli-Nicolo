package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

public class PurpleRoomBuilder {

    public Room build(){


        Room purpleRoom = new Room();
        ArrayList <Cell> cells = new ArrayList<Cell>();

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
