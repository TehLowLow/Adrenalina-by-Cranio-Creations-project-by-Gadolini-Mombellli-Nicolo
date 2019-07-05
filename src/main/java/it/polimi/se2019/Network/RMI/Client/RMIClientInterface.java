package it.polimi.se2019.Network.RMI.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Theis is the interface containing all the methods that can be invoked by the remote server onto the client.
 */

public interface RMIClientInterface extends Remote {

    String sendMsgWithAnswer(String msg) throws RemoteException;

    void sendMsg(String msg) throws RemoteException;
}
