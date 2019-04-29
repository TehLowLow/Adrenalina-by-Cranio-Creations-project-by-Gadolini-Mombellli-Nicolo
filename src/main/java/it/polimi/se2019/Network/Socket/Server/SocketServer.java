package it.polimi.se2019.Network.Socket.Server;

import it.polimi.se2019.Network.Logger;
import it.polimi.se2019.Network.Server;


public class SocketServer extends Server implements Runnable {


    @Override
    public void run() {

        //lancia i logger. I logger sono socket che eseguono su porte dedicate un controllo specifico di:
        // Presenza o meno di spazio nel server per connettere un utente con qualunque tecnologia
        // Verifica della validità della connessione di un utente, vale a dire username e password corrette, nessun doppio accesso ecc...
        // Registrazione dei nuovi utenti

        //Comodo da usare perchè permette di avere sempre un thread diverso da un thread di gioco che verifichi o meno la
        //possibilità di loggarsi.

        //TODO devo ancora gestire il fatto che occupato un thread ne devo creare un altro per ascoltare successive richieste.
        //Per ora il server genera un solo thread, utile per testare la validità del codice.

        Logger logger = new Logger();
        logger.start();

        System.out.println("Login started");

    }

}
