package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Controller.Adrenalina.Check;
import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Model.Cell;
import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Token;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.View.Message;
import  it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.*;
import java.util.ArrayList;

public class GrenadeLauncher extends Effect {




    @Override
    public void applyEffect(Player user, ArrayList<Player> targets) {

        //Chiedo all'utente se vuole usare l'effetto base o subito la granata extra.

        boolean chosen = false;
        boolean base = true;
        String answer = null;

        while(!chosen){

            answer = Server.updateWithAnswer(user, Message.lanciaGranateScegliEffetto());

            if(!InputCheck.baseEffectGrenadeLauncher(answer)){
                Server.update(user, Message.inputError());
                continue;
            }

            if(answer.equalsIgnoreCase("base")){
                chosen = true;
                continue;
            }

            if(answer.equalsIgnoreCase("extra")){

                if(user.getPlayerboard().getAmmoCubes().getRed()==0){
                    Server.update(user, Message.mancaRYB());
                    continue;
                }
                chosen = true;
                base = false;
                continue;
            }

        }

        //Se sceglie l'effetto base:

        if(base == true) {


            //--Chiamo metodo per effetto base.

            applyBaseEffect(user, targets);

            //--Devo poi chiedergli se vuole usare la granata extra, ma solo se essa ha bersagli disponibili
            // (potrebbero non esserci nel caso in cui il player ha spostato fuori dal campo visivo l'unico
            // giocatore visibile).

            if(!hasTargetsExtraGrenade(user)){
                return;
            }

            //Verifico se il player ha abbastanza soldi per utilizzare la granata extra.

            if(user.getPlayerboard().getAmmoCubes().getRed()==0){
                return;
            }

            //Ora gli chiedo se vuole usare la granata extra.

            boolean chosenGrenade = false;
            boolean useGrenade = false;
            answer = null;
            
            while(!chosenGrenade){
                
                answer = Server.updateWithAnswer(user, Message.usareEffettoOpzionale());
                
                if(!InputCheck.correctYesNo(answer)){
                    Server.update(user, Message.inputError());
                    continue;
                }
                
                if(InputCheck.yesOrNo(answer)){
                    useGrenade = true;
                    chosenGrenade = true;
                    continue;
                }
                
                chosenGrenade = true;
                
                
            }

            //Se accetta, applico l'effetto.
            
            if(useGrenade){
                
                ArrayList <Player> targetsExtraGrenade = getTargetsExtraGrenade(user);
                applyExtraGrenade(user, targetsExtraGrenade);
                
                //Scalo il costo
                
                int red = user.getPlayerboard().getAmmoCubes().getRed();
                user.getPlayerboard().getAmmoCubes().setRedCubes(red-1);
                
            }

            

        }

        //Se sceglie granata extra:

        if(base == false) {



            ArrayList<Player> targetsExtra = getTargetsExtraGrenade(user);
            applyExtraGrenade(user, targetsExtra);

            //--Devo poi chiedergli se vuole usare l'effetto base.

            if(hasTargets(user)){

                 String answer2 = null;
                 boolean chosen2 = false;
                 boolean useEffect = false;

                 while(!chosen2){

                     answer2 = Server.updateWithAnswer(user, Message.usareEffettoBase());

                     if(!InputCheck.correctYesNo(answer2)){
                         Server.update(user, Message.inputError());
                         continue;
                     }

                     useEffect = InputCheck.yesOrNo(answer2);
                     chosen2 = true;

                 }

                 if(useEffect){
                     ArrayList <Player> baseTargets = getTargets(user);
                     applyBaseEffect(user, baseTargets);
                 }

            }

            return;

        }
        

    }

    private ArrayList<Player> getTargetsExtraGrenade(Player user) {

        ArrayList <Cell> visibleSquares = Check.visibleSquares(user);
        ArrayList <Cell> squaresWithPlayers = new ArrayList<>();
        ArrayList <Player> targets = new ArrayList<>();

        for(Player target : Server.connectedPlayers) {

            if (target.getNickname().equalsIgnoreCase(user.getNickname())) {
                continue;
            }

            for (Cell square : visibleSquares) {

                if (target.getPosition().equals(square)) {

                    if (!squaresWithPlayers.contains(square)) {
                        squaresWithPlayers.add(square);
                    }
                }
            }
        }

            boolean chosen = false;
            Cell chosenCell = null;
            int cellIndex = 0;

            while(!chosen){

                String cellIndexString = Server.updateWithAnswer(user, Message.scegliCella(squaresWithPlayers));
                 try{
                     cellIndex = Integer.parseInt(cellIndexString);

                 }   catch (NumberFormatException e){

                     Server.update(user, Message.inputError());
                     continue;
                 }

                 if(cellIndex>squaresWithPlayers.size()-1 || cellIndex < 0 ){

                     Server.update(user, Message.cellaNonDisponibile());
                     continue;
                 }

                 chosenCell = squaresWithPlayers.get(cellIndex);
                 chosen = true;
            }

            for(Player target : Server.connectedPlayers){

                if(user.getNickname().equalsIgnoreCase(target.getNickname())){
                    continue;
                }

                if(target.getPosition().equals(chosenCell)){
                            targets.add(target);
                }

            }

            return targets;



    }

    private boolean hasTargetsExtraGrenade(Player user) {

        ArrayList targets = new ArrayList();

        ArrayList <Cell> visibleCells = Check.visibleSquares(user);
        
        for(Player target : Server.connectedPlayers){
            
            if(target.getNickname().equalsIgnoreCase(user.getNickname())){
                continue;
            }
            
            for(Cell visibleCell : visibleCells){
                
                if(target.getPosition().equals(visibleCell)){
                    return true;
                }
                
            }
        }
        
        return false;

    }

    private void applyExtraGrenade(Player user, ArrayList <Player> targets) {


        //Do un danno a tutti i giocatori lì dentro.

        giveDamage(user, targets);

    }

    static void giveDamage(Player user, ArrayList<Player> targets) {
        for(Player target : targets){

            Damage.giveDamage(1, user, target);

        }
    }

    private void applyBaseEffect(Player user, ArrayList<Player> targets) {

        //assegno un danno al bersaglio in targets.

        Player target = targets.get(0);
      Damage.giveDamage(1, user, target);

        //Chiamo metodo per spostare il bersaglio.

        moveTarget(user, target);

        return;
        
    }

    public static void moveTarget(Player user, Player target) {

        boolean chosen = false;

        ArrayList <Cell> reachableCells = Check.reachableCells(target, 1);

        while(!chosen){

            String direction = Server.updateWithAnswer(user, Message.spostaBersaglio(target, reachableCells));

            if(!InputCheck.directionCheck(direction)){
                Server.update(user, Message.inputError());
                continue;
            }

            Cell chosenCell = getCell(target, direction);

            if(chosenCell.equals(null)){
                Server.update(user, Message.cellaNonDisponibile());
                continue;
            }

            for(Cell reachableCell : reachableCells){

                if(chosenCell.equals(reachableCell)){
                    chosen = true;
                    target.setPosition(chosenCell);
                    continue;
                }

            }

            Server.update(user, Message.direzioneOstruita());



        }

    }

    public static Cell getCell(Player target, String direction){

        if(direction.equalsIgnoreCase("alto")){
            return target.getPosition().getUpConnection().getConnectedCell();
        }

        if(direction.equalsIgnoreCase("basso")){
            return target.getPosition().getDownConnection().getConnectedCell();
        }

        if(direction.equalsIgnoreCase("sinistra")){
            return target.getPosition().getLeftConnection().getConnectedCell();
        }

        if(direction.equalsIgnoreCase("destra")){
            return target.getPosition().getDownConnection().getConnectedCell();
        }

        return null;

    }

    @Override
    public ArrayList<Player> getTargets(Player user) {

        ArrayList <Player> possibleTargets = Check.visiblePlayers(user);
        ArrayList <Player> chosenTarget = new ArrayList<>();

        boolean found = false;

        while(!found){

            String chosenNickname = Server.updateWithAnswer(user, Message.scegliBersaglio(possibleTargets));

            if(!InputCheck.nicknameCheck(chosenNickname)){

                Server.update(user, Message.inputError());
                continue;

            }

            for(Player possibleTarget : possibleTargets){
                if(possibleTarget.getNickname().equalsIgnoreCase(chosenNickname)){
                    chosenTarget.add(possibleTarget);
                    found = true;
                    continue;
                }
            }

            Server.update(user, Message.bersaglioNonValido());

        }

        return chosenTarget;
    }

    @Override
    public boolean hasTargets(Player user) {

        ArrayList visiblePlayers = Check.visiblePlayers(user);

        if(visiblePlayers.size()>0){return true;}
        return false;
    }



}
