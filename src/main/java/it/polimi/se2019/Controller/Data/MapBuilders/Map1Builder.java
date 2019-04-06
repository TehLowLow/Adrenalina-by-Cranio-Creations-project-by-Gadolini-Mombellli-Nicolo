package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.RoomBuilders.*;

public class Map1Builder {

    public Room yellowRoom;

    public void build(){

        /*
        ------------------YELLOW ROOM-------------------
         */

        YellowRoomBuilder yellowRoomBuilder = new YellowRoomBuilder();;

        Room yellowRoom = new Room();
        yellowRoom = yellowRoomBuilder.build();


        /*costruttori di stanze rosse, blu, verde...*/

    }

}