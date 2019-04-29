package it.polimi.se2019.View;

public class Message {

    public String start() {

        return new String("Benvenuto in Adrenalina!");

    }

    public String askForUser() {

        return new String("Username:");

    }

    public String askForPsw() {

        return new String("Password");
    }

    public String logged() {

        return new String("Login Effettuato");
    }

    //--- DEBUG MESSAGES ---//

    public void boardCompletata() {
        System.out.println("Costruzione board completa.");
    }

    public void avvioMappa() {
        System.out.println("Avvio costruzione mappa...");
    }

    public void mappaCompletata() {
        System.out.println("Costruzione mappa completata.");
    }

    public void scegliMappa() {
        System.out.println("Scegli in quale mappa combattere: 1 - 2 - 3 - 4");
    }
}

