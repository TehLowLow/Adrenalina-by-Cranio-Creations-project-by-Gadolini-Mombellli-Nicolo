package it.polimi.se2019.Controller.Setup;
import  it.polimi.se2019.Controller.Data.LootBuilders.*;
import it.polimi.se2019.Controller.Data.RoomBuilders.YellowRoomBuilder;
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

        lootDeck.add(ybbBuilder.build());

        lootDeck.add(ybbBuilder.build());

        lootDeck.add(ybbBuilder.build());


        /*
        --------------------------YRR------------------
         */

        YrrBuilder yrrBuilder = new YrrBuilder();

        lootDeck.add(yrrBuilder.build());

        lootDeck.add(yrrBuilder.build());

        lootDeck.add(yrrBuilder.build());



        /*
        --------------------RBB------------------------------
         */

        RbbBuilder rbbBuilder = new RbbBuilder();

        lootDeck.add(rbbBuilder.build());

        lootDeck.add(rbbBuilder.build());

        lootDeck.add(rbbBuilder.build());



        /*
        -------------------RYY---------------------------------------
         */

        RyyBuilder ryyBuilder = new RyyBuilder();

        lootDeck.add(ryyBuilder.build());

        lootDeck.add(ryyBuilder.build());

        lootDeck.add(ryyBuilder.build());



        /*
        ----------------------------BYY-----------------
         */


        ByyBuilder byyBuilder = new ByyBuilder();

        lootDeck.add(byyBuilder.build());

        lootDeck.add(byyBuilder.build());

        lootDeck.add(byyBuilder.build());


        /*
        ------------------------BRR-----------------------
         */

        BrrBuilder brrBuilder = new BrrBuilder();

        lootDeck.add(brrBuilder.build());

        lootDeck.add(brrBuilder.build());

        lootDeck.add(brrBuilder.build());

        /*
        --------------------PYY--------------------------
         */

        PyyBuilder pyyBuilder = new PyyBuilder();

        lootDeck.add(pyyBuilder.build());

        lootDeck.add(pyyBuilder.build());


        /*
        -------------------PRR---------------------------
         */

        PrrBuilder prrBuilder = new PrrBuilder();

        lootDeck.add(prrBuilder.build());

        lootDeck.add(prrBuilder.build());


        /*
        -------------------PBB-----------------------------
         */

        PbbBuilder pbbBuilder = new PbbBuilder();

        lootDeck.add(pbbBuilder.build());

        lootDeck.add(pbbBuilder.build());



        /*
        --------------------PYR------------------------------
         */

        PyrBuilder pyrBuilder = new PyrBuilder();

        lootDeck.add(pyrBuilder.build());

        lootDeck.add(pyrBuilder.build());

        lootDeck.add(pyrBuilder.build());

        lootDeck.add(pyrBuilder.build());



        /*
        ---------------------PYB-----------------------------
         */

        PybBuilder pybBuilder = new PybBuilder();

        lootDeck.add(pybBuilder.build());

        lootDeck.add(pybBuilder.build());

        lootDeck.add(pybBuilder.build());

        lootDeck.add(pybBuilder.build());


        /*
        ---------------------PRB------------------------------
         */


        PrbBuilder prbBuilder = new PrbBuilder();

        lootDeck.add(prbBuilder.build());

        lootDeck.add(prbBuilder.build());

        lootDeck.add(prbBuilder.build());

        lootDeck.add(prbBuilder.build());


        /*
        ------------LOOTDECK COMPLETE------------------------
         */



        return lootDeck;

    }

}
