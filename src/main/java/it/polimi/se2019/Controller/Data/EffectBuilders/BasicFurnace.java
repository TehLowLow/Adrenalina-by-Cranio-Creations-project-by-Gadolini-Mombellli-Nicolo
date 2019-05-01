package it.polimi.se2019.Controller.Data.EffectBuilders;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;



/**
 * Contains the @Override of the methods of Effect.java, and builds the Furnace.
 */
public class BasicFurnace extends Effect{


        /**
         * Applies the effect of the Furnace to the target (or targets).
         * @param user the Player that wants to apply the effect.
         * @param targets the target of the effect. It can be the user itself.
         */

        @Override
        public void applyEffect(Player user, ArrayList<Player> targets) {

            for(Player target : targets){

                ArrayList <Token> damages = target.getPlayerboard().getDamage();
                Token newDamage = new Token();
                newDamage.setChampionName(user.getPlayerboard().getChampionName());
                damages.add(newDamage);
                target.getPlayerboard().setDamage(damages);

            }

        }


        /**
         * Looks for the target (or targets) of the Furnace.
         * @param user the Player thant wants to use the effect.
         * @return
         */

        @Override
        public ArrayList<Player> getTargets(Player user) {

            //Dichiaro un array di giocatori, che conterrà i bersagli.
            ArrayList <Player> targets = new ArrayList<>();
            //Dichiaro un array che contiene le stanze visibili dal giocatore.
            ArrayList<Integer> visibleRooms = getVisibleRooms(user);

            //Chiamo il metodo per far scegliere all'utente una delle stanze visibili.
            int chosenRoom = chooseRoom(user, visibleRooms);

            //Scorro tutti i giocatori e cerco quelli nella stanza scelta.
            for(Player target : Server.connectedPlayers){

                int currentRoom = target.getPosition().getColour();
                if(currentRoom == chosenRoom){
                    targets.add(target);
                }

            }

            //Se non c'è nessuno, informo il giocatore e gli faccio ripetere la scelta.
            if(targets.size()==0){
                Server.update(user, Message.nessunBersaglio());
                return getTargets(user);
            }

            //Altrimenti, ritorno i bersagli.

            return targets;
        }

        //Questo metodo permette al giocatore di scegliere una stanza tra quelle in
        //visibleRooms, e ne ritorna il colore sotto forma di intero.
        private int chooseRoom(Player user, ArrayList <Integer> visibleRooms){

            String chosenRoomString = Server.updateWithAnswer(user, Message.scegliStanza(visibleRooms));

            while(InputCheck.colourCheck(chosenRoomString)==-1){

                Server.update(user, Message.inputError());
                return chooseRoom(user, visibleRooms);

            }

            int chosenRoom = InputCheck.colourCheck(chosenRoomString);

            while(!visibleRooms.contains(chosenRoom)){
                Server.update(user, Message.stanzaNonVisibile());
                return chooseRoom(user, visibleRooms);
            }

            return chosenRoom;


        }

        /**
         * This method returns all the rooms a player can see, but not the room he is in.
         * @param user The rooms are the ones seen by this player.
         * @return an arrayList that contains the colour of the visible rooms.
         */
        private ArrayList<Integer> getVisibleRooms(Player user){

                Cell position = user.getPosition();
                int userRoomColour = position.getColour();

                ArrayList <Integer> visibleRooms = new ArrayList<>();

                if(position.getUpConnection().getType().equals(Connection.DOOR)){
                        visibleRooms.add(position.getUpConnection().getConnectedCell().getColour());
                }

                if(position.getDownConnection().getType().equals(Connection.DOOR)){
                        visibleRooms.add(position.getDownConnection().getConnectedCell().getColour());
                }

                if(position.getLeftConnection().getType().equals(Connection.DOOR)){
                        visibleRooms.add(position.getLeftConnection().getConnectedCell().getColour());
                }

                if(position.getRightConnection().getType().equals(Connection.DOOR)){
                        visibleRooms.add(position.getRightConnection().getConnectedCell().getColour());
                }

                return visibleRooms;
        }


        /**
         * This method returns true if the weapon can be used.
         * @param player who uses the weapon.
         * @return true if the weapon can be used.
         */
        public boolean hasTargets(Player player){

                //Array che contiene le stanze visibili dal giocatore.
                ArrayList <Integer> visibleRooms = getVisibleRooms(player);

                //Se non ne vede nessuna, ritorno falso.
                if(visibleRooms.size()==0){
                        return false;
                }

                //Se ne vede almeno una...
                else{
                        //Cerco tra tutti i giocatori se qualcuno è presente nelle stanze visibili.
                        for(Player target : Server.connectedPlayers){

                                int currentRoom = target.getPosition().getColour();

                                //Se c'è qualcuno, ritorno vero.
                                if(visibleRooms.contains(currentRoom)){
                                        return true;
                                }

                        }


                }

                //Se nessuno è presente, ritorno falso.
                return false;

        }

}
