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


public class RMIClient extends Client implements Runnable, RMIClientInterface, Remote {

    static RMILoggerInterface Logger;
    public static RMILoggerInterface rServer;
    public static RMIServerInterface Game;
    public static RMIServerInterface gServer;

    private boolean connected = false;
    private boolean logged = false;
    private String user;
    private String psw;
    private int localPort;
    private int value;
    private Runnable gui;
    private boolean isGui = false;
    private boolean answered = false;


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
                    }else{

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
