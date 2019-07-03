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

            if(GUI.RMI) {

                GUI.RMIAnswer = "rosso";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("rosso");
        }
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectYellow(){

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "giallo";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("giallo");
        }
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectBlue(){

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "blu";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("blu");
        }
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectGreen(){

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "verde";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("verde");
        }
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectWhite(){

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "bianco";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("bianco");
        }
        }
        catch(Exception e){

        }

    }

    @FXML
    public void selectPurple(){

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "viola";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("viola");
        }
        }
        catch(Exception e){

        }

    }







    public ChooseRoomController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
