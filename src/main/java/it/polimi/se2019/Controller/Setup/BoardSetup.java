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
        Board.shuffleWeaponDeck();

        LootDeckSetup lootDeckSetup = new LootDeckSetup();
        Board.setLootDeck(lootDeckSetup.build());
        Board.shuffleLootDeck();

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        Board.setPowerUpDeck(powerUpDeckSetup.build());
        Board.shufflePowerUpDeck();



        ArrayList<Powerup> discardedPowerUps = new ArrayList<>();
        Board.setDiscardedPowerUps(discardedPowerUps);

        ArrayList<Loot> discardedLoots = new ArrayList<>();
        Board.setDiscardedLoot(discardedLoots);




    }
}
