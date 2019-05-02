package it.polimi.se2019.Network.RMI.Client;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import it.polimi.se2019.Network.Client;
import it.polimi.se2019.Network.RMI.RMILoggerInterface;
import it.polimi.se2019.Network.RMI.Server.RMIServerInterface;
import it.polimi.se2019.View.Parser;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


import static it.polimi.se2019.Network.Server.LOGINRMIPORT;


public class RMIClient extends Client implements Runnable, RMIClientInterface {

    RMILoggerInterface Logger;

    boolean connected = false;
    boolean logged = false;
    String User;
    String psw;
    int gamePort;
    int localPort;


    @Override
    public void run() {

        System.out.println("hai avviato una connessione RMI");

        Logger = connect(LOGINRMIPORT);


        while (!logged) {

            try {

                Scanner scanner = new Scanner(System.in);

                System.out.println("inserisci username");
                User = scanner.nextLine();
                System.out.println("Inserisci psw");
                psw = scanner.nextLine();

                gamePort = Logger.verify(User, psw);
                System.out.println("la porta di gioco è " + gamePort);
                logged = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {

            localPort = Logger.getGamePort(User);
            System.out.println(localPort);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Metodo per chiudere il registry di login
        //Metodo per avviare il local registry
        //Metodo per connettersi al game server
        //Metodo per avviare il callback da parte del server
    }


    private synchronized RMILoggerInterface connect(int port) {

        while (!connected) {

            try {
                Registry registry = LocateRegistry.getRegistry(port);
                RMILoggerInterface rServer = (RMILoggerInterface) registry.lookup("LoginRMI");
                return rServer;

            } catch (Exception e) {

                System.out.println("Ritento la connessione");
                //e.printStackTrace();
            }
        }

        return null;
    }


    /*public int initLocalRegistry(String User){

        RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject.exportObject(this, )




    }*/

    public String sendMsgWithAnswer(String msg) {

        System.out.println(msg);

        Parser parser = new Parser();
        return parser.parse();
    }


    public void sendMsg(String msg) {

        System.out.println(msg);

    }


    //TODO prototipo di update, integrarlo per la comunicazione.


}
