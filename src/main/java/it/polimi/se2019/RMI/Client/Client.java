package it.polimi.se2019.RMI.Client;

import it.polimi.se2019.RMI.Server.ServerInterface;
import it.polimi.se2019.View.*;
import it.polimi.se2019.Model.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client extends Thread implements ClientInterface {

    private View view;
    private static final int serverPort = 2000;
    static int clientPort;
    static String nickname;
    private static String pass;
    private ServerInterface Server;
    private String nome = "Server";


    public static void main(String[] args) {

        Thread t = new Client();
        t.start();
    }

    @Override
    public synchronized void run() {

        Server = connect();

        Client client = new Client();
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


        Heartbeat heartbeat = new Heartbeat(Server, nickname);
        heartbeat.beat();

        //routine di handshake

        Thread t = new Handshake();
        t.start();
        try {
            Server.initCallback(nickname, clientPort);
        } catch (Exception e) {
            System.err.println("Errore initcallback");

            //client.chooseMap(server);

        }
    }
//----------------------------------------------------------------------------------------------------

    /*Il server dopo unn delay iniziale di 0.5s tenta la connessione al registry. Se tale connessione fallisce, stampa
    errore e rientra nel while, aspetta 0.5s e poi riprova. Nel momento in cui si connette cambia il bool connected (failsafe)
    e ritorna il server al thread.
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

    public void chooseMap(ServerInterface server) {

        view = new View();

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

//----------------------------------------------------------------------------------------------------

    public void chooseNickname() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il tuo nickname:");
        nickname = scanner.nextLine();
        System.out.println("Inserisci la tua password:");
        pass = scanner.nextLine();

    }

//----------------------------------------------------------------------------------------------------

    public void serverMessage(String msg) {

        System.out.println(msg);

    }


}
