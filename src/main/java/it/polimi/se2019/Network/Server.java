package it.polimi.se2019.Network;

import it.polimi.se2019.Controller.*;
import it.polimi.se2019.Controller.Adrenalina.*;
import it.polimi.se2019.Controller.Setup.BoardSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.RMI.Client.RMIClient;
import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.RMI.Server.RMIServer;
import it.polimi.se2019.Network.Socket.Server.SocketServer;
import it.polimi.se2019.View.Parser;
import it.polimi.se2019.Network.Socket.SocketLogger;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server {

    public static final int RMIPORT = 2100;
    public static final int SOCKETPORT = 2200;
    public static final int LOGINSOCKETPORT = 9999;
    public static final int LOGINRMIPORT = 8888;
    public static volatile boolean matchStarted = false;
    public static ServerSocket loginSocket;
    public static ServerSocket gameSocketl;
    public static Hashtable registrations = new Hashtable();  //associazione user psw
    public static Hashtable playerClient = new Hashtable();  //associazione fra giocatore e suo client
    public static CopyOnWriteArrayList<Player> connectedPlayers = new CopyOnWriteArrayList<>();


    public static String updateWithAnswer(Player player, String msg){

        if (player.getConnectionTech().equalsIgnoreCase("rmi")){

            RMIClient client = (RMIClient) playerClient.get(player);

            return client.sendMsgWithAnswer(msg);




        }

        if (player.getConnectionTech().equalsIgnoreCase("socket")){
            return null;
        }

        return null;


    }


    public static void update(Player player, String msg){

        if (player.getConnectionTech().equalsIgnoreCase("rmi")){

            RMIClient client = (RMIClient) playerClient.get(player);



        }


        if (player.getConnectionTech().equalsIgnoreCase("socket")){



        }

    }


    public static void main(String[] args) {

        //all avvio del server devono accadere due cose:
        //1) il server avvia i thread di inizializzazione delle connessioni dedicate alla partita
        //2) il server avvia altre due connessioni (una socket e una rmi) helpers, connessioni dedicate esclusivamente
        //   al login dei player.

        /*
        AVVIO PARTITA
         */

        Match newMatch = new Match();

        BoardSetup boardSetup = new BoardSetup();
        newMatch.setBoard(boardSetup.build());

        /*
        SCELTA DELLA MAPPA
         */




        //Avvio il logger di rmi
        Runnable loggerRMI = new RMILogger();
        Thread loginRMI = new Thread(loggerRMI);
        loginRMI.start();


        //Avvio logger socket

        Runnable loggerSocket = new SocketLogger();
        Thread loginSocket = new Thread(loggerSocket);
        loginSocket.start();


























    }


}
