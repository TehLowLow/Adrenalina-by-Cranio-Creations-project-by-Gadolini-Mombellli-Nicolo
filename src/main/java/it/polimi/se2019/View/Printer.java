package it.polimi.se2019.View;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;


public class Printer {


    public void map(Board board){

        System.out.println("Sto per stampare la mappa.");

        Map map = board.getMap();

        int mapID = map.getMapID();
        Room blueRoom = map.getBlueRoom();
        Room yellowRoom = map.getYellowRoom();
        Room redRoom = map.getRedRoom();
        Room greenRoom = map.getGreenRoom();
        Room whiteRoom = map.getWhiteRoom();
        Room purpleRoom = map.getPurpleRoom();

        ArrayList <Room> printableRooms= new ArrayList<Room>();

        if(blueRoom != null){
            printableRooms.add(blueRoom);
        }

        if(yellowRoom != null){
            printableRooms.add(yellowRoom);
        }

        if(redRoom != null){
            printableRooms.add(redRoom);
        }

        if(greenRoom!=null){
            printableRooms.add(greenRoom);
        }

        if(purpleRoom != null){
            printableRooms.add(purpleRoom);
        }

        if(whiteRoom!= null){
            printableRooms.add(whiteRoom);
        }

        System.out.println("ID MAPPA: " + mapID);

        for(Room room : printableRooms){

            System.out.println("/////////////////////");
            System.out.println("La stanza di colore " + this.colour(room.getColour())
            + " è composta dalle seguenti celle:");

            for(Cell cell : room.getCells()){

                Connection upConnection = cell.getUpConnection();
                Connection downConnection = cell.getDownConnection();
                Connection leftConnection = cell.getLeftConnection();
                Connection rightConnection = cell.getRightConnection();

                System.out.println("---");
                System.out.println("Nome: " + cell.getName());

                if(upConnection.getType() == Connection.EDGE){
                    System.out.println("Connessione in alto: limite della mappa.");
                }

               if(upConnection.getType() == Connection.DOOR){
                   System.out.println("Connessione in alto: tramite porta verso " +
                                       upConnection.getConnectedCell().getName() +
                                       " della stanza di colore " +
                                       this.colour(upConnection.getConnectedCell().getColour()));
               }

               if(upConnection.getType() == Connection.FREE){
                   System.out.println("Connessione in alto: libera verso " +
                                       upConnection.getConnectedCell().getName());
               }

               if(upConnection.getType() == Connection.WALL){
                   System.out.println("Connessione in alto: ostruita da un muro, oltre il quale si trova la cella "
                                      + upConnection.getConnectedCell().getName() +
                                      " della stanza di colore " +
                                      this.colour(upConnection.getConnectedCell().getColour()));
               }




                if(downConnection.getType() == Connection.EDGE){
                    System.out.println("Connessione in basso: limite della mappa.");
                }

                if(downConnection.getType() == Connection.DOOR){
                    System.out.println("Connessione in basso: tramite porta verso "
                           + downConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            this.colour(downConnection.getConnectedCell().getColour()));
                }

                if(downConnection.getType() == Connection.FREE){
                    System.out.println("Connessione in basso: libera verso "
                                      + downConnection.getConnectedCell().getName());
                }

                if(downConnection.getType() == Connection.WALL){
                    System.out.println("Connessione in basso: ostruita da un muro, oltre il quale si trova la cella "
                            + downConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            this.colour(downConnection.getConnectedCell().getColour()));
                }




                if(leftConnection.getType() == Connection.EDGE){
                    System.out.println("Connessione a sinistra: limite della mappa.");
                }

                if(leftConnection.getType() == Connection.DOOR){
                    System.out.println("Connessione a sinistra: tramite porta verso "
                            + leftConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            this.colour(leftConnection.getConnectedCell().getColour()));
                }

                if(leftConnection.getType() == Connection.FREE){
                    System.out.println("Connessione a sinistra: libera verso "
                            + leftConnection.getConnectedCell().getName());
                }

                if(leftConnection.getType() == Connection.WALL){
                    System.out.println("Connessione a sinistra: ostruita da un muro, oltre il quale si trova la cella "
                            + leftConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            this.colour(leftConnection.getConnectedCell().getColour()));
                }




                if(rightConnection.getType() == Connection.EDGE){
                    System.out.println("Connessione a destra: limite della mappa.");
                }

                if(rightConnection.getType() == Connection.DOOR){
                    System.out.println("Connessione a destra: tramite porta verso "
                            + rightConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            this.colour(rightConnection.getConnectedCell().getColour()));
                }

                if(rightConnection.getType() == Connection.FREE){
                    System.out.println("Connessione a destra: libera verso "
                            + rightConnection.getConnectedCell().getName());
                }

                if(rightConnection.getType() == Connection.WALL){
                    System.out.println("Connessione a destra: ostruita da un muro, oltre il quale si trova la cella "
                            + rightConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            this.colour(rightConnection.getConnectedCell().getColour()));
                }


            }

        }

        }



    public void playerboard(Playerboard board){
        /*Prints a playerboard
        */
    }

    public void score(Player player){

    }

    /**
     * This method converts an integer representing a colour to a string.
     * @param colour
     */
    public String colour(int colour){

        if(colour == Colour.BLUE){
            return "blu";
        }

        if(colour == Colour.YELLOW) {
            return "giallo";
        }

        if(colour == Colour.RED){
            return "rosso";
        }

        if(colour == Colour.GREEN){
            return "verde";
        }

        if(colour == Colour.WHITE){
            return "bianco";
        }

        if(colour == Colour.PURPLE){
            return "viola";
        }

        return "Colore sonosciuto";
    }



}


