package it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds a Yellow Spawn Cell
 */

public class YellowSpawnCellBuilder {

    /*
    -------------------FIELDS----------------------
     */

    /**
     * This field will contain the yellowSpawnCell, whose object is an instance of SpawnCell
     */


    private SpawnCell yellowSpawnCell = new SpawnCell();

    /*
    ---------------------METHODS----------------------------
     */

    /**
     * This is the builder of the SpawnCell, that assigns the four connections and sets the weapons
     * available from the Spawn Cell.
     * @return the reference of the Spawn Cell object, with all its fields filled.
     */

    public SpawnCell build(){

      /*
        yellowSpawnCell.setDownConnection();
        yellowSpawnCell.setLeftConnection();
        yellowSpawnCell.setDownConnection();
        yellowSpawnCell.setRightConnection();
        yellowSpawnCell.setAvailableWeapons();
      */

      return yellowSpawnCell;

    }



}
