package it.polimi.se2019.Network.Socket.Client;

import it.polimi.se2019.Model.Board;
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

    private static Socket client;
    private int gamePort;
    private int localPort;
    private byte[] address = {127, 0, 0, 1};
    private int signal;


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

        connectGame();

        streamInit();

        while (true) {

            //I messaggi sulla rete son di due tipi, quelli per cui un utente non deve rispondere e quelli
            //che invece necessitano di interazione col server.
            //Il primo tipo di messaggio ha codice "1", il secondo ha codice "2".
            //Il server invia il codice e attende un echo dal client, ricevuto l' echo e verificato che sia il channel
            //corretto, inizia a inviare i messaggi.


            try {
                signal = in.readInt();
            } catch (Exception e) {
                continue;
            }

            if (signal == 1) {

                try {//update

                    out.writeInt(signal);
                    System.out.println(in.readUTF());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (signal == 2) {//updatewithanswer

                try {
                    out.writeInt(signal);
                    System.out.println(in.readUTF());
                    Scanner scanner = new Scanner(System.in);
                    out.writeUTF(scanner.nextLine());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

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


    private static void streamInit() {

        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}