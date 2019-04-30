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

        System.out.println("Scegli come connetterti al server. Digita 'Socket' o 'RMI':  ");
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


}




