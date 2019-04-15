package it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class BlueRoomBuilder {

    private Room blueRoom = new Room();


    public Room build(){

        ArrayList<Cell> cells = new ArrayList <Cell>();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();
        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();

        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setName("lootCell1");
        lootCell1.setColour(Colour.BLUE);

        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setName("lootCell2");
        lootCell2.setColour(Colour.BLUE);

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setName("spawnCell");
        spawnCell.setColour(Colour.BLUE);

        //Loot Cell 1

        //Up connection

        lootCell1.getUpConnection().setType(Connection.EDGE);
        lootCell1.getUpConnection().setConnectedCell(null);

        //Down connection

        lootCell1.getDownConnection().setType(Connection.DOOR);
        lootCell1.getDownConnection().setConnectedCell(null);

        //Left connection

        lootCell1.getLeftConnection().setType(Connection.EDGE);
        lootCell1.getLeftConnection().setConnectedCell(null);

        //Right connection

        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);

        //Loot Cell 2

        //Up connection

        lootCell2.getUpConnection().setType(Connection.EDGE);
        lootCell2.getUpConnection().setConnectedCell(null);

        //Down connection

        lootCell2.getDownConnection().setType(Connection.WALL);
        lootCell2.getDownConnection().setConnectedCell(null);

        //Left connection

        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection

        lootCell2.getRightConnection().setType(Connection.FREE);
        lootCell2.getRightConnection().setConnectedCell(spawnCell);

        //Spawn cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.EDGE);
        spawnCell.getUpConnection().setConnectedCell(null);

        //Down connection

        spawnCell.getDownConnection().setType(Connection.DOOR);
        spawnCell.getDownConnection().setConnectedCell(null);

        //Left connection

        spawnCell.getLeftConnection().setType(Connection.FREE);
        spawnCell.getLeftConnection().setConnectedCell(lootCell2);

        //Right connection

        spawnCell.getRightConnection().setType(Connection.EDGE);
        spawnCell.getRightConnection().setConnectedCell(null);


        cells.add(spawnCell);
        cells.add(lootCell1);
        cells.add(lootCell2);

        blueRoom.setCells(cells);
        blueRoom.setColour(Colour.BLUE);
        return blueRoom;
    }

}
