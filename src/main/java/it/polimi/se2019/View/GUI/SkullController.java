package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @FXML
    public ImageView im5;

    @FXML
    public ImageView im6;

    @FXML
    public ImageView im7;

    @FXML
    public ImageView im8;


    public void chose5(){

        chosenNumber = "5";
        im5.setVisible(true);
        im6.setVisible(false);
        im7.setVisible(false);
        im8.setVisible(false);

        sixbutton.setSelected(false);
        sevenbutton.setSelected(false);
        eightbutton.setSelected(false);

    }

    public void chose6(){

        chosenNumber = "6";
        im5.setVisible(false);
        im6.setVisible(true);
        im7.setVisible(false);
        im8.setVisible(false);
        fivebutton.setSelected(false);
        sevenbutton.setSelected(false);
        eightbutton.setSelected(false);

    }

    public void chose7(){

        chosenNumber = "7";
        im5.setVisible(false);
        im6.setVisible(false);
        im7.setVisible(true);
        im8.setVisible(false);
        fivebutton.setSelected(false);
        sixbutton.setSelected(false);
        eightbutton.setSelected(false);

    }

    public void chose8(){

        im5.setVisible(false);
        im6.setVisible(false);
        im7.setVisible(false);
        im8.setVisible(true);
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
