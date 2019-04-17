package it.polimi.se2019.RMI.Client;
import it.polimi.se2019.RMI.Server.ServerInterface;
import it.polimi.se2019.View.*;
import it.polimi.se2019.Model.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private View view;

    public static void main(String[] args) {

        try {
            String nome = "Server";
            Registry registry = LocateRegistry.getRegistry(1099);
            ServerInterface server = (ServerInterface) registry.lookup(nome);

            server.sendMessage();

            Client client = new Client();
            client.chooseMap(server);

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

}
