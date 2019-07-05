package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Alternative effect for the Flamethrower.
 */
public class BBQMode extends Effect {


    /**
     * Applies the alternative effect of the Flamethrower.
     *
     * @param user    the Player that wants to apply the effect.
     * @param targets the target of the effect. It can be the user itself.
     */

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        CopyOnWriteArrayList<Cell> doubleDamageCells = Check.reachableCells(user, 1);

        for (Player target : targets) {

            Damage.giveDamage(1, user, target);


            if (doubleDamageCells.contains(target.getPosition())) {

                Damage.giveDamage(1, user, target);
            }


        }


    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return the array of the effect targets.
     */

    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();
        boolean foundFirst = false;

        Cell firstTargetCell = new LootCell();
        String direction = new String() ;

        while (!foundFirst) {

            direction = Server.updateWithAnswer(user, Message.scegliDirezioneSparo());

            if (!InputCheck.directionCheck(direction)) {
                Server.update(user, Message.inputError());
                continue;
            }

            if (!BasicFlamethrower.directionFree(user.getPosition(), direction)) {
                Server.update(user, Message.direzioneOstruita());
                continue;
            }

            foundFirst = true;
            firstTargetCell = BasicFlamethrower.returnConnectedCell(user.getPosition(), direction);

            possibleTargets = BasicFlamethrower.getPlayersInCell(firstTargetCell);


        }

        boolean foundSecond = false;

        while (!foundSecond) {

            if (!BasicFlamethrower.directionFree(firstTargetCell, direction)) {
                break;
            }

            Cell secondTargetCell = BasicFlamethrower.returnConnectedCell(firstTargetCell, direction);

            possibleTargets.addAll(BasicFlamethrower.getPlayersInCell(secondTargetCell));

            foundSecond = true;


        }


        if (possibleTargets.size() == 0) {
            Server.update(user, Message.nessunBersaglio());
            return getTargets(user);
        }

        return possibleTargets;

    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the BBQMode has someone to hit.
     */

    public boolean hasTargets(Player user) {

        Check check = new Check();
        CopyOnWriteArrayList<Cell> targetCells = check.reachableCells(user, 1);
        CopyOnWriteArrayList<Player> players = Server.connectedPlayers;
        CopyOnWriteArrayList<Player> two_step_targets = new CopyOnWriteArrayList<>();

        for(Cell cell : targetCells){

            if(user.getPosition().getUpConnection().getType().equals(Connection.FREE) || user.getPosition().getUpConnection().getType().equals(Connection.DOOR)){
                if(cell.equals(user.getPosition().getUpConnection().getConnectedCell())){
                    if(cell.getUpConnection().getType().equals(Connection.FREE) || cell.getUpConnection().getType().equals(Connection.DOOR)){
                        targetCells.add(cell.getUpConnection().getConnectedCell());
                    }
                }
            }

            if(user.getPosition().getDownConnection().getType().equals(Connection.FREE) || user.getPosition().getDownConnection().getType().equals(Connection.DOOR)){
                if(cell.equals(user.getPosition().getDownConnection().getConnectedCell())){
                    if(cell.getDownConnection().getType().equals(Connection.FREE) || cell.getDownConnection().getType().equals(Connection.DOOR)){
                        targetCells.add(cell.getDownConnection().getConnectedCell());
                    }
                }
            }

            if(user.getPosition().getLeftConnection().getType().equals(Connection.FREE) || user.getPosition().getLeftConnection().getType().equals(Connection.DOOR)){
                if(cell.equals(user.getPosition().getLeftConnection().getConnectedCell())){
                    if(cell.getLeftConnection().getType().equals(Connection.FREE) || cell.getLeftConnection().getType().equals(Connection.DOOR)){
                        targetCells.add(cell.getLeftConnection().getConnectedCell());
                    }
                }
            }

            if(user.getPosition().getRightConnection().getType().equals(Connection.FREE) || user.getPosition().getRightConnection().getType().equals(Connection.DOOR)){
                if(cell.equals(user.getPosition().getRightConnection().getConnectedCell())){
                    if(cell.getRightConnection().getType().equals(Connection.FREE) || cell.getRightConnection().getType().equals(Connection.DOOR)){
                        targetCells.add(cell.getRightConnection().getConnectedCell());
                    }
                }
            }


        }

        for (Player player : players) {

            if (!player.getNickname().equals(user.getNickname())) {
                if (targetCells.contains(player.getPosition())) {
                    two_step_targets.add(player);
                }
            }

        }

        if(two_step_targets.size()==0){
            return false;
        }

        return true;
    }


}

