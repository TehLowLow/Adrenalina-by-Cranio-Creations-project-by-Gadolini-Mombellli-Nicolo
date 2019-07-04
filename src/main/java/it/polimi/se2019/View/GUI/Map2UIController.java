package it.polimi.se2019.View.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Map2UIController implements Initializable {

    public DataOutputStream out;


    /*
    ------- VARIABILI CHE SALVANO LO STATO DELLA PARTITA CORRENTE --------
     */

    //Dati giocatore
    public String nickname;
    public String championName;
    public String score;
    public String firstPlayer;

    //Posizione giocatore
    String colour;
    String cellName;


    //Armi giocatore
    String weapon1;
    String loaded1;

    String weapon2;
    String loaded2;

    String weapon3;
    String loaded3;

    //PU giocatore

    String pu1;
    String pu2;
    String pu3;

    //Munizioni giocatore

    String redCubes;
    String yellowCubes;
    String blueCubes;

    //Loot posizionati

    String blueLoot1;
    String blueLoot2;

    String greenLoot;

    String redLoot;

    String yellowLoot1;
    String yellowLoot2;
    String yellowLoot3;

    String whiteLoot;

    //Armi delle celle
    String blueWeapon1name;
    String blueWeapon2name;
    String blueWeapon3name;

    String redWeapon1name;
    String redWeapon2name;
    String redWeapon3name;

    String yellowWeapon1name;
    String yellowWeapon2name;
    String yellowWeapon3name;

    //Nomi di chi ha fatto l'n-esimo danno:

    String damage1Name = "empty";
    String damage2Name = "empty";
    String damage3Name = "empty";
    String damage4Name = "empty";
    String damage5Name = "empty";
    String damage6Name = "empty";
    String damage7Name = "empty";
    String damage8Name = "empty";
    String damage9Name = "empty";
    String damage10Name = "empty";
    String damage11Name = "empty";
    String damage12Name = "empty";

    String[] damagesTaken = {damage1Name, damage2Name, damage3Name, damage4Name, damage5Name, damage6Name, damage7Name, damage8Name, damage9Name, damage10Name, damage11Name, damage12Name};

    //Valore PB
    String playerboardValue;

    //MortalBlowTrack

    String token1Name = "empty";
    String token2Name = "empty";
    String token3Name = "empty";
    String token4Name = "empty";
    String token5Name = "empty";
    String token6Name = "empty";
    String token7Name = "empty";
    String token8Name = "empty";


    //Markers

    String marker1Name = "empty";
    String marker2Name = "empty";
    String marker3Name = "empty";
    String marker4Name = "empty";
    String marker5Name = "empty";
    String marker6Name = "empty";
    String marker7Name = "empty";
    String marker8Name = "empty";
    String marker9Name = "empty";
    String marker10Name = "empty";
    String marker11Name = "empty";
    String marker12Name = "empty";

    String[] markersTaken = {marker1Name, marker2Name, marker3Name, marker4Name, marker5Name, marker6Name, marker7Name, marker8Name, marker9Name, marker10Name, marker11Name, marker12Name};

    //Posizione e nome altri giocatori

    String dozerUser = "empty";
    String destructorUser = "empty";
    String violettaUser = "empty";
    String sprogUser = "empty";
    String bansheeUser = "empty";

    String dozerRoomColour = "empty";
    String destructorRoomColour = "empty";
    String violettaRoomColour = "empty";
    String sprogRoomColour = "empty";
    String bansheeRoomColour = "empty";

    String dozerRoom = "empty";
    String destructorRoom = "empty";
    String violettaRoom = "empty";
    String sprogRoom = "empty";
    String bansheeRoom = "empty";

    //Quadrati che attivano le celle

    String blueSpawnCellIndex = "empty";
    String blueLootCell1Index = "empty";
    String blueLootCell2Index = "empty";

    String redSpawnCellIndex = "empty";
    String redLootCellIndex = "empty";


    String yellowSpawnCellIndex = "empty";
    String yellowLootCell1Index = "empty";
    String yellowLootCell2Index = "empty";
    String yellowLootCell3Index = "empty";

    String whiteLootCellIndex = "empty";
    String greenLootCellIndex= "empty";




    /*
    -----------VARIABILI ASSOLUTE PER LA POSIZIONE DEI GIOCATORI--------------

     */

    //Stanza blu

    ///spawnCell

    public int blueSpawnCellDestructorX = 462;
    public int blueSpawnCellDestructorY = 211;

    public int blueSpawnCellVioletX = 508;
    public int blueSpawnCellVioletY = 238;

    public int blueSpawnCellDozerX = 502;
    public int blueSpawnCellDozerY = 188;

    public int blueSpawnCellSprogX = 423;
    public int blueSpawnCellSprogY = 178;

    public int blueSpawnCellBansheeX = 423;
    public int blueSpawnCellBansheeY = 238;


    //lootCell1

    public int blueLootCell1DestructorX = 231;
    public int blueLootCell1DestructorY = 187;

    public int blueLootCell1VioletX = 237;
    public int blueLootCell1VioletY = 237;

    public int blueLootCell1DozerX = 143;
    public int blueLootCell1DozerY = 222;

    public int blueLootCell1SprogX = 210;
    public int blueLootCell1SprogY = 164;

    public int blueLootCell1BansheeX = 193;
    public int blueLootCell1BansheeY = 235;

    //lootCell2

    public int blueLootCell2DestructorX = 329;
    public int blueLootCell2DestructorY = 162;

    public int blueLootCell2VioletX = 375;
    public int blueLootCell2VioletY = 180;

    public int blueLootCell2DozerX = 329;
    public int blueLootCell2DozerY = 162;

    public int blueLootCell2SprogX = 288;
    public int blueLootCell2SprogY = 180;

    public int blueLootCell2BansheeX = 375;
    public int blueLootCell2BansheeY = 238;

    // Stanza rossa

    ///Spawn cell

    public int redSpawnCellDestructorX = 151;
    public int redSpawnCellDestructorY = 315;

    public int redSpawnCellVioletX = 203;
    public int redSpawnCellVioletY = 332;

    public int redSpawnCellDozerX = 210;
    public int redSpawnCellDozerY = 384;

    public int redSpawnCellSprogX = 151;
    public int redSpawnCellSprogY = 361;

    public int redSpawnCellBansheeX = 157;
    public int redSpawnCellBansheeY = 416;

    //LootCell


    public int redLootCellDestructorX = 360;
    public int redLootCellDestructorY = 315;

    public int redLootCellVioletX = 314;
    public int redLootCellVioletY = 315;

    public int redLootCellDozerX = 301;
    public int redLootCellDozerY = 407;

    public int redLootCellSprogX = 360;
    public int redLootCellSprogY = 361;

    public int redLootCellBansheeX = 360;
    public int redLootCellBansheeY = 407;


    //Stanza gialla

    //SpawnCell

    public int yellowSpawnCellDestructorX = 586;
    public int yellowSpawnCellDestructorY = 470;

    public int yellowSpawnCellVioletX = 637;
    public int yellowSpawnCellVioletY = 483;

    public int yellowSpawnCellDozerX = 591;
    public int yellowSpawnCellDozerY = 567;

    public int yellowSpawnCellSprogX = 591;
    public int yellowSpawnCellSprogY = 516;

    public int yellowSpawnCellBansheeX = 651;
    public int yellowSpawnCellBansheeY = 567;

    //LootCell2

    public int yellowLootCell2DestructorX = 608;
    public int yellowLootCell2DestructorY = 315;

    public int yellowLootCell2VioletX = 581;
    public int yellowLootCell2VioletY = 357;

    public int yellowLootCell2DozerX = 654;
    public int yellowLootCell2DozerY = 329;

    public int yellowLootCell2SprogX = 647;
    public int yellowLootCell2SprogY = 418;

    public int yellowLootCell2BansheeX = 581;
    public int yellowLootCell2BansheeY = 407;

    //LootCell1

    public int yellowLootCell1DestructorX = 440;
    public int yellowLootCell1DestructorY = 320;

    public int yellowLootCell1VioletX = 493;
    public int yellowLootCell1VioletY = 320;

    public int yellowLootCell1DozerX = 449;
    public int yellowLootCell1DozerY = 370;

    public int yellowLootCell1SprogX = 502;
    public int yellowLootCell1SprogY = 372;

    public int yellowLootCell1BansheeX = 509;
    public int yellowLootCell1BansheeY = 413;

    //LootCell3

    public int yellowLootCell3DestructorX = 436;
    public int yellowLootCell3DestructorY = 471;

    public int yellowLootCell3VioletX = 479;
    public int yellowLootCell3VioletY = 472;

    public int yellowLootCell3DozerX = 525;
    public int yellowLootCell3DozerY = 480;

    public int yellowLootCell3SprogX = 516;
    public int yellowLootCell3SprogY = 531;

    public int yellowLootCell3BansheeX = 502;
    public int yellowLootCell3BansheeY = 579;

    //Stanza bianca

    //LootCell


    public int whiteLootCellDestructorX = 327;
    public int whiteLootCellDestructorY = 488;

    public int whiteLootCellVioletX = 328;
    public int whiteLootCellVioletY = 534;

    public int whiteLootCellDozerX = 373;
    public int whiteLootCellDozerY = 506;

    public int whiteLootCellSprogX = 368;
    public int whiteLootCellSprogY = 567;

    public int whiteLootCellBansheeX = 278;
    public int whiteLootCellBansheeY = 495;
    
    //Stanza verde

    public int greenLootCellDestructorX = 586;
    public int greenLootCellDestructorY = 153;

    public int greenLootCellVioletX = 631;
    public int greenLootCellVioletY = 153;

    public int greenLootCellDozerX = 586;
    public int greenLootCellDozerY = 201;

    public int greenLootCellSprogX = 654;
    public int greenLootCellSprogY = 196;

    public int greenLootCellBansheeX = 586;
    public int greenLootCellBansheeY = 248;
    



    /*
    -------- ELEMENTI DELLA GUI --------
     */

    @FXML
    public Label terminal;

    @FXML
    public Text nicknameText;

    @FXML
    public Text scoreText;

    //

    @FXML
    public Text blueCubesText;

    @FXML
    public Text redCubesText;

    @FXML
    public Text yellowCubesText;

    //

    @FXML
    public ImageView playerboardImage;

    @FXML
    public ImageView damage1;
    @FXML
    public ImageView damage2;
    @FXML
    public ImageView damage3;
    @FXML
    public ImageView damage4;
    @FXML
    public ImageView damage5;
    @FXML
    public ImageView damage6;
    @FXML
    public ImageView damage7;
    @FXML
    public ImageView damage8;
    @FXML
    public ImageView damage9;
    @FXML
    public ImageView damage10;
    @FXML
    public ImageView damage11;
    @FXML
    public ImageView damage12;

    @FXML
    public ImageView PBSkull1;

    @FXML
    public ImageView PBSkull2;

    @FXML
    public ImageView PBSkull3;

    @FXML
    public ImageView PBSkull4;

    @FXML
    public ImageView PBSkull5;

    @FXML
    public ImageView PBSkull6;

    @FXML
    public ImageView marker1Image;
    @FXML
    public ImageView marker2Image;
    @FXML
    public ImageView marker3Image;
    @FXML
    public ImageView marker4Image;
    @FXML
    public ImageView marker5Image;
    @FXML
    public ImageView marker6Image;
    @FXML
    public ImageView marker7Image;
    @FXML
    public ImageView marker8Image;
    @FXML
    public ImageView marker9Image;
    @FXML
    public ImageView marker10Image;
    @FXML
    public ImageView marker11Image;
    @FXML
    public ImageView marker12Image;


    //

    @FXML
    public ImageView weapon1Image;

    @FXML
    public ImageView weapon2Image;

    @FXML
    public ImageView weapon3Image;

    //

    @FXML
    public ImageView pu1Image;

    @FXML
    public ImageView pu2Image;

    @FXML
    public ImageView pu3Image;

    //

    @FXML
    public ImageView blueLoot1Tile;

    @FXML
    public ImageView blueLoot2Tile;

    @FXML
    public ImageView redLootTile;

    @FXML
    public ImageView yellowLoot1Tile;

    @FXML
    public ImageView yellowLoot2Tile;

    @FXML
    public ImageView yellowLoot3Tile;

    @FXML
    public ImageView whiteLootTile;

    @FXML
    public ImageView greenLootTile;

    //

    @FXML
    public ImageView blueWeapon1;

    @FXML
    public ImageView blueWeapon2;

    @FXML
    public ImageView blueWeapon3;

    @FXML
    public ImageView redWeapon1;

    @FXML
    public ImageView redWeapon2;

    @FXML
    public ImageView redWeapon3;

    @FXML
    public ImageView yellowWeapon1;

    @FXML
    public ImageView yellowWeapon2;

    @FXML
    public ImageView yellowWeapon3;

    //

    @FXML
    public ImageView mbToken1;

    @FXML
    public Text mb1Overkill;

    @FXML
    public ImageView mbToken2;

    @FXML
    public Text mb2Overkill;

    @FXML
    public ImageView mbToken3;

    @FXML
    public Text mb3Overkill;

    @FXML
    public ImageView mbToken4;

    @FXML
    public Text mb4Overkill;

    @FXML
    public ImageView mbToken5;

    @FXML
    public Text mb5Overkill;

    @FXML
    public ImageView mbToken6;

    @FXML
    public Text mb6Overkill;

    @FXML
    public ImageView mbToken7;

    @FXML
    public Text mb7Overkill;

    @FXML
    public ImageView mbToken8;

    @FXML
    public Text mb8Overkill;

    @FXML
    public ImageView destructorIcon;

    @FXML
    public ImageView dozerIcon;

    @FXML
    public ImageView violetIcon;

    @FXML
    public ImageView bansheeIcon;

    @FXML
    public ImageView sprogIcon;

    //------------- QUADRATI

    @FXML
    public Rectangle greenLootCellSquare;

    @FXML
    public Rectangle blueSpawnCellSquare;

    @FXML
    public Rectangle blueLootCell1Square;

    @FXML
    public Rectangle blueLootCell2Square;

    @FXML
    public Rectangle redSpawnCellSquare;

    @FXML
    public Rectangle redLootCellSquare;

    @FXML
    public Rectangle yellowSpawnCellSquare;

    @FXML
    public Rectangle yellowLootCell1Square;

    @FXML
    public Rectangle yellowLootCell2Square;

    @FXML
    public Rectangle yellowLootCell3Square;


    @FXML
    public Rectangle whiteLootCellSquare;


    @FXML
    public ImageView firstplayer;


//--------------------------------------------------------------------------

    public Map2UIController() {
    }

    public void initRoomSquares() {

        blueSpawnCellIndex = "empty";
        blueLootCell1Index = "empty";
        blueLootCell2Index = "empty";

        redSpawnCellIndex = "empty";
        redLootCellIndex = "empty";

        yellowSpawnCellIndex = "empty";
        yellowLootCell1Index = "empty";
        yellowLootCell2Index = "empty";
        yellowLootCell3Index = "empty";

        whiteLootCellIndex = "empty";

        greenLootCellIndex = "empty";

    }

    @FXML
    public void parseChooseCell(String messageReceived) {

        initRoomSquares();

        String[] message = messageReceived.split("~");

        String[] messageTokens = message[1].split("\\)");

        ArrayList<String> messageARL = new ArrayList<String>(Arrays.asList(messageTokens));

        int size = messageARL.size();

        for (int i = 1; i < size; i++) {

            if (messageTokens[i].contains("blu")) {

                if (messageTokens[i].contains("spawnCell")) {
                    blueSpawnCellIndex = "" + (i - 1);
                }

                if (messageTokens[i].contains("lootCell1")) {
                    blueLootCell1Index = "" + (i - 1);
                }

                if (messageTokens[i].contains("lootCell2")) {
                    blueLootCell2Index = "" + (i - 1);
                }


            }

            if (messageTokens[i].contains("rosso")) {

                if (messageTokens[i].contains("spawnCell")) {
                    redSpawnCellIndex = "" + (i - 1);
                }

                if (messageTokens[i].contains("lootCell")) {
                    redLootCellIndex = "" + (i - 1);
                }

            }

            if (messageTokens[i].contains("giallo")) {

                if (messageTokens[i].contains("spawnCell")) {
                    yellowSpawnCellIndex = "" + (i - 1);
                }

                if (messageTokens[i].contains("lootCell1")) {
                    yellowLootCell1Index = "" + (i - 1);
                }

                if (messageTokens[i].contains("lootCell2")) {
                    yellowLootCell2Index = "" + (i - 1);
                }

                if (messageTokens[i].contains("lootCell3")) {
                    yellowLootCell3Index = "" + (i - 1);
                }

            }

            if (messageTokens[i].contains("bianco")) {

                if (messageTokens[i].contains("lootCell")) {
                    whiteLootCellIndex = "" + (i - 1);
                }

            }

            if (messageTokens[i].contains("verde")) {

                if (messageTokens[i].contains("lootCell")) {
                    greenLootCellIndex = "" + (i - 1);
                }

            }


        }
    }

    @FXML
    public void lightCells() {

        blueSpawnCellSquare.setDisable(false);
        blueLootCell1Square.setDisable(false);
        blueLootCell2Square.setDisable(false);
        redSpawnCellSquare.setDisable(false);
        redLootCellSquare.setDisable(false);
        yellowSpawnCellSquare.setDisable(false);
        yellowLootCell1Square.setDisable(false);
        yellowLootCell2Square.setDisable(false);
        yellowLootCell3Square.setDisable(false);
        whiteLootCellSquare.setDisable(false);
        greenLootCellSquare.setDisable(false);

        if (blueSpawnCellIndex.equalsIgnoreCase("empty")) {

            blueSpawnCellSquare.setOpacity(0.55);
        }

        if (blueLootCell1Index.equalsIgnoreCase("empty")) {

            blueLootCell1Square.setOpacity(0.55);
        }
        
        if (blueLootCell2Index.equalsIgnoreCase("empty")) {
            
            blueLootCell2Square.setOpacity(0.55);
        }

        if (redSpawnCellIndex.equalsIgnoreCase("empty")) {


            redSpawnCellSquare.setOpacity(0.55);

        }

        if (redLootCellIndex.equalsIgnoreCase("empty")) {


            redLootCellSquare.setOpacity(0.55);

        }

        if (yellowSpawnCellIndex.equalsIgnoreCase("empty")) {

            yellowSpawnCellSquare.setOpacity(0.55);
        }

        if (yellowLootCell1Index.equalsIgnoreCase("empty")) {


            yellowLootCell1Square.setOpacity(0.55);

        }

        if (yellowLootCell2Index.equalsIgnoreCase("empty")) {


            yellowLootCell2Square.setOpacity(0.55);

        }

        if (yellowLootCell3Index.equalsIgnoreCase("empty")) {


            yellowLootCell3Square.setOpacity(0.55);

        }

        if (whiteLootCellIndex.equalsIgnoreCase("empty")) {


            whiteLootCellSquare.setOpacity(0.55);

        }

        if (greenLootCellIndex.equalsIgnoreCase("empty")) {


            greenLootCellSquare.setOpacity(0.55);

        }


    }

    @FXML
    public void restoreCells() {


        blueSpawnCellSquare.setDisable(true);
        blueSpawnCellSquare.setOpacity(0);

        blueLootCell1Square.setDisable(true);
        blueLootCell1Square.setOpacity(0);

        blueLootCell2Square.setDisable(true);
        blueLootCell2Square.setOpacity(0);

        redSpawnCellSquare.setDisable(true);
        redSpawnCellSquare.setOpacity(0);

        redLootCellSquare.setDisable(true);
        redLootCellSquare.setOpacity(0);

        yellowSpawnCellSquare.setDisable(true);
        yellowSpawnCellSquare.setOpacity(0);

        yellowLootCell1Square.setDisable(true);
        yellowLootCell1Square.setOpacity(0);

        yellowLootCell2Square.setDisable(true);
        yellowLootCell2Square.setOpacity(0);

        yellowLootCell3Square.setDisable(true);
        yellowLootCell3Square.setOpacity(0);

        whiteLootCellSquare.setDisable(true);
        whiteLootCellSquare.setOpacity(0);

        greenLootCellSquare.setDisable(true);
        greenLootCellSquare.setOpacity(0);

    }


    @FXML
    public void setMessage(String message) {


        if (message.contains("~")) {
            String copy = message;
            String[] tokens = copy.split("~");
            terminal.setText(tokens[1]);
        } else {
            terminal.setText(message);
        }

    }


    public void parser(String message) {

        //Divido la CLI dalla stringa che rappresenta il gioco.
        String[] firstSplit = message.split("\\$");
        String gameInfo = firstSplit[1];

        //Divido gameInfo nelle sezioni che la compongono.

        String sections[] = gameInfo.split("\\*");

        String playerInfo = sections[0];

        String playerPosition = sections[1];

        String playerWeapon = sections[2];

        String playerPU = sections[3];

        String playerAmmo = sections[4];

        String mapLoot = sections[5];

        String mapWeapons = sections[6];

        String damageToken = sections[7];

        String pbValue = sections[8];

        String mbTrack = sections[9];

        String markerToken = sections[10];

        String playersPositions = sections[11];

        // --SEZIONE 1: INFORMAZIONI DEL GIOCATORE -- //

        String infoList[] = playerInfo.split("@");

        nickname = infoList[0];
        championName = infoList[1];
        score = infoList[2];
        firstPlayer = infoList[3];

        // -- SEZIONE 2: POSIZIONE DEL GIOCATORE -- //

        String positionList[] = playerPosition.split("@");

        colour = positionList[0];
        cellName = positionList[1];

        // -- SEZIONE 3: ARMI DEL GIOCATORE -- //

        weapon1 = "No weapon";
        loaded1 = "0";

        weapon2 = "No weapon";
        loaded2 = "0";

        weapon3 = "No weapon";
        loaded3 = "0";

        if (!playerWeapon.contains("noweapon")) {

            String[] weaponList = playerWeapon.split("@");

            ArrayList<String> weaponAList = new ArrayList<String>(Arrays.asList(weaponList));

            if (weaponAList.size() >= 2) {

                weapon1 = weaponList[0];
                loaded1 = weaponList[1];

            }

            if (weaponAList.size() >= 4) {

                weapon2 = weaponList[2];
                loaded2 = weaponList[3];

            }

            if (weaponAList.size() == 6) {

                weapon3 = weaponList[4];
                loaded3 = weaponList[5];

            }

        }

        // -- SEZIONE 4: POWERUP DEL GIOCATORE -- //

        pu1 = "no pu";
        pu2 = "no pu";
        pu3 = "no pu";

        if (!playerPU.contains("nopowerups")) {


            String[] arrayPu = playerPU.split("@");

            ArrayList<String> PUList = new ArrayList<String>(Arrays.asList(arrayPu));

            if (PUList.size() >= 1) {

                pu1 = arrayPu[0];

            }

            if (PUList.size() >= 2) {

                pu2 = arrayPu[1];

            }

            if (PUList.size() == 3) {

                pu3 = arrayPu[2];

            }


        }

        // -- SEZIONE 5: MUNIZIONI DEL GIOCATORE -- //

        String[] ammoList = playerAmmo.split("@");

        blueCubes = ammoList[0];
        yellowCubes = ammoList[1];
        redCubes = ammoList[2];


        // -- SEZIONE 6: LOOT SULLA MAPPA -- //

        String[] rooms = mapLoot.split("#");

        String blueRoom = rooms[0];
        String redRoom = rooms[1];
        String yellowRoom = rooms[2];
        String whiteRoom = rooms[3];
        String greenRoom = rooms[4];

        String[] blueLoots = blueRoom.split("@");
        blueLoot1 = blueLoots[0];
        blueLoot2 = blueLoots[1];

        String[] redLoots = redRoom.split("@");
        redLoot = redLoots[0];

        String[] yellowLoots = yellowRoom.split("@");
        yellowLoot1 = yellowLoots[0];
        yellowLoot2 = yellowLoots[1];
        yellowLoot3 = yellowLoots[2];

        String[] whiteLoots = whiteRoom.split("@");
        whiteLoot = whiteLoots[0];
        
        String[] greenLoots = greenRoom.split("@");
        greenLoot = greenLoots[0];

        // -- SEZIONE 7: ARMI DELLA MAPPA -- //

        String[] weaponRooms = mapWeapons.split("#");

        blueRoom = weaponRooms[0];
        redRoom = weaponRooms[1];
        yellowRoom = weaponRooms[2];

        String[] blueWeapons = blueRoom.split("@");
        blueWeapon1name = blueWeapons[0];
        blueWeapon2name = blueWeapons[1];
        blueWeapon3name = blueWeapons[2];

        String[] redWeapons = redRoom.split("@");
        redWeapon1name = redWeapons[0];
        redWeapon2name = redWeapons[1];
        redWeapon3name = redWeapons[2];


        String[] yellowWeapons = yellowRoom.split("@");
        yellowWeapon1name = yellowWeapons[0];
        yellowWeapon2name = yellowWeapons[1];
        yellowWeapon3name = yellowWeapons[2];


        // -- SEZIONE 8: DANNI SUBITI DAL GIOCATORE -- //

        String[] damageString = damageToken.split("@");

        int damageNumber = Integer.parseInt(damageString[0]);

        int i = 0;

        for (i = 0; i < damageNumber; i++) {

            damagesTaken[i] = damageString[i + 1];

        }

        damage1Name = damagesTaken[0];
        damage2Name = damagesTaken[1];
        damage3Name = damagesTaken[2];
        damage4Name = damagesTaken[3];
        damage5Name = damagesTaken[4];
        damage6Name = damagesTaken[5];
        damage7Name = damagesTaken[6];
        damage8Name = damagesTaken[7];
        damage9Name = damagesTaken[8];
        damage10Name = damagesTaken[9];
        damage11Name = damagesTaken[10];
        damage12Name = damagesTaken[11];

        // -- SEZIONE 9: VALORE DELLA PLAYERBOARD -- //


        playerboardValue = pbValue;

        // -- SEZIONE 10: TRACCIATO COLPO MORTALE -- //

        String[] mbTokens = mbTrack.split("@");

        int skullNumber = Integer.parseInt(mbTokens[0]);

        if (skullNumber == 5) {
            mbToken1.setVisible(false);
            mbToken2.setVisible(false);
            mbToken3.setVisible(false);

            if (mbTokens[1].equalsIgnoreCase("1")) {
                mb4Overkill.setVisible(true);
            }

            token4Name = mbTokens[2];

            if (mbTokens[3].equalsIgnoreCase("1")) {
                mb5Overkill.setVisible(true);
            }

            token5Name = mbTokens[4];

            if (mbTokens[5].equalsIgnoreCase("1")) {
                mb6Overkill.setVisible(true);
            }

            token6Name = mbTokens[6];

            if (mbTokens[7].equalsIgnoreCase("1")) {
                mb7Overkill.setVisible(true);
            }

            token7Name = mbTokens[8];

            if (mbTokens[9].equalsIgnoreCase("1")) {
                mb8Overkill.setVisible(true);
            }

            token8Name = mbTokens[10];


        }

        if (skullNumber == 6) {
            mbToken1.setVisible(false);
            mbToken2.setVisible(false);

            if (mbTokens[1].equalsIgnoreCase("1")) {
                mb3Overkill.setVisible(true);
            }

            token3Name = mbTokens[2];

            if (mbTokens[3].equalsIgnoreCase("1")) {
                mb4Overkill.setVisible(true);
            }

            token4Name = mbTokens[4];

            if (mbTokens[5].equalsIgnoreCase("1")) {
                mb5Overkill.setVisible(true);
            }

            token5Name = mbTokens[6];

            if (mbTokens[7].equalsIgnoreCase("1")) {
                mb6Overkill.setVisible(true);
            }

            token6Name = mbTokens[8];

            if (mbTokens[9].equalsIgnoreCase("1")) {
                mb7Overkill.setVisible(true);
            }

            token7Name = mbTokens[10];

            if (mbTokens[11].equalsIgnoreCase("1")) {
                mb8Overkill.setVisible(true);
            }

            token8Name = mbTokens[12];


        }

        if (skullNumber == 7) {

            mbToken1.setVisible(false);

            if (mbTokens[1].equalsIgnoreCase("1")) {
                mb2Overkill.setVisible(true);
            }

            token2Name = mbTokens[2];

            if (mbTokens[3].equalsIgnoreCase("1")) {
                mb3Overkill.setVisible(true);
            }

            token3Name = mbTokens[4];

            if (mbTokens[5].equalsIgnoreCase("1")) {
                mb4Overkill.setVisible(true);
            }

            token4Name = mbTokens[6];

            if (mbTokens[7].equalsIgnoreCase("1")) {
                mb5Overkill.setVisible(true);
            }

            token5Name = mbTokens[8];

            if (mbTokens[9].equalsIgnoreCase("1")) {
                mb6Overkill.setVisible(true);
            }

            token6Name = mbTokens[10];

            if (mbTokens[11].equalsIgnoreCase("1")) {
                mb7Overkill.setVisible(true);
            }

            token7Name = mbTokens[12];

            if (mbTokens[13].equalsIgnoreCase("1")) {
                mb8Overkill.setVisible(true);
            }

            token8Name = mbTokens[14];
        }

        if (skullNumber == 8) {

            if (mbTokens[1].equalsIgnoreCase("1")) {
                mb1Overkill.setVisible(true);
            }

            token1Name = mbTokens[2];

            if (mbTokens[3].equalsIgnoreCase("1")) {
                mb2Overkill.setVisible(true);
            }

            token2Name = mbTokens[4];

            if (mbTokens[5].equalsIgnoreCase("1")) {
                mb3Overkill.setVisible(true);
            }

            token3Name = mbTokens[6];

            if (mbTokens[7].equalsIgnoreCase("1")) {
                mb4Overkill.setVisible(true);
            }

            token4Name = mbTokens[8];

            if (mbTokens[9].equalsIgnoreCase("1")) {
                mb5Overkill.setVisible(true);
            }

            token5Name = mbTokens[10];

            if (mbTokens[11].equalsIgnoreCase("1")) {
                mb6Overkill.setVisible(true);
            }

            token6Name = mbTokens[12];

            if (mbTokens[13].equalsIgnoreCase("1")) {
                mb7Overkill.setVisible(true);
            }

            token7Name = mbTokens[14];

            if (mbTokens[15].equalsIgnoreCase("1")) {
                mb8Overkill.setVisible(true);
            }

            token8Name = mbTokens[16];

        }

        //SEZIONE 11: MARKER

        String[] markerString = markerToken.split("@");

        int markerNumber = Integer.parseInt(markerString[0]);

        i = 0;

        for (i = 0; i < markerNumber; i++) {

            markersTaken[i] = markerString[i + 1];

        }

        marker1Name = markersTaken[0];
        marker2Name = markersTaken[1];
        marker3Name = markersTaken[2];
        marker4Name = markersTaken[3];
        marker5Name = markersTaken[4];
        marker6Name = markersTaken[5];
        marker7Name = markersTaken[6];
        marker8Name = markersTaken[7];
        marker9Name = markersTaken[8];
        marker10Name = markersTaken[9];
        marker11Name = markersTaken[10];
        marker12Name = markersTaken[11];

        //SEZIONE 12: POSIZIONE DEI GIOCATORI

        String[] tokenPosition = playersPositions.split("@");
        int otherPlayerNumber = Integer.parseInt(tokenPosition[0]);

        if (championName.equalsIgnoreCase("dozer")) {
            dozerUser = nickname;
            dozerRoom = cellName;
            dozerRoomColour = colour;
        }

        if (championName.equalsIgnoreCase("sprog")) {
            sprogUser = nickname;
            sprogRoom = cellName;
            sprogRoomColour = colour;
        }

        if (championName.equalsIgnoreCase("banshee")) {
            bansheeUser = nickname;
            bansheeRoom = cellName;
            bansheeRoomColour = colour;
        }

        if (championName.equalsIgnoreCase("violetta")) {
            violettaUser = nickname;
            violettaRoom = cellName;
            violettaRoomColour = colour;
        }

        if (championName.equalsIgnoreCase(":d-strutt-or3")) {
            destructorUser = nickname;
            destructorRoom = cellName;
            destructorRoomColour = colour;
        }

        if (otherPlayerNumber >= 2) {

            //Di sicuro avrò personaggi nella tokenposition[1] e nella tokenposition[5].

            //tokenPosition[1]:

            if (tokenPosition[1].equalsIgnoreCase("dozer")) {

                dozerUser = tokenPosition[2];
                dozerRoomColour = tokenPosition[3];
                dozerRoom = tokenPosition[4];

            }

            if (tokenPosition[1].equalsIgnoreCase("violetta")) {

                violettaUser = tokenPosition[2];
                violettaRoomColour = tokenPosition[3];
                violettaRoom = tokenPosition[4];

            }

            if (tokenPosition[1].equalsIgnoreCase(":d-strutt-or3")) {

                destructorUser = tokenPosition[2];
                destructorRoomColour = tokenPosition[3];
                destructorRoom = tokenPosition[4];

            }

            if (tokenPosition[1].equalsIgnoreCase("sprog")) {

                sprogUser = tokenPosition[2];
                sprogRoomColour = tokenPosition[3];
                sprogRoom = tokenPosition[4];

            }

            if (tokenPosition[1].equalsIgnoreCase("banshee")) {

                bansheeUser = tokenPosition[2];
                bansheeRoomColour = tokenPosition[3];
                bansheeRoom = tokenPosition[4];

            }

            //tokenPosition[5]:

            if (tokenPosition[5].equalsIgnoreCase("dozer")) {

                dozerUser = tokenPosition[6];
                dozerRoomColour = tokenPosition[7];
                dozerRoom = tokenPosition[8];

            }

            if (tokenPosition[5].equalsIgnoreCase("violetta")) {

                violettaUser = tokenPosition[6];
                violettaRoomColour = tokenPosition[7];
                violettaRoom = tokenPosition[8];

            }

            if (tokenPosition[5].equalsIgnoreCase(":d-strutt-or3")) {

                destructorUser = tokenPosition[6];
                destructorRoomColour = tokenPosition[7];
                destructorRoom = tokenPosition[8];

            }

            if (tokenPosition[5].equalsIgnoreCase("sprog")) {

                sprogUser = tokenPosition[6];
                sprogRoomColour = tokenPosition[7];
                sprogRoom = tokenPosition[8];

            }

            if (tokenPosition[5].equalsIgnoreCase("banshee")) {

                bansheeUser = tokenPosition[6];
                bansheeRoomColour = tokenPosition[7];
                bansheeRoom = tokenPosition[8];

            }


        }

        if (otherPlayerNumber >= 3) {

            //Di sicuro avrò personaggi nella tokenPosition[9]

            if (tokenPosition[9].equalsIgnoreCase("dozer")) {

                dozerUser = tokenPosition[10];
                dozerRoomColour = tokenPosition[11];
                dozerRoom = tokenPosition[12];

            }

            if (tokenPosition[9].equalsIgnoreCase("violetta")) {

                violettaUser = tokenPosition[10];
                violettaRoomColour = tokenPosition[11];
                violettaRoom = tokenPosition[12];

            }

            if (tokenPosition[9].equalsIgnoreCase(":d-strutt-or3")) {

                destructorUser = tokenPosition[10];
                destructorRoomColour = tokenPosition[11];
                destructorRoom = tokenPosition[12];

            }

            if (tokenPosition[9].equalsIgnoreCase("sprog")) {

                sprogUser = tokenPosition[10];
                sprogRoomColour = tokenPosition[11];
                sprogRoom = tokenPosition[12];

            }

            if (tokenPosition[9].equalsIgnoreCase("banshee")) {

                bansheeUser = tokenPosition[10];
                bansheeRoomColour = tokenPosition[11];
                bansheeRoom = tokenPosition[12];

            }

        }

        if (otherPlayerNumber == 4) {

            //Di sicuro qui avrò personaggi nella tokenPosition[13].

            if (tokenPosition[13].equalsIgnoreCase("dozer")) {

                dozerUser = tokenPosition[14];
                dozerRoomColour = tokenPosition[15];
                dozerRoom = tokenPosition[16];

            }

            if (tokenPosition[13].equalsIgnoreCase("violetta")) {

                violettaUser = tokenPosition[14];
                violettaRoomColour = tokenPosition[15];
                violettaRoom = tokenPosition[16];

            }

            if (tokenPosition[13].equalsIgnoreCase(":d-strutt-or3")) {

                destructorUser = tokenPosition[14];
                destructorRoomColour = tokenPosition[15];
                destructorRoom = tokenPosition[16];

            }

            if (tokenPosition[13].equalsIgnoreCase("sprog")) {

                sprogUser = tokenPosition[14];
                sprogRoomColour = tokenPosition[15];
                sprogRoom = tokenPosition[16];

            }

            if (tokenPosition[13].equalsIgnoreCase("banshee")) {

                bansheeUser = tokenPosition[14];
                bansheeRoomColour = tokenPosition[15];
                bansheeRoom = tokenPosition[16];

            }


        }


    }


    public String provaStampa() {

        String toPrint = "";

        toPrint = toPrint + "Nickname: " + nickname + "\nChampion Name: " + championName + "\nPlayerscore: " + score + "\n";

        toPrint = toPrint + "Colour: " + colour + " - Cell name: " + cellName + "\n";

        toPrint = toPrint + "Weapon1: " + weapon1 + "Loaded: " + loaded1 + "\n";

        toPrint = toPrint + "Weapon2: " + weapon2 + "Loaded: " + loaded2 + "\n";

        toPrint = toPrint + "Weapon3: " + weapon3 + "Loaded: " + loaded3 + "\n";

        toPrint = toPrint + "PU1 " + pu1 + "\n";

        toPrint = toPrint + "PU2 " + pu2 + "\n";

        toPrint = toPrint + "PU3 " + pu3 + "\n";

        toPrint = toPrint + "Bluecubes: " + blueCubes + "\n";

        toPrint = toPrint + "Redcubes: " + redCubes + "\n";

        toPrint = toPrint + "Yellowcubes: " + yellowCubes + "\n";

        toPrint = toPrint + "Danno1: " + damage1Name + "\n";

        toPrint = toPrint + "Danno2: " + damage2Name + "\n";

        toPrint = toPrint + "Danno3: " + damage3Name + "\n";

        toPrint = toPrint + "Danno4: " + damage4Name + "\n";

        toPrint = toPrint + "Danno5: " + damage5Name + "\n";

        toPrint = toPrint + "Danno6: " + damage6Name + "\n";

        toPrint = toPrint + "Danno7: " + damage7Name + "\n";

        toPrint = toPrint + "Danno8: " + damage8Name + "\n";

        toPrint = toPrint + "Danno9: " + damage9Name + "\n";

        toPrint = toPrint + "Danno10: " + damage10Name + "\n";

        toPrint = toPrint + "Danno11: " + damage11Name + "\n";

        toPrint = toPrint + "Danno12: " + damage12Name + "\n";


        toPrint = toPrint + "Marker1: " + marker1Name + "\n";

        toPrint = toPrint + "Marker2: " + marker2Name + "\n";

        toPrint = toPrint + "Marker3: " + marker3Name + "\n";

        toPrint = toPrint + "Marker4: " + marker4Name + "\n";

        toPrint = toPrint + "Marker5: " + marker5Name + "\n";

        toPrint = toPrint + "Marker6: " + marker6Name + "\n";

        toPrint = toPrint + "Marker7: " + marker7Name + "\n";

        toPrint = toPrint + "Marker8: " + marker8Name + "\n";

        toPrint = toPrint + "Marker9: " + marker9Name + "\n";

        toPrint = toPrint + "Marker10: " + marker10Name + "\n";

        toPrint = toPrint + "Marker11: " + marker11Name + "\n";

        toPrint = toPrint + "Marker12: " + marker12Name + "\n";

        toPrint = toPrint + "Dozer user: " + dozerUser + "\n";

        toPrint = toPrint + "Destructor user: " + destructorUser + "\n";

        toPrint = toPrint + "Violetta user: " + violettaUser + "\n";

        toPrint = toPrint + "Sprog user: " + sprogUser + "\n";

        toPrint = toPrint + "Banshee user: " + bansheeUser + "\n";


        return toPrint;

    }


    @FXML
    public void initUI() {

        nicknameText.setText(nickname);

        if(firstPlayer.equalsIgnoreCase("true")){

            Image image = new Image(GUI.resourcesPath + "Icons/First_ico.png");
            firstplayer.setImage(image);
            firstplayer.setVisible(true);

        }

        if (championName.equalsIgnoreCase("dozer")) {

            Image image = new Image(GUI.resourcesPath + "Playerboards/Dozer_PB.png");
            playerboardImage.setImage(image);
            dozerUser = nickname;

        }

        if (championName.equalsIgnoreCase("violetta")) {

            Image image = new Image(GUI.resourcesPath + "Playerboards/Violet_PB.png");
            playerboardImage.setImage(image);
            violettaUser = nickname;

        }

        if (championName.equalsIgnoreCase("sprog")) {

            Image image = new Image(GUI.resourcesPath + "Playerboards/Sprog_PB.png");
            playerboardImage.setImage(image);
            sprogUser = nickname;

        }

        if (championName.equalsIgnoreCase("banshee")) {

            Image image = new Image(GUI.resourcesPath + "Playerboards/Banshee_PB.png");
            playerboardImage.setImage(image);
            bansheeUser = nickname;

        }

        if (championName.equalsIgnoreCase("d-strutt-or3")) {

            Image image = new Image(GUI.resourcesPath + "Playerboards/Destructor_PB.png");
            playerboardImage.setImage(image);
            destructorUser = nickname;

        }

        blueCubesText.setText(blueCubes);
        yellowCubesText.setText(yellowCubes);
        redCubesText.setText(redCubes);

        pu1Image.setImage(getPowerupImage(pu1));
        pu2Image.setImage(getPowerupImage(pu2));
        pu3Image.setImage(getPowerupImage(pu3));

        blueLoot1Tile.setImage(getLootTileImage(blueLoot1));
        blueLoot2Tile.setImage(getLootTileImage(blueLoot2));

        redLootTile.setImage(getLootTileImage(redLoot));
        

        yellowLoot1Tile.setImage(getLootTileImage(yellowLoot1));
        yellowLoot2Tile.setImage(getLootTileImage(yellowLoot2));
        yellowLoot3Tile.setImage(getLootTileImage(yellowLoot3));

        whiteLootTile.setImage(getLootTileImage(whiteLoot));
        
        greenLootTile.setImage(getLootTileImage(greenLoot));

        blueWeapon1.setImage(getWeaponImage(blueWeapon1name));
        blueWeapon2.setImage(getWeaponImage(blueWeapon2name));
        blueWeapon3.setImage(getWeaponImage(blueWeapon3name));

        redWeapon1.setImage(getWeaponImage(redWeapon1name));
        redWeapon2.setImage(getWeaponImage(redWeapon2name));
        redWeapon3.setImage(getWeaponImage(redWeapon3name));

        yellowWeapon1.setImage(getWeaponImage(yellowWeapon1name));
        yellowWeapon2.setImage(getWeaponImage(yellowWeapon2name));
        yellowWeapon3.setImage(getWeaponImage(yellowWeapon3name));

        damage1.setImage(getTokenImage(damage1Name));
        damage2.setImage(getTokenImage(damage2Name));
        damage3.setImage(getTokenImage(damage3Name));
        damage4.setImage(getTokenImage(damage4Name));
        damage5.setImage(getTokenImage(damage5Name));
        damage6.setImage(getTokenImage(damage6Name));
        damage7.setImage(getTokenImage(damage7Name));
        damage8.setImage(getTokenImage(damage8Name));
        damage9.setImage(getTokenImage(damage9Name));
        damage10.setImage(getTokenImage(damage10Name));
        damage11.setImage(getTokenImage(damage11Name));
        damage12.setImage(getTokenImage(damage12Name));

        mbToken1.setImage(getTokenImage(token1Name));
        mbToken2.setImage(getTokenImage(token2Name));
        mbToken3.setImage(getTokenImage(token3Name));
        mbToken4.setImage(getTokenImage(token4Name));
        mbToken5.setImage(getTokenImage(token5Name));
        mbToken6.setImage(getTokenImage(token6Name));
        mbToken7.setImage(getTokenImage(token7Name));
        mbToken8.setImage(getTokenImage(token8Name));

        marker1Image.setImage(getTokenImage(marker1Name));
        marker2Image.setImage(getTokenImage(marker2Name));
        marker3Image.setImage(getTokenImage(marker3Name));
        marker4Image.setImage(getTokenImage(marker4Name));
        marker5Image.setImage(getTokenImage(marker5Name));
        marker6Image.setImage(getTokenImage(marker6Name));
        marker7Image.setImage(getTokenImage(marker7Name));
        marker8Image.setImage(getTokenImage(marker8Name));
        marker9Image.setImage(getTokenImage(marker9Name));
        marker10Image.setImage(getTokenImage(marker10Name));
        marker11Image.setImage(getTokenImage(marker11Name));
        marker12Image.setImage(getTokenImage(marker12Name));

        drawPositions();


    }

    @FXML
    public void refresh() {

        //----- Impostazione punteggio ---- //

        scoreText.setText(score);

        //---- Impostazione cubi ---- //

        blueCubesText.setText(blueCubes);
        yellowCubesText.setText(yellowCubes);
        redCubesText.setText(redCubes);

        //---- Impostazione armi giocatore ---//

        weapon1Image.setImage(getWeaponImage(weapon1));

        if (loaded1.equalsIgnoreCase("0")) {
            weapon1Image.setEffect(new SepiaTone());
        }


        weapon2Image.setImage(getWeaponImage(weapon2));

        if (loaded2.equalsIgnoreCase("0")) {
            weapon2Image.setEffect(new SepiaTone());
        }

        weapon3Image.setImage(getWeaponImage(weapon3));

        if (loaded3.equalsIgnoreCase("0")) {
            weapon3Image.setEffect(new SepiaTone());
        }

        // -- Impostazione Potenziamenti --- //

        pu1Image.setImage(getPowerupImage(pu1));
        pu2Image.setImage(getPowerupImage(pu2));
        pu3Image.setImage(getPowerupImage(pu3));

        // -- Piazzare Loots -- //

        blueLoot1Tile.setImage(getLootTileImage(blueLoot1));
        blueLoot2Tile.setImage(getLootTileImage(blueLoot2));

        redLootTile.setImage(getLootTileImage(redLoot));

        yellowLoot1Tile.setImage(getLootTileImage(yellowLoot1));
        yellowLoot2Tile.setImage(getLootTileImage(yellowLoot2));
        yellowLoot3Tile.setImage(getLootTileImage(yellowLoot3));
        

        whiteLootTile.setImage(getLootTileImage(whiteLoot));
        greenLootTile.setImage(getLootTileImage(greenLoot));

        // -- Piazzare armi spawncells -- //

        blueWeapon1.setImage(getWeaponImage(blueWeapon1name));
        blueWeapon2.setImage(getWeaponImage(blueWeapon2name));
        blueWeapon3.setImage(getWeaponImage(blueWeapon3name));

        redWeapon1.setImage(getWeaponImage(redWeapon1name));
        redWeapon2.setImage(getWeaponImage(redWeapon2name));
        redWeapon3.setImage(getWeaponImage(redWeapon3name));

        yellowWeapon1.setImage(getWeaponImage(yellowWeapon1name));
        yellowWeapon2.setImage(getWeaponImage(yellowWeapon2name));
        yellowWeapon3.setImage(getWeaponImage(yellowWeapon3name));

        // -- Carico segnalini danno -- //

        damage1.setImage(getTokenImage(damage1Name));
        damage2.setImage(getTokenImage(damage2Name));
        damage3.setImage(getTokenImage(damage3Name));
        damage4.setImage(getTokenImage(damage4Name));
        damage5.setImage(getTokenImage(damage5Name));
        damage6.setImage(getTokenImage(damage6Name));
        damage7.setImage(getTokenImage(damage7Name));
        damage8.setImage(getTokenImage(damage8Name));
        damage9.setImage(getTokenImage(damage9Name));
        damage10.setImage(getTokenImage(damage10Name));
        damage11.setImage(getTokenImage(damage11Name));
        damage12.setImage(getTokenImage(damage12Name));

        // -- Carico teschi PB -- //

        if (playerboardValue.equalsIgnoreCase("6")) {
            PBSkull1.setVisible(true);
        }

        if (playerboardValue.equalsIgnoreCase("4")) {
            PBSkull1.setVisible(true);
            PBSkull2.setVisible(true);
        }

        if (playerboardValue.equalsIgnoreCase("2")) {
            PBSkull1.setVisible(true);
            PBSkull2.setVisible(true);
            PBSkull3.setVisible(true);
        }

        if (playerboardValue.equalsIgnoreCase("1")) {
            PBSkull1.setVisible(true);
            PBSkull2.setVisible(true);
            PBSkull3.setVisible(true);
            PBSkull4.setVisible(true);
        }

        if (playerboardValue.equalsIgnoreCase("0")) {
            PBSkull1.setVisible(true);
            PBSkull2.setVisible(true);
            PBSkull3.setVisible(true);
            PBSkull4.setVisible(true);
            PBSkull5.setVisible(true);
        }


        // -- Carico MBTrack

        mbToken1.setImage(getTokenImage(token1Name));
        mbToken2.setImage(getTokenImage(token2Name));
        mbToken3.setImage(getTokenImage(token3Name));
        mbToken4.setImage(getTokenImage(token4Name));
        mbToken5.setImage(getTokenImage(token5Name));
        mbToken6.setImage(getTokenImage(token6Name));
        mbToken7.setImage(getTokenImage(token7Name));
        mbToken8.setImage(getTokenImage(token8Name));

        // Carico markers

        marker1Image.setImage(getTokenImage(marker1Name));
        marker2Image.setImage(getTokenImage(marker2Name));
        marker3Image.setImage(getTokenImage(marker3Name));
        marker4Image.setImage(getTokenImage(marker4Name));
        marker5Image.setImage(getTokenImage(marker5Name));
        marker6Image.setImage(getTokenImage(marker6Name));
        marker7Image.setImage(getTokenImage(marker7Name));
        marker8Image.setImage(getTokenImage(marker8Name));
        marker9Image.setImage(getTokenImage(marker9Name));
        marker10Image.setImage(getTokenImage(marker10Name));
        marker11Image.setImage(getTokenImage(marker11Name));
        marker12Image.setImage(getTokenImage(marker12Name));

        //Disegno le posizioni

        drawPositions();


    }


    @FXML
    public void drawPositions() {

        //Questa funzione deve prendere i giocatori e posizionarne le pedine

        if (!sprogUser.equalsIgnoreCase("empty")) {


            //Se sprog è nella gialla...
            if (sprogRoomColour.equalsIgnoreCase("0")) {

                sprogIcon.setVisible(true);

                if (sprogRoom.equalsIgnoreCase("spawnCell")) {

                    sprogIcon.setLayoutX((double) yellowSpawnCellSprogX);
                    sprogIcon.setLayoutY((double) yellowSpawnCellSprogY);

                }

                if (sprogRoom.equalsIgnoreCase("lootCell1")) {

                    sprogIcon.setLayoutX((double) yellowLootCell1SprogX);
                    sprogIcon.setLayoutY((double) yellowLootCell1SprogY);

                }

                if (sprogRoom.equalsIgnoreCase("lootCell2")) {

                    sprogIcon.setLayoutX((double) yellowLootCell2SprogX);
                    sprogIcon.setLayoutY((double) yellowLootCell2SprogY);

                }

                if (sprogRoom.equalsIgnoreCase("lootCell3")) {

                    sprogIcon.setLayoutX((double) yellowLootCell3SprogX);
                    sprogIcon.setLayoutY((double) yellowLootCell3SprogY);

                }


            }

            //Se sprog è nella rossa...
            if (sprogRoomColour.equalsIgnoreCase("1")) {

                sprogIcon.setVisible(true);

                if (sprogRoom.equalsIgnoreCase("spawnCell")) {

                    sprogIcon.setLayoutX((double) redSpawnCellSprogX);
                    sprogIcon.setLayoutY((double) redSpawnCellSprogY);

                }

                if (sprogRoom.equalsIgnoreCase("lootCell")) {

                    sprogIcon.setLayoutX((double) redLootCellSprogX);
                    sprogIcon.setLayoutY((double) redLootCellSprogY);

                }
                

            }

            //Se sprog è nella blu...
            if (sprogRoomColour.equalsIgnoreCase("2")) {

                sprogIcon.setVisible(true);

                if (sprogRoom.equalsIgnoreCase("spawnCell")) {

                    sprogIcon.setLayoutX((double) blueSpawnCellSprogX);
                    sprogIcon.setLayoutY((double) blueSpawnCellSprogY);

                }

                if (sprogRoom.equalsIgnoreCase("lootCell1")) {

                    sprogIcon.setLayoutX((double) blueLootCell1SprogX);
                    sprogIcon.setLayoutY((double) blueLootCell1SprogY);

                }

                if (sprogRoom.equalsIgnoreCase("lootCell2")) {

                    sprogIcon.setLayoutX((double) blueLootCell2SprogX);
                    sprogIcon.setLayoutY((double) blueLootCell2SprogY);

                }

            }

            //Se è nella bianca
            if (sprogRoomColour.equalsIgnoreCase("3")) {

                sprogIcon.setVisible(true);

                if (sprogRoom.equalsIgnoreCase("lootCell")) {

                    sprogIcon.setLayoutX((double) whiteLootCellSprogX);
                    sprogIcon.setLayoutY((double) whiteLootCellSprogY);

                }
                
            }

            //Se è nella verde
            if (sprogRoomColour.equalsIgnoreCase("4")) {

                sprogIcon.setVisible(true);

                if (sprogRoom.equalsIgnoreCase("lootCell")) {

                    sprogIcon.setLayoutX((double) greenLootCellSprogX);
                    sprogIcon.setLayoutY((double) greenLootCellSprogY);

                }

            }


        }

        if (!violettaUser.equalsIgnoreCase("empty")) {


            //Se violet è nella gialla...
            if (violettaRoomColour.equalsIgnoreCase("0")) {

                violetIcon.setVisible(true);

                if (violettaRoom.equalsIgnoreCase("spawnCell")) {

                    violetIcon.setLayoutX((double) yellowSpawnCellVioletX);
                    violetIcon.setLayoutY((double) yellowSpawnCellVioletY);

                }

                if (violettaRoom.equalsIgnoreCase("lootCell1")) {

                    violetIcon.setLayoutX((double) yellowLootCell1VioletX);
                    violetIcon.setLayoutY((double) yellowLootCell1VioletY);

                }

                if (violettaRoom.equalsIgnoreCase("lootCell2")) {

                    violetIcon.setLayoutX((double) yellowLootCell2VioletX);
                    violetIcon.setLayoutY((double) yellowLootCell2VioletY);

                }

                if (violettaRoom.equalsIgnoreCase("lootCell3")) {

                    violetIcon.setLayoutX((double) yellowLootCell3VioletX);
                    violetIcon.setLayoutY((double) yellowLootCell3VioletY);

                }


            }

            //Se violet è nella rossa...
            if (violettaRoomColour.equalsIgnoreCase("1")) {

                violetIcon.setVisible(true);

                if (violettaRoom.equalsIgnoreCase("spawnCell")) {

                    violetIcon.setLayoutX((double) redSpawnCellVioletX);
                    violetIcon.setLayoutY((double) redSpawnCellVioletY);

                }

                if (violettaRoom.equalsIgnoreCase("lootCell")) {

                    violetIcon.setLayoutX((double) redLootCellVioletX);
                    violetIcon.setLayoutY((double) redLootCellVioletY);

                }


            }

            //Se violet è nella blu...
            if (violettaRoomColour.equalsIgnoreCase("2")) {

                violetIcon.setVisible(true);

                if (violettaRoom.equalsIgnoreCase("spawnCell")) {

                    violetIcon.setLayoutX((double) blueSpawnCellVioletX);
                    violetIcon.setLayoutY((double) blueSpawnCellVioletY);

                }

                if (violettaRoom.equalsIgnoreCase("lootCell1")) {

                    violetIcon.setLayoutX((double) blueLootCell1VioletX);
                    violetIcon.setLayoutY((double) blueLootCell1VioletY);

                }

                if (violettaRoom.equalsIgnoreCase("lootCell2")) {

                    violetIcon.setLayoutX((double) blueLootCell2VioletX);
                    violetIcon.setLayoutY((double) blueLootCell2VioletY);

                }

            }

            //Se è nella bianca
            if (violettaRoomColour.equalsIgnoreCase("3")) {

                violetIcon.setVisible(true);

                if (violettaRoom.equalsIgnoreCase("lootCell")) {

                    violetIcon.setLayoutX((double) whiteLootCellVioletX);
                    violetIcon.setLayoutY((double) whiteLootCellVioletY);

                }


            }

            //Se è nella verde
            if (violettaRoomColour.equalsIgnoreCase("4")) {

                violetIcon.setVisible(true);

                if (violettaRoom.equalsIgnoreCase("lootCell")) {

                    violetIcon.setLayoutX((double) greenLootCellVioletX);
                    violetIcon.setLayoutY((double) greenLootCellVioletY);

                }


            }


        }

        if (!bansheeUser.equalsIgnoreCase("empty")) {

            //Se banshee è nella gialla...
            if (bansheeRoomColour.equalsIgnoreCase("0")) {

                bansheeIcon.setVisible(true);

                if (bansheeRoom.equalsIgnoreCase("spawnCell")) {

                    bansheeIcon.setLayoutX((double) yellowSpawnCellBansheeX);
                    bansheeIcon.setLayoutY((double) yellowSpawnCellBansheeY);

                }

                if (bansheeRoom.equalsIgnoreCase("lootCell1")) {

                    bansheeIcon.setLayoutX((double) yellowLootCell1BansheeX);
                    bansheeIcon.setLayoutY((double) yellowLootCell1BansheeY);

                }

                if (bansheeRoom.equalsIgnoreCase("lootCell2")) {

                    bansheeIcon.setLayoutX((double) yellowLootCell2BansheeX);
                    bansheeIcon.setLayoutY((double) yellowLootCell2BansheeY);

                }

                if (bansheeRoom.equalsIgnoreCase("lootCell3")) {

                    bansheeIcon.setLayoutX((double) yellowLootCell3BansheeX);
                    bansheeIcon.setLayoutY((double) yellowLootCell3BansheeY);

                }


            }

            //Se banshee è nella rossa...
            if (bansheeRoomColour.equalsIgnoreCase("1")) {

                bansheeIcon.setVisible(true);

                if (bansheeRoom.equalsIgnoreCase("spawnCell")) {

                    bansheeIcon.setLayoutX((double) redSpawnCellBansheeX);
                    bansheeIcon.setLayoutY((double) redSpawnCellBansheeY);

                }

                if (bansheeRoom.equalsIgnoreCase("lootCell")) {

                    bansheeIcon.setLayoutX((double) redLootCellBansheeX);
                    bansheeIcon.setLayoutY((double) redLootCellBansheeY);

                }


            }

            //Se banshee è nella blu...
            if (bansheeRoomColour.equalsIgnoreCase("2")) {

                bansheeIcon.setVisible(true);

                if (bansheeRoom.equalsIgnoreCase("spawnCell")) {

                    bansheeIcon.setLayoutX((double) blueSpawnCellBansheeX);
                    bansheeIcon.setLayoutY((double) blueSpawnCellBansheeY);

                }

                if (bansheeRoom.equalsIgnoreCase("lootCell1")) {

                    bansheeIcon.setLayoutX((double) blueLootCell1BansheeX);
                    bansheeIcon.setLayoutY((double) blueLootCell1BansheeY);

                }

                if (bansheeRoom.equalsIgnoreCase("lootCell2")) {

                    bansheeIcon.setLayoutX((double) blueLootCell2BansheeX);
                    bansheeIcon.setLayoutY((double) blueLootCell2BansheeY);

                }

            }

            //Se è nella bianca
            if (bansheeRoomColour.equalsIgnoreCase("3")) {

                bansheeIcon.setVisible(true);

                if (bansheeRoom.equalsIgnoreCase("lootCell")) {

                    bansheeIcon.setLayoutX((double) whiteLootCellBansheeX);
                    bansheeIcon.setLayoutY((double) whiteLootCellBansheeY);

                }

            }

            //Se è nella verde
            if (bansheeRoomColour.equalsIgnoreCase("4")) {

                bansheeIcon.setVisible(true);

                if (bansheeRoom.equalsIgnoreCase("lootCell")) {

                    bansheeIcon.setLayoutX((double) greenLootCellBansheeX);
                    bansheeIcon.setLayoutY((double) greenLootCellBansheeY);

                }

            }



        }

        if (!dozerUser.equalsIgnoreCase("empty")) {


            //Se dozer è nella gialla...
            if (dozerRoomColour.equalsIgnoreCase("0")) {

                dozerIcon.setVisible(true);

                if (dozerRoom.equalsIgnoreCase("spawnCell")) {

                    dozerIcon.setLayoutX((double) yellowSpawnCellDozerX);
                    dozerIcon.setLayoutY((double) yellowSpawnCellDozerY);

                }

                if (dozerRoom.equalsIgnoreCase("lootCell1")) {

                    dozerIcon.setLayoutX((double) yellowLootCell1DozerX);
                    dozerIcon.setLayoutY((double) yellowLootCell1DozerY);

                }

                if (dozerRoom.equalsIgnoreCase("lootCell2")) {

                    dozerIcon.setLayoutX((double) yellowLootCell2DozerX);
                    dozerIcon.setLayoutY((double) yellowLootCell2DozerY);

                }

                if (dozerRoom.equalsIgnoreCase("lootCell3")) {

                    dozerIcon.setLayoutX((double) yellowLootCell3DozerX);
                    dozerIcon.setLayoutY((double) yellowLootCell3DozerY);

                }


            }

            //Se dozer è nella rossa...
            if (dozerRoomColour.equalsIgnoreCase("1")) {

                dozerIcon.setVisible(true);

                if (dozerRoom.equalsIgnoreCase("spawnCell")) {

                    dozerIcon.setLayoutX((double) redSpawnCellDozerX);
                    dozerIcon.setLayoutY((double) redSpawnCellDozerY);

                }

                if (dozerRoom.equalsIgnoreCase("lootCell")) {

                    dozerIcon.setLayoutX((double) redLootCellDozerX);
                    dozerIcon.setLayoutY((double) redLootCellDozerY);

                }


            }

            //Se dozer è nella blu...
            if (dozerRoomColour.equalsIgnoreCase("2")) {

                dozerIcon.setVisible(true);

                if (dozerRoom.equalsIgnoreCase("spawnCell")) {

                    dozerIcon.setLayoutX((double) blueSpawnCellDozerX);
                    dozerIcon.setLayoutY((double) blueSpawnCellDozerY);

                }

                if (dozerRoom.equalsIgnoreCase("lootCell1")) {

                    dozerIcon.setLayoutX((double) blueLootCell1DozerX);
                    dozerIcon.setLayoutY((double) blueLootCell1DozerY);

                }

                if (dozerRoom.equalsIgnoreCase("lootCell2")) {

                    dozerIcon.setLayoutX((double) blueLootCell2DozerX);
                    dozerIcon.setLayoutY((double) blueLootCell2DozerY);

                }

            }

            //Se è nella bianca
            if (dozerRoomColour.equalsIgnoreCase("3")) {

                dozerIcon.setVisible(true);

                if (dozerRoom.equalsIgnoreCase("lootCell")) {

                    dozerIcon.setLayoutX((double) whiteLootCellDozerX);
                    dozerIcon.setLayoutY((double) whiteLootCellDozerY);

                }

            }

            //Se è nella verde
            if (dozerRoomColour.equalsIgnoreCase("4")) {

                dozerIcon.setVisible(true);

                if (dozerRoom.equalsIgnoreCase("lootCell")) {

                    dozerIcon.setLayoutX((double) greenLootCellDozerX);
                    dozerIcon.setLayoutY((double) greenLootCellDozerY);

                }

            }



        }

        if (!destructorUser.equalsIgnoreCase("empty")) {


            //Se destructor è nella gialla...
            if (destructorRoomColour.equalsIgnoreCase("0")) {

                destructorIcon.setVisible(true);

                if (destructorRoom.equalsIgnoreCase("spawnCell")) {

                    destructorIcon.setLayoutX((double) yellowSpawnCellDestructorX);
                    destructorIcon.setLayoutY((double) yellowSpawnCellDestructorY);

                }

                if (destructorRoom.equalsIgnoreCase("lootCell1")) {

                    destructorIcon.setLayoutX((double) yellowLootCell1DestructorX);
                    destructorIcon.setLayoutY((double) yellowLootCell1DestructorY);

                }

                if (destructorRoom.equalsIgnoreCase("lootCell2")) {

                    destructorIcon.setLayoutX((double) yellowLootCell2DestructorX);
                    destructorIcon.setLayoutY((double) yellowLootCell2DestructorY);

                }

                if (destructorRoom.equalsIgnoreCase("lootCell3")) {

                    destructorIcon.setLayoutX((double) yellowLootCell3DestructorX);
                    destructorIcon.setLayoutY((double) yellowLootCell3DestructorY);

                }


            }

            //Se destructor è nella rossa...
            if (destructorRoomColour.equalsIgnoreCase("1")) {

                destructorIcon.setVisible(true);

                if (destructorRoom.equalsIgnoreCase("spawnCell")) {

                    destructorIcon.setLayoutX((double) redSpawnCellDestructorX);
                    destructorIcon.setLayoutY((double) redSpawnCellDestructorY);

                }

                if (destructorRoom.equalsIgnoreCase("lootCell")) {

                    destructorIcon.setLayoutX((double) redLootCellDestructorX);
                    destructorIcon.setLayoutY((double) redLootCellDestructorY);

                }


            }

            //Se destructor è nella blu...
            if (destructorRoomColour.equalsIgnoreCase("2")) {

                destructorIcon.setVisible(true);

                if (destructorRoom.equalsIgnoreCase("spawnCell")) {

                    destructorIcon.setLayoutX((double) blueSpawnCellDestructorX);
                    destructorIcon.setLayoutY((double) blueSpawnCellDestructorY);

                }

                if (destructorRoom.equalsIgnoreCase("lootCell1")) {

                    destructorIcon.setLayoutX((double) blueLootCell1DestructorX);
                    destructorIcon.setLayoutY((double) blueLootCell1DestructorY);

                }

                if (destructorRoom.equalsIgnoreCase("lootCell2")) {

                    destructorIcon.setLayoutX((double) blueLootCell2DestructorX);
                    destructorIcon.setLayoutY((double) blueLootCell2DestructorY);

                }

            }

            //Se è nella bianca
            if (destructorRoomColour.equalsIgnoreCase("3")) {

                destructorIcon.setVisible(true);

                if (destructorRoom.equalsIgnoreCase("lootCell")) {

                    destructorIcon.setLayoutX((double) whiteLootCellDestructorX);
                    destructorIcon.setLayoutY((double) whiteLootCellDestructorY);

                }

            }

            //Se è nella verde
            if (destructorRoomColour.equalsIgnoreCase("4")) {

                destructorIcon.setVisible(true);

                if (destructorRoom.equalsIgnoreCase("lootCell")) {

                    destructorIcon.setLayoutX((double) greenLootCellDestructorX);
                    destructorIcon.setLayoutY((double) greenLootCellDestructorY);

                }

            }


        }


    }

    @FXML
    public Image getWeaponImage(String weaponName) {

        if (weaponName.equalsIgnoreCase("Martello ionico")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_022.png");
        }

        if (weaponName.equalsIgnoreCase("Spada fotonica")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_023.png");
        }

        if (weaponName.equalsIgnoreCase("Cyberguanto")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_024.png");
        }

        if (weaponName.equalsIgnoreCase("Fucile a pompa")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_025.png");
        }

        if (weaponName.equalsIgnoreCase("Fucile laser")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_026.png");
        }

        if (weaponName.equalsIgnoreCase("zx-2")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_027.png");
        }

        if (weaponName.equalsIgnoreCase("onda d'urto")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_028.png");
        }

        if (weaponName.equalsIgnoreCase("cannone vortex")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_029.png");
        }

        if (weaponName.equalsIgnoreCase("razzo termico")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0210.png");
        }

        if (weaponName.equalsIgnoreCase("Lanciarazzi")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0211.png");
        }

        if (weaponName.equalsIgnoreCase("Lanciagranate")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0212.png");
        }

        if (weaponName.equalsIgnoreCase("Lanciafiamme")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0213.png");
        }

        if (weaponName.equalsIgnoreCase("Vulcanizzatore")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0214.png");
        }

        if (weaponName.equalsIgnoreCase("Raggio solare")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0215.png");
        }

        if (weaponName.equalsIgnoreCase("Torpedine")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0216.png");
        }

        if (weaponName.equalsIgnoreCase("Raggio traente")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0217.png");
        }

        if (weaponName.equalsIgnoreCase("Fucile di precisione")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0218.png");
        }

        if (weaponName.equalsIgnoreCase("Fucile al plasma")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0219.png");
        }

        if (weaponName.equalsIgnoreCase("Mitragliatrice")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0220.png");
        }

        if (weaponName.equalsIgnoreCase("distruttore")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0221.png");
        }

        if (weaponName.equalsIgnoreCase("falce protonica")) {
            return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0222.png");
        }

        return new Image(GUI.resourcesPath + "Weapons/AD_weapons_IT_0225.png");
    }

    @FXML
    public Image getPowerupImage(String PUName) {

        if (PUName.equalsIgnoreCase("Raggio Cinetico Blu")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_028.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Mirino Blu")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_025.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Granata Venom Blu")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_022.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Teletrasporto Blu")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_0211.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Raggio Cinetico Rosso")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_029.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Mirino Rosso")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_026.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Granata Venom Rossa")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_023.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Teletrasporto Rosso")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_0212.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Raggio Cinetico Giallo")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_0210.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Mirino Giallo")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_027.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Granata Venom Gialla")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_024.png");
            return image;

        }

        if (PUName.equalsIgnoreCase("Teletrasporto Giallo")) {

            Image image = new Image(GUI.resourcesPath + "Powerups/AD_powerups_IT_0213.png");
            return image;

        }

        return new Image(GUI.resourcesPath + "Powerups/AD_Powerups_IT_02.png");


    }

    @FXML
    public Image getLootTileImage(String name) {

        if (name.equalsIgnoreCase("brr")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0413.png");
        }

        if (name.equalsIgnoreCase("byy")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0412.png");
        }

        if (name.equalsIgnoreCase("pbb")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0422.png");
        }

        if (name.equalsIgnoreCase("prb")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0428.png");
        }

        if (name.equalsIgnoreCase("prr")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0430.png");
        }

        if (name.equalsIgnoreCase("pyb")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0427.png");
        }

        if (name.equalsIgnoreCase("pyr")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0432.png");
        }

        if (name.equalsIgnoreCase("pyy")) {
            return new Image(GUI.resourcesPath + "loot/AD_ammo_0429.png");
        }

        if (name.equalsIgnoreCase("rbb")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0410.png");
        }

        if (name.equalsIgnoreCase("ryy")) {
            return new Image(GUI.resourcesPath + "loot/AD_ammo_0417.png");
        }

        if (name.equalsIgnoreCase("ybb")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_0414.png");
        }

        if (name.equalsIgnoreCase("yrr")) {
            return new Image(GUI.resourcesPath + "Loot/AD_ammo_043.png");
        }

        return new Image(GUI.resourcesPath + "Loot/AD_ammo_04.png");


    }

    @FXML
    public Image getTokenImage(String championName) {

        if (championName.equalsIgnoreCase("dozer")) {
            return new Image(GUI.resourcesPath + "Placeable/Dozer_Mk.png");
        }

        if (championName.equalsIgnoreCase("violetta")) {
            return new Image(GUI.resourcesPath + "Placeable/Violet_Mk.png");
        }

        if (championName.equalsIgnoreCase("banshee")) {
            return new Image(GUI.resourcesPath + "Placeable/Banshee_Mk.png");
        }

        if (championName.equalsIgnoreCase("sprog")) {
            return new Image(GUI.resourcesPath + "Placeable/Sprog_Mk.png");
        }

        if (championName.equalsIgnoreCase(":d-strutt-or3")) {
            return new Image(GUI.resourcesPath + "Placeable/Destructor_Mk.png");
        }

        if (championName.equalsIgnoreCase("skull")) {
            return new Image(GUI.resourcesPath + "Placeable/RedSkull.png");
        }

        return null;

    }


    // ----- AZIONI DEI CONTROLLI ----///

    @FXML
    public void moveAction() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "muovi";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("muovi");
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void shotAction() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = "spara";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("spara");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void pickupAction() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "raccogli";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("raccogli");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ////

    @FXML
    public void choseSprog() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = sprogUser;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(sprogUser);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void choseViolet() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = violettaUser;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(violettaUser);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void choseDestructor() {
        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = destructorUser;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(destructorUser);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void choseBanshee() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = bansheeUser;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(bansheeUser);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void choseDozer() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = dozerUser;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(dozerUser);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    ///


    @FXML
    public void noAction() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "no";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("no");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void yesAction() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "si";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("si");
        }
        } catch (Exception e) {

        }

    }


    @FXML
    public void chooseBlueWeapon1() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = blueWeapon1name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(blueWeapon1name);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void chooseBlueWeapon2() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = blueWeapon2name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(blueWeapon2name);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseBlueWeapon3() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = blueWeapon3name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(blueWeapon3name);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseWeapon1() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = weapon1;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(weapon1);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseWeapon2() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = weapon2;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(weapon2);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseWeapon3() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = weapon3;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(weapon3);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///

    @FXML
    public void chooseYellowWeapon1() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = yellowWeapon1name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(yellowWeapon1name);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseYellowWeapon2() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = yellowWeapon2name;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(yellowWeapon2name);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseYellowWeapon3() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = yellowWeapon3name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(yellowWeapon3name);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //

    @FXML
    public void chooseRedWeapon1() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = redWeapon1name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(redWeapon1name);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseRedWeapon2() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = redWeapon2name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(redWeapon2name);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void chooseRedWeapon3() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = redWeapon3name;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(redWeapon3name);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //

    @FXML
    public void choosePU1() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = pu1;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(pu1);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void choosePU2() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = pu2;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(pu2);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void choosePU3() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = pu3;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(pu3);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //

    @FXML
    public void chooseBlueSpawnCell() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = blueSpawnCellIndex;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(blueSpawnCellIndex);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseBlueLootCell1() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = blueLootCell1Index;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(blueLootCell1Index);
        }


        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseBlueLootCell2() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = blueLootCell2Index;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(blueLootCell2Index);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseRedSpawnCell() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = redSpawnCellIndex;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(redSpawnCellIndex);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseRedLootCell() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = redLootCellIndex;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(redLootCellIndex);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }


    @FXML
    public void chooseYellowSpawnCell() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = yellowSpawnCellIndex;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(yellowSpawnCellIndex);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseYellowLootCell1() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = yellowLootCell1Index;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(yellowLootCell1Index);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }


    @FXML
    public void chooseYellowLootCell2() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = yellowLootCell2Index;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(yellowLootCell2Index);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseYellowLootCell3() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = yellowLootCell3Index;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(yellowLootCell3Index);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseWhiteLootCell() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = whiteLootCellIndex;
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF(whiteLootCellIndex);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseGreenLootCell() {

        try {

            if(GUI.RMI) {

                GUI.RMIAnswer = greenLootCellIndex;
                GUI.answeredRMI = true;
            }

            else {

            out.writeUTF(greenLootCellIndex);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        restoreCells();

    }

    @FXML
    public void chooseRedAmmo() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "rosso";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("rosso");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseBlueAmmo() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "blu";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("blu");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseYellowAmmo() {

        try {
            if(GUI.RMI) {

                GUI.RMIAnswer = "giallo";
                GUI.answeredRMI = true;
            }

            else {
            out.writeUTF("giallo");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
