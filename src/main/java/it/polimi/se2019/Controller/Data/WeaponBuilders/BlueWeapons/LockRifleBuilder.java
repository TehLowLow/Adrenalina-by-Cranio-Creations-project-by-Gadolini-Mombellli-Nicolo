package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.LockRifleEffect;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;


/**
 * This class builds the Lock Rifle
 */

public class LockRifleBuilder {

    /*
    ------------FIELDS-----------
    the object that will contain the Lock Rifle
     */

    /**
     * lRifle is an instance of Weapon
     */

    private Weapon lRifle = new Weapon();

    /*
    --------METHODS-----------
     */

    /**
     * builder method for Lock Rifle
     * @return the reference to the weapon, with all its fields initialized
     */

    public Weapon build() {

        /*
        setting the price rybamount
         */

        Rybamount price = new Rybamount();

        price.setBlueCubes(1);
        price.setYellowCubes(0);
        price.setRedCubes(0);

        /*
        setting the recharge cost rybamount
         */

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(2);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(0);



        /*
        setting the fields of the Lock Rifle
         */

        lRifle.setName("Distruttore");
        lRifle.setPrice(price);
        lRifle.setRechargeCost(recharge);
        lRifle.setLoaded(true);
        lRifle.setBaseEffect(new LockRifleEffect());
        lRifle.setAlternativeEffect(null);

        return lRifle;
    }

}
