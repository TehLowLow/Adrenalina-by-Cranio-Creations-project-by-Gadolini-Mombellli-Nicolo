package it.polimi.se2019.Controller.Data.MapBuilders;

import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Map;
import org.junit.Test;

import static org.junit.Assert.*;

public class Map3BuilderTest {

    @Test
    public void build() {

        Map3Builder map3Builder = new Map3Builder();

        Map map = map3Builder.build();

        assertEquals(map.getRedRoom().getCells().size(), 2);

        for(Cell cell : map.getRedRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getRightConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell"));
            }

            if(cell.getName().equalsIgnoreCase("spawnCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell1"));
            }
        }

        assertEquals(map.getYellowRoom().getCells().size(), 2);

        for(Cell cell : map.getYellowRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getLeftConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell2"));
            }

            if(cell.getName().equalsIgnoreCase("spawnCell")){
                assertTrue(cell.getLeftConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell3"));
            }

        }

        assertEquals(map.getBlueRoom().getCells().size(), 2);

        for(Cell cell : map.getBlueRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell1"));
            }

            if(cell.getName().equalsIgnoreCase("spawnCell")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell2"));
            }

        }

        assertEquals(map.getPurpleRoom().getCells().size(), 2);

        for(Cell cell : map.getPurpleRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell1")){
                assertTrue(cell.getDownConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell2"));
            }

            if(cell.getName().equalsIgnoreCase("lootCell2")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("spawnCell"));
            }

        }

        assertEquals(map.getWhiteRoom().getCells().size(), 3);

        for(Cell cell : map.getWhiteRoom().getCells()){

            if(cell.getName().equalsIgnoreCase("lootCell1")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("spawnCell"));
            }

            if(cell.getName().equalsIgnoreCase("lootCell2")){
                assertTrue(cell.getUpConnection().getConnectedCell().getName().equalsIgnoreCase("lootCell1"));
            }

        }


    }





    }
