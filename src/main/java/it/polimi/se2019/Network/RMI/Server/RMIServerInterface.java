package it.polimi.se2019.Network.RMI.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {


    void sendMsg(String msg) throws RemoteException;

    boolean logIn(String u, String p) throws RemoteException;

    void addPlayerHN(String Username, String Hostname) throws RemoteException;

    String guiUpdate(String status) throws RemoteException;

}
