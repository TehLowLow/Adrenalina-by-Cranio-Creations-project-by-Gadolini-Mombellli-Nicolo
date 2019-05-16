package it.polimi.se2019.Controller.Data.EffectBuilders;


import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Adrenalina.Interaction;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.*;

public class MachineGunEffect extends Effect {
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets == null) {

            update(user, Message.nessunBersaglio());
            return;
        }

        for (Player player : targets) {

            CopyOnWriteArrayList<Token> damages = player.getPlayerboard().getDamage();

            Token d1 = new Token();

            damages.add(d1);

            player.getPlayerboard().setDamage(damages);

            update(player, Message.colpito(user));

        }


        /*
        chiedo se vuole usare il Focus shot
         */

        boolean focusShot = false;

        if (canUseFocusShot(user)) {


            String scelta = updateWithAnswer(user, "Vuoi usare il Colpo Focalizzato?");

            while (!InputCheck.correctYesNo(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, "Vuoi usare il Colpo Focalizzato?");


            }

            if (scelta.equalsIgnoreCase("si")) {


                focusShot = true;
                applyFocusShot(user, focusShotGetTargets(user, targets));

            }

        }


        if (canUseTurretTripod(user, focusShot, targets)) {

            String scelta = updateWithAnswer(user, "Vuoi usare Tripode di supporto?");

            while (!InputCheck.correctYesNo(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, "Vuoi usare Tripode di supporto?");


            }

            if (scelta.equalsIgnoreCase("si")) {

                applyTurretTripod(user, turretTripodGetTargets(user, focusShot, targets, focusShotGetTargets(user, targets)));

            }

        }


    }

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        if (!hasTargets(user)) {

            return null;

        }

        possibleTargets = Check.visiblePlayers(user);

        if (possibleTargets.size() == 1) {

            String chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

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


        if (possibleTargets.size() > 1) {

            /*
            scelta primo bersaglio
             */

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


                    /*
                    lo tolgo x non averlo dopo nella scelta del secondo
                     */
                    possibleTargets.remove(player);

                }


            }




            /*
            eventuale scelta secondo bersaglio
             */

            String scelta = updateWithAnswer(user, Message.scegliAltroBersaglio());

            /*
            controllo se l'utente ha digitato una stringa corrispondente a sì o a no
             */

            while (!InputCheck.correctYesNo(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, Message.scegliAltroBersaglio());

            }

            if (scelta.equalsIgnoreCase("si")) {

                chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

                /*
                controllo validità dell'input dell'utente
                */

                while (!InputCheck.nicknameCheck(chosenTarget)) {

                    update(user, Message.bersaglioNonValido());

                    chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

                }

                valid = false;

                for (Player target:targets){

                    if (target.getNickname().equalsIgnoreCase(chosenTarget)){

                        valid = true;

                    }

                }



                while (!valid) {

                    update(user, Message.bersaglioNonValido());

                    chosenTarget = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

                    for (Player target:targets){

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

            }


        }

        return targets;
    }

    @Override
    public boolean hasTargets(Player user) {

        if (Check.visiblePlayers(user).size() > 0) {

            return true;

        } else {
            return false;
        }
    }


    public boolean canUseFocusShot(Player user) {

        Rybamount cost = new Rybamount();
        cost.setRedCubes(0);
        cost.setBlueCubes(0);
        cost.setYellowCubes(1);

        if (Check.affordable(user, cost)) {

            if (Check.visiblePlayers(user).size() > 0) {

                return true;

            }


        }
        return false;
    }

    public CopyOnWriteArrayList<Player> focusShotGetTargets(Player user, CopyOnWriteArrayList<Player> possibleTargets) {

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

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


    public void applyFocusShot(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets.equals(null)) {

            update(user, Message.nessunBersaglio());
            return;
        }

        for (Player player : targets) {

            CopyOnWriteArrayList<Token> damages = player.getPlayerboard().getDamage();

            Token d1 = new Token();

            damages.add(d1);

            player.getPlayerboard().setDamage(damages);

            update(player, Message.colpito(user));

            user.getPlayerboard().getAmmoCubes().setYellowCubes(user.getPlayerboard().getAmmoCubes().getYellow() - 1);

        }


    }


    public synchronized boolean canUseTurretTripod(Player user, boolean focusShot, CopyOnWriteArrayList<Player> focusShotTargets) {

        Rybamount cost = new Rybamount();
        cost.setYellowCubes(0);
        cost.setBlueCubes(1);
        cost.setRedCubes(0);

        if (Check.affordable(user, cost)) {

            if (focusShot) {

                CopyOnWriteArrayList<Player> possibleTargets = Check.visiblePlayers(user);

                for (Player player : possibleTargets) {

                    if (focusShotTargets.contains(player)) {

                        possibleTargets.remove(player);

                    }

                }

                if (possibleTargets.size() > 0) {

                    return true;

                }

                return false;
            } else {

                if (Check.visiblePlayers(user).size() > 0) {
                    return true;
                }

                return false;

            }

        }

        return false;
    }


    CopyOnWriteArrayList<Player> turretTripodGetTargets(Player user, boolean focusShot, CopyOnWriteArrayList<Player> focusShotTargets, CopyOnWriteArrayList<Player> basicTargets) {

        CopyOnWriteArrayList<Player> possibleTargetsBasicFocus = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Player> possibleOtherTargets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        possibleTargetsBasicFocus = basicTargets;
        possibleOtherTargets = Check.visiblePlayers(user);


        if (focusShot) {


            if (focusShotTargets.size() == basicTargets.size()) {

                for (Player player : Check.visiblePlayers(user)) {

                    if (basicTargets.contains(player)) {

                        possibleOtherTargets.remove(player);

                    }

                }

            }


            if (focusShotTargets.size() < basicTargets.size()) {

                for (Player player : basicTargets) {

                    if (focusShotTargets.contains(player)) {

                        possibleTargetsBasicFocus.remove(player);

                    }

                }

                for (Player player : Check.visiblePlayers(user)) {

                    if (basicTargets.contains(player)) {

                        possibleOtherTargets.remove(player);

                    }

                }

            }





            /*
            se non ho altri bersagli, scelgo il primo bersaglio (quello già colpito prima)
             */


            if (possibleOtherTargets.size() == 0) {


                for (Player player : possibleTargetsBasicFocus) {

                    targets.add(player);

                }

            }


            /*
            se no, chiedo all'utente se vuole colpire un bersaglio o due
             */

            String scelta = updateWithAnswer(user, Message.scegliBersaglioTurretTripod());

            while (!InputCheck.chooseTurretTripodTarget(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, Message.scegliBersaglioTurretTripod());

            }


            if (scelta.equalsIgnoreCase("0")) {

                for (Player player : possibleTargetsBasicFocus) {

                    targets.add(player);

                }

            }

            if (scelta.equalsIgnoreCase("1")) {

                targets.add(ChoosePlayer.one(user, possibleOtherTargets));

            }


            if (scelta.equalsIgnoreCase("2")) {

                for (Player player : possibleTargetsBasicFocus) {

                    targets.add(player);

                }

                targets.add(ChoosePlayer.one(user, possibleOtherTargets));

            }
        }

        /*
        se non ho ancora fatto Focus Shot
         */

        else {


             /*
            se non ho altri bersagli, scelgo il primo bersaglio (quello già colpito prima)
             */


            if (possibleOtherTargets.size() == 0) {


                targets.add(ChoosePlayer.one(user, possibleTargetsBasicFocus));

            }


            /*
            se no, chiedo all'utente se vuole colpire un bersaglio o due
             */

            String scelta = updateWithAnswer(user, Message.scegliBersaglioTurretTripod());

            while (!InputCheck.chooseTurretTripodTarget(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, Message.scegliBersaglioTurretTripod());

            }


            if (scelta.equalsIgnoreCase("0")) {

                targets.add(ChoosePlayer.one(user, possibleTargetsBasicFocus));

            }

            if (scelta.equalsIgnoreCase("1")) {

                targets.add(ChoosePlayer.one(user, possibleOtherTargets));

            }


            if (scelta.equalsIgnoreCase("2")) {

                targets.add(ChoosePlayer.one(user, possibleTargetsBasicFocus));

                targets.add(ChoosePlayer.one(user, possibleOtherTargets));

            }


        }

        return targets;
    }


    public void applyTurretTripod(Player user, CopyOnWriteArrayList<Player> targets) {

        for (Player player : targets) {

            Damage.giveDamage(1, user, player);

        }

        Rybamount cost = new Rybamount();
        cost.setBlueCubes(1);
        cost.setRedCubes(0);
        cost.setYellowCubes(0);

        Interaction.pay(user, cost);

    }

}
