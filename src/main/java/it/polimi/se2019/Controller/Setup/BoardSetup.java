package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * Creates the final board merging all the data coming from the other Setups classes.
 */

public class BoardSetup {


    /**
     *Creates the map from all the data.
     * @return the complete map.
     */

    public void build(){



        WeaponDeckSetup weaponDeckSetup = new WeaponDeckSetup();
        Board.setWeaponDeck(weaponDeckSetup.build());


        LootDeckSetup lootDeckSetup = new LootDeckSetup();
        Board.setLootDeck(lootDeckSetup.build());

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        Board.setPowerUpDeck(powerUpDeckSetup.build());




    }
}
