package it.polimi.se2019.Model;

import it.polimi.se2019.Controller.Adrenalina.InputCheck;
import it.polimi.se2019.Controller.Data.RoomBuilders.Colour;

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

    /**
     * Name of the power up.
     */
    private String name;

    /**
     * Value of the power up if used as ammo cube.
     */
    private Rybamount tradeValue;

    /**
     * Description of the power up.
     */
    private String cardDescription;

    /**
     * Effect of the power up.
     */
    private Effect effect;

    private int colour;




    /*
    --------------------METHODS---------------------------
     */



    /*
    -------------------GETTERS----------------------------
     */


    /**
     * Getter for the name field.
     * @return the name of the power up.
     */
    public String getName() {return name;}

    /**
     * Getter for the tradeValue field.
     * @return the trade value of the power up.
     */
    public Rybamount getTradeValue() {return tradeValue;}

    /**
     * Getter for the cardDescription field.
     * @return the description of the power up.
     */
    public String getCardDescription() {return cardDescription;}


    /**
     * Getter for the effect field.
     * @return The effect of the power up.
     */
    public Effect getEffect() {return effect;}

    public int getColour(){
        return colour;
    }



    /*
    -----------------------SETTERS---------------------------
     */

    /**
     * Sets the name of the power up.
     * @param name the name of the power up.
     */
    public void setName(String name) {this.name = name;}

    /**
     * Sets the trade value of the power up.
     * @param tradeValue the trade value of the power up.
     */
    public void setTradeValue(Rybamount tradeValue) {this.tradeValue = tradeValue;}


    /**
     * Sets the description of the power up.
     * @param cardDescription the description of the power up.
     */
    public void setCardDescription(String cardDescription) {this.cardDescription = cardDescription;}

    /**
     * Sets the effect of the power up.
     * @param effect The effect of the power up.
     */
    public void setEffect(Effect effect) {this.effect = effect;}

    public void setColour(int set){

        colour = set;

    }



}



