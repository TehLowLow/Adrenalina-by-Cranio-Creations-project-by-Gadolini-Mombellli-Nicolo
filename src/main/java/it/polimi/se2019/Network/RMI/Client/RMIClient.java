package it.polimi.se2019.Network.RMI.Client;


import it.polimi.se2019.Network.Client;
import it.polimi.se2019.Network.RMI.RMILoggerInterface;
import it.polimi.se2019.Network.RMI.Server.RMIServerInterface;
import it.polimi.se2019.View.Parser;

import static it.polimi.se2019.Network.Server.LOGINRMIPORT;
import static it.polimi.se2019.Network.Server.RMIPORT;

import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class RMIClient extends Client implements Runnable, RMIClientInterface, Remote {

    private static volatile RMILoggerInterface Logger;
    private static volatile RMILoggerInterface rServer;
    private static volatile RMIServerInterface Game;
    private static volatile RMIServerInterface gServer;

    private boolean connected = false;
    private boolean logged = false;
    private String user;
    private String psw;
    private int localPort;
    private int value;


    @Override
    public void run() {

        System.out.println("hai avviato una connessione RMI");

        connect(LOGINRMIPORT);

        while (!logged) {

            try {

                Scanner scanner = new Scanner(System.in);

                System.out.println("inserisci username");
                user = scanner.nextLine();
                System.out.println("Inserisci psw");
                psw = scanner.nextLine();


                try {

                    value = Logger.verify(user, psw);

                    if (value != -1) {
                        localPort = Logger.getGamePort(user);
                        System.out.println("la mia porta di gioco è " + localPort);
                        logged = true;
                    }

                } catch (NullPointerException e) {

                    System.out.println("Null Pointer su Login.verify");

                }

                catch(NoSuchObjectException e){

                    e.printStackTrace();

                }


            } catch (Exception e) {
                //
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

        //Metodo per avviare il callback da parte del server
    }


    private synchronized void connect(int port) {

        while (!connected) {

            if (port == LOGINRMIPORT) {

                try {

                    Registry registry = LocateRegistry.getRegistry(port);
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

                    Registry registry = LocateRegistry.getRegistry(port);
                    gServer = (RMIServerInterface) registry.lookup("GameRMI");

                    if (gServer != null) {
                        Game = gServer;
                        Game.sendMsg("bella");
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

            RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject.exportObject(this, localPort);
            Registry registry = LocateRegistry.createRegistry(localPort);
            registry.rebind(User, stub);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public String sendMsgWithAnswer(String msg) {

        System.out.println(msg);

        Parser parser = new Parser();
        return Parser.parse();
    }

    public void sendMsg(String msg) {

        System.out.println(msg);

    }

}
