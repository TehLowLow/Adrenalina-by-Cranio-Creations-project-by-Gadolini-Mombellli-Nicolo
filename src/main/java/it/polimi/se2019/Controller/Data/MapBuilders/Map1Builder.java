package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.RoomBuilders.*;


/**
 * This class creates a builder for the Map number 1.
 * Its purpose is to produce a fully working map with every room complete with cells. To achieve this,
 * it will call various builders for the rooms.
 */
public class Map1Builder {

    /*
     * ------FIELDS------
     * There will be one Room field for every room in this map.
     */

    /**
     * The Yellow Room of the Map 1.
     */
    public Room yellowRoom;


    /**
     * Builder method for the Map 1. It will produce an instance of Map corresponding to the Map number 1.
     * @return an istance of Map corresponding to the map number 1.
     */
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