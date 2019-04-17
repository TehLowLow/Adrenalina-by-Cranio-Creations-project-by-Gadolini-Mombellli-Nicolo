package it.polimi.se2019.Socket.Server;

import java.net.*;
import java.io.*;


public class TServer extends Thread {


    private ServerSocket sSocket;


    public void run() {

        try {

            sSocket = new ServerSocket(9999);

        } catch (Exception e) {
            System.err.println("EI");
        }


        while (true) {

            try {

                System.out.println("Attendo i client");

                Socket door = sSocket.accept();

                DataInputStream in = new DataInputStream(door.getInputStream());

                System.out.println(in.readUTF());

                DataOutputStream out = new DataOutputStream(door.getOutputStream());
                out.writeUTF("Ciao Client, sono il server e ti ho beccato!");

                sSocket.close();
                sSocket = new ServerSocket(9999);

            } catch (IOException e) {

                break;

            }

        }


    }

}

