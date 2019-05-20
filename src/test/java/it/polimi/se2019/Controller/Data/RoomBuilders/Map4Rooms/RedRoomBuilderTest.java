package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Room;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

public class RedRoomBuilderTest {

    @Test
    public void CorrectColourTest() {

        RedRoomBuilder redRoomBuilder = new RedRoomBuilder();
        Room redRoom = redRoomBuilder.build();

        int first = Colour.RED;
        int second = redRoom.getColour();

        Assert.assertEquals(first, second);


    }

    @Test
    public void CorrectCellNamesTest() {

        RedRoomBuilder redRoomBuilder = new RedRoomBuilder();

        Room redRoom = redRoomBuilder.build();
        CopyOnWriteArrayList<String> cellNames = new CopyOnWriteArrayList<>();

        cellNames.add("lootCell");
        cellNames.add("spawnCell");

        for (Cell cell : redRoom.getCells()) {

            assertTrue(cellNames.contains(cell.getName()));

        }


    }

    @Test
    public void CorrectConnectionsTest() {

        RedRoomBuilder redRoomBuilder = new RedRoomBuilder();
        Room redRoom = redRoomBuilder.build();

        for (Cell cell : redRoom.getCells()) {

            String upConnection = cell.getUpConnection().getType();
            String downConnection = cell.getDownConnection().getType();
            String leftConnection = cell.getLeftConnection().getType();
            String rightConnection = cell.getRightConnection().getType();

            if (cell.getName().equalsIgnoreCase("lootCell")) {
                assertTrue(upConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(downConnection.equalsIgnoreCase(Connection.FREE));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.DOOR));
                Cell downConnected = null;

                for (Cell connected : redRoom.getCells()) {
                    if (connected.getName().equalsIgnoreCase("spawnCell")) {
                       downConnected = connected;

                    }
                }

                assertTrue(downConnected.equals(cell.getDownConnection().getConnectedCell()));
            }

            if (cell.getName().equalsIgnoreCase("spawnCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.FREE));
                assertTrue(downConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.WALL));

                Cell upConnected = null;

                for (Cell connected : redRoom.getCells()) {

                    if (connected.getName().equalsIgnoreCase("lootCell")) {
                        upConnected = connected;

                    }


                }

                assertTrue(upConnected.equals(cell.getUpConnection().getConnectedCell()));

            }



        }
    }



}