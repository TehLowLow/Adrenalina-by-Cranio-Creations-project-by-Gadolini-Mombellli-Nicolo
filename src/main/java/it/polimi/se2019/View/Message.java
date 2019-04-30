package it.polimi.se2019.View;

import it.polimi.se2019.Model.Player;

import java.util.ArrayList;

public class Message {

    public static String start() {

        return new String("Benvenuto in Adrenalina!");

    }

    public static String askForUser() {

        return new String("Username:");

    }

    public static String askForPsw() {

        return new String("Password");
    }

    public static String logged() {

        return new String("Login Effettuato");
    }

    //--- DEBUG MESSAGES ---//

    public static void boardCompletata() {
        System.out.println("Costruzione board completa.");
    }

    public static void avvioMappa() {
        System.out.println("Avvio costruzione mappa...");
    }

    public static void mappaCompletata() {
        System.out.println("Costruzione mappa completata.");
    }

    /*
    SCELTA MAPPA
     */

    public static String scegliMappa() {
        return "Scegli in quale mappa combattere: 1 - 2 - 3 - 4";
    }

    /*
    SCELTA DIREZIONE
     */

    public static String scegliDirezione(){
        return "In quale direzione vuoi sparare?\nalto\nbasso\nsinistra\ndestra";
    }
    public static String direzioneOstruita(){
        return "Non puoi sparare in questa direzione! Scegline un'altra.";
    }

    /*
    SCELTA BERSAGLIO
     */

    public static String scegliBersaglio(ArrayList <Player> players){

        String string = "Scegli un bersaglio tra:\n";

        for(Player player : players){
            string = string + player.getNickname() + "\n";
        }

        return string;
    }

    public static String bersaglioNonValido(){
        return "Hai inserito un bersaglio non valido. Scegline uno tra quelli disponibili.";
    }

    /*
    SCELTA STANZA
     */

    public static String scegliStanza(ArrayList <Integer> rooms){

        String str = "Scegli una stanza tra:";

        for(Integer room : rooms){

            str = str + "\n" + Printer.colour(room);


        }

        return str;
    }

    public static String stanzaNonVisibile(){
        return "Non puoi vedere questa stanza! Scegline un'altra.";
    }
    /*
    INPUT
     */

    public static String inputError(){
        return "Input Errato!";
    }

    public static String nessunBersaglio(){
        return "Nessun bersaglio disponibile qui!";
    }


}

