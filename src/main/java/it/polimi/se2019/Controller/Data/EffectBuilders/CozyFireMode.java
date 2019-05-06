package it.polimi.se2019.Controller.Data.EffectBuilders;
import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Alternative effect for the Furnace
 */

public class CozyFireMode extends Effect {



        /**
         * Applies the alternative effect of the Furnace.
         * @param user the Player that wants to apply the effect.
         * @param targets the target of the effect. It can be the user itself.
         */

        @Override
        public void applyEffect(Player user,  ArrayList <Player> targets) {

                //Ad ogni bersaglio, assegno un danno e un marker.
                for(Player target : targets) {

                        Token damage = new Token();
                        damage.setChampionName(user.getPlayerboard().getChampionName());

                        Token marker = new Token();
                        marker.setChampionName(user.getPlayerboard().getChampionName());

                        ArrayList <Token> damages = target.getPlayerboard().getDamage();
                        ArrayList <Token> markers = target.getPlayerboard().getMarker();

                        damages.add(damage);
                        markers.add(marker);

                        target.getPlayerboard().setDamage(damages);
                        target.getPlayerboard().setMarker(markers);
                        //Controllo che non abbia più di 3 marker da parte dell'user. Il metodo rimuove quelli in eccesso.
                        Check.limitMarkers(target, user);

                }


        }

        /**
         * Looks for the target (or targets) of the alternative effect.
         * @param user the Player thant wants to use the effect.
         * @return
         */

        @Override
        public ArrayList<Player> getTargets(Player user) {


                ArrayList <Cell> cells = targettableSquares(user);
                ArrayList <Player> targets = new ArrayList<>();

                //Faccio scegliere una cella tra quelle disponibili.
                Cell chosenCell = chooseCell(cells, user);

                //Aggiungo i giocatori nella cella scelta all'array di bersagli.
                for(Player target : Server.connectedPlayers){
                        if(target.getPosition().equals(chosenCell)){
                                targets.add(target);
                        }
                }

                return targets;
        }


        /**
         * Allows the user to choose a target cell.
         * @param cells the available cells.
         * @param user the user
         * @return the chosen cell
         */
        private Cell chooseCell(ArrayList <Cell> cells, Player user){


              //Lista di direzioni disponibili.
              ArrayList <String> availableDirections = new ArrayList<>();

              //Ciclo sulle celle a distanza unitaria dal giocatore, controllo dove si trovano rispetto ad esso e
                //ne aggiungo la direzione nell'array di direzioni disponibili solo se contengono dei giocatori.
              for(Cell cell : cells){

                      if(user.getPosition().getUpConnection().getConnectedCell().equals(cell)){

                              for(Player target : Server.connectedPlayers){
                                      if(target.getPosition().equals(cell)){
                                              availableDirections.add("alto");
                                              break;
                                      }
                              }

                      }

                      if(user.getPosition().getDownConnection().getConnectedCell().equals(cell)){
                              for(Player target : Server.connectedPlayers){
                                      if(target.getPosition().equals(cell)){
                                              availableDirections.add("basso");
                                              break;
                                      }
                              }
                      }

                      if(user.getPosition().getLeftConnection().getConnectedCell().equals(cell)){
                              for(Player target : Server.connectedPlayers){
                                      if(target.getPosition().equals(cell)){
                                              availableDirections.add("sinistra");
                                              break;
                                      }
                              }
                      }

                      if(user.getPosition().getRightConnection().getConnectedCell().equals(cell)){
                              for(Player target : Server.connectedPlayers){
                                      if(target.getPosition().equals(cell)){
                                              availableDirections.add("destra");
                                              break;
                                      }
                              }
                      }
              }

                //Salvo qui dentro la stringa che identifica la cella scelta dal giocatore.
                String chosenCellString = Server.updateWithAnswer(user, Message.scegliCellaFurnace(cells, user));


              //Se il giocatore ha sbagliato a scrivere una direzione, la chiedo nuovamente.
              while(!InputCheck.directionCheck(chosenCellString)){

                      Server.update(user, Message.inputError());
                      chosenCellString = Server.updateWithAnswer(user, Message.scegliCellaFurnace(cells, user));
              }

              //Converto la direzione in lowercase.
              chosenCellString.toLowerCase();

              //Se la direzione scelta non corrisponde ad una delle disponibili, richiamo tutto il metodo.
              while(!availableDirections.contains(chosenCellString)){

                      Server.update(user, Message.cellaNonDisponibile());
                      return chooseCell(cells, user);

              }

              //Alla fine, cerco la cella corrispondente alla direzione scelta e la ritorno.
              for(Cell cell : cells){

                      if(chosenCellString.equalsIgnoreCase("alto")){
                              if(user.getPosition().getUpConnection().getConnectedCell().equals(cell)){
                                      return cell;
                              }
                      }

                      if(chosenCellString.equalsIgnoreCase("basso")){
                              if(user.getPosition().getDownConnection().getConnectedCell().equals(cell)){
                                      return cell;
                              }
                      }

                      if(chosenCellString.equalsIgnoreCase("sinistra")){
                              if(user.getPosition().getLeftConnection().getConnectedCell().equals(cell)){
                                      return cell;
                              }
                      }

                      if(chosenCellString.equalsIgnoreCase("destra")){
                              if(user.getPosition().getRightConnection().getConnectedCell().equals(cell)){
                                      return cell;
                              }
                      }

              }

              return null;

        }


        /**
         * Checks if the weapon has some available targets.
         * @param user the user of the weapon
         * @return true if there are available targets.
         */
        public boolean hasTargets(Player user){

                //Prendo le celle a distanza 1 dal giocatore.
                ArrayList <Cell> cells = targettableSquares(user);

                //Ciclo sui giocatori: se almeno uno di essi si trova in quelle celle, ritorno true.
                for(Player target : Server.connectedPlayers){

                        for(Cell cell : cells){

                                if(target.getPosition().equals(cell)){
                                        return true;
                                }

                        }

                }

                return false;

        }

        /**
         * Checks which cells are at a distance of 1 from the player.
         * @param user the player that wants to use the weapon
         * @return An arrayList containing the available cells.
         */
        private ArrayList <Cell> targettableSquares (Player user){

                return Check.reachableCells(user, 1);

        }



}
