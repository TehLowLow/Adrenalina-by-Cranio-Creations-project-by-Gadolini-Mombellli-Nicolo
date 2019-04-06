package it.polimi.se2019.Controller.Data.LootBuilders;
import it.polimi.se2019.Model.*;

public class YbbBuilder {

    /*
    this class builds the loot with 1 yellow cube and 2 blue cubes
     */

    private Loot ybb = new Loot();

    public Loot build(){
        //ybb.setRewardValue();
        ybb.hasPowerUp();
        return ybb;
    }



}
