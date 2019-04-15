package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class LockRifleBuilder {

    private Weapon lRifle = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(1);
        price.setYellowCubes(0);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(2);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(0);

        lRifle.setName("Distruttore");
        lRifle.setPrice(price);
        lRifle.setRechargeCost(recharge);
        lRifle.setLoaded(true);
        lRifle.setBaseEffect(null);
        lRifle.setAlternativeEffect(null);
        lRifle.setOptionalEffect(null);


        return lRifle;
    }

}
