package it.polimi.se2019.Controller.Data.MapBuilders;
import it.polimi.se2019.Controller.Data.RoomBuilders.Map3Rooms.*;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.*;

public class Map3Builder {

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
        --------------- PURPLE ROOM ---------------------
         */

        PurpleRoomBuilder purpleRoomBuilder = new PurpleRoomBuilder();
        Room purpleRoom = purpleRoomBuilder.build();

        Map map3 = new Map(3,null, redRoom, yellowRoom, whiteRoom, purpleRoom, blueRoom);

        this.connectRooms(map3);
        view.message.mappaCompletata();
        return map3;

    }

    private void connectRooms(Map map){

        //Questo metodo si occupa di connettere tra loro le varie stanze, sistemando porte e muri.

        Room yellow = map.getYellowRoom();
        Room purple = map.getPurpleRoom();
        Room white = map.getWhiteRoom();
        Room red = map.getRedRoom();
        Room blue = map.getBlueRoom();

        for(Cell yellowCell : yellow.getCells()){

            if(yellowCell.getName().equals("lootCell")){

                for(Cell purpleCell : purple.getCells()){

                    if(purpleCell.getName().equals("lootCell2")){

                        yellowCell.getLeftConnection().setConnectedCell(purpleCell);
                        purpleCell.getRightConnection().setConnectedCell(yellowCell);
                    }

                }

            }

            if(yellowCell.getName().equals("spawnCell")){

                for(Cell whiteCell : white.getCells()){

                    if(whiteCell.getName().equals("lootCell3")){

                        yellowCell.getLeftConnection().setConnectedCell(whiteCell);
                        whiteCell.getRightConnection().setConnectedCell(yellowCell);

                    }

                }

            }

        }

        for(Cell purpleCell : purple.getCells()){

            if(purpleCell.getName().equals("lootCell2")){

                for(Cell whiteCell : white.getCells()){

                    if(whiteCell.getName().equals("lootCell3")){

                        purpleCell.getDownConnection().setConnectedCell(whiteCell);
                        whiteCell.getUpConnection().setConnectedCell(purpleCell);
                    }

                }

                for(Cell blueCell : blue.getCells()){

                    if(blueCell.getName().equals("spawnCell")){

                        purpleCell.getUpConnection().setConnectedCell(blueCell);
                        blueCell.getDownConnection().setConnectedCell(purpleCell);

                    }

                }

            }

            if(purpleCell.getName().equals("lootCell1")){

                for(Cell whiteCell : white.getCells()){

                    if(whiteCell.getName().equals("lootCell2")){

                        purpleCell.getDownConnection().setConnectedCell(whiteCell);
                        whiteCell.getUpConnection().setConnectedCell(purpleCell);
                    }

                }

                for(Cell blueCell : blue.getCells()){

                    if(blueCell.getName().equals("lootCell")){

                        purpleCell.getUpConnection().setConnectedCell(blueCell);
                        blueCell.getDownConnection().setConnectedCell(purpleCell);

                    }

                }

            }

        }

        for(Cell redCell : red.getCells()){

            if(redCell.getName().equals("lootCell")){

                for(Cell blueCell : blue.getCells()){

                    if(blueCell.getName().equals("lootCell")){

                        redCell.getRightConnection().setConnectedCell(blueCell);
                        blueCell.getLeftConnection().setConnectedCell(redCell);

                    }



                }

            }

            if(redCell.getName().equals("spawnCell")){

                for(Cell purpleCell : purple.getCells()){

                    if(purpleCell.getName().equals("lootCell1")){

                        redCell.getRightConnection().setConnectedCell(purpleCell);
                        purpleCell.getLeftConnection().setConnectedCell(redCell);

                    }
                }

                for(Cell whiteCell : white.getCells()){

                    if(whiteCell.getName().equals("lootCell1")){

                        redCell.getDownConnection().setConnectedCell(whiteCell);
                        whiteCell.getUpConnection().setConnectedCell(redCell);

                    }

                }

            }

        }

    }

}
