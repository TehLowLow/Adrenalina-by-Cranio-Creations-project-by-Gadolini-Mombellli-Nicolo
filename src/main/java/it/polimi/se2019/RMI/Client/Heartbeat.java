package it.polimi.se2019.RMI.Client;

import it.polimi.se2019.RMI.Server.ServerInterface;

public class Heartbeat extends Thread {


    ServerInterface server;
    String nickname;

    public Heartbeat(ServerInterface server, String nickname){

        this.server = server;
        this.nickname = nickname;
    }

    public void beat(){

        Thread t = new Heartbeat(this.server, this.nickname);
        t.start();
    }

    public void run(){

        try{
            while(true) {

                sleep(500);
                server.ping(nickname);
            }
        }
        catch(Exception e){
            System.err.println("Errore.");
        }

    }

}
