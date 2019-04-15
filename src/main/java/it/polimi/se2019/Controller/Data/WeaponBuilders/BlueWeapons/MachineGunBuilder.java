package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class MachineGunBuilder {

    private Weapon mGun = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(1);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(1);
        recharge.setYellowCubes(0);

        mGun.setName("Mitragliatrice");
        mGun.setPrice(price);
        mGun.setRechargeCost(recharge);
        mGun.setLoaded(true);
        mGun.setBaseEffect(null);
        mGun.setAlternativeEffect(null);
        mGun.setOptionalEffect(null);


        return mGun;
    }

}
