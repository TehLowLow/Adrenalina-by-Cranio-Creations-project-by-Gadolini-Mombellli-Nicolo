package it.polimi.se2019.Network.Socket.Server;


import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is the extension of the server responsible of handling all the Socket clients connected for a game.
 */

public class SocketServer extends Server implements Runnable {

    /**
     *  This is the Socket of the server listening for a player.
     */
    private ServerSocket mySocket;

    /**
     * Is the socket bound to a player.
     */
    private Socket gamerChannel;

    /**
     * Is the inputstream where the server listens for all the player's messages.
     */
    private DataInputStream in;

    /**
     * Is the outputstream where the server pushes all the messages.
     */
    private DataOutputStream out;

    /**
     * Constructor of the Socket server class.
     * @param s is the serversocket to bind to a player.
     */
    public SocketServer(ServerSocket s) {

        this.mySocket = s;
    }


    /**
     * Starts the server in Socket Mode for a Game.
     */
    @Override

    public void run() {

        try {

            gamerChannel = mySocket.accept();

            in = new DataInputStream(gamerChannel.getInputStream());

            String name = in.readUTF();
            int port = in.readInt();

            for (Player player : connectedPlayers) {

                if (player.getNickname().equalsIgnoreCase(name)) {

                    player.setPORT(port);
                    player.setConnectionAlive(true);

                }
            }

            playerClient.put(gamerChannel.getPort(), gamerChannel); //lego numero di porta e socket.


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
