package it.polimi.se2019.Network.Socket.Server;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.net.Socket;

import static it.polimi.se2019.Network.Server.*;

public class Manager extends Thread {

    //Estende thread perche almeno posso creare un executor che fa partire in parallelo i thread

    private Player toPoll;
    private String response;


    public Manager(Player player) {

        this.toPoll = player;

    }

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

        if (!matchStarted) {

            connectedPlayers.remove(toPoll);

        }


    }
}
