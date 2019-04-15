package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class PowerGloveBuilder {


    private Weapon pGlove = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(1);
        price.setYellowCubes(0);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(1);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        pGlove.setName("Cyberguanto");
        pGlove.setPrice(price);
        pGlove.setRechargeCost(recharge);
        pGlove.setLoaded(true);
        pGlove.setBaseEffect(null);
        pGlove.setAlternativeEffect(null);
        pGlove.setOptionalEffect(null);


        return pGlove;


    }
}
