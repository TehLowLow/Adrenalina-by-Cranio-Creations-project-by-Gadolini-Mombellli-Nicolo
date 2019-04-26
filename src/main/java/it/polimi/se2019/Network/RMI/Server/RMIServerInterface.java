package it.polimi.se2019.Network.RMI.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {


    public void sendMsg(String msg) throws RemoteException;




}
