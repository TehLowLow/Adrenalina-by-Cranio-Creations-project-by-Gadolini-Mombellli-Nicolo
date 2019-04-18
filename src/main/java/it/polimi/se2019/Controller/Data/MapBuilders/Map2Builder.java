package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Controller.Data.RoomBuilders.Map2Rooms.*;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.*;

/**
 * This class creates a builder for the Map number 2.
 * Its purpose is to produce a fully working map with every room complete with cells. To achieve this,
 * it will call various builders for the rooms.
 */
public class Map2Builder {

    /**
     * Builder method for the Map 2. It will produce an instance of Map corresponding to the Map number 2.
     *
     * @return an instance of Map corresponding to the map number 2.
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
        ---------------- GREEN ROOM --------------------
         */

        GreenRoomBuilder greenRoomBuilder = new GreenRoomBuilder();
        Room greenRoom = greenRoomBuilder.build();


       Map map2 = new Map(2, greenRoom, redRoom, yellowRoom, whiteRoom, null, blueRoom);

       this.connectRooms(map2);

        view.message.mappaCompletata();
        return map2;

    }

    /**
     * connects the rooms together, arranging doors and walls
     * @param map is the map where the rooms are connected
     */

    private void connectRooms(Map map) {



        Room yellow = map.getYellowRoom();
        Room green = map.getGreenRoom();
        Room white = map.getWhiteRoom();
        Room red = map.getRedRoom();
        Room blue = map.getBlueRoom();

        for (Cell yellowCell : yellow.getCells()) {

            if (yellowCell.getName().equals("lootCell1")) {

                for (Cell blueCell : blue.getCells()) {

                    if (blueCell.getName().equals("spawnCell")) {

                        yellowCell.getUpConnection().setConnectedCell(blueCell);
                        blueCell.getDownConnection().setConnectedCell(yellowCell);

                    }
                }

                for (Cell redCell : red.getCells()) {

                    if (redCell.getName().equals("lootCell")) {
                        yellowCell.getLeftConnection().setConnectedCell(redCell);
                        redCell.getRightConnection().setConnectedCell(yellowCell);
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

                    if (whiteCell.getName().equals("lootCell")) {

                        yellowCell.getLeftConnection().setConnectedCell(whiteCell);
                        whiteCell.getRightConnection().setConnectedCell(yellowCell);

                    }

                }

            }

        }

        for (Cell redCell : red.getCells()) {

            if (redCell.getName().equals("lootCell")) {

                for (Cell whiteCell : white.getCells()) {

                    redCell.getDownConnection().setConnectedCell(whiteCell);
                    whiteCell.getUpConnection().setConnectedCell(redCell);

                }

                for (Cell yellowCell : yellow.getCells()) {

                    if (yellowCell.getName().equals("lootCell1")) {

                        redCell.getRightConnection().setConnectedCell(yellowCell);
                        yellowCell.getLeftConnection().setConnectedCell(redCell);

                    }

                }

                for (Cell blueCell : blue.getCells()) {

                    if (blueCell.getName().equals("lootCell2")) {

                        redCell.getUpConnection().setConnectedCell(blueCell);
                        blueCell.getDownConnection().setConnectedCell(redCell);

                    }
                }

            }

            if (redCell.getName().equals("spawnCell")) {
                for (Cell blueCell : blue.getCells()) {

                    if (blueCell.getName().equals("lootCell1")) {

                        redCell.getUpConnection().setConnectedCell(blueCell);
                        blueCell.getDownConnection().setConnectedCell(redCell);

                    }

                }
            }

        }

        for (Cell blueCell : blue.getCells()) {

            if (blueCell.getName().equals("spawnCell")) {

                for (Cell greenCell : green.getCells()) {

                    blueCell.getRightConnection().setConnectedCell(greenCell);
                    greenCell.getLeftConnection().setConnectedCell(blueCell);
                }

            }

        }


    }

}
