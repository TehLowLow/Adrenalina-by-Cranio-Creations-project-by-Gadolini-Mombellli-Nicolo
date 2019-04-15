package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class ZX2Builder {

    private Weapon zX = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(1);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(0);
        recharge.setRedCubes(1);
        recharge.setYellowCubes(1);

        zX.setName("ZX-2");
        zX.setPrice(price);
        zX.setRechargeCost(recharge);
        zX.setLoaded(true);
        zX.setBaseEffect(null);
        zX.setAlternativeEffect(null);
        zX.setOptionalEffect(null);


        return zX;


    }
}
