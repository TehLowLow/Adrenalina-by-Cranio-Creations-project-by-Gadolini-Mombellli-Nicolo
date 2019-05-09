package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.BasicShockwave;
import it.polimi.se2019.Controller.Data.EffectBuilders.TsunamiMode;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the weapon Shockwave
 */

public class ShockwaveBuilder {


    /*
    -------FIELDS--------------
    The object that will contain the weapon
     */

    /**
     * shockwave is an instance of the class Weapon
     */
    private Weapon shockwave = new Weapon();

    /*
    --------------METHODS-------------
     */


    /**
     * builder method for the weapon shockwave
     * @return the reference to the shockwave, with all his fields assigned.
     */
    public Weapon build() {

        /*
        setting the price rybamount
         */

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(0);

        /*
        setting the recharge cost rybamount
         */

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(0);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        /*
        setting the fields of the shockwave
         */

        shockwave.setName("Onda d'Urto");
        shockwave.setPrice(price);
        shockwave.setRechargeCost(recharge);
        shockwave.setLoaded(true);
        shockwave.setBaseEffect(new BasicShockwave());
        shockwave.setAlternativeEffect(new TsunamiMode());


        return shockwave;


    }
}
