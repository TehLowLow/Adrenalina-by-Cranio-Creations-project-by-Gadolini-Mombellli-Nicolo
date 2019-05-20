package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Controller.Adrenalina.InputCheck.yesOrNo;
import static it.polimi.se2019.Network.Server.*;

public class LockRifleEffect extends Effect {


    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets.equals(null)){

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

                while (!yesOrNo(answer)) {

                    update(user, Message.inputError());

                    answer = updateWithAnswer(user, Message.usareEffettoOpzionale());

                }

                if (answer.equalsIgnoreCase("no")) {

                    return;

                }

                if (answer.equalsIgnoreCase("yes")) {


                    applyOptionalEffect(user, getOptionalEffectTargets(user, targets, getTargets(user)));


                }

            }

        }

    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        if (!hasTargets(user)) {

            return null;

        }

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();

        possibleTargets = Check.visiblePlayers(user);

        String chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        /*
        controllo validità dell'input dell'utente
         */

        while (!InputCheck.nicknameCheck(chosenTarget)) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        }

        boolean valid = false;

        for (Player target:possibleTargets){

            if (target.getNickname().equalsIgnoreCase(chosenTarget)){

                valid = true;

            }

        }



        while (!valid) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

            for (Player target:possibleTargets){

                if (target.getNickname().equalsIgnoreCase(chosenTarget)){

                    valid = true;

                }

            }
        }


        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(chosenTarget)) {

                targets.add(player);

            }

        }

        return targets;


    }

    @Override
    public boolean hasTargets(Player user) {

        if (Check.visiblePlayers(user).size() != 0) {
            return true;
        }

        return false;
    }

    public CopyOnWriteArrayList<Player> getOptionalEffectTargets(Player user, CopyOnWriteArrayList<Player> alreadyHit, CopyOnWriteArrayList<Player> visiblePlayers) {

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        for (Player player : visiblePlayers) {
            if (!alreadyHit.contains(player)) {
                possibleTargets.add(player);
            }
        }

        String chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        /*
        controllo validità dell'input dell'utente
         */

        while (!InputCheck.nicknameCheck(chosenTarget)) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        }

        boolean valid = false;

        for (Player target:possibleTargets){

            if (target.getNickname().equalsIgnoreCase(chosenTarget)){

                valid = true;

            }

        }



        while (!valid) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

            for (Player target:possibleTargets){

                if (target.getNickname().equalsIgnoreCase(chosenTarget)){

                    valid = true;

                }

            }
        }


        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(chosenTarget)) {

                targets.add(player);

            }

        }

        return targets;


    }


    public void applyOptionalEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        for (Player target : targets) {

            Damage.giveMarker(1, user, target);

            update(target, Message.colpito(user));

            user.getPlayerboard().getAmmoCubes().setRedCubes(user.getPlayerboard().getAmmoCubes().getRed() - 1);
        }


    }

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
