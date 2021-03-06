package it.polimi.se2019.Network.Socket;

import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Logger;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static it.polimi.se2019.Network.Server.*;

/**
 * This class ahndels the login coming from all the socket clients.
 */

public class SocketLogger implements Logger, Runnable {

    /**
     * Is the username of the player.
     */
    private String userName;
    /**
     * Is the password of the player.
     */
    private String passWord;
    /**
     * Is the socket bound to the player that is logging in
     */
    private Socket logMeIn;

    /**
     * Is the serversocket listening for connections.
     */
    private ServerSocket mySocket;

    /**
     * Is the inputstream where the server listens for messages.
     */
    private DataInputStream in;

    /**
     * Is the outputstream where the server pushes the messages for the client.
     */
    private DataOutputStream out;

    /**
     * Temporary player used for registrations inside connectedPlayers.
     */
    private Player temp;


    /**
     * The constructor of the login class.
     *
     * @param serverSocket is the socket started into the executor pool to be assigned to a single server object.
     */


    public SocketLogger(ServerSocket serverSocket) {
        this.mySocket = serverSocket;
    }

    /**
     * Starts the server side connection for a player.
     */
    @Override
    public void run() {

        //Esegue sempre ascoltando sulla porta helper. Accetta una connessione per un login e risponde con accesso consentito o negato

        try {
            logMeIn = mySocket.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }

        in = inStream(logMeIn);
        out = outStream(logMeIn);

        logIn();
    }


    /**
     * Verifies the player credentials.
     *
     * @return true if a player is allowed into the game server.
     */
    @Override
    public synchronized int logIn() {

        try {
            out.writeUTF("Inserisci username: ");
            userName = in.readUTF();
            out.writeUTF("Inserisci la password: ");
            passWord = in.readUTF();


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (checkConnections()) {

            try {
                out.writeInt(SOCKETPORT);

                return 1;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //utilizza checkconnections per verificare se l' utente sia o meno valido
        //Se true ritorno all utente la porta socket su cui connettersi alla vera partita.

        return 0;

    }


    /**
     * This method is the verification process for the login, checks for a valid username/password combination and allows
     * the player into the lobby
     *
     * @return true if the player has given valid credentials.
     */

    @Override
    public boolean checkConnections() {

        //Il player non si è mai connesso
        if (registrations.get(userName) == null) {
            //Se il match è gia iniziato rifiuto la connessione
            if (matchStarted) {
                try {
                    out.writeUTF("Partita gia iniziata, non è possibile accedere");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
                //Se il match è ancora in fase di lobby lo aggiungo fra i player
            } else if (connectedSize() < 5) {
                temp = newPlayer(userName, passWord, "Socket");
                try {
                    out.writeUTF("Aggiunto " + userName);
                    //out.writeInt(temp.getPORT());
                    //out.writeInt(SOCKETPORT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

        //Se il player compare fra i giocatori che si son gia registrati una volta devo verificare che sia disconnesso (evito double connection)
        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(userName) && player.isConnected()) {

                try {
                    out.writeUTF("Un player è gia connesso con queste credenziali");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }


        //Se si è gia connesso ma non compare fra i connessi, gli permetto di riconnettersi
        if (registrations.get(userName) != null && passWord.equals(registrations.get(userName)) && matchStarted) {

            try {
                out.writeUTF("Bentornato " + userName);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;


        } else {


            try {
                out.writeUTF("Credenziali errate!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }


    }

    /**
     * Starts the input stream for the server
     *
     * @param socket is the socket where inputs from the client will come.
     * @return the inputstream for communication.
     */

    private synchronized DataInputStream inStream(Socket socket) {
        try {
            return new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Starts the output stream for the server
     *
     * @param socket is the socket where outputs for the vclient will be pushed.
     * @return the outputstream for communication.
     */
    private synchronized DataOutputStream outStream(Socket socket) {

        try {
            return new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
