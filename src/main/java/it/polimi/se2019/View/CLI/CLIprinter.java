package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Player;

/**
 * Prints the CLI.
 */
public class CLIprinter {

    /**
     * Prints the CLI from a specified player's point of view
     * @param player
     * @return
     */
    public static String print(Player player){

        Map map = Board.getMap();

        String mapRep = "";

        if (map.getMapID() == 1){

            mapRep = Map1StringRep.map1();

        }

        if (map.getMapID() == 2){

            mapRep = Map2StringRep.map2();

        }

        if (map.getMapID() == 3){

            mapRep = Map3StringRep.map3();

        }

        if (map.getMapID() == 4){

            mapRep = Map4StringRep.map4();

        }


        return mapRep + PlayerInfo.getInfo(player);




    }

}
