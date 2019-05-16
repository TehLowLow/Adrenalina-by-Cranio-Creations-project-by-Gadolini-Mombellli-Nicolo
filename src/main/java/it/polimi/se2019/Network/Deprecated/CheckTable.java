package it.polimi.se2019.Network.Deprecated;

import it.polimi.se2019.Model.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This checker class looks in the CopyOnWriteArrayList of connected players for an already existing instance of player.
 * Used in the validation process.
 */

public class CheckTable {


    /**
     * Iterates over the CopyOnWriteArrayList and looks for a player instance.
     * @param name is the player to look for.
     * @param players is the CopyOnWriteArrayList of connected players.
     * @return true if player is found.
     */
    boolean checker(String name, CopyOnWriteArrayList<Player> players) {

        for (Player giocatore : players) {

            if (giocatore.getNickname().equals(name)) {

                return true;

            }

        }

        return false;

    }
}
