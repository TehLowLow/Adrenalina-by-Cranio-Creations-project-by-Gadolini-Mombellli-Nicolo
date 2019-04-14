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

    public ArrayList<Powerup> build(){

        ArrayList<Powerup> powerupDeck = new ArrayList<Powerup>();


        /*
        methods to build the powerup deck -> every powerup can have 3 different colours. So, for each of them,
        we have to build 3 different cards
         */

        /*
        -----------------------VENOM GRENADE----------------------
        */

        RedTagbackGrenadeBuilder redTagbackGrenadeBuilder = new RedTagbackGrenadeBuilder();
        Powerup redVenomGrenade = redTagbackGrenadeBuilder.build();

        powerupDeck.add(redVenomGrenade);


        BlueTagbackGrenadeBuilder blueTagbackGrenadeBuilder = new BlueTagbackGrenadeBuilder();
        Powerup blueVenomGrenade = blueTagbackGrenadeBuilder.build();

        powerupDeck.add(blueVenomGrenade);

        YellowTagbackGrenadeBuilder yellowTagbackGrenadeBuilder = new YellowTagbackGrenadeBuilder();
        Powerup yellowVenomGrenade = yellowTagbackGrenadeBuilder.build();

        powerupDeck.add(yellowVenomGrenade);




        return powerupDeck;
    }



}
