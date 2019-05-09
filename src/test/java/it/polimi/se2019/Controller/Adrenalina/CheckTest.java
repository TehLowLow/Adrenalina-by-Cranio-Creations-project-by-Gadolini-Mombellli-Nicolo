package it.polimi.se2019.Controller.Adrenalina;

import it.polimi.se2019.Controller.Data.EffectBuilders.GeneralMethods.Damage;
import it.polimi.se2019.Model.Player;
import it.polimi.se2019.Model.Playerboard;
import it.polimi.se2019.Model.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CheckTest {

    @Test
    public void death() {

        Player damaged = new Player();
        damaged.setPlayerboard(new Playerboard());
        damaged.getPlayerboard().setDamage(new ArrayList<Token>());
        Player attacker = new Player();
        attacker.setPlayerboard(new Playerboard());
        attacker.getPlayerboard().setChampionName("TestChampion");

        Damage.giveDamage(12, attacker, damaged);

        Assert.assertTrue(Check.death(damaged)==2);


    }



}