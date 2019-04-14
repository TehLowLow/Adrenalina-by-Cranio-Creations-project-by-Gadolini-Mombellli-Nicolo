package it.polimi.se2019.Controller.Data.PowerUpBuilder;


import it.polimi.se2019.Model.Effect;
import it.polimi.se2019.Model.Powerup;
import it.polimi.se2019.Model.Rybamount;

/**
 * This class creates 2 Yellow Newton  power ups, and adds them to the PUp deck.
 * In the italian game this card's name is "Raggio Cinetico"
 */
public class YellowNewtonBuilder {

    private Powerup yNewton = new Powerup();
    private Effect eff;
    private String cDesc;


    public Powerup build(){

        Rybamount tValue = new Rybamount();

        tValue.setYellowCubes(1);
        tValue.setRedCubes(0);
        tValue.setBlueCubes(0);



        yNewton.setName("Yellow Newton");
        yNewton.setTradeValue(tValue);
        yNewton.setCardDescription(cDesc);
        yNewton.setEffect(eff);

        return yNewton;
    }





}
