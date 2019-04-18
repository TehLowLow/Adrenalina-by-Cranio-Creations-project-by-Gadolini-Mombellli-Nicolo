package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;


/**
 * Builds a Blue Scope card
 */
public class BlueTargetingScopeBuilder {

    /*
    ---------FIELDS----------
     */

    /**
     * btScope is an instance of powerup
     */

    private Powerup btScope = new Powerup();

    /**
     * eff is the effect of the powerup
     */
    private Effect eff;

    /**
     * cDesc is the description of the card
     */
    private String cDesc;

    /*
    ------------METHODS--------------
     */


    /**
     * Builds a Blue tScope card
     * @return a PUp object
     */
    public Powerup build() {

        /*
        setting the trade value of the powerup
         */

        Rybamount tValue = new Rybamount();

        tValue.setBlueCubes(1);
        tValue.setRedCubes(0);
        tValue.setYellowCubes(0);


        /*
        setting the fields of the powerup
         */

        btScope.setName("Mirino Blu");
        btScope.setTradeValue(tValue);
        btScope.setCardDescription(cDesc);
        btScope.setEffect(eff);

        return btScope;
    }

}
