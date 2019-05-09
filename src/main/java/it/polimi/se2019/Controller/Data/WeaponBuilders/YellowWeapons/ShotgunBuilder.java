package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.BasicShotgun;
import it.polimi.se2019.Controller.Data.EffectBuilders.LongBarrelMode;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the shotgun
 */

public class ShotgunBuilder {

    /*
    -------------FIELDS-------------
    The object that will contain shotgun
     */

    /**
     * shotgun is an instance of Weapon
     */

    private Weapon shotgun = new Weapon();

    /*
    -------------METHODS--------------
     */

    /**
     * builder method for shotgun
     * @return the reference to the weapon shotgun, with all its fields initialized
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

        recharge.setBlueCubes(0);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(2);

        Rybamount altPrice = new Rybamount();

        altPrice.setBlueCubes(0);
        altPrice.setRedCubes(0);
        altPrice.setYellowCubes(0);

        /*
        setting the fields of the weapon
         */

        shotgun.setName("Fucile a Pompa");
        shotgun.setPrice(price);
        shotgun.setRechargeCost(recharge);
        shotgun.setLoaded(true);
        shotgun.setBaseEffect(new BasicShotgun());
        shotgun.setAlternativeEffect(new LongBarrelMode());
        shotgun.setAlternativeCost(altPrice);

        return shotgun;


    }
}
