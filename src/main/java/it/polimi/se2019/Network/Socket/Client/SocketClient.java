package it.polimi.se2019.Network.Socket.Client;

import it.polimi.se2019.Network.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static it.polimi.se2019.Network.Server.*;


public class SocketClient extends Client implements Runnable {


    private boolean connected = false;

    Socket client;
    int gamePort;
    int localPort;

    private static DataOutputStream out;
    private static DataInputStream in;


    @Override
    public void run() {

        System.out.println("hai avviato una connessione socket");

        connect(LOGINSOCKETPORT);

        try {

            Scanner scanner = new Scanner(System.in);

            streamInit();

            System.out.println(in.readUTF());   //inserisci user
            out.writeUTF(scanner.nextLine());   //mando user
            System.out.println(in.readUTF());   //inserisci psw
            out.writeUTF(scanner.nextLine());   //mando psw
            System.out.println(in.readUTF());   //"aggiunto user"
            localPort = in.readInt();           //Porta locale per il nuovo login.
            System.out.println(localPort);
            gamePort = in.readInt();            //Porta del game server.
            System.out.println(gamePort);


        } catch (Exception e) {
            e.printStackTrace();
        }



        connect(gamePort);  //Risolvere il problema di connettersi con la propria porta statica

        streamInit();
    }


    private void connect(int serverport) {


        //Il ciclo while evita che il client crashi perchè la connessione è non disponibile.
        while (!connected) {

            try {

                client = new Socket("localhost", serverport);
                connected = true;

            } catch (ConnectException e) {

                System.err.println("ritento la connessione");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void streamInit() {

        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}