package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;

import java.util.ArrayList;

import static it.polimi.se2019.Model.Connection.*;
import static it.polimi.se2019.Network.Server.connectedPlayers;

/**
 * This class contains all the methods used to check if an interaction during the game is legit.
 */
public class Check {


    /**
     * Checks if a player has sustained enough damage to be declared dead.
     * @param damaged is the player that receives damage.
     * @return an int value. 0 if the player hasn't been damaged enough to die, 1 if the player receives damage only to
     * die, 2 if the attacker overkills.
     */
    public int death(Player damaged){

        /*
        saving the number of the damages on the playerboard
         */

        int damage = damaged.getPlayerboard().getDamage().size();

        /*
        checking the damage
         */


        if (damage == 11){
            return 1;
        }

        if (damage > 11){
            return 2;
        }

        else{
            return 0;
        }

    }


    /**
     * When a player kills another player, this method resolves the board and assigns the players
     * their scores.
     * @param killed the defeated player whose board needs to be resolved.
     */
    public void resolveBoard(Player killed, Board board, Player killer, boolean overkill){

        ArrayList<Player> killers = new ArrayList<Player>();

        ArrayList<Integer> tokenCounter = new ArrayList<Integer>();

        ArrayList<Token>  damages = new ArrayList<Token>();
        damages = killed.getPlayerboard().getDamage();


        int playerCounter = 0;


        for (Player player1: connectedPlayers){

            if (player1.getNickname().equals(damages.get(0).getChampionName())){
                /*
                aggiungo un punto a chi ha fatto il primo sangue
                 */
                player1.setScore(player1.getScore() + 1);
            }
        }

        /*
        assigning the points to the killers
         */

        




        /*
        placing the kill and overkill tokens on the MortalBlow track
         */




        /*
        changing the value of the playerboard
         */

        killed.getPlayerboard().getPlayerboardValue().remove(0);


        /*
        clearing the damages
         */

        for (Token damage:damages){
            int counter = 0;
            damages.set(counter, null);

            counter++;
        }





    }

    /**
     * Checks if the player that is being attacked has markers to add to the total damage.
     * @param active is the attacker, used to look for attacker's markers.
     * @param passive is the player being attacked that needs te markers check to resolve total damage.
     */
    public void markers(Player active, Player passive){


        /*
        counter
        */

        int counter=0;

        for (Token marker: passive.getPlayerboard().getMarker()){


            if (marker.getChampionName().equals(active.getNickname())){

                /*
                aggiunge il marker ai danni
                 */

                passive.getPlayerboard().getDamage().add(counter, marker);

                /*
                lo rimuove dai marker
                 */

                passive.getPlayerboard().getMarker().remove(counter);

            }


        counter++;


        }

    }

    /**
     * Checks the amount of damages dealt to a player by another player.
     * @param attacker player that has dealt the damages.
     * @param defender player that has taken the damages.
     * @return the amount of damage received by the attacker.
     */
    private int damages(Player attacker, Player defender){

        int damagesAmount = 0;

        ArrayList<Token> damages = defender.getPlayerboard().getDamage();

        for (Token damage:damages){
            if (damage.getChampionName().equals(attacker.getNickname())){
                damagesAmount++;
            }
        }

        return damagesAmount;
    }

    /**
     * Checks if a buyer can afford a weapon found in the spawn room. If not, returns false.
     * @param buyer the buyer of the weapon.
     * @param weapon the weapon to be checked.
     * @return true if a player can afford a weapon.
     */
    public boolean affordableWeapon(Player buyer, Weapon weapon){

        if     (buyer.getPlayerboard().getAmmoCubes().getBlue() >= weapon.getPrice().getBlue() &&
                buyer.getPlayerboard().getAmmoCubes().getRed() >= weapon.getPrice().getRed() &&
                buyer.getPlayerboard().getAmmoCubes().getYellow() >= weapon.getPrice().getYellow()) {

            return true;

        }

        else {return false;}
    }

    /**
     * Checks if a player can afford to reload a weapon that is holding.
     * @param player the player that wants to reload.
     * @param weapon the weapon to reload.
     * @return true if the player can afford to reload.
     */
    public boolean affordableReload(Player player, Weapon weapon){

        if     (player.getPlayerboard().getAmmoCubes().getBlue() >= weapon.getRechargeCost().getBlue() &&
                player.getPlayerboard().getAmmoCubes().getRed() >= weapon.getRechargeCost().getRed() &&
                player.getPlayerboard().getAmmoCubes().getYellow() >= weapon.getRechargeCost().getYellow()) {

            return true;

        }

        else {return false;}

    }

    /**
     * Checks if a player has sustained enough damage to use enhanced pickup.
     * @param player is the player to be checked.
     * @return true if the player has sustained enough damage.
     */
    public boolean availableEnhancedPickUp(Player player){

        if (player.getPlayerboard().getDamage().size() >= 3){
            return true;
        }

        else {return false;}
    }

    /**
     * Checks if a player has sustained enough damage to use enhanced shoot.
     * @param player is the player to be checked.
     * @return true if the player has sustained enough damage.
     */
    public boolean availableEnhancedShoot(Player player){

        if (player.getPlayerboard().getDamage().size() >= 6){
            return true;
        }

        else {return false;}

    }

    /**
     * Runs to limit the total amount of ammo per color to 3 ammos. For example, if a player has more than 3 red
     * ammo cubes this method will return the amount of cubes to 3.
     * @param player is the player to be checked.
     */
    public void limitAmmoCubes(Player player){

        if (player.getPlayerboard().getAmmoCubes().getYellow() > 3){
            player.getPlayerboard().getAmmoCubes().setYellowCubes(3);
        }

        if (player.getPlayerboard().getAmmoCubes().getRed() > 3){
            player.getPlayerboard().getAmmoCubes().setRedCubes(3);
        }

        if (player.getPlayerboard().getAmmoCubes().getBlue() > 3){
            player.getPlayerboard().getAmmoCubes().setBlueCubes(3);
        }

    }

    /**
     * Checks if a player has reached the limit of weapons he can keep.
     * @param player is the player to be checked.
     * @return true if the player already has got 3 weapons
     */
    public boolean limitWeapon(Player player){

        if (player.getPlayerboard().getWeapons().size() >= 3){
            return true;
        }

        else {return false;}
    }

    /**
     * Checks if a player has reached the limit of powerUps he can keep
     * @param player is the player to be checked.
     */
    public boolean limitPowerUp(Player player){

        if (player.getPlayerboard().getPowerups().size() >= 3){
            return true;
        }

        else {return false;}
    }

    /**
     * A player can place up to 3 markers on each enemy playerboard, and the player can have up to 3 marker from each
     * other player. This method runs to check if the number of markers has reached limit and fix it.
     * @param player is the player to be checked.
     */
    public void limitMarkers(Player player){

        /*
        DA SISTEMARE, SERVIREBBE ARRAY DI GIOCATORI

        ArrayList<Token> markers = player.getPlayerboard().getMarker();

        for (Player foe: )

        int samePlayerTokens = 0;

        for (Token marker:markers){



        }

        */
    }

    /**
     * Returns an arraylist containing all the players in the field of view of a player.
     * @param player is the player that needs the fow check.
     * @return all the others players that are in the fov.
     */
    public ArrayList <Player> visiblePlayers (Player player){
        return new ArrayList<Player>();
    }

    /**
     * Returns all the cells that are a maximum of n steps away from player.
     * @param player is the player that wants the check to move.
     * @param steps is the number of steps to do.
     * @return an arraylist of reachable cells.
     */
    public ArrayList <Cell> reachableCells (Player player, int steps){

        ArrayList<Cell> reachableCells = new ArrayList<>();

        Cell position = player.getPosition();

        if (steps==1){

            /*
            controllo solo le celle raggiungibili dalla posizione del player
             */

            if (position.getUpConnection().getType().equals(DOOR) || position.getUpConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getRightConnection().getType().equals(DOOR) || position.getRightConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getLeftConnection().getType().equals(DOOR) || position.getLeftConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getDownConnection().getType().equals(DOOR) || position.getDownConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

        }


        if (steps > 1){

            /*
            prima controllo la mia posizione
             */

            if (position.getUpConnection().getType().equals(DOOR) || position.getUpConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getRightConnection().getType().equals(DOOR) || position.getRightConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getLeftConnection().getType().equals(DOOR) || position.getLeftConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            if (position.getDownConnection().getType().equals(DOOR) || position.getDownConnection().getType().equals(FREE)){

                reachableCells.add(position.getUpConnection().getConnectedCell());

            }

            /*
            poi controllo le celle raggiungibili da quelle arrivabili dopo il primo step
             */

            for (int i=0; i<steps-1; i++){

                ArrayList<Cell> copiesToCheck = reachableCells;

                for (Cell cell:copiesToCheck){

                    if (cell.getUpConnection().getType().equals(DOOR) || cell.getUpConnection().getType().equals(FREE)){

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if(!reachableCells.contains(cell.getUpConnection().getConnectedCell())){

                            reachableCells.add(cell.getUpConnection().getConnectedCell());

                        }

                    }

                    if (cell.getRightConnection().getType().equals(DOOR) || cell.getRightConnection().getType().equals(FREE)){

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if(!reachableCells.contains(cell.getRightConnection().getConnectedCell())){

                            reachableCells.add(cell.getRightConnection().getConnectedCell());

                        }

                    }

                    if (cell.getLeftConnection().getType().equals(DOOR) || cell.getLeftConnection().getType().equals(FREE)){

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if(!reachableCells.contains(cell.getLeftConnection().getConnectedCell())){

                            reachableCells.add(cell.getLeftConnection().getConnectedCell());

                        }

                    }

                    if (cell.getDownConnection().getType().equals(DOOR) || cell.getDownConnection().getType().equals(FREE)){

                    /*
                    se la cella raggiungibile non è già presente nell'array, essa viene aggiunta.
                     */

                        if(!reachableCells.contains(cell.getDownConnection().getConnectedCell())){

                            reachableCells.add(cell.getDownConnection().getConnectedCell());

                        }

                    }



                }

            }

        }

        return reachableCells;

    }

    /**
     * This method runs after the final frenzy, and checks (/resolve) all the last points remaining on the board.
     * Then it proceeds to sum up the points of each player and declare a winner
     * (or a chart with the points of all the players).
     * @return the winner.
     */
    public Player winner(){

        return new Player();
    }

    public boolean isSpawn(Cell cell){

        if(cell.getName().equals("spawnCell")){
            return true;
        }

        return false;

    }

    public boolean canBeUsedSTD(Player user, Weapon weapon){

        return weapon.getBaseEffect().hasTargets(user);

    }

    public boolean canBeUsedAlternative(Player user, Weapon weapon){

        if(weapon.getAlternativeEffect()!=null){

            return weapon.getAlternativeEffect().hasTargets(user);

        }

        else{

            for(Effect effect : weapon.getOptionalEffect()){
                if(!effect.hasTargets(user)){
                    return true;
                }
            }
        }

        return false;

    }



}
