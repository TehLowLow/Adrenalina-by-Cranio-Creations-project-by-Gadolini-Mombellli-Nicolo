package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds a blue teleporter object
 */
public class BlueTeleporterBuilder {

    /*
    ---------FIELDS-------------
     */


    /**
     * bTeleport is an instance of Powerup
     */
    private Powerup bTeleport = new Powerup();


    /**
     * eff is the effect of the powerup
     */
    private Effect eff;

    /**
     * cDesc is the card description
     */
    private String cDesc;


    /*
    ---------METHODS----------
     */

    /**
     * Builds the PUp object.
     * @return the object.
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

        bTeleport.setName("Teletrasporto Blu");
        bTeleport.setTradeValue(tValue);
        bTeleport.setCardDescription(cDesc);
        bTeleport.setEffect(eff);

        return bTeleport;

    }





}
