package it.polimi.se2019.Controller.Setup;
import  it.polimi.se2019.Controller.Data.LootBuilders.*;
import  it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class LootDeckSetup {

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
