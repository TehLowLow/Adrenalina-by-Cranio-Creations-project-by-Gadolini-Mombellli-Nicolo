package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * this class builds the Electroschythe
 */

public class ElectroschytheBuilder {

    /*
    --------------FIELDS-----------
    The object that will contain the weapon
     */

    /**
     * eScythe is an instance of Weapon
     */


    private Weapon eScythe = new Weapon();

    /*
    ----------------METHODS-----------
     */

    /**
     * builder method for ElectroSchythe
     * @return the reference to the weapon with all its fields initialized
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

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(0);

        /*
        setting the fields of the weapon
         */

        eScythe.setName("Falce Protonica");
        eScythe.setPrice(price);
        eScythe.setRechargeCost(recharge);
        eScythe.setLoaded(true);
        eScythe.setBaseEffect(null);
        eScythe.setAlternativeEffect(null);
        eScythe.setOptionalEffect(null);


        return eScythe;
    }
}