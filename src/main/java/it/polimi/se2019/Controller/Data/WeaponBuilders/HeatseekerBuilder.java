package it.polimi.se2019.Controller.Data.WeaponBuilders;
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

        heatseeker.setName("Furnace");
        return heatseeker;


    }

}