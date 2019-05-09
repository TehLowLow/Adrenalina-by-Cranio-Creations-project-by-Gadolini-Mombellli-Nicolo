package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.THOR;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the weapon THOR
 */

public class THORBuilder {

    /*
    ----------FIELDS---------
    the object that will contain THOR
     */

    /**
     * thor is an instance of Weapon
     */

    private Weapon thor = new Weapon();

    /*
    ------------METHODS----------
     */

    /**
     * builder method for THOR
     * @return the reference to Thor, with all its fields initialized
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
        setting the fields of THOR
         */

        thor.setName("Torpedine");
        thor.setPrice(price);
        thor.setRechargeCost(recharge);
        thor.setLoaded(true);
        thor.setBaseEffect(new THOR());
        thor.setAlternativeEffect(null);



        return thor;
    }

}
