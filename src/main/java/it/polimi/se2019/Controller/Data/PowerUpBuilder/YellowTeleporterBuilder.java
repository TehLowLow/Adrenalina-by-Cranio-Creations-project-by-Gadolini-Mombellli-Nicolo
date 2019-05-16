package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Controller.Data.EffectBuilders.TeleporterEffect;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * This class builds the Yellow Teleport
 */

public class YellowTeleporterBuilder {

    /*
    -----------FIELDS-----------
     */

    /**
     * yTeleport is an instance of Powerup
     */

    private Powerup yTeleport;

    /**
     * eff is the effect of the powerup;
     */
    private Effect eff;

    /**
     * cDesc is the card description
     */
    private String cDesc;


    /*
    ------------METHODS-----------
     */

    /**
     * builder method for the Yellow Teleport
     * @return the reference of the Powerup
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

        yTeleport= new Powerup();
        yTeleport.setName("Teletrasporto Giallo");
        yTeleport.setTradeValue(tValue);
        yTeleport.setCardDescription(cDesc);
        yTeleport.setEffect(new TeleporterEffect());
        yTeleport.setColour(Colour.YELLOW);

        return yTeleport;


    }






}
