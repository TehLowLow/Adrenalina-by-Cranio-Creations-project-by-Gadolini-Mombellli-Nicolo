package it.polimi.se2019.View.GUI;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseMapController implements Initializable {

    public DataOutputStream out;


    public ChooseMapController(){}

    @FXML
    private void chosen1(){

        try {

            out.writeUTF("1");
        }
        catch(Exception e){

            System.out.println("Eccezione in mappa");
            e.printStackTrace();

        }
    }

    @FXML
    private void chosen2(){

        try {

            out.writeUTF("2");
        }
        catch(Exception e){

            System.out.println("Eccezione in mappa");
            e.printStackTrace();

        }

    }

    @FXML
    private void chosen3(){

        try {

            out.writeUTF("3");
        }
        catch(Exception e){

            System.out.println("Eccezione in mappa");
            e.printStackTrace();

        }

    }

    @FXML
    private void chosen4(){

        try {

            out.writeUTF("4");
        }
        catch(Exception e){

            System.out.println("Eccezione in mappa");
            e.printStackTrace();

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}
