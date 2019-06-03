package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectTerminatorController implements Initializable {

    @FXML
    DataOutputStream out;


    @FXML
   public void selectClassica(){

       try{

           out.writeUTF("classica");
       }catch(Exception e){

       }


   }

   @FXML
   public void selectTerminator(){

       try{

           out.writeUTF("terminator");
       }catch(Exception e){

       }

   }


    public SelectTerminatorController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
