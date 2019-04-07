package it.polimi.se2019.Controller.Data.WeaponBuilders;
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

        flamethrower.setName("Flamethrower");
        return flamethrower;



    }

}
