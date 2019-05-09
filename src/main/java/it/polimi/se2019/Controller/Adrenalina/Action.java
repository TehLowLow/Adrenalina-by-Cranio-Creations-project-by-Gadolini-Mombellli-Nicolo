package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server.*;
import it.polimi.se2019.View.Message;
import it.polimi.se2019.View.Parser;

import java.util.ArrayList;

import static it.polimi.se2019.Network.Server.update;
import static it.polimi.se2019.Network.Server.updateWithAnswer;

/**
 * This class collects all the actions that a player can perform, both in their standard version and
 * enhanced version. Moreover, it provides the methods for the more powerful actions that can be performed only
 * in Final Frenzy Mode.
 */


public class Action {

    private Check check;
    private InputCheck inputCheck;
    private Interaction interaction;
    private int steps;
    private int cell;
    private ArrayList<Cell> reachable;

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
    public void perform(Player player) {

    }

    /**
     * This method allows a player to reload a weapon.
     *
     * @param player the player that wants to perform the reload action.
     */
    public void reload(Player player, Weapon weapon) {

        if (check.affordableReload(player, weapon)) {

            Interaction.pay(player, weapon.getRechargeCost());

        } else {
            update(player, Message.cubiInsuff());
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
    private void move(Player player) {


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
    private void shot(Player player) {

    }

    /**
     * This method implements the standard "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private void pickUp(Player player) {

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
    private void enhancedMove(Player player) {

        this.move(player);
    }

    /**
     * This method implements the enhanced "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private void enhancedShot(Player player) {

    }

    /**
     * This method implements the enhanced "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private void enhancedPickUp(Player player) {

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
    private void frenzyMove(Player player) {


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
    private void frenzyShot(Player player) {
    }

    /**
     * This method implements the Frenzy Mode "pick up" action.
     *
     * @param player the Player that performs the pick up action.
     */
    private void frenzyPickUp(Player player) {
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
    private void enhancedFrenzyPickUp(Player player) {

    }

    /**
     * This method implements the enhanced Frenzy Mode "shot" action.
     *
     * @param player the Player that performs the shot action.
     */
    private void enhancedFrenzyShot(Player player) {
    }


}
