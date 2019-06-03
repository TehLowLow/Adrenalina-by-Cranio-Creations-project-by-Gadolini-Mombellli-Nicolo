package it.polimi.se2019.View.GUI;

import javafx.fxml.Initializable;

import java.io.DataOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseShootingModeController implements Initializable {

    public ChooseShootingModeController() {
    }

    DataOutputStream out;


    public void chooseBasic() {

        try {

            out.writeUTF("base");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseAlternative() {

        try {

            out.writeUTF("alternativo");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
