package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.RoomBuilders.*;

public class Map1Builder {

    public Room yellowRoom;



    public Map build(){

        /*
        ------------------YELLOW ROOM-------------------
         */

        YellowRoomBuilder yellowRoomBuilder = new YellowRoomBuilder();

        Room yellowRoom = new Room();
        yellowRoom = yellowRoomBuilder.build();


        /*costruttori di stanze rosse, blu, verde...*/

        //TODO: Inserire il codice per settare le connessioni tra celle che appartengono a stanze diverse.
        // le connessioni tra celle della stessa stanza sono già state impostate dai builder delle stanze.

        return new Map(1, null, null, yellowRoom, null,null, null);

    }

}