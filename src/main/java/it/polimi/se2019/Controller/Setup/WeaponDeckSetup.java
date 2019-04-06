package it.polimi.se2019.Controller.Setup;
import  it.polimi.se2019.Controller.Data.WeaponBuilders.*;
import  it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class WeaponDeckSetup {

    public ArrayList<Weapon> build() {

        ArrayList<Weapon> weaponDeck = new ArrayList<Weapon>();

        /*
        --------------FLAMETHROWER-----------------------
         */

        FlamethrowerBuilder flamethrowerBuilder = new FlamethrowerBuilder();
        Weapon flamethrower = flamethrowerBuilder.build();

        weaponDeck.add(flamethrower);

        /*
        --------------FURNACE------------------------------
         */

        FurnaceBuilder furnaceBuilder = new FurnaceBuilder();
        Weapon furnace = furnaceBuilder.build();

        weaponDeck.add(furnace);


        /*
        ---------------HEATSEEKER---------------------------
         */

        HeatseekerBuilder heatseekerBuilder = new HeatseekerBuilder();
        Weapon heatseeker = heatseekerBuilder.build();

        weaponDeck.add(heatseeker);




        return weaponDeck;

    }


}
