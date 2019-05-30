package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class SkullController implements Initializable {


    public DataOutputStream out;

    String chosenNumber = "8";

    @FXML
    public ToggleButton fivebutton;

    @FXML
    public ToggleButton sixbutton;

    @FXML
    public ToggleButton sevenbutton;

    @FXML
    public ToggleButton eightbutton;


    public void chose5(){

        chosenNumber = "5";
        sixbutton.setSelected(false);
        sevenbutton.setSelected(false);
        eightbutton.setSelected(false);

    }

    public void chose6(){

        chosenNumber = "6";
        fivebutton.setSelected(false);
        sevenbutton.setSelected(false);
        eightbutton.setSelected(false);

    }

    public void chose7(){

        chosenNumber = "7";
        fivebutton.setSelected(false);
        sixbutton.setSelected(false);
        eightbutton.setSelected(false);

    }

    public void chose8(){

        chosenNumber = "8";
        fivebutton.setSelected(false);
        sixbutton.setSelected(false);
        sevenbutton.setSelected(false);

    }

    public void confirm(){

        try {

            out.writeUTF(chosenNumber);
        }
        catch(Exception e){

            System.out.println("Eccezione in skulls");
            e.printStackTrace();

        }
    }

    public SkullController(){}



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
