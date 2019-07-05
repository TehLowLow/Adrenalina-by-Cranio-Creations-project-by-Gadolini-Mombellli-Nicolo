package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class prints the interface for the player, showing damages, weapons and powerups available.
 */
public class PlayerInfo {

    /**
     * This method gets the information of a player.
     * @param player the player whose information must be retrieved.
     * @return a string representing player's info.
     */
    public static String getInfo(Player player){

        CopyOnWriteArrayList<Token> damages = player.getPlayerboard().getDamage();
        CopyOnWriteArrayList<Token> markers = player.getPlayerboard().getMarker();
        int score = player.getScore();
        Rybamount ammoCubes = player.getPlayerboard().getAmmoCubes();
        CopyOnWriteArrayList<Powerup> powerups = player.getPlayerboard().getPowerups();
        String champion = player.getPlayerboard().getChampionName();
        CopyOnWriteArrayList<Weapon> weapons = player.getPlayerboard().getWeapons();

        return printChampionName(champion) + "-----\n" + printScore(score) + "-----\n" + printDamages(damages) + "-----\n" + printMarkers(markers) + "-----\n" + printCubes(ammoCubes) + "-----\n" + printWeapons(weapons) + "-----\n" + printPowerups(powerups) + "\n";

    }

    /**
     * Prints player's champion name
     * @param championName the champion name
     * @return a string representing the championName;
     */
    private static String printChampionName(String championName){

        return "---------------" + championName + "---------------\n";

    }


    /**
     * Prints player's score
     * @param score the champion's name
     * @return a string representing the championName;
     */
    private static String printScore(int score){

        return "Punteggio: " + score + "\n";

    }

    /**
     * Prints player's damages
     * @param damages the champion's damages
     * @return a string representing the champion's damages;
     */
    private static String printDamages(CopyOnWriteArrayList<Token> damages){

        int nDamages = damages.size();

        String finalString = "Danni subiti: " + nDamages + "/11\n";

        int violettaDamages = 0;
        int dozerDamages = 0;
        int bansheeDamages = 0;
        int distruttoreDamages = 0;
        int sprogDamages = 0;

        for (Token damage:damages){

            if (damage.getChampionName().equalsIgnoreCase("Violetta")){

                violettaDamages++;

            }

            if (damage.getChampionName().equalsIgnoreCase("Dozer")){

                dozerDamages++;

            }

            if (damage.getChampionName().equalsIgnoreCase("Sprog")){

                sprogDamages++;

            }

            if (damage.getChampionName().equalsIgnoreCase("Banshee")){

                bansheeDamages++;

            }

            if (damage.getChampionName().equalsIgnoreCase(":D-strutt-or3")){

                distruttoreDamages++;

            }


        }

        if (violettaDamages > 0){

            finalString = finalString + "Violetta (" + violettaDamages + ")\n";

        }

        if (dozerDamages > 0){

           finalString = finalString + "Dozer (" + dozerDamages + ")\n";

        }

        if (distruttoreDamages > 0){

            finalString = finalString + ":D-strutt-or3" + distruttoreDamages + ")\n";

        }

        if (bansheeDamages > 0){

            finalString = finalString + "Banshee (" + bansheeDamages + ")\n";

        }

        if (sprogDamages > 0){

            finalString = finalString + "Sprog (" + sprogDamages + ")\n";

        }

     return finalString;

    }



    /**
     * Prints player's champion markers
     * @param markers the champion markers
     * @return a string representing the markers;
     */
    private static String printMarkers(CopyOnWriteArrayList<Token> markers){

        int nMarkers = markers.size();

        String finalString = "Marchi subiti: " + nMarkers + "\n";


        int bansheeMarkers = 0;
        int distruttoreMarkers = 0;
        int sprogMarkers = 0;
        int violettaMarkers = 0;
        int dozerMarkers = 0;


        for (Token marker:markers){



            if (marker.getChampionName().equalsIgnoreCase("Dozer")){

                dozerMarkers++;

            }

            if (marker.getChampionName().equalsIgnoreCase("Sprog")){

                sprogMarkers++;

            }

            if (marker.getChampionName().equalsIgnoreCase("Violetta")){

                violettaMarkers++;

            }

            if (marker.getChampionName().equalsIgnoreCase("Banshee")){

                bansheeMarkers++;

            }

            if (marker.getChampionName().equalsIgnoreCase(":D-strutt-or3")){

                distruttoreMarkers++;

            }


        }

        if (violettaMarkers > 0){

            finalString = finalString + "Violetta (" + violettaMarkers + ")\n";

        }

        if (dozerMarkers > 0){

            finalString = finalString + "Dozer (" + dozerMarkers + ")\n";

        }

        if (distruttoreMarkers > 0){

            finalString = finalString + ":D-strutt-or3" + distruttoreMarkers + ")\n";

        }

        if (bansheeMarkers > 0){

            finalString = finalString + "Banshee (" + bansheeMarkers + ")\n";

        }

        if (sprogMarkers > 0){

            finalString = finalString + "Sprog (" + sprogMarkers + ")\n";

        }

        return finalString;

    }

    /**
     * Prints player's weapons
     * @param weapons the champion name
     * @return a string representing the weapons;
     */
    private static String printWeapons(CopyOnWriteArrayList<Weapon> weapons){

        String finalString = "Le tue armi sono:\n";

        for (Weapon weapon:weapons){

            finalString = finalString + weapon.getName() + " ";
            if (weapon.isLoaded()){

                finalString = finalString + "(Carica)\n";

            }

            else{

                finalString = finalString + "(Scarica)\n";

            }
        }

    return finalString;

    }


    /**
     * Prints player's powerups;
     * @param powerups the champion name
     * @return a string representing the powerups;
     */
    private static String printPowerups(CopyOnWriteArrayList<Powerup> powerups){

        String finalString = "I tuoi potenziamenti sono:\n";

        for (Powerup powerup:powerups){

            finalString = finalString + powerup.getName() + "\n";

        }

        return finalString;

    }

    /**
     * Prints player's ammunitions;
     * @param cubes the champion name
     * @return a string representing the ammunitions;
     */
    private static String printCubes(Rybamount cubes){

        return "Munizioni :\nRosse: " + cubes.getRed() + "\nBlu: " + cubes.getBlue() + "\nGialle: " + cubes.getYellow() + "\n";



    }



}
