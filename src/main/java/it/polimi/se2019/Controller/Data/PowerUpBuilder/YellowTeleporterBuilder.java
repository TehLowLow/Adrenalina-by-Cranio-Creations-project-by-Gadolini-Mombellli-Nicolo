package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

public class YellowTeleporterBuilder {

    private Powerup yTeleport = new Powerup();
    private Effect eff;
    private String cDesc;


    public Powerup build(){


        Rybamount tValue = new Rybamount();

        tValue.setYellowCubes(1);
        tValue.setRedCubes(0);
        tValue.setBlueCubes(0);

        yTeleport.setName("Yellow Teleporter");
        yTeleport.setTradeValue(tValue);
        yTeleport.setCardDescription(cDesc);
        yTeleport.setEffect(eff);

        return yTeleport;


    }






}
