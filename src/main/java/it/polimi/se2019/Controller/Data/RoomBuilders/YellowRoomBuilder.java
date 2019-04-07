package it.polimi.se2019.Controller.Data.RoomBuilders;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.YellowSpawnCellBuilder;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class YellowRoomBuilder {

    private Room yellowRoom = new Room();

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
