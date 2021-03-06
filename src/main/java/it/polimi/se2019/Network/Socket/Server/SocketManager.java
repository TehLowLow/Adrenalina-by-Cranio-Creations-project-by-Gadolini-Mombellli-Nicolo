package it.polimi.se2019.Network.Socket.Server;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.net.Socket;

import static it.polimi.se2019.Network.Server.*;

/**
 * This class manages the disconnection of a client
 */

public class SocketManager extends Thread {

    //Estende thread perche almeno posso creare un executor che fa partire in parallelo i thread

    /**
     * The player to manage.
     */
    private Player toPoll;


    public SocketManager(Player player) {

        this.toPoll = player;

    }

    /**
     * Removes the connected player's client from the clients list, and sets is status as not connected.
     * If the disconnection happens during the lobby, every signle instance of the player is removed, and his nickname is freed.
     */
    @Override
    public void run() {

        //processo da eseguire durante la fase di Lobby
        //Il manager accede a connected players e manda un update a ogni client

        //gestisco la disconnessione

        //Devo modificare lo status del campo connected in Server.connectedPlayers
        //Devo rimuovere il client da playerclient MA non la porta
        //Devo verificare che il logger restituisca la porta assegnata al player salvata in playerclient alla riconnessione
        //Testare per le dimenticanze LOL


        Socket remove = (Socket) playerClient.get(toPoll);

        try {
            remove.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        playerClient.remove(toPoll);
        toPoll.setConnectionAlive(false);

        if (!matchStarted) {

            connectedPlayers.remove(toPoll);
            registrations.remove(toPoll.getNickname());


        }


    }
}
