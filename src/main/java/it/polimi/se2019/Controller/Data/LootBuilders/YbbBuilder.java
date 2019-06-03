package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

    /**
     * This class builds the loot with 1 yellow cube and 2 blue cubes.
     */

public class YbbBuilder {

    /*
     *--------------------FIELDS-----------------------------
     * There is the Loot object that will contain ybb.
     */

    private Loot ybb;

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for ybb
     * @return the reference to the ybb object, with its fields filled.
     */

    public Loot build(){

        /*
         * ybb object as an instance of Loot.
         */

        ybb = new Loot();

        ybb.setName("ybb");

        /*
        setting the Rybamount corresponding to the loot.
         */

        /*
         * ybbRybamount is a Rybamount instance that has to be filled with 2 blue cubes and 1 yellow cube.
         */
        Rybamount ybbRybamount = new Rybamount();

        /*
        ------ybbRybamount setters-----------
         */

        ybbRybamount.setBlueCubes(2);
        ybbRybamount.setYellowCubes(1);
        ybbRybamount.setRedCubes(0);

        /*
        -----ybb loot setters-----------------
         */
        ybb.setRewardValue(ybbRybamount);

        ybb.setPowerUp(false);

        return ybb;
    }



}
