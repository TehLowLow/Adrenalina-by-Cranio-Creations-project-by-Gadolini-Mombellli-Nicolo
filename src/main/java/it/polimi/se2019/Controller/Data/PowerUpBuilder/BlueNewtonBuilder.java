package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Controller.Data.EffectBuilders.Newton;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds the Blue Newton Powerup.
 * The italian name of this card is "Raggio cinetico"
 */

public class BlueNewtonBuilder {

    /*
    ----------FIELDS--------------
     */

    /**
     * bNewton is an instance of Powerup
     */

    private Powerup bNewton = new Powerup();

    /**
     * eff represents the effect of the Powerup
     */
    private Effect eff;

    /**
     * cText is an instance of String that represents the card description
     */

    private String cText;

    /*
    -------METHODS--------
     */

    /**
     * Builds and sets a bNewton object.
     * @return reference of the bNewton.
     */
    public Powerup build(){

        /*
        setting the trade value rybamount
         */

        Rybamount tValue = new Rybamount();
        tValue.setBlueCubes(1);
        tValue.setRedCubes(0);
        tValue.setYellowCubes(0);

        /*
        setting the fields of the powerup
         */

        bNewton.setName("Raggio Cinetico Blu");
        bNewton.setColour(Colour.BLUE);
        bNewton.setTradeValue(tValue);
        bNewton.setCardDescription(cText);
        bNewton.setEffect(new Newton());

       return bNewton;

    }



}
