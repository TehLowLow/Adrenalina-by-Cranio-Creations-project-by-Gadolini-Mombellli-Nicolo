package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Adrenalina.Exceptions.EmptyDeckException;
import it.polimi.se2019.Controller.Adrenalina.Exceptions.LimitPowerUpException;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.Server.*;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

import it.polimi.se2019.View.Message;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

/**
 * This class collects all the actions that a player can perform, both in their standard version and
 * enhanced version. Moreover, it provides the methods for the more powerful actions that can be performed only
 * in Final Frenzy Mode.
 */


public class Action {

    private static int steps;
    private static int cell;
    private static ArrayList<Cell> reachable;

    /*
    METHODS
     */

    /**
     * This method is called every time a player must perform an action.
     * It checks if the player has unlocked the enhanced actions and if it's the Final Frenzy Mode.
     * If none of the previous conditions is true, this method allows the player to perform the standard version
     * of the actions by calling corresponding private methods.
     * If one or both of these conditions are true, this method calls the private methods that perform
     * the enhanced or frenzy version of the actions.
     *
     * @param player the player that is performing an action.
     */
    public static void perform(Player player) {

        ArrayList<String> possibleActions = new ArrayList<>();

        possibleActions.add("Spara");
        possibleActions.add("Muovi");
        possibleActions.add("Raccogli");


        String chosenAction = new String();
        boolean actionPerformed = false;

        for (int actionNumber = 0; actionNumber < 2; actionNumber++) {


            while (!actionPerformed) {

                chosenAction = Server.updateWithAnswer(player, Message.scegliAzione());

                if (!InputCheck.correctAction(chosenAction)) {
                    Server.update(player, Message.inputError());
                    continue;
                }

                if (chosenAction.equalsIgnoreCase("Spara") && !Check.canShot(player)) {
                    Server.update(player, Message.noSparo());
                    continue;
                }

                if (chosenAction.equalsIgnoreCase("spara")) {

                    if (player.getPlayerboard().getDamage().size() >= 6) {

                        if (Check.canShotEnhanced(player)) {
                            enhancedShot(player);
                            continue;
                        } else {
                            shot(player);
                        }

                    } else {
                        shot(player);
                    }

                }

                if (chosenAction.equalsIgnoreCase("muovi")) {

                    move(player);

                }

                if (chosenAction.equalsIgnoreCase("raccogli")) {

                    if (player.getPlayerboard().getDamage().size() >= 3) {

                        enhancedPickUp(player);

                    } else {
                        pickUp(player);
                    }

                }

                actionPerformed = true;


            }

        }


    }

    public static void performFrenzy(Player player, boolean afterFirstPlayer) {

        if (!afterFirstPlayer) {

            ArrayList<String> possibleActions = new ArrayList<>();

            possibleActions.add("Spara");
            possibleActions.add("Muovi");
            possibleActions.add("Raccogli");


            String chosenAction = new String();
            boolean actionPerformed = false;

            for (int actionNumber = 0; actionNumber < 2; actionNumber++) {

                while (!actionPerformed) {

                    chosenAction = Server.updateWithAnswer(player, Message.scegliAzione());

                    if (!InputCheck.correctAction(chosenAction)) {
                        Server.update(player, Message.inputError());
                        continue;
                    }

                    if (chosenAction.equalsIgnoreCase("Spara") && !Check.canShotEnhanced(player)) {
                        Server.update(player, Message.noSparo());
                        continue;
                    }

                    if (chosenAction.equalsIgnoreCase("spara")) {

                        frenzyShot(player);

                    }

                    if (chosenAction.equalsIgnoreCase("muovi")) {

                        frenzyMove(player);

                    }

                    if (chosenAction.equalsIgnoreCase("raccogli")) {

                        frenzyPickUp(player);

                    }

                    actionPerformed = true;


                }

            }

            return;

        } else {


            ArrayList<String> possibleActions = new ArrayList<>();

            possibleActions.add("Spara");
            possibleActions.add("Muovi");
            possibleActions.add("Raccogli");


            String chosenAction = new String();
            boolean actionPerformed = false;


            while (!actionPerformed) {

                chosenAction = Server.updateWithAnswer(player, Message.scegliAzioneFrenesiaFP());

                if (!InputCheck.correctAction(chosenAction)) {
                    Server.update(player, Message.inputError());
                    continue;
                }

                if (chosenAction.equalsIgnoreCase("Spara") && !Check.canShotEnhancedFrenzy(player)) {
                    Server.update(player, Message.noSparo());
                    continue;
                }

                if (chosenAction.equalsIgnoreCase("spara")) {

                    enhancedFrenzyShot(player);

                }

                if (chosenAction.equalsIgnoreCase("raccogli")) {

                    enhancedFrenzyPickUp(player);

                }

                actionPerformed = true;


            }


        }

    }

    /**
     * This method allows a player to reload a weapon.
     *
     * @param player the player that wants to perform the reload action.
     */
    public static void reload(Player player) {

        boolean canReload = false;

        for (Weapon weapon : player.getPlayerboard().getWeapons()) {

            if (!weapon.isLoaded() && Check.affordable(player, weapon.getRechargeCost())) {
                canReload = true;
            }

        }

        if (canReload) {

            boolean chosen = false;

            while (!chosen) {

                String answer = Server.updateWithAnswer(player, Message.vuoiRicaricare());

                if (!InputCheck.correctYesNo(answer)) {
                    Server.update(player, Message.inputError());
                    continue;
                }

                chosen = true;

                if (InputCheck.yesOrNo(answer)) {

                    boolean chosenWeapon = false;

                    while (!chosenWeapon) {

                        String weaponName = Server.updateWithAnswer(player, Message.scegliArma(player.getPlayerboard().getWeapons()));

                        for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                            if (weapon.getName().equalsIgnoreCase(weaponName)) {

                                if (!weapon.isLoaded() && Check.affordable(player, weapon.getRechargeCost())) {

                                    chosenWeapon = true;
                                    Interaction.pay(player, weapon.getRechargeCost());
                                    weapon.setLoaded(true);
                                    Server.update(player, Message.haiRicaricato(weapon));

                                }
                            }

                        }

                    }

                }

            }

        }

    }

    /*
    ----- STANDARD ACTIONS
    The standard version of the actions.
     */

    /**
     * This method implements the standard "move" action.
     *
     * @param player the Player that performs the move action.
     */
    private static void move(Player player) {


        String response;
        String s;
        boolean correct = false;


        while (!correct) {

            s = updateWithAnswer(player, Message.stepNumber());

            try {
                steps = InputCheck.numberCheck(s);
                correct = true;
            } catch (NumberFormatException e) {
                update(player, Message.inputError());
            }


            if (steps > 0 && steps < 4) {
                reachable = Check.reachableCells(player, steps);
                response = updateWithAnswer(player, Message.scegliCella(reachable));

                try {
                    cell = InputCheck.numberCheck(response);
                } catch (NumberFormatException e) {
                    update(player, Message.inputError());
                }

            }


            if (cell > 0 && cell < reachable.size()) {

                player.setPosition(reachable.get(cell));
                update(player, Message.movedTo());

            } else {
                update(player, Message.inputError());
            }

        }

    }

    /**
     * This method implements the standard "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private static void shot(Player player) {

        ArrayList<Weapon> weapons = player.getPlayerboard().getWeapons();


        String armaScelta = updateWithAnswer(player, Message.scegliArma(weapons));

        while (!InputCheck.correctWeapon(armaScelta, weapons)) {

            update(player, Message.inputError());
            armaScelta = updateWithAnswer(player, Message.scegliArma(weapons));

        }


        Weapon chosenWeapon = new Weapon();

        for (Weapon weapon : weapons) {

            if (armaScelta.equalsIgnoreCase(weapon.getName())) {

                chosenWeapon = weapon;

            }


        }

        /*
        armi con solo eff base o opzionali
         */

        if (chosenWeapon.getAlternativeEffect() == null) {

            while (!(chosenWeapon.getBaseEffect().hasTargets(player) && chosenWeapon.isLoaded())) {

                update(player, Message.inputError());
                armaScelta = updateWithAnswer(player, Message.scegliArma(weapons));

                for (Weapon weapon : weapons) {

                    if (armaScelta.equalsIgnoreCase(weapon.getName())) {

                        chosenWeapon = weapon;

                    }
                }

            }

            chosenWeapon.getBaseEffect().applyEffect(player, chosenWeapon.getBaseEffect().getTargets(player));


        }

        /*
        armi con effetto alternativo
         */

        while (!((chosenWeapon.getBaseEffect().hasTargets(player) || chosenWeapon.getAlternativeEffect().hasTargets(player)) && chosenWeapon.isLoaded())) {


            update(player, Message.inputError());
            armaScelta = updateWithAnswer(player, Message.scegliArma(weapons));

            for (Weapon weapon : weapons) {

                if (armaScelta.equalsIgnoreCase(weapon.getName())) {

                    chosenWeapon = weapon;

                }
            }

        }

            /*
            vedo se può usare l'effetto alternativo
             */


            /*
            se non lo può usare uso il base
             */

        if (!Check.affordable(player, chosenWeapon.getAlternativeEffectCost())) {

            chosenWeapon.getBaseEffect().applyEffect(player, chosenWeapon.getBaseEffect().getTargets(player));

        }


            /*
            se lo può usare gli faccio scegliere
             */

        else {

            String effettoScelto = updateWithAnswer(player, Message.scegliBaseAlternativo());

            while (!InputCheck.correctBasicOrAlternative(effettoScelto)) {

                update(player, Message.inputError());
                effettoScelto = updateWithAnswer(player, Message.scegliBaseAlternativo());


            }

            if (effettoScelto.equalsIgnoreCase("base")) {

                chosenWeapon.getBaseEffect().applyEffect(player, chosenWeapon.getBaseEffect().getTargets(player));

            }

            if (effettoScelto.equalsIgnoreCase("alternativo")) {

                chosenWeapon.getAlternativeEffect().applyEffect(player, chosenWeapon.getAlternativeEffect().getTargets(player));
                Interaction.pay(player, chosenWeapon.getAlternativeEffectCost());


            }

        }


        chosenWeapon.setLoaded(false);


    }

    /**
     * This method implements the standard "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private static void pickUp(Player player) {

        String answer = new String();
        boolean answered = false;

        while (!answered) {

            answer = Server.updateWithAnswer(player, Message.vuoiMuovertiPU());

            if (InputCheck.correctYesNo(answer)) {
                Server.update(player, Message.inputError());
                continue;
            }

            answered = true;
            if (InputCheck.yesOrNo(answer)) {

                ArrayList<Cell> reachableCells = Check.reachableCells(player, 1);
                Cell toReach = new LootCell();
                int chosenCell = 0;
                boolean chosen = false;

                while (!chosen) {

                    String chosenC = Server.updateWithAnswer(player, Message.scegliCella(reachableCells));

                    try {

                        chosenCell = InputCheck.numberCheck(chosenC);
                        toReach = reachableCells.get(chosenCell);
                        chosen = true;
                    } catch (NumberFormatException e) {
                        Server.update(player, Message.inputError());
                        continue;
                    }

                    player.setPosition(toReach);

                }

            }

        }

        if (player.getPosition().getName().equalsIgnoreCase("SpawnCell")) {

            ArrayList<Weapon> availableWeapons = new ArrayList<>();
            SpawnCell position = (SpawnCell) player.getPosition();
            availableWeapons = position.getAvailableWeapons();
            Weapon newWeapon = new Weapon();

            boolean chosen = false;

            //Scelta dell'arma. Ricordati che può scambiarla e che può anche pagarla.

            while (!chosen) {

                String chosenWeaponName = Server.updateWithAnswer(player, Message.scegliArma(availableWeapons));

                for (Weapon weapon : availableWeapons) {

                    if (weapon.getName().equalsIgnoreCase(chosenWeaponName)) {

                        if (!Check.affordable(player, weapon.getPrice())) {
                            Server.update(player, Message.cubiInsuff());
                            continue;
                        }

                        chosen = true;
                        newWeapon = weapon;
                        continue;

                    }
                }

                Server.update(player, Message.inputError());

            }

            if (player.getPlayerboard().getWeapons().size() == 3) {

                chosen = false;

                while (!chosen) {

                    String chosenWeaponName = Server.updateWithAnswer(player, Message.scegliArma(player.getPlayerboard().getWeapons()));

                    for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                        if (weapon.getName().equalsIgnoreCase(chosenWeaponName)) {

                            chosen = true;

                            position.getAvailableWeapons().remove(newWeapon);
                            player.getPlayerboard().getWeapons().remove(weapon);
                            position.getAvailableWeapons().add(weapon);
                            continue;

                        }

                        Server.update(player, Message.inputError());

                    }
                }

                Server.update(player, Message.inputError());

            }

            Interaction.pay(player, newWeapon.getPrice());
            player.getPlayerboard().getWeapons().add(newWeapon);

            return;
        }

        LootCell position = (LootCell) player.getPosition();
        Loot pickedUp = position.getLoot();

        Interaction.giveRybamount(player, pickedUp);

        if (pickedUp.hasPowerUp()) {

            try {

                Interaction.drawPowerUp(player);

            } catch (EmptyDeckException e) {
                ArrayList<Loot> discarded = Board.getDiscardedLoot();
                Collections.shuffle(discarded);
                Board.setLootDeck(discarded);
                Board.setDiscardedLoot(new ArrayList<Loot>());

                ArrayList<Powerup> powerUps = player.getPlayerboard().getPowerups();
                Powerup drawnPowerUp = Board.getPowerUpDeck().get(0);
                Board.getPowerUpDeck().remove(drawnPowerUp);
                powerUps.add(drawnPowerUp);
                player.getPlayerboard().setPowerups(powerUps);
            } catch (LimitPowerUpException e) {
                Server.update(player, Message.limitePowerup());
            }
        }

        position.setLoot(null);
        return;

    }

    /*
    ----- ENHANCED VERSION
    When a player has taken a certain amount of damages can perform a more powerful version
    of the standard actions, called "enhanced actions".
     */

    /**
     * This method implements the enhanced "move" action. It is not different from the standard "move"
     * action, but it has a dedicated method for future different implementations.
     *
     * @param player the Player that performs the move action.
     */
    private static void enhancedMove(Player player) {

        move(player);
    }

    /**
     * This method implements the enhanced "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private static void enhancedShot(Player player) {

        String sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        while (!InputCheck.correctMoveEnhancedShot(sceltaMovimento)) {

            update(player, Message.inputError());
            sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        }

        boolean canShot = false;

        while (!canShot) {

            if (sceltaMovimento.equalsIgnoreCase("stop")) {

                for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("su")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getUpConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {


                        player.setPosition(fakePlayer.getPosition());
                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }


            if (sceltaMovimento.equalsIgnoreCase("giu")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getDownConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {

                        canShot = true;
                        player.setPosition(fakePlayer.getPosition());

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }


            if (sceltaMovimento.equalsIgnoreCase("destra")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getRightConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {


                        player.setPosition(fakePlayer.getPosition());
                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("sinistra")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getLeftConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {

                        canShot = true;
                        player.setPosition(fakePlayer.getPosition());

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }



        }

        shot(player);


    }





    /**
     * This method implements the enhanced "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private static void enhancedPickUp(Player player) {

        String answer = new String();
        boolean answered = false;

        while (!answered) {

            answer = Server.updateWithAnswer(player, Message.vuoiMuovertiPU());

            if (InputCheck.correctYesNo(answer)) {
                Server.update(player, Message.inputError());
                continue;
            }

            answered = true;
            if (InputCheck.yesOrNo(answer)) {

                ArrayList<Cell> reachableCells = Check.reachableCells(player, 1);
                Cell toReach = new LootCell();
                int chosenCell = 0;
                boolean chosen = false;

                while (!chosen) {

                    String chosenC = Server.updateWithAnswer(player, Message.scegliCella(reachableCells));

                    try {

                        chosenCell = InputCheck.numberCheck(chosenC);
                        toReach = reachableCells.get(chosenCell);
                        chosen = true;
                    } catch (NumberFormatException e) {
                        Server.update(player, Message.inputError());
                        continue;
                    }

                    player.setPosition(toReach);

                }

            }

        }

        pickUp(player);

    }

    /*
    ----- FRENZY MODE VERSION
    When the Final Frenzy Mode is activated, the players whose turn is before first player's turn have access
    to the Frenzy Mode version of the actions.
     */

    /**
     * This method implements the Frenzy Mode "move" action.
     *
     * @param player the Player that performs the move action.
     */
    private static void frenzyMove(Player player) {


        String response;
        String s;
        boolean correct = false;


        while (!correct) {

            s = updateWithAnswer(player, Message.stepNumber());

            try {
                steps = InputCheck.numberCheck(s);
                correct = true;
            } catch (NumberFormatException e) {
                update(player, Message.inputError());
            }


            if (steps > 0 && steps < 5) {
                reachable = Check.reachableCells(player, steps);
                response = updateWithAnswer(player, Message.scegliCella(reachable));

                try {
                    cell = InputCheck.numberCheck(response);
                } catch (NumberFormatException e) {
                    update(player, Message.inputError());
                }

            }


            if (cell > 0 && cell < reachable.size()) {

                player.setPosition(reachable.get(cell));
                update(player, Message.movedTo());

            } else {
                update(player, Message.inputError());
            }

        }

    }

    /**
     * This method implements the Frenzy Mode "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private static void frenzyShot(Player player) {

        String sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        while (!InputCheck.correctMoveEnhancedShot(sceltaMovimento)) {

            update(player, Message.inputError());
            sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        }

        boolean canShot = false;

        while (!canShot) {

            if (sceltaMovimento.equalsIgnoreCase("stop")) {

                for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("su")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getUpConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {


                        player.setPosition(fakePlayer.getPosition());
                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }


            if (sceltaMovimento.equalsIgnoreCase("giu")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getDownConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {

                        canShot = true;
                        player.setPosition(fakePlayer.getPosition());

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }


            if (sceltaMovimento.equalsIgnoreCase("destra")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getRightConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {


                        player.setPosition(fakePlayer.getPosition());
                        canShot = true;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("sinistra")) {

                Player fakePlayer = new Player();

                fakePlayer.setPosition(player.getPosition().getLeftConnection().getConnectedCell());
                fakePlayer.setPlayerboard(new Playerboard());
                fakePlayer.getPlayerboard().setWeapons(player.getPlayerboard().getWeapons());

                for (Weapon weapon : fakePlayer.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(fakePlayer) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(fakePlayer))) {

                        canShot = true;
                        player.setPosition(fakePlayer.getPosition());

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    updateWithAnswer(player, Message.scegliMovimento());

                }

            }



        }
        reload(player);
        shot(player);


    }



    /**
     * This method implements the Frenzy Mode "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private static void frenzyPickUp(Player player) {
    }

    /*
    ----- ENHANCED FRENZY MODE ACTIONS
    The first player and the players whose Frenzy Mode turn is after the first player's one can perform
    one of these two enhanced actions.
     */

    /**
     * This method implements the enhanced Frenzy Mode "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private static void enhancedFrenzyPickUp(Player player) {

    }

    /**
     * This method implements the enhanced Frenzy Mode "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private static void enhancedFrenzyShot(Player player) {
    }


}
