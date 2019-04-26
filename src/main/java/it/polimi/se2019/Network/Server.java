package it.polimi.se2019.Network;

import it.polimi.se2019.Network.RMI.Server.RMIServer;
import it.polimi.se2019.Network.Socket.Server.SocketServer;

import java.net.ServerSocket;


public class Server {

    public static final int RMIPORT = 2100;
    public static final int SOCKETPORT = 2200;
    public static ServerSocket sSocket;


    public static void main(String[] args) {


        //Lancia il thread per avviare l RMI registry

        RMIServer rServer = new RMIServer();
        rServer.start();

        //Lancia il thread per ascoltare su più porte socket

        try {
            sSocket = new ServerSocket(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {

            SocketServer sServer = new SocketServer();
            sServer.start();

        }
        //avvia un login

    }


}
