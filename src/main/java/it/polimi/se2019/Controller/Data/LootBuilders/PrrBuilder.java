package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 powerUp and 2 red cubes.
 */

public class PrrBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain prr.
     */

    /**
     * prr object as an instance of Loot.
     */

    private Loot prr = new Loot();

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for prr
     * @return the reference to the prr object, with its fields filled.
     */

    public Loot build(){

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
          prrRybamount is a Rybamount instance that has to be filled with 2 red cubes.
         */
        Rybamount prrRybamount = new Rybamount();

        /*
        ------prrRybamount setters-----------
         */

        prrRybamount.setBlueCubes(0);
        prrRybamount.setYellowCubes(0);
        prrRybamount.setRedCubes(2);

        /*
        ----- loot setters-----------------
         */
        prr.setRewardValue(prrRybamount);

        prr.setPowerUp(true);

        return prr;
    }



}