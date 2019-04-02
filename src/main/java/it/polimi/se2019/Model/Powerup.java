package it.polimi.se2019.Model;

/**
 * This class represents the Powerups.
 * Powerups are objects that can be used in two ways:
 *
 1) A player can use their effects;
 2) A player can use their trade value instead of the ammo cubes.
 *
 */
public class Powerup {


    /*
    --------------------FIELDS------------------------------
    PowerUp contains his name, his trade value in terms of "Rybamounts".
    It has also the effect and a description of the card.
     */

    private String name;

    private Rybamount tradeValue;

    private String cardDescription;

    private Effect effect;




    /*
    --------------------METHODS---------------------------
     */



    /*
    -------------------GETTERS----------------------------
     */

    public String getName() {return name;}

    public Rybamount getTradeValue() {return tradeValue;}

    public String getCardDescription() {return cardDescription;}

    public Effect getEffect() {return effect;}





    /*
    -----------------------SETTERS---------------------------
     */

    public void setName(String name) {this.name = name;}

    public void setTradeValue(Rybamount tradeValue) {this.tradeValue = tradeValue;}

    public void setCardDescription(String cardDescription) {this.cardDescription = cardDescription;}

    public void setEffect(Effect effect) {this.effect = effect;}



}



