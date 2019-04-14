package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;


/**
 * Builds a Blue Scope card
 */
public class BlueTargetingScopeBuilder {

    private Powerup btScope = new Powerup();
    private Effect eff;
    private String cDesc;


    /**
     * Builds a Blue tScope card
     * @return a PUp object
     */
    public Powerup build() {

        Rybamount tValue = new Rybamount();

        tValue.setBlueCubes(1);
        tValue.setRedCubes(0);
        tValue.setYellowCubes(0);


        btScope.setName("Blue Targeting Scope");
        btScope.setTradeValue(tValue);
        btScope.setCardDescription(cDesc);
        btScope.setEffect(eff);

        return btScope;
    }

}
