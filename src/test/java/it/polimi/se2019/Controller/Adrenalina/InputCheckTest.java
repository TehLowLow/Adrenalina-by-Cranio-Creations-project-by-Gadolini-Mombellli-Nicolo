package it.polimi.se2019.Controller.Adrenalina;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputCheckTest {

    @Test
    public void standardAction() {
    }

    @Test
    public void enhancedAction() {
    }

    @Test
    public void frenzyAction() {
    }

    @Test
    public void frenzyEnhancedAction() {
    }

    @Test
    public void checkMapInput() {

       assertTrue(InputCheck.checkMapInput("1"));
        assertTrue(InputCheck.checkMapInput("2"));
        assertTrue(InputCheck.checkMapInput("3"));
        assertTrue(InputCheck.checkMapInput("4"));

    }

    @Test
    public void directionCheck() {

        assertTrue(InputCheck.directionCheck("alto"));
        assertTrue(InputCheck.directionCheck("basso"));
        assertTrue(InputCheck.directionCheck("sinistra"));
        assertTrue(InputCheck.directionCheck("destra"));
        
    }
    
    @Test
    public void colourCheck() {

        assertEquals(InputCheck.colourCheck("rosso"), 1);
        assertEquals(InputCheck.colourCheck("giallo"), 0);
        assertEquals(InputCheck.colourCheck("blu"), 2);
        assertEquals(InputCheck.colourCheck("bianco"), 3);
        assertEquals(InputCheck.colourCheck("verde"), 4);
        assertEquals(InputCheck.colourCheck("viola"), 5);


        
    }

    @Test
    public void numberCheck() {
    }

    @Test
    public void checkSkullsInput() {

        assertTrue(InputCheck.checkSkullsInput("5"));
        assertTrue(InputCheck.checkSkullsInput("6"));
        assertTrue(InputCheck.checkSkullsInput("7"));
        assertTrue(InputCheck.checkSkullsInput("8"));

    }



    @Test
    public void yesOrNo() {
    }

    @Test
    public void correctYesNo() {
    }

    @Test
    public void baseEffectGrenadeLauncher() {
    }

    @Test
    public void availablePlayer() {
    }

    @Test
    public void chooseTurretTripodTarget() {
    }

    @Test
    public void chooseChampion() {
    }

    @Test
    public void correctWeapon() {
    }

    @Test
    public void correctBasicOrAlternative() {

        assertTrue(InputCheck.correctBasicOrAlternative("base"));
        assertTrue(!InputCheck.correctBasicOrAlternative("alternativp"));
        assertTrue(InputCheck.correctBasicOrAlternative("alternativo"));
        
    }

    @Test
    public void correctAction() {

        assertTrue(InputCheck.correctAction("Spara"));
        assertTrue(InputCheck.correctAction("Muovi"));
        assertTrue(InputCheck.correctAction("Raccogli"));
        assertTrue(!InputCheck.correctAction("Sparp"));
    }

    @Test
    public void correctPowerUp() {

        assertTrue(InputCheck.correctPowerUp("Granata Venom"));
        assertTrue(InputCheck.correctPowerUp("Teletrasporto"));
        assertTrue(InputCheck.correctPowerUp("Mirino"));
        assertTrue(InputCheck.correctPowerUp("Raggio Cinetico"));
        assertTrue(!InputCheck.correctPowerUp("Francesco Totti"));
    }

    @Test
    public void correctMoveEnhancedShot() {

        assertTrue(InputCheck.correctMoveEnhancedShot("stop"));
        assertTrue(InputCheck.correctMoveEnhancedShot("su"));
        assertTrue(InputCheck.correctMoveEnhancedShot("giu"));
        assertTrue(InputCheck.correctMoveEnhancedShot("destra"));
        assertTrue(InputCheck.correctMoveEnhancedShot("sinistra"));
        assertTrue(!InputCheck.correctMoveEnhancedShot("Francesco Totti della Roma"));
    }

    @Test
    public void correctWeaponRF() {
    }

    @Test
    public void chooseTerminator() {

        assertTrue(InputCheck.chooseTerminator("classica"));
        assertTrue(InputCheck.chooseTerminator("terminator"));
        assertTrue(!InputCheck.chooseTerminator("Forza Lazio"));
    }
}