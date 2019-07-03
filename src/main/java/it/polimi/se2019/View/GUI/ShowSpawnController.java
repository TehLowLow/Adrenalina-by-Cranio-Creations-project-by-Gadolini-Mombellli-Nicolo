package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShowSpawnController implements Initializable {

    public DataOutputStream out;

    @FXML
    public ImageView firstPU;

    @FXML
    public ImageView secondPU;

    @FXML
    public ImageView thirdPU;

    @FXML
    public ImageView fourthPU;

    public String firstPUName = "null";
    public String secondPUName = "null";
    public String thirdPUName = "null";
    public String fourthPUName = "null";

    public ShowSpawnController(){}


    @FXML
    public void selectFirst(){

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "0";
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF("0");
        }
            firstPU.setEffect(new Bloom());
            firstPU.getScene().getWindow().hide();
        }
        catch (Exception e){

        }

    }

    @FXML
    public void selectSecond(){

        try {


            if(GUI.RMI) {

                GUI.RMIAnswer = "1";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("1");
        }
            secondPU.setEffect(new Bloom());
            secondPU.getScene().getWindow().hide();
        }
        catch (Exception e){

        }

    }

    @FXML
    public void selectThird(){

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "2";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("2");
        }
            thirdPU.setEffect(new Bloom());
            thirdPU.getScene().getWindow().hide();
        }
        catch (Exception e){

        }

    }

    @FXML
    public void selectFourth(){

        try {


            if(GUI.RMI) {

                GUI.RMIAnswer = "3";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("3");
        }
            fourthPU.setEffect(new Bloom());
            fourthPU.getScene().getWindow().hide();
        }
        catch (Exception e){

        }

    }

    @FXML
    public void setUp(String messageReceived){

        String[] message = messageReceived.split("~");


        String[] tokens = message[1].split("\\)");
        
        CopyOnWriteArrayList<String> powerups = new CopyOnWriteArrayList<String>(Arrays.asList(tokens));

        firstPUName = getPUname(powerups.get(1));
        
        if(powerups.size()>=3) {
            secondPUName = getPUname(powerups.get(2));
        }

        if(powerups.size()>=4){
            thirdPUName = getPUname(powerups.get(3));
        }

        if(powerups.size()>=5){
            thirdPUName = getPUname(powerups.get(4));
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
        
        //-------------------

        if(thirdPUName.equalsIgnoreCase("Raggio Cinetico Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_028.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Mirino Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_025.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Granata Venom Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_022.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Teletrasporto Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0211.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Raggio Cinetico Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_029.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Mirino Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_026.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Granata Venom Rossa")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_023.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Teletrasporto Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0212.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Raggio Cinetico Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0210.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Mirino Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_027.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Granata Venom Gialla")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_024.png");
            thirdPU.setImage(image);

        }

        if(thirdPUName.equalsIgnoreCase("Teletrasporto Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0213.png");
            thirdPU.setImage(image);

        }
        
        //---------

        if(fourthPUName.equalsIgnoreCase("Raggio Cinetico Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_028.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Mirino Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_025.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Granata Venom Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_022.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Teletrasporto Blu")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0211.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Raggio Cinetico Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_029.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Mirino Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_026.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Granata Venom Rossa")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_023.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Teletrasporto Rosso")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0212.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Raggio Cinetico Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0210.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Mirino Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_027.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Granata Venom Gialla")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_024.png");
            fourthPU.setImage(image);

        }

        if(fourthPUName.equalsIgnoreCase("Teletrasporto Giallo")){

            Image image = new Image("/assets/Powerups/AD_powerups_IT_0213.png");
            fourthPU.setImage(image);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
