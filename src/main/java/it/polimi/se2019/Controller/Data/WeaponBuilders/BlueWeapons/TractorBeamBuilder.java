package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.BasicTractorBeam;
import it.polimi.se2019.Controller.Data.EffectBuilders.PunisherMode;
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

        Rybamount altPrice = new Rybamount();

        altPrice.setBlueCubes(0);
        altPrice.setRedCubes(1);
        altPrice.setYellowCubes(1);

        /*
        setting the fields of the weapon
         */

        tBeam.setName("Raggio Traente");
        tBeam.setPrice(price);
        tBeam.setRechargeCost(recharge);
        tBeam.setLoaded(true);
        tBeam.setBaseEffect(new BasicTractorBeam());
        tBeam.setAlternativeEffect(new PunisherMode());
        tBeam.setAlternativeCost(altPrice);



        return tBeam;
    }
}
