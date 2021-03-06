package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map2Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map3Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map4Builder;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Printer;

/**
 * Class that prints the CLI, useful for testing purposes.
 */
public class CLItest {


    /**
     * Prints the CLI.
     * @param args
     */
    public static void main(String[] args) {

        //Map1Builder map1 = new Map1Builder();
        //Board.setMap(map1.build());

        Map2Builder map2 = new Map2Builder();
       Board.setMap(map2.build());


        //Map3Builder map3 = new Map3Builder();
        //Board.setMap((map3.build()));

       // Map4Builder map4 = new Map4Builder();
       // Board.setMap(map4.build());


        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();

        player1.setNickname("Gio");
        player2.setNickname("Lollo");
        player3.setNickname("Dan");
        player4.setNickname("Luca");
        player5.setNickname("Kev");

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(0));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        player3.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player4.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player5.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        Server.connectedPlayers.add(player1);
        Server.connectedPlayers.add(player2);
        Server.connectedPlayers.add(player3);
        Server.connectedPlayers.add(player4);
        Server.connectedPlayers.add(player5);

        //  System.out.println(Map1StringRep.map1());

         System.out.println(Map2StringRep.map2());

        // System.out.println(Map3StringRep.map3());

        //System.out.println(Map4StringRep.map4());

        for (Player player : Server.connectedPlayers) {

            Cell cell = player.getPosition();
            int colour = cell.getColour();
            String cellName = cell.getName();

            System.out.println(player.getNickname() + " si trova nella stanza " + cellName + " di colore " + Printer.colour(colour) + ".");

        }
    }
}
