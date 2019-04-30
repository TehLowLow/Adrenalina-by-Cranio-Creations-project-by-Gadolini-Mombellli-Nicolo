package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.EffectExceptions.*;
import it.polimi.se2019.Model.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.*;


/**
 * Contains the @Override of the methods of Effect.java, and builds the Flamethrower.
 */
public class BasicFlamethrower extends Effect {

    /**
     * Applies the effect of the Flamethrower to the target (or targets).
     *
     * @param user   the Player that wants to apply the effect.
     * @param targets the targets of the effect. It can be the user itself.
     */

    @Override
    public void applyEffect(Player user, ArrayList <Player> targets) {

        for(Player target : targets){

            ArrayList <Token> damages = target.getPlayerboard().getDamage();
            Token newDamage = new Token();
            newDamage.setChampionName(user.getPlayerboard().getChampionName());
            damages.add(newDamage);
            target.getPlayerboard().setDamage(damages);

        }


    }

    /**
     * Looks for the target (or targets) of the Flamethrower.
     *
     * @param user the Player thant wants to use the effect.
     * @return an arraylist of targets. The effect must be applied to them.
     */

    @Override
    public ArrayList<Player> getTargets(Player user) {

        //Lista di bersagli: alla fine conterrà i giocatori da colpire con applyEffect.
        ArrayList<Player> targets = new ArrayList<>();

        //Prima di tutto, chiedo al player la direzione in cui sparare.
        String direction = Server.updateWithAnswer(user, Message.scegliDirezione());

        //Controllo che sia valida. Se ha digitato male, la chiedo nuovamente.
        while (!InputCheck.directionCheck(direction)) {

            Server.update(user, Message.inputError());
            direction = Server.updateWithAnswer(user, Message.scegliDirezione());

        }

        //Prendo la posizione dell'utente.
        Cell position = user.getPosition();

        //Se il giocatore sceglie di sparare in alto, eseguo questo if.
        if (direction.equalsIgnoreCase("alto")) {


            //Se in alto rispetto alla posizione non ho un muro o il bordo della mappa, proseguo.
            if (!position.getUpConnection().getType().equals(Connection.WALL) && !position.getUpConnection().getType().equals(Connection.EDGE)) {

                //Provo a vedere se trovo un bersaglio nella casella in alto. Se non lo trovo, lancio eccezione e chiamo nuovamente getTargets.
                try {

                    targets.add(searchDirection(position.getUpConnection().getConnectedCell(), user, targets));

                }
                catch (NoTargetException e) {

                    Server.update(user, Message.nessunBersaglio());
                    return getTargets(user);

                }

                //Se non è stata sollevata l'eccezione, cerco un bersaglio nella casella ancora successiva a quella del precedente bersaglio, se possibile..
                Player firstTarget = targets.get(0);

                if(firstTarget.getPosition().getUpConnection().getType().equals(Connection.WALL) || firstTarget.getPosition().getUpConnection().getType().equals(Connection.EDGE)){

                    //Se entro qui, non c'è un'ulteriore casella. Ritorno l'array di bersagli.
                    return targets;
                }

                else{
                    try{
                        targets.add(searchDirection(firstTarget.getPosition().getUpConnection().getConnectedCell(), user, targets));
                    }
                    catch(NoTargetException e){
                        //Se becco l'eccezione, vuol dire che non ci sono bersagli in quella casella. Ritorno.
                        return targets;
                    }
                }

            }

            //Se non posso sparare verso l'alto, eseguo questo else, ritornando il risultato di una nuova chiamata di getTargets.
            else {

                Server.update(user, Message.direzioneOstruita());
                return getTargets(user);

            }

        }


        if (direction.equalsIgnoreCase("basso")) {

            //Se in basso rispetto alla posizione non ho un muro o il bordo della mappa, proseguo.
            if (!position.getDownConnection().getType().equals(Connection.WALL) && !position.getDownConnection().getType().equals(Connection.EDGE)) {

                //Provo a vedere se trovo un bersaglio nella casella in basso. Se non lo trovo, lancio eccezione e chiamo nuovamente getTargets.
                try {

                    targets.add(searchDirection(position.getDownConnection().getConnectedCell(), user, targets));

                }
                catch (NoTargetException e) {

                    Server.update(user, Message.nessunBersaglio());
                    return getTargets(user);

                }

                //Se non è stata sollevata l'eccezione, cerco un bersaglio nella casella ancora successiva a quella del precedente bersaglio,
                // sempre nella stessa direzione, se possibile..
                Player firstTarget = targets.get(0);

                if(firstTarget.getPosition().getDownConnection().getType().equals(Connection.WALL) || firstTarget.getPosition().getDownConnection().getType().equals(Connection.EDGE)){

                    //Se entro qui, non c'è un'ulteriore casella. Ritorno l'array di bersagli.
                    return targets;
                }

                else{
                    try{
                        targets.add(searchDirection(firstTarget.getPosition().getDownConnection().getConnectedCell(), user, targets));
                    }
                    catch(NoTargetException e){
                        //Se becco l'eccezione, vuol dire che non ci sono bersagli in quella casella. Ritorno.
                        return targets;
                    }
                }

            }

            //Se non posso sparare verso il basso, eseguo questo else, ritornando il risultato di una nuova chiamata di getTargets.
            else {

                Server.update(user, Message.direzioneOstruita());
                return getTargets(user);

            }


        }

        if (direction.equalsIgnoreCase("sinistra")) {

            //Se a sinistra rispetto alla posizione non ho un muro o il bordo della mappa, proseguo.
            if (!position.getLeftConnection().getType().equals(Connection.WALL) && !position.getLeftConnection().getType().equals(Connection.EDGE)) {

                //Provo a vedere se trovo un bersaglio nella casella a sinistra. Se non lo trovo, lancio eccezione e chiamo nuovamente getTargets.
                try {

                    targets.add(searchDirection(position.getLeftConnection().getConnectedCell(), user, targets));

                }
                catch (NoTargetException e) {

                    Server.update(user, Message.nessunBersaglio());
                    return getTargets(user);

                }

                //Se non è stata sollevata l'eccezione, cerco un bersaglio nella casella ancora successiva a quella del precedente bersaglio,
                // sempre nella stessa direzione, se possibile..
                Player firstTarget = targets.get(0);

                if(firstTarget.getPosition().getLeftConnection().getType().equals(Connection.WALL) || firstTarget.getPosition().getLeftConnection().getType().equals(Connection.EDGE)){

                    //Se entro qui, non c'è un'ulteriore casella. Ritorno l'array di bersagli.
                    return targets;
                }

                else{
                    try{
                        targets.add(searchDirection(firstTarget.getPosition().getLeftConnection().getConnectedCell(), user, targets));
                    }
                    catch(NoTargetException e){
                        //Se becco l'eccezione, vuol dire che non ci sono bersagli in quella casella. Ritorno.
                        return targets;
                    }
                }

            }

            //Se non posso sparare verso sinistra, eseguo questo else, ritornando il risultato di una nuova chiamata di getTargets.
            else {

                Server.update(user, Message.direzioneOstruita());
                return getTargets(user);

            }

        }

        if (direction.equalsIgnoreCase("destra")) {

            //Se a destra rispetto alla posizione non ho un muro o il bordo della mappa, proseguo.
            if (!position.getRightConnection().getType().equals(Connection.WALL) && !position.getRightConnection().getType().equals(Connection.EDGE)) {

                //Provo a vedere se trovo un bersaglio nella casella a destra. Se non lo trovo, lancio eccezione e chiamo nuovamente getTargets.
                try {

                    targets.add(searchDirection(position.getRightConnection().getConnectedCell(), user, targets));

                }
                catch (NoTargetException e) {

                    Server.update(user, Message.nessunBersaglio());
                    return getTargets(user);

                }

                //Se non è stata sollevata l'eccezione, cerco un bersaglio nella casella ancora successiva a quella del precedente bersaglio,
                // sempre nella stessa direzione, se possibile..
                Player firstTarget = targets.get(0);

                if(firstTarget.getPosition().getRightConnection().getType().equals(Connection.WALL) || firstTarget.getPosition().getRightConnection().getType().equals(Connection.EDGE)){

                    //Se entro qui, non c'è un'ulteriore casella. Ritorno l'array di bersagli.
                    return targets;
                }

                else{
                    try{
                        targets.add(searchDirection(firstTarget.getPosition().getRightConnection().getConnectedCell(), user, targets));
                    }
                    catch(NoTargetException e){
                        //Se becco l'eccezione, vuol dire che non ci sono bersagli in quella casella. Ritorno.
                        return targets;
                    }
                }

            }

            //Se non posso sparare verso il basso, eseguo questo else, ritornando il risultato di una nuova chiamata di getTargets.
            else {

                Server.update(user, Message.direzioneOstruita());
                return getTargets(user);

            }

        }

        return null;

    }


    /**
     * This method returns one target given a cell. If on the given cell there are more than one player, the user must
     * choose one of them.
     */
    private Player searchDirection(Cell position, Player user, ArrayList<Player> targets) throws NoTargetException {

        //Quando il giocatore sceglierà un bersaglio, salverò il suo nickname qui.
        String nicknameChosenTarget;

        //Con un ciclo, cerco tutti i giocatori che si trovano in quella cella. Li metto per il momento nell'array di targets.
        for (Player player : Server.connectedPlayers) {

            if (player.getNickname().equalsIgnoreCase(user.getNickname())) {
                if (player.getPosition().equals(position)) {

                    targets.add(player);

                }
            }

        }

        //Se ho trovato più di un giocatore nel quadrato in alto, chiedo all'utente di sceglierne uno solo come bersaglio.
        if (targets.size() > 1) {

            //Quando ho scelto con successo un bersaglio, lui diventa true.
            boolean found = false;

            //Ci salvo il bersaglio scelto.
            Player possibleTarget = null;

            //Finché non lo trovo...
            while (!found) {

                //Chiedo all'utente di scegliere un bersaglio.
                nicknameChosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(targets));

                //Cerco se il nickname corrisponde a quello di un giocatore nell'array di bersagli.
                for (Player target : targets) {
                    if (target.getNickname().equalsIgnoreCase(nicknameChosenTarget)) {
                        //Se corrisponde, quel bersaglio diventa il possibile bersaglio.
                        possibleTarget = target;
                    }
                }

                //Se dopo il for precedente possibleTarget è ancora a null, vuol dire che il nickname digitato non
                //corrispondeva a nessun giocatore. Notifico l'utente e ripeto il ciclo.
                if (possibleTarget == null) {

                    Server.update(user, Message.bersaglioNonValido());

                }

                //Se possibleTarget non è null, allora ho trovato un bersaglio valido.
                else {
                    found = true;
                }

            }

            //Ora devo lasciare soltanto il target scelto nell'array di targets, rimuovendo gli altri poiché non vengono colpiti.
            for (Player target : targets) {
                if (target != possibleTarget) {
                    targets.remove(target);
                }
            }


        }

        //Se non ne ho trovati, sollevo un'eccezione.
        else if (targets.size() == 0) {

            throw new NoTargetException("Nessun bersaglio in questa casella.");
        }

        //ritorno il bersaglio.
        return targets.get(0);


    }


    @Override
    public boolean hasTargets(Player user) {

        Check check = new Check();
        ArrayList<Cell> targetCells = check.reachableCells(user, 2);
        CopyOnWriteArrayList<Player> players = Server.connectedPlayers;
        ArrayList<Player> two_step_targets = new ArrayList<>();

        for (Player player : players) {

            if (!player.getNickname().equals(user.getNickname())) {
                if (targetCells.contains(player.getPosition())) {
                    two_step_targets.add(player);
                }
            }

        }

        if (two_step_targets.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

}


