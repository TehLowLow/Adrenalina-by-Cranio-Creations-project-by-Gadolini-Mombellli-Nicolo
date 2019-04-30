package it.polimi.se2019.Network.Socket.Client;

import it.polimi.se2019.Network.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class SocketClient extends Client implements Runnable {


    private boolean connected = false;

    Socket client;

    @Override
    public void run() {

        System.out.println("hai avviato una connessione socket");

        connect();

        try {

            //Normale interazione Socket con input e output stream.

            while (true) {

                Scanner scanner = new Scanner(System.in);

                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                DataInputStream in = new DataInputStream(client.getInputStream());

                System.out.println(in.readUTF());
                out.writeUTF(scanner.nextLine());

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    private void connect() {


        //Il ciclo while evita che il client crashi perchè la connessione è non disponibile.
        while (!connected) {

            try {

                client = new Socket("localhost", 9999);
                connected = true;

            } catch (ConnectException e) {

                System.err.println("ritento la connessione");

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}