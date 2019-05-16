package it.polimi.se2019.Network;


import it.polimi.se2019.Controller.Adrenalina.Match;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.RMI.Server.RMIServer;
import it.polimi.se2019.View.CLI.*;
import it.polimi.se2019.Network.Socket.Server.SocketServer;
import it.polimi.se2019.Network.Socket.SocketLogger;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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


    private synchronized static ServerSocket initServer(int port) {

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


        System.out.println("Ho aggiunto " + u);

        return player;

    }


    public static void main(String[] args) {

        //All avvio del server devono accadere due cose:
        //1) il server avvia i thread di inizializzazione delle connessioni dedicate alla partita
        //2) il server avvia altre due connessioni (una socket e una rmi) helpers, connessioni dedicate esclusivamente
        //   al login dei player.


        Scanner scanner = new Scanner(System.in);
        System.out.println("Specificare timer per la durata della lobby (in secondi): ");
        lobbyTimer = scanner.nextInt();


       /* //Avvio il logger di rmi
        Runnable loggerRMI = new RMILogger();
        Thread loginRMI = new Thread(loggerRMI);
        loginRMI.start();*/


        //Avvio thread pool logger di socket
        ExecutorService logExec = Executors.newFixedThreadPool(5);
        loginSocket = initServer(LOGINSOCKETPORT);

        for (int i = 0; i < 5; i++) {
            logExec.execute(new SocketLogger(loginSocket));
        }


      /*  //avvio il server di gioco
        Runnable gameServer = new RMIServer();
        Thread game = new Thread(gameServer);
        game.start();*/


        ExecutorService gamePool = Executors.newFixedThreadPool(5);


        gameSocket = initServer(SOCKETPORT);


        for (int i = 0; i < 5; i++) {
            gamePool.submit(new SocketServer(gameSocket));
        }

        while(Server.connectedPlayers.size()<3){
            continue;
        }

        Match match = new Match();
        match.start();


    }


    public synchronized static String updateWithAnswer(Player player, String msg) {

        if (Board.getMap() != null && player.getPosition() != null){

            msg = CLIprinter.print(player) + msg;

        }

        if (player.getConnectionTech().equalsIgnoreCase("socket")) {

            Socket stream = (Socket) playerClient.get(player.getPORT());

            try {

                DataOutputStream out = new DataOutputStream(stream.getOutputStream());
                DataInputStream in = new DataInputStream(stream.getInputStream());
                out.writeInt(2);
                System.out.println(in.readInt());
                out.writeUTF(msg);
                return in.readUTF();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        return null;


    }


    public synchronized static void update(Player player, String msg) {

        if (Board.getMap() != null && player.getPosition() != null){

            msg = CLIprinter.print(player) + msg;

        }



        if (player.getConnectionTech().equalsIgnoreCase("socket")) {
            Socket stream = (Socket) playerClient.get(player.getPORT());

            try {


                DataOutputStream out = new DataOutputStream(stream.getOutputStream());
                DataInputStream echo = new DataInputStream(stream.getInputStream());
                out.writeInt(1);
                System.out.println(echo.readInt());
                out.writeUTF(msg);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized static void updateAll(String msg) {

        /*for (Player player : connectedPlayers) {

            update(player, "test di UpdateAll, resta in linea");

        }*/


    }


    private static synchronized int calcPorts() {

        clientPort = clientPort + 100;
        return clientPort;

    }



    /*
    Per gestire le connessioni devo ricordarmi di inserire nel daemon thread che controlla i connessi una regola tale
    per cui a circa 5/10 secondi dalla fine imposto il match come "iniziato". Questo previene falsi positivi di client
    che aprono la connessione e poi vanno afk falsando il numero di connessi con registeredPlayers.
    Con sto sgamo impedisco che cio accada chiudendo preventivamente le iscrizioni a pochi secondi dalla fine cosi che
    ogni user possa con calma finire la registrazione.


    Pensare per il futuro di inserire anche un timer per la registrazione, cosi da impedire ulteriormente casini legati
    al login
     */


}