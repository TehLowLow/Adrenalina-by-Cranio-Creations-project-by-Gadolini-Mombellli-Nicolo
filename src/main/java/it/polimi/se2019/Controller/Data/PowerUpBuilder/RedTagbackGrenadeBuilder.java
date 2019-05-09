package it.polimi.se2019.Controller.Data.PowerUpBuilder;
import it.polimi.se2019.Controller.Data.EffectBuilders.TagBackGrenadeEffect;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
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

    /**
     * eff is the effect of the Powerup
     */
    private Effect eff;

    /**
     * cDesc is the descriptions of the card
     */
    private String cDesc;

    /*
    -------------------METHODS------------------------
     */


    /**
     * This method builds the object, setting all its fields.
     * @return red tagback grenade reference.
     */


    public Powerup build(){

        /*
        setting the trade value rybamount
         */

        Rybamount tValue = new Rybamount();

        tValue.setRedCubes(1);
        tValue.setBlueCubes(0);
        tValue.setYellowCubes(0);

        /*
        setting the fields of the powerup
         */

        rTagback.setName("Granata Venom Rossa");
        rTagback.setTradeValue(tValue);
        rTagback.setColour(Colour.RED);
        rTagback.setCardDescription(cDesc);
        rTagback.setEffect(new TagBackGrenadeEffect());

        return rTagback;


    }
}
