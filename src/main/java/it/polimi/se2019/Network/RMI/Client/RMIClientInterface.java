package it.polimi.se2019.Network.RMI.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {

    String sendMsgWithAnswer(String msg) throws RemoteException;
  }
