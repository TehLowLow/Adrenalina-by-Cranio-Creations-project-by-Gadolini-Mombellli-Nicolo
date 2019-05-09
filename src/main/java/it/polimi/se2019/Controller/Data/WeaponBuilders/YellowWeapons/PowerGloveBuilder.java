package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.BasicPowerGlove;
import it.polimi.se2019.Controller.Data.EffectBuilders.PulverizeMode;
import it.polimi.se2019.Controller.Data.EffectBuilders.RocketFistMode;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * this class builds the weapon Power Glove
 */

public class PowerGloveBuilder {

    /*
    -----------------FIELDS-------------------
    The weapon object that will contain the Power Glove
     */

    /**
     * pGlove is an instance of Weapon
     */

    private Weapon pGlove = new Weapon();

    /*
    ------------METHODS----------------------------
     */


    /**
     * builder method for Power Glove
     * @return the reference to the object Power Glove, with its fields initialized
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

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        Rybamount altPrice = new Rybamount();

        altPrice.setBlueCubes(1);
        altPrice.setRedCubes(0);
        altPrice.setYellowCubes(0);

        /*
        setting the fields of the Power Glove
         */

        pGlove.setName("Cyberguanto");
        pGlove.setPrice(price);
        pGlove.setRechargeCost(recharge);
        pGlove.setLoaded(true);
        pGlove.setBaseEffect(new BasicPowerGlove());
        pGlove.setAlternativeEffect(new RocketFistMode());
        pGlove.setAlternativeCost(altPrice);


        return pGlove;


    }
}
