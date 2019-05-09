package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.Cyberblade;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the weapon Cyber Blade
 */


public class CyberBladeBuilder {

    /*
    ---------------FIELDS-------------------
    The weapon object that will contain the Cyber Blade
     */


    /**
     * the weapon cBlade is an instance of Weapon
     */

    private Weapon cBlade = new Weapon();

    /*
    ----------------METHODS----------------
     */

    /**
     * builder method that will build the weapon
     *
     * @return the reference to the cBlade object, with all its fields initialized
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

        recharge.setBlueCubes(0);
        recharge.setRedCubes(1);
        recharge.setYellowCubes(1);


        /*
        setting the fields of the cyber blade
         */


        cBlade.setName("Spada Fotonica");
        cBlade.setPrice(price);
        cBlade.setRechargeCost(recharge);
        cBlade.setLoaded(true);
        cBlade.setBaseEffect(new Cyberblade());
        cBlade.setAlternativeEffect(null);


        return cBlade;


    }
}
