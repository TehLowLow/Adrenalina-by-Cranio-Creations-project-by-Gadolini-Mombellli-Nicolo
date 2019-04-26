package it.polimi.se2019.Network.Deprecated;

import it.polimi.se2019.View.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * This class is the main client class that interacts with the network and the server.
 * This class on startup builds a connection with the server, then logs in the user, and prints the messages that the server sends.
 */


public class Clientrmi extends Thread implements ClientInterface {

    private View view;
    private static final int serverPort = 2000;
    static int clientPort;
    static String nickname;
    private static String pass;
    private ServerInterface Server;
    private String nome = "Server";


    public static void main(String[] args) {

        Clientrmi c = new Clientrmi();
        c.start();
    }

    @Override
    public synchronized void run() {

        clientInit(this);

    }
//----------------------------------------------------------------------------------------------------

    public void clientInit(Clientrmi client) {

        Server = connect();

        client.chooseNickname();

        System.err.println(nickname);//debug
        System.err.println(pass);//debug

        try {  //routine di login

            while (!Server.logIn(nickname, pass)) {

                sleep(1000);
                client.chooseNickname();
            }

        } catch (Exception e) {
            System.err.println("riprova routine login");
        }


        try { //richiede una porta su cui fare callback

            clientPort = Server.firstFreePort(nickname);
        } catch (Exception e) {
            System.err.println("eccezione su freeclientports");
        }


        Thread t = new Handshake();
        t.start();

        try {
            Server.initCallback(nickname, clientPort);
        } catch (Exception e) {
            System.err.println("Errore initcallback");
        }

    }


//----------------------------------------------------------------------------------------------------


    /*Il server dopo unn delay iniziale di 0.5s tenta la connessione al registry. Se tale connessione fallisce, stampa
    errore e rientra nel while, aspetta 0.5s e poi riprova. Nel momento in cui si connette cambia il bool connected (failsafe)
    e ritorna il server al thread.
     */

    /**
     * Establishes the connection to the registry bound o the server.
     *
     * @return the serverinterface conneted to the regitry.
     */
    private ServerInterface connect() {

        boolean connected = false;


        while (!connected) {

            try {

                sleep(500);
                Registry registry = LocateRegistry.getRegistry(serverPort);
                Server = (ServerInterface) registry.lookup(nome);
                connected = true;
                return Server;

            } catch (java.rmi.ConnectException e) {

                System.err.println("Ritento la connessione");

            } catch (Exception e) {

                System.err.println("Eccezione connect");
            }
        }
        return null;
    }

//----------------------------------------------------------------------------------------------------

    @Deprecated
    public void chooseMap(ServerInterface server) {
    }

   /*     view = new View();

        view.message.scegliMappa();
        String mappaScelta = view.parser.parse();

        try {

            Map mappa = server.buildMap(mappaScelta);
            Board board = new Board();
            board.setMap(mappa);

            view.printer.map(board);
        } catch (Exception e) {
            System.err.println("Errore nella mappa!");
            e.printStackTrace();
        }
    }
*/
//----------------------------------------------------------------------------------------------------

    /**
     * Sends username and password to the validation system on the server.
     */
    private void chooseNickname() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il tuo nickname:");
        nickname = scanner.nextLine();
        System.out.println("Inserisci la tua password:");
        pass = scanner.nextLine();

    }

//----------------------------------------------------------------------------------------------------

    /**
     * Is the remote method that can be invoked by the server to print all the data coming from server.
     *
     * @param msg is the string coming from the server.
     */
    public void serverMessage(String msg) {

        System.out.println(msg);

    }

//----------------------------------------------------------------------------------------------------

    public void pingClient() {

        System.out.println("Eccomi"); //debug


    }

}
