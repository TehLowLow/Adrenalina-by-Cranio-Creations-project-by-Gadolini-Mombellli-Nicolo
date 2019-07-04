package it.polimi.se2019.Network;


import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.RMI.Client.RMIClientInterface;
import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.RMI.Server.RMIServer;
import it.polimi.se2019.Network.RMI.Server.RmiManager;
import it.polimi.se2019.Network.Socket.Server.SocketManager;
import it.polimi.se2019.Network.Socket.Server.SocketServer;
import it.polimi.se2019.Network.Socket.SocketLogger;
import it.polimi.se2019.View.CLI.CLIprinter;
import it.polimi.se2019.View.CLI.GameStringRep;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Server is the main class, being responsible of supervising network actions such as new connections and data sharing,
 * generating players data, and managing a complete match.
 */

public class Server {


    private static volatile int clientPort = 52000;
    public static final int LOGINSOCKETPORT = 55000;
    public static final int LOGINRMIPORT = 56000;
    public static final int RMIPORT = 59000;
    public static final int SOCKETPORT = 60000;
    public static int lobbyTimer;
    public static volatile boolean matchStarted = false;
    public static volatile boolean matchFinished = false;
    private static ServerSocket loginSocket;
    private static ServerSocket gameSocket;
    public static boolean start = false;
    public static Hashtable registrations = new Hashtable();  //associazione user psw
    public static Hashtable playerClient = new Hashtable();  //associazione fra giocatore e suo client
    public static CopyOnWriteArrayList<Player> connectedPlayers = new CopyOnWriteArrayList<>();  //Arraylist di player connessi.


    private static RMIClientInterface rmiplayer;

    /**
     * Creates and starts a socket for incoming connections over a predetermined PORT.
     *
     * @param port is the port where to listen to connections.
     * @return a serversocket able to listen for connections.
     */

    private static synchronized ServerSocket initServer(int port) {

        try {

            ServerSocket sSocket = new ServerSocket(port);
            System.out.println("Server online listening on port " + port);
            return sSocket;

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }

    /**
     * Returns the number of players connected to the lobby
     *
     * @return the number of players in the lobby.
     */

    public static synchronized int connectedSize() {
        return connectedPlayers.size();
    }

    /**
     * Recives approved credentials and builds the Player class, responsible of collecting all the player's data.
     *
     * @param u          is the provided and verified username of the player.
     * @param p          is the password used for the unique reconnection purposes.
     * @param connection stores the type of a player's connection.
     * @return a Player class containing all the above information.
     */
    public static synchronized Player newPlayer(String u, String p, String connection) {

        Player player = new Player();
        player.setNickname(u);
        player.setConnectionAlive(true);
        player.setConnectionTech(connection);
        if (connection.equalsIgnoreCase("rmi")){

            player.setPORT(calcPorts());

        }
        registrations.put(u, p);
        connectedPlayers.add(player);
        System.out.println("Ho aggiunto " + u);

        return player;

    }


    /**
     * Starts the server, initializes connections and await players. After the players are officially logged and the timer
     * ends, starts a new game.
     */

    public static void main(String[] args) {

        //All avvio del server devono accadere due cose:
        //1) il server avvia i thread di inizializzazione delle connessioni dedicate alla partita
        //2) il server avvia altre due connessioni (una socket e una rmi) helpers, connessioni dedicate esclusivamente
        //   al login dei player.


        Scanner scanner = new Scanner(System.in);
        System.out.println("Specificare di seguito un valore Timer per la durata della Lobby (in secondi) maggiore di 10: ");
        lobbyTimer = scanner.nextInt();

        boolean correct = false;


        while (!correct) {

            System.out.println("Il timer specificato è troppo breve per permettere ai giocatori che utilizzano" +
                    " RMI di connettersi, specifica un timer maggiore di 10:");

            lobbyTimer = scanner.nextInt();

            if (lobbyTimer >= 10) {
                correct = true;
            }

        }


        //Avvio il logger di rmi
        Runnable loggerRMI = new RMILogger();
        Thread loginRMI = new Thread(loggerRMI);
        loginRMI.start();


        //Avvio thread pool logger di socket
        ExecutorService logExec = Executors.newFixedThreadPool(5);
        loginSocket = initServer(LOGINSOCKETPORT);

        for (int i = 0; i < 5; i++) {
            logExec.execute(new SocketLogger(loginSocket));
        }


        //avvio il server di gioco
        Runnable gameServer = new RMIServer();
        Thread game = new Thread(gameServer);
        game.start();


        ExecutorService gamePool = Executors.newFixedThreadPool(5);

        gameSocket = initServer(SOCKETPORT);


        for (int i = 0; i < 5; i++) {
            gamePool.submit(new SocketServer(gameSocket));
        }


        Lobby lobby = new Lobby();
        lobby.start();

    }


    /**
     * Is one of the communication methods between Server and clients. The method sends to the player a String, and listens
     * for a player's answer.
     *
     * @param player is the player that will receive the string.
     * @param msg    is the information to push to the player.
     * @return the player's answer
     */

    public static synchronized String updateWithAnswer(Player player, String msg) {

        if (Board.getMap() != null && player.getPosition() != null && player.getPlayerboard().getChampionName() != null && matchStarted) {

            msg = CLIprinter.print(player) + "~" + msg + "~\n" + GameStringRep.print(player);

        }

        if (player.getConnectionTech().equalsIgnoreCase("testEnvironment")) {

            String answer = TestBot.getAnswer();
            System.out.println(msg + '\n' + answer);
            return answer;

        } else if (player.getConnectionTech().equalsIgnoreCase("socket")) {

            Socket stream = (Socket) playerClient.get(player.getPORT());

            try {

                DataOutputStream out = new DataOutputStream(stream.getOutputStream());
                DataInputStream in = new DataInputStream(stream.getInputStream());
                out.writeInt(2);
                System.out.println("Echo UWA: " + in.readInt());
                out.writeUTF(msg);
                String answer = in.readUTF(); //debug gui  TODO
                System.out.println(player.getNickname() + ": " + answer); //debug gui
                return answer; //in.readUTF(); debug gui

            } catch (SocketException e) {

                SocketManager socketManager = new SocketManager(player);
                socketManager.start();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (player.getConnectionTech().equalsIgnoreCase("rmi")) {

            try {

                Registry registry = LocateRegistry.getRegistry((String) playerClient.get(player.getNickname()), player.getPORT());
                RMIClientInterface rmiplayer = (RMIClientInterface) registry.lookup(player.getNickname());
                return rmiplayer.sendMsgWithAnswer(msg);

            } catch (RemoteException e) {

                RmiManager rmiManager = new RmiManager(player);
                rmiManager.start();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    /**
     * Pushes a string to a single player, and expects no answer from his side.
     *
     * @param player Is the player that will receive the message.
     * @param msg    is the information to push to the player.
     */

    public static synchronized void update(Player player, String msg) {

        if (player.getNickname().equalsIgnoreCase("terminator")) {

            return;

        }

        if (Board.getMap() != null && player.getPosition() != null && player.getPlayerboard().getChampionName() != null && matchStarted) {

            msg = CLIprinter.print(player) + "~" + msg + "~\n" + GameStringRep.print(player);

        }

        if (player.getConnectionTech().equalsIgnoreCase("testEnvironment")) {

            System.out.println(msg);
            return;

        }

        if (msg.equals("Polling")) {

            if (player.getConnectionTech().equalsIgnoreCase("socket")) {

                Socket stream = (Socket) playerClient.get(player.getPORT());

                try {

                    DataOutputStream out = new DataOutputStream(stream.getOutputStream());
                    DataInputStream echo = new DataInputStream(stream.getInputStream());
                    out.writeInt(200);
                    System.out.println(echo.readUTF());

                } catch (SocketException e) {

                    SocketManager socketManager = new SocketManager(player);
                    socketManager.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    Registry registry = LocateRegistry.getRegistry((String) playerClient.get(player.getNickname()), player.getPORT());
                    RMIClientInterface rmiplayer = (RMIClientInterface) registry.lookup(player.getNickname());  //TODO fixato questo il progetto è finito
                    rmiplayer.sendMsg("200");

                } catch (RemoteException re) {

                    RmiManager rmiManager = new RmiManager(player);
                    rmiManager.start();

                } catch (Exception e) {
                    e.printStackTrace();


                }

            }
        }

        if (player.getConnectionTech().equalsIgnoreCase("socket")) {
            Socket stream = (Socket) playerClient.get(player.getPORT());

            try {

                DataOutputStream out = new DataOutputStream(stream.getOutputStream());
                DataInputStream echo = new DataInputStream(stream.getInputStream());
                out.writeInt(1);
                System.out.println("Echo update: " + echo.readInt());
                out.writeUTF(msg);

            } catch (SocketException e) {

                SocketManager socketManager = new SocketManager(player);
                socketManager.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (player.getConnectionTech().equalsIgnoreCase("RMI")) {

            try {

                Registry registry = LocateRegistry.getRegistry((String) playerClient.get(player.getNickname()), player.getPORT());
                RMIClientInterface rmiplayer = (RMIClientInterface) registry.lookup(player.getNickname());  //TODO fixato questo il progetto è finito
                rmiplayer.sendMsg(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    //Gli int sono messaggi di controllo per decidere sul client in che routine entrare


    /**
     * Pushes a single message to all the connected clients and expects no answer from anyone.
     *
     * @param msg is the message to push to the clients.
     */

    public static synchronized void updateAll(String msg) {

        /*
        Update all deve ciclare sui connected players e mandare a tutti il msg
         */

        for (Player player : connectedPlayers) {

            if (player.getNickname().equalsIgnoreCase("Terminator")) {
                continue;
            }

            update(player, msg);
        }
    }

    /**
     * @return the first static port available for a player's client connection.
     */

    private static synchronized int calcPorts() {

        clientPort = clientPort + 100;
        return clientPort;

    }

}