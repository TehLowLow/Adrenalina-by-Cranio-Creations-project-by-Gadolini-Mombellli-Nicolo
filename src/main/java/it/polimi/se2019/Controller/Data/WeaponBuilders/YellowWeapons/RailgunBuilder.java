package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the weapon Railgun
 */

public class RailgunBuilder {

    /*
    ------------FIELDS----------------
    The object that will contain the Railgun
     */


    /**
     * rGun is an instance of weapon
     */

    private Weapon rGun = new Weapon();

    /*
    ----------METHODS-----------------
     */


    /**
     * builder method for the railgun
     * @return the reference to the object railgun, with all its fields initialized
     */


    public Weapon build() {

        /*
        setting the price rybamount
         */

        Rybamount price = new Rybamount();

        price.setBlueCubes(1);
        price.setYellowCubes(1);
        price.setRedCubes(0);


        /*
        setting the recharge cost rybamount
         */

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(2);

        /*
        setting the fields of the weapon
         */

        rGun.setName("Fucile Laser");
        rGun.setPrice(price);
        rGun.setRechargeCost(recharge);
        rGun.setLoaded(true);
        rGun.setBaseEffect(null);
        rGun.setAlternativeEffect(null);
        rGun.setOptionalEffect(null);


        return rGun;


    }
}
