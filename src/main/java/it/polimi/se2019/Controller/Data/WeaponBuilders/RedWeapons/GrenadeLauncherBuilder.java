package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
import it.polimi.se2019.Model.*;

/**
 * This class builds the weapon Grenade Launcher
 */

public class GrenadeLauncherBuilder {

    /*
    ------------------FIELDS----------------------------
     */

    /**
     * grenadeLauncher is an instance of Weapon
     */

    private Weapon grenadeLauncher = new Weapon();

    /*
    --------------------------METHODS--------------------
     */

    /**
     * This method builds the weapon
     * @return the reference to the grenadeLauncher, with all his fields initialized
     */

    public Weapon build(){

        /*
        setting the name of the weapon
         */

        grenadeLauncher.setName("Lanciagranate");

        /*
        setting the recharge cost of the weapon
         */

        Rybamount rechargeCost = new Rybamount();
        rechargeCost.setBlueCubes(0);
        rechargeCost.setRedCubes(1);
        rechargeCost.setYellowCubes(0);
        grenadeLauncher.setRechargeCost(rechargeCost);


        /*
        setting the price of the weapon
         */

        Rybamount price = new Rybamount();
        price.setYellowCubes(0);
        price.setRedCubes(0);
        price.setBlueCubes(0);
        grenadeLauncher.setPrice(price);


        /*
        when picked up, the weapon is loaded
         */

        grenadeLauncher.setLoaded(true);


        return grenadeLauncher;

    }

}
