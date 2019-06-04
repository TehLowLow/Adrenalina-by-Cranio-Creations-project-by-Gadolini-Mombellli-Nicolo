package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PayPowerUpController implements Initializable {

    DataOutputStream out;

    @FXML

    public ImageView pu0;

    @FXML
    public ImageView pu1;

    @FXML
    public ImageView pu2;
    
    
    public String pu0Name = "empty";
    public String pu1Name = "empty";
    public String pu2Name = "empty";
    
    public void setUp(String messageReceived){
        
        String[] message = messageReceived.split("~");

        String[] tokens = message[1].split("\\)");

        ArrayList <String> tokensArl = new ArrayList<>(Arrays.asList(tokens));
        
        if(tokensArl.size()>=2){

           pu0Name = getPUname(tokens[1]);

        }


        if(tokensArl.size()>=3){

            pu1Name = getPUname(tokens[2]);

        }

        if(tokensArl.size()==4){

            pu2Name = getPUname(tokens[3]);

        }
        
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

        if(pu0Name.equalsIgnoreCase("Raggio Cinetico Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_028.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Mirino Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_025.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Granata Venom Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_022.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Teletrasporto Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0211.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Raggio Cinetico Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_029.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Mirino Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_026.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Granata Venom Rossa")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_023.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Teletrasporto Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0212.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Raggio Cinetico Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0210.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Mirino Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_027.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Granata Venom Gialla")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_024.png");
            pu0.setImage(image);

        }

        if(pu0Name.equalsIgnoreCase("Teletrasporto Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0213.png");
            pu0.setImage(image);

        }

        //-------//


        if(pu1Name.equalsIgnoreCase("Raggio Cinetico Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_028.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Mirino Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_025.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Granata Venom Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_022.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Teletrasporto Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0211.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Raggio Cinetico Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_029.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Mirino Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_026.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Granata Venom Rossa")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_023.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Teletrasporto Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0212.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Raggio Cinetico Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0210.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Mirino Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_027.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Granata Venom Gialla")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_024.png");
            pu1.setImage(image);

        }

        if(pu1Name.equalsIgnoreCase("Teletrasporto Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0213.png");
            pu1.setImage(image);

        }
        
        ///////

        if(pu2Name.equalsIgnoreCase("Raggio Cinetico Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_028.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Mirino Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_025.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Granata Venom Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_022.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Teletrasporto Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0211.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Raggio Cinetico Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_029.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Mirino Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_026.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Granata Venom Rossa")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_023.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Teletrasporto Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0212.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Raggio Cinetico Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0210.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Mirino Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_027.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Granata Venom Gialla")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_024.png");
            pu2.setImage(image);

        }

        if(pu2Name.equalsIgnoreCase("Teletrasporto Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0213.png");
            pu2.setImage(image);

        }

    }


    @FXML
    public void choosePU0(){

        try {
            out.writeUTF("0");
            pu1.getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    public void choosePU1(){

        try {
            out.writeUTF("1");
            pu1.getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    public void choosePU2(){

        try {
            out.writeUTF("2");
            pu1.getScene().getWindow().hide();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }


    public PayPowerUpController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }



}
