package it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Room;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class YellowRoomBuilderTest {

    @Test
    public void CorrectColourTest() {

        YellowRoomBuilder yellowRoomBuilder = new YellowRoomBuilder();
        Room yellowRoom = yellowRoomBuilder.build();

        int first = Colour.YELLOW;
        int second = yellowRoom.getColour();

        Assert.assertEquals(first, second);


    }

    @Test
    public void CorrectCellNamesTest() {

        YellowRoomBuilder yellowRoomBuilder = new YellowRoomBuilder();

        Room yellowRoom = yellowRoomBuilder.build();
        ArrayList<String> cellNames = new ArrayList<>();

        cellNames.add("lootCell");
        cellNames.add("spawnCell");

        for (Cell cell : yellowRoom.getCells()) {

            assertTrue(cellNames.contains(cell.getName()));

        }


    }

    @Test
    public void CorrectConnectionsTest() {

        YellowRoomBuilder yellowRoomBuilder = new YellowRoomBuilder();
        Room yellowRoom = yellowRoomBuilder.build();

        for (Cell cell : yellowRoom.getCells()) {

            String upConnection = cell.getUpConnection().getType();
            String downConnection = cell.getDownConnection().getType();
            String leftConnection = cell.getLeftConnection().getType();
            String rightConnection = cell.getRightConnection().getType();


            if (cell.getName().equalsIgnoreCase("spawnCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.FREE));
                assertTrue(downConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.EDGE));


                Cell upConnected = null;


                for (Cell connected : yellowRoom.getCells()) {


                    if (connected.getName().equalsIgnoreCase("lootCell")) {
                        upConnected = connected;

                    }



                }

                assertTrue(upConnected.equals(cell.getUpConnection().getConnectedCell()));

            }

            if (cell.getName().equalsIgnoreCase("lootCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.EDGE));
                assertTrue(downConnection.equalsIgnoreCase(Connection.FREE));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.EDGE));



                Cell downConnected = null;


                for (Cell connected : yellowRoom.getCells()) {



                    if (connected.getName().equalsIgnoreCase("spawnCell")) {
                        downConnected = connected;

                    }



                }

                assertTrue(downConnected.equals(cell.getDownConnection().getConnectedCell()));


            }

        }
    }




}