package it.polimi.se2019.Network.Deprecated;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface contains all the methods offered to the clients to interact over RMI.
 */
public interface ServerInterface extends Remote {

    //public String sendMessage() throws RemoteException;

    //public Map buildMap(String map) throws RemoteException;

    /**
     * Is used by the client to update his current state, if the server recives a ping, the client is still alive.
     * @param nickname is the name of the client that is pinging.
     * @throws RemoteException
     */
   // public void ping(String nickname) throws RemoteException;

    /**
     * Checks the username and password of any client that tries to connect to a game, either starting or already started
     * and performs user validation. If the user has never connected to the server, it is granted connection and saved
     * into a database. This method also stores a port number into another database allowing the client to be contacted
     * for a callback channel.
     * @param user is the username the player wants to use, and it must be unique.
     * @param pass is the player's password used for validation purposes
     * @return true if the player is granted access, false if the player is trying a double access.
     * @throws RemoteException
     */
    public boolean logIn(String user, String pass) throws  RemoteException;


    /**
     * Checks the connection database and gets the corresponding user's port for callback conction.
     * @param string is the name of the user for the table' lookup
     * @return the port number
     * @throws RemoteException
     */
    public int firstFreePort(String string) throws RemoteException;

    /**
     * Looks for any RMIregistry bound to String, and starts connection to the corresponding registry to contact the client.
     * @param string is the name of the client
     * @param port is the port number for registry lookup.
     * @throws RemoteException
     */
    public void initCallback(String string, int port) throws  RemoteException;

}
