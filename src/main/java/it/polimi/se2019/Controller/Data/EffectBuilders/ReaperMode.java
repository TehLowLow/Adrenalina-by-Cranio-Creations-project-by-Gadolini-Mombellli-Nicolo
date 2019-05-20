package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.*;

public class ReaperMode extends Effect {
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets.equals(null)){

            update(user, Message.nessunBersaglio());
            return;

        }


        for (Player target:targets){

            CopyOnWriteArrayList<Token> damages = target.getPlayerboard().getDamage();

            Damage.giveDamage(2, user, target);

            user.getPlayerboard().getAmmoCubes().setBlueCubes(user.getPlayerboard().getAmmoCubes().getBlue() - 1);
            user.getPlayerboard().getAmmoCubes().setRedCubes(user.getPlayerboard().getAmmoCubes().getRed() - 1);

        }

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

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
