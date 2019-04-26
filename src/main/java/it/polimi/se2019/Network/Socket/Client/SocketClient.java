package it.polimi.se2019.Network.Socket.Client;

import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

import static it.polimi.se2019.Network.Server.SOCKETPORT;

public class SocketClient extends Thread {


    private boolean connected = false;

    Socket client;

    @Override
    public void run() {

        System.out.println("hai avviato una connessione socket");

        connect();

        try {

            while (true) {

                Scanner scanner = new Scanner(System.in);

                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                out.writeUTF("Client Socket: " + scanner.nextLine());

            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }


    private void connect() {

        while (!connected) {

            try {

                client = new Socket("localhost", 4000);
                connected = true;

            } catch (ConnectException e) {

                System.err.println("ritento la connessione");

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}