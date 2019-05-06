package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PulverizeMode extends Effect {


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(3, user, target);
        moveTarget(target, user);

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Player> targets = new ArrayList<Player>();
        ArrayList <Player> chosenTarget = new ArrayList<Player>();

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.getPosition().equals(user.getPosition())){
                targets.add(target);
            }
        }

        chosenTarget.add(ChoosePlayer.one(user, targets));
        return chosenTarget;
    }

    @Override
    public boolean hasTargets(Player user) {

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.getPosition().equals(user.getPosition())){
                return true;
            }
        }

        return false;
    }

    public void moveTarget(Player target, Player user){

        ArrayList <Cell> reachableCells = Check.reachableCells(target, 2);
        reachableCells.add(target.getPosition());

        boolean chosen = false;
        int index = 0;

        while(!chosen){

            String indexCell = Server.updateWithAnswer(user, Message.scegliCella(reachableCells));

            try{
                index = InputCheck.numberCheck(indexCell);

                if(index<0 || index>reachableCells.size()-1){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

                target.setPosition(reachableCells.get(index));


            }catch(NumberFormatException e){
                Server.update(user, Message.inputError());
                continue;
            }

        }

    }



}
