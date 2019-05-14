package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.*;

import java.util.ArrayList;


/**
 * Creates the deck containing all the power ups. Every power up is created twice for every color, for a total of 6 for
 * each power up.
 */

public class PowerUpDeckSetup {

    /**
     * Creates an instance of all the PowerUp cards.
     * @return an ArrayList of PowerUps as a deck.
     */



        private ArrayList<Powerup> powerupDeck = new ArrayList<>();
        private BlueNewtonBuilder bNewton = new BlueNewtonBuilder();
        private BlueTagbackGrenadeBuilder bTagback = new BlueTagbackGrenadeBuilder();
        private BlueTargetingScopeBuilder btScope = new BlueTargetingScopeBuilder();
        private BlueTeleporterBuilder bTeleport = new BlueTeleporterBuilder();
        private RedNewtonBuilder rNewton = new RedNewtonBuilder();
        private RedTagbackGrenadeBuilder rTagback = new RedTagbackGrenadeBuilder();
        private RedTargetingScopeBuilder rtScope = new RedTargetingScopeBuilder();
        private RedTeleporterBuilder rTeleport = new RedTeleporterBuilder();
        private YellowNewtonBuilder yNewton = new YellowNewtonBuilder();
        private YellowTagbackGrenadeBuilder yTagback = new YellowTagbackGrenadeBuilder();
        private YellowTargetingScopeBuilder ytScope = new YellowTargetingScopeBuilder();
        private YellowTeleporterBuilder yTeleport = new YellowTeleporterBuilder();



    public ArrayList<Powerup> build(){


        for(int i = 0; i<2; i++) {
            powerupDeck.add(bNewton.build());
            powerupDeck.add(bTagback.build());
            powerupDeck.add(btScope.build());
            powerupDeck.add(bTeleport.build());
            powerupDeck.add(rNewton.build());
            powerupDeck.add(rTagback.build());
            powerupDeck.add(rtScope.build());
            powerupDeck.add(rTeleport.build());
            powerupDeck.add(yNewton.build());
            powerupDeck.add(yTagback.build());
            powerupDeck.add(ytScope.build());
            powerupDeck.add(yTeleport.build());
        }


        return powerupDeck;
    }

//TODO:  refactor, sposta i new nelle add.
}
