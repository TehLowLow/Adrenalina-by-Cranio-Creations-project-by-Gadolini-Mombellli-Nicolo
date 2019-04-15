package it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.LootCellBuilder;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.SpawnCellBuilder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
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

        //La stanza ha un arraylist di celle.

        ArrayList<Cell> cells = new ArrayList <Cell>();

        //Creo ogni cella della stanza.
        SpawnCellBuilder spawnCellBuilder = new SpawnCellBuilder();
        LootCellBuilder lootCellBuilder = new LootCellBuilder();


        Cell spawnCell = spawnCellBuilder.build();
        spawnCell.setName("spawnCell");
        spawnCell.setColour(Colour.YELLOW);
        Cell lootCell = lootCellBuilder.build();
        lootCell.setName("lootCell");
        lootCell.setColour(Colour.YELLOW);

        //Connetto le celle della stanza tra di loro.

        //----- SpawnCell:

        //Right connection
        spawnCell.getRightConnection().setType(Connection.EDGE);



        //Down connection
        spawnCell.getDownConnection().setType(Connection.EDGE);



        //Left connection
        spawnCell.getLeftConnection().setType(Connection.DOOR);



        //Up connection

        spawnCell.getUpConnection().setType(Connection.FREE);
        spawnCell.getUpConnection().setConnectedCell(lootCell);


        //--- LootCell:

        //Right Connection

        lootCell.getRightConnection().setType(Connection.EDGE);



        //Left Connection

        lootCell.getLeftConnection().setType(Connection.DOOR);



        //Down connection

        lootCell.getDownConnection().setType(Connection.FREE);
        lootCell.getDownConnection().setConnectedCell(spawnCell);


        //Up connection

        lootCell.getUpConnection().setType(Connection.EDGE);



        //Aggiungo le celle nell'arraylist della stanza

        cells.add(spawnCell);
        cells.add(lootCell);

        //Assegno alla stanza l'ArrayList di celle e il colore.
        yellowRoom.setCells(cells);
        yellowRoom.setColour(Colour.YELLOW);


        return yellowRoom;

    }


}



