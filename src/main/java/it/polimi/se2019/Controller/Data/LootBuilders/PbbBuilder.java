package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 powerUp and 2 blue cubes.
 */

public class PbbBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain pbb.
     */

    /**
     * pbb object as an instance of Loot.
     */

    private Loot pbb = new Loot();

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for pbb
     * @return the reference to the pbb object, with its fields filled.
     */

    public Loot build(){

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          pbbRybamount is a Rybamount instance that has to be filled with 2 blue cubes.
         */
        Rybamount pbbRybamount = new Rybamount();

        /*
        ------pbbRybamount setters-----------
         */

        pbbRybamount.setBlueCubes(2);
        pbbRybamount.setYellowCubes(0);
        pbbRybamount.setRedCubes(0);

        /*
        ----- loot setters-----------------
         */
        pbb.setRewardValue(pbbRybamount);

        pbb.setPowerUp(true);

        return pbb;
    }



}
