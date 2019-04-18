package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds a Red Newton
 */

public class RedNewtonBuilder {

    /*
    -------FIELDS------------
     */

    /**
     * rNewton is an instance of Powerup
     */

    private Powerup rNewton = new Powerup();

    /**
     * eff is the effect of the powerup
     */

    private Effect eff;

    /**
     * cDesc is the description of the card
     */
    private  String cDesc;

    /*
    ----------METHODS-------------
     */

    /**
     * builder method for the Red Newton
     *@return a red newton object
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

        rNewton.setName("Raggio Cinetico Rosso");
        rNewton.setTradeValue(tValue);
        rNewton.setCardDescription(cDesc);
        rNewton.setEffect(eff);

        return rNewton;

    }
}
