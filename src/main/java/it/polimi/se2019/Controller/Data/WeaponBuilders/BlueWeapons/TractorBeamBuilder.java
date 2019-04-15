package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class TractorBeamBuilder {

    private Weapon tBeam = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(0);

        tBeam.setName("Raggio Traente");
        tBeam.setPrice(price);
        tBeam.setRechargeCost(recharge);
        tBeam.setLoaded(true);
        tBeam.setBaseEffect(null);
        tBeam.setAlternativeEffect(null);
        tBeam.setOptionalEffect(null);


        return tBeam;
    }
}
