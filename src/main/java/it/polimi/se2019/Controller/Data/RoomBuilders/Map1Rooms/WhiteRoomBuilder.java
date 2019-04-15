package it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class WhiteRoomBuilder {

    private Room whiteRoom = new Room();

    public Room build(){

        ArrayList<Cell> cells = new ArrayList <Cell>();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setName("lootCell1");
        lootCell1.setColour(Colour.WHITE);
        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setName("lootCell2");
        lootCell2.setColour(Colour.WHITE);

        //Loot cell 1

        //Up connection:

        lootCell1.getUpConnection().setType(Connection.DOOR);
        lootCell1.getUpConnection().setConnectedCell(null);

        //Right connection

        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);

        //Down connection

        lootCell1.getDownConnection().setType(Connection.EDGE);
        lootCell1.getDownConnection().setConnectedCell(null);

        //Left connection

        lootCell1.getLeftConnection().setType(Connection.EDGE);
        lootCell1.getLeftConnection().setConnectedCell(null);

        //Loot cell 2

        //Up connection

        lootCell2.getUpConnection().setType(Connection.WALL);
        lootCell2.getUpConnection().setConnectedCell(null);

        //Down connection

        lootCell2.getDownConnection().setType(Connection.EDGE);
        lootCell2.getDownConnection().setConnectedCell(null);

        //Left connection

        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection

        lootCell2.getRightConnection().setType(Connection.DOOR);
        lootCell2.getRightConnection().setConnectedCell(null);


        cells.add(lootCell1);
        cells.add(lootCell2);

        whiteRoom.setCells(cells);

        whiteRoom.setColour(Colour.WHITE);

        return whiteRoom;
    }

}
