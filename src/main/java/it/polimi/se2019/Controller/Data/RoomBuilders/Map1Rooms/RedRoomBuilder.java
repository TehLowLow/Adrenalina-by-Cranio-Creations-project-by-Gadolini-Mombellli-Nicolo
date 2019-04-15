package it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import java.util.ArrayList;

public class RedRoomBuilder {

    private Room redRoom = new Room();

    public Room build(){

        ArrayList<Cell> cells = new ArrayList<Cell>();
        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();

        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setName("spawnCell");
        spawnCell.setColour(Colour.RED);

        //Spawn cell

        //Up connection

        spawnCell.getUpConnection().setType(Connection.DOOR);
        spawnCell.getUpConnection().setConnectedCell(null);

        //Down connection

        spawnCell.getDownConnection().setType(Connection.EDGE);
        spawnCell.getDownConnection().setConnectedCell(null);

        //Left connection

        spawnCell.getLeftConnection().setType(Connection.EDGE);
        spawnCell.getLeftConnection().setConnectedCell(null);

        //Right connection

        spawnCell.getRightConnection().setType(Connection.WALL);
        spawnCell.getRightConnection().setConnectedCell(null);

        cells.add(spawnCell);

        redRoom.setCells(cells);
        redRoom.setColour(Colour.RED);
        return redRoom;

    }

}
