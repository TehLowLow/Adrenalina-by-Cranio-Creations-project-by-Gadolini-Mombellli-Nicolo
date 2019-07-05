package it.polimi.se2019.Network.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface contains all the methods that a client can invoke during login routine onto the server.
 */

public interface RMILoggerInterface extends Remote {


    int verify(String u, String p) throws RemoteException;

    int getGamePort(String s) throws RemoteException;

}
