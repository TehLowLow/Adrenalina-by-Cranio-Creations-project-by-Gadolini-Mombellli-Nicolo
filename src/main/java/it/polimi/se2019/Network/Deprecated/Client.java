package it.polimi.se2019.Network.Deprecated;

import java.net.*;
import java.io.*;

public class Client {


    public static void main(String[] args) {


        String servName = "test server";
        int port = 9999;

        try {

            Socket cSocket = new Socket("localhost", 9999);

            OutputStream toServer = cSocket.getOutputStream();
            DataOutputStream out = new DataOutputStream(toServer);
            out.writeUTF("Bella raga sono il client");

            InputStream fromServer = cSocket.getInputStream();
            DataInputStream in = new DataInputStream(fromServer);

            System.out.println(in.readUTF());


        } catch (Exception e) {


        }


    }

}
