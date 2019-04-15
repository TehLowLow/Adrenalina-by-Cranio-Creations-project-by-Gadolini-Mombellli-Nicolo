package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds the Blue Newton Powerup.
 * The italian name of this card is "Raggio cinetico"
 */

public class BlueNewtonBuilder {


    private Powerup bNewton = new Powerup();
    private Effect eff;
    private String cText;

    /**
     * Builds and sets a bNewton object.
     * @return reference of the bNewton.
     */
    public Powerup build(){


        Rybamount tValue = new Rybamount();
        tValue.setBlueCubes(1);
        tValue.setRedCubes(0);
        tValue.setYellowCubes(0);



        bNewton.setName("Raggio Cinetico Blu");
        bNewton.setTradeValue(tValue);
        bNewton.setCardDescription(cText);
        bNewton.setEffect(eff);

       return bNewton;

    }



}
