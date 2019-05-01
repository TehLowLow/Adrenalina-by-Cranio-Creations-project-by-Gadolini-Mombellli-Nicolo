package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Room;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GreenRoomBuilderTest {

    @Test
    public void CorrectColourTest() {

        GreenRoomBuilder greenRoomBuilder = new GreenRoomBuilder();
        Room greenRoom = greenRoomBuilder.build();

        int first = Colour.GREEN;
        int second = greenRoom.getColour();

        Assert.assertEquals(first, second);


    }

    @Test
    public void CorrectCellNamesTest() {

        GreenRoomBuilder greenRoomBuilder = new GreenRoomBuilder();

        Room greenRoom = greenRoomBuilder.build();
        ArrayList<String> cellNames = new ArrayList<>();

        cellNames.add("lootCell");

        for (Cell cell : greenRoom.getCells()) {

            assertTrue(cellNames.contains(cell.getName()));

        }


    }

    @Test
    public void CorrectConnectionsTest() {

        GreenRoomBuilder greenRoomBuilder = new GreenRoomBuilder();
        Room greenRoom = greenRoomBuilder.build();

        for (Cell cell : greenRoom.getCells()) {

            String upConnection = cell.getUpConnection().getType();
            String downConnection = cell.getDownConnection().getType();
            String leftConnection = cell.getLeftConnection().getType();
            String rightConnection = cell.getRightConnection().getType();


            if (cell.getName().equalsIgnoreCase("spawnCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(downConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.EDGE));


            }
        }
    }




}