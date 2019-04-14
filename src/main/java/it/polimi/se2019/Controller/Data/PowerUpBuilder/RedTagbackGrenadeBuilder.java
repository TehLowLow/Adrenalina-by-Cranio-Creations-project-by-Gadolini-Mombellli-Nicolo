package it.polimi.se2019.Controller.Data.PowerUpBuilder;
import it.polimi.se2019.Model.*;

/**
 * This class creates the powerup Red Venom Grenade.
 */


public class RedTagbackGrenadeBuilder {

    /*
    ----------------FIELDS------------------------
     */


    /**
     * redVenomGrenade is an instance of Powerup.
     */

    private Powerup redVenomGrenade = new Powerup();

    /*
    -------------------METHODS------------------------
     */


    /**
     * This method builds the object, setting all its fields.
     * @return red venom grenade reference.
     */


    public Powerup build(){

        redVenomGrenade.setName("Red Venom Grenade");
        return redVenomGrenade;


    }
}
