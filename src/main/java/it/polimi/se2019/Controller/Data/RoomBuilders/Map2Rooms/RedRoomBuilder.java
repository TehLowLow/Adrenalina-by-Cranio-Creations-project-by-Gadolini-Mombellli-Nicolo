package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

public class RedRoomBuilder {


    public Room build() {

        ArrayList<Cell> cells = new ArrayList<Cell>();
        Room redRoom = new Room();

        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setColour(Colour.RED);
        spawnCell.setName("spawnCell");

        Cell lootCell = lootCellBuilder.build();
        lootCell.setColour(Colour.RED);
        lootCell.setName("lootCell");

        //Spawn Cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.DOOR);

        //Down connection

        spawnCell.getDownConnection().setType(Connection.EDGE);

        //Left connection

        spawnCell.getLeftConnection().setType(Connection.EDGE);

        //Right connection

        spawnCell.getRightConnection().setType(Connection.FREE);
        spawnCell.getRightConnection().setConnectedCell(lootCell);


        //Loot cell

        //Up connection

        lootCell.getUpConnection().setType(Connection.WALL);

        //Down connection

        lootCell.getDownConnection().setType(Connection.DOOR);

        //Left connection

        lootCell.getLeftConnection().setType(Connection.FREE);
        lootCell.getLeftConnection().setConnectedCell(spawnCell);

        //Right connection

        lootCell.getRightConnection().setType(Connection.WALL);


        cells.add(spawnCell);
        cells.add(lootCell);

        redRoom.setCells(cells);
        redRoom.setColour(Colour.RED);

        return redRoom;

    }

}