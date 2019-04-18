package it.polimi.se2019.RMI.Client;

import it.polimi.se2019.RMI.Server.ServerInterface;
import it.polimi.se2019.View.*;
import it.polimi.se2019.Model.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client extends Thread {

    private View view;
    private static String nickname;
    private static String pass;
    ServerInterface Server;
    String nome = "Server";


    public static void main(String[] args) {

        Thread t = new Client();
        t.start();
    }

    public synchronized void run() {

        Server = connect();

        try {
            System.out.println(Server.sendMessage());
        } catch (Exception e) {
            System.err.println("errore");
        }

        Client client = new Client();

        client.chooseNickname(Server);

        try {
            while (!Server.logIn(nickname, pass)) {

                sleep(1000);
                client.chooseNickname(Server);
            }

        } catch (Exception e) {
            System.err.println("riprova");
        }

        //client.chooseMap(server);
        Heartbeat heartbeat = new Heartbeat(Server, nickname);
        heartbeat.beat();
    }

//----------------------------------------------------------------------------------------------------

    /*Il server dopo unn delay iniziale di 0.5s tenta la connessione al registry. Se tale connessione fallisce, stampa
    errore e rientra nel while, aspetta 0.5s e poi riprova. Nel momento in cui si connette cambia il bool connected (failsafe)
    e ritorna il server al thread.
     */
    private ServerInterface connect() {

        boolean connected = false;
        ServerInterface server;

        while (!connected) {

            try {

                sleep(500);
                Registry registry = LocateRegistry.getRegistry(1099);
                server = (ServerInterface) registry.lookup(nome);
                connected = true;
                return server;

            } catch (java.rmi.ConnectException e) {

                System.err.println("Ritento la connessione");

            } catch (Exception e) {

                System.err.println("Eccezione");
            }
        }
        return null;
    }

//----------------------------------------------------------------------------------------------------

    public void chooseMap(ServerInterface server) {

        view = new View();

        view.message.scegliMappa();
        String mappa_scelta = view.parser.parse();

        try {

            Map mappa = server.buildMap(mappa_scelta);
            Board board = new Board();
            board.setMap(mappa);

            view.printer.map(board);
        } catch (Exception e) {
            System.err.println("Errore nella mappa!");
            e.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------
    public void chooseNickname(ServerInterface server) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il tuo nickname:");
        nickname = scanner.nextLine();
        System.out.println("Inserisci la tua password:");
        pass = scanner.nextLine();

    }
}
