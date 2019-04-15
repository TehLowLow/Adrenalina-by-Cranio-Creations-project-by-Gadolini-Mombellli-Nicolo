package it.polimi.se2019.Controller.Data.PowerUpBuilder;
import it.polimi.se2019.Model.*;

/**
 * This class creates the powerup Blue Venom Grenade.
 */

public class BlueTagbackGrenadeBuilder {

    /*
     * ----------------FIELDS-----------------------------
     */


    /**
     * blueVenomGrenade is an instance of Powerup.
     */

    private Powerup bTagback = new Powerup();
    private Effect eff;
    private String cDesc;

    /*
    -------------------METHODS------------------------
     */


    /**
     * This method builds the object, setting all its fields.
     * @return blue venom grenade reference.
     */

    public Powerup build(){

        Rybamount tValue = new Rybamount();

        tValue.setBlueCubes(1);
        tValue.setYellowCubes(0);
        tValue.setRedCubes(0);

        bTagback.setName("Granata Venom Blu");
        bTagback.setTradeValue(tValue);
        bTagback.setCardDescription(cDesc);
        bTagback.setEffect(eff);
        return bTagback;


    }
}
