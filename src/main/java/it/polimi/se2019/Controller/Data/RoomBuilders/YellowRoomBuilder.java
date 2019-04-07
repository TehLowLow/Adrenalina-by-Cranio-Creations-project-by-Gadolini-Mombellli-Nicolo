package it.polimi.se2019.Controller.Data.RoomBuilders;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.YellowSpawnCellBuilder;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * This class is an example for the builder of a room. Every room is represented by an arraylist of
 * cells, and it has a colour as an identifier.
 */
public class YellowRoomBuilder {

    /*
     * ----------- FIELDS ----------
     * There is just a field for the room that will be returned.
     */

    /**
     * The room that will be built.
     */
    private Room yellowRoom = new Room();

    /*
     * ----------- METHODS -----------
     */

    /**
     * This method builds a room by creating an ArrayList of cells and filling it by calling builders for every
     * cell.
     * @return a Room complete with cells. Connection with cells of other rooms aren't created yet.
     */
    public Room build(){

        YellowSpawnCellBuilder yellowSpawnCellBuilder = new YellowSpawnCellBuilder();
        ArrayList<Cell> cells = new ArrayList <Cell>();

        cells.add(yellowSpawnCellBuilder.build());


        yellowRoom.setCells(cells);
        yellowRoom.setColour(0);


        return yellowRoom;

        //TODO: Una volta create tutte le celle della stanza, bisogna connetterle tra loro, settando
        // le relative connection. In questo punto del codice si possono assegnare solamente le connessioni interne
        // alla stanza corrente (YellowRoom in questo caso).
        // Si dovrà aspettare di avere tutte le altre stanze per settare le connessioni delle celle che si affacciano
        // su altre stanze: questa operazione verrà effettuata dal buider della mappa una volta istanziate tutte le stanze.
    }


}
