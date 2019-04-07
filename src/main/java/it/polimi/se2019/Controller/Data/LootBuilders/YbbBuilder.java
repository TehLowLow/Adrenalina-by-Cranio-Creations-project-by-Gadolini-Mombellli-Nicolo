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

    /**
     * ybb object as an instance of Loot.
     */

    private Loot ybb = new Loot();

    /*
     * ----------------------METHODS-------------------------------
     */

    /**
     * Builder method for ybb
     * @return the reference to the ybb object, with its fields filled.
     */

    public Loot build(){
        //ybb.setRewardValue();
        ybb.hasPowerUp();
        return ybb;
    }



}
