package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Controller.Data.EffectBuilders.TargetingScopeEffect;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * This class creates the powerUp Red Targeting Scope
 */

public class RedTargetingScopeBuilder {

    /*
    ------------FIELDS-----------
     */

    /**
     * rtScope is an instance of Powerup
     */

    private Powerup rtScope = new Powerup();

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
     * builder method for the Red Targeting Scope
     * @return the reference to the Powerup
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
        setting the fields of the Powerup
         */

        rtScope.setName("Mirino Rosso");
        rtScope.setTradeValue(tValue);
        rtScope.setCardDescription(cDesc);
        rtScope.setColour(Colour.RED);
        rtScope.setEffect(new TargetingScopeEffect());

        return rtScope;
    }






}
