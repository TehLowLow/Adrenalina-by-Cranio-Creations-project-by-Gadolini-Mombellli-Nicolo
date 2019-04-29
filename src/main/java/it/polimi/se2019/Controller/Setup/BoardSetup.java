package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;

/**
 * Creates the final board merging all the data coming from the other Setups classes.
 */

public class BoardSetup {


    /**
     *Creates the map from all the data.
     * @return the complete map.
     */

    public Board build(){

        Board board = new Board();

        WeaponDeckSetup weaponDeckSetup = new WeaponDeckSetup();
        board.setWeaponDeck(weaponDeckSetup.build());



        LootDeckSetup lootDeckSetup = new LootDeckSetup();
        board.setLootDeck(lootDeckSetup.build());

        PowerUpDeckSetup powerUpDeckSetup = new PowerUpDeckSetup();
        board.setPowerUpDeck(powerUpDeckSetup.build());


        return board;

    }
}
