package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Controller.Data.CellBuilders.LootCellBuilders.*;
import it.polimi.se2019.Controller.Data.CellBuilders.SpawnCellBuilders.*;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;

public class CellSetup {

    public ArrayList<Cell> build(){

        ArrayList<Cell> Room = new ArrayList<Cell>();

        /*
        ----------------YELLOW SPAWN CELL-------------------------
         */


        YellowSpawnCellBuilder yellowSpawnCellBuilder = new YellowSpawnCellBuilder();
        SpawnCell yellowSpawnCell = yellowSpawnCellBuilder.build();

        /*
        -----------------YELLOW LOOT CELL-----------------------
         */

        YellowLootCellBuilder yellowLootCellBuilder = new YellowLootCellBuilder();
        LootCell yellowLootCell = yellowLootCellBuilder.build();



        return Room;
    }
}
