package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.*;

import java.util.ArrayList;

public class PowerUpDeckSetup {

    public ArrayList<Powerup> build(){

        ArrayList<Powerup> powerupDeck = new ArrayList<Powerup>();


        /*
        methods to build the powerup deck -> every powerup can have 3 different colours. So, for each of them,
        we have to build 3 different cards
         */

        /*
        -----------------------VENOM GRENADE----------------------
        */

        RedVenomGrenadeBuilder redVenomGrenadeBuilder = new RedVenomGrenadeBuilder();
        Powerup redVenomGrenade = redVenomGrenadeBuilder.build();

        powerupDeck.add(redVenomGrenade);


        BlueVenomGrenadeBuilder blueVenomGrenadeBuilder = new BlueVenomGrenadeBuilder();
        Powerup blueVenomGrenade = blueVenomGrenadeBuilder.build();

        powerupDeck.add(blueVenomGrenade);

        YellowVenomGrenadeBuilder yellowVenomGrenadeBuilder = new YellowVenomGrenadeBuilder();
        Powerup yellowVenomGrenade = yellowVenomGrenadeBuilder.build();

        powerupDeck.add(yellowVenomGrenade);




        return powerupDeck;
    }



}
