package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.MachineGunEffect;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the weapon Machine Gun
 */

public class MachineGunBuilder {

    /*
    ---------FIELDS---------
    it's the object that will contain the machine gun
     */

    /**
     * mGun is an instance of Weapon
     */

    private Weapon mGun = new Weapon();

    /*
    ----------METHODS------------
     */

    /**
     * builder method for the machine gun
     * @return the reference to the weapon machine gun, with all its fields initialized
     */

    public Weapon build() {

        /*
        setting the price rybamount
         */

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(1);

        /*
        setting the recharge cost rybamount
         */

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(1);
        recharge.setYellowCubes(0);

        /*
        setting the fields of the machine gun
         */

        mGun.setName("Mitragliatrice");
        mGun.setPrice(price);
        mGun.setRechargeCost(recharge);
        mGun.setLoaded(true);
        mGun.setBaseEffect(new MachineGunEffect());
        mGun.setAlternativeEffect(null);




        return mGun;
    }

}
