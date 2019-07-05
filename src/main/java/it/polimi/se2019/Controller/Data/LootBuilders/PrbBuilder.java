package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 powerUp, 1 red cube and 1 blue cube.
 */

public class PrbBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain prb.
     */

    /**
     * Represents a PowerUp, yellow and blue cubes.
     */
    private Loot prb;


    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for prb
     * @return the reference to the prb object, with its fields filled.
     */

    public Loot build(){

        /*
         * prb object as an instance of Loot.
         */

        prb = new Loot();

        prb.setName("prb");


        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          prbRybamount is a Rybamount instance that has to be filled with 1 red cube and 1 blue cube.
         */
        Rybamount prbRybamount = new Rybamount();

        /*
        ------prbRybamount setters-----------
         */

        prbRybamount.setBlueCubes(1);
        prbRybamount.setYellowCubes(0);
        prbRybamount.setRedCubes(1);

        /*
        ----- loot setters-----------------
         */
        prb.setRewardValue(prbRybamount);

        prb.setPowerUp(true);

        return prb;
    }



}