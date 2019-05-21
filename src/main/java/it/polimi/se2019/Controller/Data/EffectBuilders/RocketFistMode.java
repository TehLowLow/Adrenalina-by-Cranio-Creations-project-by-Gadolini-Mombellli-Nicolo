package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Controller.Data.WeaponBuilders.RedWeapons.FlamethrowerBuilder;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

public class RocketFistMode extends Effect {

    String direction;

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        if(targets.size()==0){
            return;
        }

       for(Player target : targets){
           Damage.giveDamage(2, user, target);
       }

    }


    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {


        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();


        //Devo scegliere una direzione, farlo muovere in quella direzione ed eventualmente fargli scegliere i bersagli da colpire.

        CopyOnWriteArrayList<Cell> cardinalCells = BasicFlamethrower.getCardinalCells(user);

        boolean chosen = false;
        String direction = new String();

        while (!chosen) {

            direction = Server.updateWithAnswer(user, Message.scegliCellaFurnace(cardinalCells, user));

            if (!InputCheck.directionCheck(direction)) {
                Server.update(user, Message.inputError());
                continue;
            }

            chosen = true;

        }

        Cell next = null;

        if (direction.equals("alto")) {

            for (Cell cell : cardinalCells) {

                if (cell.equals(user.getPosition().getUpConnection().getConnectedCell())) {

                    user.setPosition(cell);
                    Server.update(user, Message.movedTo());


                    if (cell.getUpConnection().getType().equals(Connection.FREE) || cell.getUpConnection().getType().equals(Connection.DOOR)) {

                        if (cardinalCells.contains(cell.getUpConnection().getConnectedCell())) {
                            next = cell.getUpConnection().getConnectedCell();

                        }

                    }

                }
            }

        }

        if (direction.equals("basso")) {

            for (Cell cell : cardinalCells) {
                if (cell.equals(user.getPosition().getDownConnection().getConnectedCell())) {
                    user.setPosition(cell);
                    Server.update(user, Message.movedTo());


                    if (cell.getDownConnection().getType().equals(Connection.FREE) || cell.getDownConnection().getType().equals(Connection.DOOR)) {

                        if (cardinalCells.contains(cell.getDownConnection().getConnectedCell())) {
                            next = cell.getDownConnection().getConnectedCell();

                        }

                    }

                }
            }

        }

        if (direction.equals("sinistra")) {

            for (Cell cell : cardinalCells) {
                if (cell.equals(user.getPosition().getLeftConnection().getConnectedCell())) {

                    user.setPosition(cell);
                    Server.update(user, Message.movedTo());

                    if (cell.getLeftConnection().getType().equals(Connection.FREE) || cell.getLeftConnection().getType().equals(Connection.DOOR)) {

                        if (cardinalCells.contains(cell.getLeftConnection().getConnectedCell())) {
                            next = cell.getLeftConnection().getConnectedCell();

                        }

                    }

                }
            }

        }

        if (direction.equals("destra")) {

            for (Cell cell : cardinalCells) {
                if (cell.equals(user.getPosition().getUpConnection().getConnectedCell())) {
                    user.setPosition(cell);
                    Server.update(user, Message.movedTo());

                    if (cell.getRightConnection().getType().equals(Connection.FREE) || cell.getRightConnection().getType().equals(Connection.DOOR)) {

                        if (cardinalCells.contains(cell.getRightConnection().getConnectedCell())) {
                            next = cell.getRightConnection().getConnectedCell();

                        }

                    }

                }
            }

        }

        //Qui codice per scegliere se colpire qualcuno.

        Player target = selectTarget(user);
        if(target!=null){
            targets.add(target);
        }

        chosen = false;
        String answer = new String();

        //Se next non è null, posso spostarmi ancora se voglio.
        if (next != null) {

            while (!chosen) {

                answer = Server.updateWithAnswer(user, Message.vuoiSpostartiAncora());

                if (!InputCheck.correctYesNo(answer)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;

            }


            if (InputCheck.yesOrNo(answer)) {

                user.setPosition(next);
                Server.update(user, Message.movedTo());

                target = selectTarget(user);
                if(target!=null){
                    targets.add(target);

                }

            }

        }

        return targets;

    }


    private static Player selectTarget(Player user){

        CopyOnWriteArrayList<Player> possibleTargets = new CopyOnWriteArrayList<>();

        boolean chosen = false;
        String answer = new String();


        for (Player player : Server.connectedPlayers) {
            if (player.equals(user)) {
                continue;
            }

            if (player.getPosition().equals(user.getPosition())) {
                possibleTargets.add(player);
            }
        }

        if(possibleTargets.size()==0){
            return null;
        }


        while (!chosen) {

            answer = Server.updateWithAnswer(user, Message.vuoiColpire());

            if (!InputCheck.correctYesNo(answer)) {
                Server.update(user, Message.inputError());
                continue;
            }

            chosen = true;

        }


        if (InputCheck.yesOrNo(answer)) {

            return ChoosePlayer.one(user, possibleTargets);

        }

        return null;

    }

    @Override
    public boolean hasTargets(Player user) {

        //Al massimo si muove e basta e non colpisce nessuno.
       return true;

    }

}
