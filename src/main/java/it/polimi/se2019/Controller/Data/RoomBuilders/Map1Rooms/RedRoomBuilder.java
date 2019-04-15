package it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

public class RedRoomBuilder {

    private Room redRoom = new Room();

    public Room build(){

        ArrayList<Cell> cells = new ArrayList<Cell>();
        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setName("spawnCell");
        spawnCell.setColour(Colour.RED);
        Cell lootCell1 = lootCellBuilder.build();
        lootCell1.setName("lootCell1");
        lootCell1.setColour(Colour.RED);
        Cell lootCell2 = lootCellBuilder.build();
        lootCell2.setColour(Colour.RED);
        lootCell2.setName("lootCell2");


        //Spawn cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.DOOR);


        //Down connection

        spawnCell.getDownConnection().setType(Connection.EDGE);


        //Left connection

        spawnCell.getLeftConnection().setType(Connection.EDGE);


        //Right connection

        spawnCell.getRightConnection().setType(Connection.FREE);
        spawnCell.getRightConnection().setConnectedCell(lootCell1);



        //Loot cell 1

        //Up connection

        lootCell1.getUpConnection().setType(Connection.WALL);


        //Down connection

        lootCell1.getDownConnection().setType(Connection.DOOR);


        //Left connection

        lootCell1.getLeftConnection().setType(Connection.FREE);
        lootCell1.getLeftConnection().setConnectedCell(spawnCell);

        //Right connection

        lootCell1.getRightConnection().setType(Connection.FREE);
        lootCell1.getRightConnection().setConnectedCell(lootCell2);



        //Loot cell 2

        //Up connection

        lootCell2.getUpConnection().setType(Connection.DOOR);


        //Down connection

        lootCell2.getDownConnection().setType(Connection.WALL);


        //Left connection

        lootCell2.getLeftConnection().setType(Connection.FREE);
        lootCell2.getLeftConnection().setConnectedCell(lootCell1);

        //Right connection

        lootCell2.getRightConnection().setType(Connection.DOOR);



        cells.add(spawnCell);
        cells.add(lootCell1);
        cells.add(lootCell2);

        redRoom.setCells(cells);
        redRoom.setColour(Colour.RED);
        return redRoom;

    }

}
