package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class PlasmaGunBuilder {

    private Weapon pGun = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(1);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        pGun.setName("Fucile al Plasma");
        pGun.setPrice(price);
        pGun.setRechargeCost(recharge);
        pGun.setLoaded(true);
        pGun.setBaseEffect(null);
        pGun.setAlternativeEffect(null);
        pGun.setOptionalEffect(null);


        return pGun;

    }

}
