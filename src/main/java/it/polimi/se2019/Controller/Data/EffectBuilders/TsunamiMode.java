package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.ArrayList;

public class TsunamiMode extends Effect {
    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        for (Player target : targets) {
            Damage.giveDamage(1, user, target);
        }


    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Cell> reachableCells = Check.reachableCells(user, 1);
        ArrayList<Player> targets = new ArrayList<>();

        for (Player target : Server.connectedPlayers) {

            if (target.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (reachableCells.contains(target.getPosition())) {
                targets.add(target);
            }
        }

        return targets;
    }


    @Override
    public boolean hasTargets(Player user) {

        ArrayList<Cell> reachableCells = Check.reachableCells(user, 1);

        for (Player target : Server.connectedPlayers) {

            if (target.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (reachableCells.contains(target.getPosition())) {
                return true;
            }
        }

        return false;

    }
}

