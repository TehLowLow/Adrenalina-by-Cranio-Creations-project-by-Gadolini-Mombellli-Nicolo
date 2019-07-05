package it.polimi.se2019.Network.RMI.Server;

import it.polimi.se2019.Model.Player;

import static it.polimi.se2019.Network.Server.*;
import static it.polimi.se2019.Network.Server.registrations;

/**
 * This class is responsible of managing reportedly disconnected players. The manager removes the reference to the player connection
 * and sets the connection inside connectedPlayers as not alive. If the match is still in the lobby phase, the player is also removed
 * from connectedPlayers and registrations, freeing his spot inside the lobby and his nickname.
 */

public class RmiManager extends Thread {


    /**
     * The player to remove.
     */
    private Player toPoll;


    /**
     * COnstructor of the manager.
     * @param player is the player to manage.
     */
    public RmiManager(Player player) {

        this.toPoll = player;
    }


    /**
     * Starts the managing routine.
     */
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
