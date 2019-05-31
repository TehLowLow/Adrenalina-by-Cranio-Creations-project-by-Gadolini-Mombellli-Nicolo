package it.polimi.se2019.View.GUI;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class ChooseMapController implements Initializable {

    public DataOutputStream out;

    @FXML
    public ImageView map1;

    @FXML
    public ImageView map2;

    @FXML
    public ImageView map3;

    @FXML
    public ImageView map4;


    public ChooseMapController(){}

    @FXML
    private void chosen1(){

        try {
            Bloom bloom = new Bloom();
            map1.setEffect(bloom);
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
            Bloom bloom = new Bloom();
            map2.setEffect(bloom);
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

            Bloom bloom = new Bloom();
            map3.setEffect(bloom);
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

            Bloom bloom = new Bloom();
            map4.setEffect(bloom);
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
