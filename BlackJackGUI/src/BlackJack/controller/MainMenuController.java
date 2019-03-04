package BlackJack.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
    @FXML
    Button QuitBtn;

    @FXML
    public void Quit(){
        System.exit(0);
    }
}
