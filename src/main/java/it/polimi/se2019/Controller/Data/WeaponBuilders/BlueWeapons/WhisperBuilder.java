package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class WhisperBuilder {


    private Weapon whisper = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(1);
        price.setYellowCubes(1);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(2);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        whisper.setName("Fucile di Precisione");
        whisper.setPrice(price);
        whisper.setRechargeCost(recharge);
        whisper.setLoaded(true);
        whisper.setBaseEffect(null);
        whisper.setAlternativeEffect(null);
        whisper.setOptionalEffect(null);


        return whisper;

    }


}
