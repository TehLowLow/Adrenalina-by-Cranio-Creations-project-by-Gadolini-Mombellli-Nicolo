package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowSpawnController implements Initializable {

    public DataOutputStream out;

    @FXML
    public ImageView firstPU;

    public String firstPUName;
    public String secondPUName;

    @FXML
    public ImageView secondPU;

    public ShowSpawnController(){}


    @FXML
    public void selectFirst(){

        try {

            out.writeUTF("0");
            firstPU.setEffect(new Bloom());
            firstPU.getScene().getWindow().hide();
        }
        catch (Exception e){

        }

    }

    @FXML
    public void selectSecond(){

        try {

            out.writeUTF("1");
            secondPU.setEffect(new Bloom());
            secondPU.getScene().getWindow().hide();
        }
        catch (Exception e){

        }

    }

    @FXML
    public void setUp(String messageReceived){

        String[] message = messageReceived.split("~");


        String[] tokens = message[1].split("\\)");

        firstPUName = getPUname(tokens[1]);
        secondPUName = getPUname(tokens[2]);

        System.out.println("Primo pescato: " + firstPUName + '\n');
        System.out.println("Secondo pescato: " + secondPUName + '\n');

        loadImages();

    }


    public String getPUname(String s){
        
        if(s.contains("Raggio Cinetico Blu")){
            return "Raggio Cinetico Blu";
        }

        if(s.contains("Granata Venom Blu")){
            return "Granata Venom Blu";
        }

        if(s.contains("Mirino Blu")){
            return "Mirino Blu";
        }

        if(s.contains("Teletrasporto Blu")){
            return "Teletrasporto Blu";
        }

        if(s.contains("Raggio Cinetico Rosso")){
            return "Raggio Cinetico Rosso";
        }

        if(s.contains("Granata Venom Rossa")){
            return "Granata Venom Rossa";
        }

        if(s.contains("Mirino Rosso")){
            return "Mirino Rosso";
        }

        if(s.contains("Teletrasporto Rosso")){
            return "Teletrasporto Rosso";
        }

        if(s.contains("Raggio Cinetico Giallo")){
            return "Raggio Cinetico Giallo";
        }

        if(s.contains("Granata Venom Gialla")){
            return "Granata Venom Gialla";
        }

        if(s.contains("Mirino Giallo")){
            return "Mirino Giallo";
        }

        if(s.contains("Teletrasporto Giallo")){
            return "Teletrasporto Giallo";
        }

        return "Raggio Cinetico Blu";
        
    }

    @FXML
    public void loadImages(){

        if(firstPUName.equalsIgnoreCase("Raggio Cinetico Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_028.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Mirino Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_025.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Granata Venom Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_022.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Teletrasporto Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0211.png");
            firstPU.setImage(image);

        }
        
        if(firstPUName.equalsIgnoreCase("Raggio Cinetico Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_029.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Mirino Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_026.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Granata Venom Rossa")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_023.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Teletrasporto Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0212.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Raggio Cinetico Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0210.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Mirino Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_027.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Granata Venom Gialla")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_024.png");
            firstPU.setImage(image);

        }

        if(firstPUName.equalsIgnoreCase("Teletrasporto Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0213.png");
            firstPU.setImage(image);

        }

        //-------//


        if(secondPUName.equalsIgnoreCase("Raggio Cinetico Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_028.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Mirino Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_025.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Granata Venom Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_022.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Teletrasporto Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0211.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Raggio Cinetico Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_029.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Mirino Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_026.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Granata Venom Rossa")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_023.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Teletrasporto Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0212.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Raggio Cinetico Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0210.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Mirino Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_027.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Granata Venom Gialla")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_024.png");
            secondPU.setImage(image);

        }

        if(secondPUName.equalsIgnoreCase("Teletrasporto Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0213.png");
            secondPU.setImage(image);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
