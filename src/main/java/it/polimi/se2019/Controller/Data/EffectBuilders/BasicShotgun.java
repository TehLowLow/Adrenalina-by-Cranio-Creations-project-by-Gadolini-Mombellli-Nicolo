package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

public class BasicShotgun extends Effect{



    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets){

        Player target = targets.get(0);

        Damage.giveDamage(3, user, target);

        boolean chosen = false;

        while(!chosen){

            String answer = Server.updateWithAnswer(user, Message.vuoiSpostare());

            if(!InputCheck.correctYesNo(answer)){
                Server.update(user, Message.inputError());
                continue;
            }

            chosen = true;

            if(InputCheck.yesOrNo(answer)){
                //Riciclo da GrenadeLauncher il metodo per spostare il bersaglio.
                GrenadeLauncher.moveTarget(user, target);
            }

        }

    }


    public CopyOnWriteArrayList<Player> getTargets(Player user){

        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<Player>();
        CopyOnWriteArrayList <Player> chosenTarget = new CopyOnWriteArrayList<Player>();

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.getPosition().equals(user.getPosition())){
                targets.add(target);
            }
        }

        boolean chosen = false;

        while(!chosen){

            String chosenNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(targets));

            if(!InputCheck.nicknameCheck(chosenNickname)){
                Server.update(user, Message.inputError());
                continue;
            }

            for(Player possibleTarget : targets){
                if(possibleTarget.getNickname().equalsIgnoreCase(chosenNickname)){
                    chosenTarget.add(possibleTarget);
                    chosen = true;
                    continue;
                }
            }

            Server.update(user, Message.bersaglioNonValido());
        }

        return chosenTarget;
    }

    public boolean hasTargets(Player user){

        for(Player target : Server.connectedPlayers){
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }

            if(target.getPosition().equals(user.getPosition())){
                return true;
            }
        }

        return false;
    }

}
