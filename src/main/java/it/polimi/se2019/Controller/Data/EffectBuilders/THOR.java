package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Adrenalina.Interaction;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

/**
 * Thor effect
 */

public class THOR extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        for (Player player:targets){

            Damage.giveDamage(2, user, player);

            /*
            chain reaction
             */

            if (canUseChainReaction(user, targets.get(0))){

                String scelta = updateWithAnswer(user, "Vuoi usare la reazione a catena?");

                while (!InputCheck.correctYesNo(scelta)) {

                    update(user, Message.inputError());
                    scelta = updateWithAnswer(user, "Vuoi usare la reazione a catena?");


                }

                CopyOnWriteArrayList <Player> damagedChain = new CopyOnWriteArrayList<>();
                boolean usedReaction = false;

                if (scelta.equalsIgnoreCase("si")){

                    usedReaction = true;
                    damagedChain = chainReactionGetTargets(user, targets.get(0));
                    applyChainReaction(user, damagedChain);

                }



                if (canUseHighVoltage(user, targets.get(0), damagedChain, usedReaction)){

                    scelta = updateWithAnswer(user, "Vuoi usare l'alta tensione?");

                    while (!InputCheck.correctYesNo(scelta)) {

                        update(user, Message.inputError());
                        scelta = updateWithAnswer(user, "Vuoi usare l'alta tensione?");


                    }

                    if (scelta.equalsIgnoreCase("si")){

                        applyHighVoltage(user, highVoltageGetTargets(user, damagedChain.get(0), targets.get(0)));

                    }

                }

            }

        }

    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the targets of the THOR.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(user);
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();


        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;

    }

    /**
     *
     * @param user the player who has to use the effect.
     * @return true if there are some visible targets.
     */

    @Override
    public boolean hasTargets(Player user) {

        if (Check.visiblePlayers(user).size()>0){
            return true;
        }
        return false;
    }

    /**
     *
     * @param user of the effect
     * @param damaged from the basic effect
     * @return true if the damaged can see some players different from the user and the user can use the second effect
     */

    public boolean canUseChainReaction(Player user, Player damaged){

        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(damaged);

        Rybamount cost = new Rybamount();

        cost.setYellowCubes(0);
        cost.setRedCubes(0);
        cost.setBlueCubes(1);

        if(Check.affordable(user, cost)){

            for (Player player:possibleTargets){

                if (player.equals(user)){

                    possibleTargets.remove(player);

                }

            }

            if (possibleTargets.size()>0){
                return true;
            }





        }

        return false;
    }


    /**
     *
     * @param user of the effect
     * @param damaged from the basic effect
     * @return the targets of the chain reaction
     */

    public CopyOnWriteArrayList<Player> chainReactionGetTargets(Player user, Player damaged){

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        possibleTargets = Check.visiblePlayers(damaged);

        for (Player player:possibleTargets){

            if (player.equals(user)){

                possibleTargets.remove(player);

            }

        }

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;

    }

    /**
     * applies the chain reaction
     * @param user of the effect
     * @param targets of the chain reaction
     */

    public void applyChainReaction(Player user, CopyOnWriteArrayList<Player> targets){

        for (Player player:targets){

            Rybamount cost = new Rybamount();

            cost.setYellowCubes(0);
            cost.setRedCubes(0);
            cost.setBlueCubes(1);
            Interaction.pay(user, cost);
            Damage.giveDamage(1, user, player);

        }

    }

    /**
     *
     * @param user of the effect
     * @param damaged from the basic effect
     * @param damagedChainReaction from the chain reaction
     * @param usedReaction is true if the player has performed the chain reaction
     * @return true if the user can perform high voltage
     */

    public boolean canUseHighVoltage(Player user, Player damaged, CopyOnWriteArrayList <Player> damagedChainReaction, boolean usedReaction){

        CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(damaged);

        //Vuol dire che non hai usato la chain reaction
        if(!usedReaction){
            return false;
        }


        Rybamount cost = new Rybamount();

        cost.setBlueCubes(1);
        cost.setYellowCubes(0);
        cost.setRedCubes(0);

        if(Check.affordable(user, cost)){

            for (Player player:possibleTargets){

                if (player.equals(damagedChainReaction.get(0))){

                    possibleTargets.remove(player);

                }


                if (player.equals(user)){

                    possibleTargets.remove(player);

                }

            }

            if (possibleTargets.size()>0){
                return true;
            }





        }

        return false;
    }


    /**
     *
     * @param user of the effect
     * @param damagedChainReaction from the chain reaction
     * @param firstDamaged is the damaged from the basic effect
     * @return the targets for high voltage
     */

    public CopyOnWriteArrayList<Player> highVoltageGetTargets(Player user, Player damagedChainReaction, Player firstDamaged){

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();

        possibleTargets = Check.visiblePlayers(damagedChainReaction);

        for (Player player:possibleTargets){


            if (player.equals(user)){

                possibleTargets.remove(player);

            }

            if (player.equals(damagedChainReaction)){

                possibleTargets.remove(player);

            }

            if(player.equals(firstDamaged)){
                possibleTargets.remove(player);
            }




        }

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;




    }


    /**
     * applies high voltage mode
     * @param user of the effect
     * @param targets of high voltage
     */

    public void applyHighVoltage(Player user, CopyOnWriteArrayList<Player> targets){

        Rybamount cost = new Rybamount();


        cost.setRedCubes(0);
        cost.setBlueCubes(1);
        cost.setYellowCubes(0);
        Interaction.pay(user, cost);


        for (Player player:targets){

            Damage.giveDamage(2, user, player);

        }

    }




}
