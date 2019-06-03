package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

/**
 * This class builds the loot with 1 red cube and 2 yellow cubes.
 */

public class RyyBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain ryy.
     */

    private Loot ryy;

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for ryy
     * @return the reference to the ryy object, with its fields filled.
     */

    public Loot build(){

        /*
         * ryy object as an instance of Loot.
         */

        ryy = new Loot();

        ryy.setName("ryy");

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
         * ryyRybamount is a Rybamount instance that has to be filled with 2 yellow cubes and 1 red cube.
         */
        Rybamount ryyRybamount = new Rybamount();

        /*
        ------ryyRybamount setters-----------
         */

        ryyRybamount.setBlueCubes(0);
        ryyRybamount.setYellowCubes(2);
        ryyRybamount.setRedCubes(1);

        /*
        -----ryy loot setters-----------------
         */
        ryy.setRewardValue(ryyRybamount);

        ryy.setPowerUp(false);

        return ryy;
    }



}
