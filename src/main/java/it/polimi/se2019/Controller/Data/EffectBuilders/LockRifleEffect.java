package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Controller.Adrenalina.InputCheck.yesOrNo;
import static it.polimi.se2019.Network.Server.*;

public class LockRifleEffect extends Effect {


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        if (targets.equals(null)){

            update(user, Message.nessunBersaglio());
            return;

        }


        for (Player target : targets) {

            ArrayList<Token> damages = target.getPlayerboard().getDamage();
            ArrayList<Token> markers = target.getPlayerboard().getMarker();

            Token d1 = new Token();
            Token d2 = new Token();
            Token m1 = new Token();


            damages.add(d1);
            damages.add(d2);
            markers.add(m1);

            Check.limitMarkers(target, user);

            target.getPlayerboard().setDamage(damages);
            target.getPlayerboard().setMarker(markers);

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
    public ArrayList<Player> getTargets(Player user) {


        ArrayList<Player> targets = new ArrayList<>();

        if (!hasTargets(user)) {

            return null;

        }

        ArrayList<Player> possibleTargets = new ArrayList<>();

        possibleTargets = Check.visiblePlayers(user);

        String chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        /*
        controllo validità dell'input dell'utente
         */

        while (!InputCheck.nicknameCheck(chosenTarget)) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

        }

        while (!possibleTargets.contains(chosenTarget)) { //TODO

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));


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

    public ArrayList<Player> getOptionalEffectTargets(Player user, ArrayList<Player> alreadyHit, ArrayList<Player> visiblePlayers) {

        ArrayList<Player> possibleTargets = new ArrayList<>();

        ArrayList<Player> targets = new ArrayList<>();

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

        while (!possibleTargets.contains(chosenTarget)) {

            update(user, Message.bersaglioNonValido());

            chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));


        }


        for (Player player : connectedPlayers) {

            if (player.getNickname().equals(chosenTarget)) {

                targets.add(player);

            }

        }

        return targets;


    }


    public void applyOptionalEffect(Player user, ArrayList<Player> targets) {

        for (Player target : targets) {

            ArrayList<Token> markers = target.getPlayerboard().getMarker();

            Token m1 = new Token();

            markers.add(m1);

            Check.limitMarkers(target, user);

            target.getPlayerboard().setMarker(markers);

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
