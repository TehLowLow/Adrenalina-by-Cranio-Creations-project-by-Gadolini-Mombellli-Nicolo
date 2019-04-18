package it.polimi.se2019.Controller.Data.MapBuilders;

import it.polimi.se2019.Controller.Data.RoomBuilders.Map1Rooms.*;
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
     *
     * @return an instance of Map corresponding to the map number 1.
     */
    public Map build() {

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

        Room whiteRoom = whiteRoomBuilder.build();

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


        Map map1 = new Map(1, null, redRoom, yellowRoom, whiteRoom, null, blueRoom);

        this.connectRooms(map1);

        view.message.mappaCompletata();
        return map1;

    }


    /**
     * connects the rooms together, arranging doors and walls
     * @param map is the map where the rooms are connected
     */
    private void connectRooms(Map map) {




        Room yellow = map.getYellowRoom();
        Room purple = map.getPurpleRoom();
        Room white = map.getWhiteRoom();
        Room red = map.getRedRoom();
        Room blue = map.getBlueRoom();

        for (Cell yellowCell : yellow.getCells()) {

            if (yellowCell.getName().equals("lootCell")) {


                for (Cell redCell : red.getCells()) {

                    if (redCell.getName().equals("lootCell2")) {

                        yellowCell.getLeftConnection().setConnectedCell(redCell);
                        redCell.getRightConnection().setConnectedCell(yellowCell);
                    }
                }

            }

            if (yellowCell.getName().equals("spawnCell")) {

                for (Cell whiteCell : white.getCells()) {

                    if (whiteCell.getName().equals("lootCell2")) {

                        yellowCell.getLeftConnection().setConnectedCell(whiteCell);
                        whiteCell.getRightConnection().setConnectedCell(yellowCell);

                    }

                }
            }

        }

        for (Cell whiteCell : white.getCells()) {

            if (whiteCell.getName().equals("lootCell1")) {

                for (Cell redCell : red.getCells()) {

                    if (redCell.getName().equals("lootCell1")) {

                        whiteCell.getUpConnection().setConnectedCell(redCell);
                        redCell.getDownConnection().setConnectedCell(whiteCell);
                    }
                }
            }

            if (whiteCell.getName().equals("lootCell2")) {

                for (Cell redCell : red.getCells()) {

                    if (redCell.getName().equals("lootCell2")) {

                        whiteCell.getUpConnection().setConnectedCell(redCell);
                        redCell.getDownConnection().setConnectedCell(whiteCell);

                    }

                }

            }
        }

        for (Cell blueCell : blue.getCells()) {

            if (blueCell.getName().equals("lootCell1")) {

                for (Cell redCell : red.getCells()) {

                    if (redCell.getName().equals("spawnCell"))
                        redCell.getUpConnection().setConnectedCell(blueCell);
                    blueCell.getDownConnection().setConnectedCell(redCell);

                }
            }

            if (blueCell.getName().equals("lootCell2")) {

                for (Cell redCell : red.getCells()) {

                    if (redCell.getName().equals("lootCell1")) {

                        blueCell.getDownConnection().setConnectedCell(redCell);
                        redCell.getUpConnection().setConnectedCell(blueCell);
                    }
                }

            }

            if (blueCell.getName().equals("spawnCell")) {

                for (Cell redCell : red.getCells()) {
                    if (redCell.getName().equals("lootCell2")) {
                        redCell.getUpConnection().setConnectedCell(blueCell);
                        blueCell.getDownConnection().setConnectedCell(redCell);
                    }
                }

            }
        }


    }

}