package it.polimi.se2019.Network;


import it.polimi.se2019.Controller.Adrenalina.*;
import it.polimi.se2019.Controller.Setup.BoardSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.RMI.Client.RMIClient;
import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.Socket.SocketLogger;
import org.jetbrains.annotations.NotNull;

import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.*;


public class Server {

    public static final int RMIPORT = 2100;
    public static final int SOCKETPORT = 2200;

    public static final int LOGINSOCKETPORT = 9999;
    public static final int LOGINRMIPORT = 8888;

    private static int lobbyTimer;

    public static volatile boolean matchStarted = false;


    public static Hashtable registrations = new Hashtable();  //associazione user psw
    public static Hashtable playerClient = new Hashtable();  //associazione fra giocatore e suo client
    public static CopyOnWriteArrayList<Player> connectedPlayers = new CopyOnWriteArrayList<>();  //Arraylist di player connessi.


    public static String updateWithAnswer(Player player, String msg) {

        if (player.getConnectionTech().equalsIgnoreCase("rmi")) {

            RMIClient client = (RMIClient) playerClient.get(player);

            return client.sendMsgWithAnswer(msg);


        }

        if (player.getConnectionTech().equalsIgnoreCase("socket")) {
            return null;
        }

        return null;


    }


    public static void update(Player player, String msg) {

        if (player.getConnectionTech().equalsIgnoreCase("rmi")) {

            RMIClient client = (RMIClient) playerClient.get(player);


        }


        if (player.getConnectionTech().equalsIgnoreCase("socket")) {


        }

    }


    public static synchronized int connectedSize() {
        return connectedPlayers.size();

    }

    public static synchronized void newPlayer(String u, String p, String connection) {

        Player player = new Player();
        player.setNickname(u);
        player.setConnectionAlive(true);
        player.setConnectionTech(connection);
        registrations.put(u, p);
        connectedPlayers.add(player);


    }


    public static void main(String[] args) {

        //All avvio del server devono accadere due cose:
        //1) il server avvia i thread di inizializzazione delle connessioni dedicate alla partita
        //2) il server avvia altre due connessioni (una socket e una rmi) helpers, connessioni dedicate esclusivamente
        //   al login dei player.


        Scanner scanner = new Scanner(System.in);
        System.out.println("Specificare timer per la durata della lobby (in secondi): ");
        lobbyTimer = scanner.nextInt();


        //Avvio il logger di rmi
        Runnable loggerRMI = new RMILogger();
        Thread loginRMI = new Thread(loggerRMI);
        loginRMI.start();


        //Avvio thread pool logger di socket

        ExecutorService logExec = Executors.newFixedThreadPool(5);


        for (int i = 0; i < 5; i++) {


            Runnable loggerSocket = () -> {
                new RMILogger();
            };
            logExec.submit(loggerSocket);
        } //Submitto i worker correttamente ma non li avvio.

        Match newMatch = new Match();

        BoardSetup boardSetup = new BoardSetup();
        newMatch.setBoard(boardSetup.build());



   /* = new SocketLogger();

        loginSocket.start();*/

    }
}