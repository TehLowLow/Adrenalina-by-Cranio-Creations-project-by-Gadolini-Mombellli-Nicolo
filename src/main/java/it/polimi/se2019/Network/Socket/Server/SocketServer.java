package it.polimi.se2019.Network.Socket.Server;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Logger;
import it.polimi.se2019.Network.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer extends Server implements Runnable {

    private ServerSocket mySocket;
    private Socket gamerChannel;
    private DataInputStream in;
    private DataOutputStream out;

    public SocketServer(ServerSocket s) {

        this.mySocket = s;
    }


    @Override

    public void run() {

        try {
            gamerChannel = mySocket.accept();
            playerClient.put(gamerChannel.getPort(), gamerChannel); //lego numero di porta e socket.
            update(connectedPlayers.get(0), "ciao man, testo update");   //TODO Attenzione al multithreading
            update(connectedPlayers.get(0), "Servizio di testing, attendere...");

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
