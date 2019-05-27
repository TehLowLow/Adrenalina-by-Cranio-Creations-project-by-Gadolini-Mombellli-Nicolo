package it.polimi.se2019.Network;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Network.RMI.Client.RMIClient;
import it.polimi.se2019.Network.Socket.Client.SocketClient;
import it.polimi.se2019.View.GUI.GUI;
import it.polimi.se2019.View.Message;

import java.util.Scanner;


/**
 * Is the player side of the game, interacting with the player, establishing a connection of choice and managing game I/O.
 */


public class Client {

    private static String response;
    static SocketClient sClient = new SocketClient();
    static RMIClient rClient = new RMIClient();



    /**
     * Starts the client side of the connection and asks the server for a port to connect to after the login of the player.
     */

    public static void main(String args[]) {

        boolean isGui = false;

        System.out.println("Vuoi giocare con la CLI?");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        while(!InputCheck.correctYesNo(answer)){

            System.out.println(Message.inputError());
            System.out.println("Vuoi giocare con la CLI?");
            answer = scanner.nextLine();

        }

        if (!InputCheck.yesOrNo(answer)){

            isGui = true;

        }

        if (isGui){

            GUI gui = new GUI();
            gui.main(null);


        }


        //Un player che a inizio partita si connette può solo riconnettersi con la prima tecnologia scelta.
        System.out.println("Scegli come connetterti al server. Digita 'Socket' o 'RMI':  ");
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




