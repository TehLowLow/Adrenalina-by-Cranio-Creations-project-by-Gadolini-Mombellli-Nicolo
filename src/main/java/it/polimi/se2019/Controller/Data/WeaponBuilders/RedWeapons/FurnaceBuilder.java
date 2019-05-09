package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
import it.polimi.se2019.Controller.Data.EffectBuilders.BasicFurnace;
import it.polimi.se2019.Controller.Data.EffectBuilders.CozyFireMode;
import it.polimi.se2019.Model.*;

/**
 * This class builds the weapon Furnace.
 */
public class FurnaceBuilder {

    /*
     * -------- FIELDS --------
     * The weapon object that will contain the Furnace.
     */

    /**
     * The object Furnace is an instance of Weapon.
     */
    private Weapon furnace = new Weapon();

    /**
     * Builder method that fills the fields of the Furnace object.
     * @return reference to the Furnace object complete with every field initialized.
     */
    public Weapon build(){

        /*
        setting the name of the weapon
         */

        furnace.setName("Vulcanizzatore");

        /*
        setting the recharge cost of the weapon
         */
        Rybamount rechargeCost = new Rybamount();
        rechargeCost.setRedCubes(1);
        rechargeCost.setYellowCubes(0);
        rechargeCost.setBlueCubes(1);
        furnace.setRechargeCost(rechargeCost);

        /*
        setting the price of the weapon
         */

        Rybamount price = new Rybamount();
        price.setBlueCubes(1);
        price.setYellowCubes(0);
        price.setRedCubes(0);
        furnace.setPrice(price);

           /*
        Setting the effect.
         */
        furnace.setBaseEffect(new BasicFurnace());
        furnace.setAlternativeEffect(new CozyFireMode());

        /*
        the weapon, at the beginning, is loaded
         */

        furnace.setLoaded(true);



        return furnace;


    }

}
