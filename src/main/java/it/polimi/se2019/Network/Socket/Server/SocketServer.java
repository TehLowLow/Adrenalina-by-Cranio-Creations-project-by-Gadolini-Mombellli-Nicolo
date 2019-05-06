package it.polimi.se2019.Network.Socket.Server;

import it.polimi.se2019.Network.Logger;
import it.polimi.se2019.Network.Server;

import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer extends Server implements Runnable {

    private ServerSocket gameSocket;


    public SocketServer(ServerSocket game){

        this.gameSocket = game;

    }




    @Override
    public void run() {

        //Deve gestire solamente le connessioni dopo la lobby, quindi per prima cosa genera una fixed pool di thread, e li passa ai player


    }

}
