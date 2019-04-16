package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class BlueRoomBuilder {



    public Room build(){

        Room blueRoom = new Room();
        ArrayList <Cell> cells = new ArrayList<Cell>();

        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setColour(Colour.BLUE);
        spawnCell.setName("spawnCell");

        Cell lootCell = lootCellBuilder.build();
        lootCell.setColour(Colour.BLUE);
        lootCell.setName("lootCell");


        //spawnCell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.EDGE);

        //Down connection
        spawnCell.getDownConnection().setType(Connection.DOOR);

        //Left connection
        spawnCell.getLeftConnection().setType(Connection.FREE);
        spawnCell.getLeftConnection().setConnectedCell(lootCell);

        //Right connection
        spawnCell.getRightConnection().setType(Connection.DOOR);


        //lootCell

        //Up connection
        lootCell.getUpConnection().setType(Connection.EDGE);

        //Down connection
        lootCell.getDownConnection().setType(Connection.DOOR);


        //Left connection
        lootCell.getLeftConnection().setType(Connection.DOOR);

        //Right connection
        lootCell.getRightConnection().setType(Connection.FREE);
        lootCell.getRightConnection().setConnectedCell(spawnCell);


        cells.add(spawnCell);
        cells.add(lootCell);

        blueRoom.setCells(cells);
        blueRoom.setColour(Colour.BLUE);
        return blueRoom;

    }

}

