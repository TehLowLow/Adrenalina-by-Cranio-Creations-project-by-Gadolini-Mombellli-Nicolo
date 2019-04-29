package it.polimi.se2019.Network.RMI.Client;

import it.polimi.se2019.Network.Client;
import it.polimi.se2019.Network.RMI.RMILoggerInterface;
import it.polimi.se2019.Network.RMI.Server.RMIServerInterface;
import it.polimi.se2019.View.Parser;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


import static it.polimi.se2019.Network.Server.RMIPORT;

public class RMIClient extends Client implements Runnable, RMIClientInterface {

    RMILoggerInterface Logger;

    boolean connected = false;
    boolean logged = false;
    String User;
    String psw;

    @Override
    public void run() {

        System.out.println("hai avviato una connessione RMI");

        Logger = connect();

        while (!logged) {

            //Logger.verify();

        }


            while (true) {

                Scanner scanner = new Scanner(System.in);
                try {

                    //Logger.sendMsg("Clientrmi RMI: " + scanner.nextLine());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


        private RMILoggerInterface connect () {

            while (!connected) {

                try {
                    Registry registry = LocateRegistry.getRegistry(RMIPORT);
                    RMIServerInterface rServer = (RMIServerInterface) registry.lookup("Server");

                    connected = true;

                    //return rServer;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;


            //TODO
        }

        private synchronized void credentials () {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Inserisci lo Username");
            User = scanner.nextLine();
            System.out.println("inserisci password");
            psw = scanner.nextLine();


        }


        public String sendMsgWithAnswer(String msg){

            System.out.println(msg);

            Parser parser = new Parser();
            return parser.parse();

        }


        public void sendMsg(String msg){

            System.out.println(msg);

        }

    }