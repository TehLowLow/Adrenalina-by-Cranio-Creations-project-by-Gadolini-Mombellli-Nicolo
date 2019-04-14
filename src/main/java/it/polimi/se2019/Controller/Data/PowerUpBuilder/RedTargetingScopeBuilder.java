package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

public class RedTargetingScopeBuilder {

    private Powerup rtScope = new Powerup();
    private Effect eff;
    private String cDesc;


    public Powerup build(){


        Rybamount tValue = new Rybamount();

        tValue.setRedCubes(1);
        tValue.setBlueCubes(0);
        tValue.setYellowCubes(0);

        rtScope.setName("Red Targeting Scope");
        rtScope.setTradeValue(tValue);
        rtScope.setCardDescription(cDesc);
        rtScope.setEffect(eff);

        return rtScope;
    }






}
