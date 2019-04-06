package it.polimi.se2019.Controller.Data.WeaponBuilders;
import it.polimi.se2019.Model.*;

public class FlamethrowerBuilder {

    private Weapon flamethrower = new Weapon();

    public Weapon build(){

        flamethrower.setName("Flamethrower");
        return flamethrower;


    }
}