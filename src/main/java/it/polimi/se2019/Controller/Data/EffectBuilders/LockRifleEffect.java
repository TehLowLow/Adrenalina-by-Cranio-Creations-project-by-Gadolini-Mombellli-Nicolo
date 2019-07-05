package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.Interaction;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Controller.Adrenalina.InputCheck.correctYesNo;
import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

/**
 * Lock Rifle effect
 */


public class LockRifleEffect extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets == null){

            update(user, Message.nessunBersaglio());
            return;

        }


        for (Player target : targets) {


            Damage.giveDamage(2, user, target);
            Damage.giveMarker(1, user, target);

            update(target, Message.colpito(user));


            /*
            ora effetto opzionale
             */

            if (canUseOptionalEffect(user)) {

                String answer = updateWithAnswer(user, Message.usareEffettoOpzionale());

                while (!correctYesNo(answer)) {

                    update(user, Message.inputError());

                    answer = updateWithAnswer(user, Message.usareEffettoOpzionale());

                }

                if (answer.equalsIgnoreCase("no")) {

                    return;

                }

                if (answer.equalsIgnoreCase("si")) {


                    applyOptionalEffect(user, getOptionalEffectTargets(user, targets));


                }

            }

        }

    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the effect.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        if (!hasTargets(user)) {

            return null;

        }

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();

        possibleTargets = Check.visiblePlayers(user);

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;


    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the effect has targets to shot.
     */
    @Override
    public boolean hasTargets(Player user) {

        if (Check.visiblePlayers(user).size() != 0) {
            return true;
        }

        return false;
    }

    /**
     *
     * @param user of the effect.
     * @param alreadyHit is an array of the players hit by the basic effect
     * @return the targets of the optional effect.
     */

    public CopyOnWriteArrayList<Player> getOptionalEffectTargets(Player user, CopyOnWriteArrayList<Player> alreadyHit) {

        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(user);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        for (Player player :possibleTargets) {
            if (alreadyHit.contains(player)) {
                possibleTargets.remove(player);
            }
        }

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;


    }

    /**
     *
     * @param user of the optional effect.
     * @param targets of the optional effect.
     */

    public void applyOptionalEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        for (Player target : targets) {

            Rybamount cost = new Rybamount();

            cost.setRedCubes(1);
            cost.setBlueCubes(0);
            cost.setYellowCubes(0);

            Interaction.pay(user, cost);

            Damage.giveMarker(1, user, target);

            update(target, Message.colpito(user));

        }


    }

    /**
     *
     * @param user of the optional effect.
     * @return true if the optional effect can be used, false otherwise.
     */

    public boolean canUseOptionalEffect(Player user) {

        Rybamount cost = new Rybamount();

        cost.setRedCubes(1);
        cost.setBlueCubes(0);
        cost.setYellowCubes(0);



        if (Check.affordable(user, cost)) {

            if (Check.visiblePlayers(user).size() > 1) {

                return true;

            }


        }

        return false;


    }


}
