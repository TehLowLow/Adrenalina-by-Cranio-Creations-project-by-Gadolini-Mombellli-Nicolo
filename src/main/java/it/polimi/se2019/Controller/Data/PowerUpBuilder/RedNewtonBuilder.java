package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds a Red Newton
 */

public class RedNewtonBuilder {

    private Powerup rNewton = new Powerup();
    private Effect eff;
    private  String cDesc;

    /**
     *@return a red newton object
     */

    public Powerup build(){


        Rybamount tValue = new Rybamount();

        tValue.setRedCubes(1);
        tValue.setBlueCubes(0);
        tValue.setYellowCubes(0);

        rNewton.setName("Raggio Cinetico Rosso");
        rNewton.setTradeValue(tValue);
        rNewton.setCardDescription(cDesc);
        rNewton.setEffect(eff);

        return rNewton;

    }
}
