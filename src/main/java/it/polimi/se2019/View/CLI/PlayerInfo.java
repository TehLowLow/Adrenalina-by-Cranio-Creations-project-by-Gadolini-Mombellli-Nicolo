package it.polimi.se2019.View.CLI;

import it.polimi.se2019.Model.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerInfo {

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

    private static String printChampionName(String championName){

        return "---------------" + championName + "---------------\n";

    }


    private static String printScore(int score){

        return "Punteggio: " + score + "\n";

    }

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

    private static String printWeapons(CopyOnWriteArrayList<Weapon> weapons){

        String finalString = "Le tue armi sono:\n";

        for (Weapon weapon:weapons){

            finalString = finalString + weapon + " ";
            if (weapon.isLoaded()){

                finalString = finalString + "(Carica)\n";

            }

            else{

                finalString = finalString + "(Scarica)\n";

            }
        }

    return finalString;

    }


    private static String printPowerups(CopyOnWriteArrayList<Powerup> powerups){

        String finalString = "I tuoi potenziamenti sono:\n";

        for (Powerup powerup:powerups){

            finalString = finalString + powerup + "\n";

        }

        return finalString;

    }

    private static String printCubes(Rybamount cubes){

        return "Munizioni :\nRosse: " + cubes.getRed() + "\nBlu: " + cubes.getBlue() + "\nGialle: " + cubes.getYellow() + "\n";



    }



}
