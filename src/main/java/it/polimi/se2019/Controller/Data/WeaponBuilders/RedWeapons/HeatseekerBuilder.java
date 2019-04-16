package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
import it.polimi.se2019.Model.*;

/**
 * This class builds the weapon Heatseeker.
 */
public class HeatseekerBuilder {

    /*
     * -------- FIELDS --------
     * The weapon object that will contain the Heatseeker.
     */

    /**
     * The object Heatseeker is an instance of Weapon.
     */
    private Weapon heatseeker = new Weapon();

    /**
     * Builder method that fills the fields of the Heatseeker object.
     * @return reference to the Heatseeker object complete with every field initialized.
     */
    public Weapon build(){

        /*
        setting the name of the weapon
         */
        heatseeker.setName("Razzo Termico");


        /*
        setting the recharge cost of the weapon
         */
        Rybamount rechargeCost = new Rybamount();
        rechargeCost.setRedCubes(2);
        rechargeCost.setYellowCubes(1);
        rechargeCost.setBlueCubes(0);
        heatseeker.setRechargeCost(rechargeCost);

        /*
        setting the price of the weapon
         */

        Rybamount price = new Rybamount();
        price.setBlueCubes(0);
        price.setYellowCubes(1);
        price.setRedCubes(1);
        heatseeker.setPrice(price);

        /*
        the weapon, at the beginning, is loaded
         */

        heatseeker.setLoaded(true);

        return heatseeker;


    }

}