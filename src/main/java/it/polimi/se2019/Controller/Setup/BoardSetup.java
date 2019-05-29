package it.polimi.se2019.Controller.Setup;

import it.polimi.se2019.Controller.Adrenalina.Interaction;
import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Creates the final board merging all the data coming from the other Setups classes.
 */

public class BoardSetup {


    /**
     * Creates the map from all the data.
     *
     * @return the complete map.
     */

    public void build() {

        WeaponDeckSetup weaponDeckSetup = new WeaponDeckSetup();
        Board.setWeaponDeck(weaponDeckSetup.build());
        Board.shuffleWeaponDeck();

        LootDeckSetup lootDeckSetup = new LootDeckSetup();
        Board.setLootDeck(lootDeckSetup.build());
        Board.shuffleLootDeck();

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        Board.setPowerUpDeck(powerUpDeckSetup.build());
        Board.shufflePowerUpDeck();

        Board.setLimbo(new SpawnCell());

        CopyOnWriteArrayList<Powerup> discardedPowerUps = new CopyOnWriteArrayList<>();
        Board.setDiscardedPowerUps(discardedPowerUps);

        CopyOnWriteArrayList<Loot> discardedLoots = new CopyOnWriteArrayList<>();
        Board.setDiscardedLoot(discardedLoots);

        CopyOnWriteArrayList<Integer> mortalBlowTrackValue = new CopyOnWriteArrayList<>();
        Board.getMortalBlowTrackValue().add(8);
        Board.getMortalBlowTrackValue().add(6);
        Board.getMortalBlowTrackValue().add(4);
        Board.getMortalBlowTrackValue().add(2);
        Board.getMortalBlowTrackValue().add(1);
        Board.getMortalBlowTrackValue().add(1);

        Interaction.placeLoot();
        Interaction.placeWeapons();

    }

}

