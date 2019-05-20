package it.polimi.se2019.Controller.Data.EffectBuilders;

import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Map;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Weapon;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class CozyFireModeTest {


    @Before
    public void preparePlayers() {

        ConfigurationTest.createTestConfiguration();

    }


    @Test
    public void applyEffect() {

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);

        CopyOnWriteArrayList<Player> targets = new CopyOnWriteArrayList<>();

        targets.add(target1);
        targets.add(target2);

        Weapon furnace = new Weapon();
        furnace.setAlternativeEffect(new CozyFireMode());

        furnace.getAlternativeEffect().applyEffect(shooter, targets);

        assertEquals(1, target1.getPlayerboard().getDamage().size());
        assertEquals(1, target2.getPlayerboard().getDamage().size());
        assertEquals(1, target1.getPlayerboard().getMarker().size());
        assertEquals(1, target2.getPlayerboard().getMarker().size());
    }

    @Test
    public void getTargets() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("sinistra");
        TestBot.initAnswers(answers);

        Player shooter = Server.connectedPlayers.get(0);
        Player target1 = Server.connectedPlayers.get(1);
        Player target2 = Server.connectedPlayers.get(2);
        Player unreachable = Server.connectedPlayers.get(3);


        target1.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        target2.setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        unreachable.setPosition(Board.getMap().getBlueRoom().getCells().get(1));

        Weapon furnace = new Weapon();
        furnace.setAlternativeEffect(new CozyFireMode());

        CopyOnWriteArrayList<Player> targets = furnace.getAlternativeEffect().getTargets(shooter);

        assertEquals(2, targets.size());

    }

    @Test
    public void hasTargets() {


        Weapon weapon = new Weapon();
        weapon.setAlternativeEffect(new CozyFireMode());
        boolean result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);


        Server.connectedPlayers.get(1).setPosition(Board.getMap().getBlueRoom().getCells().get(2));
        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, true);


        for (int i = 1; i < 5; i++) {

            Server.connectedPlayers.get(i).setPosition(Board.getMap().getYellowRoom().getCells().get(1));
        }

        result = weapon.getAlternativeEffect().hasTargets(Server.connectedPlayers.get(0));
        assertEquals(result, false);
    }
}
