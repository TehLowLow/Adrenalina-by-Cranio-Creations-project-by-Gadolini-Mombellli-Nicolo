package it.polimi.se2019.Network.RMI.Client;


import it.polimi.se2019.Network.Client;
import it.polimi.se2019.Network.RMI.RMILoggerInterface;
import it.polimi.se2019.Network.RMI.Server.RMIServerInterface;
import it.polimi.se2019.View.GUI.GUI;
import javafx.application.Platform;

import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import static it.polimi.se2019.Network.Server.LOGINRMIPORT;
import static it.polimi.se2019.Network.Server.RMIPORT;
import static java.lang.Thread.sleep;

/**
 * This subclass extends client and adds all the methods used for a match played with an RMI connection from the client to the server.
 * This class is responsible of starting the login routine and initializing the local rmiregistry used for dual channel connection.
 */


public class RMIClient extends Client implements Runnable, RMIClientInterface, Remote {

    /**
     * Is the remote interface of the server responsible for the login of a player..
     */
    static RMILoggerInterface Logger;

    /**
     * Temporary interface used for connections.
     */
    public static RMILoggerInterface rServer;

    /**
     * Is the remote interface of the server responsible for all the match interactions.
     */
    public static RMIServerInterface Game;

    /**
     * Temporary interface used for connections.
     */
    public static RMIServerInterface gServer;

    /**
     * True if the client establishes an rmi channel
     */
    private boolean connected = false;

    /**
     * True if the player succesfully logs into a game server.
     */
    private boolean logged = false;

    /**
     * Temporary string that stores the username of the player.
     */
    private String user;

    /**
     * Temprorary string that stores the password of the player.
     */
    private String psw;

    /**
     * Stores the local port of the player's pc connected to the game server.
     */
    private int localPort;

    /**
     * Result of a login routine, used as a flag for failures.
     */
    private int value;

    /**
     * GUI thread responsible of starting the main stage of a match player using the GUI over RMI.
     */
    private Runnable gui;

    /**
     * True if the player wants to use the  GUI.
     */
    private boolean isGui = false;

    /**
     * True if the player has answered an UpdateWithAnswer invocation.
     */
    private boolean answered = false;


    /**
     * Starts the rmiClient.
     */
    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("hai avviato una connessione RMI");

        connect(LOGINRMIPORT);

        while (!logged) {

            try {

                System.out.println("inserisci username: ");
                user = scanner.nextLine();
                System.out.println("Inserisci psw:");
                psw = scanner.nextLine();


                try {

                    value = Logger.verify(user, psw);

                    if (value != -1) {
                        localPort = Logger.getGamePort(user);
                        System.out.println("La mia porta di gioco è " + localPort);
                        logged = true;
                    } else {

                        System.out.println("Errore durante il login, digita di nuovo le tue credenziali.");

                    }

                } catch (NullPointerException e) {

                    System.out.println("Null Pointer su Login.verify");

                } catch (NoSuchObjectException e) {

                    e.printStackTrace();

                    System.err.println("nosuch");
                }


            } catch (Exception e) {

                System.out.println("Ritento la connessione");
            }
        }

        initLocalRegistry(user);

        connected = false;

        connect(RMIPORT);

        try {
            Game.sendMsg("Connesso");
        } catch (Exception e) {
            e.printStackTrace();
        }


        while (!answered) {
            askGui();
        }

        if (isGui) {

            gui = new GUI();

            GUI.RMI = true;
            Thread executingGui = new Thread(gui);
            executingGui.start();

        }

    }

    /**
     * Manages the connection routine to a server. Finds the remote registry and stores its location.
     *
     * @param port is the port where to look for a remote registry.
     */

    private synchronized void connect(int port) {

        while (!connected) {

            if (port == LOGINRMIPORT) {

                try {

                    Registry registry = LocateRegistry.getRegistry(host, port);
                    rServer = (RMILoggerInterface) registry.lookup("LoginRMI");

                    if (rServer != null) {
                        Logger = rServer;
                        connected = true;
                    }

                } catch (Exception e) {

                    System.out.println("Ritento la connessione");
                    e.printStackTrace();
                }

            } else if (port == RMIPORT) {

                try {

                    Registry registry = LocateRegistry.getRegistry(host, port);
                    gServer = (RMIServerInterface) registry.lookup("GameRMI");

                    if (gServer != null) {
                        Game = gServer;
                        Game.addPlayerHN(user, InetAddress.getLocalHost().getHostName());
                        connected = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * Starts automatically the local registry of a player when granted access to a game server. This happens to grant
     * dual-channel comunication between client and server.
     *
     * @param User is the player that has been logged into a game server.
     */
    private void initLocalRegistry(String User) {

        try {

            String pathfile = System.getProperty("user.home") + "\\Desktop";

            File file = new File(pathfile + "\\rmiClient_" + user + ".bat");

            FileWriter fw = new FileWriter(file, true);

            fw.write("javaw rmiregistry " + localPort + "\n");

            fw.close();

            pathfile = pathfile + "\\rmiClient_" + user + ".bat";

            Runtime.getRuntime().exec("cmd /c start cmd.exe /k" + pathfile);  //TODO testare


            try {
                sleep(2 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject.exportObject(this, localPort);
            Registry registry = LocateRegistry.createRegistry(localPort);
            registry.rebind(User, stub);   //assegno il nome del player al registry locale


        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    /**
     * Remote method used by UpdateWithAnswer. When invoked, prints a message and waits for an answer.
     * @param msg is the message coming from the server.
     * @return the answer of the player.
     */
    @Override
    public String sendMsgWithAnswer(String msg) {

        GUI.answeredRMI = false;
        GUI.RMIAnswer = "null";

        if (isGui) {

            System.out.println(msg);
            Platform.runLater(() -> ((GUI) gui).update(msg));

            while (!GUI.answeredRMI) {
                try {
                    sleep(200);
                    continue;
                } catch (Exception e) {
                }
            }

            return (GUI.RMIAnswer);
        } else {

            Scanner scanner = new Scanner(System.in);
            if (msg.contains("$")) {
                String[] tokens = msg.split("\\$");
                System.out.println(tokens[0]);

            } else {
                System.out.println(msg);
            }

            return scanner.nextLine();
        }

    }

    /**
     * This method is invoked from the server. Used for printing simple messages without the need of an answer.
     * @param msg is the message from the server.
     */
    @Override
    public void sendMsg(String msg) {


        if (isGui) {

            System.out.println(msg);
            Platform.runLater(() -> ((GUI) gui).update(msg));
        } else {

            if (msg.contains("$")) {
                String[] tokens = msg.split("\\$");
                System.out.println(tokens[0]);
            } else {
                System.out.println(msg);
            }
        }


    }


    /**
     * Asks a player if he wants to use the GUI during the match.
     */
    private synchronized void askGui() {

        System.out.println("Vuoi usare la gui? Si/No");

        Scanner scanner = new Scanner(System.in);

        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("si") || answer.equalsIgnoreCase("no")) {

            if (answer.equalsIgnoreCase("si")) {

                isGui = true;
                answered = true;

            } else {

                isGui = false;
                answered = true;

            }

        } else {

            System.out.println("Digita la risposta corretta");

        }
    }


}
