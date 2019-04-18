package it.polimi.se2019.RMI.Server;

import it.polimi.se2019.Model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public String sendMessage() throws RemoteException;

    public Map buildMap(String map) throws RemoteException;

    //public void login(String nickname) throws RemoteException;

    public void ping(String nickname) throws RemoteException;

    public boolean logIn(String user, String pass) throws  RemoteException;

}
