package it.polimi.se2019.View;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Print class used for testing purposes.
 */
public class Printer {


    public static void map(){

        System.out.println("Sto per stampare la mappa.");

        Map map = Board.getMap();

        int mapID = map.getMapID();
        Room blueRoom = map.getBlueRoom();
        Room yellowRoom = map.getYellowRoom();
        Room redRoom = map.getRedRoom();
        Room greenRoom = map.getGreenRoom();
        Room whiteRoom = map.getWhiteRoom();
        Room purpleRoom = map.getPurpleRoom();

        CopyOnWriteArrayList <Room> printableRooms= new CopyOnWriteArrayList<Room>();

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
            System.out.println("La stanza di colore " + colour(room.getColour())
            + " è composta dalle seguenti celle:");

            for(Cell cell : room.getCells()){

                Connection upConnection = cell.getUpConnection();
                Connection downConnection = cell.getDownConnection();
                Connection leftConnection = cell.getLeftConnection();
                Connection rightConnection = cell.getRightConnection();

                System.out.println("---");
                System.out.println("Nome: " + cell.getName());

                if(upConnection.getType().equals(Connection.EDGE)){
                    System.out.println("Connessione in alto: limite della mappa.");
                }

               if(upConnection.getType().equals(Connection.DOOR)){
                   System.out.println("Connessione in alto: tramite porta verso " +
                                       upConnection.getConnectedCell().getName() +
                                       " della stanza di colore " +
                                       colour(upConnection.getConnectedCell().getColour()));
               }

               if(upConnection.getType().equals(Connection.FREE)){
                   System.out.println("Connessione in alto: libera verso " +
                                       upConnection.getConnectedCell().getName());
               }

               if(upConnection.getType().equals((Connection.WALL))){
                   System.out.println("Connessione in alto: ostruita da un muro, oltre il quale si trova la cella "
                                      + upConnection.getConnectedCell().getName() +
                                      " della stanza di colore " +
                                      colour(upConnection.getConnectedCell().getColour()));
               }




                if(downConnection.getType().equals(Connection.EDGE)){
                    System.out.println("Connessione in basso: limite della mappa.");
                }

                if(downConnection.getType().equals(Connection.DOOR)){
                    System.out.println("Connessione in basso: tramite porta verso "
                           + downConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            colour(downConnection.getConnectedCell().getColour()));
                }

                if(downConnection.getType().equals(Connection.FREE)){
                    System.out.println("Connessione in basso: libera verso "
                                      + downConnection.getConnectedCell().getName());
                }

                if(downConnection.getType().equals(Connection.WALL)){
                    System.out.println("Connessione in basso: ostruita da un muro, oltre il quale si trova la cella "
                            + downConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            colour(downConnection.getConnectedCell().getColour()));
                }




                if(leftConnection.getType().equals(Connection.EDGE)){
                    System.out.println("Connessione a sinistra: limite della mappa.");
                }

                if(leftConnection.getType().equals(Connection.DOOR)){
                    System.out.println("Connessione a sinistra: tramite porta verso "
                            + leftConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            colour(leftConnection.getConnectedCell().getColour()));
                }

                if(leftConnection.getType().equals(Connection.FREE)){
                    System.out.println("Connessione a sinistra: libera verso "
                            + leftConnection.getConnectedCell().getName());
                }

                if(leftConnection.getType().equals(Connection.WALL)){
                    System.out.println("Connessione a sinistra: ostruita da un muro, oltre il quale si trova la cella "
                            + leftConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            colour(leftConnection.getConnectedCell().getColour()));
                }




                if(rightConnection.getType().equals(Connection.EDGE)){
                    System.out.println("Connessione a destra: limite della mappa.");
                }

                if(rightConnection.getType().equals(Connection.DOOR)){
                    System.out.println("Connessione a destra: tramite porta verso "
                            + rightConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            colour(rightConnection.getConnectedCell().getColour()));
                }

                if(rightConnection.getType().equals(Connection.FREE)){
                    System.out.println("Connessione a destra: libera verso "
                            + rightConnection.getConnectedCell().getName());
                }

                if(rightConnection.getType().equals(Connection.WALL)){
                    System.out.println("Connessione a destra: ostruita da un muro, oltre il quale si trova la cella "
                            + rightConnection.getConnectedCell().getName() +
                            " della stanza di colore " +
                            colour(rightConnection.getConnectedCell().getColour()));
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
     * @param colour is an integer representing the colour
     * @return the converted integer into a string
     */
    public static String colour(int colour){

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


