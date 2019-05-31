package it.polimi.se2019.View.GUI;

import it.polimi.se2019.Model.Connection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Thread.sleep;

public class GUI extends Application implements Runnable {

    private static Stage primaryStage;
    private static AnchorPane rootLayout;
    public static DataOutputStream out;


    @Override
    public void start(Stage primaryStage) throws Exception{


            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Adrenalina");
            initGUI();

    }

    //----- METODI DI AGGIORNAMENTO -----//

    public void initGUI(){

        try {

            Font.loadFont(
                    GUI.class.getResource("/assets/Fonts/Ethnocentric/spaceranger.ttf").toExternalForm(),
                    20
            );

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/cover.fxml"));
            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Eccezione in initGUI");
        }

    }

    public void showChooseMap(){

        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/chooseMap.fxml"));
            AnchorPane chooseMap = (AnchorPane) loader.load();

            ChooseMapController controller = loader.getController();
            controller.out = out;


            Scene scene = new Scene(chooseMap);

            primaryStage.setScene(scene);
            primaryStage.show();

            System.out.println("Grafica disegnata correttamente.");

        }
        catch(Exception e){
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }

    }

    public void showChooseChampion(String message){

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/selectchampion.fxml"));
            AnchorPane chooseChampion = (AnchorPane) loader.load();

            SelectChampionController controller = loader.getController();
            controller.out = out;
            controller.activateRadioButtons(message);

            Scene scene = new Scene(chooseChampion);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        }
        catch(Exception e){}


    }

    public void showChooseSkulls(){

        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/Skulls.fxml"));
            AnchorPane chooseSkulls = (AnchorPane) loader.load();

            SkullController controller = loader.getController();
            controller.out = out;


            Scene scene = new Scene(chooseSkulls);

            primaryStage.setScene(scene);
            primaryStage.show();

            System.out.println("Grafica disegnata correttamente");

        }
        catch(Exception e){
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }


    }

    public void showCommonDialog(String message){

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/commonDialog.fxml"));
            AnchorPane commonDialog = (AnchorPane) loader.load();

            CommonDialogController commonDialogController = loader.getController();

            commonDialogController.setText(message);

            Scene scene = new Scene(commonDialog);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


        }catch(Exception e){
            System.out.println("Eccezione.");
            e.printStackTrace();
        }

    }

    public void update(String message) {

        if(message.contains("combattere")){
            showChooseMap();
        }

        else if(message.contains("teschi da piazzare")){
            showChooseSkulls();
        }

        else if(message.contains("Scegli il tuo campione tra:")){
            showChooseChampion(message);
        }


        else{
            showCommonDialog(message);
        }

    }

    public void run() {

        launch(null);

    }



}