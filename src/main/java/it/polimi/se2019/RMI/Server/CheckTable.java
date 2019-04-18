package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Model.Player;

import java.util.ArrayList;

public class CheckTable {


    boolean checker(String name, ArrayList<Player> players) {

        for (Player giocatore : players) {

            if (giocatore.getNickname().equals(name)) {

                return true;

            }

        }

        return false;

    }
}
