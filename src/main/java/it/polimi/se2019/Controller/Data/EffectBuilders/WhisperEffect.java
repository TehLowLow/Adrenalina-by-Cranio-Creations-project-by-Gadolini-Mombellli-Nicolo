package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.update;

/**
 * whisper effect
 */

public class WhisperEffect extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets == null) {

            update(user, Message.nessunBersaglio());
            return;

        }

        for (Player target : targets) {

            Damage.giveDamage(3, user, target);
            Damage.giveMarker(1, user, target);


            update(target, Message.colpito(user));
        }


    }


    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the whisper
     */
    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {


        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(user);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        for (Player player : possibleTargets) {

            /*
            verifico che non sia nella stessa cella
             */

            if (player.getPosition().equals(user.getPosition())) {

                possibleTargets.remove(player);

            }

            /*
            verifico che non sia a distanza unitaria
             */

            if(unitDistance(player, user)){
                possibleTargets.remove(player);
            }

        }


        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;

    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the whisper can shot somebody.
     */

    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList<Player> visiblePlayers = Check.visiblePlayers(user);

        for (Player player : visiblePlayers) {

            /*
            verifico che non sia nella stessa cella
             */

            if (player.getPosition().equals(user.getPosition())) {

                visiblePlayers.remove(player);
                continue;
            }

            /*
            verifico che non sia a distanza unitaria
             */

            if (player.getPosition().getUpConnection().getConnectedCell() != null && player.getPosition().getUpConnection().getConnectedCell().equals(user.getPosition())) {

                visiblePlayers.remove(player);
                continue;

            }

            if (player.getPosition().getDownConnection().getConnectedCell() != null && player.getPosition().getDownConnection().getConnectedCell().equals(user.getPosition())) {

                visiblePlayers.remove(player);
                continue;

            }

            if (player.getPosition().getLeftConnection().getConnectedCell() != null && player.getPosition().getLeftConnection().getConnectedCell().equals(user.getPosition())) {

                visiblePlayers.remove(player);
                continue;

            }

            if (player.getPosition().getRightConnection().getConnectedCell() != null && player.getPosition().getRightConnection().getConnectedCell().equals(user.getPosition())) {

                visiblePlayers.remove(player);
                continue;

            }

        }

        if (visiblePlayers.isEmpty()) {
            return false;
        }

        return true;


    }

    /**
     *
     * @param target of the whisper
     * @param user of the weapon
     * @return true if user and target are one cell far
     */
    private boolean unitDistance(Player target, Player user) {


        Connection up = user.getPosition().getUpConnection();
        Connection down = user.getPosition().getDownConnection();
        Connection left = user.getPosition().getLeftConnection();
        Connection right = user.getPosition().getRightConnection();

        if (up.getType().equals(Connection.FREE) || up.getType().equals(Connection.DOOR)) {
            if (target.getPosition().equals(up.getConnectedCell())) {
                return true;
            }
        }

        if (down.getType().equals(Connection.FREE) || down.getType().equals(Connection.DOOR)) {
            if (target.getPosition().equals(down.getConnectedCell())) {
                return true;
            }
        }

        if (left.getType().equals(Connection.FREE) || left.getType().equals(Connection.DOOR)) {
            if (target.getPosition().equals(left.getConnectedCell())) {
                return true;
            }
        }

        if (right.getType().equals(Connection.FREE) || right.getType().equals(Connection.DOOR)) {
            if (target.getPosition().equals(right.getConnectedCell())) {
                return true;
            }
        }

        return false;
    }

}
