package it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms;

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


            if (cell.getName().equalsIgnoreCase("spawnCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(downConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.FREE));

                Cell rightConnected = null;

                for (Cell connected : redRoom.getCells()) {


                    if (connected.getName().equalsIgnoreCase("lootCell")) {
                        rightConnected = connected;

                    }


                }

                assertTrue(rightConnected.equals(cell.getRightConnection().getConnectedCell()));

            }

            if (cell.getName().equalsIgnoreCase("lootCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.WALL));
                assertTrue(downConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.FREE));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.WALL));

                Cell leftConnected = null;


                for (Cell connected : redRoom.getCells()) {



                    if (connected.getName().equalsIgnoreCase("spawnCell")) {
                        leftConnected = connected;

                    }



                }


                assertTrue(leftConnected.equals(cell.getLeftConnection().getConnectedCell()));

            }

        }
    }




}