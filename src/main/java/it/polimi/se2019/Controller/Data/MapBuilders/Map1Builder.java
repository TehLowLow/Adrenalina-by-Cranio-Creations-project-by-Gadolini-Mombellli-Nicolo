package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms.*;
import java.util.Iterator;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.*;


/**
 * This class creates a builder for the Map number 1.
 * Its purpose is to produce a fully working map with every room complete with cells. To achieve this,
 * it will call various builders for the rooms.
 */
public class Map1Builder {

    /**
     * Builder method for the Map 1. It will produce an instance of Map corresponding to the Map number 1.
     * @return an instance of Map corresponding to the map number 1.
     */
    public Map build(){

        View view = new View();

        view.message.avvioMappa();
        /*
        ------------------YELLOW ROOM-------------------
         */

        YellowRoomBuilder yellowRoomBuilder = new YellowRoomBuilder();

        Room yellowRoom = yellowRoomBuilder.build();

        /*
        ------------------WHITE ROOM-------------------
         */

        WhiteRoomBuilder whiteRoomBuilder = new WhiteRoomBuilder();

        Room whiteRoom =  whiteRoomBuilder.build();

        /*
        ---------------- RED ROOM ---------------------
         */

        RedRoomBuilder redRoomBuilder = new RedRoomBuilder();
        Room redRoom = redRoomBuilder.build();

         /*
        ---------------- BLUE ROOM ---------------------
         */
         BlueRoomBuilder blueRoomBuilder = new BlueRoomBuilder();
         Room blueRoom = blueRoomBuilder.build();

          /*
        ---------------- PURPLE ROOM ---------------------
         */

          PurpleRoomBuilder purpleRoomBuilder = new PurpleRoomBuilder();
          Room purpleRoom = purpleRoomBuilder.build();

          Map map1 =  new Map(1, null, redRoom, yellowRoom, whiteRoom, purpleRoom, blueRoom);

          this.connectRooms(map1);

        view.message.mappaCompletata();
        return map1;

    }

    private void connectRooms(Map map) {


        //Questo metodo si occupa di connettere tra loro le varie stanze, sistemando porte e muri.

        Room yellow = map.getYellowRoom();
        Room purple = map.getPurpleRoom();
        Room white = map.getWhiteRoom();
        Room red = map.getRedRoom();
        Room blue = map.getBlueRoom();

       for(Cell yellowCell: yellow.getCells()){

           if (yellowCell.getName() == "lootCell"){


               for(Cell purpleCell: purple.getCells()){

                   if(purpleCell.getName() == "lootCell2"){

                       yellowCell.getLeftConnection().setConnectedCell(purpleCell);
                       purpleCell.getRightConnection().setConnectedCell(yellowCell);
                   }
               }

           }

           if (yellowCell.getName() == "spawnCell"){

               for( Cell whiteCell : white.getCells()){

                   if (whiteCell.getName() == "lootCell2") {

                       yellowCell.getLeftConnection().setConnectedCell(whiteCell);
                       whiteCell.getRightConnection().setConnectedCell(yellowCell);

                   }

               }
           }

       }

       for(Cell whiteCell : white.getCells()){

           if(whiteCell.getName() == "lootCell1"){

               for(Cell purpleCell : purple.getCells()){

                   if(purpleCell.getName() == "lootCell1"){

                       whiteCell.getUpConnection().setConnectedCell(purpleCell);
                       purpleCell.getDownConnection().setConnectedCell(whiteCell);
                   }
               }
           }

           if(whiteCell.getName() == "lootCell2"){

               for(Cell purpleCell : purple.getCells()){

                   if(purpleCell.getName() == "lootCell2"){

                       whiteCell.getUpConnection().setConnectedCell(purpleCell);
                       purpleCell.getDownConnection().setConnectedCell(whiteCell);

                   }

               }

           }
       }

      for(Cell blueCell : blue.getCells()){

           if(blueCell.getName() == "lootCell1"){

               Cell redCell = red.getCells().get(0);
               redCell.getUpConnection().setConnectedCell(blueCell);
               blueCell.getDownConnection().setConnectedCell(redCell);

           }

           if(blueCell.getName() == "lootCell2"){

               for(Cell purpleCell : purple.getCells()){

                   if(purpleCell.getName() == "lootCell1"){

                       blueCell.getDownConnection().setConnectedCell(purpleCell);
                       purpleCell.getUpConnection().setConnectedCell(blueCell);
                   }
               }

           }

           if(blueCell.getName() == "spawnCell"){

               for(Cell purpleCell : purple.getCells()){
                   if(purpleCell.getName()== "lootCell2"){
                       purpleCell.getUpConnection().setConnectedCell(blueCell);
                       blueCell.getDownConnection().setConnectedCell(purpleCell);
                   }
               }

           }
       }

      for(Cell purpleCell : purple.getCells()){

           if(purpleCell.getName() == "lootCell1"){
               Cell redSpawnCell = red.getCells().get(0);
               purpleCell.getLeftConnection().setConnectedCell(redSpawnCell);
               redSpawnCell.getRightConnection().setConnectedCell(purpleCell);
           }
       }

    }

}