package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseStepsController implements Initializable {

    DataOutputStream out;

    @FXML
    Button chose1;

    @FXML
    Button chose2;

    @FXML
    Button chose3;

    public ChooseStepsController(){
    }

    @FXML
    public void chosen1(){

        try{
            out.writeUTF("1");
            chose1.setDisable(true);
            chose2.setDisable(true);
            chose3.setDisable(true);
            chose1.getScene().getWindow().hide();
        }catch(Exception e){

        }

    }

    @FXML
    public void chosen2(){

        try{
            out.writeUTF("2");
            chose1.setDisable(true);
            chose2.setDisable(true);
            chose3.setDisable(true);
            chose1.getScene().getWindow().hide();
        }catch(Exception e){

        }


    }

    @FXML
    public void chosen3(){

        try{
            out.writeUTF("3");
            chose1.setDisable(true);
            chose2.setDisable(true);
            chose3.setDisable(true);
            chose1.getScene().getWindow().hide();
        }catch(Exception e){

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
