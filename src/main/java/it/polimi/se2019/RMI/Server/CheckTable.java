package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Model.Player;

import java.util.ArrayList;

/**
 * This checker class looks in the arraylist of connected players for an already existing instance of player.
 * Used in the validation process.
 */

public class CheckTable {


    /**
     * Iterates over the ArrayList and looks for a player instance.
     * @param name is the player to look for.
     * @param players is the arraylist of connected players.
     * @return true if player is found.
     */
    boolean checker(String name, ArrayList<Player> players) {

        for (Player giocatore : players) {

            if (giocatore.getNickname().equals(name)) {

                return true;

            }

        }

        return false;

    }
}
