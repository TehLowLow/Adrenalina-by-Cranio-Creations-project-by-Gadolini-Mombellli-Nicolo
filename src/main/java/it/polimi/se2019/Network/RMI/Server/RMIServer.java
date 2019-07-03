package it.polimi.se2019.Network.RMI.Server;

import it.polimi.se2019.Network.RMI.Client.RMIClientInterface;
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

    @Override
    public void sendMsg(String msg) {

        System.out.println(msg);

    }

    @Override
    public synchronized boolean logIn(String u, String p) {

        RMILogger logger = new RMILogger();
        return true;

    }


    @Override
    public synchronized void addPlayerHN(String userName, String hostname) {

        playerClient.put(userName, hostname);

        System.out.println("test");

    }

    @Override
    public synchronized String guiUpdate(String status) {

        return status;

    }

    //Devo chiamarla per mettere in playerclient ogni nome e host dei vari player, cosi da poter avere tutto per cercare i registry
    //Una volta presi, chiama il registry e chiama i metodi remoti.
    //Una volta fatto cio gli update funzionano e possiamo giocare tutto con cli


    //NB: avviare sempre tutti i registry, come dice luca creare un batch che fa partire le porte necessarie.
    //Inoltre pensare a come fare per la presentazione, perche se apro le porte socket e poi ci apro sopra rmi potrebbe dare
    //errore.
}
