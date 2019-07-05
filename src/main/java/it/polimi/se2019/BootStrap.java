package it.polimi.se2019;

import it.polimi.se2019.Network.Client;
import it.polimi.se2019.Network.Server;

/**
 * This class loads the game.
 */
public class BootStrap {

    public static void main(String[] args) {

        if (args.length != 0 && args[0].equalsIgnoreCase("server")) {

            Server.main(args);

        } else {

            Client.main(args);

        }
    }
}



