package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the window that asks if the players want to use the Terminator.
 */
public class SelectTerminatorController implements Initializable {

    @FXML
    DataOutputStream out;


    @FXML
   public void selectClassica(){

       try {

           if(GUI.RMI) {

               GUI.RMIAnswer = "classica";
               GUI.answeredRMI = true;
           }

           else {

           out.writeUTF("classica");
       }
       }catch(Exception e){

       }


   }

   @FXML
   public void selectTerminator(){

       try {

           if(GUI.RMI) {

               GUI.RMIAnswer = "terminator";
               GUI.answeredRMI = true;
           }

           else {

           out.writeUTF("terminator");
       }
       }catch(Exception e){

       }

   }


    public SelectTerminatorController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
