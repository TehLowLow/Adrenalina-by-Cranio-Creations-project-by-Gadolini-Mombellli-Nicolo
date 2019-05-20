package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.connectedPlayers;
import static it.polimi.se2019.Network.Server.update;

public class BasicElectroschythe extends Effect {
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets.equals(null)){

            update(user, Message.nessunBersaglio());
            return;

        }


        for (Player target:targets){

            Damage.giveDamage(1, user, target);

            update(target, Message.colpito(user));

        }

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        if (!hasTargets(user)){

            return null;

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
