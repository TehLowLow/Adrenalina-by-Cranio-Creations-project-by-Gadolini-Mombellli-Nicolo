package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Model.Player;

import java.util.ArrayList;

public class CheckAlive extends Thread {

    boolean start = false;
    int ETA;
    ArrayList<Player> players;

    public CheckAlive(int ETA, ArrayList<Player> players) {

        this.ETA = ETA;
        this.players = players;

    }

    public void check() {

        Thread t = new CheckAlive(ETA, players);
        t.start();

    }

    public void run() {


        while (!start) {

            try {

                sleep(2000);
                ETA = ETA - 2;
                System.out.println(ETA);
                for (Player player : players) {

                    if (player.isConnected()) {
                        player.setConnectionAlive(false);
                    } else {
                        players.remove(player);
                    }
                }

                if (players.size() < 3) {

                    break;

                }

                if (ETA <= 0 || players.size()==5) {

                    start = true;
                    System.out.println("Inizio partita!");

                }

            } catch (Exception e) {

            }

        }
    }

}
