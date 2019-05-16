package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Room;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

public class WhiteRoomBuilderTest {

    @Test
    public void CorrectColourTest() {

        WhiteRoomBuilder whiteRoomBuilder = new WhiteRoomBuilder();
        Room whiteRoom = whiteRoomBuilder.build();

        int first = Colour.WHITE;
        int second = whiteRoom.getColour();

        Assert.assertEquals(first, second);


    }

    @Test
    public void CorrectCellNamesTest() {

        WhiteRoomBuilder whiteRoomBuilder = new WhiteRoomBuilder();

        Room whiteRoom = whiteRoomBuilder.build();
        CopyOnWriteArrayList<String> cellNames = new CopyOnWriteArrayList<>();

        cellNames.add("lootCell");

        for (Cell cell : whiteRoom.getCells()) {

            assertTrue(cellNames.contains(cell.getName()));

        }


    }

    @Test
    public void CorrectConnectionsTest() {

        WhiteRoomBuilder whiteRoomBuilder = new WhiteRoomBuilder();
        Room whiteRoom = whiteRoomBuilder.build();

        for (Cell cell : whiteRoom.getCells()) {

            String upConnection = cell.getUpConnection().getType();
            String downConnection = cell.getDownConnection().getType();
            String leftConnection = cell.getLeftConnection().getType();
            String rightConnection = cell.getRightConnection().getType();


            if (cell.getName().equalsIgnoreCase("lootCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(downConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.DOOR));


            }
        }
    }




}