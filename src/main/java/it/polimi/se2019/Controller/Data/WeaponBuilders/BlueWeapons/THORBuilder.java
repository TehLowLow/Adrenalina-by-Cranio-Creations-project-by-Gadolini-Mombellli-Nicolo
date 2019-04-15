package it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class THORBuilder {

    private Weapon thor = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(1);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(1);
        recharge.setYellowCubes(0);

        thor.setName("Torpedine");
        thor.setPrice(price);
        thor.setRechargeCost(recharge);
        thor.setLoaded(true);
        thor.setBaseEffect(null);
        thor.setAlternativeEffect(null);
        thor.setOptionalEffect(null);


        return thor;
    }

}
