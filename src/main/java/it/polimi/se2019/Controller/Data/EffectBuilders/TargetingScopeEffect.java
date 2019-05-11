package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

public class TargetingScopeEffect extends Effect {


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        boolean chosenC = false;

        while(!chosenC) {

            String chosenColour = Server.updateWithAnswer(user, Message.scegliColoreAmmo());
            int colourChosen = InputCheck.colourCheck(chosenColour);

            if (colourChosen == Colour.BLUE){
                if(user.getPlayerboard().getAmmoCubes().getBlue()==0){
                    Server.update(user, Message.cubiInsuff());
                    continue;
                }

                chosenC = true;

                user.getPlayerboard().getAmmoCubes().setBlueCubes(user.getPlayerboard().getAmmoCubes().getBlue()-1);
            }

            if (colourChosen == Colour.RED){
                if(user.getPlayerboard().getAmmoCubes().getRed()==0){
                    Server.update(user, Message.cubiInsuff());
                    continue;
                }

                chosenC = true;

                user.getPlayerboard().getAmmoCubes().setRedCubes(user.getPlayerboard().getAmmoCubes().getRed()-1);
            }

            if (colourChosen == Colour.YELLOW){
                if(user.getPlayerboard().getAmmoCubes().getYellow()==0){
                    Server.update(user, Message.cubiInsuff());
                    continue;
                }

                chosenC = true;

                user.getPlayerboard().getAmmoCubes().setYellowCubes(user.getPlayerboard().getAmmoCubes().getYellow()-1);
            }


        }

        Player chosen = ChoosePlayer.one(user, targets);
        Damage.giveDamage(1, user, chosen);

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {
        return null;
    }

    @Override
    public boolean hasTargets(Player user) {
        return false;
    }



}