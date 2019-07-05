package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 yellow cube and 2 red cubes.
 */

public class YrrBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain yrr.
     */

    /**
     * Represents a yellow and 2x red cubes.
     */

    private Loot yrr;

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for yrr
     * @return the reference to the yrr object, with its fields filled.
     */

    public Loot build(){

        /*
         * yrr object as an instance of Loot.
         */

        yrr = new Loot();

        yrr.setName("yrr");

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          yrrRybamount is a Rybamount instance that has to be filled with 2 red cubes and 1 yellow cube.
         */
        Rybamount yrrRybamount = new Rybamount();

        /*
        ------yrrRybamount setters-----------
         */

        yrrRybamount.setBlueCubes(0);
        yrrRybamount.setYellowCubes(1);
        yrrRybamount.setRedCubes(2);

        /*
        -----yrr loot setters-----------------
         */
        yrr.setRewardValue(yrrRybamount);

        yrr.setPowerUp(false);

        return yrr;
    }



}
