package it.polimi.se2019.Network.RMI.Server;

import it.polimi.se2019.Network.RMI.Client.RMIClientInterface;
import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.Server;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class extends the main server class and is responsible of managing all the interaction with all the rmi clients connected to the game.
 */

public class RMIServer extends Server implements RMIServerInterface, Runnable {


    /**
     * Starts the rmi server side.
     */
    @Override
    public void run() {

        try { //avvio il server registry

            RMIServer server = new RMIServer();
            String nome = "GameRMI";
            Registry registry = LocateRegistry.createRegistry(RMIPORT);
            RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(server, RMIPORT);
            registry.rebind(nome, stub);
            System.out.println("Server online listening on port " + RMIPORT);


        } catch (Exception e) {
            System.err.println("Errore di init registry!");
            e.printStackTrace();

        }
    }

    /**
     * Remote method to be used by the clients. Sends a simple message from the client to the server.
     * @param msg is the message that the server will recive.
     */
    @Override
    public void sendMsg(String msg) {

        System.out.println(msg);

    }

    /**
     * Invokes the login routine from the clients.
     * @param u is the player's username.
     * @param p is the player's password.
     * @return true if the player can log in.
     */
    @Override
    @Deprecated
    public synchronized boolean logIn(String u, String p) {

        RMILogger logger = new RMILogger();
        return true;

    }


    /**
     * Adds the player's pc hostname to the database used to store all the clients of a match.
     * @param userName is the player's nickname.
     * @param hostname is the player's pc's hostname.
     */
    @Override
    public synchronized void addPlayerHN(String userName, String hostname) {

        playerClient.put(userName, hostname);


    }

    /**
     * Updates the client with GUI reresh.
     * @param status is the message that refersh the gui
     * @return the new status.
     */
    @Override
    public synchronized String guiUpdate(String status) {

        return status;

    }


}
