package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;

import java.util.concurrent.CopyOnWriteArrayList;

public class BasicHellion extends Effect {



    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        Player target = targets.get(0);

        Damage.giveDamage(1, user, target);

        for (Player other : Server.connectedPlayers) {

            if (other.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (other.getPosition().equals(target.getPosition())) {

                Damage.giveMarker(1, user, other);

            }

        }

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> chosenTarget = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList<Player> visiblePlayers = Check.visiblePlayers(user);

        for (Player player:visiblePlayers){

            if (player.getPosition().equals(user.getPosition())){

                visiblePlayers.remove(player);

            }

        }

        chosenTarget.add(ChoosePlayer.one(user, visiblePlayers));

        return chosenTarget;
    }

    @Override
    public boolean hasTargets(Player user) {


        CopyOnWriteArrayList<Player> visiblePlayers = Check.visiblePlayers(user);

        for (Player player:visiblePlayers){

            if (player.getPosition().equals(user.getPosition())){

                visiblePlayers.remove(player);

            }

        }

        if (!visiblePlayers.isEmpty()){

            return true;

        }

        return false;
    }


}
