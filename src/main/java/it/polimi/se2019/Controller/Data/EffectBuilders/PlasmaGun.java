package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Adrenalina.Interaction;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.ChoosePlayer;
import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Rybamount;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * plasma gun effect
 */

public class PlasmaGun extends Effect {

    /**
     *
     * @param user the Player that wants to apply the effect.
     * @param targets the targets of the effect.
     */

    @Override
    public void applyEffect(Player user, CopyOnWriteArrayList<Player> targets) {

        boolean usedMove = false;
        boolean chosen = false;

        if(isPhaseAvailable(user)) {

            while (!chosen) {

                String answer = Server.updateWithAnswer(user, Message.usareSlittamento());

                if (!InputCheck.correctYesNo(answer)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                if (!InputCheck.yesOrNo(answer)) {
                    if (!someTargetsAvailable(user)) {
                        Server.update(user, Message.nessunBersaglioFase());
                        continue;
                    }
                    chosen = true;
                    continue;
                }


                phaseMove(user);
                chosen = true;
                usedMove = true;

            }

        }

        targets = getTargets(user);

        Damage.giveDamage(2, user, targets.get(0));

        chosen = false;

        Rybamount effectRyb = new Rybamount();
        effectRyb.setBlueCubes(1);
        effectRyb.setYellowCubes(0);
        effectRyb.setRedCubes(0);

        if(Check.affordable(user, effectRyb)) {

            while (!chosen) {


                String answer = Server.updateWithAnswer(user, Message.usareSovraccarico());

                if (!InputCheck.correctYesNo(answer)) {
                    Server.update(user, Message.inputError());
                    continue;
                }

                if (InputCheck.yesOrNo(answer)) {
                    Interaction.pay(user, effectRyb);
                    Damage.giveDamage(1, user, targets.get(0));
                }

                chosen = true;

            }

        }

        if(!usedMove){

            chosen = false;

            while(!chosen){

                String answer = Server.updateWithAnswer(user, Message.usareSlittamento());

                if(!InputCheck.correctYesNo(answer)){
                    Server.update(user, Message.inputError());
                    continue;
                }

                if(InputCheck.yesOrNo(answer)){
                    phaseMove(user);
                }

                chosen = true;

            }

        }


    }

    /**
     * Performs the phase move
     * @param user of the effect
     */

    private void phaseMove(Player user) {

        CopyOnWriteArrayList <Cell> reachableCells = Check.reachableCells(user, 2);

        boolean chosen = false;

        while(!chosen){

            int index = 0;

            String indexCell = Server.updateWithAnswer(user, Message.scegliCella(reachableCells));

            try{

                index = InputCheck.numberCheck(indexCell);

                if(index<0 || index>reachableCells.size()-1){
                    Server.update(user, Message.inputError());
                    continue;
                }

                chosen = true;
                Cell newPosition = reachableCells.get(index);
                user.setPosition(newPosition);


            }catch (NumberFormatException e){
                Server.update(user, Message.inputError());
                continue;
            }

        }

    }

    /**
     *
     * @param user the Player that wants to use the effect.
     * @return an array containing the targets of the plasma gun.
     */

    @Override
    public CopyOnWriteArrayList<Player> getTargets(Player user) {

        CopyOnWriteArrayList <Player> possibleTargets = Check.visiblePlayers(user);
        CopyOnWriteArrayList <Player> targets = new CopyOnWriteArrayList<>();

        targets.add(ChoosePlayer.one(user, possibleTargets));

        return targets;
    }

    /**
     *
     * @param user of the effect
     * @return true if there is someone visible
     */

    public boolean someTargetsAvailable(Player user){

        CopyOnWriteArrayList <Player> visible = Check.visiblePlayers(user);

        if(visible.isEmpty()){
            return false;
        }

        return true;

    }

    /**
     *
     * @param user the effect.
     * @return false if the user, using phase move, could reach a cell where he can't hit anybody.
     */

    //Questa funzione ritorna false se il player, usando phasemove, raggiungerebbe un punto in cui non può colpire nessuno.
    public boolean isPhaseAvailable(Player user){

        CopyOnWriteArrayList <Cell> reachableCells = Check.reachableCells(user, 2);
        Player fakePlayer = new Player();
        fakePlayer.setPosition(user.getPosition());

        for(Cell cell: reachableCells){

            fakePlayer.setPosition(cell);

            CopyOnWriteArrayList visiblePlayers = Check.visiblePlayers(fakePlayer);

            if(visiblePlayers.contains(user)){
                visiblePlayers.remove(user);
            }

            if(!visiblePlayers.isEmpty()){
                return true;
            }

        }

        return false;

    }


    /**
     *
     * @param user the player who has to use the effect.
     * @return true if the user can shot somebody.
     */
    @Override
    public boolean hasTargets(Player user) {

        CopyOnWriteArrayList <Player> visiblePlayers = new CopyOnWriteArrayList<>();
        visiblePlayers = Check.visiblePlayers(user);

        if(!visiblePlayers.isEmpty()){
            return true;
        }

        CopyOnWriteArrayList <Cell> reachableCells = Check.reachableCells(user, 2);
        Player fakePlayer = new Player();
        fakePlayer.setPosition(user.getPosition());

        for(Cell cell: reachableCells){

            fakePlayer.setPosition(cell);

            visiblePlayers = Check.visiblePlayers(fakePlayer);

            if(visiblePlayers.contains(user)){
                visiblePlayers.remove(user);
            }

            if(!visiblePlayers.isEmpty()){
                return true;
            }

        }

        return false;

    }


}
