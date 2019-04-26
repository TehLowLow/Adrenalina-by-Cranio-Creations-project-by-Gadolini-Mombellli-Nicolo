package it.polimi.se2019.Network.Socket.Server;

import it.polimi.se2019.Network.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static it.polimi.se2019.Network.Server.SOCKETPORT;
import static it.polimi.se2019.Network.Server.sSocket;

public class SocketServer extends Thread {


    @Override
    public void run() {

        try {


            Socket server = sSocket.accept();

            System.out.println("Thread ready on port 2200 and listening");

            while(true){

                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println(in.readUTF());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
