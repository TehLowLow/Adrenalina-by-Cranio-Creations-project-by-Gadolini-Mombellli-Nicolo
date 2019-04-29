package it.polimi.se2019.Network.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMILoggerInterface extends Remote {


    void verify() throws RemoteException;




}
