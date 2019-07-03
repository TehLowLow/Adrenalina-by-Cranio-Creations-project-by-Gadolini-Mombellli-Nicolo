package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseExtraGrenade implements Initializable {

    DataOutputStream out;


    @FXML
    Button basic;

    @FXML
    Button extra;

    public ChooseExtraGrenade() {
    }


    @FXML

    public void chooseBasic() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "base";
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF("base");
        }
            basic.setDisable(true);
            extra.setDisable(true);
            basic.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void chooseExtra() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "extra";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("extra");
        }
            basic.setDisable(true);
            extra.setDisable(true);
            extra.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
