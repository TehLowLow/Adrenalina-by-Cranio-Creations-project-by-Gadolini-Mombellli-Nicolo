package it.polimi.se2019.RMI.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    public void serverMessage(String s) throws RemoteException;


}
