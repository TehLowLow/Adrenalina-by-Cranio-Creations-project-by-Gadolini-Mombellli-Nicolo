package it.polimi.se2019.Controller.Data.PowerUpBuilder;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * Builds the Yellow Tagback Grenade powerup.
 * The italian name of this card is "Granata Venom ".
 */

public class YellowTagbackGrenadeBuilder {


    private Powerup yTagback= new Powerup();
    private Effect eff;
    private String cDesc;

    /**
     * Builds and sets a yTagback object
     * @return reference of the yTagback
     */

    public Powerup build(){
        Rybamount tValue = new Rybamount();

        tValue.setYellowCubes(1);
        tValue.setRedCubes(0);
        tValue.setBlueCubes(0);

        yTagback.setName("Granata Venom Gialla");
        yTagback.setTradeValue(tValue);
        yTagback.setCardDescription(cDesc);
        yTagback.setEffect(eff);

        return yTagback;

    }
}
