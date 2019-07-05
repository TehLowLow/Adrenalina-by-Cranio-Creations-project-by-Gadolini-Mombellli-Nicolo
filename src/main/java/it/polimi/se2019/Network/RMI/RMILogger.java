package it.polimi.se2019.Network.RMI;


import it.polimi.se2019.Model.Player;

import it.polimi.se2019.Network.Logger;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import static it.polimi.se2019.Network.Server.*;

/**
 * This class is used for validation of credentials of a player.
 */


public class RMILogger implements Logger, Runnable, RMILoggerInterface {

    /**
     * The username written by the player.
     */
    private static String userName;
    /**
     * The password written by the player.
     */
    private static String passWord;

    /**
     * Temporary player instance used for creating a new dedicated player instance inside connectedPlayers.
     */
    Player temp;


    @Override

    /**
     * Starts the server-side for the login routine.
     */
    public void run() {
        //In questo thread devo avviare un registry, accettare una connessione alla volta, e verificare il login.

        initServer();


    }


    /**
     * In this method occurs the initialization of the server's registry.
     */
    private synchronized void initServer() {

        try { //avvio il server registry

            String nome = "LoginRMI";
            RMILoggerInterface stub = (RMILoggerInterface) UnicastRemoteObject.exportObject(this, LOGINRMIPORT);
            Registry registry = LocateRegistry.createRegistry(LOGINRMIPORT);
            registry.rebind(nome, stub);
            System.out.println("Server online listening on port " + LOGINRMIPORT);


        } catch (Exception e) {
            System.err.println("Errore di init registry!");
            e.printStackTrace();

        }

    }

    /**
     * Remote method, can be invoked by the remote client. Verifies the credentials of a player and returns a value status
     * of the login.
     *
     * @param u is the username of the remote player.
     * @param p is the passwor dof the remote player.
     * @return the login result that is the game port.
     */
    @Override
    public synchronized int verify(String u, String p) {

        this.userName = u;
        this.passWord = p;

        return logIn();

    }

    /**
     * Checks the credentials of a player.
     *
     * @return the RMI game port if the player is granted access into the game.
     */
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


    /**
     * This is the method that actually looks for errors during the login process. It looks for double connections, credentials errors and
     * creates new instances fro each new player.
     *
     * @return true if the player is granted access.
     */
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

            if (player.getNickname().equals(userName) && player.isConnected()) {

                System.out.println("Utente gia registrato con queste credenziali");

                return false;
            }
        }


        if (registrations.get(userName) != null && passWord.equals(registrations.get(userName)) && matchStarted) { //player disconnesso che tenta di riconnettersi.


            System.out.println("Bentornato " + userName); //successo
            return true;

        } else {

            System.out.println("Credenziali errate!"); //errore
            return false;

        }

    }

    /**
     * Returns a game port onto which the client will open his local registry.
     *
     * @param s is the username of the player.
     * @return the player selected port.
     */
    public synchronized int getGamePort(String s) {

        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(s)) {

                return player.getPORT();

            }
        }
        return 0;
    }
}


