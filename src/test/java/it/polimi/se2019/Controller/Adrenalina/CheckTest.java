package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Controller.Data.EffectBuilders.LockRifleEffect;
import it.polimi.se2019.Controller.Data.EffectBuilders.MachineGunEffect;
import it.polimi.se2019.Controller.Data.EffectBuilders.WhisperEffect;
import it.polimi.se2019.Model.*;
import it.polimi.se2019.Network.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class CheckTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }


    /*
    DEATH TESTS
     */


    @Test
    public void deathOverkilled() {

        Player damaged = Server.connectedPlayers.get(0);
        for (int i = 0; i < 12; i++) {

            damaged.getPlayerboard().getDamage().add(new Token());

        }
        int result = Check.death(damaged);
        assertEquals(2, result);

    }


    @Test
    public void deathKilled() {

        Player damaged = Server.connectedPlayers.get(0);
        for (int i = 0; i < 11; i++) {

            damaged.getPlayerboard().getDamage().add(new Token());

        }
        int result = Check.death(damaged);
        assertEquals(1, result);


    }

    @Test
    public void deathNotKilled() {

        Player damaged = Server.connectedPlayers.get(0);
        for (int i = 0; i < 10; i++) {

            damaged.getPlayerboard().getDamage().add(new Token());

        }
        int result = Check.death(damaged);
        assertEquals(0, result);

    }

    /*
    RESOLVE BOARD TESTS
     */


    @Test
    public void resolveBoardWithoutTie() {

        Player killed = Server.connectedPlayers.get(4);

        for (int i = 0; i < 11; i++) {

            Token damage = new Token();

            killed.getPlayerboard().getDamage().add(damage);

            if (i < 6) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion0");

            }

            if (i >= 6 && i < 9) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion1");

            }

            if (i >= 9) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion2");

            }

        }

        Check.resolveBoard(killed, false);

        assertEquals(9, Server.connectedPlayers.get(0).getScore());
        assertEquals(6, Server.connectedPlayers.get(1).getScore());
        assertEquals(4, Server.connectedPlayers.get(2).getScore());
        assertEquals(0, Server.connectedPlayers.get(4).getPlayerboard().getDamage().size());
        assertEquals((Integer) 6, Server.connectedPlayers.get(4).getPlayerboard().getPlayerboardValue().get(0));
        assertEquals(8, Board.getMortalBlowTrack().size());
        assertEquals(Server.connectedPlayers.get(2), Board.getMortalBlowTrack().get(0).getKiller());
        assertEquals(false, Board.getMortalBlowTrack().get(0).isSkull());
        assertEquals(false, Board.getMortalBlowTrack().get(0).isOverkill());


    }


    @Test

    public void resolveBoardWithTie() {


        Player killed = Server.connectedPlayers.get(4);

        for (int i = 0; i < 12; i++) {

            Token damage = new Token();

            killed.getPlayerboard().getDamage().add(damage);

            if (i < 5) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion0");

            }

            if (i >= 5 && i < 10) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion1");

            }

            if (i >= 10) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion2");

            }

        }

        Check.resolveBoard(killed, true);

        assertEquals(9, Server.connectedPlayers.get(0).getScore());
        assertEquals(6, Server.connectedPlayers.get(1).getScore());
        assertEquals(4, Server.connectedPlayers.get(2).getScore());
        assertEquals(0, Server.connectedPlayers.get(4).getPlayerboard().getDamage().size());
        assertEquals((Integer) 6, Server.connectedPlayers.get(4).getPlayerboard().getPlayerboardValue().get(0));
        assertEquals(8, Board.getMortalBlowTrack().size());
        assertEquals(Server.connectedPlayers.get(2), Board.getMortalBlowTrack().get(0).getKiller());
        assertEquals(false, Board.getMortalBlowTrack().get(0).isSkull());
        assertEquals(true, Board.getMortalBlowTrack().get(0).isOverkill());


    }


    @Test
    public void markers() {

        Player attacker = Server.connectedPlayers.get(0);
        Player defender = Server.connectedPlayers.get(1);

        defender.getPlayerboard().getDamage().add(new Token());
        defender.getPlayerboard().getDamage().get(0).setChampionName(attacker.getPlayerboard().getChampionName());


        for (int i = 0; i < 2; i++) {

            defender.getPlayerboard().getMarker().add(new Token());
            defender.getPlayerboard().getMarker().get(i).setChampionName(attacker.getPlayerboard().getChampionName());


        }

        Check.markers(attacker, defender);

        assertEquals(3, defender.getPlayerboard().getDamage().size());
        assertEquals(0, defender.getPlayerboard().getMarker().size());

        for (Token damage : defender.getPlayerboard().getDamage()) {

            assertEquals(attacker.getPlayerboard().getChampionName(), damage.getChampionName());

        }


    }

    //TODO da fare affordable

    @Test
    public void affordable() {




    }


    @Test
    public void limitAmmoCubes() {

        Player player = Server.connectedPlayers.get(0);

        player.getPlayerboard().getAmmoCubes().setYellowCubes(4);
        player.getPlayerboard().getAmmoCubes().setBlueCubes(15);
        player.getPlayerboard().getAmmoCubes().setRedCubes(2);

        Check.limitAmmoCubes(player);

        assertEquals(3, player.getPlayerboard().getAmmoCubes().getYellow());
        assertEquals(3, player.getPlayerboard().getAmmoCubes().getBlue());
        assertEquals(2, player.getPlayerboard().getAmmoCubes().getRed());


    }


    @Test
    public void limitPowerUp() {

        Player player = Server.connectedPlayers.get(0);

        for (int i = 0; i < 3; i++) {

            Powerup powerup = new Powerup();

            player.getPlayerboard().getPowerups().add(powerup);

        }

        Check.limitPowerUp(player);

        assertEquals(3, player.getPlayerboard().getPowerups().size());

    }


    @Test
    public void limitMarkers() {

        Player defender = Server.connectedPlayers.get(0);
        Player attacker = Server.connectedPlayers.get(1);


        for (int i = 0; i < 5; i++) {

            Token marker = new Token();
            marker.setChampionName(attacker.getPlayerboard().getChampionName());
            defender.getPlayerboard().getMarker().add(marker);

        }


        Check.limitMarkers(defender, attacker);

        assertEquals(3, defender.getPlayerboard().getMarker().size());


    }


    @Test
    public void visiblePlayersAllVisible() {

        Player player = Server.connectedPlayers.get(0);

        CopyOnWriteArrayList<Player> visible = Check.visiblePlayers(player);

        assertEquals(true, visible.contains(Server.connectedPlayers.get(1)));
        assertEquals(true, visible.contains(Server.connectedPlayers.get(2)));
        assertEquals(true, visible.contains(Server.connectedPlayers.get(3)));
        assertEquals(true, visible.contains(Server.connectedPlayers.get(4)));
        assertEquals(4, visible.size());


    }

    @Test
    public void visiblePlayersSomeVisible() {

        Player player = Server.connectedPlayers.get(0);

        for (int i = 2; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));


        }

        CopyOnWriteArrayList<Player> visible = Check.visiblePlayers(player);

        assertEquals(true, visible.contains(Server.connectedPlayers.get(1)));
        assertEquals(false, visible.contains(Server.connectedPlayers.get(2)));
        assertEquals(false, visible.contains(Server.connectedPlayers.get(3)));
        assertEquals(false, visible.contains(Server.connectedPlayers.get(4)));
        assertEquals(1, visible.size());

    }

    @Test
    public void visiblePlayersNoOne() {


        Player player = Server.connectedPlayers.get(0);

        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));


        }

        CopyOnWriteArrayList<Player> visible = Check.visiblePlayers(player);

        assertEquals(false, visible.contains(Server.connectedPlayers.get(1)));
        assertEquals(false, visible.contains(Server.connectedPlayers.get(2)));
        assertEquals(false, visible.contains(Server.connectedPlayers.get(3)));
        assertEquals(false, visible.contains(Server.connectedPlayers.get(4)));
        assertEquals(0, visible.size());

    }

    @Test

    public void reachableCellsOneStep() {

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        CopyOnWriteArrayList<Cell> reachable = Check.reachableCells(player, 1);

        assertEquals(true, reachable.contains(Board.getMap().getRedRoom().getCells().get(0)));
        assertEquals(true, reachable.contains(Board.getMap().getBlueRoom().getCells().get(2)));
        assertEquals(2, reachable.size());
    }


    @Test
    public void reachableCellsMoreSteps() {

        Player player = Server.connectedPlayers.get(0);

        CopyOnWriteArrayList<Cell> reachable = Check.reachableCells(player, 3);

        assertEquals(false, reachable.contains(Board.getMap().getBlueRoom().getCells().get(0)));
        assertEquals(false, reachable.contains(Board.getMap().getWhiteRoom().getCells().get(1)));
        assertEquals(8, reachable.size());


    }


    @Test

    public void checkFrenzyTrue() {

        CopyOnWriteArrayList<MortalBlow> mortalBlows = Board.getMortalBlowTrack();

        for (MortalBlow mortalBlow : mortalBlows) {

            mortalBlow.setSkull(false);

        }

        boolean result = Check.checkFrenzy();

        assertEquals(true, result);


    }


    @Test
    public void checkFrenzyFalse() {

        boolean result = Check.checkFrenzy();

        assertEquals(false, result);

    }


    @Test
    public void checkFrenzyboardNoTie() {


        Player killed = Server.connectedPlayers.get(4);

        Interaction.turnPlayerboard(killed);

        for (int i = 0; i < 11; i++) {

            Token damage = new Token();

            killed.getPlayerboard().getDamage().add(damage);

            if (i < 6) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion0");

            }

            if (i >= 6 && i < 9) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion1");

            }

            if (i >= 9) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion2");

            }

        }

        Check.resolveFrenzyboard(killed, true);

        assertEquals(2, Server.connectedPlayers.get(0).getScore());
        assertEquals(1, Server.connectedPlayers.get(1).getScore());
        assertEquals(1, Server.connectedPlayers.get(2).getScore());
        assertEquals(0, Server.connectedPlayers.get(4).getPlayerboard().getDamage().size());
        assertEquals((Integer) 2, Server.connectedPlayers.get(4).getPlayerboard().getPlayerboardValue().get(0));
        assertEquals(9, Board.getMortalBlowTrack().size());
        assertEquals(Server.connectedPlayers.get(2), Board.getMortalBlowTrack().get(8).getKiller());
        assertEquals(false, Board.getMortalBlowTrack().get(8).isSkull());
        assertEquals(true, Board.getMortalBlowTrack().get(8).isOverkill());

    }


    @Test
    public void resolveFrenzyboardWithTie() {


        Player killed = Server.connectedPlayers.get(4);

        Interaction.turnPlayerboard(killed);


        for (int i = 0; i < 12; i++) {

            Token damage = new Token();

            killed.getPlayerboard().getDamage().add(damage);

            if (i < 5) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion0");

            }

            if (i >= 5 && i < 10) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion1");

            }

            if (i >= 10) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion2");

            }

        }

        Check.resolveFrenzyboard(killed, true);

        assertEquals(2, Server.connectedPlayers.get(0).getScore());
        assertEquals(1, Server.connectedPlayers.get(1).getScore());
        assertEquals(1, Server.connectedPlayers.get(2).getScore());
        assertEquals(0, Server.connectedPlayers.get(4).getPlayerboard().getDamage().size());
        assertEquals((Integer) 2, Server.connectedPlayers.get(4).getPlayerboard().getPlayerboardValue().get(0));
        assertEquals(9, Board.getMortalBlowTrack().size());
        assertEquals(Server.connectedPlayers.get(2), Board.getMortalBlowTrack().get(8).getKiller());
        assertEquals(false, Board.getMortalBlowTrack().get(8).isSkull());
        assertEquals(true, Board.getMortalBlowTrack().get(8).isOverkill());

    }


    @Test
    public void winnerOne() {

        Interaction.turnPlayerboard(Server.connectedPlayers.get(0));
        Interaction.turnPlayerboard(Server.connectedPlayers.get(2));
        Interaction.turnPlayerboard(Server.connectedPlayers.get(3));
        Interaction.turnPlayerboard(Server.connectedPlayers.get(4));

        /*
        aggiungo danni al player 1
         */

        for (int i = 0; i < 5; i++) {

            Token damage = new Token();

            if (i < 3) {

                damage.setChampionName(Server.connectedPlayers.get(4).getPlayerboard().getChampionName());


            }

            if (i >= 3 && i<4) {

                damage.setChampionName(Server.connectedPlayers.get(0).getPlayerboard().getChampionName());

            }

            if (i >= 4) {

                damage.setChampionName(Server.connectedPlayers.get(2).getPlayerboard().getChampionName());

            }

            Server.connectedPlayers.get(1).getPlayerboard().getDamage().add(damage);

        }


        /*
        aggiungo danni al player 2
        */

        for (int i = 0; i < 4; i++) {

            Token damage = new Token();


            damage.setChampionName(Server.connectedPlayers.get(3).getPlayerboard().getChampionName());

            Server.connectedPlayers.get(2).getPlayerboard().getDamage().add(damage);

        }


        /*
        aggiungo danni al player 3
         */


        for (int i=0; i<5; i++){

            Token damage = new Token();

            if (i<3){

                damage.setChampionName(Server.connectedPlayers.get(2).getPlayerboard().getChampionName());


            }

            if (i>=3 && i<4){

                damage.setChampionName(Server.connectedPlayers.get(4).getPlayerboard().getChampionName());


            }

            if (i>=4){

                damage.setChampionName(Server.connectedPlayers.get(1).getPlayerboard().getChampionName());


            }

            Server.connectedPlayers.get(3).getPlayerboard().getDamage().add(damage);


        }


        /*
        danni player 4
         */

        for (int i=0; i<6; i++){

            Token damage = new Token();

            if (i<4){

                damage.setChampionName(Server.connectedPlayers.get(1).getPlayerboard().getChampionName());


            }

            if (i>=4){

                damage.setChampionName(Server.connectedPlayers.get(2).getPlayerboard().getChampionName());


            }

            Server.connectedPlayers.get(4).getPlayerboard().getDamage().add(damage);


        }

        /*
        setto i punti iniziali
         */

        Server.connectedPlayers.get(0).setScore(7);
        Server.connectedPlayers.get(1).setScore(5);
        Server.connectedPlayers.get(2).setScore(3);
        Server.connectedPlayers.get(3).setScore(12);
        Server.connectedPlayers.get(4).setScore(7);


        /*
        setto il mortal blow track
         */

        CopyOnWriteArrayList<MortalBlow> newMBTrack = new CopyOnWriteArrayList<>();

        for (int i=0; i<11; i++){

            MortalBlow mortalBlow = new MortalBlow();
            mortalBlow.setSkull(false);
            mortalBlow.setOverkill(false);


            if (i<5){

                mortalBlow.setKiller(Server.connectedPlayers.get(1));

            }

            if (i>=5 && i<8){

                mortalBlow.setKiller(Server.connectedPlayers.get(2));
                if (i==5 || i==7){

                    mortalBlow.setOverkill(true);

                }

            }

            if (i>=8 && i<9){

                mortalBlow.setKiller(Server.connectedPlayers.get(3));

            }

            if (i>=9 && i<10){

                mortalBlow.setKiller(Server.connectedPlayers.get(0));
                mortalBlow.setOverkill(true);

            }

            if (i>=10){

                mortalBlow.setKiller(Server.connectedPlayers.get(4));

            }

        newMBTrack.add(mortalBlow);

        }

        Board.setMortalBlowTrack(newMBTrack);

        Check.winner();

        assertEquals(17, Server.connectedPlayers.get(0).getScore());
        assertEquals(16, Server.connectedPlayers.get(1).getScore());
        assertEquals(16, Server.connectedPlayers.get(2).getScore());
        assertEquals(16, Server.connectedPlayers.get(3).getScore());
        assertEquals(18, Server.connectedPlayers.get(4).getScore());



    }


    @Test
    public void winnerTieWithOneWinner(){

        Server.connectedPlayers.get(0).setScore(5);
        Server.connectedPlayers.get(1).setScore(5);
        Server.connectedPlayers.get(2).setScore(5);
        Server.connectedPlayers.get(3).setScore(5);
        Server.connectedPlayers.get(4).setScore(5);

        CopyOnWriteArrayList<MortalBlow> newMBTrack = new CopyOnWriteArrayList<>();

        for (int i=0; i<11; i++){

            MortalBlow mortalBlow = new MortalBlow();
            mortalBlow.setSkull(false);
            mortalBlow.setOverkill(false);


            if (i<5){

                mortalBlow.setKiller(Server.connectedPlayers.get(1));

            }

            if (i>=5 && i<8){

                mortalBlow.setKiller(Server.connectedPlayers.get(2));
                if (i==5 || i==7){

                    mortalBlow.setOverkill(true);

                }

            }

            if (i>=8 && i<9){

                mortalBlow.setKiller(Server.connectedPlayers.get(3));

            }

            if (i>=9 && i<10){

                mortalBlow.setKiller(Server.connectedPlayers.get(0));
                mortalBlow.setOverkill(true);

            }

            if (i>=10){

                mortalBlow.setKiller(Server.connectedPlayers.get(4));

            }

            newMBTrack.add(mortalBlow);

        }

        Board.setMortalBlowTrack(newMBTrack);


        Check.winner();

        assertEquals(13, Server.connectedPlayers.get(1).getScore());
        assertEquals(11, Server.connectedPlayers.get(2).getScore());
        assertEquals(9, Server.connectedPlayers.get(0).getScore());
        assertEquals(7, Server.connectedPlayers.get(3).getScore());
        assertEquals(6, Server.connectedPlayers.get(4).getScore());


    }


    @Test
    public void winnerWithTieMoreWinners(){

        Server.connectedPlayers.get(0).setScore(5);
        Server.connectedPlayers.get(1).setScore(5);
        Server.connectedPlayers.get(2).setScore(5);
        Server.connectedPlayers.get(3).setScore(5);
        Server.connectedPlayers.get(4).setScore(5);

        Check.winner();

        assertEquals(5, Server.connectedPlayers.get(1).getScore());
        assertEquals(5, Server.connectedPlayers.get(2).getScore());
        assertEquals(5, Server.connectedPlayers.get(0).getScore());
        assertEquals(5, Server.connectedPlayers.get(3).getScore());
        assertEquals(5, Server.connectedPlayers.get(4).getScore());

    }

    @Test
    public void visibleCells(){

        Player user = Server.connectedPlayers.get(0);

        CopyOnWriteArrayList<Cell> visible = Check.visibleSquares(user);

        assertEquals(true, visible.containsAll(Board.getMap().getBlueRoom().getCells()));
        assertEquals(true, visible.containsAll(Board.getMap().getRedRoom().getCells()));
        assertEquals(6, visible.size());


    }

    @Test
    public void canShot(){

        Player player0 = Server.connectedPlayers.get(0);
        Player player1 = Server.connectedPlayers.get(1);
        Player player2 = Server.connectedPlayers.get(2);

        Weapon lockrifle= new Weapon();
        lockrifle.setBaseEffect(new LockRifleEffect());
        lockrifle.setLoaded(true);

        player0.getPlayerboard().getWeapons().add(lockrifle);

        Weapon whisper = new Weapon();
        whisper.setBaseEffect(new WhisperEffect());
        whisper.setLoaded(true);

        player1.getPlayerboard().getWeapons().add(whisper);

        Weapon machineGun = new Weapon();
        machineGun.setBaseEffect(new MachineGunEffect());
        machineGun.setLoaded(false);

        player2.getPlayerboard().getWeapons().add(machineGun);


        boolean result0 = Check.canShot(player0);
        boolean result1 = Check.canShot(player1);
        boolean result2 = Check.canShot(player2);


        assertEquals(true, result0);
        assertEquals(false, result1);
        assertEquals(false, result2);



    }


    @Test
    public void canShotEnhanced(){

        Player player1 = Server.connectedPlayers.get(1);
        player1.setPosition(Board.getMap().getYellowRoom().getCells().get(1));

        Weapon lockrifle = new Weapon();
        lockrifle.setBaseEffect(new LockRifleEffect());
        lockrifle.setLoaded(true);

        player1.getPlayerboard().getWeapons().add(lockrifle);

        boolean result1 = Check.canShotEnhanced(player1);

        assertEquals(true, result1);
    }

    @Test
    public void canShotEnhancedFrenzy(){


        Player player1 = Server.connectedPlayers.get(1);
        player1.setPosition(Board.getMap().getYellowRoom().getCells().get(0));

        Weapon lockrifle = new Weapon();
        lockrifle.setBaseEffect(new LockRifleEffect());
        lockrifle.setLoaded(true);

        player1.getPlayerboard().getWeapons().add(lockrifle);

        boolean result1 = Check.canShotEnhancedFrenzy(player1);

        assertEquals(true, result1);

    }

    @Test
    public void moveManager(){

        Player player = Server.connectedPlayers.get(0);

        CopyOnWriteArrayList<Cell> reachable = Check.moveManager(player, 3);

        assertEquals(true, reachable.contains(Board.getMap().getRedRoom().getCells().get(0)));
        assertEquals(true, reachable.contains(Board.getMap().getWhiteRoom().getCells().get(0)));
        assertEquals(true, reachable.contains(Board.getMap().getYellowRoom().getCells().get(0)));
        assertEquals(3, reachable.size());

    }
}