package it.polimi.se2019.View.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class LoginController implements Initializable {

    @FXML
    private String connectionType = "";

    public LoginController(){}

    @FXML
    private void selectedSocket(){

        connectionType = "socket";

    }

    @FXML
    private void selectedRMI(){

        connectionType = "RMI";

    }

    @FXML
    private void avvio(){

        System.out.println("Hai scelto la connessione di tipo " + connectionType);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
