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

import java.util.concurrent.CopyOnWriteArrayList;

public class BasicShockwave extends Effect {


    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        for(Player target : targets){
            Damage.giveDamage(1, user, target);
        }

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(user, 1);
        CopyOnWriteArrayList <Player> chosenTargets = new CopyOnWriteArrayList();
        CopyOnWriteArrayList <Player> possibleTargets = new CopyOnWriteArrayList();

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachableCells.contains(target.getPosition())){
                possibleTargets.add(target);
            }
        }

        boolean chose = false;

        //Gli faccio scegliere un giocatore;
        Player chosen = ChoosePlayer.one(user, possibleTargets);
        //Lo metto nell'array di scelti;
        chosenTargets.add(chosen);
        //Lo elimino dai possible insieme a quelli nello stesso quadrato.
        for(Player toremove : possibleTargets){
            if(toremove.getPosition().equals(chosen.getPosition())){
                possibleTargets.remove(toremove);
            }
        }

        int hitNumber = 0;

        while(!possibleTargets.isEmpty() || hitNumber<2){

            String answer = Server.updateWithAnswer(user, Message.scegliAltroBersaglio());

            if(!InputCheck.correctYesNo(answer)){
                continue;
            }


            if(InputCheck.yesOrNo(answer)){

                hitNumber++;

                //Gli faccio scegliere un giocatore;
                chosen = ChoosePlayer.one(user, possibleTargets);
                //Lo metto nell'array di scelti;
                chosenTargets.add(chosen);
                //Lo elimino dai possible insieme a quelli nello stesso quadrato.
                for(Player toremove : possibleTargets){
                    if(toremove.getPosition().equals(chosen.getPosition())){
                        possibleTargets.remove(toremove);
                    }
                }

            }

            hitNumber = 4;

        }

        return chosenTargets;
    }

    public boolean hasTargets(Player user){

        CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachableCells.contains(target.getPosition())){
                return true;
            }
        }

        return false;

    }

}
