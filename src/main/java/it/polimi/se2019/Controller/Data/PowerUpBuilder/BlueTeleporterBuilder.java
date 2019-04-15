package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds a blue teleporter object
 */
public class BlueTeleporterBuilder {

    private Powerup bTeleport = new Powerup();
    private Effect eff;
    private String cDesc;

    /**
     * Builds the PUp object.
     * @return the object.
     */

    public Powerup build(){


        Rybamount tValue = new Rybamount();

        tValue.setBlueCubes(1);
        tValue.setRedCubes(0);
        tValue.setYellowCubes(0);

        bTeleport.setName("Teletrasporto Blu");
        bTeleport.setTradeValue(tValue);
        bTeleport.setCardDescription(cDesc);
        bTeleport.setEffect(eff);

        return bTeleport;

    }





}
