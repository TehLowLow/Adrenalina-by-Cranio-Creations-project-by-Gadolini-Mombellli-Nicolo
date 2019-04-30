package it.polimi.se2019.View;

import java.util.Scanner;

public class Parser {

    public static String parse(){
        /*
         Legge ciò che è stato digitato dall'utente e lo restituisce.
         */

        String input;

        Scanner scanIn = new Scanner(System.in);
        input = scanIn.nextLine();

        scanIn.close();
        //System.out.println(input);

        return input;
    }

}
