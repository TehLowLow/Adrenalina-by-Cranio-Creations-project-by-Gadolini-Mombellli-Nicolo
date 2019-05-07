package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

public class PlasmaGun extends Effect {

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        boolean usedMove = false;
        boolean chosen = false;

        while(!chosen){

            String answer = Server.updateWithAnswer(user, Message.usareSlittamento());

            if(!InputCheck.correctYesNo(answer)){
                Server.update(user, Message.inputError());
                continue;
            }

            if(!InputCheck.yesOrNo(answer)){
                chosen = true;
                continue;
            }

            phaseMove(user);
            chosen = true;
            usedMove = true;

        }

        targets = getTargets(user);

        Damage.giveDamage(2, user, targets.get(0));

        chosen = false;

        Rybamount effectRyb = new Rybamount();
        effectRyb.setBlueCubes(1);
        effectRyb.setYellowCubes(0);
        effectRyb.setRedCubes(0);

        if(Check.affordable(user, effectRyb)) {

            while (!chosen) {


                String answer = Server.updateWithAnswer(user, Message.usareSovraccarico());

                if (!InputCheck.correctYesNo(answer)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                if (InputCheck.yesOrNo(answer)) {
                    Damage.giveDamage(1, user, targets.get(0));
                }

                chosen = true;

            }

        }

        if(!usedMove){

            chosen = false;

            while(!chosen){

                String answer = Server.updateWithAnswer(user, Message.usareSlittamento());

                if(!InputCheck.correctYesNo(answer)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                if(InputCheck.yesOrNo(answer)){
                    phaseMove(user);
                }

                chosen = true;

            }

        }


    }

    private void phaseMove(Player user) {

        ArrayList <Cell> reachableCells = Check.reachableCells(user, 2);

        boolean chosen = false;

        while(!chosen){

            int index = 0;

            String indexCell = Server.updateWithAnswer(user, Message.scegliCella(reachableCells));

            try{

                index = InputCheck.numberCheck(indexCell);

                if(index<0 || index>reachableCells.size()-1){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

                Cell newPosition = reachableCells.get(index);
                user.setPosition(newPosition);

            }catch (NumberFormatException e){
                Server.update(user, Message.inputError());
                continue;
            }

        }

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Player> possibleTargets = Check.visiblePlayers(user);
        ArrayList <Player> targets = new ArrayList<>();

        if(possibleTargets.isEmpty()){
            return targets;
        }

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return null;
    }

    @Override
    public boolean hasTargets(Player user) {

        return true;
    }
}
