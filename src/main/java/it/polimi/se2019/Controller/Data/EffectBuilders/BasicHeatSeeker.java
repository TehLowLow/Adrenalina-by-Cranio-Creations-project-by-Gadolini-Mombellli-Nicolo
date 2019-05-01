package it.polimi.se2019.Controller.Data.EffectBuilders;
import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Contains the @Override of the methods of Effect.java, and builds the Heatseeker.
 */
public class BasicHeatSeeker extends Effect {



        /**
         * Applies the effect of the Heatseeker to the target.
         * @param user the Player that wants to apply the effect.
         * @param targets the target of the effect. It can be the user itself.
         */

        @Override
        public void applyEffect(Player user,  ArrayList <Player> targets) {


                Token d1 = new Token();
                Token d2 = new Token();
                Token d3 = new Token();

                d1.setChampionName(user.getPlayerboard().getChampionName());
                d2.setChampionName(user.getPlayerboard().getChampionName());
                d3.setChampionName(user.getPlayerboard().getChampionName());

                for(Player target : targets){

                       ArrayList <Token> damages =  target.getPlayerboard().getDamage();
                       damages.add(d1);
                       damages.add(d2);
                       damages.add(d3);
                       target.getPlayerboard().setDamage(damages);

                }


        }

        /**
         * Looks for the target of the Heatseeker.
         * @param user the Player thant wants to use the effect.
         * @return
         */

        @Override
        public ArrayList<Player> getTargets(Player user) {

                CopyOnWriteArrayList<Player> players = Server.connectedPlayers;
                ArrayList <Player> possibleTargets = new ArrayList();
                ArrayList <Player> visiblePlayers = Check.visiblePlayers(user);
                ArrayList <Player> chosenTarget = new ArrayList<>();

                players.remove(user);

                for(Player target : players){

                        if(!visiblePlayers.contains(target)){
                                possibleTargets.add(target);
                        }

                }

                String targetNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));
                boolean found = false;

                while(!found) {

                        while (!InputCheck.nicknameCheck(targetNickname)) {

                                Server.update(user, Message.inputError());
                                targetNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

                        }

                        for(Player possibleTarget : possibleTargets){
                                if(possibleTarget.getNickname().equalsIgnoreCase(targetNickname)){
                                        chosenTarget.add(possibleTarget);
                                        found=true;
                                }
                        }

                        if(!found){
                                Server.update(user, Message.bersaglioNonValido());
                        }

                }

                return chosenTarget;


        }


        public boolean hasTargets(Player user){

                ArrayList <Player> visiblePlayers = Check.visiblePlayers(user);

                if(visiblePlayers.size() == Server.connectedPlayers.size()-1){
                        return false;
                }

                return true;

        }
}
