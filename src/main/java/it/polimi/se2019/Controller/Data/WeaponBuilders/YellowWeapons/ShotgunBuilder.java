package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class ShotgunBuilder {


    private Weapon shotgun = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(1);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(0);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(2);

        shotgun.setName("Fucile a Pompa");
        shotgun.setPrice(price);
        shotgun.setRechargeCost(recharge);
        shotgun.setLoaded(true);
        shotgun.setBaseEffect(null);
        shotgun.setAlternativeEffect(null);
        shotgun.setOptionalEffect(null);


        return shotgun;


    }
}
