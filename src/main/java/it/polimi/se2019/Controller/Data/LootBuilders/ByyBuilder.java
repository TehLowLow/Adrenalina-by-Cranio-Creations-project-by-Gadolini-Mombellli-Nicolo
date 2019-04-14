package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 blue cube and 2 yellow cubes.
 */

public class ByyBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain byy.
     */

    /**
     * byy object as an instance of Loot.
     */

    private Loot byy = new Loot();

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for byy
     * @return the reference to the byy object, with its fields filled.
     */

    public Loot build(){

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          byyRybamount is a Rybamount instance that has to be filled with 2 yellow cubes and 1 blue cube.
         */
        Rybamount byyRybamount = new Rybamount();

        /*
        ------byyRybamount setters-----------
         */

        byyRybamount.setBlueCubes(1);
        byyRybamount.setYellowCubes(2);
        byyRybamount.setRedCubes(0);

        /*
        ----- loot setters-----------------
         */
        byy.setRewardValue(byyRybamount);

        byy.setPowerUp(false);

        return byy;
    }



}
