package it.polimi.se2019.Controller.Setup;
import  it.polimi.se2019.Controller.Data.WeaponBuilders.*;
import  it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * Creates all the weapon cards and collects them on a Deck.
 */

public class WeaponDeckSetup {

    /**
     * Creates all the instances of weapons, and then collects them in an ArrayList, to be used as a deck.
     * @return an ArrayList of weapons.
     */
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
