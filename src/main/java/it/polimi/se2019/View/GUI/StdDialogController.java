package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Window;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class StdDialogController implements Initializable {

    GUI gui;

    public StdDialogController(){}

    @FXML
    public static TextField textReceived;

    @FXML
    TextField textField;

    @FXML
    public Button confermaButton;

    @FXML
    public void conferma(){

        Window stage = confermaButton.getScene().getWindow();
        stage.hide();

        try {

        }
        catch(Exception e){

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setGui(GUI gui){
        this.gui = gui;
    }
}
