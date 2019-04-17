package it.polimi.se2019.RMI.Server;
import it.polimi.se2019.Controller.Data.MapBuilders.*;
import it.polimi.se2019.View.*;
import it.polimi.se2019.Model.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerInterface {



    public static void main(String[] args) {

        try{

            String nome = "Server";
            Server server = new Server();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, 1099);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(nome, stub);
            System.out.println("Questo server è lavato e pronto per essere mangiato.");


        }
        catch(Exception e){
            System.err.println("Errore!");
            e.printStackTrace();
            return;
        }

    }


    public void sendMessage(){

        View view = new View();
        view.message.start();

    }

    public Map buildMap(String map){

        if(map.equals("1")){

            Map1Builder mapBuilder = new Map1Builder();

            return mapBuilder.build();

        }

        if(map.equals("2")){

            Map2Builder mapBuilder = new Map2Builder();

            return mapBuilder.build();

        }

        if(map.equals("3")){

            Map3Builder mapBuilder = new Map3Builder();

            return mapBuilder.build();

        }

        if(map.equals("4")){

            Map4Builder mapBuilder = new Map4Builder();

            return mapBuilder.build();

        }

        return null;
    }


}
