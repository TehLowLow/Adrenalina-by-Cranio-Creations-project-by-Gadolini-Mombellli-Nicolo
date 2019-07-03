package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.ConfigurationTest;
import it.polimi.se2019.Controller.Data.EffectBuilders.TagBackGrenadeEffect;
import it.polimi.se2019.Controller.Data.PowerUpBuilder.BlueTagbackGrenadeBuilder;
import it.polimi.se2019.Model.Board;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Network.Server;
import it.polimi.se2019.Network.TestBot;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertTrue;

public class TurnTest {

    @Before
    public void prepareEnvironment() {

        ConfigurationTest.createTestConfiguration();

    }

    @Test
    public void standardNoTerminator() {

        CopyOnWriteArrayList<String> answers = new CopyOnWriteArrayList<>();
        answers.add("muovi");
        answers.add("1");
        answers.add("0");
        answers.add("muovi");
        answers.add("1");
        answers.add("0");

        TestBot.initAnswers(answers);





        Turn t = new Turn();

        Player player = Server.connectedPlayers.get(0);

        BlueTagbackGrenadeBuilder tagbackGrenadeBuilder = new BlueTagbackGrenadeBuilder();
        Powerup tagBack = tagbackGrenadeBuilder.build();
        tagBack.setEffect(new TagBackGrenadeEffect());

        player.getPlayerboard().getPowerups().add(tagBack);


        t.standard(player, false);

        assertTrue(player.getPosition().equals(Board.getMap().getBlueRoom().getCells().get(0)));


    }
}