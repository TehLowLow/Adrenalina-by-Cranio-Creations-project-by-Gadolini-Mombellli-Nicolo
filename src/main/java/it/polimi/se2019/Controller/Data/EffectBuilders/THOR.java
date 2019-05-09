package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

public class THOR extends Effect {
    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

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

                if (scelta.equalsIgnoreCase("si")){

                    applyChainReaction(user, chainReactionGetTargets(user, targets.get(0)));

                }

                if (canUseHighVoltage(user, targets.get(0), chainReactionGetTargets(user, targets.get(0)).get(0) )){

                    scelta = updateWithAnswer(user, "Vuoi usare l'alta tensione?");

                    while (!InputCheck.correctYesNo(scelta)) {

                        update(user, Message.inputError());
                        scelta = updateWithAnswer(user, "Vuoi usare l'alta tensione?");


                    }

                    if (scelta.equalsIgnoreCase("si")){

                        applyHighVoltage(user, highVoltageGetTargets(user, targets.get(0),chainReactionGetTargets(user, targets.get(0)).get(0)));

                    }

                }

            }

        }

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> possibleTargets = new ArrayList<>();
        ArrayList<Player> targets = new ArrayList<>();

        if (hasTargets(user)){

            return null;

        }

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;

    }

    @Override
    public boolean hasTargets(Player user) {

        if (Check.visiblePlayers(user).size()>0){
            return true;
        }
        return false;
    }



    public boolean canUseChainReaction(Player user, Player damaged){

        ArrayList<Player> possibleTargets = Check.visiblePlayers(damaged);

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


    public ArrayList<Player> chainReactionGetTargets(Player user, Player damaged){

        ArrayList<Player> possibleTargets = new ArrayList<>();
        ArrayList<Player> targets = new ArrayList<>();

        possibleTargets = Check.visiblePlayers(damaged);

        for (Player player:possibleTargets){

            if (player.equals(user)){

                possibleTargets.remove(player);

            }

        }

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;

    }

    public void applyChainReaction(Player user, ArrayList<Player> targets){

        for (Player player:targets){

            Damage.giveDamage(1, user, player);

        }

    }

    public boolean canUseHighVoltage(Player user, Player damaged, Player damagedChainReaction){

        ArrayList<Player> possibleTargets = Check.visiblePlayers(damaged);

        Rybamount cost = new Rybamount();

        cost.setYellowCubes(0);
        cost.setRedCubes(0);
        cost.setBlueCubes(1);


        if(Check.affordable(user, cost)){

            for (Player player:possibleTargets){

                if (player.equals(damagedChainReaction)){

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

    public ArrayList<Player> highVoltageGetTargets(Player user, Player damaged, Player damagedChainReaction){

        ArrayList<Player> targets = new ArrayList<>();
        ArrayList<Player> possibleTargets = new ArrayList<>();

        possibleTargets = Check.visiblePlayers(damaged);

        for (Player player:possibleTargets){

            if (player.equals(damagedChainReaction)){

                possibleTargets.remove(player);

            }


            if (player.equals(user)){

                possibleTargets.remove(player);

            }

        }

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;




    }


    public void applyHighVoltage(Player user, ArrayList<Player> targets){

        for (Player player:targets){

            Damage.giveDamage(2, user, player);

        }

    }




}
