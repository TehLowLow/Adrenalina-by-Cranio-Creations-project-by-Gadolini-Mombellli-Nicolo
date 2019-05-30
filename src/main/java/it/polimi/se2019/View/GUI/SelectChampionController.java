package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectChampionController implements Initializable {

    public DataOutputStream out;

    public SelectChampionController(){

    }

    @FXML
    public RadioButton bansheeButton;

    @FXML
    public RadioButton violettaButton;

    @FXML
    public RadioButton distruttoreButton;

    @FXML
    public RadioButton dozerButton;

    @FXML
    public RadioButton sprogButton;

    public String championName;

    boolean oneSelected = false;

    @FXML
    public void selectDozer(){

            championName = "Dozer";
            oneSelected = true;

    }


    @FXML
    public void selectDistruttore(){

        championName = ":D-strutt-or3";
        oneSelected = true;

    }

    @FXML
    public void selectVioletta(){

        championName = "Violetta";
        oneSelected = true;

    }

    @FXML
    public void selectSprog(){

        championName = "Sprog";
        oneSelected = true;

    }

    @FXML
    public void selectBanshee(){


        championName = "Banshee";
        oneSelected = true;


    }

    @FXML
    public void select(){

        if(oneSelected){
            try {

                out.writeUTF(championName);
            }

            catch(Exception e){

                System.out.println("Eccezione in selectChampion");
                e.printStackTrace();

            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void activateRadioButtons(String message){

        if(!message.contains("Violetta")){
            violettaButton.setDisable(true);
        }

        if(!message.contains("Dozer")){
            dozerButton.setDisable(true);
        }

        if(!message.contains("Banshee")){
            bansheeButton.setDisable(true);
        }
        if(!message.contains("Sprog")){
            sprogButton.setDisable(true);
        }
        if(!message.contains("Violetta")){
            violettaButton.setDisable(true);
        }
        if(!message.contains(":D-strutt-or3")){
            violettaButton.setDisable(true);
        }

    }
}
