package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class RailgunBuilder {


    private Weapon rGun = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(1);
        price.setYellowCubes(1);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(2);

        rGun.setName("Fucile Laser");
        rGun.setPrice(price);
        rGun.setRechargeCost(recharge);
        rGun.setLoaded(true);
        rGun.setBaseEffect(null);
        rGun.setAlternativeEffect(null);
        rGun.setOptionalEffect(null);


        return rGun;


    }
}
