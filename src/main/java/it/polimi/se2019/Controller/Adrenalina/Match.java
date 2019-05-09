package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Setup.MapSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.*;

import java.util.ArrayList;
import java.util.Collections;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;

import static it.polimi.se2019.Controller.Adrenalina.Check.checkStartFrenzy;
import static it.polimi.se2019.Network.Server.*;


/**
 * This class represents a single match of Adrenalina.
 * Its purpose is to iterate through the players and make them play a turn.
 */
public class Match extends Thread {


    /*
     * ---------- FIELDS ----------
     */

    /**
     * Turn is a parameter that holds a Turn object.
     */
    private Turn turn;

    /**
     * This is needed to perform various checks.
     */
    private Check check ;

    /**
     * ArrayList of players who take part to the match.
     */
    private ArrayList<Player> players;

    /**
     * Board used for the match.
     */
    private Board board;

    private boolean finish = false;

    private static Interaction interaction = new Interaction();



    /*
     * ---------- METHODS ----------
     */


    /**
     * Constructor for the Match class. It just creates an instance of Turn.
     */
    public Match() {

        this.turn = new Turn();


    }


    /**
     * When called, this method starts the match.
     * This methods comprehends three cycles. The first one iterates through the ArrayList of players one time
     * and makes them perform the first turn, which requires particular actions, different from the ones of the
     * standard turn.
     * Then it starts iterating through the players and at every step makes them perform a turn. At the end of
     * every step, the methods for replacing picked up loots and weapons on the map are called. This loop
     * goes on until all the Skulls on the Killshot track have been removed. When this happens, the current loop
     * ends and starts the Final Frenzy Mode loop, which iterates one last time through the ArrayList of Players
     * by making them perform their last Final Frenzy turn.
     * After this final loop, the methods for checking the winner are called, and then the match ends.
     */
    public void run() {

        chooseMap();
        chooseSkulls();
        chooseChampion();
        chooseFirstPlayer();

        updateAll("Inizio partita");


        for (Player player : connectedPlayers) {

            Turn t = new Turn();
            t.firstSpawn(player);
            interaction.placeLoot(board);
            interaction.placeWeapons(board);
        }


        while (!finish) {

            for (Player player : connectedPlayers) {

                Turn t = new Turn();
                finish = checkStartFrenzy();
                interaction.placeLoot(board);
                interaction.placeWeapons(board);
            }

        }

        for (Player player : connectedPlayers) {

            Turn t = new Turn();
            t.frenzy(player);

        }

        check = new Check();
        check.winner(board);
    }

    /**
     * Setter for the ArrayList of players that take part at the Match.
     *
     * @param players ArrayList of players.
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Setter for the board used in the current match.
     *
     * @param board the board used in the current match.
     */
    public void setBoard(Board board) {

        this.board = board;

    }

    public void chooseMap() {

        ArrayList<Integer> voti = new ArrayList<>();

        /*
        inizializzo l'array con 4 elementi settati a zero
         */

        voti.add(0);
        voti.add(0);
        voti.add(0);
        voti.add(0);



        /*
        valore del voto
         */
        int value;

        InputCheck inputCheck = new InputCheck();

        for (Player player : connectedPlayers) {

            String chosenMap = updateWithAnswer(player, Message.scegliMappa());

            while (!inputCheck.checkMapInput(chosenMap)) {

                update(player, Message.inputError());
                chosenMap = updateWithAnswer(player, Message.scegliMappa());

            }

            value = Integer.parseInt(chosenMap);

            /*
            aumento il contatore alla posizione del voto ricevuto
             */

            voti.set(value, voti.get(value) + 1);


        }

        int max = Collections.max(voti);

        int mapNumber = voti.indexOf(max) + 1;

        MapSetup mapSetup = new MapSetup();
        board.setMap(mapSetup.build(mapNumber));

        for (Player player : connectedPlayers) {

            update(player, "La mappa scelta è la numero " + mapNumber);

        }

    }

    /**
     * This method let the player choose the number of skulls to place on the mortalblow track.
     * The number has to be between 5 and 8.
     * The players will vote their favourite number of skulls and finally the chosen number will be the average of
     * the votes, approximated by excess.
     */

    public void chooseSkulls() {

        ArrayList<Integer> preferenze = new ArrayList<>();

        /*
        valore del voto
         */
        int value;

        InputCheck inputCheck = new InputCheck();

        for (Player player : connectedPlayers) {

            String chosenSkullNumber = updateWithAnswer(player, Message.scegliNumeroTeschi());

            while (!inputCheck.checkSkullsInput(chosenSkullNumber)) {

                update(player, Message.inputError());
                chosenSkullNumber = updateWithAnswer(player, Message.scegliNumeroTeschi());

            }

            value = Integer.parseInt(chosenSkullNumber);

            /*
            aggiungo nell'array il voto di ogni utente
             */

            preferenze.add(value);

        }

        /*
        faccio la media dei voti
         */

        float average;
        int sum = 0;

        for (int number : preferenze) {
            sum = sum + number;
        }

        average = (((float) sum) / preferenze.size());

        /*
        approssimo per eccesso (ceil è un metodo della libreria java.Math che ritorna l'approx per eccesso
        di un float o un double)
         */

        int nSkulls = (int) Math.ceil(average);


        /*
        setting del tracciato colpo mortale usando nSkulls teschi
         */

        ArrayList<MortalBlow> mortalBlowTrack = new ArrayList<>();


        for (int i = 0; i < nSkulls; i++) {

            MortalBlow skull = new MortalBlow();

            skull.setOverkill(false);
            skull.setSkull(true);
            skull.setKiller(null);

            mortalBlowTrack.add(skull);

        }

        Board.setMortalBlowTrack(mortalBlowTrack);

        /*
        notifico ai giocatori il numero di teschi con cui giocheranno
         */

        for (Player player : connectedPlayers) {

            update(player, "Il numero di teschi scelto per iniziare la partita è " + nSkulls);

        }


    }


    public static void chooseChampion() {

        ArrayList<String> champions = new ArrayList<>();

        champions.add("Banshee");
        champions.add(":D-strutt-or3");
        champions.add("Dozer");
        champions.add("Violetta");
        champions.add("Sprog");

        for (Player player : connectedPlayers) {

            update(player, "Scegli il tuo campione tra:");

            for (String champion : champions) {

                update(player, "" + champion);

            }

            String scelta = updateWithAnswer(player, "Fai la tua scelta");

            while (!InputCheck.chooseChampion(scelta)) {

                update(player, Message.inputError());
                scelta = updateWithAnswer(player, "Fai la tua scelta");
            }

            for (String champion : champions) {

                if (scelta.equalsIgnoreCase(champion)) {

                    player.getPlayerboard().setChampionName(champion);

                    champions.remove(champion);

                    update(player, "Hai scelto " + champion);

                }

            }
        }


    }


    public static void chooseFirstPlayer() {

        Collections.shuffle(connectedPlayers);
        connectedPlayers.get(0).setFirstPlayer(true);

    }


}
