package it.polimi.se2019.Controller.Data.RoomBuilders.Map3Rooms;

import static org.junit.Assert.*;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Room;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

public class BlueRoomBuilderTest {

    @Test
    public void CorrectColourTest() {

        BlueRoomBuilder blueRoomBuilder = new BlueRoomBuilder();
        Room blueRoom = blueRoomBuilder.build();

        int first = Colour.BLUE;
        int second = blueRoom.getColour();

        Assert.assertEquals(first, second);


    }

    @Test
    public void CorrectCellNamesTest() {

        BlueRoomBuilder blueRoomBuilder = new BlueRoomBuilder();

        Room blueRoom = blueRoomBuilder.build();
        CopyOnWriteArrayList<String> cellNames = new CopyOnWriteArrayList<>();

        cellNames.add("lootCell");
        cellNames.add("spawnCell");

        for (Cell cell : blueRoom.getCells()) {

            assertTrue(cellNames.contains(cell.getName()));

        }


    }

    @Test
    public void CorrectConnectionsTest() {

        BlueRoomBuilder blueRoomBuilder = new BlueRoomBuilder();
        Room blueRoom = blueRoomBuilder.build();

        for (Cell cell : blueRoom.getCells()) {

            String upConnection = cell.getUpConnection().getType();
            String downConnection = cell.getDownConnection().getType();
            String leftConnection = cell.getLeftConnection().getType();
            String rightConnection = cell.getRightConnection().getType();


            if (cell.getName().equalsIgnoreCase("spawnCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(downConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.FREE));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.EDGE));


                Cell leftConnected = null;


                for (Cell connected : blueRoom.getCells()) {


                    if (connected.getName().equalsIgnoreCase("lootCell")) {
                        leftConnected = connected;

                    }


                }


                assertTrue(leftConnected.equals(cell.getLeftConnection().getConnectedCell()));

            }

            if (cell.getName().equalsIgnoreCase("lootCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(downConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.FREE));


                Cell rightConnected = null;


                for (Cell connected : blueRoom.getCells()) {


                    if (connected.getName().equalsIgnoreCase("spawnCell")) {
                        rightConnected = connected;

                    }


                }


                assertTrue(rightConnected.equals(cell.getRightConnection().getConnectedCell()));

            }

        }
    }
}

