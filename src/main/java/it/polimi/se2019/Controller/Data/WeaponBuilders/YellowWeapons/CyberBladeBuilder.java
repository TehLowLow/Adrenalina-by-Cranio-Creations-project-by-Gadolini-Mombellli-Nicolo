package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class CyberBladeBuilder {


    private Weapon cBlade = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(1);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(0);
        recharge.setRedCubes(1);
        recharge.setYellowCubes(1);

        cBlade.setName("Spada Fotonica");
        cBlade.setPrice(price);
        cBlade.setRechargeCost(recharge);
        cBlade.setLoaded(true);
        cBlade.setBaseEffect(null);
        cBlade.setAlternativeEffect(null);
        cBlade.setOptionalEffect(null);


        return cBlade;


    }
}
