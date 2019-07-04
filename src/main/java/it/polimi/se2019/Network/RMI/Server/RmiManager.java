package it.polimi.se2019.Network.RMI.Server;

import it.polimi.se2019.Model.Player;

import static it.polimi.se2019.Network.Server.*;
import static it.polimi.se2019.Network.Server.registrations;

public class RmiManager extends Thread {


    private Player toPoll;


    public RmiManager(Player player) {

        this.toPoll = player;
    }


    @Override
    public void run() {

        playerClient.remove(toPoll);
        toPoll.setConnectionAlive(false);

        if (!matchStarted) {

            connectedPlayers.remove(toPoll);
            registrations.remove(toPoll.getNickname());

        }
    }


}
