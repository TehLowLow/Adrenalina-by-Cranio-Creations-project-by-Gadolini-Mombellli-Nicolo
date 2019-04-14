package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 blue cube and 2 red cubes.
 */

public class BrrBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain brr.
     */

    /**
     * brr object as an instance of Loot.
     */

    private Loot brr = new Loot();

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for brr
     * @return the reference to the brr object, with its fields filled.
     */

    public Loot build(){

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          brrRybamount is a Rybamount instance that has to be filled with 2 red cubes and 1 blue cube.
         */
        Rybamount brrRybamount = new Rybamount();

        /*
        ------brrRybamount setters-----------
         */

        brrRybamount.setBlueCubes(1);
        brrRybamount.setYellowCubes(0);
        brrRybamount.setRedCubes(2);

        /*
        ----- loot setters-----------------
         */
        brr.setRewardValue(brrRybamount);

        brr.setPowerUp(false);

        return brr;
    }



}
