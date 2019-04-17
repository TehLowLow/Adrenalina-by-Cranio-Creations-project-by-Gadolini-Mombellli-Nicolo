package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Controller.Data.MapBuilders.*;
import it.polimi.se2019.View.*;
import it.polimi.se2019.Model.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Server implements ServerInterface {


    static ArrayList<Player> players;

    public static void main(String[] args) {

        try {

            String nome = "Server";
            Server server = new Server();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, 1099);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(nome, stub);
            System.out.println("Questo server è lavato e pronto per essere mangiato.");
            players = new ArrayList<>();


        } catch (Exception e) {
            System.err.println("Errore!");
            e.printStackTrace();
            return;
        }

    }


    public String sendMessage() {

        View view = new View();
        return view.message.start();

    }

    public Map buildMap(String map) {

        if (map.equals("1")) {

            Map1Builder mapBuilder = new Map1Builder();

            return mapBuilder.build();

        }

        if (map.equals("2")) {

            Map2Builder mapBuilder = new Map2Builder();

            return mapBuilder.build();

        }

        if (map.equals("3")) {

            Map3Builder mapBuilder = new Map3Builder();

            return mapBuilder.build();

        }

        if (map.equals("4")) {

            Map4Builder mapBuilder = new Map4Builder();

            return mapBuilder.build();

        }

        return null;
    }

    public synchronized void login(String nickname) {

        Player player = new Player();
        player.setNickname(nickname);
        players.add(player);
        System.out.println("Si è aggiunto il giocatore " + nickname);
        System.out.println("Per ora i giocatori sono:");

        for (Player giocatore : players) {

            System.out.println(giocatore.getNickname());

        }

        if(players.size()==3){
            CheckAlive checkAlive = new CheckAlive(30, players);
            checkAlive.check();
        }

        System.out.println("-----");
    }

    public int startTimer(int time) {


        return time;
    }

    public synchronized void ping(String nickname) {

        //System.out.println(nickname + " è ancora vivo.");
        for (Player player : players) {
            if (player.getNickname().equals(nickname)) {
                player.setConnectionAlive(true);
            }
        }

    }

}

