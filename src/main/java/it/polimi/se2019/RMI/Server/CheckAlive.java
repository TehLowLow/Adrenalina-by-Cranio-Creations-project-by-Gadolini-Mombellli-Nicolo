package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Model.Player;

import java.util.ArrayList;

public class CheckAlive extends Thread {

    private boolean start = false;
    private int eta;
    ArrayList<Player> players;

    CheckAlive(int eta, ArrayList<Player> players) {

        this.eta = eta;
        this.players = players;

    }

    public void check() {

        Thread t = new CheckAlive(eta, players);
        t.start();

    }

    @Override
    public void run() {

        while (!start) {

            try {
                sleep(2000);
                eta = eta - 2;
                if (eta % 10 == 0 && eta != 0) {
                    System.out.println("mancano " + eta + " secondi");
                }
                checker(players);

            } catch (Exception e) {

                System.err.println("Errore di CheckAlive");

            }

            if (players.size() < 3) {
                System.out.println("Troppi giocatori disconnessi, attendere.");
                break;
            }

            if (eta <= 0 || players.size() == 5) {
                start = true;
                System.out.println("Inizio partita!");

            }
        }
    }


    private void checker(ArrayList<Player> players) {


        for (Player player : players) {
            if (player.isConnected()) {
                player.setConnectionAlive(false);
            } else {
                players.remove(player);
                break;
            }
        }


    }


}
