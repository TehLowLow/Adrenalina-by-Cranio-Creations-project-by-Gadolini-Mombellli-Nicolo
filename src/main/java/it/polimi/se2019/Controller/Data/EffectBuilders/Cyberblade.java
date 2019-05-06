package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Adrenalina.Interaction;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

public class Cyberblade extends Effect {

    boolean usedStep = false;
    Rybamount diceCost = new Rybamount();


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        ArrayList currentTargets = getCurrentTargets(user, null);

        //Se non ho bersagli nella mia casella, devo obbligare il giocatore a spostarsi.
        if(currentTargets.isEmpty()){
            shadowStep(user);
        }

        //Devo chiedere al giocatore se vuole usare subito la mossa oppure se usare l'effetto.

        boolean useStep = askStep(user);

        //Se sceglie la mossa, usedStep deve andare a true.

        if(useStep){
            shadowStep(user);
            usedStep = true;
        }

        //Nel caso il giocatore si sia spostato, devo vedere se ha ancora bersagli a tiro.
        currentTargets = getCurrentTargets(user, null);

        //Se non ne ha, l'effetto finisce.
        if(currentTargets.isEmpty()){
            return;
        }

        //Se ne ha, si prosegue applicando l'effetto base.
        baseEffect(user, currentTargets);
        return;

    }

    //Ritorna true se il giocatore vuole usare lo shadow step.
    private boolean askStep(Player user){

        boolean hasChosen = false;
        boolean useStep = false;

        while(!hasChosen){

            String answer = Server.updateWithAnswer(user, Message.usaShadowStep());

            if(!InputCheck.correctYesNo(answer)){
                continue;
            }

            useStep = InputCheck.yesOrNo(answer);
            hasChosen = true;

        }

        return useStep;

    }

    //Dopo l'effetto, se l'utente ancora non ha usato la mossa, gli chiedo se vuole
    // usarla. Se si, usedStep va a true. Se non vuole, gli chiedo se vuole attivare
    //l'effetto secondario (se può).
    private void baseEffect(Player user, ArrayList <Player> targets){

        Player target = ChoosePlayer.one(user, targets);

        givedamage(user, target);


        if(!usedStep){

            boolean useStep = askStep(user);

            if(useStep){
                shadowStep(user);
                usedStep = true;
            }

        }

        diceCost.setYellowCubes(1);
        diceCost.setRedCubes(0);
        diceCost.setBlueCubes(0);
        boolean hasChosen = false;

        if(Check.affordable(user, diceCost)){

            ArrayList currentTargets = getCurrentTargets(user, target);

            if(currentTargets.isEmpty()){
                return;
            }

            boolean useDice = false;

            while(!hasChosen){

                String answer = Server.updateWithAnswer(user, Message.useDice());

                if(!InputCheck.correctYesNo(answer)){
                    continue;
                }

                hasChosen = true;
                useDice = InputCheck.yesOrNo(answer);

            }

            if(useDice){
                sliceDice(user, currentTargets);
            }

        }

    }

    private void givedamage(Player user, Player target){

        Token d1 = new Token();
        Token d2 = new Token();
        d1.setChampionName(user.getPlayerboard().getChampionName());
        d2.setChampionName(user.getPlayerboard().getChampionName());
        ArrayList <Token> damages = target.getPlayerboard().getDamage();
        damages.add(d1);
        damages.add(d2);

        target.getPlayerboard().setDamage(damages);

    }


    private void shadowStep(Player user){

        ArrayList <Cell> reachables = Check.reachableCells(user, 1);

        boolean chosen = false;
        Cell chosenCell = null;

        while(!chosen){

            String direction = Server.updateWithAnswer(user, Message.scegliDirezioneMossa(user, reachables));

            if(!InputCheck.directionCheck(direction)){
                Server.update(user, Message.inputError());
                continue;
            }

            if(direction.equalsIgnoreCase("alto")){
                chosenCell = user.getPosition().getUpConnection().getConnectedCell();
            }

            if(direction.equalsIgnoreCase("basso")){
                chosenCell = user.getPosition().getDownConnection().getConnectedCell();
            }

            if(direction.equalsIgnoreCase("sinistra")){
                chosenCell = user.getPosition().getLeftConnection().getConnectedCell();
            }

            if(direction.equalsIgnoreCase("destra")){
                chosenCell = user.getPosition().getRightConnection().getConnectedCell();
            }

            if(!reachables.contains(chosenCell)){
                Server.update(user, Message.cellaNonDisponibile());
                continue;
            }

            chosen = true;
        }

        user.setPosition(chosenCell);

    }

    //Se l'utente ancora non ha usato la mossa, alla fine dell'effetto gli chiedo se vuole usarla.
    private void sliceDice(Player user, ArrayList<Player> targets){

        boolean hasChosen = false;
        Player target = new Player();

        while(!hasChosen){

            String targetNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(targets));

            if(!InputCheck.nicknameCheck(targetNickname)){
                Server.update(user,Message.inputError());
            }

            for(Player possibleTarget : targets){

                if(possibleTarget.getNickname().equalsIgnoreCase(targetNickname)){
                    target = possibleTarget;
                    hasChosen = true;
                    continue;
                }


            }

            Server.update(user, Message.bersaglioNonValido());

        }

        givedamage(user, target);

        Interaction.pay(user, diceCost);

        if(!usedStep){

            boolean useStep = askStep(user);

            if(useStep){
                shadowStep(user);
                usedStep = true;
            }

        }

    }

    //A questa funzione si dovrà passare un arraylist dei bersagli da colpire. Ricorda che
    //chi è già stato colpito non deve farne parte.
    private ArrayList <Player> getCurrentTargets(Player user, Player oldTarget){

        ArrayList <Player> targets = new ArrayList<Player>();


            for(Player target : Server.connectedPlayers){

                if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                    continue;
                }

                //Se oldTarget non è null,vuol dire che qualcuno è stato colpito prima.
                if(oldTarget!= null && target.equals(oldTarget)){

                    continue;

                }

                if(target.getPosition().equals(user.getPosition())){
                    targets.add(target);
                }
        }

        return targets;

    }

    //In quest'arma, il metodo getTargets ritorna un arraylist vuoto, poiché i bersagli dipendono
    //dal fatto che l'utente può scegliere di muoversi o meno. Essi vengono quindi recuperati in applyEffect
    //con il metodo getCurrentTargets. Questo metodo va tuttavia lasciato per rendere l'arma consistente con le altre
    //e poterla usare nello stesso modo in cui le altre armi vengono utilizzate.
    @Override
    public ArrayList<Player> getTargets(Player user) {

        return new ArrayList<Player>();
    }

    @Override
    public boolean hasTargets(Player user) {

        ArrayList <Cell> reachables = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.getPosition().equals(user.getPosition())){
                return true;
            }

            if(reachables.contains(target.getPosition())){
                return true;
            }

        }

        return false;
    }
}
