package it.polimi.se2019.Controller.Setup;
import  it.polimi.se2019.Controller.Data.LootBuilders.*;
import  it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * Builds all the loot instances.
 */


public class LootDeckSetup {

    /**
     * Creates all the single loots, and then returns them in an ArrayList.
     * @return an ArrayList containing all the loots.
     */
    public ArrayList<Loot> build(){

        ArrayList<Loot> lootDeck = new ArrayList<Loot>();

        /*
        ----------------------YBB--------------------------------
         */

        YbbBuilder ybbBuilder = new YbbBuilder();
        Loot ybb = ybbBuilder.build();

        lootDeck.add(ybb);



        return lootDeck;

    }

}
