package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.BasicSledgehammer;
import it.polimi.se2019.Controller.Data.EffectBuilders.PulverizeMode;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * this class builds the sledgehammer
 */

public class SledgehammerBuilder {

    /*
    --------------FIELDS------------
    The object that will contain the Sledgehammer
     */


    /**
     * sHammer is an instance of Weapon and will contain the sledgehammer
     */

    private Weapon sHammer = new Weapon();

    /*
    -----------------METHODS-----------------------
     */

    /**
     * builder method for the weapon Sledgehammer
     * @return the reference to the weapon, with all its fields initialized
     */

    public Weapon build() {

        /*
        setting the build rybamount
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

        Rybamount altPrice = new Rybamount();

        altPrice.setBlueCubes(0);
        altPrice.setRedCubes(1);
        altPrice.setYellowCubes(0);

        /*
        setting the fields of the sledgehammer
         */

        sHammer.setName("Martello Ionico");
        sHammer.setPrice(price);
        sHammer.setRechargeCost(recharge);
        sHammer.setLoaded(true);
        sHammer.setBaseEffect(new BasicSledgehammer());
        sHammer.setAlternativeEffect(new PulverizeMode());
        sHammer.setAlternativeCost(altPrice);


        return sHammer;


    }
}
