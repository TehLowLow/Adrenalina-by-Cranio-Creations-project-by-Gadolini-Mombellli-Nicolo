package it.polimi.se2019.Network.RMI.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import static it.polimi.se2019.Network.Server.RMIPORT;

public class RMIServer extends Thread implements RMIServerInterface {


    @Override
    public void run() {


        try { //avvio il server registry

            RMIServer server = new RMIServer();
            String nome = "Server";
            Registry registry = LocateRegistry.createRegistry(RMIPORT);
            RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(server, RMIPORT);
            registry.rebind(nome, stub);
            System.out.println("Server online listening on port " + RMIPORT);


        } catch (Exception e) {
            System.err.println("Errore di init registry!");
            e.printStackTrace();

        }

    }


    public void sendMsg(String msg) throws RemoteException {

        System.out.println(msg);


    }


}
