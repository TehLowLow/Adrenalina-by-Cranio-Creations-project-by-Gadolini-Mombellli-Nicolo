package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the plasma gun
 */

public class PlasmaGunBuilder {

    /*
    --------FIELDS--------
    This is the object that will contain the plasma gun
     */

    /**
     * pGun is an instance of Weapon
     */

    private Weapon pGun = new Weapon();

    /*
    -----------METHODS------------
     */

    /**
     * builder method for plasma gun
     * @return the reference to the plasma gun with all its fields initialized
     */

    public Weapon build() {

        /*
        setting the price rybamount
         */

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(1);
        price.setRedCubes(0);

        /*
        setting the recharge cost rybamount
         */

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        /*
        setting the fields of the weapon
         */

        pGun.setName("Fucile al Plasma");
        pGun.setPrice(price);
        pGun.setRechargeCost(recharge);
        pGun.setLoaded(true);
        pGun.setBaseEffect(null);
        pGun.setAlternativeEffect(null);
        pGun.setOptionalEffect(null);


        return pGun;

    }

}
