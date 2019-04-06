package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.RoomBuilders.*;

public class Map1Builder {

    //private Map map1 = new Map();

    public Map build(){

        /*
        ------------------YELLOW ROOM-------------------
         */

        YellowRoomBuilder yellowRoomBuilder = new YellowRoomBuilder();;

        Room yellowRoom = new Room();
        yellowRoom = yellowRoomBuilder.build();



        return map1;
    }

}