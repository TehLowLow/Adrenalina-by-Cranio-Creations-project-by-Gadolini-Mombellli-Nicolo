package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;

import java.util.ArrayList;

public class RocketLauncher extends Effect {

    ArrayList<Player> warheadTargets;


    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        //Verifico se l'utente ha il cubo blu per la mossa.

        boolean hasBlue = false;

        if (user.getPlayerboard().getAmmoCubes().getBlue() > 0) {

            hasBlue = true;

        }

        //Se no, attivo l'effetto base.

        if (!hasBlue) {

            applyBaseEffect(user, targets);
            return;
        }

        //Se ha il cubo, gli chiedo se vuole usare la mossa o no.

        boolean choseUse = false;
        boolean use = false;

        while (!choseUse) {

            String answer = Server.updateWithAnswer(user, Message.usareRazzi());
            if (!InputCheck.correctYesNo(answer)) {
                continue;
            }

            choseUse = true;
            use = InputCheck.yesOrNo(answer);

        }


        if (use) {

            //Se si, gli chiedo se vuole usarla ora.


            boolean moveBefore = false;
            boolean chosenMove = false;

            while (!chosenMove) {

                String wantsMove = Server.updateWithAnswer(user, Message.usaRazziPortatiliPrima());

                if (!InputCheck.correctYesNo(wantsMove)) {
                    continue;
                }

                chosenMove = true;
                moveBefore = InputCheck.yesOrNo(wantsMove);

            }


            //Se sceglie di usarla:

            if (moveBefore) {

                applyRocketJump(user);
                warheadTargets = getVisibleOtherSquares(user);
                applyBaseEffect(user, getTargets(user));
                return;

            }

            //Se ha scelto di usarla dopo:

            applyBaseEffect(user, targets);
            applyRocketJump(user);
            return;

        }

       //Se non sceglie di usarla, applico solo l'effetto base

        applyBaseEffect(user, targets);
        return;

    }

    private void applyRocketJump(Player user){

        ArrayList <Cell> reachableCells = Check.reachableCells(user, 2);

        boolean chosen = false;
        int indexCell = 0;

        while(!chosen){

            String answer = Server.updateWithAnswer(user, Message.scegliCella(reachableCells));
            try{

                indexCell = InputCheck.numberCheck(answer);

                if(indexCell<0 || indexCell>reachableCells.size()-1){
                    Server.update(user, Message.cellaNonDisponibile());
                    continue;
                }

                chosen = true;


            } catch(NumberFormatException e){
                Server.update(user, Message.inputError());
                continue;
            }

        }

        user.setPosition(reachableCells.get(indexCell));

    }

    private void applyBaseEffect(Player user, ArrayList<Player> targets) {

        //Applico effetto


        Player target = targets.get(0);
        Token d1 = new Token();
        Token d2 = new Token();
        d1.setChampionName(user.getPlayerboard().getChampionName());
        d2.setChampionName(user.getPlayerboard().getChampionName());
        ArrayList<Token> damages = target.getPlayerboard().getDamage();
        damages.add(d1);
        damages.add(d2);
        target.getPlayerboard().setDamage(damages);

        //Chiedo all'utente se vuole muovere il bersaglio.

        boolean answered = false;
        boolean move = false;
        String chosenMove = new String();

        while (!answered) {

            chosenMove = Server.updateWithAnswer(user, Message.vuoiSpostare());

            if (InputCheck.correctYesNo(chosenMove)) {
                continue;
            }

            answered = true;
            move = InputCheck.yesOrNo(chosenMove);

        }


        //Se sì, lo muovo.

        if (move) {

            moveTarget(user, target);

        }

        //Verifico se ha il cubo giallo

        boolean yellowAvailable = false;

        if (user.getPlayerboard().getAmmoCubes().getYellow() > 0) {
            yellowAvailable = true;
        }

        //Nel caso, chiedo se vuole utilizzare la warhead

        boolean chosenWarhead = false;
        boolean useWarhead = false;

        if (yellowAvailable) {

            while (!chosenWarhead) {

                String answer = Server.updateWithAnswer(user, Message.usaTestata());

                if (!InputCheck.correctYesNo(answer)) {
                    continue;
                }

                chosenWarhead = true;
                useWarhead = InputCheck.yesOrNo(answer);

            }

        }

        //Se vuole utilizzarla, applico il suo effetto.

        if (useWarhead) {

            for (Player warheadTarget : warheadTargets) {

                Token d = new Token();
                d.setChampionName(user.getPlayerboard().getChampionName());
                ArrayList<Token> damagesWarhead = warheadTarget.getPlayerboard().getDamage();
                damagesWarhead.add(d);
                warheadTarget.getPlayerboard().setDamage(damagesWarhead);

            }

        }

    }

    private void moveTarget(Player user, Player target) {

        //Riutilizzo lo stesso metodo del lanciagranate.
        GrenadeLauncher.moveTarget(user, target);

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList<Player> chosenTarget = new ArrayList<>();

        warheadTargets = getVisibleOtherSquares(user);

        boolean chosen = false;
        String targetNickname = null;

        while (!chosen) {

            targetNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(warheadTargets));

            if (!InputCheck.nicknameCheck(targetNickname) || !InputCheck.availablePlayer(targetNickname, warheadTargets)) {
                Server.update(user, Message.inputError());
                continue;
            }

            chosen = true;

        }

        for (Player target : warheadTargets) {
            if (target.getNickname().equalsIgnoreCase(targetNickname)) {
                chosenTarget.add(target);
            }
        }

        return chosenTarget;

    }

    private ArrayList<Player> getVisibleOtherSquares(Player user) {

        ArrayList<Player> visiblePlayers = Check.visiblePlayers(user);

        for (Player target : Server.connectedPlayers) {

            if (target.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            if (target.getPosition().equals(user.getPosition())) {
                visiblePlayers.remove(target);
            }
        }

        return visiblePlayers;
    }

    //TODO: Vedere test. Questo metodo non funziona perché il player può muoversi se ha il cubo blu, e nel caso può mirare ad altri bersagli.
    @Override
    public boolean hasTargets(Player user) {

        ArrayList<Player> visiblePlayers = getVisibleOtherSquares(user);


        if (visiblePlayers.isEmpty()) {

            if (user.getPlayerboard().getAmmoCubes().getBlue() > 0){

                Player fakePlayer = new Player();

                ArrayList<Cell> reachableCells = Check.reachableCells(user, 2);

                for (Cell cell: reachableCells){

                    fakePlayer.setPosition(cell);

                    if (!getVisibleOtherSquares(fakePlayer).isEmpty()){

                        return true;

                    }

                }

                return false;

            }


        }
        return true;
    }


}
