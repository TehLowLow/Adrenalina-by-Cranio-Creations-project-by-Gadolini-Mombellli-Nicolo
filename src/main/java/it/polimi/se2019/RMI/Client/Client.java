package it.polimi.se2019.RMI.Client;
import it.polimi.se2019.RMI.Server.ServerInterface;
import it.polimi.se2019.View.*;
import it.polimi.se2019.Model.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client extends Thread {

    private View view;
    private static String nickname;

    public static void main(String[] args) {

       Thread t = new Client();
       t.start();

    }

    public synchronized void run(){

        try {

            String nome = "Server";
            Registry registry = LocateRegistry.getRegistry(1099);
            ServerInterface server = (ServerInterface) registry.lookup(nome);

            System.out.println(server.sendMessage());

            Client client = new Client();
            client.chooseNickname(server);
            //client.chooseMap(server);
            Heartbeat heartbeat = new Heartbeat(server, nickname);
            heartbeat.beat();

        } catch (Exception e) {
            System.err.println("Eccezione Server:");
            e.printStackTrace();
        }

    }


    public void chooseMap(ServerInterface server){

       view = new View();

       view.message.scegliMappa();
       String mappa_scelta = view.parser.parse();

       try {

           Map mappa = server.buildMap(mappa_scelta);
           Board board = new Board();
           board.setMap(mappa);

           view.printer.map(board);
       }
       catch(Exception e){
           System.err.println("Errore nella mappa!");
           e.printStackTrace();
       }
    }

    public void chooseNickname(ServerInterface server){

        View view = new View();

        System.out.println("Inserisci il tuo nickname:");
        nickname = view.parser.parse();

        try {
            server.login(nickname);
        }
          catch(Exception e){
            System.out.println("Eccezione!");
        }

    }




}
