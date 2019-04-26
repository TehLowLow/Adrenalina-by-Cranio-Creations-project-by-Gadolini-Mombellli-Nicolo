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

        //Tutto questo avviene una volta che il server è avviato, percui devo gestire la storia di non far crashare il client
        //se nessun server è attivo.
        //Per convenzione se la stringa viene accettata non permetto di cambiarla, percui initConnection prova a connettersi
        //su quella data tecnologia fino a quando non ci riesce.
        //Il server in caso di lobby al completo (con 5 player) ammette il login ma lo termina da remoto.

        if (string.equalsIgnoreCase("socket")) {

            //avvia una connessione socket
            sClient.start();


        } else if (string.equalsIgnoreCase("RMI")) {

            //avvia una connessione RMI
            rClient.start();


        } else {

            //stampa la stringa di errore.
            System.err.println("input invalido, riscrivi attentamente che connessione desideri");

        }


    }
//----------------------------------------------------------------------------------------------------


}




