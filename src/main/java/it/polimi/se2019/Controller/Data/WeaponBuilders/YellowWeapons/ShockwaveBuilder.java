package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class ShockwaveBuilder {


    private Weapon shockwave = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(0);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        shockwave.setName("Onda d'Urto");
        shockwave.setPrice(price);
        shockwave.setRechargeCost(recharge);
        shockwave.setLoaded(true);
        shockwave.setBaseEffect(null);
        shockwave.setAlternativeEffect(null);
        shockwave.setOptionalEffect(null);


        return shockwave;


    }
}
