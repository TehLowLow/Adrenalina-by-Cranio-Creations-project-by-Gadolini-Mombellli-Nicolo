package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;
import java.util.ArrayList;


import static it.polimi.se2019.Model.Connection.DOOR;

public class VortexCannon extends Effect {

   //Creo un vortice. Lo tratto come un player perché è comodo da spostare.

    Player vortex;

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        //Prima di tutto, assegno il danno al target.

        Player target = targets.get(0);
        Token d = new Token();
        d.setChampionName(user.getPlayerboard().getChampionName());
        Token d2 = new Token();
        d2.setChampionName(user.getPlayerboard().getChampionName());

        ArrayList <Token> damages = target.getPlayerboard().getDamage();
        damages.add(d);
        damages.add(d2);

        target.getPlayerboard().setDamage(damages);

        //Se non è nella stanza del vortice, ce lo metto.

        if(!target.getPosition().equals(vortex.getPosition())){
            target.setPosition(vortex.getPosition());
        }

        //----- FINE EFFETTO PRINCIPALE ---- //

        //----- INIZIO EFFETTO SECONDARIO ---- //

        //Innanzitutto, controllo che il giocatore abbia abbastanza soldi.
        if(user.getPlayerboard().getAmmoCubes().getRed()==0){
            return;
        }

        //Ora controllo se nelle celle a distanza 1 dal vortex o sul vortex ci sono giocatori che non sono il target
        if(!hasNewTargets(target, user)){
            return;
        }

        //Ora chiedo al giocatore se vuole usare l'effetto secondario.

        boolean answerReceived = false;

        String answer = null;

        while(!answerReceived){

            answer = Server.updateWithAnswer(user, Message.usaEffetto());

            if(!InputCheck.correctYesNo(answer)){
                Server.update(user, Message.inputError());
                continue;
            }

            answerReceived = true;

        }

        if(!InputCheck.yesOrNo(answer)){
            return;
        }

        //Adesso, chiedo al giocatore di scegliere fino a due bersagli da colpire, diversi dal precedente.

        ArrayList <Player> newTargets = new ArrayList();

        for(Player  possibleTarget : Server.connectedPlayers ){

            if(possibleTarget.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.equals(possibleTarget)){
                continue;
            }

            newTargets.add(possibleTarget);

        }

        getNewTargets(user, newTargets);

        //Applico danno ai bersagli

        for(Player newTarget : newTargets){

            Token d1 = new Token();
            d1.setChampionName(user.getPlayerboard().getChampionName());
            ArrayList <Token> damages1 = newTarget.getPlayerboard().getDamage();
            damages1.add(d1);

            if(!newTarget.getPosition().equals(vortex.getPosition())){
                newTarget.setPosition(vortex.getPosition());
            }


        }

        //Ritorno
        return;
    }



    private void getNewTargets(Player user, ArrayList<Player> newTargets) {

        //newTargets è già senza player e senza precedente bersaglio.
        //devo chiedere all'utente di scegliere uno o due bersagli.
       //Elimino dall'array newTargets tutti i giocatori che non possono essere colpiti.

        for(Player possibleTarget : newTargets){

            if(possibleTarget.getPosition().equals(vortex.getPosition())){
                continue;
            }

            if(Check.reachableCells(vortex, 1).contains(possibleTarget.getPosition())){
                continue;
            }

            newTargets.remove(possibleTarget);

        }

        //Ora ho solamente i giocatori che si possono colpire.

        boolean chosen = false;
        String chosenTarget = null;
        ArrayList <Player> chosenTargets = null;

        while(!chosen){

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(newTargets));

            if(!InputCheck.nicknameCheck(chosenTarget)){
                Server.update(user, Message.inputError());
                continue;
            }

            for(Player target : newTargets){
                if(target.getNickname().equalsIgnoreCase(chosenTarget)){
                    chosen = true;
                    chosenTargets.add(target);
                    newTargets.remove(target);
                    break;
                }
            }

            Server.update(user, Message.bersaglioNonValido());

        }

        //Se ci sono altri giocatori da colpire, chiedo all'utente se vuole sceglierne altri.

        if(newTargets.size()>0){

            boolean answerReceived = false;

            String answer = null;

            while(!answerReceived){

                answer = Server.updateWithAnswer(user, Message.usaEffetto());

                if(!InputCheck.correctYesNo(answer)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                answerReceived = true;

            }

            if(!InputCheck.yesOrNo(answer)){
                newTargets = chosenTargets;
                return;
            }

            //Se vuole sceglierne un altro, lo ottengo.

            boolean chosen2 = false;
            String chosen2Target = null;


            while(!chosen2){

                chosen2Target = Server.updateWithAnswer(user, Message.scegliBersaglio(newTargets));

                if(!InputCheck.nicknameCheck(chosen2Target)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                for(Player target : newTargets){
                    if(target.getNickname().equalsIgnoreCase(chosen2Target)){
                        chosen = true;
                        chosenTargets.add(target);
                        newTargets.remove(target);
                        break;
                    }
                }

                Server.update(user, Message.bersaglioNonValido());

            }


        }


        //Termine

        newTargets = chosenTargets;
        return;


    }

    private boolean hasNewTargets(Player target, Player user) {

        ArrayList <Cell> targetCells = Check.reachableCells(vortex, 1);

        for(Player possibleTarget : Server.connectedPlayers){
            if(possibleTarget.getPosition().equals(vortex.getPosition())){
                return true;
            }

            if(targetCells.contains(possibleTarget.getPosition())){
                return true;
            }
        }

        return false;

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        //Prima di tutto, si deve far scegliere all'utente un quadrato che può vedere, tranne il suo, purché
        //contenga almeno un giocatore oppure ci sia almeno un giocatore nei quadrati a distanza unitaria da esso.

        //Estraggo i quadrati che può vedere:

        ArrayList <Cell> targetSquares = getVisibleSquares(user);

        //Rimuovo quello dell'utente:
        targetSquares.remove(user.getPosition());

        //Per tutte le celle, le inserisco dentro all'array targetCells se contengono un player o se a distanza unitaria ne hanno uno.

        ArrayList<Cell> targetCells =  new ArrayList<>();

        for(Cell possibleTarget : targetSquares){

            for(Player player : Server.connectedPlayers){

                if(player.getNickname().equalsIgnoreCase(user.getNickname())){
                    continue;
                }

                if(player.getPosition().equals(possibleTarget)){
                    targetCells.add(possibleTarget);
                }

                //Devo controllare se questo giocatore è presente nelle celle a distanza unitaria dalla cella possible.

                vortex.setPosition(possibleTarget);

                if(Check.reachableCells(vortex, 1).contains(player.getPosition())){
                    targetCells.add(possibleTarget);
                }

            }

        }

        //Ora, chiedo all'utente di scegliere una cella tra targetCells.

        boolean foundCell = false;
        String chosenCell = new String();
        int indexCell = 0;

        while(!foundCell){

            chosenCell = Server.updateWithAnswer(user, Message.scegliCellaVortex(targetCells));

            try{

                InputCheck.numberCheck(chosenCell);
                indexCell = Integer.parseInt(chosenCell);

                if(indexCell<0 || indexCell>targetCells.size()-1){
                    Server.update(user, Message.cellaVortexNonDisponibile());
                    continue;
                }

                foundCell = true;

            }catch(NumberFormatException e){
                    Server.update(user, Message.cellaVortexNonDisponibile());
                    continue;
            }

        }

        //Ora, dentro vortexCell ho aperto un vortice.

        Cell vortexCell = targetCells.get(indexCell);
        vortex.setPosition(vortexCell);
        Server.update(user, Message.vortexAperto());

        //Cerco i player che si trovano o in quella cella, o in quelle a distanza unitaria da essa, giocatore escluso.

        ArrayList <Player> targetPlayers = new ArrayList<>();

        for(Player possibleTarget : Server.connectedPlayers){

            if(possibleTarget.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(possibleTarget.getPosition().equals(vortexCell)){

                targetPlayers.add(possibleTarget);

            }

            if(Check.reachableCells(vortex, 1).contains(possibleTarget.getPosition())){
                targetPlayers.add(possibleTarget);
            }

        }

        //Ora dentro targetPlayers ho i possibili bersagli. Chiedo al giocatore di sceglierne uno.

        boolean foundTarget = false;
        ArrayList <Player> finalTarget = new ArrayList();

        while(!foundTarget){

            String targetNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(targetPlayers));

            if(!InputCheck.nicknameCheck(targetNickname)){
                Server.update(user, Message.inputError());
                continue;
            }

            for(Player target : targetPlayers){
                if(target.getNickname().equalsIgnoreCase(targetNickname)){
                    finalTarget.add(target);
                    foundTarget = true;
                }
            }

            Server.update(user, Message.bersaglioNonValido());

        }


        return finalTarget;
    }

    @Override
    public boolean hasTargets(Player user) {

        //Prima di tutto, si ottengono i quadrati che l'utente può vedere, ma non il proprio quadrato.

        ArrayList <Cell> visibleSquares = getVisibleSquares(user);
        visibleSquares.remove(user.getPosition());

        //Ad essi, devo aggiungere i quadrati a distanza unitaria.

        addUnitDistance(visibleSquares);

        //In tutti questi quadrati, devo cercare se c'è almeno un player, giocatore escluso.
        //se c'è, ritorno true. Alrimenti, false.

        for(Cell cell : visibleSquares){

            for(Player player : Server.connectedPlayers){
                if(player.getNickname().equalsIgnoreCase(user.getNickname())){
                    continue;
                }

                if(player.getPosition().equals(cell)){
                    return true;
                }
            }

        }


        return false;
    }

    public ArrayList <Cell> getVisibleSquares(Player user){

        ArrayList<Cell> visibleSquares = new ArrayList<>();

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

    private void addVisibleCells(ArrayList<Cell> visibleSquares, int room) {

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

    private void addUnitDistance(ArrayList <Cell> cells){

        for(Cell cell : cells){

            Player fakePlayer = new Player();
            fakePlayer.setPosition(cell);

            ArrayList <Cell> unitCells = Check.reachableCells(fakePlayer, 1);

            for(Cell unitCell : unitCells){
                if(!cells.contains(unitCell)){
                    cells.add(unitCell);
                }
            }

        }

    }

}
