package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.connectedPlayers;
import static it.polimi.se2019.Network.Server.update;

public class BasicElectroschythe extends Effect {
    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        if (targets.equals(null)){

            update(user, Message.nessunBersaglio());
            return;

        }


        for (Player target:targets){

            ArrayList<Token> damages = target.getPlayerboard().getDamage();

            Token d1 = new Token();

            damages.add(d1);

            target.getPlayerboard().setDamage(damages);

            update(target, Message.colpito(user));

        }

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> targets = new ArrayList<>();

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