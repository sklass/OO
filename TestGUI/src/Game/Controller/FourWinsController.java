package Game.Controller;

import Game.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FourWinsController {

    public FourWinsController(){

    }
    @FXML
    private AnchorPane FourWinsPane;

    @FXML
    private Button backBtn;

    @FXML
    private void backToMainMenu() throws Exception
    {
        System.out.println("MainMenu");
        MainMenuView MainMenu = new MainMenuView();                      //TicTacToeView erzeugen
        Stage primaryStage = (Stage)FourWinsPane.getScene().getWindow();   //primaryStage ermitteln
        MainMenu.start(primaryStage);                                      //TicTacToe auf PrimaryStage anzeigen
    }
}
