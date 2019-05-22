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

public class MachineGunEffect extends Effect {
    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets == null) {

            update(user, Message.nessunBersaglio());
            return;
        }

        for (Player player : targets) {

            Damage.giveDamage(1, user, player);

            update(player, Message.colpito(user));

        }


        /*
        chiedo se vuole usare il Focus shot
         */

        boolean focusShot = false;

        CopyOnWriteArrayList<Player> focusShotTargets = new CopyOnWriteArrayList<>();

        if (canUseFocusShot(user)) {


            String scelta = updateWithAnswer(user, "Vuoi usare il Colpo Focalizzato?");

            while (!InputCheck.correctYesNo(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, "Vuoi usare il Colpo Focalizzato?");


            }

            if (scelta.equalsIgnoreCase("si")) {


                focusShot = true;
                focusShotTargets = focusShotGetTargets(user, targets);
                applyFocusShot(user, focusShotTargets);

            }

        }


        if (canUseTurretTripod(user, focusShot, targets)) {

            String scelta = updateWithAnswer(user, "Vuoi usare Tripode di supporto?");

            while (!InputCheck.correctYesNo(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, "Vuoi usare Tripode di supporto?");


            }

            if (scelta.equalsIgnoreCase("si")) {

                applyTurretTripod(user, turretTripodGetTargets(user, focusShot, focusShotTargets, targets));

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

            targets.add(ChoosePlayer.one(user, possibleTargets));

            return targets;

        }


        if (possibleTargets.size() > 1) {

            /*
            scelta primo bersaglio
             */

            targets.add(ChoosePlayer.one(user, possibleTargets));

            /*
            tolgo quello colpito dalla seconda scelta
             */
            possibleTargets.remove(targets.get(0));




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

                targets.add(ChoosePlayer.one(user, possibleTargets));

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

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;


    }


    public void applyFocusShot(Player user, CopyOnWriteArrayList<Player> targets) {

        if (targets == null) {

            update(user, Message.nessunBersaglio());
            return;
        }

        for (Player player : targets) {

            Damage.giveDamage(1, user, player);

            update(player, Message.colpito(user));

            Rybamount cost = new Rybamount();
            cost.setBlueCubes(0);
            cost.setRedCubes(0);
            cost.setYellowCubes(1);

            Interaction.pay(user, cost);
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
                        possibleTargetsBasicFocus.remove(player);

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

                targets.add(ChoosePlayer.one(user, possibleTargetsBasicFocus));
                return targets;

            }

            /*
            se nel base ne ho colpito 1 solo e ne ho altri, faccio scegliere 1 degli altri
             */

            if (possibleTargetsBasicFocus.size() == 0){

                targets.add(ChoosePlayer.one(user, possibleOtherTargets));
                return targets;

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

                return targets;

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
