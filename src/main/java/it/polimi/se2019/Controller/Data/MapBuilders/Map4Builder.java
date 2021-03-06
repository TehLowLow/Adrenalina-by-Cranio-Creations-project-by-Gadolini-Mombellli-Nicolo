package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Controller.Data.RoomBuilders.Map4Rooms.*;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.*;

/**
 * This class creates a builder for the Map number 4.
 * Its purpose is to produce a fully working map with every room complete with cells. To achieve this,
 * it will call various builders for the rooms.
 */

public class Map4Builder {

    /**
     * Builder method for the Map 4. It will produce an instance of Map corresponding to the Map number 4.
     *
     * @return an instance of Map corresponding to the map number 4.
     */

    public Map build(){

       Message.avvioMappa();

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
        ---------------- GREEN ROOM --------------------
         */

        GreenRoomBuilder greenRoomBuilder = new GreenRoomBuilder();
        Room greenRoom = greenRoomBuilder.build();

        /*
        -------------- PURPLE ROOM ----------------------
         */

        PurpleRoomBuilder purpleRoomBuilder = new PurpleRoomBuilder();
        Room purpleRoom = purpleRoomBuilder.build();


        Map map4 = new Map(4, greenRoom, redRoom, yellowRoom, whiteRoom, purpleRoom, blueRoom);

        this.connectRooms(map4);

        Message.mappaCompletata();
        return map4;

    }

    /**
     * connects the rooms together, arranging doors and walls
     * @param map is the map where the rooms are connected
     */


    private void connectRooms(Map map){


        Room yellow = map.getYellowRoom();
        Room purple = map.getPurpleRoom();
        Room white = map.getWhiteRoom();
        Room red = map.getRedRoom();
        Room blue = map.getBlueRoom();
        Room green = map.getGreenRoom();

        for (Cell yellowCell : yellow.getCells()) {

            if (yellowCell.getName().equals("lootCell1")) {

                for (Cell blueCell : blue.getCells()) {

                    if (blueCell.getName().equals("spawnCell")) {

                        yellowCell.getUpConnection().setConnectedCell(blueCell);
                        blueCell.getDownConnection().setConnectedCell(yellowCell);

                    }
                }

                for (Cell purpleCell : purple.getCells()) {

                    if (purpleCell.getName().equals("lootCell")) {
                        yellowCell.getLeftConnection().setConnectedCell(purpleCell);
                        purpleCell.getRightConnection().setConnectedCell(yellowCell);
                    }

                }

            }

            if (yellowCell.getName().equals("lootCell2")) {

                for (Cell greenCell : green.getCells()) {

                    if (greenCell.getName().equals("lootCell")) {

                        yellowCell.getUpConnection().setConnectedCell(greenCell);
                        greenCell.getDownConnection().setConnectedCell(yellowCell);

                    }
                }

            }

            if (yellowCell.getName().equals("lootCell3")) {

                for (Cell whiteCell : white.getCells()) {

                    if (whiteCell.getName().equals("lootCell2")) {

                        yellowCell.getLeftConnection().setConnectedCell(whiteCell);
                        whiteCell.getRightConnection().setConnectedCell(yellowCell);

                    }

                }

            }

        }

        for (Cell blueCell : blue.getCells()){

            if (blueCell.getName().equals("spawnCell")){

                for(Cell greenCell: green.getCells()){

                    if(greenCell.getName().equals("lootCell")){

                        blueCell.getRightConnection().setConnectedCell(greenCell);
                        greenCell.getLeftConnection().setConnectedCell(blueCell);


                    }
                }

            }

            if (blueCell.getName().equals("lootCell")){

                for(Cell purpleCell : purple.getCells()){

                    if(purpleCell.getName().equals("lootCell")){

                        blueCell.getDownConnection().setConnectedCell(purpleCell);
                        purpleCell.getUpConnection().setConnectedCell(blueCell);

                    }

                }

                for(Cell redCell : red.getCells()){

                    if(redCell.getName().equals("lootCell")){

                        blueCell.getLeftConnection().setConnectedCell(redCell);
                        redCell.getRightConnection().setConnectedCell(blueCell);

                    }

                }

            }

        }

        for(Cell purpleCell : purple.getCells()){

            if(purpleCell.getName().equals("lootCell")){

                for(Cell redCell : red.getCells()){

                    if(redCell.getName().equals("spawnCell")){

                        purpleCell.getLeftConnection().setConnectedCell(redCell);
                        redCell.getRightConnection().setConnectedCell(purpleCell);
                    }

                }

                for(Cell whiteCell : white.getCells()){

                    if(whiteCell.getName().equals("lootCell2")){

                        purpleCell.getDownConnection().setConnectedCell(whiteCell);
                        whiteCell.getUpConnection().setConnectedCell(purpleCell);

                    }

                }

            }

        }

        for(Cell whiteCell : white.getCells()){

            if(whiteCell.getName().equals("lootCell1")){

                for(Cell redCell : red.getCells()){

                    if(redCell.getName().equals("spawnCell")){

                        redCell.getDownConnection().setConnectedCell(whiteCell);
                        whiteCell.getUpConnection().setConnectedCell(redCell);

                    }

                }

            }

        }


    }

}

