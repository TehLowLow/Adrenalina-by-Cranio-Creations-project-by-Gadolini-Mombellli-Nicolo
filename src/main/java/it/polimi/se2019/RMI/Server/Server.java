package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Controller.Data.MapBuilders.*;
import it.polimi.se2019.RMI.Client.ClientInterface;
import it.polimi.se2019.View.*;
import it.polimi.se2019.Model.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;

import static java.lang.Thread.sleep;

public class Server extends Thread implements ServerInterface {


    static ArrayList<Player> players;
    public static Hashtable logInTable = new Hashtable();
    static final int PORT = 2000;
    static int clientPorts = 4000;
    static Hashtable coccupiedClientPorts = new Hashtable();

    public static void main(String[] args) {

        players = new ArrayList<>();

        Thread s = new Server();
        s.run();

    }


    @Override
    public synchronized void run() {

        serverInit();


    }
//----------------------------------------------------------------------------------------------------

    private void serverInit() {

        try { //avvio il server registry

            String nome = "Server";
            Server server = new Server();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, PORT);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind(nome, stub);
            System.out.println("Server online listening on port " + PORT);


        } catch (Exception e) {
            System.err.println("Errore di init registry!");
            e.printStackTrace();

        }
    }


    //----------------------------------------------------------------------------------------------------

    public synchronized boolean logIn(String user, String pass) {

        String verify = new String();
        CheckTable checkTable = new CheckTable();

        if (logInTable.get(user) == null) {

            logInTable.put(user, pass);
            coccupiedClientPorts.put(user, clientPorts);
            clientPorts = clientPorts + 500;

            Player player = new Player();
            player.setNickname(user);

            players.add(player);


            System.out.println("Aggiunto" + " " + user);

            if (players.size() == 3) {
                CheckAlive checkAlive = new CheckAlive(30, players);
                checkAlive.check();
            }
            return true;

        } else if (checkTable.checker(user, players)) {

            return false; //Il player sta tentando di accedere due volte

        } else {

            verify = (String) logInTable.get(user); //il player si è disconnesso e vuole riaccedere
            if (verify.equals(pass)) {

                Player player = new Player();
                player.setNickname(user);
                player.setConnectionAlive(true);
                players.add(player);
                if (players.size() == 3) {
                    CheckAlive checkAlive = new CheckAlive(30, players);
                    checkAlive.check();
                }
                return true;
            }
        }

        return false;
    }
    //TODO Issue: Il login non controlla il valore dei caratteri inseriti, percui unutente che si registra con "Nome"
    // e uno che si registra con "Nome(spazio)" verranno trattati come due user con nomi tecnicamente diversi ma in
    // pratica poco chiari all' utente.
    // esempio: "Lollo" e "Lollo " hanno lunghezza diversa, il secondo ha un carattere diverso dal primo però non
    // sarà distinguibile a terminale
    // Il login inoltre non verifica che la password non sia vuota.
//----------------------------------------------------------------------------------------------------


    public synchronized int firstFreePort(String nick) {

        return (int) coccupiedClientPorts.get((nick));
    }


//----------------------------------------------------------------------------------------------------

    private synchronized void callBack(String nick, int port) {

        ClientInterface client;

        try {
            Registry registry = LocateRegistry.getRegistry(port);
            client = (ClientInterface) registry.lookup(nick);
            client.serverMessage("ciao client benvenuto sul server di adrenalina");
        } catch (Exception e) {
            System.err.println("Errore callBack");
        }
    }


//----------------------------------------------------------------------------------------------------

    public synchronized void initCallback(String nick, int port) {

        callBack(nick, port);

    }

//----------------------------------------------------------------------------------------------------


    @Deprecated
    public synchronized void login(String nickname) {

        Player player = new Player();
        player.setNickname(nickname);
        players.add(player);
        System.out.println("Si è aggiunto il giocatore " + nickname);
        System.out.println("Per ora i giocatori sono:");

        for (Player giocatore : players) {

            System.out.println(giocatore.getNickname());

        }

        if (players.size() == 3) {
            CheckAlive checkAlive = new CheckAlive(30, players);
            checkAlive.check();
        }

        System.out.println("-----");
    }


//----------------------------------------------------------------------------------------------------

    @Deprecated
    public synchronized void ping(String nickname) {

        //System.out.println(nickname + " è ancora vivo.");
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) {
                player.setConnectionAlive(true);
            }
        }
    }

//----------------------------------------------------------------------------------------------------

    @Deprecated
    public Map buildMap(String map) {  //da pensare con dual channel rmi

        if (map.equals("1")) {
            return new Map1Builder().build();
        }

        if (map.equals("2")) {
            return new Map2Builder().build();
        }

        if (map.equals("3")) {
            return new Map3Builder().build();
        }

        if (map.equals("4")) {

            return new Map4Builder().build();
        }
        return null;
    }
}