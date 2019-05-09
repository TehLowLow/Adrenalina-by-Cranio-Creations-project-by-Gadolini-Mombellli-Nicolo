package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Controller.Data.EffectBuilders.TargetingScopeEffect;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * This class builds the Yellow Targeting Scope
 */

public class YellowTargetingScopeBuilder {

    /*
    ----------FIELDS-----------
     */

    /**
     * ytScope is an instance of Powerup
     */

    private Powerup ytScope = new Powerup();

    /**
     * eff is the effect of the Powerup
     */
    private Effect eff;

    /**
     * cDesc is the description of the card
     */
    private String cDesc;

    /*
    -------METHODS------
     */

    /**
     * builder method for the Yellow Targeting Scope
     * @return the reference to the Powerup
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

        ytScope.setName("Mirino Giallo");
        ytScope.setTradeValue(tValue);
        ytScope.setCardDescription(cDesc);
        ytScope.setEffect(new TargetingScopeEffect());
        ytScope.setColour(Colour.YELLOW);

        return ytScope;




    }






}
