package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Arrays;

public class LeaderboardController {


    @FXML
    Text firstPlayer;

    @FXML
    Text secondPlayer;

    @FXML
    Text thirdPlayer;

    @FXML
    Text fourthPlayer;

    @FXML
    Text fifthPlayer;

   @FXML
   Text firstPoint;

   @FXML
   Text secondPoint;

   @FXML
   Text thirdPoint;

   @FXML
   Text fourthPoint;

   @FXML
   Text fifthPoint;

   @FXML
   Text firstMB;

   @FXML
   Text secondMB;

   @FXML
   Text thirdMB;

   @FXML
   Text fourthMB;

   @FXML
   Text fifthMB;

   public void parseMessage(String message) {

       String[] firstSplit = message.split("Classifica Finale:");

       String results = firstSplit[1];

       String resultsCleaned = results.replaceAll("\r", "").replaceAll("\n", "");

       System.out.println(resultsCleaned);

       String[] splitted = resultsCleaned.split(": |- ");

       CopyOnWriteArrayList<String> winners = new CopyOnWriteArrayList<String>();
       CopyOnWriteArrayList <String> score = new CopyOnWriteArrayList<String>();
       CopyOnWriteArrayList<String> mbTrack = new CopyOnWriteArrayList<String>();

       CopyOnWriteArrayList<String> tokenList = new CopyOnWriteArrayList<String>(Arrays.asList(splitted));


       for(String token : tokenList){


           if(token.contains("Nome")){

               tokenList.remove(token);

           }

           if(token.contains("Totale")){

               tokenList.remove(token);

           }

           if(token.contains("mortale")){

               tokenList.remove(token);

           }


       }

       winners.add(tokenList.get(0));
       firstPlayer.setText(tokenList.get(0));
       firstPlayer.setVisible(true);
       score.add(tokenList.get(1));
       firstPoint.setText(tokenList.get(1));
       firstPoint.setVisible(true);
       mbTrack.add(tokenList.get(2));
       firstMB.setText(tokenList.get(2));
       firstMB.setVisible(true);

       winners.add(tokenList.get(3));
       secondPlayer.setText(tokenList.get(3));
       secondPlayer.setVisible(true);
       score.add(tokenList.get(4));
       secondPoint.setText(tokenList.get(4));
       secondPoint.setVisible(true);
       mbTrack.add(tokenList.get(5));
       secondMB.setText(tokenList.get(5));
       secondMB.setVisible(true);


       winners.add(tokenList.get(6));
       thirdPlayer.setText(tokenList.get(6));
       thirdPlayer.setVisible(true);
       score.add(tokenList.get(7));
       thirdPoint.setText(tokenList.get(7));
       thirdPoint.setVisible(true);
       mbTrack.add(tokenList.get(8));
       thirdMB.setText(tokenList.get(8));
       thirdMB.setVisible(true);

       if(tokenList.size()==12){

           winners.add(tokenList.get(9));
           fourthPlayer.setText(tokenList.get(9));
           fourthPlayer.setVisible(true);
           score.add(tokenList.get(10));
           fourthPoint.setText(tokenList.get(10));
           fourthPoint.setVisible(true);
           mbTrack.add(tokenList.get(11));
           fourthMB.setText(tokenList.get(11));
           fourthMB.setVisible(true);

       }

       if(tokenList.size()==15){

           winners.add(tokenList.get(12));
           fifthPlayer.setText(tokenList.get(12));
           fifthPlayer.setVisible(true);
           score.add(tokenList.get(13));
           fifthPoint.setText(tokenList.get(13));
           fifthPoint.setVisible(true);
           mbTrack.add(tokenList.get(14));
           fifthMB.setText(tokenList.get(14));
           fifthMB.setVisible(true);
       }




   }




   public LeaderboardController(){

   }


}
