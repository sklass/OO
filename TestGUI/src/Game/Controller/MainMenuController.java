package Game.Controller;

import Game.Model.MainMenuModel;
import Game.Model.TicTacToe;
import Game.View.FourWinsView;
import Game.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController{
    MainMenuModel MainMenuModel;


    public MainMenuController(){

    }

    public void setModel(MainMenuModel MainMenuModel){
        this.MainMenuModel = MainMenuModel;
    }

    public void showView() throws Exception{
        MainMenuView MainMenu = new MainMenuView();
        MainMenu.start(MainMenuModel.getPrimaryStage());
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
        TicTacToe TicTacToe = new TicTacToe();
        TicTacToe.primaryStage = (Stage) MainMenuPane.getScene().getWindow();
        TicTacToeController TicTacToeController = new TicTacToeController();
        TicTacToeController.setModel(TicTacToe);
        TicTacToeController.showView();
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
