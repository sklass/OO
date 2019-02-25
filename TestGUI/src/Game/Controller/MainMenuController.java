package Game.Controller;

import Game.Model.TicTacToe;
import Game.View.FourWinsView;
import Game.View.TicTacToeView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController{

    TicTacToe TicTacToe;
    public MainMenuController(){
    }

    @FXML
    private AnchorPane MainMenuPane;

   @FXML
    private Label MainMenuLabel;

    @FXML
    private Button StartTicTacToeBtn;

    @FXML
    private Button StartFourWinsBtn;

    @FXML
    private Button QuitBtn;

    //Methode zum starten von TicTacToe
    @FXML
    private void TicTacToe () throws Exception
    {
        System.out.println("TicTacToe");
        TicTacToeView TicTacToeView = new TicTacToeView();                      //TicTacToeView erzeugen
        Stage primaryStage = (Stage) MainMenuPane.getScene().getWindow();   //primaryStage ermitteln
        TicTacToeView.start(primaryStage);                                      //TicTacToe auf PrimaryStage anzeigen
        TicTacToe = new TicTacToe();
    }

    //Methode zum starten von FourWins
    @FXML
    private void FourWins()throws Exception
    {
        //
        System.out.println("FourWins");
        FourWinsView FourWinsView = new FourWinsView();                      //TicTacToeView erzeugen
        Stage primaryStage = (Stage) MainMenuPane.getScene().getWindow();   //primaryStage ermitteln
        FourWinsView.start(primaryStage);                                   //TicTacToe auf PrimaryStage anzeigen
    }

    //Methode zum Beenden des Programms
    @FXML
    private void Quit()
    {
        System.exit(0);
    }

}
