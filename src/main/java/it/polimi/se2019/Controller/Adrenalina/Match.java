package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Setup.BoardSetup;
import it.polimi.se2019.Controller.Setup.MapSetup;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.View.Message;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Controller.Adrenalina.Check.checkFrenzy;
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
    private Check check;

    /**
     * CopyOnWriteArrayList of players who take part to the match.
     */
    private CopyOnWriteArrayList<Player> players;

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
     * This methods comprehends three cycles. The first one iterates through the CopyOnWriteArrayList of players one time
     * and makes them perform the first turn, which requires particular actions, different from the ones of the
     * standard turn.
     * Then it starts iterating through the players and at every step makes them perform a turn. At the end of
     * every step, the methods for replacing picked up loots and weapons on the map are called. This loop
     * goes on until all the Skulls on the Killshot track have been removed. When this happens, the current loop
     * ends and starts the Final Frenzy Mode loop, which iterates one last time through the CopyOnWriteArrayList of Players
     * by making them perform their last Final Frenzy turn.
     * After this final loop, the methods for checking the winner are called, and then the match ends.
     */
    public void run() {



        chooseMap();
        chooseSkulls();
        boolean terminator = chooseMode();

        /*
        inizializzo board
         */

        BoardSetup boardSetup = new BoardSetup();
        boardSetup.build();

        /*
        inizializzo i player
         */
        for (Player player:connectedPlayers){

            player.setPosition(Board.getLimbo());

            player.setPlayerboard(new Playerboard());
            player.setScore(0);
            player.getPlayerboard().setChampionName(null);
            player.getPlayerboard().setWeapons(new CopyOnWriteArrayList<>());
            player.getPlayerboard().setAmmoCubes(new Rybamount());
            player.getPlayerboard().getAmmoCubes().setBlueCubes(1);
            player.getPlayerboard().getAmmoCubes().setRedCubes(1);
            player.getPlayerboard().getAmmoCubes().setYellowCubes(1);
            player.getPlayerboard().setMarker(new CopyOnWriteArrayList<>());
            player.getPlayerboard().setDamage(new CopyOnWriteArrayList<>());
            player.getPlayerboard().setFrenzyboard(false);
            player.getPlayerboard().setPlayerboardValue(new CopyOnWriteArrayList<>());
            player.getPlayerboard().getPlayerboardValue().add(8);
            player.getPlayerboard().getPlayerboardValue().add(6);
            player.getPlayerboard().getPlayerboardValue().add(4);
            player.getPlayerboard().getPlayerboardValue().add(2);
            player.getPlayerboard().getPlayerboardValue().add(1);
            player.getPlayerboard().getPlayerboardValue().add(1);
            player.getPlayerboard().setPowerups(new CopyOnWriteArrayList<>());

        }

        chooseFirstPlayer();


        //PARTITA CON TERMINATOR
        if (terminator) {


            //inizializzo il terminator
            Board.setTerminator(new Player());
            Board.getTerminator().setPlayerboard(new Playerboard());
            Board.getTerminator().getPlayerboard().setFrenzyboard(false);
            Board.getTerminator().getPlayerboard().setDamage(new CopyOnWriteArrayList<>());
            Board.getTerminator().getPlayerboard().setMarker(new CopyOnWriteArrayList<>());
            Board.getTerminator().getPlayerboard().setPlayerboardValue(new CopyOnWriteArrayList<>());
            Board.getTerminator().getPlayerboard().getPlayerboardValue().add(8);
            Board.getTerminator().getPlayerboard().getPlayerboardValue().add(6);
            Board.getTerminator().getPlayerboard().getPlayerboardValue().add(4);
            Board.getTerminator().getPlayerboard().getPlayerboardValue().add(2);
            Board.getTerminator().getPlayerboard().getPlayerboardValue().add(1);
            Board.getTerminator().getPlayerboard().getPlayerboardValue().add(1);
            Board.getTerminator().setNickname("Terminator");
            connectedPlayers.add(Board.getTerminator());

        }





        chooseChampion(terminator);




        Player lastPlayer = new Player();

        updateAll("Inizio partita");


        for (Player player : connectedPlayers) {


            if (!terminator) {

                Turn t = new Turn();
                t.first(player);
                Interaction.placeLoot();
                Interaction.placeWeapons();

            }


            else{

                if (!player.getNickname().equalsIgnoreCase("Terminator")){

                    Turn t = new Turn();
                    t.firstTerminator(player);
                    Interaction.placeLoot();
                    Interaction.placeWeapons();

                }


            }
        }


        while (!finish) {

            for (Player player : connectedPlayers) {

                if (!terminator) {
                    Turn t = new Turn();
                    finish = checkFrenzy();
                    if (finish) {
                        continue;
                    }
                    t.standard(player, false);
                    Interaction.placeLoot();
                    Interaction.placeWeapons();
                    lastPlayer = player;
                }

                else{

                    if(!player.getNickname().equalsIgnoreCase("Terminator")){

                        Turn t = new Turn();
                        finish = checkFrenzy();
                        if (finish) {
                            continue;
                        }
                        t.standard(player, true);
                        Interaction.placeLoot();
                        Interaction.placeWeapons();
                        lastPlayer = player;

                    }

                }
            }

        }


        //Devo riordinare l'array connectedPlayers in modo tale da avere l'ordine di esecuzione giusto.

        if(terminator){
            //se sono in modalità terminator lo tolgo prima di riordinare l'array
            Board.setTerminator(connectedPlayers.get(connectedPlayers.size()-1));
            connectedPlayers.remove(connectedPlayers.size()-1);

        }

        CopyOnWriteArrayList<Player> newOrder = new CopyOnWriteArrayList<>();

        boolean startCopy = false;

        for (Player player : connectedPlayers) {

            if (player.equals(lastPlayer)) {
                startCopy = true;
                continue;
            }

            if (startCopy) {

                newOrder.add(player);

            }

        }

        for (Player player : connectedPlayers) {

            if (!newOrder.contains(player)) {
                newOrder.add(player);
            }

        }

        connectedPlayers = newOrder;


        //se sono in modalità terminator lo riaggiungo alla fine
        if(terminator){

            connectedPlayers.add(Board.getTerminator());

        }

        //Controllo quali giocatori devono cambiare playerboard

        for (Player player : connectedPlayers) {
            if (player.getPlayerboard().getDamage().size() == 0) {
                Interaction.turnPlayerboard(player);
            }
        }

        boolean afterFirstPlayer = false;

        for (Player player : connectedPlayers) {

            //Durante l'ultimo giro, se sono dopo il primo giocatore devo fare azioni diverse.
            if (player.isFirstPlayer()) {
                afterFirstPlayer = true;
            }

            Turn t = new Turn();

            if(!terminator) {
                t.frenzy(player, afterFirstPlayer, false);
            }

            else{

                t.frenzy(player, afterFirstPlayer, true);

            }

        }

        Check.winner();

        //TODO inserire chiusura di tutto qua dentro

    }

    /**
     * Setter for the CopyOnWriteArrayList of players that take part at the Match.
     *
     * @param players CopyOnWriteArrayList of players.
     */
    public void setPlayers(CopyOnWriteArrayList<Player> players) {
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

        CopyOnWriteArrayList<Integer> voti = new CopyOnWriteArrayList<>();

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

            while (!InputCheck.checkMapInput(chosenMap)) {

                update(player, Message.inputError());
                chosenMap = updateWithAnswer(player, Message.scegliMappa());

            }

            value = Integer.parseInt(chosenMap);

            /*
            aumento il contatore alla posizione del voto ricevuto
             */

            voti.set((value - 1), voti.get(value - 1) + 1);


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

        CopyOnWriteArrayList<Integer> preferenze = new CopyOnWriteArrayList<>();

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

        CopyOnWriteArrayList<MortalBlow> mortalBlowTrack = new CopyOnWriteArrayList<>();


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


    public static void chooseChampion(boolean isTerminator) {

        /*
        QUA VIENE INIZIALIZZATA LA PLAYERBOARD
         */

        CopyOnWriteArrayList<String> champions = new CopyOnWriteArrayList<>();

        champions.add("Banshee");
        champions.add(":D-strutt-or3");
        champions.add("Dozer");
        champions.add("Violetta");
        champions.add("Sprog");

        for (Player player : connectedPlayers) {
            if(player.getNickname().equalsIgnoreCase("Terminator")){
                continue;
            }

            String toSend = Message.choseChampion(champions);

            String scelta = updateWithAnswer(player, toSend);

            while (!InputCheck.chooseChampion(scelta, champions)) {

                update(player, Message.inputError());
                scelta = updateWithAnswer(player, toSend);

            }

            for (String champion : champions) {

                if (scelta.equalsIgnoreCase(champion)) {

                    player.getPlayerboard().setChampionName(champion);

                    update(player, "Hai scelto " + champion);

                    champions.remove(champion);

                }

            }


        }


        if (isTerminator){

            Player terminator = connectedPlayers.get(connectedPlayers.size()-1);
            Player firstPlayer = connectedPlayers.get(0);

            String campioneTerminator = updateWithAnswer(firstPlayer, "Scegli un campione da assegnare al Terminator\n" + Message.choseChampion(champions));
            boolean scelto = false;

            for (String champion:champions){
                if (champion.equalsIgnoreCase(campioneTerminator)){
                    terminator.getPlayerboard().setChampionName(champion);
                    scelto = true;
                    champions.remove(champion);

                }
            }


            while (!scelto){

                update(firstPlayer, Message.inputError());
                campioneTerminator = updateWithAnswer(firstPlayer, "Scegli un campione da assegnare al Terminator\n" + Message.choseChampion(champions));


                for (String champion:champions){
                    if (champion.equalsIgnoreCase(campioneTerminator)){
                        terminator.getPlayerboard().setChampionName(champion);
                        champions.remove(champion);
                        scelto = true;


                    }
                }

            }

        }


    }


    public static void chooseFirstPlayer() {

        Collections.shuffle(connectedPlayers);
        connectedPlayers.get(0).setFirstPlayer(true);
        for (Player player:connectedPlayers){

            update(player, "Il primo giocatore è " + connectedPlayers.get(0).getNickname());

        }

    }

    public static boolean chooseMode(){

        if (connectedPlayers.size() == 5){

            return false;

        }

        CopyOnWriteArrayList<Integer> preferenze = new CopyOnWriteArrayList<>();
        preferenze.add(0);
        preferenze.add(0);

        for (Player player:connectedPlayers){

            String choice = updateWithAnswer(player, "Vuoi usare la modalità classica o il terminator? (Rispondi classica/terminator)");

            while (!InputCheck.chooseTerminator(choice)){

                update(player, Message.inputError());
                choice = updateWithAnswer(player, "Vuoi usare la modalità classica o il terminator? (Rispondi classica/terminator)");


            }

            if (choice.equalsIgnoreCase("classica")){

                preferenze.set(0, preferenze.get(0) + 1);

            }

            if (choice.equalsIgnoreCase("terminator")){

                preferenze.set(1, preferenze.get(1) + 1);

            }





        }

        if (preferenze.get(1) >= preferenze.get(0)){

            return true;

        }

        return false;

    }


}
