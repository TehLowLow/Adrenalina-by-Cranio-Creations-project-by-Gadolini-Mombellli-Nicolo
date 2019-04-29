package it.polimi.se2019.Network.RMI;


import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Logger;
import org.jetbrains.annotations.Contract;

import static it.polimi.se2019.Network.Server.connectedPlayers;
import static it.polimi.se2019.Network.Server.registrations;

public class RMILogger implements Logger {


    private String userName;
    private String passWord;
    private boolean connected = false;


    @Contract(pure = true)
    RMILogger(String user, String psw) {

        this.userName = user;
        this.passWord = psw;

    }


    @Override
    public synchronized void logIn() {

        while (!connected) {

            checkConnections();

        }

        //inserire anche qua metodo di secondo registry per il login
        //la partita si svolge su un altra porta e su un altro registry



    }

    @Override
    public synchronized boolean checkConnections() {

        if (registrations.get(userName) == null) {

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
                connected = true;
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
