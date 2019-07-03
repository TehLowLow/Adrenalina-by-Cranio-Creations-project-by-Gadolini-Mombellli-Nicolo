package it.polimi.se2019.Controller.Setup;

import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map2Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map3Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map4Builder;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapSetupTest {

    @Test
    public void buildTest() {

        Map1Builder map1Builder = new Map1Builder();
        Map2Builder map2Builder = new Map2Builder();
        Map3Builder map3Builder = new Map3Builder();
        Map4Builder map4Builder = new Map4Builder();

        MapSetup mapSetup = new MapSetup();

        assertEquals(map1Builder.build().getRedRoom().getCells().size(), mapSetup.build(1).getRedRoom().getCells().size());
        assertEquals(map2Builder.build().getRedRoom().getCells().size(), mapSetup.build(2).getRedRoom().getCells().size());
        assertEquals(map3Builder.build().getRedRoom().getCells().size(), mapSetup.build(3).getRedRoom().getCells().size());
        assertEquals(map4Builder.build().getRedRoom().getCells().size(), mapSetup.build(4).getRedRoom().getCells().size());

    }
}