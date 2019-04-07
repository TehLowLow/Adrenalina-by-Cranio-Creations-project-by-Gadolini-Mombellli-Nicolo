package it.polimi.se2019.Controller.Data.PowerUpBuilder;
import it.polimi.se2019.Model.*;

/**
 * This class creates the powerup Blue Venom Grenade.
 */

public class BlueVenomGrenadeBuilder {

    /*
     * ----------------FIELDS-----------------------------
     */


    /**
     * blueVenomGrenade is an instance of Powerup.
     */

    private Powerup blueVenomGrenade = new Powerup();

    /*
    -------------------METHODS------------------------
     */


    /**
     * This method builds the object, setting all its fields.
     * @return blue venom grenade reference.
     */

    public Powerup build(){

        blueVenomGrenade.setName("Blue Venom Grenade");
        return blueVenomGrenade;


    }
}
