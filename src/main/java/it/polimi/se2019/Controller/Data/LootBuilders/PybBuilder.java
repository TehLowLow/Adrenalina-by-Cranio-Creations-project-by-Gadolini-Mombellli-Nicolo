package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 powerUp, 1 yellow cube and 1 blue cube.
 */

public class PybBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain pyb.
     */

    private Loot pyb;

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for pyb
     * @return the reference to the pyb object, with its fields filled.
     */

    public Loot build(){

        /*
         * pyb object as an instance of Loot.
         */

        pyb = new Loot();

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          pybRybamount is a Rybamount instance that has to be filled with 1 yellow cube and 1 blue cube.
         */
        Rybamount pybRybamount = new Rybamount();

        /*
        ------pybRybamount setters-----------
         */

        pybRybamount.setBlueCubes(1);
        pybRybamount.setYellowCubes(1);
        pybRybamount.setRedCubes(0);

        /*
        ----- loot setters-----------------
         */
        pyb.setRewardValue(pybRybamount);

        pyb.setPowerUp(true);

        return pyb;
    }



}