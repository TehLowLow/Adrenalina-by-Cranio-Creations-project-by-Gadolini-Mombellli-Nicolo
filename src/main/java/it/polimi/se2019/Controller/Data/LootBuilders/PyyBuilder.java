package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 powerUp and 2 yellow cubes.
 */

public class PyyBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain pyy.
     */

    private Loot pyy;

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for pyy
     * @return the reference to the pyy object, with its fields filled.
     */

    public Loot build(){

        /*
         * pyy object as an instance of Loot.
         */

        pyy = new Loot();

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          pyyRybamount is a Rybamount instance that has to be filled with 2 yellow cubes.
         */
        Rybamount pyyRybamount = new Rybamount();

        /*
        ------pyyRybamount setters-----------
         */

        pyyRybamount.setBlueCubes(0);
        pyyRybamount.setYellowCubes(2);
        pyyRybamount.setRedCubes(0);

        /*
        ----- loot setters-----------------
         */
        pyy.setRewardValue(pyyRybamount);

        pyy.setPowerUp(true);

        return pyy;
    }



}
