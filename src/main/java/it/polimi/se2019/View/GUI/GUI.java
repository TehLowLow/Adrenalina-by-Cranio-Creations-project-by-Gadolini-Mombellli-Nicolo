package it.polimi.se2019.View.GUI;

import it.polimi.se2019.Model.Connection;
import it.polimi.se2019.View.Message;
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

    public int chosenMap;
    public int chosenSkulls;

    Map1UIController map1UIController;


    public boolean isMapActive;
    public String toWrite;


    @Override
    public void start(Stage primaryStage) throws Exception {


        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Adrenalina");
        initGUI();

    }

    //----- METODI DI AGGIORNAMENTO -----//

    public void initGUI() {

        isMapActive = false;

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

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Eccezione in initGUI");
        }

    }

    public void showChooseMap() {

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

        } catch (Exception e) {
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }

    }

    private void showChooseTerminator() {

        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/selectTerminator.fxml"));
            AnchorPane chooseTerminator = (AnchorPane) loader.load();

            SelectTerminatorController controller = loader.getController();
            controller.out = out;


            Scene scene = new Scene(chooseTerminator);

            primaryStage.setScene(scene);
            primaryStage.show();

            System.out.println("Grafica disegnata correttamente.");

        } catch (Exception e) {
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }


    }

    public void showChooseChampion(String message) {

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

        } catch (Exception e) {
        }


    }

    public void showChooseSkulls() {

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

        } catch (Exception e) {
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }


    }

    public void showCommonDialog(String message) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/commonDialog.fxml"));
            AnchorPane commonDialog = (AnchorPane) loader.load();

            CommonDialogController commonDialogController = loader.getController();

            commonDialogController.setText(message);

            Scene scene = new Scene(commonDialog);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Adrenalina: messaggio dal server!");
            stage.show();


        } catch (Exception e) {
            System.out.println("Eccezione.");
            e.printStackTrace();
        }

    }

    public void showSpawn(String message) {


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/discardPowerUpSpawn.fxml"));
            AnchorPane showSpawn = (AnchorPane) loader.load();

            ShowSpawnController showSpawnController = loader.getController();

            showSpawnController.out = out;
            showSpawnController.setUp(message);

            Scene scene = new Scene(showSpawn);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Scegli dove spawnare!");
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void showChooseSteps() {

        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/chooseSteps.fxml"));
            AnchorPane chooseSteps = (AnchorPane) loader.load();

            ChooseStepsController controller = loader.getController();
            controller.out = out;


            Scene scene = new Scene(chooseSteps);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            System.out.println("Grafica disegnata correttamente");

        } catch (Exception e) {
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }


    }

    public void showChooseDirection(boolean enhanced) {

        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/chooseDirection.fxml"));
            AnchorPane chooseDirection = (AnchorPane) loader.load();

            ChooseDirectionController controller = loader.getController();
            controller.out = out;


            Scene scene = new Scene(chooseDirection);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            if (enhanced) {
                controller.stop.setVisible(true);
            }

            System.out.println("Grafica disegnata correttamente");

        } catch (Exception e) {
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }


    }

    public void showMainStage(String message) {

        try {

            FXMLLoader loader = new FXMLLoader();

            if (chosenMap == 1) {

                loader.setLocation(GUI.class.getResource("/map1UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map1UIController = loader.getController();
                map1UIController.out = out;

                map1UIController.setMessage(message);
                map1UIController.parser(message);
                map1UIController.initUI();
                map1UIController.refresh();

                System.out.println(map1UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void showMainStageChooseCell(String message) {

        try {

            FXMLLoader loader = new FXMLLoader();

            if (chosenMap == 1) {

                loader.setLocation(GUI.class.getResource("/map1UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map1UIController = loader.getController();
                map1UIController.out = out;

                map1UIController.setMessage("Scegli una cella tra quelle evidenziate cliccandoci sopra.");
                map1UIController.parseChooseCell(message);
                map1UIController.lightCells();
                map1UIController.parser(message);
                map1UIController.initUI();
                map1UIController.refresh();

                System.out.println(map1UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void refreshGUI(String message) {

        try {

            FXMLLoader loader = new FXMLLoader();

            if (chosenMap == 1) {

                loader.setLocation(GUI.class.getResource("/map1UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map1UIController.setMessage(message);
                map1UIController.parser(message);
                map1UIController.refresh();

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }


        } catch (Exception e) {


        }

    }

    public void showChooseShootingMode() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/chooseShootingMode.fxml"));
            AnchorPane chooseMode = (AnchorPane) loader.load();

            ChooseShootingModeController controller = loader.getController();
            controller.out = out;


            Scene scene = new Scene(chooseMode);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            System.out.println("Grafica disegnata correttamente");

        } catch (Exception e) {
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }
    }

    public void showChoosePayPU(String message) {


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/choosePayPU.fxml"));
            AnchorPane showPUPay = (AnchorPane) loader.load();

            PayPowerUpController showPayPUController = loader.getController();

            showPayPUController.out = out;
            showPayPUController.setUp(message);

            Scene scene = new Scene(showPUPay);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Scegli come pagare!");
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void showExtraGrenade() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/ExtraGrenade.fxml"));
            AnchorPane chooseExtra = loader.load();

            ChooseExtraGrenade control = loader.getController();

            control.out = out;

            Scene scene = new Scene(chooseExtra);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void update(String message) {

        if (message.contains("combattere")) {
            showChooseMap();
        } else if (message.contains("teschi da piazzare")) {
            showChooseSkulls();
        } else if (message.contains("Scegli il tuo campione tra:")) {
            showChooseChampion(message);
        } else if (message.contains("Spawnerai")) {

            showSpawn(message);

        } else if (message.contains("Il tuo campione è")) {

            showMainStage(message);
            //Da questo momento si mostra la mappa quando arriva un messaggio.
            isMapActive = true;

        } else if (message.contains("classica o il terminator")) {
            showChooseTerminator();
        } else if (message.contains("passi vuoi fare")) {

            showMainStage(message);
            showChooseSteps();
        } else if (message.contains("direzione vuoi") || message.contains("basso")) {
            showMainStage(message);
            showChooseDirection(false);
        } else if (message.contains("cella") && message.contains("indicandone il numero")) {

            showMainStageChooseCell(message);

        } else if (message.contains("alternativo")) {

            showChooseShootingMode();

        } else if (message.contains("da scartare per pagare il costo indicandone il numero")) {

            showChoosePayPU(message);

        } else if (message.contains("granata extra")) {

            showExtraGrenade();

        } else if (message.contains("stop")) {


            showChooseDirection(true);

        } else if (isMapActive) {

            showMainStage(message);

        } else if (!isMapActive) {

            //Se non c'è ancora la mappa, siamo nelle prime fasi
            showCommonDialog(message);

            //Se mi capita il messaggio in cui trovo la conferma della mappa,
            // leggo che mappa hanno scelto,così poi so caricare la UI giusta.

            if (message.contains("numero 1")) {
                chosenMap = 1;
            }

            if (message.contains("numero 2")) {
                chosenMap = 2;
            }

            if (message.contains("numero 3")) {
                chosenMap = 3;
            }

            if (message.contains("numero 4")) {
                chosenMap = 4;
            }
        }


    }


    public void run() {

        launch(null);

    }


}


//TODO: Bug nella gui per gli spostamenti, powerup all inizio (falsi) doppi, testare base o alternativo, escape character per azioni



