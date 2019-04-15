package it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds a Loot Cell.
 */
public class LootCellBuilder {

    /*
     *--------------------------FIELDS----------------------
     */

    /**
     * This field will contain the lootCell as an instance of LootCell
     */
    private LootCell lootCell;

    /*
     *---------------------------METHODS---------------------
     */


    /**
     * Builder method that assigns the four connection of this cell and sets its loot.
     * @return the yellowLootCell object, with all its connections and its loot initialized.
     */
    public LootCell build(){

        lootCell = new LootCell();

        lootCell.setDownConnection(new Connection());
        lootCell.setLeftConnection(new Connection());
        lootCell.setRightConnection(new Connection());
        lootCell.setUpConnection(new Connection());
        //lootCell.setLoot();


        return lootCell;

    }

}
