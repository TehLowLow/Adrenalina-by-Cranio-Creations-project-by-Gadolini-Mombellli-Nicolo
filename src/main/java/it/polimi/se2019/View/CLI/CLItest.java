package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Controller.Data.MapBuilders.Map1Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map4Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map2Builder;
import it.polimi.se2019.Controller.Data.MapBuilders.Map3Builder;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Printer;

public class CLItest {


    public static void main(String[] args) {

        Map1Builder map1Builder = new Map1Builder();
        Board.setMap(map1Builder.build());

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

        player1.setPosition(Board.getMap().getBlueRoom().getCells().get(1));
        player2.setPosition(Board.getMap().getRedRoom().getCells().get(1));
        player3.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player4.setPosition(Board.getMap().getWhiteRoom().getCells().get(0));
        player5.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        Server.connectedPlayers.add(player1);
        Server.connectedPlayers.add(player2);
        Server.connectedPlayers.add(player3);
        Server.connectedPlayers.add(player4);
        Server.connectedPlayers.add(player5);

        System.out.println(Map1StringRep.map1);

        for(Player player : Server.connectedPlayers){

            Cell cell = player.getPosition();
            int colour = cell.getColour();
            String cellName = cell.getName();

            System.out.println(player.getNickname() + " si trova nella stanza " + cellName + " di colore " + Printer.colour(colour) + "." );

        }




    }

}
