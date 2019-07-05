package it.polimi.se2019.Network.Socket.Server;


import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is the extension of the server
 */

public class SocketServer extends Server implements Runnable {

    private ServerSocket mySocket;
    private Socket gamerChannel;
    private DataInputStream in;
    private DataOutputStream out;

    public SocketServer(ServerSocket s) {

        this.mySocket = s;
    }


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
