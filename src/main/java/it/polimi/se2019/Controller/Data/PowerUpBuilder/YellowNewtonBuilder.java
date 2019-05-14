package it.polimi.se2019.Controller.Data.PowerUpBuilder;


import it.polimi.se2019.Controller.Data.EffectBuilders.Newton;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

import java.text.CollationElementIterator;

/**
 * This class creates 2 Yellow Newton  power ups, and adds them to the PUp deck.
 * In the italian game this card's name is "Raggio Cinetico"
 */
public class YellowNewtonBuilder {

    /*
    -----------FIELDS-------------
     */

    /**
     * yNewton is an instance of Powerup
     */

    private Powerup yNewton;

    /**
     * eff is the effect of the Powerup
     */
    private Effect eff;


    /**
     * cDesc is the card description
     */
    private String cDesc;

    /*
    ------------METHODS-----------------
     */


    /**
     * builder method for the Yellow Newton
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

        yNewton= new Powerup();
        yNewton.setName("Raggio Cinetico Giallo");
        yNewton.setTradeValue(tValue);
        yNewton.setCardDescription(cDesc);
        yNewton.setColour(Colour.YELLOW);
        yNewton.setEffect(new Newton());

        return yNewton;
    }





}
