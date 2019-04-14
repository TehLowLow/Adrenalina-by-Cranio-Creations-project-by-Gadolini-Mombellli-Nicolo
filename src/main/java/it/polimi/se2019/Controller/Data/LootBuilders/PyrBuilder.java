package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 powerUp, 1 yellow cube and 1 red cube.
 */

public class PyrBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain pyr.
     */

    /**
     * pyr object as an instance of Loot.
     */

    private Loot pyr = new Loot();

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for pyr
     * @return the reference to the pyr object, with its fields filled.
     */

    public Loot build(){

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          pyrRybamount is a Rybamount instance that has to be filled with 1 yellow cube and 1 red cube.
         */
        Rybamount pyrRybamount = new Rybamount();

        /*
        ------pyrRybamount setters-----------
         */

        pyrRybamount.setBlueCubes(0);
        pyrRybamount.setYellowCubes(1);
        pyrRybamount.setRedCubes(1);

        /*
        ----- loot setters-----------------
         */
        pyr.setRewardValue(pyrRybamount);

        pyr.setPowerUp(true);

        return pyr;
    }



}
