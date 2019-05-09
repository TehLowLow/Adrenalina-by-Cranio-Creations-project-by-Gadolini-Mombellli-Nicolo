package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Controller.Data.EffectBuilders.TagBackGrenadeEffect;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds the Yellow Tagback Grenade powerup.
 * The italian name of this card is "Granata Venom ".
 */

public class YellowTagbackGrenadeBuilder {


    /*
    ----------FIELDS------------
     */

    /**
     * yTagback is an instance of Powerup
     */

    private Powerup yTagback= new Powerup();

    /**
     * eff is the effect of the Powerup
     */
    private Effect eff;

    /**
     * cDesc is the card description
     */
    private String cDesc;

    /*
    -------------METHODS--------
     */

    /**
     * Builds and sets a yTagback object
     * @return reference of the yTagback
     */

    public Powerup build(){

        /*
        setting the trade value rybamount
         */

        Rybamount tValue = new Rybamount();

        tValue.setYellowCubes(1);
        tValue.setRedCubes(0);
        tValue.setBlueCubes(0);

        /*
        setting the fields of the Powerup
         */

        yTagback.setName("Granata Venom Gialla");
        yTagback.setTradeValue(tValue);
        yTagback.setCardDescription(cDesc);
        yTagback.setColour(Colour.YELLOW);
        yTagback.setEffect(new TagBackGrenadeEffect());

        return yTagback;

    }
}
