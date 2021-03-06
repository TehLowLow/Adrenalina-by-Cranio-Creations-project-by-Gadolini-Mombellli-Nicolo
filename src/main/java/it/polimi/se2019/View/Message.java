package it.polimi.se2019.View;

import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This clas defines all the messages that are sent through the network and shown to the user. Italian language only.
 */
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
    SCELTA CAMPIONE
     */

    public static String choseChampion(CopyOnWriteArrayList<String> champions) {

        String message = "Scegli il tuo campione tra:\n";

        for (String c : champions) {

            message = message + c + "\n";

        }

        return message;

    }

    /*
    SCELTA DIREZIONE
     */

    public static String scegliDirezioneSparo() {
        return "In quale direzione vuoi sparare?\n- alto\n- basso\n- sinistra\n- destra";
    }

    public static String scegliDirezioneMossa(Player user, CopyOnWriteArrayList<Cell> reachables) {

        String str = "In quale direzione vuoi muoverti?\n";

        for (Cell cell : reachables) {

            if (user.getPosition().getUpConnection().getConnectedCell() != null && cell.equals(user.getPosition().getUpConnection().getConnectedCell())) {
                str = str + "- Alto\n";
            }

            if (user.getPosition().getLeftConnection().getConnectedCell() != null && cell.equals(user.getPosition().getLeftConnection().getConnectedCell())) {
                str = str + "- Sinistra\n";
            }

            if (user.getPosition().getDownConnection().getConnectedCell() != null && cell.equals(user.getPosition().getDownConnection().getConnectedCell())) {
                str = str + "- Basso\n";
            }
            if (user.getPosition().getRightConnection().getConnectedCell() != null && cell.equals(user.getPosition().getRightConnection().getConnectedCell())) {
                str = str + "- Destra\n";
            }

        }

        return str;
    }

    public static String direzioneOstruita() {
        return "Questa direzione è ostruita! Scegline un'altra.";
    }

    public static String spostaBersaglio(Player target, CopyOnWriteArrayList<Cell> cells) {

        String str = "In quale direzione vuoi spostare il bersaglio?\n";

        for (Cell cell : cells) {

            if (target.getPosition().getUpConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getUpConnection().getType().equalsIgnoreCase(Connection.DOOR)) {
                if (target.getPosition().getUpConnection().getConnectedCell().equals(cell)) {
                    str = str + "alto\n";
                }
            }

            if (target.getPosition().getDownConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getDownConnection().getType().equalsIgnoreCase(Connection.DOOR)) {
                if (target.getPosition().getDownConnection().getConnectedCell().equals(cell)) {
                    str = str + "basso\n";
                }
            }

            if (target.getPosition().getLeftConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getLeftConnection().getType().equalsIgnoreCase(Connection.DOOR)) {
                if (target.getPosition().getLeftConnection().getConnectedCell().equals(cell)) {
                    str = str + "sinistra\n";
                }
            }

            if (target.getPosition().getRightConnection().getType().equalsIgnoreCase(Connection.FREE) || target.getPosition().getRightConnection().getType().equalsIgnoreCase(Connection.DOOR)) {
                if (target.getPosition().getRightConnection().getConnectedCell().equals(cell)) {
                    str = str + "destra\n";
                }
            }

        }

        return str;

    }

    public static String vuoiSpostare() {
        return "Vuoi spostare il bersaglio? (si/no)";
    }


    /*
    SCELTA BERSAGLIO
     */

    public static String scegliBersaglio(CopyOnWriteArrayList<Player> players) {

        String string = "Scegli un bersaglio tra:\n";

        for (Player player : players) {
            string = string + player.getNickname() + "\n";
        }

        return string;
    }

    public static String bersaglioNonValido() {
        return "Hai inserito un bersaglio non valido, non fare il furbo. Scegline uno tra quelli disponibili.";
    }

    public static String scegliAltroBersaglio() {
        return "Vuoi scegliere un altro bersaglio? (si/no)";
    }

    public static String nessunBersaglioFase() {
        return "Se ti sposti qui non potrai colpire nessuno.";
    }

    /*
    SCELTA STANZA
     */

    public static String scegliStanza(CopyOnWriteArrayList<Integer> rooms) {

        String str = "Scegli una stanza tra:";

        for (Integer room : rooms) {

            str = str + "\n" + Printer.colour(room);


        }

        return str;
    }

    public static String stanzaNonVisibile() {
        return "Non puoi vedere questa stanza! Scegline un'altra.";
    }

    /*
    SCELTA CELLA
     */

    //Scelta cella per Furnace Weapon. Se una cella non contiene giocatori, non viene mostrata tra quelle disponibili.
    public static String scegliCellaFurnace(CopyOnWriteArrayList<Cell> cells, Player user) {

        String str = "Scegli una cella (digitando 'alto', 'basso', 'sinistra' o 'destra') tra:";


        for (Cell cell : cells) {


            if (!user.getPosition().getUpConnection().getType().equals(Connection.EDGE) && !user.getPosition().getUpConnection().getType().equals(Connection.WALL)) {

                for (Player target : Server.connectedPlayers) {
                    if (target.getPosition().equals(user.getPosition().getUpConnection().getConnectedCell())) {
                        str = str + "\n" + "- Cella in alto: ";
                    }
                }

                for (Player target : Server.connectedPlayers) {

                    if (target.getPosition().equals(cell)) {
                        str = str + target.getNickname() + " ";
                    }

                }

                str = str + '\n';

            }

            if (!user.getPosition().getDownConnection().getType().equals(Connection.EDGE) && !user.getPosition().getDownConnection().getType().equals(Connection.WALL)) {

                for (Player target : Server.connectedPlayers) {
                    if (target.getPosition().equals(user.getPosition().getDownConnection().getConnectedCell())) {
                        str = str + "\n" + "- Cella in basso: ";
                    }
                }

                for (Player target : Server.connectedPlayers) {

                    if (target.getPosition().equals(cell)) {
                        str = str + target.getNickname() + " ";
                    }

                }

                str = str + '\n';

            }

            if (!user.getPosition().getLeftConnection().getType().equals(Connection.EDGE) && !user.getPosition().getLeftConnection().getType().equals(Connection.WALL)) {

                for (Player target : Server.connectedPlayers) {
                    if (target.getPosition().equals(user.getPosition().getLeftConnection().getConnectedCell())) {
                        str = str + "\n" + "- Cella a sinistra: ";
                    }
                }

                for (Player target : Server.connectedPlayers) {

                    if (target.getPosition().equals(cell)) {
                        str = str + target.getNickname() + " ";
                    }

                }

                str = str + '\n';

            }

            if (!user.getPosition().getRightConnection().getType().equals(Connection.EDGE) && !user.getPosition().getRightConnection().getType().equals(Connection.WALL)) {

                for (Player target : Server.connectedPlayers) {
                    if (target.getPosition().equals(user.getPosition().getRightConnection().getConnectedCell())) {
                        str = str + "\n" + "- Cella a destra: ";
                    }
                }

                for (Player target : Server.connectedPlayers) {

                    if (target.getPosition().equals(cell)) {
                        str = str + target.getNickname() + " ";
                    }

                }

                str = str + '\n';

            }


        }


        return str;
    }

    public static String scegliCellaVortex(CopyOnWriteArrayList<Cell> cells) {

        String str = "Scegli la cella dove aprire il vortex indicandone il numero.\n";


        for (Cell cell : cells) {

            str = str + "Numero " + cells.indexOf(cell) + ")";
            str = str + "Stanza di colore " + Printer.colour(cell.getColour());
            str = str + ", cella: " + cell.getName();
            str = str + '\n';

        }

        return str;

    }

    public static String scegliCella(CopyOnWriteArrayList<Cell> cells) {

        String str = "Scegli una cella indicandone il numero.\n";


        for (Cell cell : cells) {

            str = str + "Numero " + cells.indexOf(cell) + ")";
            str = str + "Stanza di colore " + Printer.colour(cell.getColour());
            str = str + ", cella: " + cell.getName();
            str = str + '\n';

        }

        return str;

    }

    public static String cellaNonDisponibile() {
        return "Errore: scegli una cella disponibile.";
    }

    public static String cellaVortexNonDisponibile() {
        return "Errore: aprire il vortice qui non avrebbe alcun effetto! Scegli un'altra cella.";
    }

    /*
    VORTEX
     */

    public static String vortexAperto(Cell vortexCell) {

        return "Hai creato un vortice nella cella " + vortexCell.getName() + " di colore " + Printer.colour(vortexCell.getColour()) + "!";

    }


    /*
    INPUT
     */

    public static String inputError() {
        return "Input Errato!";
    }

    public static String nessunBersaglio() {
        return "Nessun bersaglio disponibile qui!";
    }


    /*
    NOTIFICA AL BERSAGLIO COLPITO
     */

    public static String colpito(Player attacker) {
        return "Sei stato colpito da " + attacker.getNickname();
    }

    /*
    SCELTA NUMERO TESCHI
     */

    public static String scegliNumeroTeschi() {
        return "Scegli un numero di teschi da piazzare nel tracciato colpo mortale tra 5 e 8.";
    }

    /*
    SCELTA EFFETTO
     */

    public static String usareEffettoOpzionale() {

        return "Vuoi usare l'effetto opzionale? (si/no)";
    }

    public static String usareEffettoBase() {

        return "Vuoi usare l'effetto base? (si/no)";
    }

    public static String lanciaGranateScegliEffetto() {

        return "Vuoi usare l'effetto base o la granata extra?\nDigita 'base' per l'effetto base, 'extra' per la granata extra.";

    }


    public static String usaTestata() {

        return "Vuoi usare la testata a frammentazione? (si/no)";
    }


    public static String usareRazzi() {

        return "Vuoi utilizzare i razzi? Potrai attivarli prima o dopo l'effetto base. (si/no)";

    }

    public static String usaRazziPortatiliPrima() {

        return "Vuoi usare i razzi portatili adesso? (si/no)";

    }

    public static String usaShadowStep() {

        return "Vuoi usare il passo d'ombra adesso? (si/no)";

    }

    public static String useDice() {

        return "Vuoi attivare la modalità sminuzzare? (si/no)";

    }

    public static String payWithPowerUp(CopyOnWriteArrayList<Powerup> powerups) {

        String str = "Scegli un power up da scartare per pagare il costo indicandone il numero.\n";

        for (Powerup powerup : powerups) {
            str = str + powerups.indexOf(powerup) + ") " + powerup.getName() + "\n";
        }

        return str;

    }

    public static String powerupTerminati() {

        return "Non hai più powerup da convertire in cubi munizione.";
    }

    public static String vuoiPagareConPU() {

        return "Vuoi utilizzare un PowerUp per pagare? (si/no)";

    }

    public static String vuoiPagareConPUAncora() {

        return "Vuoi pagare con un altro powerup? (si/no)";

    }

    public static String vuoiSpostartiAncora() {
        return "Vuoi spostarti ancora? (si/no)";
    }

    public static String direzioneSenzaBersagli() {

        return "Qui non colpiresti nessuno!";
    }


    public static String scegliBersaglioTurretTripod() {

        return "Scegli 0 per colpire il bersaglio dell'effetto base, 1 per colpire un altro bersaglio o 2 per colpirli tutti e due";

    }

    public static String usareSlittamento() {
        return "Vuoi attivare lo slittamento di fase ora? (si/no)";
    }

    public static String usareSovraccarico() {
        return "Vuoi usare il colpo sovraccarico? (si/no)";
    }

    public static String scegliColoreAmmo() {
        return "Scegli il colore di una munizione da scartare:\n- giallo\n- rosso\n- blu";
    }

    public static String cubiInsuff() {
        return "Non hai abbastanza cubi munizione.";
    }

    public static String stepNumber() {
        return "Quanti passi vuoi fare?";
    }

    public static String movedTo() {
        return "Ti sei spostato!";
    }



    /*
    SPARARE
     */

    public static String scegliArma(CopyOnWriteArrayList<Weapon> weapons) {

        String stringaFinale = "Scegli un'arma digitandone il nome:\n";
        ;

        for (Weapon weapon : weapons) {

            stringaFinale += weapon.getName() + "\n";

        }

        return stringaFinale;

    }

    /*
    EFFETTO BASE O ALTERNATIVO
     */

    public static String scegliBaseAlternativo() {

        return "Scegli se usare l'effetto base o quello alternativo (base/alternativo)";

    }


    public static String scegliAzione() {

        return "Scegli un'azione tra:\nSpara\nMuovi\nRaccogli";

    }

    public static String scegliAzioneFrenesiaFP() {

        return "Scegli un'azione tra:\nSpara\nRaccogli";

    }

    public static String noSparo() {
        return "Non puoi sparare: da questa posizione non colpiresti nessuno!";
    }

    public static String vuoiMuovertiPU() {
        return "Vuoi muoverti prima di raccogliere? (si/no)";
    }

    public static String limitePowerup() {
        return "Non hai più spazio per raccogliere altri power up.";
    }

    public static String scegliPowerUpSpawn(CopyOnWriteArrayList<Powerup> powerups) {

        String str = "Scegli un powerup da scartare indicandone il numero. Spawnerai nella cella di quel colore.\n";

        for (Powerup powerup : powerups) {

            str = str + powerups.indexOf(powerup) + ") " + powerup.getName() + "\n";

        }

        return str;

    }

    public static String powerUpSpawnScelto(Player player, Powerup powerup) {

        return "Il giocatore " + player.getNickname() + " ha scelto di scartare " + powerup.getName();

    }

    public static String spawn(Player player, int colour) {

        return "Il giocatore " + player.getNickname() + " è spawnato nella cella di colore " + Printer.colour(colour) + ".";

    }

    public static String vuoiRicaricare() {
        return "Vuoi ricaricare qualche arma? (si/no)";
    }

    public static String haiRicaricato(Weapon weapon) {

        return "Hai ricaricato " + weapon.getName();

    }

    /*
    Enhanced shot scelta movimento
     */

    public static String scegliMovimento() {

        return "Scegli una direzione (su/giu/destra/sinistra) o se stare fermo (stop)";

    }

    public static String vuoiUsarePowerup() {
        return "Vuoi usare un potenziamento? (si/no)";
    }

    public static String scegliPowerUp(CopyOnWriteArrayList<Powerup> powerups) {

        String stringaFinale = "Scegli un potenziamento:\n";
        ;

        for (Powerup powerup : powerups) {

            stringaFinale += powerup.getName() + "\n";

        }

        return stringaFinale;

    }

    public static String vuoiColpire() {
        return "Vuoi colpire qualcuno? (si/no)";
    }

    public static String nessunLoot() {
        return "Non c'è nessun loot qua, raccogli in un altra cella";
    }

    public static String scegliArmaRF(CopyOnWriteArrayList<Weapon> useableWeapons) {

        String finalString = "";

        finalString = finalString + "Queste sono le armi che puoi ricaricare e che hanno bersagli a disposizione:\n";

        for (Weapon weapon : useableWeapons) {

            finalString = finalString + weapon.getName() + "\n\n";

        }

        finalString = finalString + "Scegline una tra quelle elencate sopra!\n";

        return finalString;

    }


    public static String altroBersaglio() {

        return "Vuoi colpire un altro bersaglio?";

    }

}



