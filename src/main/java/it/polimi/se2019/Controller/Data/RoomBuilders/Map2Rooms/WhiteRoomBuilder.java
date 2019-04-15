package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

public class WhiteRoomBuilder {

    public Room build(){

        ArrayList<Cell> cells = new ArrayList<Cell>();
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
