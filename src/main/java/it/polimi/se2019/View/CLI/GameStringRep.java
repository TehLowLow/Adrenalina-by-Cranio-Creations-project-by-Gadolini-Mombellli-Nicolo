package it.polimi.se2019.View.CLI;

import com.sun.java.accessibility.util.GUIInitializedListener;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.GUI.GUI;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameStringRep {

/*
Fornisce una stringa che verrà parsata dalla GUI, al fine di refreshare la grafca. Questa stringa viene inviata insieme
alla CLI, ed è separata da essa dal carattere '$'. Ogni sezione, separata da *, rappresenta una parte di model che viene
codificata e inviata.
 */

    public static String print(Player player){

        String GUIData = "";
        GUIData = GUIData + "$";


        //---- AGGIUNTA INFORMAZIONI PLAYER ----//

        GUIData = GUIData + player.getNickname() + '@';
        GUIData = GUIData + player.getPlayerboard().getChampionName() + '@';
        GUIData = GUIData + player.getScore() + '@';
        GUIData = GUIData + "*";

        //----- AGGIUNTA INFORMAZIONI POSIZIONE GIOCATORE ----//

        GUIData = GUIData + player.getPosition().getColour() + '@';
        GUIData = GUIData + player.getPosition().getName() + '@';
        GUIData = GUIData + "*";

        //--- AGGIUNTA INFORMAZIONI ARMI GIOCATORE ----//

        for(Weapon weapon : player.getPlayerboard().getWeapons()){

            GUIData = GUIData + weapon.getName();

            if(weapon.isLoaded()){
                GUIData = GUIData + "@1";
            }
            else{
                GUIData = GUIData + "@0";
            }

            GUIData = GUIData + '@';
        }

        if(player.getPlayerboard().getWeapons().size()==0){

            GUIData = GUIData + "noweapons";

        }

        GUIData = GUIData + '*';


        // -- AGGIUNTA INFORMAZIONI POWERUP GIOCATORE -- //

        for(Powerup powerup : player.getPlayerboard().getPowerups()){

            GUIData = GUIData + powerup.getName();
            GUIData = GUIData + '@';
        }

        if(player.getPlayerboard().getPowerups().size()==0){
            GUIData = GUIData + "nopowerups";
        }

        GUIData = GUIData + '*';

        // -- AGGIUNTA INFORMAZIONI MUNIZIONI GIOCATORE -- //

        GUIData = GUIData + player.getPlayerboard().getAmmoCubes().getBlue() + '@';
        GUIData = GUIData + player.getPlayerboard().getAmmoCubes().getYellow() + '@';
        GUIData = GUIData + player.getPlayerboard().getAmmoCubes().getRed() + '@';
        GUIData = GUIData + '*';

        // -- AGGIUNTA INFORMAZIONI LOOT --- //
       /* la stringa del loot è una stringa del tipo:
       ryy@ryb@pyy#byy@byy@byy#...
       dove le stanze sono separate da #, i singoli loot sono separati da @.
        */

        Room blueRoom = Board.getMap().getBlueRoom();
        Room redRoom = Board.getMap().getRedRoom();
        Room yellowRoom = Board.getMap().getYellowRoom();
        Room whiteRoom = Board.getMap().getWhiteRoom();
        Room greenRoom = Board.getMap().getGreenRoom();
        Room purpleRoom = Board.getMap().getPurpleRoom();

        CopyOnWriteArrayList <Cell> blueRoomCells = blueRoom.getCells();
        CopyOnWriteArrayList <Cell> redRoomCells = redRoom.getCells();
        CopyOnWriteArrayList <Cell> yellowRoomCells = yellowRoom.getCells();


        for(Cell cell : blueRoomCells){

             if(!cell.getName().equalsIgnoreCase("spawnCell")){

                 LootCell lootc = (LootCell)cell;

                 if(lootc.getLoot()!=null) {
                     GUIData = GUIData + lootc.getLoot().getName();
                     GUIData = GUIData + '@';
                 }
                 else{
                     GUIData = GUIData + "empty@";
                 }

             }

        }

        GUIData = GUIData + '#';

        for(Cell cell : redRoomCells){

            if(!cell.getName().equalsIgnoreCase("spawnCell")){

                LootCell lootc = (LootCell)cell;

                if(lootc.getLoot()!=null) {
                    GUIData = GUIData + lootc.getLoot().getName();
                    GUIData = GUIData + '@';
                }
                else{
                    GUIData = GUIData + "empty@";
                }

            }

        }

        GUIData = GUIData + '#';

        for(Cell cell : yellowRoomCells){

            if(!cell.getName().equalsIgnoreCase("spawnCell")){

                LootCell lootc = (LootCell)cell;

                if(lootc.getLoot()!=null) {
                    GUIData = GUIData + lootc.getLoot().getName();
                    GUIData = GUIData + '@';
                }
                else{
                    GUIData = GUIData + "empty@";
                }

            }

        }

        GUIData = GUIData + '#';

        if(whiteRoom!=null){

            CopyOnWriteArrayList <Cell> whiteRoomCells = whiteRoom.getCells();

            for(Cell cell : whiteRoomCells){

                LootCell lootc = (LootCell) cell;

                if(lootc.getLoot()==null){
                    GUIData = GUIData + "empty@";
                }

                else {
                    GUIData = GUIData + lootc.getLoot().getName();
                    GUIData = GUIData + '@';
                }


            }

            GUIData = GUIData + '#';

        }

        if(greenRoom!=null){

            CopyOnWriteArrayList <Cell> greenRoomCells = greenRoom.getCells();

            for(Cell cell : greenRoomCells){

                LootCell lootc = (LootCell) cell;

                if(lootc.getLoot()!=null) {
                    GUIData = GUIData + lootc.getLoot().getName();
                    GUIData = GUIData + '@';
                }
                else{
                    GUIData = GUIData + "empty@";
                }


            }

            GUIData = GUIData + '#';
        }

        if(purpleRoom!=null){

            CopyOnWriteArrayList <Cell> purpleRoomCells = purpleRoom.getCells();

            for(Cell cell : purpleRoomCells){

                LootCell lootc = (LootCell) cell;

                if(lootc.getLoot()!=null) {

                    GUIData = GUIData + lootc.getLoot().getName();
                    GUIData = GUIData + '@';
                }
                else{
                    GUIData = GUIData + "empty@";
                }


            }

            GUIData = GUIData + '#';

        }

        GUIData = GUIData + '*';


        // -- AGGIUNTA INFORMAZIONI ARMI CELLE -- //

        for(Cell cell : blueRoomCells){

            if(cell.getName().equalsIgnoreCase("spawnCell")){

                SpawnCell sCell = (SpawnCell) cell;

                CopyOnWriteArrayList <Weapon> weapons = sCell.getAvailableWeapons();

                for(Weapon weapon : weapons){

                    GUIData = GUIData + weapon.getName();
                    GUIData = GUIData + '@';

                }

                if(weapons.size()<3){

                    for(int i = 0; i<(3-weapons.size()); i++){
                        GUIData = GUIData + "empty@";
                    }

                }

            }

        }

        GUIData = GUIData + '#';

        for(Cell cell : redRoomCells){

            if(cell.getName().equalsIgnoreCase("spawnCell")){

                SpawnCell sCell = (SpawnCell) cell;

                CopyOnWriteArrayList <Weapon> weapons = sCell.getAvailableWeapons();

                for(Weapon weapon : weapons){

                    GUIData = GUIData + weapon.getName();
                    GUIData = GUIData + '@';

                }

                if(weapons.size()<3){

                    for(int i = 0; i<(3-weapons.size()); i++){
                        GUIData = GUIData + "empty@";
                    }

                }

            }

        }

        GUIData = GUIData + '#';

        for(Cell cell : yellowRoomCells){

            if(cell.getName().equalsIgnoreCase("spawnCell")){

                SpawnCell sCell = (SpawnCell) cell;

                CopyOnWriteArrayList <Weapon> weapons = sCell.getAvailableWeapons();

                for(Weapon weapon : weapons){

                    GUIData = GUIData + weapon.getName();
                    GUIData = GUIData + '@';

                }

                if(weapons.size()<3){

                    for(int i = 0; i<(3-weapons.size()); i++){
                        GUIData = GUIData + "empty@";
                    }

                }

            }

        }

        GUIData = GUIData + '*';

        // -- AGGIUNTA INFORMAZIONI DANNI SUBITI -- //

        int damageNumber = player.getPlayerboard().getDamage().size();

        GUIData = GUIData + damageNumber + '@';

        for(Token damage : player.getPlayerboard().getDamage()){

            GUIData = GUIData + damage.getChampionName() + '@';

        }

        GUIData = GUIData + '*';


        // -- AGGIUNTA PLAYERBOARD VALUE -- //

        if(player.getPlayerboard().getPlayerboardValue().size()==1){
            GUIData = GUIData + '0';
        }
        else {
            GUIData = GUIData + player.getPlayerboard().getPlayerboardValue().get(0);
        }
        GUIData = GUIData + '*';

        // -- AGGIUNTA MORTALBLOWTRACK -- //

        int skullNumber = Board.getMortalBlowTrack().size();

        GUIData = GUIData + skullNumber + '@';

        for(MortalBlow mb : Board.getMortalBlowTrack()){

            if(mb.isSkull()){

                GUIData = GUIData + "0@";

                GUIData = GUIData + "skull@";

            }

            if(!mb.isSkull()){

                if(mb.isOverkill()){
                    GUIData = GUIData + "1@";
                }
                else{
                    GUIData = GUIData + "0@";
                }

                GUIData = GUIData + mb.getKiller().getPlayerboard().getChampionName() + '@';

            }

        }

        GUIData = GUIData + '*';

        // AGGIUNTA INFORMAZIONI MARKER

        int markerNumber = player.getPlayerboard().getMarker().size();

        GUIData = GUIData + markerNumber + '@';

        for(Token marker : player.getPlayerboard().getMarker()){

            GUIData = GUIData + marker.getChampionName() + '@';

        }

        GUIData = GUIData + '*';

        // AGGIUNTA INFORMAZIONI POSIZIONE ALTRI GIOCATORI

        int otherPlayers = Server.connectedPlayers.size() - 1;

        GUIData = GUIData + otherPlayers + '@';

        for(Player other : Server.connectedPlayers){

            if(!other.equals(player)){

                GUIData = GUIData + other.getPlayerboard().getChampionName() + '@';
                GUIData = GUIData + other.getNickname() + '@';
                GUIData = GUIData + other.getPosition().getColour() + '@';
                GUIData = GUIData + other.getPosition().getName() + '@';


            }

        }

        GUIData = GUIData + '*';




        return GUIData;


    }

}
