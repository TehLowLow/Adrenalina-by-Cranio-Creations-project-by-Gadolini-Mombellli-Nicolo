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
     * red Tagback grenade is an instance of Powerup.
     */

    private Powerup rTagback = new Powerup();
    private Effect eff;
    private String cDesc;

    /*
    -------------------METHODS------------------------
     */


    /**
     * This method builds the object, setting all its fields.
     * @return red tagback grenade reference.
     */


    public Powerup build(){

        Rybamount tValue = new Rybamount();

        tValue.setRedCubes(1);
        tValue.setBlueCubes(0);
        tValue.setYellowCubes(0);

        rTagback.setName("Granata Venom Rossa");
        rTagback.setTradeValue(tValue);
        rTagback.setCardDescription(cDesc);
        rTagback.setEffect(eff);

        return rTagback;


    }
}
