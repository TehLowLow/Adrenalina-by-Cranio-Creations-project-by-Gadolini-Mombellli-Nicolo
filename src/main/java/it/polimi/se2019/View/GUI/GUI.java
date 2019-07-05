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

import static it.polimi.se2019.Network.RMI.Client.RMIClient.Game;
import static java.lang.Thread.sleep;

/**
 * Principal GUI controller. This class manages the inizialization and the adrministration of all the others GUI windows.
 */
public class GUI extends Application implements Runnable {


    /**
     * The primaryStage for the GUI.
     */
    private static Stage primaryStage;

    /**
     * The rootLayout for the GUI.
     */
    private static AnchorPane rootLayout;

    /**
     * Output stream used by socket to write the data.
     */
    public static DataOutputStream out;

    /**
     * This is true if RMI is used.
     */
    public static boolean RMI = false;

    /**
     * This is true if RMI has sent an answer.
     */
    public static boolean answeredRMI = false;

    /**
     * This contains the answer sent by RMI
     */
    public static String RMIAnswer = "null";

    /**
     * Path of the JAR.
     */
    public static String jarPath = GUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    /**
     * This contains the path of the graphic resource, relative to the user's pc.
     */
    public static String resourcesPath = "";

    /**
     * Chosen map.
     */
    public int chosenMap;

    /**
     * Number of skull chosen.
     */
    public int chosenSkulls;


    /**
     * Controller class for the interface with the map1.
     */
    Map1UIController map1UIController;

    /**
     * Controller class fort the user interface with the map2;
     */
    Map2UIController map2UIController;

    /**
     * Controller class for the user interface with the map3;
     */
    Map3UIController map3UIController;

    /**
     * Controller class for the user interface with the map4.
     */
    Map4UIController map4UIController;


    /**
     * this is true if the UI must show the game's map.
     */
    public boolean isMapActive;


    /**
     * This method starts the GUI.
     * @param primaryStage the primarystage for the GUI
     * @throws Exception if there are some errors on the loading of the GUI.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {


        String cleanedPath = jarPath.replaceAll("adrenalina-1\\.0-SNAPSHOT\\.jar", "");
       resourcesPath = "file://" + cleanedPath + "assets/";

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Adrenalina");
        initGUI();

    }

    //----- METODI DI AGGIORNAMENTO -----//

    /**
     * This method initializes the GUI.
     */
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

    /**
     * Shows the window for choosing the map.
     */
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

    /**
     * Shows the window for choosing the terminator.
     */
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

    /**
     * Shows the window for choosing the champion.
     * @param message the message that must be printed.
     */
    public void showChooseChampion(String message) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/selectchampion.fxml"));
            AnchorPane chooseChampion = (AnchorPane) loader.load();

            SelectChampionController controller = loader.getController();
            controller.out = out;
            controller.activateRadioButtons(message);

            if(message.contains("Terminator")){
                this.primaryStage.setTitle("Scegli un campione per il Terminator!");
            }
            else{
                this.primaryStage.setTitle("Scegli il tuo campione!");
            }

            Scene scene = new Scene(chooseChampion);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        } catch (Exception e) {
        }


    }

    /**
     * Shows the window for choosing the skulls.
     */
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

    /**
     * Shows a standard dialog window.
     * @param message the message that must be shown.
     */
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

    /**
     * SHows the spawn window.
     * @param message the message that must be shown.
     */
    public void showSpawn(String message) {


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/Respawn.fxml"));
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

    /**
     * Shows the window for choosing the steps
     */
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

    /**
     * Shows the window for choosing the direction
     * @param enhanced if true, shows also the "stop" option.
     */
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

    /**
     * Shows the main GUI window.
     * @param message the message that must be shown.
     */
    public void showMainStage(String message) {


        if(message.contains("indicandone il numero")){

            String newMessage = message.replace("indicandone il numero", ".");
            message = newMessage;

        }

        if(message.contains("digitandone il nome")){

            String newMessage = message.replace("digitandone il nome:", "cliccandoci sopra.");
            message = newMessage;

        }

        if(message.contains("per posizionare il Terminator")){

            String newMessage = "Clicca su un cubo munizione (rosso, blu, giallo) per posizionare il terminator nella cella di quel colore.";
            message = newMessage;
        }


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

            if (chosenMap == 2) {

                loader.setLocation(GUI.class.getResource("/map2UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map2UIController = loader.getController();
                map2UIController.out = out;

                map2UIController.setMessage(message);
                map2UIController.parser(message);
                map2UIController.initUI();
                map2UIController.refresh();

                System.out.println(map2UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }

            if (chosenMap == 3) {

                loader.setLocation(GUI.class.getResource("/map3UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map3UIController = loader.getController();
                map3UIController.out = out;

                map3UIController.setMessage(message);
                map3UIController.parser(message);
                map3UIController.initUI();
                map3UIController.refresh();

                System.out.println(map3UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }

            if (chosenMap == 4) {

                loader.setLocation(GUI.class.getResource("/map4UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map4UIController = loader.getController();
                map4UIController.out = out;

                map4UIController.setMessage(message);
                map4UIController.parser(message);
                map4UIController.initUI();
                map4UIController.refresh();

                System.out.println(map4UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * Lights the cells.
     * @param message the message that must be shown.
     */
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

            if (chosenMap == 2) {

                loader.setLocation(GUI.class.getResource("/map2UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map2UIController = loader.getController();
                map2UIController.out = out;

                map2UIController.setMessage("Scegli una cella tra quelle evidenziate cliccandoci sopra.");
                map2UIController.parseChooseCell(message);
                map2UIController.lightCells();
                map2UIController.parser(message);
                map2UIController.initUI();
                map2UIController.refresh();

                System.out.println(map2UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }

            if (chosenMap == 3) {

                loader.setLocation(GUI.class.getResource("/map3UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map3UIController = loader.getController();
                map3UIController.out = out;

                map3UIController.setMessage("Scegli una cella tra quelle evidenziate cliccandoci sopra.");
                map3UIController.parseChooseCell(message);
                map3UIController.lightCells();
                map3UIController.parser(message);
                map3UIController.initUI();
                map3UIController.refresh();

                System.out.println(map3UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }


            if (chosenMap == 4) {

                loader.setLocation(GUI.class.getResource("/map4UI.fxml"));
                AnchorPane UIPane = (AnchorPane) loader.load();

                map4UIController = loader.getController();
                map4UIController.out = out;

                map4UIController.setMessage("Scegli una cella tra quelle evidenziate cliccandoci sopra.");
                map4UIController.parseChooseCell(message);
                map4UIController.lightCells();
                map4UIController.parser(message);
                map4UIController.initUI();
                map4UIController.refresh();

                System.out.println(map4UIController.provaStampa());

                rootLayout = UIPane;

                Scene scene = new Scene(rootLayout);
                this.primaryStage.setScene(scene);
                this.primaryStage.show();

            }


        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    /**
     * SHows the window for choosing the shooting mode.
     */
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


    /**
     * Shows the window for choosing a room colour.
     */
    public void showChooseRoom() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("/chooseRoomColour.fxml"));
            AnchorPane chooseRoom = (AnchorPane) loader.load();

            ChooseRoomController controller = loader.getController();
            controller.out = out;


            Scene scene = new Scene(chooseRoom);
            Stage stage = new Stage();

            stage.setTitle("Scegli il colore di una stanza.");
            stage.setScene(scene);
            stage.show();

            System.out.println("Grafica disegnata correttamente");

        } catch (Exception e) {
            System.out.println("Ho beccato un'eccezione");
            e.printStackTrace();
        }
    }

    /**
     * Shows the window for choosing the powerup for paying.
     * @param message the message that must be shown.
     */
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

    /*
    Shows the window for choosing the extra grenade.
     */
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

    /**
     * Shows the final leaderboard.
     * @param message the message that must be shown.
     */
    public void showWinner(String message) {


        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GUI.class.getResource("/leaderboard.fxml"));
        AnchorPane winner = loader.load();

        LeaderboardController control = loader.getController();

        control.parseMessage(message);

        Scene scene = new Scene(winner);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    } catch (Exception e) {
        e.printStackTrace();
    }


}

    /**
     * The standard update method. It loades different "show" methods depending on the received message.
     * @param message
     */
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

        }

        else if (message.contains("vincitori sono") || message.contains("vincitore è")){

            showWinner(message);

        }

        else if (message.contains("Scegli una stanza tra:")){

            showChooseRoom();

        }

        else if (message.contains("da assegnare al Terminator")){

            showChooseChampion(message);

        }

        else if (isMapActive) {

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



