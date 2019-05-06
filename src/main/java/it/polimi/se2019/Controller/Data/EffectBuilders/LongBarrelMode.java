package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.ArrayList;

public class LongBarrelMode extends Effect {


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        Damage.giveDamage(2, user, targets.get(0));

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Cell> reachables = Check.reachableCells(user, 1);
        ArrayList<Player> possibleTargets = new ArrayList<>();
        ArrayList<Player> chosenTarget = new ArrayList<>();

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachables.contains(target.getPosition())){
                possibleTargets.add(target);
            }
        }

        chosenTarget.add(ChoosePlayer.one(user, possibleTargets));

        return chosenTarget;
    }

    @Override
    public boolean hasTargets(Player user) {

        ArrayList <Cell> reachables = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(reachables.contains(target.getPosition())){
                return true;
            }
        }

        return false;
    }
}
