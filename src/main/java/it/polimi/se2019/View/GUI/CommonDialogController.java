package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the common dialog window.
 */
public class CommonDialogController implements Initializable {

    @FXML
    public Button okButton;

    @FXML
    public Label label;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void close(){

        okButton.getScene().getWindow().hide();

    }

    public CommonDialogController(){}


    @FXML
    public void setText(String message){

        label.setText(message);

    }


}
