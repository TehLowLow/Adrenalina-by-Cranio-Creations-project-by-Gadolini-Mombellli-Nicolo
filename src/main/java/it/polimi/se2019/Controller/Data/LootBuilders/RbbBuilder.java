package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 red cube and 2 blue cubes.
 */

public class RbbBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain rbb.
     */

    /**
     * rbb object as an instance of Loot.
     */

    private Loot rbb = new Loot();

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for rbb
     * @return the reference to the rbb object, with its fields filled.
     */

    public Loot build(){

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
         * rbbRybamount is a Rybamount instance that has to be filled with 2 blue cubes and 1 red cube.
         */
        Rybamount rbbRybamount = new Rybamount();

        /*
        ------rbbRybamount setters-----------
         */

        rbbRybamount.setBlueCubes(2);
        rbbRybamount.setYellowCubes(0);
        rbbRybamount.setRedCubes(1);

        /*
        -----rbb loot setters-----------------
         */
        rbb.setRewardValue(rbbRybamount);

        rbb.setPowerUp(false);

        return rbb;
    }



}
