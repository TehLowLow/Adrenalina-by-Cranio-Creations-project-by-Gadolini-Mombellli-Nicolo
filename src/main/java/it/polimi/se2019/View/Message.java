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

    public static String scegliDirezioneSparo(){
        return "In quale direzione vuoi sparare?\n- alto\n- basso\n- sinistra\n- destra";
    }

    public static String scegliDirezioneMossa(Player user, ArrayList <Cell> reachables){

        String str = "In quale direzione vuoi muoverti?\n";

        for(Cell cell : reachables){

            if(cell.equals(user.getPosition().getUpConnection().getConnectedCell())){
                str = str + "- Alto\n";
            }

            if(cell.equals(user.getPosition().getLeftConnection().getConnectedCell())){
                str = str + "- Sinistra\n";
            }

            if(cell.equals(user.getPosition().getDownConnection().getConnectedCell())){
                str = str + "- Basso\n";
            }
            if(cell.equals(user.getPosition().getRightConnection().getConnectedCell())){
                str = str + "- Destra\n";
            }

        }

        return str;
    }
    public static String direzioneOstruita(){
        return "Questa direzione è ostruita! Scegline un'altra.";
    }

    public static String spostaBersaglio(Player target, ArrayList <Cell> cells){

        String str = "In quale direzione vuoi spostare il bersaglio?\n";

        for(Cell cell : cells){

            if(target.getPosition().getUpConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getUpConnection().getType().equalsIgnoreCase(Connection.DOOR)){
                if(target.getPosition().getUpConnection().getConnectedCell().equals(cell)){
                    str = str + "alto\n";
                }
            }

            if(target.getPosition().getDownConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getDownConnection().getType().equalsIgnoreCase(Connection.DOOR)){
                if(target.getPosition().getDownConnection().getConnectedCell().equals(cell)){
                    str = str + "basso\n";
                }
            }

            if(target.getPosition().getLeftConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getLeftConnection().getType().equalsIgnoreCase(Connection.DOOR)){
                if(target.getPosition().getLeftConnection().getConnectedCell().equals(cell)){
                    str = str + "sinistra\n";
                }
            }

            if(target.getPosition().getRightConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getRightConnection().getType().equalsIgnoreCase(Connection.DOOR)){
                if(target.getPosition().getRightConnection().getConnectedCell().equals(cell)){
                    str = str + "destra\n";
                }
            }

        }

        return str;

    }

    public static String vuoiSpostare(){
        return "Vuoi spostare il bersaglio?";
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
        return "Hai inserito un bersaglio non valido, non fare il furbo. Scegline uno tra quelli disponibili.";
    }

    public static String scegliAltroBersaglio(){
        return "Vuoi scegliere un altro bersaglio?";
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


            if(user.getPosition().getUpConnection().getConnectedCell().equals(cell)){

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

            if(user.getPosition().getDownConnection().getConnectedCell().equals(cell)){

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

            if(user.getPosition().getLeftConnection().getConnectedCell().equals(cell)){

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

            if(user.getPosition().getRightConnection().getConnectedCell().equals(cell)){

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

    public static String scegliCellaVortex(ArrayList <Cell> cells){

        String str = "Scegli la cella dove aprire il vortex indicandone il numero.\n";


        for(Cell cell : cells){

            str = str + "Numero " + cells.indexOf(cell) + ")";
            str = str + "Stanza di colore " + Printer.colour(cell.getColour());
            str = str + ", cella: " + cell.getName();
            str = str + '\n';

        }

        return str;

    }

    public static String scegliCella(ArrayList <Cell> cells){

        String str = "Scegli una cella indicandone il numero.\n";


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

     public static String usareEffettoOpzionale(){

         return "Vuoi usare l'effetto opzionale?";
     }

     public static String usareEffettoBase(){

         return "Vuoi usare l'effetto base?";
     }

    public static String lanciaGranateScegliEffetto() {

         return "Vuoi usare l'effetto base o la granata extra?\nDigita 'base' per l'effetto base, 'extra' per la granata extra.";

    }

    public static String mancaRYB(){

         return "Non hai abbastanza cubi munizione per questa azione!";
    }

    public static String usaTestata(){

         return "Vuoi usare la testata a frammentazione?";
    }


    public static String usareRazzi(){

         return "Vuoi utilizzare i razzi? Potrai attivarli prima o dopo l'effetto base.";

    }

    public static String usaRazziPortatiliPrima(){

         return "Vuoi usare i razzi portatili adesso?";

    }

    public static String usaShadowStep(){

         return "Vuoi usare il passo d'ombra adesso?";

    }

    public static String useDice(){

         return "Vuoi attivare la modalità sminuzzare?";

    }

    public static String payWithPowerUp(ArrayList <Powerup> powerups){

        String str = "Scegli un power up da scartare per pagare il costo indicandone il numero.\n";

        for(Powerup powerup : powerups){
            str = str + powerups.indexOf(powerup) + ") " + powerup.getName() + "\n";
        }

        return str;

    }

    public static String vuoiSpostartiAncora(){
         return "Vuoi spostarti ancora?";
    }

    public static String direzioneSenzaBersagli(){

         return "Qui non colpiresti nessuno!";
    }

}

