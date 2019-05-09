package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Controller.Data.EffectBuilders.WhisperEffect;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

/**
 * This class builds the weapon Whisper
 */

public class WhisperBuilder {

    /*
    -----------FIELDS---------
    the object that will contain the Whisper
     */

    /**
     * whisper is an instance of Weapon
     */

    private Weapon whisper = new Weapon();

    /*
    ----------METHODS---------
     */

    /**
     * builder method for the whisper
     *
     * @return the reference to the weapon whisper, with all its fields initialized
     */


    public Weapon build() {

        /*
        setting the price rybamount
         */

        Rybamount price = new Rybamount();

        price.setBlueCubes(1);
        price.setYellowCubes(1);
        price.setRedCubes(0);

        /*
        setting the recharge rybamount
         */

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(2);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        /*
        setting the fields of the weapon
         */

        whisper.setName("Fucile di Precisione");
        whisper.setPrice(price);
        whisper.setRechargeCost(recharge);
        whisper.setLoaded(true);
        whisper.setBaseEffect(new WhisperEffect());
        whisper.setAlternativeEffect(null);
        return whisper;

    }


}
