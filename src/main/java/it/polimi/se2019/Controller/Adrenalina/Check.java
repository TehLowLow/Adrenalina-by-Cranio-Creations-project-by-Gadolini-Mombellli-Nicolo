package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Model.Connection.DOOR;
import static it.polimi.se2019.Model.Connection.FREE;
import static it.polimi.se2019.Network.Server.connectedPlayers;
import static it.polimi.se2019.Network.Server.updateAll;


/**
 * This class contains all the methods used to check if an interaction during the game is legit.
 */
public class Check {


    /**
     * Checks if a player has sustained enough damage to be declared dead.
     *
     * @param damaged is the player that receives damage.
     * @return an int value. 0 if the player hasn't been damaged enough to die, 1 if the player receives damage only to
     * die, 2 if the attacker overkills.
     */
    public static int death(Player damaged) {

        /*
        saving the number of the damages on the playerboard
         */

        int damage = damaged.getPlayerboard().getDamage().size();

        /*
        checking the damage
         */

        if (damage == 11) {
            return 1;
        }

        if (damage > 11) {
            return 2;
        } else {
            return 0;
        }
    }


    /**
     * When a player kills another player, this method resolves the board and assigns the players
     * their scores.
     *
     * @param killed the defeated player whose board needs to be resolved.
     */
    public static void resolveBoard(Player killed, boolean overkill) {

        CopyOnWriteArrayList<Token> damages = killed.getPlayerboard().getDamage();
        CopyOnWriteArrayList<PlayerWithScore> playersWithScore;
        CopyOnWriteArrayList<Player> whoShotFirst;

        /*
        First blood
         */

        for (Player player1 : connectedPlayers) {
            if (player1.getPlayerboard().getChampionName().equals(damages.get(0).getChampionName())) {
                /*
                aggiungo un punto a chi ha fatto il primo sangue
                 */
                player1.setScore(player1.getScore() + 1);
            }
        }

        /*
        assigning the points to the killers
         */


        playersWithScore = initLeaderboard(damages);
        whoShotFirst = whoShotFirst(damages);

        Comparator<PlayerWithScore> comparator = new Comparator<PlayerWithScore>() {
            @Override
            public int compare(PlayerWithScore o1, PlayerWithScore o2) {

                if (o1.score > o2.score) {

                    return 1;

                }

                if (o1.score == o2.score) {

                    if (whoShotFirst.indexOf(o1.player) < whoShotFirst.indexOf(o2.player)) {
                        return 1;
                    }

                    if (whoShotFirst.indexOf(o1.player) > whoShotFirst.indexOf(o2.player)) {
                        return -1;
                    }


                }

                if (o1.score < o2.score) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(playersWithScore, comparator);

        Collections.reverse(playersWithScore);

        int playerboardCounter = 0;

        for (PlayerWithScore attacker : playersWithScore) {

            if (attacker.score != 0) {

                attacker.player.setScore(attacker.player.getScore() + killed.getPlayerboard().getPlayerboardValue().get(playerboardCounter));

                playerboardCounter++;
            }

        }

        /*
        placing the kill and overkill tokens on the MortalBlow track
         */

        Interaction interaction = new Interaction();

        String killerChampionName = killed.getPlayerboard().getDamage().get(10).getChampionName();

        Player killer = new Player();

        for (Player player : connectedPlayers) {

            if (player.getPlayerboard().getChampionName().equalsIgnoreCase(killerChampionName)) {

                killer = player;

            }

        }

        interaction.claimSkull(killer, killed, overkill);



        /*
        changing the value of the playerboard
         */

        killed.getPlayerboard().getPlayerboardValue().remove(0);


        /*
        clearing the damages
         */

        for (Token damage : damages) {
            damages.remove(damage);
        }


    }

    /**
     * Checks if the player that is being attacked has markers to add to the total damage.
     *
     * @param active  is the attacker, used to look for attacker's markers.
     * @param passive is the player being attacked that needs te markers check to resolve total damage.
     */
    public static void markers(Player active, Player passive) {


        for (Token marker : passive.getPlayerboard().getMarker()) {


            if (marker.getChampionName().equals(active.getPlayerboard().getChampionName())) {

                /*
                aggiunge il marker ai danni
                 */

                passive.getPlayerboard().getDamage().add(marker);

                /*
                lo rimuove dai marker
                 */

                passive.getPlayerboard().getMarker().remove(marker);

            }


        }

    }

    //TODO testare affordable

    /**
     * Generic check: if the player can afford the cost,returns true.
     *
     * @param player the buyer
     * @param cost   the cost
     * @return true if the player can afford the cost
     */
    public static boolean affordable(Player player, Rybamount cost) {

        int blueAvailable = player.getPlayerboard().getAmmoCubes().getBlue();
        int yellowAvailable = player.getPlayerboard().getAmmoCubes().getYellow();
        int redAvailable = player.getPlayerboard().getAmmoCubes().getRed();

        CopyOnWriteArrayList<Powerup> playerPowerups = player.getPlayerboard().getPowerups();

        for (Powerup powerup : playerPowerups) {

            blueAvailable = blueAvailable + powerup.getTradeValue().getBlue();
            yellowAvailable = yellowAvailable + powerup.getTradeValue().getYellow();
            redAvailable = redAvailable + powerup.getTradeValue().getRed();

        }

        int yellowCost = cost.getYellow();
        int blueCost = cost.getBlue();
        int redCost = cost.getRed();

        if (yellowCost > yellowAvailable || redCost > redAvailable || blueCost > blueAvailable) {
            return false;
        }

        return true;

    }


    /**
     * Runs to limit the total amount of ammo per color to 3 ammos. For example, if a player has more than 3 red
     * ammo cubes this method will return the amount of cubes to 3.
     *
     * @param player is the player to be checked.
     */
    public static void limitAmmoCubes(Player player) {

        if (player.getPlayerboard().getAmmoCubes().getYellow() > 3) {
            player.getPlayerboard().getAmmoCubes().setYellowCubes(3);
        }

        if (player.getPlayerboard().getAmmoCubes().getRed() > 3) {
            player.getPlayerboard().getAmmoCubes().setRedCubes(3);
        }

        if (player.getPlayerboard().getAmmoCubes().getBlue() > 3) {
            player.getPlayerboard().getAmmoCubes().setBlueCubes(3);
        }

    }

    /**
     * Checks if a player has reached the limit of powerUps he can keep
     *
     * @param player is the player to be checked.
     */
    public static boolean limitPowerUp(Player player) {

        if (player.getPlayerboard().getPowerups().size() >= 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A player can place up to 3 markers on each enemy playerboard, and the player can have up to 3 marker from each
     * other player. This method runs to check if the number of markers has reached limit and fix it.
     *
     * @param defender is who has the markers to check
     * @param attacker is who gives the markers
     */
    public static void limitMarkers(Player defender, Player attacker) {

        CopyOnWriteArrayList<Token> markers = defender.getPlayerboard().getMarker();

        int attackerMarkers = 0;


        for (Token marker : markers) {

            if (marker.getChampionName().equals(attacker.getPlayerboard().getChampionName())) {
                attackerMarkers++;

                if (attackerMarkers > 3) {

                    attackerMarkers--;
                    markers.remove(marker);

                }
            }
        }

        defender.getPlayerboard().setMarker(markers);


    }

    /**
     * Returns an CopyOnWriteArrayList containing all the players in the field of view of a player.
     *
     * @param player is the player that needs the fov check.
     * @return all the others players that are in the fov.
     */
    public static CopyOnWriteArrayList<Player> visiblePlayers(Player player) {

        CopyOnWriteArrayList<Player> visiblePlayers = new CopyOnWriteArrayList<Player>();

        for (Player player1 : connectedPlayers) {


            /*
            tra i visibili non ci deve essere lui stesso
             */
            if (!player1.equals(player)) {

                if (player1.getPosition() != null || !player1.getPosition().getName().equalsIgnoreCase("limbo")) {

                    if (player.getPosition().getColour() == player1.getPosition().getColour()) {
                        if (!visiblePlayers.contains(player1)) {
                            visiblePlayers.add(player1);
                        }
                    }

                    if (player.getPosition().getUpConnection().getType().equals(DOOR)) {
                        if (player1.getPosition().getColour() == player.getPosition().getUpConnection().getConnectedCell().getColour()) {
                            if (!visiblePlayers.contains(player1)) {
                                visiblePlayers.add(player1);
                            }
                        }
                    }

                    if (player.getPosition().getRightConnection().getType().equals(DOOR)) {
                        if (player1.getPosition().getColour() == player.getPosition().getRightConnection().getConnectedCell().getColour()) {
                            if (!visiblePlayers.contains(player1)) {
                                visiblePlayers.add(player1);
                            }
                        }
                    }

                    if (player.getPosition().getLeftConnection().getType().equals(DOOR)) {
                        if (player1.getPosition().getColour() == player.getPosition().getLeftConnection().getConnectedCell().getColour()) {
                            if (!visiblePlayers.contains(player1)) {
                                visiblePlayers.add(player1);
                            }
                        }
                    }

                    if (player.getPosition().getDownConnection().getType().equals(DOOR)) {
                        if (player1.getPosition().getColour() == player.getPosition().getDownConnection().getConnectedCell().getColour()) {
                            if (!visiblePlayers.contains(player1)) {
                                visiblePlayers.add(player1);
                            }
                        }
                    }

                }

            }


        }


        return visiblePlayers;

    }

    /**
     * Returns all the cells that are a maximum of n steps away from player.
     *
     * @param player is the player that wants the check to move.
     * @param steps  is the number of steps to do.
     * @return an CopyOnWriteArrayList of reachable cells.
     */
    public static CopyOnWriteArrayList<Cell> reachableCells(Player player, int steps) {

        CopyOnWriteArrayList<Cell> reachableCells = new CopyOnWriteArrayList<>();

        Cell position = player.getPosition();

        if (steps == 1) {

            /*
            controllo solo le celle raggiungibili dalla posizione del player
             */


            reachableCells = pathFinder(position);

        }


        if (steps > 1) {

            /*
            prima controllo la mia posizione
             */

            reachableCells = pathFinder(position);

            /*
            poi controllo le celle raggiungibili da quelle arrivabili dopo il primo step
             */


            for (int i = 1; i < steps; i++) {

                CopyOnWriteArrayList<Cell> copiesToCheck = new CopyOnWriteArrayList<>();

                for (Cell cell : reachableCells) {
                    copiesToCheck.add(cell);
                }

                for (Cell cell : copiesToCheck) {

                    if (cell.getUpConnection().getType().equals(DOOR) || cell.getUpConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getUpConnection().getConnectedCell())) {

                            reachableCells.add(cell.getUpConnection().getConnectedCell());

                        }

                    }

                    if (cell.getRightConnection().getType().equals(DOOR) || cell.getRightConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getRightConnection().getConnectedCell())) {

                            reachableCells.add(cell.getRightConnection().getConnectedCell());

                        }

                    }

                    if (cell.getLeftConnection().getType().equals(DOOR) || cell.getLeftConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getLeftConnection().getConnectedCell())) {

                            reachableCells.add(cell.getLeftConnection().getConnectedCell());

                        }

                    }

                    if (cell.getDownConnection().getType().equals(DOOR) || cell.getDownConnection().getType().equals(FREE)) {

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if (!reachableCells.contains(cell.getDownConnection().getConnectedCell())) {

                            reachableCells.add(cell.getDownConnection().getConnectedCell());

                        }

                    }


                }

            }

        }

        if (reachableCells.contains(position)) {

            reachableCells.remove(position);

        }


        return reachableCells;

    }

    /**
     * This method checks if there are the conditions to perform the final frenzy.
     *
     * @return true if final frenzy can start, else otherwise.
     */

    public static boolean checkFrenzy() {

        CopyOnWriteArrayList<MortalBlow> mortalBlows = Board.getMortalBlowTrack();
        int mbCounter = 0;

        for (MortalBlow mortalBlow : mortalBlows) {

            if (mortalBlow.isSkull()) {

                mbCounter++;

            }


        }

        if (mbCounter == 0) {
            return true;
        }


        return false;


    }

    /**
     * This method resolves a board in the final franzy mode.
     *
     * @param killed   is the player whom board has to be resolved.
     * @param overkill is true if the player has been overkilled, else otherwise.
     */

    public static void resolveFrenzyboard(Player killed, boolean overkill) {


        CopyOnWriteArrayList<Token> damages = new CopyOnWriteArrayList<Token>();
        damages = killed.getPlayerboard().getDamage();
        CopyOnWriteArrayList<it.polimi.se2019.Controller.Adrenalina.PlayerWithScore> playersWithScore;
        CopyOnWriteArrayList<Player> whoShotFirst;


        whoShotFirst = whoShotFirst(damages);
        playersWithScore = initLeaderboard(damages);


        Comparator<PlayerWithScore> comparator = new Comparator<PlayerWithScore>() {
            @Override
            public int compare(PlayerWithScore o1, PlayerWithScore o2) {

                if (o1.score > o2.score) {

                    return 1;

                }

                if (o1.score == o2.score) {

                    if (whoShotFirst.indexOf(o1.player) < whoShotFirst.indexOf(o2.player)) {
                        return 1;
                    }

                    if (whoShotFirst.indexOf(o1.player) > whoShotFirst.indexOf(o2.player)) {
                        return -1;
                    }


                }

                if (o1.score < o2.score) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(playersWithScore, comparator);

        Collections.reverse(playersWithScore);

        int playerboardCounter = 0;


        Player killer = new Player();

        String killerChampionName = killed.getPlayerboard().getDamage().get(10).getChampionName();


        for (Player player : connectedPlayers) {

            if (player.getPlayerboard().getChampionName().equalsIgnoreCase(killerChampionName)) {

                killer = player;

            }

        }

        for (PlayerWithScore attacker : playersWithScore) {

            if (attacker.score != 0) {

                attacker.player.setScore(killer.getScore() + killed.getPlayerboard().getPlayerboardValue().get(playerboardCounter));

                playerboardCounter++;
            }

        }


        /*
        aggiungo i token kill e overkill al tracciato colpo mortale
         */

        MortalBlow kill = new MortalBlow();
        kill.setKiller(killer);
        kill.setSkull(false);
        kill.setOverkill(overkill);

        Board.getMortalBlowTrack().add(kill);



         /*
        clearing the damages
         */

        for (Token damage : damages) {
            damages.remove(damage);
        }

    }

    /**
     * This method runs after the final frenzy, and checks (/resolve) all the last points remaining on the board.
     * Then it proceeds to sum up the points of each player and declare a winner
     * (or a chart with the points of all the players).
     *
     * @return the winner.
     */
    public static void winner() {

        /*
        risolvo le finalfrenzy playerboard
         */

        for (Player player : connectedPlayers) {

            CopyOnWriteArrayList<Token> damages = player.getPlayerboard().getDamage();


            /*
            primo sangue se la board non è frenzy
             */

            if (!player.getPlayerboard().isFrenzyboard()) {

                for (Player attacker : connectedPlayers) {
                    if (!damages.isEmpty() && attacker.getPlayerboard().getChampionName().equals(damages.get(0).getChampionName())) {
                /*
                aggiungo un punto a chi ha fatto il primo sangue
                 */
                        attacker.setScore(attacker.getScore() + 1);
                    }
                }

            }

        /*
        assigning the points to the killers
         */

            CopyOnWriteArrayList<PlayerWithScore> playersWithScore;
            CopyOnWriteArrayList<Player> whoShotFirst;

            playersWithScore = initLeaderboard(damages);
            whoShotFirst = whoShotFirst(damages);


            Comparator<PlayerWithScore> comparator = new Comparator<PlayerWithScore>() {
                @Override
                public int compare(PlayerWithScore o1, PlayerWithScore o2) {

                    if (o1.score > o2.score) {

                        return 1;

                    }

                    if (o1.score == o2.score) {

                        if (whoShotFirst.indexOf(o1.player) < whoShotFirst.indexOf(o2.player)) {
                            return 1;
                        }

                        if (whoShotFirst.indexOf(o1.player) > whoShotFirst.indexOf(o2.player)) {
                            return -1;
                        }


                    }

                    if (o1.score < o2.score) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            };

            Collections.sort(playersWithScore, comparator);

            Collections.reverse(playersWithScore);

            int playerboardCounter = 0;

            for (PlayerWithScore attacker : playersWithScore) {

                if (attacker.score != 0) {

                    attacker.player.setScore(attacker.player.getScore() + player.getPlayerboard().getPlayerboardValue().get(playerboardCounter));

                    playerboardCounter++;
                }

            }

        }



        /*
        risoluzione tracciato colpo mortale
         */

        class PlayerWithKills {


            Player player;
            int kills;


        }

        CopyOnWriteArrayList<PlayerWithKills> playersWithKills = new CopyOnWriteArrayList<>();

        for (Player killer : connectedPlayers) {

            PlayerWithKills playerKills = new PlayerWithKills();

            playerKills.player = killer;

            playerKills.kills = 0;

            for (MortalBlow mortalBlow : Board.getMortalBlowTrack()) {

                if (mortalBlow.getKiller() != null && mortalBlow.getKiller().equals(killer)) {

                    playerKills.kills++;

                    if (mortalBlow.isOverkill()) {
                        playerKills.kills++;
                    }

                }

            }

            playersWithKills.add(playerKills);

        }


        /*
        creo un array ordinato per ordine temporale di uccisioni
         */

        CopyOnWriteArrayList<Player> whoKilledFirst = new CopyOnWriteArrayList<>();

        for (MortalBlow mortalBlow : Board.getMortalBlowTrack()) {

            for (Player killer : connectedPlayers) {

                if (mortalBlow.getKiller() != null && mortalBlow.getKiller().equals(killer)) {

                    if (!whoKilledFirst.contains(killer)) {

                        whoKilledFirst.add(killer);

                    }

                }
            }
        }

        /*
        creo il comparatore per le parità
         */

        Comparator<PlayerWithKills> comparator1 = new Comparator<PlayerWithKills>() {
            @Override
            public int compare(PlayerWithKills o1, PlayerWithKills o2) {

                if (o1.kills > o2.kills) {

                    return 1;

                }

                if (o1.kills == o2.kills) {

                    if (whoKilledFirst.indexOf(o1.player) < whoKilledFirst.indexOf(o2.player)) {
                        return 1;
                    }

                    if (whoKilledFirst.indexOf(o1.player) > whoKilledFirst.indexOf(o2.player)) {
                        return -1;
                    }


                }

                if (o1.kills < o2.kills) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(playersWithKills, comparator1);

        Collections.reverse(playersWithKills);

        int mortalBlowCounter = 0;

        for (PlayerWithKills killer : playersWithKills) {

            if (killer.kills != 0) {

                killer.player.setScore(killer.player.getScore() + Board.getMortalBlowTrackValue().get(mortalBlowCounter));

                mortalBlowCounter++;
            }

        }



        /*
        controllo dei punti, risoluzione parità e calcolo del vincitore
         */

        class PotentialWinner {

            Player player;

            int points;


            int scoreOnMortalBlowTrack;

            boolean tie;

        }

        /*
        ora viene creato un array dei possibili vincitori che contiene in ogni posto un giocatore,
        i suoi punti totali e i suoi punti ottenuti nel tracciato colpo mortale(questi ultimi servono a gestire
        un'eventuale situazione di parità)
         */

        CopyOnWriteArrayList<PotentialWinner> potentialWinners = new CopyOnWriteArrayList<>();

        for (Player player : connectedPlayers) {

            PotentialWinner potentialWinner = new PotentialWinner();

            potentialWinner.points = player.getScore();

            potentialWinner.player = player;

            /*
            inizialmente setto a false il flag parità di ogni potenziale vincitore
             */

            potentialWinner.tie = false;


            /*
            all'inizio deve valere zero perchè se non lo modifico dentro il for poi si fotte
             */
            potentialWinner.scoreOnMortalBlowTrack = 0;

            mortalBlowCounter = 0;

            /*
            qui viene assegnato ad ogni player il suo punteggio nel tracciato colpo mortale
             */

            for (PlayerWithKills killer : playersWithKills) {

                if (killer.kills != 0) {

                    if (killer.player.equals(player)) {

                        potentialWinner.scoreOnMortalBlowTrack = Board.getMortalBlowTrackValue().get(mortalBlowCounter);

                    }

                    mortalBlowCounter++;
                }

            }

            potentialWinners.add(potentialWinner);

        }



        /*
        comparatore per poter ordinare Potential Winners prima in base ai punti totali e poi in base a
        quelli ottenuti nel tracciato colpo mortale
         */


        Comparator<PotentialWinner> potentialWinnerComparator = new Comparator<PotentialWinner>() {
            @Override
            public int compare(PotentialWinner o1, PotentialWinner o2) {

                if (o1.points > o2.points) {
                    return 1;
                }

                if (o1.points == o2.points) {

                    if (o1.scoreOnMortalBlowTrack > o2.scoreOnMortalBlowTrack) {

                        return 1;

                    }

                    if (o1.scoreOnMortalBlowTrack == o2.scoreOnMortalBlowTrack) {

                        /*
                        mi segno che i 2 player sono in parità tra di loro
                         */
                        o1.tie = true;
                        o2.tie = true;

                        return 0;

                    } else {
                        return -1;
                    }


                }

                if (o1.points < o2.points) {
                    return -1;
                }

                return 0;
            }
        };

        /*
        ora l'ordinamento in base al comparator appena creato
         */

        Collections.sort(potentialWinners, potentialWinnerComparator);

        Collections.reverse(potentialWinners);


        /*
        adesso viene stabilito il vincitore
         */

        Player winner = new Player();


        /*
        CREAZIONE STRINGA CON VINCITORE E CLASSIFICA
         */

        String schermataFinale = "";


        /*
        caso di un solo vincitore
         */

        if (!potentialWinners.get(0).tie) {

            winner = potentialWinners.get(0).player;

            /*
            notifico a tutti il nome del vincitore
             */


            if (connectedPlayers.get(connectedPlayers.size() - 1).getNickname().equalsIgnoreCase("terminator")) {

                connectedPlayers.remove(connectedPlayers.size() - 1);

            }



            /*
            aggiungo il vincitore alla schermata finale
             */


            schermataFinale = schermataFinale + "Il vincitore è " + winner.getNickname() + "!\n";


        }

        /*
        caso di parità
         */


        if (potentialWinners.get(0).tie) {
            /*
            notifico ai giocatori chi sono i vincitori
             */

            PotentialWinner firstWinner = new PotentialWinner();

            firstWinner = potentialWinners.get(0);


            schermataFinale = schermataFinale + "I vincitori sono:\n";

                /*
                stampo gli altri
                 */

            for (PotentialWinner potentialWinner : potentialWinners) {
                if (potentialWinner.points == firstWinner.points && potentialWinner.scoreOnMortalBlowTrack == firstWinner.scoreOnMortalBlowTrack) {

                    schermataFinale = schermataFinale + potentialWinner.player.getNickname() + "\n";


                }

            }


        }

        schermataFinale = schermataFinale + "\n";


        /*
        notifico a tutti i giocatori la classifica finale
         */


        schermataFinale = schermataFinale + "Classifica Finale:\n";

        for (PotentialWinner potentialWinner : potentialWinners) {

                /*
                faccio vedere per ogni giocatore il nome, il punteggio totale e quello sul tracciato colpo mortale
                 */

            schermataFinale = schermataFinale + "Nome: " + potentialWinner.player.getNickname() + " Punteggio Totale: " + potentialWinner.points + " Punti tracciato colpo mortale " + potentialWinner.scoreOnMortalBlowTrack + "\n";

        }

            /*
            infine stampo la stringa con le informazioni finali
             */


        updateAll(schermataFinale);


    }


    /**
     * Verifies if a cell is a spawn or not
     *
     * @param cell is the cell to check
     * @return true if it's a spawn, false otherwise
     */


    public static boolean isSpawn(Cell cell) {

        if (cell.getName().equals("spawnCell")) {
            return true;
        }

        return false;

    }

    /**
     * this method is used to check which cells are visible by a player
     *
     * @param user is who has to check the visible cells.
     * @return an array containins the visible cells.
     */

    public static CopyOnWriteArrayList<Cell> visibleSquares(Player user) {

        CopyOnWriteArrayList<Cell> visibleSquares = new CopyOnWriteArrayList<>();

        Cell position = user.getPosition();

        int room = position.getColour();


        addVisibleCells(visibleSquares, room);

        if (position.getUpConnection().getType().equalsIgnoreCase(DOOR)) {

            room = position.getUpConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);

        }

        if (position.getDownConnection().getType().equalsIgnoreCase(DOOR)) {

            room = position.getDownConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);

        }

        if (position.getLeftConnection().getType().equalsIgnoreCase(DOOR)) {
            room = position.getLeftConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);
        }

        if (position.getRightConnection().getType().equalsIgnoreCase(DOOR)) {
            room = position.getRightConnection().getConnectedCell().getColour();
            addVisibleCells(visibleSquares, room);
        }

        return visibleSquares;

    }

    private static void addVisibleCells(CopyOnWriteArrayList<Cell> visibleSquares, int room) {

        if (room == Colour.RED) {
            visibleSquares.addAll(Board.getMap().getRedRoom().getCells());
        }

        if (room == Colour.YELLOW) {
            visibleSquares.addAll(Board.getMap().getYellowRoom().getCells());
        }

        if (room == Colour.BLUE) {
            visibleSquares.addAll(Board.getMap().getBlueRoom().getCells());
        }

        if (room == Colour.WHITE) {
            visibleSquares.addAll(Board.getMap().getWhiteRoom().getCells());
        }

        if (room == Colour.GREEN) {
            visibleSquares.addAll(Board.getMap().getGreenRoom().getCells());
        }

        if (room == Colour.PURPLE) {
            visibleSquares.addAll(Board.getMap().getPurpleRoom().getCells());
        }

    }


    /**
     * Checks if the user can perform the shot action
     *
     * @param player is the user
     * @return true if he can shot, false otherwise
     */

    public static boolean canShot(Player player) {

        CopyOnWriteArrayList<Weapon> playerWeapons = player.getPlayerboard().getWeapons();

        for (Weapon weapon : playerWeapons) {

            if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                if (weapon.isLoaded()) {
                    return true;
                }

            }
        }


        return false;

    }

    /**
     * This method check if the enhanced shot can be performed
     *
     * @param player is who has to shoot
     * @return true if he can shoot enhanced, false otherwise
     */


    public static boolean canShotEnhanced(Player player) {

        Cell position = player.getPosition();
        CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 1);
        reachableCells.add(player.getPosition());

        for (Cell cell : reachableCells) {

            player.setPosition(cell);

            for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                    if (weapon.isLoaded()) {
                        player.setPosition(position);
                        return true;
                    }

                }
            }

        }

        return false;

    }


    public static boolean canShotFrenzy(Player player) {

        Cell position = player.getPosition();
        CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 1);
        reachableCells.add(player.getPosition());

        for (Cell cell : reachableCells) {

            player.setPosition(cell);

            for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                    if (Check.affordable(player, weapon.getRechargeCost()) || weapon.isLoaded()) {
                        player.setPosition(position);
                        return true;
                    }

                }
            }

        }

        return false;

    }


    /**
     * This method checks if the user can perform the enhanced frenzy shot
     *
     * @param player is the user
     * @return true if he can perform it, else otherwise
     */


    public static boolean canShotEnhancedFrenzy(Player player) {

        Cell position = player.getPosition();
        CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 2);
        reachableCells.add(player.getPosition());

        for (Cell cell : reachableCells) {

            player.setPosition(cell);

            for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                    if (weapon.isLoaded() || Check.affordable(player, weapon.getRechargeCost())) {

                        player.setPosition(position);
                        return true;
                    }

                }
            }

        }

        return false;

    }

    public static CopyOnWriteArrayList<Cell> moveManager(Player player, int steps) {

        CopyOnWriteArrayList<Cell> maxNPassi = reachableCells(player, steps);
        CopyOnWriteArrayList<Cell> maxNMenoUnoPassi = reachableCells(player, steps - 1);
        CopyOnWriteArrayList<Cell> nPassi = new CopyOnWriteArrayList<>();

        for (Cell cell : maxNPassi) {

            if (!maxNMenoUnoPassi.contains(cell)) {

                nPassi.add(cell);

            }

        }

        return nPassi;


    }


    private static CopyOnWriteArrayList<Cell> pathFinder(Cell start) {

        CopyOnWriteArrayList reached = new CopyOnWriteArrayList();

        if (start.getUpConnection().getType().equals(DOOR) || start.getUpConnection().getType().equals(FREE)) {

            reached.add(start.getUpConnection().getConnectedCell());

        }

        if (start.getRightConnection().getType().equals(DOOR) || start.getRightConnection().getType().equals(FREE)) {

            reached.add(start.getRightConnection().getConnectedCell());

        }

        if (start.getLeftConnection().getType().equals(DOOR) || start.getLeftConnection().getType().equals(FREE)) {

            reached.add(start.getLeftConnection().getConnectedCell());

        }

        if (start.getDownConnection().getType().equals(DOOR) || start.getDownConnection().getType().equals(FREE)) {

            reached.add(start.getDownConnection().getConnectedCell());

        }

        return reached;
    }


    private static CopyOnWriteArrayList<PlayerWithScore> initLeaderboard(CopyOnWriteArrayList<Token> damages) {


        CopyOnWriteArrayList<PlayerWithScore> playersWithScore = new CopyOnWriteArrayList<>();

        for (Player player : connectedPlayers) {

            PlayerWithScore playerScore = new PlayerWithScore();

            playerScore.player = player;

            playerScore.score = 0;

            for (Token damage : damages) {

                if (damage.getChampionName().equals(player.getPlayerboard().getChampionName())) {

                    playerScore.score++;

                }

            }

            playersWithScore.add(playerScore);

        }

        return playersWithScore;

    }

    private static CopyOnWriteArrayList<Player> whoShotFirst(CopyOnWriteArrayList<Token> damages) {

        CopyOnWriteArrayList<Player> whoShotFirst = new CopyOnWriteArrayList<>();

        for (Token damage : damages) {

            for (Player player : connectedPlayers) {

                if (damage.getChampionName().equals(player.getPlayerboard().getChampionName())) {

                    if (!whoShotFirst.contains(player)) {

                        whoShotFirst.add(player);

                    }

                }
            }
        }

        return whoShotFirst;

    }
}


class PlayerWithScore {


    Player player;
    int score;


}



