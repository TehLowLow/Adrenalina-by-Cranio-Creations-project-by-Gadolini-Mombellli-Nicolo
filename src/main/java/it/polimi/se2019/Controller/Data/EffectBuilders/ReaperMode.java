package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.*;

public class ReaperMode extends Effect {
    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        if (targets.equals(null)){

            update(user, Message.nessunBersaglio());
            return;

        }


        for (Player target:targets){

            ArrayList<Token> damages = target.getPlayerboard().getDamage();

            Token d1 = new Token();
            Token d2 = new Token();

            damages.add(d1);
            damages.add(d2);

            target.getPlayerboard().setDamage(damages);

            update(target, Message.colpito(user));

            user.getPlayerboard().getAmmoCubes().setBlueCubes(user.getPlayerboard().getAmmoCubes().getBlue() - 1);
            user.getPlayerboard().getAmmoCubes().setRedCubes(user.getPlayerboard().getAmmoCubes().getRed() - 1);

        }

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> targets = new ArrayList<>();

        if (!hasTargets(user)){

            return targets;

        }


        for (Player player: connectedPlayers){
            if (!player.equals(user)){

                if (player.getPosition().equals(user.getPosition())){

                    targets.add(player);

                }

            }
        }


        return targets;




    }

    @Override
    public boolean hasTargets(Player user) {
        for (Player player: connectedPlayers){
            if (!player.equals(user)){

                if (player.getPosition().equals(user.getPosition())){

                    return true;
                }

            }
        }

        return false;

    }
}
