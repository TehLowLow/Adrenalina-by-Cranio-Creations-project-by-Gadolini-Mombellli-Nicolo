package it.polimi.se2019.Network;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.RMI.RMILogger;
import it.polimi.se2019.Network.RMI.Server.RMIServer;
import it.polimi.se2019.Network.Socket.Server.SocketServer;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server {

    public static final int RMIPORT = 2100;
    public static final int SOCKETPORT = 2200;
    public static final int LOGINPORT = 9999;
    public static ServerSocket loginSocket;
    public static ServerSocket gameSocketl;
    public static Hashtable registrations = new Hashtable();  //associazione user psw
    public static Hashtable playerClient = new Hashtable();  //associazione fra giocatore e suo client
    public static CopyOnWriteArrayList<Player> connectedPlayers = new CopyOnWriteArrayList<>();


    public static void main(String[] args) {

        //all avvio del server devono accadere due cose:
        //1) il server avvia i thread di inizializzazione delle connessioni dedicate alla partita
        //2) il server avvia altre due connessioni (una socket e una rmi) helpers, connessioni dedicate esclusivamente
        //   al login dei player.


    }


}
