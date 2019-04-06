package it.polimi.se2019.Controller.Data.WeaponBuilders;
import it.polimi.se2019.Model.*;

public class FurnaceBuilder {

    private Weapon furnace = new Weapon();

    public Weapon build(){

        furnace.setName("Furnace");
        return furnace;


    }

}
