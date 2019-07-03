package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseShootingModeController implements Initializable {

    public ChooseShootingModeController() {
    }

    DataOutputStream out;

    @FXML
    Button base;


    public void chooseBasic() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "base";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("base");
        }
            base.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseAlternative() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "alternativo";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("alternativo");
        }
            base.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
