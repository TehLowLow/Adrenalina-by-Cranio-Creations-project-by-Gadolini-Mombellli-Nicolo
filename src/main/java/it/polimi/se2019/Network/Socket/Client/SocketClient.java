package it.polimi.se2019.Network.Socket.Client;

import it.polimi.se2019.Network.Client;
import it.polimi.se2019.View.GUI.GUI;
import javafx.application.Platform;

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
    private byte[] address ={127,0,0,1} ;//{127, 0, 0, 1};
    private int signal;


    private static DataOutputStream out;
    private static DataInputStream in;

    private boolean useGui = false;


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

        askGui();
        Runnable gui= new GUI();

        if(useGui) {

            GUI.out = out;
            Thread executingGui = new Thread(gui);
            executingGui.start();
        }

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

                    out.writeInt(signal); //Echo al server
                    String received = in.readUTF();

                    if(useGui){
                        System.out.println(received);
                        Platform.runLater(()->((GUI) gui).update(received));
                    }

                    else {

                        //Se devo stampare la stringa che contiene anche le info per la GUI...
                        if(received.contains("$")){
                            String[] tokens = received.split("\\$");
                            System.out.println(tokens[0]);
                        }
                        else{
                            System.out.println(received);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (signal == 2) {//updatewithanswer

                try {
                    out.writeInt(signal);
                    String received = in.readUTF();

                    if(useGui) {

                        System.out.println(received);
                        Platform.runLater(() -> ((GUI) gui).update(received));
                    }
                    else {

                        //Nel caso in cui ci sia la stringa da parsare con la mappa...
                        if(received.contains("\\$")){

                            String[] tokens = received.split("\\$");
                            System.out.println(tokens[0]);
                            Scanner scanner = new Scanner(System.in);
                            out.writeUTF(scanner.nextLine());
                        }

                        else {
                            System.out.println(received);
                            Scanner scanner = new Scanner(System.in);
                            out.writeUTF(scanner.nextLine());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (signal == 200) {

                try {
                    out.writeUTF("200 OK");
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

                client = new Socket("Lollo-PC", LOGINSOCKETPORT); //"localhost"-------> IP del server remoto per login LAN
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
                InetAddress gameserver = InetAddress.getLocalHost();
                client = new Socket("Lollo-PC", SOCKETPORT, gameserver, localPort); // "192.168.0.202" ----> IP del server remoto per game LAN
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

    private void askGui(){

        System.out.println("Vuoi usare la GUI?");
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        boolean answered = false;

        while(!answered){

            answer = scanner.nextLine();

            if(answer.equals("si")){
                answered = true;
                useGui = true;
            }

            if(answer.equals("no")){
                answered = true;
            }


        }



    }
}

//Per connettersi il codice funziona, serve solo controllare che il pc abbia:
//1) la scheda di rete connessa alla lan come primaria, e si nota debuggando connect game, se il valore che becca il getlocalhost non combacia sistemare
//2) impostando la rete come privata windows setta il pc come visibile nella lan
//3) l' hostname sostituisce l' ip, meglio come soluzione dinamica.
//4) molti problemi spariscono con del buon tinkering nel pannello di rete e di schede di rete.