package it.polimi.se2019.Network.RMI.Client;

import it.polimi.se2019.Network.RMI.Server.RMIServerInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import static it.polimi.se2019.Network.Server.RMIPORT;

public class RMIClient extends Thread {

    RMIServerInterface Server;
    boolean connected = false;

    @Override
    public void run() {

        System.out.println("hai avviato una connessione RMI");

        Server = connect();

        while (true) {

            Scanner scanner = new Scanner(System.in);

            try {

                Server.sendMsg("Clientrmi RMI: " + scanner.nextLine());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private RMIServerInterface connect() {

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
