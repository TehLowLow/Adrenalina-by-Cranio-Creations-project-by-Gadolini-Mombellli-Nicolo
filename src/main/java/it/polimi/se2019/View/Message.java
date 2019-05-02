package it.polimi.se2019.View;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;

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
    SCELTA CELLA
     */

    //Scelta cella per Furnace Weapon. Se una cella non contiene giocatori, non viene mostrata tra quelle disponibili.
    public static String scegliCellaFurnace(ArrayList <Cell> cells, Player user){

        String str = "Scegli una cella (digitando 'alto', 'basso', 'sinistra' o 'destra') tra:";


        for(Cell cell : cells){


            if(user.getPosition().getUpConnection().equals(cell)){

               for(Player target : Server.connectedPlayers){
                   if(target.getPosition().equals(cell)){
                       str = str + "\n" + "- Cella in alto: ";
                   }
               }

               for(Player target : Server.connectedPlayers){

                   if(target.getPosition().equals(cell)){
                       str = str + target.getNickname() + " ";
                   }

               }

               str = str + '\n';

           }

            if(user.getPosition().getDownConnection().equals(cell)){

                for(Player target : Server.connectedPlayers){
                    if(target.getPosition().equals(cell)){
                        str = str + "\n" + "- Cella in basso: ";
                    }
                }

                for(Player target : Server.connectedPlayers){

                    if(target.getPosition().equals(cell)){
                        str = str + target.getNickname() + " ";
                    }

                }

                str = str + '\n';

            }

            if(user.getPosition().getLeftConnection().equals(cell)){

                for(Player target : Server.connectedPlayers){
                    if(target.getPosition().equals(cell)){
                        str = str + "\n" + "- Cella a sinistra: ";
                    }
                }

                for(Player target : Server.connectedPlayers){

                    if(target.getPosition().equals(cell)){
                        str = str + target.getNickname() + " ";
                    }

                }

                str = str + '\n';

            }

            if(user.getPosition().getRightConnection().equals(cell)){

                for(Player target : Server.connectedPlayers){
                    if(target.getPosition().equals(cell)){
                        str = str + "\n" + "- Cella a destra: ";
                    }
                }

                for(Player target : Server.connectedPlayers){

                    if(target.getPosition().equals(cell)){
                        str = str + target.getNickname() + " ";
                    }

                }

                str = str + '\n';

            }


        }


        return str;
    }

    public static String scegliCellaVortex(ArrayList <Cell> cells, Player user){

        String str = "Scegli la cella dove aprire il vortex indicandone il numero.\n";


        for(Cell cell : cells){

            str = str + "Numero " + cells.indexOf(cell) + ")";
            str = str + "Stanza di colore " + Printer.colour(cell.getColour());
            str = str + ", cella: " + cell.getName();
            str = str + '\n';

        }

        return str;

    }

    public static String cellaNonDisponibile(){
        return "Errore: scegli una cella disponibile.";
    }

    public static String cellaVortexNonDisponibile(){
        return "Errore: aprire il vortice qui non avrebbe alcun effetto! Scegli un'altra cella.";
    }

    /*
    VORTEX
     */

    public static String vortexAperto(){

        return "Hai creato un vortice!";

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


    /*
    NOTIFICA AL BERSAGLIO COLPITO
     */

    public static String colpito(Player attacker){
        return "Sei stato colpito da " + attacker;
    }

    /*
    SCELTA NUMERO TESCHI
     */

    public static String scegliNumeroTeschi(){ return "Scegli un numero di teschi da piazzare nel tracciato colpo mortale tra 5 e 8.";}

    /*
    SCELTA EFFETTO
     */

     public static String usaEffetto(){

         return "Vuoi usare l'effetto opzionale?";
     }


}

