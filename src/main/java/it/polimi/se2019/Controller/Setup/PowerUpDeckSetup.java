package it.polimi.se2019.Controller.Setup;

import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.*;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Creates the deck containing all the power ups. Every power up is created twice for every color, for a total of 6 for
 * each power up.
 */

public class PowerUpDeckSetup {

    /**
     * Creates a deck of powerups.
     *
     */

    private CopyOnWriteArrayList<Powerup> powerupDeck = new CopyOnWriteArrayList<>();


    /**
     * Adds all the powerups to the powerups array.
     * @return the array of powerups.
     */

    public CopyOnWriteArrayList<Powerup> build() {

        for (int i = 0; i < 2; i++) {
            powerupDeck.add(new BlueNewtonBuilder().build());
            powerupDeck.add(new BlueTagbackGrenadeBuilder().build());
            powerupDeck.add(new BlueTargetingScopeBuilder().build());
            powerupDeck.add(new BlueTeleporterBuilder().build());
            powerupDeck.add(new RedNewtonBuilder().build());
            powerupDeck.add(new RedTagbackGrenadeBuilder().build());
            powerupDeck.add(new RedTargetingScopeBuilder().build());
            powerupDeck.add(new RedTeleporterBuilder().build());
            powerupDeck.add(new YellowNewtonBuilder().build());
            powerupDeck.add(new YellowTagbackGrenadeBuilder().build());
            powerupDeck.add(new YellowTargetingScopeBuilder().build());
            powerupDeck.add(new YellowTeleporterBuilder().build());
        }

        return powerupDeck;
    }
}
