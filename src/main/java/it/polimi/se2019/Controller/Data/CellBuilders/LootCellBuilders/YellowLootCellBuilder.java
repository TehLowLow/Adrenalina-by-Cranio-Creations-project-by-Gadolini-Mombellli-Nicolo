package it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds a Yellow Loot Cell.
 */
public class YellowLootCellBuilder {

    /*
     *--------------------------FIELDS----------------------
     */

    /**
     * This field will contain the yellowLootCell as an instance of LootCell
     */
    private LootCell yellowLootCell = new LootCell();

    /*
     *---------------------------METHODS---------------------
     */


    /**
     * Builder method that assigns the four connection of this cell and sets its loot.
     * @return the yellowLootCell object, with all its connections and its loot initialized.
     */
    public LootCell build(){

        /*
        yellowLootCell.setDownConnection();
        yellowLootCell.setLeftConnection();
        yellowLootCell.setRightConnection();
        yellowLootCell.setUpConnection();
        yellowLootCell.setLoot();
        */

        return yellowLootCell;

    }

}
