package it.polimi.se2019.RMI.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    /**
     * Is the main communication infrastructure from server to client. The server uses this method that prints on the
     * client CLI the information needed.
     * @param s is the message string
     * @throws RemoteException
     */
    public void serverMessage(String s) throws RemoteException;


}
