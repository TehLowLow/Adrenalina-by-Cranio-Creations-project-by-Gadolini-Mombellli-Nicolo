package it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds a Spawn Cell
 */

public class SpawnCellBuilder {

    /*
    -------------------FIELDS----------------------
     */

    /**
     * This field will contain the Spawn Cell, whose object is an instance of SpawnCell
     */


    private SpawnCell spawnCell;

    /*
    ---------------------METHODS----------------------------
     */

    /**
     * This is the builder of the SpawnCell, that assigns the four connections and sets the weapons
     * available from the Spawn Cell.
     * @return the reference of the Spawn Cell object, with all its fields filled.
     */

    public SpawnCell build(){

        spawnCell = new SpawnCell();
        spawnCell.setDownConnection(new Connection());
        spawnCell.setLeftConnection(new Connection());
        spawnCell.setUpConnection(new Connection());
        spawnCell.setRightConnection(new Connection());
        //spawnCell.setAvailableWeapons();


      return spawnCell;

    }



}
