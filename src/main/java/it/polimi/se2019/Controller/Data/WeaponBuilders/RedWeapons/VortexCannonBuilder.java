package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
import it.polimi.se2019.Model.*;

/**
 * This class builds the weapon Vortex Cannon
  */

public class VortexCannonBuilder {

    /*
    ------------------------FIELDS--------------------------
     */

    /**
     * the object vortexCannon is an instance of Weapon
     */

    private Weapon vortexCannon = new Weapon();

    /*
    ----------------------------METHODS--------------------
     */

    /**
     * This method builds the weapon Vortex Cannon
     * @return the reference to the Vortex Cannon, with all his fields initialized.
     */

    public Weapon build(){

        /*
        setting the name of the weapon
         */

        vortexCannon.setName("Cannone Vortex");


        /*
        setting the recharge cost of the weapon
         */

        Rybamount rechargeCost = new Rybamount();
        rechargeCost.setRedCubes(1);
        rechargeCost.setYellowCubes(0);
        rechargeCost.setBlueCubes(1);
        vortexCannon.setRechargeCost(rechargeCost);

        /*
        setting the price of the weapon
         */

        Rybamount price = new Rybamount();
        price.setYellowCubes(0);
        price.setRedCubes(0);
        price.setBlueCubes(1);
        vortexCannon.setPrice(price);


        /*
        at the beginning the weapon is loaded
         */

        vortexCannon.setLoaded(true);

        return vortexCannon;

    }


}
