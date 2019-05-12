package it.polimi.se2019.View.CLI;
import java.io.Console;

public class CLItest {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    static String line1 = "------------------------- ------------------------- -----------------------";
    static String line2 = "|                        '                         '                      |";
    static String line3 =  "----------|   |---------- ------------------------- ----------|   |--------";
    static String line4 = "-----------------------";
    static String line5 = "|                     |";
    static String line6 = "|                        '                         '                       ";
    static String line7 = "                      |" ;
    static String line8 = "------------------------- ----------|   |---------- -----------------------";
    static String line9 =  "|. . . . . . . . . . .|";
    static String line10 = ANSI_RESET + "                          " + ANSI_WHITE_BACKGROUND + "----------|   |---------- -----------------------";
    static String line11 = "                          " + ANSI_WHITE_BACKGROUND + "|                        '                      |" + ANSI_YELLOW_BACKGROUND + "|                     |";
    static String line12 = "                          " + ANSI_WHITE_BACKGROUND + "|                        '                       " + ANSI_YELLOW_BACKGROUND + "                      |";
    static String line14 = "                          " + ANSI_WHITE_BACKGROUND + "------------------------- -----------------------" + ANSI_YELLOW_BACKGROUND + "-----------------------";


    public static void main(String[] args) {

        System.out.println(ANSI_CYAN_BACKGROUND +

                        line1 + ANSI_RESET + "\n" + ANSI_CYAN_BACKGROUND +

                        line2 + ANSI_RESET + "\n" + ANSI_CYAN_BACKGROUND +
                        line2 + ANSI_RESET + "\n" + ANSI_CYAN_BACKGROUND +
                        line2 + ANSI_RESET + "\n" + ANSI_CYAN_BACKGROUND +
                        line2 + ANSI_RESET + "\n" + ANSI_CYAN_BACKGROUND +
                        line3 + ANSI_RESET + "\n" + ANSI_RED_BACKGROUND  +
                        line3 + ANSI_RESET + ANSI_YELLOW_BACKGROUND +
                        line4 + ANSI_RESET + "\n" + ANSI_RED_BACKGROUND +
                        line2 + ANSI_RESET + ANSI_YELLOW_BACKGROUND +
                        line5 + ANSI_RESET + "\n" + ANSI_RED_BACKGROUND +
                        line6 + ANSI_RESET + ANSI_YELLOW_BACKGROUND +
                        line7 + ANSI_RESET + "\n" + ANSI_RED_BACKGROUND +
                        line6 + ANSI_RESET + ANSI_YELLOW_BACKGROUND +
                        line7 + ANSI_RESET + "\n" + ANSI_RED_BACKGROUND +
                        line8 + ANSI_RESET + ANSI_YELLOW_BACKGROUND +
                        line9 + ANSI_RESET + "\n" +
                        line10 + ANSI_RESET + ANSI_YELLOW_BACKGROUND +
                        line9 + ANSI_RESET + "\n" +
                        line11 + ANSI_RESET + "\n" +
                        line12 + ANSI_RESET + "\n" +
                        line12 + ANSI_RESET + "\n" +
                        line11 + ANSI_RESET + "\n" +
                        line14 + ANSI_RESET + "\n"

        );


    }

}
