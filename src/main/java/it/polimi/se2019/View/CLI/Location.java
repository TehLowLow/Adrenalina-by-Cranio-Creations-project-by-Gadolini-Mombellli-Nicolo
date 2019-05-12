package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Room;
import it.polimi.se2019.Network.Server;

public class Location {

    //Questa classe contiene i metodi che permettono alle classi "MapStringRep" di capire se in una determinata cella
    //è presente o meno un giocatore, e nel caso stamparlo. Non mi veniva in mente un nome migliore da darle.

    public static Cell getCell(Room room, String name){

        Cell foundCell = null;

        for(Cell cell : room.getCells()){
            if(cell.getName().equalsIgnoreCase(name)){
                foundCell = cell;
            }
        }

        return foundCell;
    }

    public static String firstRow(Cell cell){

        String str = "                        ";

        Player player = Server.connectedPlayers.get(0);

        if(player.getPosition().equals(cell)){

           str = player.getNickname();

           if(str.length()<24){

               int size = str.length();

               for(; size<24; size++){
                   str = str + " ";
               }

           }

       }

        return str;

    }

    public static String secondRow(Cell cell){

        String str = "                        ";

        Player player = Server.connectedPlayers.get(1);

        if(player.getPosition().equals(cell)){

            str = player.getNickname();

            if(str.length()<24){

                int size = str.length();

                for(; size<24; size++){
                    str = str + " ";
                }

            }

        }

        return str;
    }

    public static String thirdRow(Cell cell){

        String str = "                        ";

        Player player = Server.connectedPlayers.get(2);

        if(player.getPosition().equals(cell)){

            str = player.getNickname();

            if(str.length()<24){

                int size = str.length();

                for(; size<24; size++){
                    str = str + " ";
                }

            }

        }

        return str;
    }

    public static String fourthRow(Cell cell){

        String str = "                        ";

        if(Server.connectedPlayers.size()<=3){
            return str;
        }

        Player player = Server.connectedPlayers.get(3);

        if(player.getPosition().equals(cell)){

            str = player.getNickname();

            if(str.length()<24){

                int size = str.length();

                for(; size<24; size++){
                    str = str + " ";
                }

            }

        }

        return str;
    }

    public static String fifthRow(Cell cell){

        String str = "                        ";

        if(Server.connectedPlayers.size()<=4){
            return str;
        }

        Player player = Server.connectedPlayers.get(4);

        if(player.getPosition().equals(cell)){

            str = player.getNickname();

            if(str.length()<24){

                int size = str.length();

                for(; size<24; size++){
                    str = str + " ";
                }

            }

        }

        return str;
    }

}

//TODO: pensare ad un nome migliore per questa classe
