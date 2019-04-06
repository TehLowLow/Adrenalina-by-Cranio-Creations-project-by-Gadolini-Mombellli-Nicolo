package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;

/**
 * Creates a map instance, gathering all the rooms.
 */

public class Map1Setup {

    /**
     * Creates a map collecting all the rooms.
     * @return a complete map.
     */
    public Map build() {

        Map1Builder map1Builder = new Map1Builder();

        map1Builder.build();

        return new Map(1, map1Builder.yellowRoom);



    }
}