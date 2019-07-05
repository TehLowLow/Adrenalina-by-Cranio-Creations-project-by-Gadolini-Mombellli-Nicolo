package it.polimi.se2019.Network;

import it.polimi.se2019.Controller.Adrenalina.Match;
import it.polimi.se2019.Model.Player;

import static it.polimi.se2019.Network.Server.*;

/**
 * This class is responsible of managing the lobby routine, starting the timer that will start the game after it's expired.
 */

public class Lobby extends Thread {

    /**
     * Starts the lobby routine.
     */
    @Override
    public synchronized void run() {

        //Qua dentro va il polling e il timer di lobby
        //Tenere conto del time shift, facendo una sottrazione di shift alla sleep


        int time = lobbyTimer * 1000;
        boolean wait = true;

        while (!matchStarted && time >= 0) {

            if (connectedPlayers.size() < 3){

                wait = true;

            }



            if (connectedPlayers.size() >= 3) {

                if (wait){

                    try {
                        sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    wait = false;

                }

                for (Player player : connectedPlayers) {


                    update(player, "Polling");

                }

                try {

                    sleep(500);
                    time -= 500;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                time = lobbyTimer * 1000;
            }
        }



        matchStarted = true;
        Match match = new Match();
        match.start();

    }


}




