package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class championSelection implements Initializable {

    public championSelection(){

    }

    @FXML
    public String championName;

    @FXML
    public void selectDozer(){

        championName = "Dozer";

    }


    @FXML
    public void selectDistruttore(){

        championName = ":D-strutt-or3";

    }

    @FXML
    public void selectVioletta(){

        championName = "Violetta";

    }

    @FXML
    public void selectSprog(){

        championName = "Sprog";

    }

    @FXML
    public void selectBanshee(){


        championName = "Banshee";


    }

    @FXML
    public void select(){

        System.out.println(championName);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
