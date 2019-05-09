package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.BasicZX2;
import it.polimi.se2019.Controller.Data.EffectBuilders.ScannerMode;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the weapon ZX-2
 */

public class ZX2Builder {

    /*
    -------------FIELDS---------------
    It's the object that will contain the weapon
     */

    /**
     * zX is an instance of Weapon
     */

    private Weapon zX = new Weapon();

    /*
    ----------------METHODS-------------
     */

    /**
     * builder method for the weapon ZX-2
     * @return the reference to the object representing the weapon, with all its fields initialized
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
        setting the recharge rybamount
         */

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(0);
        recharge.setRedCubes(1);
        recharge.setYellowCubes(1);

        /*
        setting the fields of the weapon
         */

        zX.setName("ZX-2");
        zX.setPrice(price);
        zX.setRechargeCost(recharge);
        zX.setLoaded(true);
        zX.setBaseEffect(new BasicZX2());
        zX.setAlternativeEffect(new ScannerMode());


        return zX;


    }
}
