package Game.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController {

    public MainMenuController(){
    }

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
    private void TicTacToe()
    {
        //
        System.out.println("TicTacToe");
    }

    //Methode zum starten von FourWins
    @FXML
    private void FourWins()
    {
        //
        System.out.println("FourWins");
    }

    //Methode zum Beenden des Programms
    @FXML
    private void Quit()
    {
        System.exit(0);
    }

}
