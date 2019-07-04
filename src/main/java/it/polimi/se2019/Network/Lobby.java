package it.polimi.se2019.Network;

import it.polimi.se2019.Controller.Adrenalina.Match;
import it.polimi.se2019.Model.Player;

import static it.polimi.se2019.Network.Server.*;

public class Lobby extends Thread {

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
                        sleep(10000);
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


//Questa classe avvia il polling ogni x millisec, cosi da triggerare la Socketexception o la futura RMIHandler
//solamente da quando ci saranno 3 o piu players. Inoltre quando la size sale sopra i 3 avvia anche il timer

