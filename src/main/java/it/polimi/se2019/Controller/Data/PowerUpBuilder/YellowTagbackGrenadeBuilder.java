package it.polimi.se2019.Controller.Data.PowerUpBuilder;
import it.polimi.se2019.Model.*;

/**
 * This class creates the powerup Red Venom Grenade.
 */

public class YellowTagbackGrenadeBuilder {

    /*
    ---------------------FIELDS-------------------------
     */

    /**
     * yellowVenomGrenade is an instance of Powerup.
     */


    private Powerup yellowVenomGrenade = new Powerup();

    /*
    -------------------METHODS------------------------
     */


    /**
     * This method builds the object, setting all its fields.
     * @return yellow venom grenade reference.
     */

    public Powerup build(){

        yellowVenomGrenade.setName("Red Venom Grenade");
        return yellowVenomGrenade;


    }
}
