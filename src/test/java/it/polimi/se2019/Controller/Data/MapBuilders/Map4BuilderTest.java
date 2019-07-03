package it.polimi.se2019.Controller.Data.MapBuilders;

import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Map;
import org.junit.Test;

import static org.junit.Assert.*;

public class Map4BuilderTest {

    @Test
    public void build() {

        Map4Builder map4Builder = new Map4Builder();

        Map map = map4Builder.build();

        assertEquals(map.getRedRoom().getCells().size(), 2);

        for(Cell cell : map.getRedRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getRightConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell"));
            }

            if(cell.getName().equalsIgnoreCase("spawnCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell1"));
            }
        }

        assertEquals(map.getYellowRoom().getCells().size(), 4);

        for(Cell cell : map.getYellowRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell1")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("spawnCell"));
            }

            if(cell.getName().equalsIgnoreCase("lootCell2")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell"));
            }

            if(cell.getName().equalsIgnoreCase("lootCell3")){
                assertTrue(cell.getLeftConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell2"));
            }
        }

        assertEquals(map.getBlueRoom().getCells().size(), 2);

        for(Cell cell : map.getBlueRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell"));
            }

            if(cell.getName().equalsIgnoreCase("spawnCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell1"));
            }

        }

        assertEquals(map.getGreenRoom().getCells().size(), 1);

        for(Cell cell : map.getGreenRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell2"));
            }

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getLeftConnection().getConnectedCell().getName().equalsIgnoreCase("spawnCell"));
            }

        }

        assertEquals(map.getWhiteRoom().getCells().size(), 2);

        for(Cell cell : map.getWhiteRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell1")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("spawnCell"));
            }

            if(cell.getName().equalsIgnoreCase("lootCell2")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell"));
            }

        }

        assertEquals(map.getPurpleRoom().getCells().size(), 1);

        for(Cell cell : map.getPurpleRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell"));
            }

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell2"));
            }

        }


    }

}