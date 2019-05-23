package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
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

            if (i < 4) {

                killed.getPlayerboard().getDamage().get(i).setChampionName("champion0");

            }

            if (i >= 4 && i < 10) {

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
    public void visiblePlayersNoOne(){


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

    public void reachableCellsOneStep(){

        Player player = Server.connectedPlayers.get(0);
        player.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        CopyOnWriteArrayList<Cell> reachable = Check.reachableCells(player, 1);

        assertEquals(true, reachable.contains(Board.getMap().getRedRoom().getCells().get(0)));
        assertEquals(true, reachable.contains(Board.getMap().getBlueRoom().getCells().get(2)));
        assertEquals(2, reachable.size());
    }



    @Test
    public void reachableCellsMoreSteps(){

        Player player = Server.connectedPlayers.get(0);

        CopyOnWriteArrayList<Cell> reachable = Check.reachableCells(player,3);

        assertEquals(false, reachable.contains(Board.getMap().getBlueRoom().getCells().get(0)));
        assertEquals(false, reachable.contains(Board.getMap().getWhiteRoom().getCells().get(1)));
        assertEquals(8, reachable.size());


    }


    @Test

    public void checkFrenzyTrue(){

        CopyOnWriteArrayList<MortalBlow> mortalBlows = Board.getMortalBlowTrack();

        for (MortalBlow mortalBlow:mortalBlows){

            mortalBlow.setSkull(false);

        }

        boolean result = Check.checkFrenzy();

        assertEquals(true, result);


    }


    @Test
    public void checkFrenzyFalse(){

        boolean result = Check.checkFrenzy();

        assertEquals(false, result);

    }


    /*@Test
    public void checkFrenzyboardNoTie(){


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

    */
}