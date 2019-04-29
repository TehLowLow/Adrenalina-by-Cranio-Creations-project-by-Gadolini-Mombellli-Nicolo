package it.polimi.se2019.Network.Socket;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Logger;
import org.jetbrains.annotations.Contract;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static it.polimi.se2019.Network.Server.*;

public class SocketLogger implements Logger, Runnable {

    private String userName;
    private String passWord;
    private ServerSocket sSocket;
    private DataInputStream in;
    private DataOutputStream out;


    @Override
    public void run() {

        //Esegue sempre ascoltando sulla porta helper. Accetta una connessione per un login e risponde con accesso consentito o negato


        initServer();


    }


    private synchronized void initServer() {

        try {

            ServerSocket sSocket = new ServerSocket(LOGINSOCKETPORT);
            System.out.println("Server online listening on port " + LOGINSOCKETPORT);
            Socket logIn = sSocket.accept();
            DataOutputStream out = new DataOutputStream(logIn.getOutputStream());
            DataInputStream in = new DataInputStream(logIn.getInputStream());
            out.writeUTF("Inserisci username: ");
            userName = in.readUTF();
            out.writeUTF("Inserisci la password: ");
            passWord = in.readUTF();

            logIn(userName, passWord);

            logIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public synchronized void logIn(String u, String p) {


        if (checkConnections()) {

            try {
                out.writeInt(SOCKETPORT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //utilizza checkconnections per verificare se l' utente sia o meno valido
        //Se true ritorno all utente la porta socket su cui connettersi alla vera partita.

    }


    @Override
    public synchronized boolean checkConnections() {


        if (registrations.get(userName) == null) {

            if (matchStarted) {

                try {
                    out.writeUTF("Partita gia iniziata, non è possibile accedere");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;

            }

            Player player = new Player();
            player.setNickname(userName);
            player.setConnectionAlive(true);
            registrations.put(userName, passWord);
            connectedPlayers.add(player);
            try {
                out.writeUTF("Aggiunto " + userName);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(userName)) {

                try {
                    out.writeUTF("Un player è gia connesso con queste credenziali");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;

            }


        }


        if (registrations.get(userName) != null && passWord.equals(registrations.get(userName))) {

            try {
                out.writeUTF("Bentornato " + userName);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;


        } else {


            try {
                out.writeUTF("Password errata!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;


        }

    }


}

