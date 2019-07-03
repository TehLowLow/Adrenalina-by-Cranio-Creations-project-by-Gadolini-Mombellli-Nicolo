package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Adrenalina.Exceptions.EmptyDeckException;
import it.polimi.se2019.Controller.Adrenalina.Exceptions.LimitPowerUpException;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

import static it.polimi.se2019.Model.Connection.DOOR;
import static it.polimi.se2019.Model.Connection.FREE;
import static it.polimi.se2019.Network.Server.*;


/**
 * This class collects all the actions that a player can perform, both in their standard version and
 * enhanced version. Moreover, it provides the methods for the more powerful actions that can be performed only
 * in Final Frenzy Mode.
 */


public class Action {


    /**
     * The number of steps a player wants to make.
     */
    private static int steps;

    /**
     * The index of a cell.
     */
    private static int cell;

    /**
     * The array of reachable cells from a player's position.
     */
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

                if (chosenAction.equalsIgnoreCase("Spara") && !Check.canShot(player) && player.getPlayerboard().getDamage().size() < 6) {
                    Server.update(player, Message.noSparo());
                    continue;
                }

                if (chosenAction.equalsIgnoreCase("spara") && !Check.canShot(player) && player.getPlayerboard().getDamage().size() >= 6) {

                    if (Check.canShotEnhanced(player)) {

                        enhancedShot(player);

                    } else {

                        Server.update(player, Message.noSparo());
                        continue;

                    }

                }

                if (chosenAction.equalsIgnoreCase("spara") && Check.canShot(player)) {

                    if (player.getPlayerboard().getDamage().size() >= 6) {

                        if (Check.canShotEnhanced(player)) {
                            enhancedShot(player);
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

    /**
     * Allows the player to perform one of the last frenzy actions for the final turn.
     *
     * @param player           is the player that has the turn.
     * @param afterFirstPlayer is the list of players after the first player of the game.
     */

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

                    if (chosenAction.equalsIgnoreCase("Spara") && !Check.canShotFrenzy(player)) {
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
     * Perform an action in terminator mode
     *
     * @param player is who performs the action
     * @param last   is true if the player has already done his 2 actions, else otherwise
     */

    public static boolean performTerminator(Player player, boolean last) {

        if (!last) {

            String useTerminator = updateWithAnswer(player, "Vuoi effettuare ora l'azione Terminator?");
            while (!InputCheck.correctYesNo(useTerminator)) {

                update(player, Message.inputError());
                useTerminator = updateWithAnswer(player, "Vuoi effettuare ora l'azione Terminator?");

            }

            if (!InputCheck.yesOrNo(useTerminator)) {
                return false;
            }

        }

        Server.updateAll(player.getNickname() + " sta facendo l'azione Terminator!");

        String move = updateWithAnswer(player, "Vuoi muovere il Terminator di una cella?");
        while (!InputCheck.correctYesNo(move)) {

            update(player, Message.inputError());
            move = updateWithAnswer(player, "Vuoi muovere il Terminator di una cella?");

        }

        if (InputCheck.yesOrNo(move)) {

            String response = "";
            boolean correct = false;

            while (!correct) {

                reachable = Check.moveManager(connectedPlayers.get(connectedPlayers.size() - 1), 1);
                response = updateWithAnswer(player, Message.scegliCella(reachable));

                try {
                    cell = InputCheck.numberCheck(response);
                } catch (NumberFormatException e) {
                    update(player, Message.inputError());
                    updateWithAnswer(player, Message.scegliCella(reachable));
                }


                if (cell >= 0 && cell < reachable.size()) {

                    connectedPlayers.get(connectedPlayers.size() - 1).setPosition(reachable.get(cell));
                    update(player, Message.movedTo());
                    correct = true;
                    updateAll("Il Terminator si è spostato di una cella!");

                } else {
                    update(player, Message.inputError());
                    updateWithAnswer(player, Message.scegliCella(reachable));
                }

            }


        }


        shotTerminator(player);
        return true;


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
                                    updateAll(player.getNickname() + " ha ricaricato " + weapon.getName());

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

        s = updateWithAnswer(player, Message.stepNumber());

        while (!correct) {


            try {
                steps = InputCheck.numberCheck(s);
            } catch (NumberFormatException e) {
                update(player, Message.inputError());
                s = updateWithAnswer(player, Message.stepNumber());
                continue;
            }

            boolean correctCell = false;


            if (steps > 0 && steps < 4) {
                reachable = Check.moveManager(player, steps);
                response = updateWithAnswer(player, Message.scegliCella(reachable));
                correct = true;


                while (!correctCell) {

                    try {
                        cell = InputCheck.numberCheck(response);


                    } catch (NumberFormatException e) {
                        update(player, Message.inputError());
                        response = updateWithAnswer(player, Message.scegliCella(reachable));
                        continue;
                    }

                    if (cell >= 0 && cell < reachable.size()) {

                        player.setPosition(reachable.get(cell));
                        correctCell = true;
                        update(player, Message.movedTo());


                    } else {
                        update(player, Message.inputError());
                        response = updateWithAnswer(player, Message.scegliCella(reachable));

                    }
                }
            } else {
                update(player, Message.inputError());
                s = updateWithAnswer(player, Message.stepNumber());


            }

        }

        updateAll(player.getNickname() + " si è mosso di " + steps + " celle!");

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

            if (armaScelta != null && armaScelta.equalsIgnoreCase(weapon.getName())) {

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

                    if (armaScelta != null && armaScelta.equalsIgnoreCase(weapon.getName())) {

                        chosenWeapon = weapon;

                    }
                }

            }

            chosenWeapon.getBaseEffect().applyEffect(player, chosenWeapon.getBaseEffect().getTargets(player));
            chosenWeapon.setLoaded(false);
            update(player, "Hai sparato!");
            return;

        }

        /*
        armi con effetto alternativo
         */

        while (!((chosenWeapon.getBaseEffect().hasTargets(player) || chosenWeapon.getAlternativeEffect().hasTargets(player)) && chosenWeapon.isLoaded())) {


            update(player, Message.inputError());
            armaScelta = updateWithAnswer(player, Message.scegliArma(weapons));

            for (Weapon weapon : weapons) {

                if (armaScelta != null && armaScelta.equalsIgnoreCase(weapon.getName())) {

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

        if (chosenWeapon.getAlternativeEffect() == null || !Check.affordable(player, chosenWeapon.getAlternativeEffectCost())) {

            chosenWeapon.getBaseEffect().applyEffect(player, chosenWeapon.getBaseEffect().getTargets(player));

        }


            /*
            se lo può usare gli faccio scegliere
             */

        else {

            String effettoScelto = updateWithAnswer(player, Message.scegliBaseAlternativo());
            boolean done = false;

            while (!InputCheck.correctBasicOrAlternative(effettoScelto)) {

                update(player, Message.inputError());
                effettoScelto = updateWithAnswer(player, Message.scegliBaseAlternativo());


            }

            while (!done) {

                if (effettoScelto.equalsIgnoreCase("base") && chosenWeapon.getBaseEffect().hasTargets(player)) {

                    chosenWeapon.getBaseEffect().applyEffect(player, chosenWeapon.getBaseEffect().getTargets(player));
                    done = true;
                    continue;

                }

                if (effettoScelto.equalsIgnoreCase("alternativo") && chosenWeapon.getAlternativeEffect().hasTargets(player)) {

                    Interaction.pay(player, chosenWeapon.getAlternativeEffectCost());
                    chosenWeapon.getAlternativeEffect().applyEffect(player, chosenWeapon.getAlternativeEffect().getTargets(player));
                    done = true;
                    continue;

                }

                while (!InputCheck.correctBasicOrAlternative(effettoScelto)) {

                    update(player, Message.inputError());
                    effettoScelto = updateWithAnswer(player, Message.scegliBaseAlternativo());


                }

            }

        }


        chosenWeapon.setLoaded(false);
        update(player, "Hai sparato!");


    }

    /**
     * This method implements the standard "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private static void pickUp(Player player) {

        askForMovementOneStep(player);

        if (player.getPosition().getName().equalsIgnoreCase("SpawnCell")) {

            CopyOnWriteArrayList<Weapon> availableWeapons = new CopyOnWriteArrayList<>();
            SpawnCell position = (SpawnCell) player.getPosition();
            availableWeapons = position.getAvailableWeapons();
            Weapon newWeapon = new Weapon();

            boolean chosen = false;
            boolean notAffordable = false;

            //Scelta dell'arma. Ricordati che può scambiarla e che può anche pagarla.

            while (!chosen) {

                String chosenWeaponName = Server.updateWithAnswer(player, Message.scegliArma(availableWeapons));

                for (Weapon weapon : availableWeapons) {

                    if (weapon.getName().equalsIgnoreCase(chosenWeaponName)) {

                        if (!Check.affordable(player, weapon.getPrice())) {
                            Server.update(player, Message.cubiInsuff());
                            notAffordable = true;
                            continue;
                        }

                        chosen = true;
                        newWeapon = weapon;
                        continue;

                    }
                }


                if (!chosen && !notAffordable) {
                    Server.update(player, Message.inputError());
                }

                notAffordable = false;


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

            if (Check.isSpawn(player.getPosition())) {

                updateAll(player.getNickname() + " ha acquistato l'arma " + newWeapon.getName() + "!");

            }

            return;
        }

        LootCell position = (LootCell) player.getPosition();
        Loot pickedUp = position.getLoot();


        if (pickedUp == null) {
            update(player, Message.nessunLoot());
            return;
        }


        Interaction.giveRybamount(player, pickedUp);

        if (pickedUp.hasPowerUp()) {

            try {

                Interaction.drawPowerUp(player);

            } catch (EmptyDeckException e) {

                Interaction.recoverPowerUps();

                try {
                    Interaction.drawPowerUp(player);
                } catch (Exception e1) {

                    Server.update(player, Message.limitePowerup());
                }

            } catch (LimitPowerUpException e) {

                Server.update(player, Message.limitePowerup());
            }
        }

        position.setLoot(null);
        Server.update(player, "Hai raccolto il loot!");


        if (!Check.isSpawn(player.getPosition())) {

            updateAll(player.getNickname() + " ha raccolto un loot!");

        }

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

        askForMovementOneStep(player);
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

        s = updateWithAnswer(player, Message.stepNumber());

        while (!correct) {


            try {


                steps = InputCheck.numberCheck(s);
            } catch (NumberFormatException e) {
                update(player, Message.inputError());
                s = updateWithAnswer(player, Message.stepNumber());
                continue;
            }

            boolean correctCell = false;


            if (steps > 0 && steps < 5) {
                reachable = Check.moveManager(player, steps);
                correct = true;
                response = updateWithAnswer(player, Message.scegliCella(reachable));

                while (!correctCell) {

                    try {
                        cell = InputCheck.numberCheck(response);


                    } catch (NumberFormatException e) {
                        update(player, Message.inputError());
                        response = updateWithAnswer(player, Message.scegliCella(reachable));
                        continue;
                    }

                    if (cell >= 0 && cell < reachable.size()) {


                        correctCell = true;
                        player.setPosition(reachable.get(cell));

                        update(player, Message.movedTo());


                    } else {
                        update(player, Message.inputError());
                        response = updateWithAnswer(player, Message.scegliCella(reachable));

                    }
                }
            } else {
                update(player, Message.inputError());
                s = updateWithAnswer(player, Message.stepNumber());


            }

        }

        updateAll(player.getNickname() + " si è mosso di " + steps + " celle!");

    }

    /**
     * This method implements the Frenzy Mode "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private static void frenzyShot(Player player) {

        selectShotMove(player);

        /*
        aggiungo in un array le armi cariche e che hanno bersagli a disposizione
         */

        CopyOnWriteArrayList<Weapon> usableWeapons = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Weapon> playersWeapons = player.getPlayerboard().getWeapons();

        for (Weapon weapon : playersWeapons) {

            if (weapon.getBaseEffect().hasTargets(player) && weapon.isLoaded()) {

                usableWeapons.add(weapon);

            }

        }

        if (!usableWeapons.isEmpty()) {

            reload(player);

        } else {

            for (Weapon weapon : playersWeapons) {

                if (weapon.getBaseEffect().hasTargets(player) && Check.affordable(player, weapon.getRechargeCost())) {

                    usableWeapons.add(weapon);

                }

            }

            reloadFrenzy(player, usableWeapons);

        }


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

        askForMovementOneStep(player);
        enhancedPickUp(player);
    }

    /**
     * This method implements the enhanced Frenzy Mode "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private static void enhancedFrenzyShot(Player player) {

        selectEnhancedFrenzyShotMove(player);

        CopyOnWriteArrayList<Weapon> usableWeapons = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Weapon> playersWeapons = player.getPlayerboard().getWeapons();

        for (Weapon weapon : playersWeapons) {

            if (weapon.getBaseEffect().hasTargets(player) && weapon.isLoaded()) {

                usableWeapons.add(weapon);

            }

        }

        if (!usableWeapons.isEmpty()) {

            reload(player);

        } else {

            for (Weapon weapon : playersWeapons) {

                if (weapon.getBaseEffect().hasTargets(player) && Check.affordable(player, weapon.getRechargeCost())) {

                    usableWeapons.add(weapon);

                }

            }

            reloadFrenzy(player, usableWeapons);

        }


        shot(player);

    }

    /**
     * Allows the player to use a powerup from his hand.
     *
     * @param player is the player asked to use a powerup.
     * @return true if the player has successfully used the powerup.
     */

    public static boolean usePowerUp(Player player) {

        CopyOnWriteArrayList<Powerup> available = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Powerup> powerups = player.getPlayerboard().getPowerups();


        for (Powerup powerup : powerups) {

            if (powerup.getName().contains("Teletrasporto")) {

                available.add(powerup);

            }

            if (powerup.getName().contains("Raggio Cinetico")) {


                for (Player other : connectedPlayers) {

                    if (!other.getPosition().equals(Board.getLimbo()) && !other.equals(player)) {
                        available.add(powerup);
                        break;
                    }
                }


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

        for (Powerup powerup : available) {

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

        CopyOnWriteArrayList<Player> targets = chosen.getEffect().getTargets(player);
        chosen.getEffect().applyEffect(player, targets);
        player.getPlayerboard().getPowerups().remove(chosen);
        Board.getDiscardedPowerUps().add(chosen);
        updateAll(player.getNickname() + " ha usato il " + chosen.getName());


        return true;

    }


    /**
     * Asks a player if he wants to move and reach an adjacent cell.
     *
     * @param input  contains the positive or negative answer after being asked if the player wants to move.
     * @param player is the player that wants to move
     */

    private static void toReachOneStep(String input, Player player) {

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


    /**
     * Asks a player if he wants to move to loot a power up
     *
     * @param player is the player playing the action.
     */

    private static void askForMovementOneStep(Player player) {
        String answer;
        boolean answered = false;

        while (!answered) {

            answer = Server.updateWithAnswer(player, Message.vuoiMuovertiPU());

            if (!InputCheck.correctYesNo(answer)) {
                Server.update(player, Message.inputError());
                continue;
            }

            answered = true;

            toReachOneStep(answer, player);

        }
    }

    /**
     * Asks a player if he wants to move before shooting. This is asked only when enhanced shot is active.
     *
     * @param player is the player that is playing the turn.
     */

    private static void selectShotMove(Player player) {

        Cell position = player.getPosition();

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
                        break;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("su")) {

                if (player.getPosition().getUpConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getUpConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getUpConnection().getConnectedCell());

                    for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                        if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {


                            canShot = true;
                            update(player, "Ti sei spostato in su di una cella!");
                            break;

                        }

                    }

                    if (!canShot) {

                        player.setPosition(position);
                        update(player, "Non puoi sparare");
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }

                } else {
                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }


            }


            if (sceltaMovimento.equalsIgnoreCase("giu")) {

                if (player.getPosition().getDownConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getDownConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getDownConnection().getConnectedCell());

                    for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                        if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {


                            canShot = true;
                            update(player, "Ti sei spostato in giù di una cella!");
                            break;

                        }

                    }

                    if (!canShot) {

                        update(player, "Non puoi sparare");
                        player.setPosition(position);
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }

                } else {
                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }

            }


            if (sceltaMovimento.equalsIgnoreCase("destra")) {

                if (player.getPosition().getRightConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getRightConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getRightConnection().getConnectedCell());

                    for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                        if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {


                            canShot = true;
                            update(player, "Ti sei spostato a destra di una cella!");
                            break;

                        }

                    }

                    if (!canShot) {


                        player.setPosition(position);
                        update(player, "Non puoi sparare");
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }

                } else {
                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }

            }

            if (sceltaMovimento.equalsIgnoreCase("sinistra")) {

                if (player.getPosition().getLeftConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getLeftConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getLeftConnection().getConnectedCell());

                    for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                        if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {


                            canShot = true;
                            update(player, "Ti sei spostato a sinistra di una cella!");
                            break;

                        }

                    }

                    if (!canShot) {


                        player.setPosition(position);
                        update(player, "Non puoi sparare");
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }

                } else {
                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }

            }
        }
    }


    /**
     * Asks a player if he wants to perform the reload Frenzy move.
     *
     * @param player        is the player playing the frenzy turn.
     * @param usableWeapons is the list of available weapons to the player.
     */

    private static void reloadFrenzy(Player player, CopyOnWriteArrayList<Weapon> usableWeapons) {

        String chosenWeapon = updateWithAnswer(player, Message.scegliArmaRF(usableWeapons));

        while (!InputCheck.correctWeaponRF(chosenWeapon, usableWeapons)) {

            update(player, Message.inputError());
            chosenWeapon = updateWithAnswer(player, Message.scegliArmaRF(usableWeapons));

        }

        Weapon chosen = new Weapon();

        for (Weapon weapon : usableWeapons) {

            if (weapon.getName().equalsIgnoreCase(chosenWeapon)) {

                chosen = weapon;
                break;

            }

        }

        chosen.setLoaded(true);
        Interaction.pay(player, chosen.getRechargeCost());
        update(player, Message.haiRicaricato(chosen));

        updateAll(player.getNickname() + " ha ricaricato l'arma " + chosen.getName());


    }


    /**
     * Asks a player if he wants to move before shooting another player during the frenzy turn.
     *
     * @param player is the player playing the frenzy turn.
     */

    private static void selectEnhancedFrenzyShotMove(Player player) {


        String sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        Cell position = player.getPosition();

        while (!InputCheck.correctMoveEnhancedShot(sceltaMovimento)) {

            update(player, Message.inputError());
            sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());

        }

        boolean canShot = false;
        boolean canDoSecondMove = false;

        while (!canShot && !canDoSecondMove) {

            if (sceltaMovimento.equalsIgnoreCase("stop")) {

                for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                    if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                        canShot = true;
                        break;

                    }

                }

                if (!canShot) {

                    update(player, "Non puoi sparare");

                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }


            }


            if (sceltaMovimento.equalsIgnoreCase("su")) {

                if (player.getPosition().getUpConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getUpConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getUpConnection().getConnectedCell());

                    Cell fakePosition = player.getPosition();

                    CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 1);

                    reachableCells.add(fakePosition);

                    for (Cell cell : reachableCells) {

                        player.setPosition(cell);

                        for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                            if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                                canDoSecondMove = true;

                                player.setPosition(fakePosition);

                                break;

                            }


                        }


                    }

                    if (canDoSecondMove) {

                        update(player, "Ti sei spostato in alto di una cella!");

                        selectShotMove(player);

                    } else {

                        player.setPosition(position);
                        update(player, "Non puoi sparare");
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }


                } else {

                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }


            }

            if (sceltaMovimento.equalsIgnoreCase("giu")) {

                if (player.getPosition().getDownConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getDownConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getDownConnection().getConnectedCell());

                    Cell fakePosition = player.getPosition();

                    CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 1);

                    reachableCells.add(fakePosition);

                    for (Cell cell : reachableCells) {

                        player.setPosition(cell);

                        for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                            if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                                canDoSecondMove = true;

                                player.setPosition(fakePosition);

                                break;

                            }


                        }


                    }

                    if (canDoSecondMove) {

                        update(player, "Ti sei spostato in basso di una cella!");

                        selectShotMove(player);

                    } else {

                        player.setPosition(position);
                        update(player, "Non puoi sparare");
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }


                } else {

                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }


            }


            if (sceltaMovimento.equalsIgnoreCase("sinistra")) {

                if (player.getPosition().getLeftConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getLeftConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getLeftConnection().getConnectedCell());

                    Cell fakePosition = player.getPosition();

                    CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 1);

                    reachableCells.add(fakePosition);

                    for (Cell cell : reachableCells) {

                        player.setPosition(cell);

                        for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                            if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                                canDoSecondMove = true;

                                player.setPosition(fakePosition);

                                break;

                            }


                        }


                    }

                    if (canDoSecondMove) {

                        update(player, "Ti sei spostato a sinistra di una cella!");

                        selectShotMove(player);

                    } else {

                        player.setPosition(position);
                        update(player, "Non puoi sparare");
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }


                } else {

                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }


            }


            if (sceltaMovimento.equalsIgnoreCase("destra")) {

                if (player.getPosition().getRightConnection().getType().equalsIgnoreCase(DOOR) || player.getPosition().getRightConnection().getType().equalsIgnoreCase(FREE)) {

                    player.setPosition(player.getPosition().getRightConnection().getConnectedCell());

                    Cell fakePosition = player.getPosition();

                    CopyOnWriteArrayList<Cell> reachableCells = Check.reachableCells(player, 1);

                    reachableCells.add(fakePosition);

                    for (Cell cell : reachableCells) {

                        player.setPosition(cell);

                        for (Weapon weapon : player.getPlayerboard().getWeapons()) {

                            if (weapon.getBaseEffect().hasTargets(player) || (weapon.getAlternativeEffect() != null && weapon.getAlternativeEffect().hasTargets(player))) {

                                canDoSecondMove = true;

                                player.setPosition(fakePosition);

                                break;

                            }


                        }


                    }

                    if (canDoSecondMove) {

                        update(player, "Ti sei spostato a destra di una cella!");

                        selectShotMove(player);

                    } else {

                        player.setPosition(position);
                        update(player, "Non puoi sparare");
                        sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                        continue;

                    }


                } else {

                    update(player, "Cella non valida!");
                    sceltaMovimento = updateWithAnswer(player, Message.scegliMovimento());
                    continue;

                }


            }


        }


    }


    /**
     * Is the terminator shot action.
     *
     * @param player is the player that is playing the terminator turn.
     */

    private static void shotTerminator(Player player) {

        CopyOnWriteArrayList<Player> visible = Check.visiblePlayers(connectedPlayers.get(connectedPlayers.size() - 1));

        for (Player target : visible) {

            if (target.equals(player)) {

                visible.remove(target);

            }

        }


        if (visible.isEmpty()) {

            return;

        }

        Player target = ChoosePlayer.one(player, visible);

        Damage.giveDamage(1, connectedPlayers.get(connectedPlayers.size() - 1), target);

        if (connectedPlayers.get(connectedPlayers.size() - 1).getPlayerboard().getDamage().size() >= 6) {

            Damage.giveMarker(1, Board.getTerminator(), target);

        }

        updateAll("Il Terminator ha sparato a" + target.getNickname());

    }


}