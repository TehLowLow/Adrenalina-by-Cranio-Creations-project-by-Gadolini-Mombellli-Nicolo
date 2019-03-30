package it.polimi.se2019.Model;

public class MortalBlow {

    /*
    *------------------------FIELDS-----------------------------
    * Here there are the 3 possible way to fill the mortal blow
     */

    private boolean skull;

    private boolean overkill;

    private Player killer;


    /*
    -------------------------METHODS----------------------------
     */





    /*
    -------------------------GETTERS----------------------------
     */

    public Player getKiller(){ return killer; }



    public boolean isSkull() {
        return skull;
    }


    public boolean isOverkill() {
        return overkill;
    }



    /*
    ------------------------SETTERS------------------------------
     */


    public void setSkull(boolean skull) { this.skull = skull; }


    public void setOverkill(boolean overkill) { this.overkill = overkill; }


    public void setKiller(Player killer) {this.killer = killer; }


}
