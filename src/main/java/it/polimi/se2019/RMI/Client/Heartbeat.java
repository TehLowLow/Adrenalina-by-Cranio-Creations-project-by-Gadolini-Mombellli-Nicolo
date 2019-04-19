package it.polimi.se2019.RMI.Client;

import it.polimi.se2019.RMI.Server.ServerInterface;

public class Heartbeat extends Thread {


    private ServerInterface server;
    private String nickname;

    Heartbeat(ServerInterface server, String nickname) {

        this.server = server;
        this.nickname = nickname;
    }

    void beat() {

        Thread t = new Heartbeat(this.server, this.nickname);
        t.start();
    }

    @Override
    public void run() {

        try {
            while (true) {

                sleep(500);
                server.ping(nickname);
            }
        } catch (Exception e) {
            System.err.println("Errore di hearthbeat.");
        }

    }

}
