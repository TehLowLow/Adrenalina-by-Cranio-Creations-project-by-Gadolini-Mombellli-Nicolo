package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseRoomController implements Initializable {


    DataOutputStream out;

    @FXML
    public void selectRed(){

        try {
            out.writeUTF("rosso");
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectYellow(){

        try {
            out.writeUTF("giallo");
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectBlue(){

        try {
            out.writeUTF("blu");
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectGreen(){

        try {
            out.writeUTF("verde");
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectWhite(){

        try {
            out.writeUTF("bianco");
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectPurple(){

        try {
            out.writeUTF("viola");
        }
        catch(Exception e){

        }

    }







    public ChooseRoomController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
