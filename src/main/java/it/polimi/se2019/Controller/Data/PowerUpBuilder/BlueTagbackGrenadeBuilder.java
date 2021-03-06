package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Controller.Data.EffectBuilders.TagBackGrenadeEffect;
import it.polimi.se2019.Controller.Data.EffectBuilders.TargetingScopeEffect;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
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

    private Powerup bTagback;

    /**
     * eff represents the effect of the powerup.
     */

    private Effect eff;

    /**
     * cDesc is the description of the card
     */

    private String cDesc;

    /*
    -------------------METHODS------------------------
     */


    /**
     * This method builds the object, setting all its fields.
     *
     * @return blue venom grenade reference.
     */

    public Powerup build() {

        /*
        setting the trade value rybamount
         */

        Rybamount tValue = new Rybamount();

        tValue.setBlueCubes(1);
        tValue.setYellowCubes(0);
        tValue.setRedCubes(0);

        /*
        setting the fields of the Powerup
         */

        bTagback = new Powerup();
        bTagback.setName("Granata Venom Blu");
        bTagback.setColour(Colour.BLUE);
        bTagback.setTradeValue(tValue);
        bTagback.setCardDescription(cDesc);
        bTagback.setEffect(new TagBackGrenadeEffect());
        return bTagback;


    }
}
