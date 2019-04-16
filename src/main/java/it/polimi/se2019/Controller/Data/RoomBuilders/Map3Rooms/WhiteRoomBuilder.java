package it.polimi.se2019.Controller.Data.RoomBuilders.Map3Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

public class WhiteRoomBuilder {

    public Room build(){

        Room whiteRoom = new Room();
        ArrayList <Cell> cells = new ArrayList<Cell>();

        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setColour(Colour.WHITE);
        lootCell1.setName("lootCell1");

        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setColour(Colour.WHITE);
        lootCell2.setName("lootCell2");

        Cell lootCell3 = lootCellBuilder.build();
        lootCell3.setColour(Colour.WHITE);
        lootCell3.setName("lootCell3");

        //lootCell 1

        //Up connection
        lootCell1.getUpConnection().setType(Connection.DOOR);

        //Down connection
        lootCell1.getDownConnection().setType(Connection.EDGE);


        //Left connection
        lootCell1.getLeftConnection().setType(Connection.EDGE);

        //Right connection
        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);


        //lootCell 2

        //Up connection
        lootCell2.getUpConnection().setType(Connection.DOOR);

        //Down connection
        lootCell2.getDownConnection().setType(Connection.EDGE);

        //Left connection
        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection
        lootCell2.getRightConnection().setType(Connection.FREE);
        lootCell2.getRightConnection().setConnectedCell(lootCell3);

        //lootCell 3

        //Up connection
        lootCell3.getUpConnection().setType(Connection.WALL);

        //Down connection
        lootCell3.getDownConnection().setType(Connection.EDGE);


        //Left connection
        lootCell3.getLeftConnection().setType(Connection.FREE);
        lootCell3.getLeftConnection().setConnectedCell(lootCell2);


        //Right connection
        lootCell3.getRightConnection().setType(Connection.DOOR);



        cells.add(lootCell1);
        cells.add(lootCell2);
        cells.add(lootCell3);

        whiteRoom.setCells(cells);
        whiteRoom.setColour(Colour.WHITE);
        return whiteRoom;
    }

}
