package it.polimi.se2019.Controller.Setup;
import it.polimi.se2019.Model.*;

public class BoardSetup {

    public Board build(){

        Board board = new Board(new Map(null, null, null, null,null,null,null ));

        WeaponDeckSetup weaponDeckSetup = new WeaponDeckSetup();
        MapSetup mapSetup = new MapSetup();
        board.setWeaponDeck(weaponDeckSetup.build());
        return board;

    }


}

//TODO: MODIFICARE IL COSTRUTTORE DI BOARD, TOGLIERE IL PARAMETRO MAP, E INSERIRE PER ESSO GETTERS E SETTERS.