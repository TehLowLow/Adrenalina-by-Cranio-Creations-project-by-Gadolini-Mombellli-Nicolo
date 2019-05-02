package it.polimi.se2019.Network.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMILoggerInterface extends Remote {


    int verify(String u, String p) throws RemoteException;

    int getGamePort(String s) throws RemoteException;




}
