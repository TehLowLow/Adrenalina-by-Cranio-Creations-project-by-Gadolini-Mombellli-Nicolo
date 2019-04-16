package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.MapBuilders.*;

/**
 * Creates a map instance, gathering all the rooms.
 */

public class MapSetup {

    /**
     * Creates a map collecting all the rooms.
     * @return a complete map.
     */
    public Map build() {

        Map3Builder map3Builder = new Map3Builder();


        return map3Builder.build();


    }
}