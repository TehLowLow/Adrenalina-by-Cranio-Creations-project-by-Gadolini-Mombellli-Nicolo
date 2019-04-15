package it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons;

import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Weapon;

public class SledgehammerBuilder {


    private Weapon sHammer = new Weapon();

    public Weapon build() {

        Rybamount price = new Rybamount();

        price.setBlueCubes(0);
        price.setYellowCubes(0);
        price.setRedCubes(0);

        Rybamount recharge = new Rybamount();

        recharge.setBlueCubes(0);
        recharge.setRedCubes(0);
        recharge.setYellowCubes(1);

        sHammer.setName("MArtello Ionico");
        sHammer.setPrice(price);
        sHammer.setRechargeCost(recharge);
        sHammer.setLoaded(true);
        sHammer.setBaseEffect(null);
        sHammer.setAlternativeEffect(null);
        sHammer.setOptionalEffect(null);


        return sHammer;


    }
}
