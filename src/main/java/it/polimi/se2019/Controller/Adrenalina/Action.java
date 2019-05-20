package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Adrenalina.Exceptions.EmptyDeckException;
import it.polimi.se2019.Controller.Adrenalina.Exceptions.LimitPowerUpException;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Controller.Adrenalina.Interaction.selectShotMove;
import static it.polimi.se2019.Controller.Adrenalina.Interaction.shuffleAndDraw;
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
    private static CopyOnWriteArrayList<Cell> reachable;

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

        CopyOnWriteArrayList<String> possibleActions = new CopyOnWriteArrayList<>();

        possibleActions.add("Spara");
        possibleActions.add("Muovi");
        possibleActions.add("Raccogli");


        String chosenAction;
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

            CopyOnWriteArrayList<String> possibleActions = new CopyOnWriteArrayList<>();

            possibleActions.add("Spara");
            possibleActions.add("Muovi");
            possibleActions.add("Raccogli");


            String chosenAction;
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


            CopyOnWriteArrayList<String> possibleActions = new CopyOnWriteArrayList<>();

            possibleActions.add("Spara");
            possibleActions.add("Muovi");
            possibleActions.add("Raccogli");


            String chosenAction;
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
                reachable = Check.moveManager(player, steps);
                response = updateWithAnswer(player, Message.scegliCella(reachable));

                try {
                    cell = InputCheck.numberCheck(response);
                } catch (NumberFormatException e) {
                    update(player, Message.inputError());
                }

            }


            if (cell >= 0 && cell < reachable.size()) {

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

        CopyOnWriteArrayList<Weapon> weapons = player.getPlayerboard().getWeapons();


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
            return;

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

        String answer;
        boolean answered = false;

        while (!answered) {

            answer = Server.updateWithAnswer(player, Message.vuoiMuovertiPU());

            if (!InputCheck.correctYesNo(answer)) {
                Server.update(player, Message.inputError());
                continue;
            }

            answered = true;

            toReach(answer, player);

        }

        if (player.getPosition().getName().equalsIgnoreCase("SpawnCell")) {

            CopyOnWriteArrayList<Weapon> availableWeapons = new CopyOnWriteArrayList<>();
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


                if (!chosen) {
                    Server.update(player, Message.inputError());
                }


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

                        if (!chosen) {
                            Server.update(player, Message.inputError());
                        }

                    }
                }

                /*
                commentato, non dovrebbe servire
                 */
                //Server.update(player, Message.inputError());

            }

            Interaction.pay(player, newWeapon.getPrice());
            player.getPlayerboard().getWeapons().add(newWeapon);

            if (position.getAvailableWeapons().contains(newWeapon)) {
                position.getAvailableWeapons().remove(newWeapon);
            }

            Server.update(player, "Hai acquistato l'arma " + newWeapon.getName() + "!");

            return;
        }

        LootCell position = (LootCell) player.getPosition();
        Loot pickedUp = position.getLoot();

        Interaction.giveRybamount(player, pickedUp);

        if (pickedUp.hasPowerUp()) {

            try {

                Interaction.drawPowerUp(player);

            } catch (EmptyDeckException e) {

                shuffleAndDraw(player);

            } catch (LimitPowerUpException e) {

                Server.update(player, Message.limitePowerup());
            }
        }

        position.setLoot(null);
        Server.update(player, "Hai raccolto il loot!");
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

        selectShotMove(player);

        shot(player);


    }


    /**
     * This method implements the enhanced "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private static void enhancedPickUp(Player player) {

        askForMovement(player);
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

        selectShotMove(player);
        reload(player);
        shot(player);


    }


    /**
     * This method implements the Frenzy Mode "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private static void frenzyPickUp(Player player) {

        enhancedPickUp(player);

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

        askForMovement(player);
        enhancedPickUp(player);
    }

    /**
     * This method implements the enhanced Frenzy Mode "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private static void enhancedFrenzyShot(Player player) {

        selectShotMove(player);
        selectShotMove(player);
        reload(player);
        shot(player);

    }

    public static boolean usePowerUp(Player player) {

        CopyOnWriteArrayList<Powerup> available = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Powerup> powerups = player.getPlayerboard().getPowerups();


        for (Powerup powerup : powerups) {

            if (powerup.getName().contains("Teletrasporto")) {

                available.add(powerup);

            }

            if (powerup.getName().contains("Raggio Cinetico")) {

                available.add(powerup);

            }

        }


        if (available.isEmpty()) {

            return false;

        }

        String scelta = updateWithAnswer(player, Message.vuoiUsarePowerup());

        while (!InputCheck.correctYesNo(scelta)) {

            update(player, Message.inputError());
            scelta = updateWithAnswer(player, Message.vuoiUsarePowerup());

        }

        if (scelta.equalsIgnoreCase("no")) {

            return false;

        }

        scelta = updateWithAnswer(player, Message.scegliPowerUp(available));

        boolean valid = false;
        Powerup chosen = new Powerup();

        for (Powerup powerup:available) {

            if (powerup.getName().equalsIgnoreCase(scelta)) {

                valid = true;
                chosen = powerup;
            }

        }


        while (!valid) {

            update(player, Message.inputError());
            scelta = updateWithAnswer(player, Message.scegliPowerUp(available));

            for (Powerup powerup : available) {

                if (powerup.getName().equalsIgnoreCase(scelta)) {

                    valid = true;
                    chosen = powerup;

                }

            }

        }

        chosen.getEffect().applyEffect(player, null);
        player.getPlayerboard().getPowerups().remove(chosen);
        Board.getDiscardedPowerUps().add(chosen);


        return true;

    }

    private static void toReach(String input, Player player) {

        if (InputCheck.yesOrNo(input)) {

            CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 1);
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

    private static void askForMovement(Player player) {
        String answer;
        boolean answered = false;

        while (!answered) {

            answer = Server.updateWithAnswer(player, Message.vuoiMuovertiPU());

            if (InputCheck.correctYesNo(answer)) {
                Server.update(player, Message.inputError());
                continue;
            }

            answered = true;
        }
    }



}
