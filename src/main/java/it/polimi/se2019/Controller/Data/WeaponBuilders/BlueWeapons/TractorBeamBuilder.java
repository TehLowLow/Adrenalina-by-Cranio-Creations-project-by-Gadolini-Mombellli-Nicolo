package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the Tractor Beam
 */

public class TractorBeamBuilder {

    /*
    ------------FIELDS-----------
    the object that will contain the Tractor Beam
     */

    /**
     * tBeam is an instance of Weapon
     */

    private Weapon tBeam = new Weapon();

    /*
    ---------------METHODS------------
     */

    /**
     * builder method for Tractor Beam
     * @return the reference to the weapon, with its fields initialized
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

        tBeam.setName("Raggio Traente");
        tBeam.setPrice(price);
        tBeam.setRechargeCost(recharge);
        tBeam.setLoaded(true);
        tBeam.setBaseEffect(null);
        tBeam.setAlternativeEffect(null);
        tBeam.setOptionalEffect(null);


        return tBeam;
    }
}
