package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

public class RedTeleporterBuilder {

    private Powerup rTeleport = new Powerup();
    private Effect eff;
    private String cDesc;


    public Powerup build(){


        Rybamount tValue = new Rybamount();

        tValue.setRedCubes(1);
        tValue.setBlueCubes(0);
        tValue.setYellowCubes(0);

        rTeleport.setName("Red Teleporter");
        rTeleport.setTradeValue(tValue);
        rTeleport.setCardDescription(cDesc);
        rTeleport.setEffect(eff);

        return rTeleport;

    }


}
