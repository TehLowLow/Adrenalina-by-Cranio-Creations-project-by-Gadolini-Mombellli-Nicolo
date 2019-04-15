package it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons;
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

        furnace.setName("Furnace");
        return furnace;


    }

}
