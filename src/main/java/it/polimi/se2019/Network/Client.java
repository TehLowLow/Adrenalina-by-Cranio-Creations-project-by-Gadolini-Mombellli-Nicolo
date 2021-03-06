package it.polimi.se2019.Network;

import it.polimi.se2019.Network.RMI.Client.RMIClient;
import it.polimi.se2019.Network.Socket.Client.SocketClient;

import java.util.Scanner;


/**
 * Is the player side of the game, interacting with the player, establishing a connection of choice and managing game I/O.
 */


public class Client {

    /**
     * Is the name of the pc of the player.
     */

    public static String host;


    /**
     * Starts the client side of the connection and asks the server for a port to connect to after the login of the player.
     */

    public static void main(String args[]) {


        //Un player che a inizio partita si connette può solo riconnettersi con la prima tecnologia scelta.

        System.out.println("Inserisci il nome del Pc Host sul quale è in esecuzione il server:");
        Scanner scanner = new Scanner(System.in);
        host = scanner.nextLine();



        System.out.println("Scegli come connetterti al server. Digita Socket o RMI:  ");
        initConnection();//qua vengono lanciati i thread.
    }

//----------------------------------------------------------------------------------------------------

    /**
     * Asks the player for the desired connection, and parses the response, starting the appropriate client side of the
     * tcp connection.
     */

    private static void initConnection() {

        boolean connected = false;

        Scanner scanner = new Scanner(System.in);

        while (!connected) {

            String string = scanner.nextLine();

            if (string.equalsIgnoreCase("socket")) {


                //avvia una connessione socket

                connected = true;

                Runnable clientSocket = new SocketClient();
                Thread sClient = new Thread(clientSocket);
                sClient.start();


            } else if (string.equalsIgnoreCase("RMI")) {


                //avvia una connessione RMI

                connected= true;

                Runnable clientRMI = new RMIClient();
                Thread rClient = new Thread(clientRMI);
                rClient.start();

            } else {

                //stampa la stringa di errore.
                System.err.println("Input invalido, riscrivi attentamente che connessione desideri");

            }
        }
    }
//----------------------------------------------------------------------------------------------------


}




