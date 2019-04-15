package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

public class YellowTargetingScopeBuilder {

    private Powerup ytScope = new Powerup();
    private Effect eff;
    private String cDesc;


    public Powerup build(){


        Rybamount tValue = new Rybamount();

        tValue.setYellowCubes(1);
        tValue.setRedCubes(0);
        tValue.setBlueCubes(0);

        ytScope.setName("Mirino Giallo");
        ytScope.setTradeValue(tValue);
        ytScope.setCardDescription(cDesc);
        ytScope.setEffect(eff);

        return ytScope;




    }






}
