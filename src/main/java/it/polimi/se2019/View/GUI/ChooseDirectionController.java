package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseDirectionController implements Initializable {

    DataOutputStream out;

    @FXML
    Button altoButton;

    @FXML
    Button stop;


    @FXML
    public void chooseAlto() {

        try {

            out.writeUTF("alto");
            stop.setVisible(false);
            altoButton.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseBasso() {

        try {

            out.writeUTF("basso");
            stop.setVisible(false);
            altoButton.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseDestra() {

        try {

            out.writeUTF("destra");
            stop.setVisible(false);
            altoButton.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseSinistra() {

        try {

            out.writeUTF("sinistra");
            stop.setVisible(false);
            altoButton.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseStop() {

        try {
            out.writeUTF("stop");
            stop.setVisible(false);
            altoButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ChooseDirectionController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
