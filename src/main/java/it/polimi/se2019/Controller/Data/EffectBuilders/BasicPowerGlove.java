package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.ArrayList;

public class BasicPowerGlove extends Effect {

    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(1, user, target);
        Damage.giveMarker(2, user, target);
        user.setPosition(target.getPosition());

    }


    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Player> chosenTarget = new ArrayList<>();
        ArrayList <Player> possibleTargets = new ArrayList<>();

        ArrayList <Cell> cells = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(cells.contains(target.getPosition())){
                possibleTargets.add(target);
            }
        }

        chosenTarget.add(ChoosePlayer.one(user, possibleTargets));

        return chosenTarget;
    }


    @Override
    public boolean hasTargets(Player user) {

        ArrayList <Cell> cells = Check.reachableCells(user, 1);

        for(Player target : Server.connectedPlayers){

            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(cells.contains(target.getPosition())){
                return true;
            }
        }

        return false;
    }
}
