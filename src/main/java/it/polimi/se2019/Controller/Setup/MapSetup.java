package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.MapBuilders.*;

/**
 * Creates a map instance, gathering all the rooms.
 */

public class MapSetup {

    /**
     * Creates a map collecting all the rooms.
     * @param mapNumber is an int representing the map.
     * @return a complete map.
     */
    public Map build(int mapNumber) {

        if (mapNumber == 1){
            Map1Builder map1Builder = new Map1Builder();

            return map1Builder.build();
        }

        if (mapNumber == 2){
            Map2Builder map2Builder = new Map2Builder();

            return map2Builder.build();
        }


        if (mapNumber == 3){
            Map3Builder map3Builder = new Map3Builder();

            return map3Builder.build();
        }


        if (mapNumber == 4){
            Map4Builder map4Builder = new Map4Builder();


            return map4Builder.build();
        }

        else{
            Map1Builder map1Builder = new Map1Builder();

            return map1Builder.build();
        }
    }
}