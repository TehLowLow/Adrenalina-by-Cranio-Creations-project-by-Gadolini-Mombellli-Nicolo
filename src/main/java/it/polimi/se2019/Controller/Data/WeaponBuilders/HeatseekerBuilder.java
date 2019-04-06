package it.polimi.se2019.Controller.Data.WeaponBuilders;
import it.polimi.se2019.Model.*;

public class HeatseekerBuilder {

    private Weapon heatseeker = new Weapon();

    public Weapon build(){

        heatseeker.setName("Furnace");
        return heatseeker;


    }

}