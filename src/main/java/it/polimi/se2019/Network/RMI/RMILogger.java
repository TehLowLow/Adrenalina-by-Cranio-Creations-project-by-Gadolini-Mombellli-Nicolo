package it.polimi.se2019.Network.RMI;


import it.polimi.se2019.Model.Player;

import it.polimi.se2019.Network.Logger;
import org.jetbrains.annotations.Contract;

import java.io.DataInput;
import java.io.DataInputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import static it.polimi.se2019.Network.Server.*;

public class RMILogger implements Logger, Runnable, RMILoggerInterface {


    private String userName;
    private String passWord;




    @Override
    public void run() {


        //In questo thread devo avviare un registry, accettare una connessione alla volta, e verificare il login.

        initServer(this);


    }


    private void initServer(RMILogger LoginRMI) {

        try { //avvio il server registry

            String nome = "LoginRMI";
            RMILoggerInterface stub = (RMILoggerInterface) UnicastRemoteObject.exportObject(LoginRMI, LOGINRMIPORT);
            Registry registry = LocateRegistry.createRegistry(LOGINRMIPORT);
            registry.rebind(nome, stub);
            System.out.println("Server online listening on port " + LOGINRMIPORT);


        } catch (Exception e) {
            System.err.println("Errore di init registry!");
            e.printStackTrace();

        }

    }


    @Override
    public synchronized void verify() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il tuo username: ");
        userName = scanner.nextLine();
        System.out.println("Inserisci la tua password: ");
        passWord = scanner.nextLine();

        logIn(userName, passWord);

        //Lo metto synch percchè devo testare l' interazione dello scanner concorrenziale.
        //Se uno scrive lo username, e poi un' altro client invoca verify e scrive lo username cosa accade?


    }


    @Override
    public synchronized void logIn(String u, String p) {


        checkConnections();
    }


    //Dentro qua devo inserire il metodo che se accetto la connessione ritorno al chiamante la nuova porta privata
    // e il nome dello stub su cui gestire la partita.
    //Ritorno solo i dati perche il Server si occupa da solo dell' apertura dell altro registry, i client dovranno solo
    // connettersi.

    @Override
    public synchronized boolean checkConnections() {

        if (registrations.get(userName) == null) {

            if (matchStarted) {


                System.out.println("Partita iniziata, non è possibile aggiungere giocatori");
                return false;


            }

            //aggiungi un nuovo player e registra i suoi dati, inoltre viene aggiunto all' array di connessi.

            Player player = new Player();
            player.setNickname(userName);
            player.setConnectionAlive(true);
            registrations.put(userName, passWord);
            connectedPlayers.add(player);
            System.out.println("Aggiunto " + userName + " all' elenco di player connessi!");
            return true;


        }

        //verifica ste sta nell'arraylist di player per evitare doppie connessioni.

        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(userName)) {

                System.out.println("Utente gia registrato con queste credenziali");

                return false;
            }
        }


        if (registrations.get(userName) != null && passWord.equals(registrations.get(userName))) { //player disconnesso che tenta di riconnettersi.


            System.out.println("Bentornato " + userName); //successo
            return true;

        } else {

            System.out.println("password errata!"); //errore
            return false;

        }


    }


}
