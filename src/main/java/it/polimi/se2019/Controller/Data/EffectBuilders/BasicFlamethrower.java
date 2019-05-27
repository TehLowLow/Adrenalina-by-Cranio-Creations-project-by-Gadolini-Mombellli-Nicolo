package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;

import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.*;


public class BasicFlamethrower extends Effect {


    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        GrenadeLauncher.giveDamage(user, targets);

    }


    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Player> chosenTargets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList <Player> possibleTargets = new CopyOnWriteArrayList<>();
        String chosenDirection =  "";

        Connection upConnection = user.getPosition().getUpConnection();
        Connection downConnection = user.getPosition().getDownConnection();
        Connection leftConnection = user.getPosition().getLeftConnection();
        Connection rightConnection = user.getPosition().getRightConnection();

        CopyOnWriteArrayList <Cell> chosenCells = new CopyOnWriteArrayList<>();

        boolean chosen = false;

        while (!chosen){

            chosenDirection = Server.updateWithAnswer(user, Message.scegliDirezioneSparo());

            if(!InputCheck.directionCheck(chosenDirection)){
                Server.update(user, Message.inputError());
            }

            if(chosenDirection.equals("alto")){

                if(directionFree(user.getPosition(), "alto")){
                    
                    if(directionFree(upConnection.getConnectedCell(), "alto")){
                        if(hasPlayers(upConnection.getConnectedCell().getUpConnection().getConnectedCell())){
                            chosen = true;
                            chosenCells.add(upConnection.getConnectedCell().getUpConnection().getConnectedCell());
                        }
                    }
                    
                    if(hasPlayers(upConnection.getConnectedCell())){
                     
                        chosen = true;
                        chosenCells.add(upConnection.getConnectedCell());
                    }
                    
                }

            }

            if(chosenDirection.equals("basso")){

                if(directionFree(user.getPosition(), "basso")){

                    if(directionFree(downConnection.getConnectedCell(), "basso")){
                        if(hasPlayers(downConnection.getConnectedCell().getDownConnection().getConnectedCell())){
                            chosen = true;
                            chosenCells.add(downConnection.getConnectedCell().getDownConnection().getConnectedCell());
                        }
                    }

                    if(hasPlayers(downConnection.getConnectedCell())){

                        chosen = true;
                        chosenCells.add(downConnection.getConnectedCell());
                    }

                }

            }

            if(chosenDirection.equals("sinistra")){


                if(directionFree(user.getPosition(), "sinistra")){

                    if(directionFree(leftConnection.getConnectedCell(), "sinistra")){
                        if(hasPlayers(leftConnection.getConnectedCell().getLeftConnection().getConnectedCell())){
                            chosen = true;
                            chosenCells.add(leftConnection.getConnectedCell().getLeftConnection().getConnectedCell());
                        }
                    }

                    if(hasPlayers(leftConnection.getConnectedCell())){

                        chosen = true;
                        chosenCells.add(leftConnection.getConnectedCell());
                    }

                }



            }

            if(chosenDirection.equals("destra")){

                if(directionFree(user.getPosition(), "destra")){

                    if(directionFree(rightConnection.getConnectedCell(), "destra")){
                        if(hasPlayers(rightConnection.getConnectedCell().getRightConnection().getConnectedCell())){
                            chosen = true;
                            chosenCells.add(rightConnection.getConnectedCell().getRightConnection().getConnectedCell());
                        }
                    }

                    if(hasPlayers(rightConnection.getConnectedCell())){

                        chosen = true;
                        chosenCells.add(rightConnection.getConnectedCell());
                        continue;
                    }

                }

            }


            if(!chosen) {
                Server.update(user, Message.direzioneSenzaBersagli());
            }


        }

        //Dentro chosenCells ho la/le celle da colpire. Una cella è presente solo se ha i bersagli. Ricavo i bersagli.

        for(Cell cell : chosenCells){

            for(Player player : Server.connectedPlayers){

                if(player.getPosition().equals(cell)){

                    possibleTargets.add(player);

                }

            }

        }

        //Ora scelgo un bersaglio.

        Player firstChosen = ChoosePlayer.one(user, possibleTargets);
        possibleTargets.remove(firstChosen);

        for(Player player : possibleTargets){
            if(player.getPosition().equals(firstChosen.getPosition())){
                possibleTargets.remove(player);
            }
        }

        chosenTargets.add(firstChosen);

        Player secondChosen = null;

        //Se ci sono due celle con giocatori, ne scelgo un altro.

        chosen = false;

        if(chosenCells.size()==2 && !possibleTargets.isEmpty()) {

            while (!chosen) {

                String ans = Server.updateWithAnswer(user, Message.scegliAltroBersaglio());

                if (!InputCheck.correctYesNo(ans)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                if (InputCheck.yesOrNo(ans)) {

                    secondChosen = ChoosePlayer.one(user, possibleTargets);
                    chosenTargets.add(secondChosen);

                }

                chosen = true;

            }

        }

        return chosenTargets;


    }


    public boolean hasPlayers(Cell cell){

        for(Player player : Server.connectedPlayers){
            if(player.getPosition().equals(cell)){
                return true;
            }
        }

        return false;
    }

    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList<Player> players = Server.connectedPlayers;
        CopyOnWriteArrayList<Cell> reachableCardinalCells = getCardinalCells(user);

        for (Player player : players) {

            if (player.equals(user)) {
                continue;
            }

            if (reachableCardinalCells.contains(player.getPosition())) {
                return true;
            }

        }

        return false;


    }


    protected static boolean directionFree(Cell cell, String direction) {

        if (direction.equalsIgnoreCase("alto")) {
            if (cell.getUpConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getUpConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        if (direction.equalsIgnoreCase("basso")) {
            if (cell.getDownConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getDownConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        if (direction.equalsIgnoreCase("sinistra")) {
            if (cell.getLeftConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getLeftConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        if (direction.equalsIgnoreCase("destra")) {
            if (cell.getRightConnection().getType().equalsIgnoreCase(Connection.DOOR) || cell.getRightConnection().getType().equalsIgnoreCase(Connection.FREE)) {
                return true;
            }
        }

        return false;

    }

    protected static Cell returnConnectedCell(Cell cell, String direction) {

        if (direction.equalsIgnoreCase("alto")) {
            return cell.getUpConnection().getConnectedCell();
        }

        if (direction.equalsIgnoreCase("basso")) {
            return cell.getDownConnection().getConnectedCell();
        }

        if (direction.equalsIgnoreCase("sinistra")) {
            return cell.getLeftConnection().getConnectedCell();
        }

        if (direction.equalsIgnoreCase("destra")) {
            return cell.getRightConnection().getConnectedCell();
        }

        return cell;
    }

    protected static CopyOnWriteArrayList<Player> getPlayersInCell(Cell cell) {

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        for (Player target : Server.connectedPlayers) {

            if (target.getPosition().equals(cell)) {
                targets.add(target);
            }

        }

        return targets;
    }

    protected static CopyOnWriteArrayList <Cell> getCardinalCells(Player user){


        CopyOnWriteArrayList<Cell> reachableCardinalCells = new CopyOnWriteArrayList<>();
        Cell position = user.getPosition();
        
            
        Cell upCell = null;
        Cell downCell = null;
        Cell leftCell = null;
        Cell rightCell = null;


        if(position.getUpConnection().getType().equals(Connection.FREE) || position.getUpConnection().getType().equals(Connection.DOOR)){
                    upCell = position.getUpConnection().getConnectedCell();
                    reachableCardinalCells.add(upCell);
                    
                    if(upCell.getUpConnection().getType().equals(Connection.FREE) || position.getUpConnection().getType().equals(Connection.DOOR)){
                        reachableCardinalCells.add(upCell.getUpConnection().getConnectedCell());
                    }
                
            }

        if(position.getDownConnection().getType().equals(Connection.FREE) || position.getDownConnection().getType().equals(Connection.DOOR)){
            downCell = position.getDownConnection().getConnectedCell();
            reachableCardinalCells.add(downCell);

            if(downCell.getDownConnection().getType().equals(Connection.FREE) || position.getDownConnection().getType().equals(Connection.DOOR)){
                reachableCardinalCells.add(downCell.getDownConnection().getConnectedCell());
            }

        }

        if(position.getLeftConnection().getType().equals(Connection.FREE) || position.getLeftConnection().getType().equals(Connection.DOOR)){
            leftCell = position.getLeftConnection().getConnectedCell();
            reachableCardinalCells.add(leftCell);

            if(leftCell.getLeftConnection().getType().equals(Connection.FREE) || position.getLeftConnection().getType().equals(Connection.DOOR)){
                reachableCardinalCells.add(leftCell.getLeftConnection().getConnectedCell());
            }

        }

        if(position.getRightConnection().getType().equals(Connection.FREE) || position.getRightConnection().getType().equals(Connection.DOOR)){
            rightCell = position.getRightConnection().getConnectedCell();
            reachableCardinalCells.add(rightCell);

            if(rightCell.getRightConnection().getType().equals(Connection.FREE) || position.getRightConnection().getType().equals(Connection.DOOR)){
                reachableCardinalCells.add(rightCell.getRightConnection().getConnectedCell());
            }

        }
            

        return reachableCardinalCells;

    }

}
