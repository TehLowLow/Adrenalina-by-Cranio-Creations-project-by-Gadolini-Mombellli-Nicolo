package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * alternative effect of the zx-2
 */

public class ScannerMode extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        for (Player target : targets) {
            Damage.giveMarker(1, user, target);
        }

    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the scanner mode
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> target = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(user);

        int number;

        for (number = 0; number < 3; number++) {

            Player chosen = ChoosePlayer.one(user, possibleTargets);
            target.add(chosen);
            possibleTargets.remove(chosen);

            boolean hasChosen = false;

            while (!hasChosen) {

                if (possibleTargets.isEmpty()) {

                    return target;
                }

                String answer = Server.updateWithAnswer(user, Message.scegliAltroBersaglio());

                if (!InputCheck.correctYesNo(answer)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                if (InputCheck.yesOrNo(answer)) {
                    break;
                } else {
                    return target;

                }

            }
        }

        return target;

    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the scanner mode has targets.
     */
    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList<Player> visible = Check.visiblePlayers(user);
        if (visible.isEmpty()) {
            return false;
        }
        return true;
    }
}
