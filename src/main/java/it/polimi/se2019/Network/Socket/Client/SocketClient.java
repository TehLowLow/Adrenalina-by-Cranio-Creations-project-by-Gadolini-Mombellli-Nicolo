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

    private Socket client;
    private int gamePort;
    private int localPort;
    private byte address[] = {127, 0, 0, 1};

    private static DataOutputStream out;
    private static DataInputStream in;


    @Override
    public void run() {

        System.out.println("hai avviato una connessione socket");

        connectLogger();

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

        //Inserire qua ascii code per resettare la cli

        connectGame();  //Risolvere il problema di connettersi con la propria porta statica

        streamInit();

        try {
            out.writeUTF("Connesso alla partita, fai partire sto timer");
            System.out.println(in.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private synchronized void connectLogger() {


        //Il ciclo while evita che il client crashi perchè la connessione è non disponibile.
        while (!connected) {

            try {

                client = new Socket("localhost", LOGINSOCKETPORT);
                connected = true;

            } catch (ConnectException e) {

                System.err.println("ritento la connessione");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private synchronized void connectGame() {

        connected = false; //Test per resettare connected, da decidere se inserirlo da qualche altra parte.

        while (!connected) {

            try {
                InetAddress gameserver = InetAddress.getByAddress(address);
                client = new Socket("localhost", SOCKETPORT, gameserver, localPort);
                connected = true;
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