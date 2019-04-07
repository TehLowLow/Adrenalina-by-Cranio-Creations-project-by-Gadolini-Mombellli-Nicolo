package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;

/**
 * Creates a map instance, gathering all the rooms.
 */

public class MapSetup {

    /**
     * Creates a map collecting all the rooms.
     * @return a complete map.
     */
    public Map build() {

        Map1Builder map1Builder = new Map1Builder();


        return map1Builder.build();


    }
}