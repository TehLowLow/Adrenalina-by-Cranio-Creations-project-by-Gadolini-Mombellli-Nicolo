package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Controller.Data.EffectBuilders.TeleporterEffect;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * This class builds the Red Teleporter
 */

public class RedTeleporterBuilder {

    /*
    ------------FIELDS---------
     */

    /**
     * rTeleport is an instance of Powerup
     */

    private Powerup rTeleport;

    /**
     * eff is the effect of the Powerup
     */
    private Effect eff;

    /**
     * cDesc is the card description
     */
    private String cDesc;


    /*
    ----------METHODS----------
     */

    /**
     * builder method for the Red Teleport
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

        rTeleport= new Powerup();
        rTeleport.setName("Teletrasporto Rosso");
        rTeleport.setTradeValue(tValue);
        rTeleport.setColour(Colour.RED);
        rTeleport.setCardDescription(cDesc);
        rTeleport.setEffect(new TeleporterEffect());

        return rTeleport;

    }


}
