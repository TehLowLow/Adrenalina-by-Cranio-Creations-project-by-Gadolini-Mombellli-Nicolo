package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Room;
import it.polimi.se2019.Network.Server;


/**
 * This class contains the methods that can determine a player's position.
 */
public class Location {


    /**
     * Gets a cell from a room.
     * @param room the room of the cell
     * @param name the name of the cell
     * @return the cell found.
     */
    public static Cell getCell(Room room, String name){

        Cell foundCell = null;

        for(Cell cell : room.getCells()){
            if(cell.getName().equalsIgnoreCase(name)){
                foundCell = cell;
            }
        }

        return foundCell;
    }

    /**
     * Prints the first row of a cell of a room in the CLI.
     * @param cell the cell that must be printed.
     * @return a string that eventually contains the name of a player on that cell.
     */
    public static String firstRow(Cell cell){

        String str = "                        ";

        Player player = Server.connectedPlayers.get(0);

        if(player.getPosition() != null && player.getPosition().equals(cell)){

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

    /**
     * Prints the first row of a cell of a room in the CLI.
     * @param cell the cell that must be printed.
     * @return a string that eventually contains the name of a player on that cell.
     */
    public static String secondRow(Cell cell){

        String str = "                        ";

        Player player = Server.connectedPlayers.get(1);

        if(player.getPosition() != null && player.getPosition().equals(cell)){

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


    /**
     * Prints the first row of a cell of a room in the CLI.
     * @param cell the cell that must be printed.
     * @return a string that eventually contains the name of a player on that cell.
     */
    public static String thirdRow(Cell cell){

        String str = "                        ";

        Player player = Server.connectedPlayers.get(2);

        if(player.getPosition() != null && player.getPosition().equals(cell)){

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

    /**
     * Prints the first row of a cell of a room in the CLI.
     * @param cell the cell that must be printed.
     * @return a string that eventually contains the name of a player on that cell.
     */
    public static String fourthRow(Cell cell){

        String str = "                        ";

        if(Server.connectedPlayers.size()<=3){
            return str;
        }

        Player player = Server.connectedPlayers.get(3);

        if(player.getPosition() != null && player.getPosition().equals(cell)){

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

    /**
     * Prints the first row of a cell of a room in the CLI.
     * @param cell the cell that must be printed.
     * @return a string that eventually contains the name of a player on that cell.
     */
    public static String fifthRow(Cell cell){

        String str = "                        ";

        if(Server.connectedPlayers.size()<=4){
            return str;
        }

        Player player = Server.connectedPlayers.get(4);

        if(player.getPosition() != null && player.getPosition().equals(cell)){

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

