package it.polimi.se2019.Network;


import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.RMI.Client.RMIClient;
import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.RMI.Server.RMIServer;

import it.polimi.se2019.Network.Socket.Server.SocketServer;
import it.polimi.se2019.Network.Socket.SocketLogger;



import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.*;


public class Server {


    private static volatile int clientPort = 4000;

    public static final int LOGINSOCKETPORT = 9999;
    public static final int LOGINRMIPORT = 8888;

    public static final int RMIPORT = 2100;
    public static final int SOCKETPORT = 2200;

    private static int lobbyTimer;

    public static volatile boolean matchStarted = false;

    private static ServerSocket loginSocket;
    private static ServerSocket gameSocket;

    public static boolean start = false;


    public static Hashtable registrations = new Hashtable();  //associazione user psw
    public static Hashtable playerClient = new Hashtable();  //associazione fra giocatore e suo client
    public static CopyOnWriteArrayList<Player> connectedPlayers = new CopyOnWriteArrayList<>();  //Arraylist di player connessi.


    private static ServerSocket initServer(int port) {

        try {

            ServerSocket sSocket = new ServerSocket(port);
            System.out.println("Server online listening on port " + port);
            return sSocket;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static synchronized int connectedSize() {
        return connectedPlayers.size();

    }

    public static synchronized Player newPlayer(String u, String p, String connection) {

        Player player = new Player();
        player.setNickname(u);
        player.setConnectionAlive(true);
        player.setConnectionTech(connection);
        player.setPORT(calcPorts());
        registrations.put(u, p);
        connectedPlayers.add(player);

        return player;

    }





    public static void main(String[] args) {

        //All avvio del server devono accadere due cose:
        //1) il server avvia i thread di inizializzazione delle connessioni dedicate alla partita
        //2) il server avvia altre due connessioni (una socket e una rmi) helpers, connessioni dedicate esclusivamente
        //   al login dei player.


        System.out.println((char)27 + "[33m");


        Scanner scanner = new Scanner(System.in);
        System.out.println("Specificare timer per la durata della lobby (in secondi): ");
        lobbyTimer = scanner.nextInt();


        //Avvio il logger di rmi
        Runnable loggerRMI = new RMILogger();
        Thread loginRMI = new Thread(loggerRMI);
        loginRMI.start();


        //Avvio thread pool logger di socket
        ExecutorService logExec = Executors.newFixedThreadPool(5);
        loginSocket = initServer(LOGINSOCKETPORT);

        for (int i = 0; i < 5; i++) {
            logExec.execute(new SocketLogger(loginSocket));  //Problema: resto in wait sulthread di login, trovare un modo per caricare i 5 thread e poi eseguirli in un altro flusso
            //di controllo.
        }

        //Da breakkare nel caso finisca il tempo per il login

        for (Player player : connectedPlayers) {

            System.out.println(player.getNickname());

        }

        //avvio il server di gioco

        Runnable gameServer = new RMIServer();
        Thread game = new Thread(gameServer);
        game.start();

        ExecutorService gameExec = Executors.newFixedThreadPool(5);  //Aggiungendo al game server

        gameSocket = initServer(SOCKETPORT);


        for (int i = 0; i < 5; i++) {

            gameExec.submit(new SocketServer(gameSocket));

        }


    }


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

    private static synchronized int calcPorts() {

        clientPort = clientPort + 100;
        return clientPort;
    }


}