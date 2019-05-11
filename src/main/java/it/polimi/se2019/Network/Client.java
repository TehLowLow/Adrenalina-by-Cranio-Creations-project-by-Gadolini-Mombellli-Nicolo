package it.polimi.se2019.Network;

import it.polimi.se2019.Network.RMI.Client.RMIClient;
import it.polimi.se2019.Network.Socket.Client.SocketClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.Scanner;

public class Client {

    private static String response;
    static SocketClient sClient = new SocketClient();
    static RMIClient rClient = new RMIClient();
    private static volatile int myPort = 4000;

    public static void main(String[] args) {


        //Un player che a inizio partita si connette può solo riconnettersi con la prima tecnologia scelta.
        System.out.println("Scegli come connetterti al server. Digita 'Socket' o 'RMI':  ");
        initConnection();//qua vengono lanciati i thread.
    }

//----------------------------------------------------------------------------------------------------

    private static String input() {

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

//----------------------------------------------------------------------------------------------------

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

           /* Runnable clientRMI = new RMIClient();
            Thread rClient = new Thread(clientRMI);
            rClient.start();*/

                System.out.println("Il servizio richiesto è momentaneamente non disponibile, utilizza la connessione socket");


            } else {

                //stampa la stringa di errore.
                System.err.println("input invalido, riscrivi attentamente che connessione desideri");

            }
        }
    }
//----------------------------------------------------------------------------------------------------


}




