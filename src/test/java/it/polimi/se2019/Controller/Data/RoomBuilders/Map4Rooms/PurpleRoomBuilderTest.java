package it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Room;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

public class PurpleRoomBuilderTest {

    @Test
    public void CorrectColourTest() {

        PurpleRoomBuilder purpleRoomBuilder = new PurpleRoomBuilder();
        Room purpleRoom = purpleRoomBuilder.build();

        int first = Colour.PURPLE;
        int second = purpleRoom.getColour();

        Assert.assertEquals(first, second);


    }

    @Test
    public void CorrectCellNamesTest() {

        PurpleRoomBuilder purpleRoomBuilder = new PurpleRoomBuilder();

        Room purpleRoom = purpleRoomBuilder.build();
        CopyOnWriteArrayList<String> cellNames = new CopyOnWriteArrayList<>();

        cellNames.add("lootCell");

        for (Cell cell : purpleRoom.getCells()) {

            assertTrue(cellNames.contains(cell.getName()));

        }


    }

    @Test
    public void CorrectConnectionsTest() {

        PurpleRoomBuilder purpleRoomBuilder = new PurpleRoomBuilder();
        Room purpleRoom = purpleRoomBuilder.build();

        for (Cell cell : purpleRoom.getCells()) {

            String upConnection = cell.getUpConnection().getType();
            String downConnection = cell.getDownConnection().getType();
            String leftConnection = cell.getLeftConnection().getType();
            String rightConnection = cell.getRightConnection().getType();


            if (cell.getName().equalsIgnoreCase("lootCell")) {

                assertTrue(upConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(downConnection.equalsIgnoreCase(Connection.DOOR));
                assertTrue(leftConnection.equalsIgnoreCase(Connection.WALL));
                assertTrue(rightConnection.equalsIgnoreCase(Connection.WALL));


            }
        }
    }

}