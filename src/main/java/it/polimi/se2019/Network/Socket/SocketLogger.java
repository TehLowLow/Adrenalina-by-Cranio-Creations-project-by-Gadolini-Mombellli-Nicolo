package it.polimi.se2019.Network.Socket;

import it.polimi.se2019.Network.Logger;
import org.jetbrains.annotations.Contract;

public class SocketLogger implements Logger, Runnable {

    private String userName;
    private String passWord;


    @Contract(pure = true)
    SocketLogger(String username, String psw) {

        this.userName = username;
        this.passWord = psw;


    }

    @Override
    public void run() {

        //Esegue sempre ascoltando sulla porta helper. Accetta una connessione per un login e risponde con accesso consentito o negato

    }

    @Override
    public synchronized void logIn() {

        //utilizza checkconnections per verificare se l' utente sia o meno valido




    }


    @Override
    public synchronized boolean checkConnections() {

        return false;

    }






}

