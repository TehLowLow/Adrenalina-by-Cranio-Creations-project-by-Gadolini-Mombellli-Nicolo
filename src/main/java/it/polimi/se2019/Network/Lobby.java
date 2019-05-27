package it.polimi.se2019.Network;

import it.polimi.se2019.Model.Player;

import java.util.TimerTask;

import static it.polimi.se2019.Network.Server.*;

public class Lobby extends Thread {

    @Override
    public void run() {

        //Qua dentro va il polling e il timer di lobby
        //Tenere conto del time shift, facendo una sottrazione di shift alla sleep

        while (!matchStarted) {

            if (connectedPlayers.size() >= 3) {


                for (Player player : connectedPlayers) {

                    update(player, "Polling");
                }

            }
        }
    }
}



//Questa classe avvia il polling ogni x millisec, cosi da triggerare la Socketexception o la futura RMIHandler
//solamente da quando ci saranno 3 o piu players. Inoltre quando la size sale sopra i 3 avvia anche il timer

