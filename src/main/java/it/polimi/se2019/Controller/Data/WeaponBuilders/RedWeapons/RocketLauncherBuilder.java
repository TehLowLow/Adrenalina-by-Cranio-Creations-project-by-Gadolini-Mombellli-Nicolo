package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
import it.polimi.se2019.Controller.Data.EffectBuilders.RocketLauncher;
import it.polimi.se2019.Model.*;

/**
 * This class builds the Rocket Launcher
 */

public class RocketLauncherBuilder {

    /*
    ---------------FIELDS-----------------
     */

    /**
     * rocketLauncher is an instance of Weapon
     */

    private Weapon rocketLauncher = new Weapon();

    /*
    ---------------METHODS------------------------
     */


    /**
     * this method builds the weapon
     * @return the reference to the Rocket Laucher, with all his fields initialized
     */

    public Weapon build(){

        /*
        setting the name of the weapon
         */

        rocketLauncher.setName("Lanciarazzi");

        /*
        setting the recharge cost of the weapon
         */

        Rybamount rechargeCost = new Rybamount();
        rechargeCost.setBlueCubes(0);
        rechargeCost.setRedCubes(2);
        rechargeCost.setYellowCubes(0);
        rocketLauncher.setRechargeCost(rechargeCost);


        /*
        setting the price of the weapon
         */

        Rybamount price = new Rybamount();
        price.setYellowCubes(0);
        price.setRedCubes(1);
        price.setBlueCubes(0);
        rocketLauncher.setPrice(price);

        /*
        Sets the effect
         */

        rocketLauncher.setBaseEffect(new RocketLauncher());
        rocketLauncher.setAlternativeEffect(null);

        /*
        when picked up, the weapon is loaded
         */

        rocketLauncher.setLoaded(true);


        return rocketLauncher;

    }


}
