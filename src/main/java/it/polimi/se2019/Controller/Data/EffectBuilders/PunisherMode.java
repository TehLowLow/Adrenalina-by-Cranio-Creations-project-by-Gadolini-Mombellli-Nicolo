package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.ArrayList;

public class PunisherMode extends Effect {


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(3, user, target);

        target.setPosition(user.getPosition());

        return;


    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Cell> reachableCells = Check.reachableCells(user, 2);
        reachableCells.add(user.getPosition());
        ArrayList <Player> possibleTargets = new ArrayList<>();
        ArrayList <Player> targets = new ArrayList<>();

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachableCells.contains(target.getPosition())){
                possibleTargets.add(target);
            }

        }

        targets.add(ChoosePlayer.one(user, possibleTargets));
        return targets;
    }

    @Override
    public boolean hasTargets(Player user) {

        ArrayList <Cell> reachableCells = Check.reachableCells(user, 2);
        reachableCells.add(user.getPosition());
        ArrayList <Player> targets = new ArrayList<>();

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachableCells.contains(target.getPosition())){
                targets.add(target);
            }

        }


        if(targets.isEmpty()) {
            return false;
        }
        return true;
    }
}
