package it.polimi.se2019.View;

/**
 * This class works as an interface between Controller and View's classes. With a single istance of View,
 * any class can have access to the methods and objects needed to print messages, game's status and
 * to receive user's inputs. Its fields are public to avoid using getters and setters, that would decrease
 * the code's readability.
 */

public class View {

    /**
     * Message object. Its methods can be used for printing various kinds of text messages.
     */
    public Message message;

    /**
     * Parser object. Its methods return the input inserted by the user.
     */
    public Parser parser;

    /**
     * Printer object. Its methods can print the textual representation of the game's elements, such as the map.
     */
    public Printer printer;

    /**
     * Constructor of the view. It just instantiates objects for the field.
     */
    public View(){

        this.parser = new Parser();
        this.message = new Message();
        this.printer = new Printer();

    }

}
