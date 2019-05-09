package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
import it.polimi.se2019.Controller.Data.EffectBuilders.BBQMode;
import it.polimi.se2019.Controller.Data.EffectBuilders.BasicFlamethrower;
import it.polimi.se2019.Model.*;


/**
 * This class builds the weapon Flamethrower.
 */
public class FlamethrowerBuilder {

    /*
     * -------- FIELDS --------
     * The weapon object that will contain the Flamethrower.
     */

    /**
     * The object Flamethrower is an instance of Weapon.
     */
    private Weapon flamethrower = new Weapon();

    /**
     * Builder method that fills the fields of the Flamethrower object.
     * @return reference to the flamethrower object complete with every field initialized.
     */
    public Weapon build(){

        /*
        setting the name of the weapon
         */
        flamethrower.setName("Lanciafiamme");


        /*
        setting the recharge cost of the weapon
         */
        Rybamount rechargeCost = new Rybamount();
        rechargeCost.setRedCubes(1);
        rechargeCost.setYellowCubes(0);
        rechargeCost.setBlueCubes(0);
        flamethrower.setRechargeCost(rechargeCost);

        /*
        setting the price of the weapon
         */

        Rybamount price = new Rybamount();
        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(0);
        flamethrower.setPrice(price);

        /*
        Setting the effect.
         */
        flamethrower.setBaseEffect(new BasicFlamethrower());
        flamethrower.setAlternativeEffect(new BBQMode());

        /*
        the weapon, at the beginning, is loaded
         */

        flamethrower.setLoaded(true);




        return flamethrower;



    }











}
