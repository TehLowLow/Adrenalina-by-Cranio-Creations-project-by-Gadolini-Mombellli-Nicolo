package it.polimi.se2019.Controller.Setup;
import  it.polimi.se2019.Controller.Data.WeaponBuilders.FlamethrowerBuilder;
import  it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class WeaponDeckSetup {

    public ArrayList<Weapon> build() {

        ArrayList<Weapon> weaponDeck = new ArrayList<Weapon>();

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();

        weaponDeck.add(flamethrower);

        return weaponDeck;

    }


}
