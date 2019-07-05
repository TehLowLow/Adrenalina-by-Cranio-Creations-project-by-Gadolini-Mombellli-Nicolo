package it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Network.Server.*;

/**
 * this class manages the damages and the markers
 */

public class Damage {

    /**
     * This method gives a damage from the user to the target
     * @param user who damages
     * @param target the damaged
     */

    private static void giveOneDamage(Player user, Player target) {

        Token damage = new Token();
        damage.setChampionName(user.getPlayerboard().getChampionName());
        CopyOnWriteArrayList<Token> damages = target.getPlayerboard().getDamage();
        damages.add(damage);
        target.getPlayerboard().setDamage(damages);
    }


    /**
     * This method gives damages from the user to the target and checks the markers and the usable powerups
     * @param damageAmount is the amount of damages
     * @param user is who damages
     * @param target is the damaged
     */

    public static void giveDamage(int damageAmount, Player user, Player target) {

        for (int i = 0; i < damageAmount; i++) {

            giveOneDamage(user, target);

        }

        CopyOnWriteArrayList<Token> targetMarkers = target.getPlayerboard().getMarker();

        for (Token marker:targetMarkers){

            if (marker.getChampionName().equalsIgnoreCase(user.getPlayerboard().getChampionName())){

                giveOneDamage(user, target);
                targetMarkers.remove(marker);

            }


        }

        CopyOnWriteArrayList<Powerup> targetPowerups = target.getPlayerboard().getPowerups();
        CopyOnWriteArrayList<Powerup> userPowerups = user.getPlayerboard().getPowerups();
        CopyOnWriteArrayList<Powerup> availableTargetingScopes = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Powerup> availableTagBacks = new CopyOnWriteArrayList<>();

        if(!user.getNickname().equalsIgnoreCase("terminator")) {

            for (Powerup powerup : userPowerups) {

                if (powerup.getName().contains("Mirino")) {

                    availableTargetingScopes.add(powerup);

                }

            }

        }

        if (!availableTargetingScopes.isEmpty()) {

            String scelta = updateWithAnswer(user, Message.vuoiUsarePowerup());

            while (!InputCheck.correctYesNo(scelta)) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, Message.vuoiUsarePowerup());

            }

            if (scelta.equalsIgnoreCase("no")) {

                return;

            }

            scelta = updateWithAnswer(user, Message.scegliPowerUp(availableTargetingScopes));

            boolean valid = false;
            Powerup chosen = new Powerup();

            for (Powerup powerup : availableTargetingScopes) {

                if (powerup.getName().equalsIgnoreCase(scelta)) {

                    valid = true;
                    chosen = powerup;
                }

            }


            while (!valid) {

                update(user, Message.inputError());
                scelta = updateWithAnswer(user, Message.scegliPowerUp(availableTargetingScopes));

                for (Powerup powerup : availableTargetingScopes) {

                    if (powerup.getName().equalsIgnoreCase(scelta)) {

                        valid = true;
                        chosen = powerup;

                    }

                }

            }

            CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();
            targets.add(target);

            chosen.getEffect().applyEffect(user, targets);
            user.getPlayerboard().getPowerups().remove(chosen);
            Board.getDiscardedPowerUps().add(chosen);
            updateAll(user.getNickname() + " ha usato il Mirino per colpire " + target.getNickname());
        }

        Check.markers(user, target);

        update(target, Message.colpito(user));

        if (!target.getNickname().equalsIgnoreCase("terminator")) {

            for (Powerup powerup : targetPowerups) {


                if (powerup.getName().contains("Granata Venom")) {

                    availableTagBacks.add(powerup);

                }

            }
        }


        if (!availableTagBacks.isEmpty()) {

            String scelta = updateWithAnswer(target, Message.vuoiUsarePowerup());

            while (!InputCheck.correctYesNo(scelta)) {

                update(target, Message.inputError());
                scelta = updateWithAnswer(target, Message.vuoiUsarePowerup());

            }

            if (scelta.equalsIgnoreCase("no")) {

                return;

            }

            scelta = updateWithAnswer(target, Message.scegliPowerUp(availableTagBacks));

            boolean valid = false;
            Powerup chosen = new Powerup();

            for (Powerup powerup : availableTagBacks) {

                if (powerup.getName().equalsIgnoreCase(scelta)) {

                    valid = true;
                    chosen = powerup;
                }

            }


            while (!valid) {

                update(target, Message.inputError());
                scelta = updateWithAnswer(target, Message.scegliPowerUp(availableTagBacks));

                for (Powerup powerup : availableTagBacks) {

                    if (powerup.getName().equalsIgnoreCase(scelta)) {

                        valid = true;
                        chosen = powerup;

                    }

                }

            }


            CopyOnWriteArrayList<Player> tagBackTargets = new CopyOnWriteArrayList<>();
            tagBackTargets.add(user);
            chosen.getEffect().applyEffect(target, tagBackTargets);
            target.getPlayerboard().getPowerups().remove(chosen);
            Board.getDiscardedPowerUps().add(chosen);


            update(user, target.getNickname() + " ti ha colpito con una Granata Venom!");
            Server.updateAll(target.getNickname() + " ha colpito " + user.getNickname() + " con una Granata Venom!");
        }

        Server.updateAll(user.getNickname() + " ha colpito " + target.getNickname());

    }

    /**
     * Gives one user marker to the target
     * @param user is who marks
     * @param target is the marked
     */

    private static void giveOneMarker(Player user, Player target) {

        Token marker = new Token();
        marker.setChampionName(user.getPlayerboard().getChampionName());
        CopyOnWriteArrayList<Token> markers = target.getPlayerboard().getMarker();
        markers.add(marker);
        target.getPlayerboard().setMarker(markers);

    }

    /**
     * Gives more user markers to the target
     * @param markerAmount is the n of the markers
     * @param user is who marks
     * @param target is the marked
     */

    public static void giveMarker(int markerAmount, Player user, Player target) {

        for (int i = 0; i < markerAmount; i++) {

            giveOneMarker(user, target);

        }

        Check.limitMarkers(target, user);

        update(target, Message.colpito(user));

        updateAll(user.getNickname() + " ha colpito " + target.getNickname());

    }


}
