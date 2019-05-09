package it.polimi.se2019.Controller.Setup;

import it.polimi.se2019.Controller.Data.WeaponBuilders.BlueWeapons.*;
import it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons.*;
import it.polimi.se2019.Controller.Data.WeaponBuilders.YellowWeapons.*;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

/**
 * Creates all the weapon cards and collects them on a Deck.
 */

public class WeaponDeckSetup {

    /**
     * Creates all the instances of weapons, and then collects them in an ArrayList, to be used as a deck.
     *
     * @return an ArrayList of weapons.
     */
    public ArrayList<Weapon> build() {


        ArrayList<Weapon> weaponDeck = new ArrayList<Weapon>();

        weaponDeck.add(new ElectroschytheBuilder().build());
        weaponDeck.add(new LockRifleBuilder().build());
        weaponDeck.add(new MachineGunBuilder().build());
        weaponDeck.add(new PlasmaGunBuilder().build());
        weaponDeck.add(new THORBuilder().build());
        weaponDeck.add(new TractorBeamBuilder().build());
        weaponDeck.add(new WhisperBuilder().build());
        weaponDeck.add(new FlamethrowerBuilder().build());
        weaponDeck.add(new FurnaceBuilder().build());
        weaponDeck.add(new GrenadeLauncherBuilder().build());
        weaponDeck.add(new HeatseekerBuilder().build());
        weaponDeck.add(new HellionBuilder().build());
        weaponDeck.add(new RocketLauncherBuilder().build());
        weaponDeck.add(new VortexCannonBuilder().build());
        weaponDeck.add(new CyberBladeBuilder().build());
        weaponDeck.add(new PowerGloveBuilder().build());
        weaponDeck.add(new RailgunBuilder().build());
        weaponDeck.add(new ShockwaveBuilder().build());
        weaponDeck.add(new ShotgunBuilder().build());
        weaponDeck.add(new SledgehammerBuilder().build());
        weaponDeck.add(new ZX2Builder().build());


        return weaponDeck;

    }


}
