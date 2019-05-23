package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;
import java.util.concurrent.CopyOnWriteArrayList;


import static it.polimi.se2019.Controller.Adrenalina.Check.visibleSquares;

public class VortexCannon extends Effect {

   //Creo un vortice. Lo tratto come un player perché è comodo da spostare.

   private static Player vortex;

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        //Prima di tutto, assegno il danno al target.

        Player target = targets.get(0);
        Damage.giveDamage(2, user, target);

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

            answer = Server.updateWithAnswer(user, Message.usareEffettoBase());

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

        CopyOnWriteArrayList <Player> newTargets = new CopyOnWriteArrayList();

        for(Player  possibleTarget : Server.connectedPlayers ){

            if(possibleTarget.equals(user)){
                continue;
            }

            if(possibleTarget.equals(target)){
                continue;
            }

            newTargets.add(possibleTarget);

        }

        newTargets = getNewTargets(user, newTargets);

        //Applico danno ai bersagli

        for(Player newTarget : newTargets){

            Damage.giveDamage(1, user, newTarget);

            if(!newTarget.getPosition().equals(vortex.getPosition())){
                newTarget.setPosition(vortex.getPosition());
            }


        }

        //Ritorno
        return;


    }



    private CopyOnWriteArrayList <Player> getNewTargets(Player user, CopyOnWriteArrayList<Player> newTargets) {

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

        CopyOnWriteArrayList <Player> chosenTargets = new CopyOnWriteArrayList<>();

        Player chosenPlayer = ChoosePlayer.one(user, newTargets);

        chosenTargets.add(chosenPlayer);



        //Se ci sono altri giocatori da colpire, chiedo all'utente se vuole sceglierne altri.

        if(newTargets.size()>0){

            boolean answerReceived = false;

            String answer = null;

            while(!answerReceived){

                answer = Server.updateWithAnswer(user, Message.usareEffettoBase());

                if(!InputCheck.correctYesNo(answer)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                answerReceived = true;

            }

            if(!InputCheck.yesOrNo(answer)){
                newTargets = chosenTargets;
                return chosenTargets;
            }

            //Se vuole sceglierne un altro, lo ottengo.

            boolean chosen2 = false;
            String chosen2Target = null;

            chosenPlayer = ChoosePlayer.one(user, newTargets);

            chosenTargets.add(chosenPlayer);

        }

        return chosenTargets;


    }

    private boolean hasNewTargets(Player target, Player user) {

        CopyOnWriteArrayList <Cell> targetCells = Check.reachableCells(vortex, 1);

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
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        //Prima di tutto, si deve far scegliere all'utente un quadrato che può vedere, tranne il suo, purché
        //contenga almeno un giocatore oppure ci sia almeno un giocatore nei quadrati a distanza unitaria da esso.

        //Estraggo i quadrati che può vedere:

        CopyOnWriteArrayList <Cell> visibleCells = Check.visibleSquares(user);

        //Rimuovo quello dell'utente:
        visibleCells.remove(user.getPosition());

        //Per tutte le celle, le inserisco dentro all'array targetCells se contengono un player o se a distanza unitaria ne hanno uno.

        CopyOnWriteArrayList<Cell> targetCells =  new CopyOnWriteArrayList<>();

        for(Cell possibleCell : visibleCells){

            for(Player player : Server.connectedPlayers){

                if(player.equals(user)){
                    continue;
                }

                if(player.getPosition().equals(possibleCell) && !targetCells.contains(possibleCell)){
                    targetCells.add(possibleCell);
                    continue;
                }

                //Devo controllare se questo giocatore è presente nelle celle a distanza unitaria dalla cella possible.

                vortex = new Player();

                vortex.setPosition(possibleCell);

                if(Check.reachableCells(vortex, 1).contains(player.getPosition()) && !targetCells.contains(possibleCell)){
                    targetCells.add(possibleCell);
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
        Server.update(user, Message.vortexAperto(vortexCell));

        //Cerco i player che si trovano o in quella cella, o in quelle a distanza unitaria da essa, giocatore escluso.

        CopyOnWriteArrayList <Player> targetPlayers = new CopyOnWriteArrayList<>();

        for(Player possibleTarget : Server.connectedPlayers){

            if(possibleTarget.equals(user)){
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

        CopyOnWriteArrayList <Player> finalTarget = new CopyOnWriteArrayList();

        finalTarget.add(ChoosePlayer.one(user, targetPlayers));

        return finalTarget;
    }

    @Override
    public boolean hasTargets(Player user) {

        //Prima di tutto, si ottengono i quadrati che l'utente può vedere, ma non il proprio quadrato.

        CopyOnWriteArrayList <Cell> visibleSquares = visibleSquares(user);
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

    private void addUnitDistance(CopyOnWriteArrayList <Cell> cells){

        for(Cell cell : cells){

            Player fakePlayer = new Player();
            fakePlayer.setPosition(cell);

            CopyOnWriteArrayList <Cell> unitCells = Check.reachableCells(fakePlayer, 1);

            for(Cell unitCell : unitCells){
                if(!cells.contains(unitCell)){
                    cells.add(unitCell);
                }
            }

        }

    }


}
