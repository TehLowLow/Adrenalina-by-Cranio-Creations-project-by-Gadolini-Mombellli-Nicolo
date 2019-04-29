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

    public static void main(String[] args) {

        System.out.println("Scegli come connetterti al server. Digita 'socket' o 'RMI':  ");
        response = input();
        initConnection(response);//qua vengono lanciati i thread.

    }

//----------------------------------------------------------------------------------------------------

    private static String input() {

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

//----------------------------------------------------------------------------------------------------

    private static void initConnection(String string) {


        if (string.equalsIgnoreCase("socket")) {

            //avvia una connessione socket
            Runnable clientSocket = new SocketClient();
            Thread sClient = new Thread(clientSocket);
            sClient.start();

        } else if (string.equalsIgnoreCase("RMI")) {

            //avvia una connessione RMI
            Runnable clientRMI = new RMIClient();
            Thread rClient = new Thread(clientRMI);
            rClient.start();

        } else {

            //stampa la stringa di errore.
            System.err.println("input invalido, riscrivi attentamente che connessione desideri");

        }
    }
//----------------------------------------------------------------------------------------------------


    //TODO: è per forza necessario incapsulare i messaggi socket in un oggetto, perchè devo contenere da qualche parte l' informazione
    //      "è un messaggio da stampare a schermo", "è un messaggio a cui serve una risposta", "è un messaggio di servizio (ping, aggiornamentti o
    //      roba in background)"
}




