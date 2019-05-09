package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
import it.polimi.se2019.Controller.Data.EffectBuilders.BasicHellion;
import it.polimi.se2019.Controller.Data.EffectBuilders.NanoTracerMode;
import it.polimi.se2019.Model.*;

/**
 * This class builds the weapon HellionBuilder
 */

public class HellionBuilder {

    /*
    ---------------------FIELDS----------------------
     */

    /**
     * hellion is an instance of Weapon
     */

    private Weapon hellion = new Weapon();

    /*
    ----------------------METHODS---------------------
     */

    /**
     * This method builds the Weapon hellion
     * @return the reference to the weapon hellion, with all his fields initialized
     */

    public Weapon build() {

        /*
        setting the name of the weapon
         */
        hellion.setName("Raggio Solare");


        /*
        setting the recharge cost of the weapon
         */
        Rybamount rechargeCost = new Rybamount();
        rechargeCost.setRedCubes(1);
        rechargeCost.setYellowCubes(1);
        rechargeCost.setBlueCubes(0);
        hellion.setRechargeCost(rechargeCost);

        /*
        setting the price of the weapon
         */

        Rybamount price = new Rybamount();
        price.setBlueCubes(0);
        price.setYellowCubes(1);
        price.setRedCubes(0);
        hellion.setPrice(price);


        Rybamount altPrice = new Rybamount();

        altPrice.setBlueCubes(0);
        altPrice.setRedCubes(1);
        altPrice.setYellowCubes(0);
        /*
        Sets the effect
         */
        hellion.setBaseEffect(new BasicHellion());
        hellion.setAlternativeEffect(new NanoTracerMode());
        hellion.setAlternativeCost(altPrice);

        /*
        the weapon, at the beginning, is loaded
         */

        hellion.setLoaded(true);

        return hellion;
    }


}

