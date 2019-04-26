package it.polimi.se2019.Network.Deprecated;

import it.polimi.se2019.Network.Deprecated.TServer;

public class Main {


    public static void main(String[] args) {

        try {

            //Integer port = Integer.parseInt(args[0]);
            Thread t = new TServer();
            t.start();

        }catch(Exception e){

            System.err.println("AO");
        }

    }
}
