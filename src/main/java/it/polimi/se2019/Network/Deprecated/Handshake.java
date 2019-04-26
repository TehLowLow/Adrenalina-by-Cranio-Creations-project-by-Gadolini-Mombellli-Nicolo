package it.polimi.se2019.Network.Deprecated;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static it.polimi.se2019.Network.Deprecated.Clientrmi.clientPort;
import static it.polimi.se2019.Network.Deprecated.Clientrmi.nickname;

public class Handshake extends Thread {


    public void run() {

        handShake(nickname, clientPort);

    }

    private void handShake(String nick, int clientPort) {


        try {
            Clientrmi client = new Clientrmi();
            ClientInterface stub = (ClientInterface) UnicastRemoteObject.exportObject(client, clientPort);
            Registry registry = LocateRegistry.createRegistry(clientPort);
            registry.rebind(nick, stub);
            System.out.println("Server di " + nickname + " attivo");
        } catch (Exception e) {
            System.err.println("Errore di handshake");

        }


    }
}