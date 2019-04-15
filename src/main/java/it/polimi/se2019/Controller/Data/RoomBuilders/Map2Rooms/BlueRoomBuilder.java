package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

public class BlueRoomBuilder {


    public Room build() {

        ArrayList<Cell> cells = new ArrayList<Cell>();
        Room blueRoom = new Room();

        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setColour(Colour.BLUE);
        spawnCell.setName("spawnCell");

        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setColour(Colour.BLUE);
        lootCell1.setName("lootCell1");

        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setColour(Colour.BLUE);
        lootCell2.setName("lootCell2");

        //Spawn cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.EDGE);

        //Down connection

        spawnCell.getDownConnection().setType(Connection.DOOR);

        //Left connection

        spawnCell.getLeftConnection().setType(Connection.FREE);
        spawnCell.getLeftConnection().setConnectedCell(lootCell2);

        //Right connection

        spawnCell.getRightConnection().setType(Connection.DOOR);

        //Loot cell 1

        //Up connection

        lootCell1.getUpConnection().setType(Connection.EDGE);

        //Down connection

       lootCell1.getDownConnection().setType(Connection.DOOR);

        //Left connection

        lootCell1.getLeftConnection().setType(Connection.EDGE);

        //Right connection

        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);


        //Loot cell 2

        //Up connection
        lootCell2.getUpConnection().setType(Connection.EDGE);

        //Down connection

        lootCell2.getDownConnection().setType(Connection.WALL);

        //Left connection

        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection

        lootCell2.getRightConnection().setType(Connection.FREE);
        lootCell2.getRightConnection().setConnectedCell(spawnCell);


        cells.add(spawnCell);
        cells.add(lootCell1);
        cells.add(lootCell2);

        blueRoom.setCells(cells);
        blueRoom.setColour(Colour.BLUE);

        return blueRoom;


    }

}