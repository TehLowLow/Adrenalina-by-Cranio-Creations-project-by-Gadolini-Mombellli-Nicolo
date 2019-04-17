package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public void sendMessage() throws RemoteException;

    public Map buildMap(String map) throws RemoteException;

}
