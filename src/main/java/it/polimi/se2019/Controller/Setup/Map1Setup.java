package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;

public class Map1Setup {

    public Map build(){

        Map1Builder map1Builder = new Map1Builder();

        map1Builder.build();

        Map map1 = new Map(1, map1Builder.yellowRoom);

        return map1;

    }










}
