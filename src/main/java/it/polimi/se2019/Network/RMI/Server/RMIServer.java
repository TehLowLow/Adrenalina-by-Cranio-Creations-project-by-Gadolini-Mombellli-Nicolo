package it.polimi.se2019.Network.RMI.Server;

import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.Server;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class RMIServer extends Server implements RMIServerInterface, Runnable {


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


    public void sendMsg(String msg){

        System.out.println(msg);

    }


    public synchronized boolean logIn(String u, String p) {

        RMILogger logger = new RMILogger();
        return true;

    }


}
