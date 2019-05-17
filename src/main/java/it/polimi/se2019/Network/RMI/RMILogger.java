package it.polimi.se2019.Network.RMI;


import it.polimi.se2019.Model.Player;

import it.polimi.se2019.Network.Logger;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import static it.polimi.se2019.Network.Server.*;

public class RMILogger implements Logger, Runnable, RMILoggerInterface {


    private String userName;

    private String passWord;
    Player temp;


    @Override
    public void run() {
        //In questo thread devo avviare un registry, accettare una connessione alla volta, e verificare il login.
        initServer(this);

    }


    private synchronized void initServer(RMILogger loginRMI) {

        try { //avvio il server registry

            String nome = "LoginRMI";
            RMILoggerInterface stub = (RMILoggerInterface) UnicastRemoteObject.exportObject(loginRMI, LOGINRMIPORT);
            Registry registry = LocateRegistry.createRegistry(LOGINRMIPORT);
            registry.rebind(nome, stub);
            System.out.println("Server online listening on port " + LOGINRMIPORT);


        } catch (Exception e) {
            System.err.println("Errore di init registry!");
            e.printStackTrace();

        }

    }


    @Override
    public synchronized int verify(String u, String p) {

        this.userName = u;
        this.passWord = p;

        return logIn();

    }


    @Override
    public synchronized int logIn() {

        boolean value = checkConnections();

        if (value) {
            return RMIPORT;
        } else {
            System.out.println("Connessione rifiutata");//TODO 3
            return -1;
        }
    }


    @Override
    public synchronized boolean checkConnections() {

        if (registrations.get(userName) == null) {

            if (matchStarted) {
                System.out.println("Partita iniziata, non è possibile aggiungere giocatori");
                return false;
            }

            //aggiungi un nuovo player e registra i suoi dati, inoltre viene aggiunto all' array di connessi.

            if (connectedSize() < 5) {   //TODO 2
                temp = newPlayer(userName, passWord, "RMI");
                return true;
            }


        }

        //verifica ste sta nell'arraylist di player per evitare doppie connessioni.

        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(userName)) {

                System.out.println("Utente gia registrato con queste credenziali");

                return false;
            }
        }


        if (registrations.get(userName) != null) { //player disconnesso che tenta di riconnettersi.

            if (passWord.equals(registrations.get(userName))) {

                System.out.println("Bentornato " + userName); //successo
                return true;
            } else {

                System.out.println("password errata!"); //errore
                return false;

            }
        }

        System.out.println("Errore nelle credenziali");
        return false;


    }

    public synchronized int getGamePort(String s) {

        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(s)) {

                return player.getPORT();

            }
        }
        return 0;
    }


}


//TODO 2: failsafe per evitare aggiunte sopra i 5 giocatori, da rivedere una volta creato il thread per la verifica delle connessioni.
//TODO 3: tutti i println sono stampati sulla cli del server come debug, i metodi che dovranno ritornare roba all' utente dovranno sfruttare i canali di rete.
//TODO 4: BUG Uno user che sbaglia la psw viene disconnesso dal registry, invece dovrebbe rimanere connesso. Inoltre il server lo rileva come doppia connessione BUG