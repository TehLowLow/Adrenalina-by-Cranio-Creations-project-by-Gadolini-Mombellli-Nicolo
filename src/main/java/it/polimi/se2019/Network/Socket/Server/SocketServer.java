package it.polimi.se2019.Network.Socket.Server;

import it.polimi.se2019.Network.Logger;
import it.polimi.se2019.Network.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class SocketServer extends Server implements Runnable {

    ServerSocket mySocket;
    Socket gamerChannel;
    DataInputStream in;
    DataOutputStream out;

    public SocketServer(ServerSocket s) {

        this.mySocket = s;
    }


    @Override

    public void run() {

        try {
            gamerChannel = mySocket.accept(); //da salvare per creare una sorta di playerclient hash
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            in = new DataInputStream(gamerChannel.getInputStream());
            out = new DataOutputStream((gamerChannel.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.out.println(in.readUTF());
            out.writeUTF("Eccomi");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
