package it.polimi.se2019.Network.RMI.Client;

import it.polimi.se2019.Network.Client;
import it.polimi.se2019.Network.RMI.Server.RMIServerInterface;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


import static com.sun.javaws.JnlpxArgs.verify;
import static it.polimi.se2019.Network.Server.RMIPORT;

public class RMIClient extends Client implements Runnable {

    RMIServerInterface Server;
    boolean connected = false;
    boolean logged = false;
    String User;
    String psw;

    @Override
    public void run() {

        System.out.println("hai avviato una connessione RMI");

        Server = connect();

        while (!logged) {

            verify();

        }


            while (true) {

                Scanner scanner = new Scanner(System.in);
                try {

                    Server.sendMsg("Clientrmi RMI: " + scanner.nextLine());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


        private RMIServerInterface connect () {

            while (!connected) {

                try {
                    Registry registry = LocateRegistry.getRegistry(RMIPORT);
                    RMIServerInterface rServer = (RMIServerInterface) registry.lookup("Server");

                    connected = true;

                    return rServer;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;

        }

    }
