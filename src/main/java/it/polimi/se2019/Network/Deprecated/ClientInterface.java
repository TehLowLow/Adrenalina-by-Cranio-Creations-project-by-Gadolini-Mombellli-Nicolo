package it.polimi.se2019.Network.Deprecated;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    /**
     * Is the main communication infrastructure from server to client. The server uses this method that prints on the
     * client CLI the information needed.
     * @param s is the message string
     * @throws RemoteException
     */
     void serverMessage(String s) throws RemoteException;


     void pingClient() throws RemoteException;


}
