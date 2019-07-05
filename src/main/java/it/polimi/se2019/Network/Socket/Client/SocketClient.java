package it.polimi.se2019.Network.Socket.Client;

import it.polimi.se2019.Network.Client;
import it.polimi.se2019.View.GUI.GUI;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static it.polimi.se2019.Network.Server.*;
import static java.lang.Thread.sleep;

/**
 * This class extends the client with all the functionalities that are needed to handle a Socket connection to the game server.
 */

public class SocketClient extends Client implements Runnable {


    /**
     * True if a player is granted access to a server
     */
    private boolean connected = false;
    /**
     * Stores the socket onto which the player can listen for and send messages.
     */
    private static Socket client;

    /**
     * Used for distinction between the various updates from the server.
     */
    private int signal;

    /**
     * Thread that starts the gui
     */
    private Runnable gui;

    /**
     * The name of the player.
     */
    private String user;

    /**
     * The outputstream that writes onto the socket.
     */
    private static DataOutputStream out;

    /**
     * The inputstream where the client listen for an update.
     */
    private static DataInputStream in;

    /**
     * True if the palyer wants to use the GUI.
     */
    private boolean useGui = false;


    /**
     * After the player chooses the socket connection, this method starts the login routine and then manages all the communications.
     */
    @Override
    public void run() {

        System.out.println("Hai avviato una connessione Socket. \n");

        connectLogger();

        try {

            Scanner scanner = new Scanner(System.in);

            streamInit();

            System.out.println(in.readUTF());
            user = scanner.nextLine();
            out.writeUTF(user);   //mando user
            System.out.println(in.readUTF());   //inserisci psw
            out.writeUTF(scanner.nextLine());   //mando psw
            System.out.println(in.readUTF());   //"aggiunto user"
            /*localPort = in.readInt();           //Porta locale per il nuovo login.
            System.out.println(localPort);
            gamePort = in.readInt();            //Porta del game server.
            System.out.println(gamePort);*/


        } catch (Exception e) {
            e.printStackTrace();
        }

        connectGame();

        streamInit();

        askGui();

        try {
            out.writeUTF(user);
            out.writeInt(client.getLocalPort());
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (useGui) {

            gui = new GUI();  //TODO spostare nell if;

            JFXPanel panel = new JFXPanel();

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

                    out.flush();
                    out.writeInt(signal); //Echo al server

                    String received = in.readUTF();


                    if (useGui) {
                        System.out.println(received);
                        Platform.runLater(() -> ((GUI) gui).update(received));
                    } else {

                        //Se devo stampare la stringa che contiene anche le info per la GUI...
                        if (received.contains("$")) {
                            String[] tokens = received.split("\\$");
                            System.out.println(tokens[0]);
                        } else {
                            System.out.println(received);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (signal == 2) {//updatewithanswer

                try {

                    out.flush();
                    out.writeInt(signal);
                    String received = in.readUTF();

                    if (useGui) {

                        System.out.println(received);
                        Platform.runLater(() -> ((GUI) gui).update(received));

                    } else {

                        //Nel caso in cui ci sia la stringa da parsare con la mappa...
                        if (received.contains("$")) {
                            String[] tokens = received.split("\\$");
                            System.out.println(tokens[0]);
                            Scanner scanner = new Scanner(System.in);
                            out.writeUTF(scanner.nextLine());
                        } else {
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

    /**
     * Connects to the login server.
     */
    private synchronized void connectLogger() {

        //Il ciclo while evita che il client crashi perchè la connessione è non disponibile.
        while (!connected) {

            try {

                client = new Socket(host, LOGINSOCKETPORT); //"localhost"-------> IP del server remoto per login LAN
                connected = true;

            } catch (ConnectException e) {

                System.err.println("ritento la connessione");

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }


    /**
     * Connects to the game server.
     */
    private synchronized void connectGame() {

        connected = false; //Test per resettare connected, da decidere se inserirlo da qualche altra parte.

        while (!connected) {

            try {
                //InetAddress gameserver = InetAddress.getLocalHost();
                client = new Socket(host, SOCKETPORT); // "192.168.0.202" ----> IP del server remoto per game LAN
                connected = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts the streams connected to the client socket.
     */
    private static void streamInit() {

        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Asks the player if he wants to use the gui.
     */
    private void askGui() {

        System.out.println("Vuoi usare la GUI?");
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        boolean answered = false;

        while (!answered) {

            answer = scanner.nextLine();

            if (answer.equals("si")) {
                answered = true;
                useGui = true;
            }

            if (answer.equals("no")) {
                answered = true;
            }


        }


    }
}

