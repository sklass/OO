package BlackJack.controller;

import BlackJack.Model.BlackJackModel;
import BlackJack.View.BlackJackView;
import BlackJack.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class MainMenuController {
     BlackJackModel Model;

    public void setModel(BlackJackModel Model) {
        this.Model = Model;
    }

    public void shoView()throws Exception{
        MainMenuView View = new MainMenuView(Model);
    }

    @FXML
    Button PlayBtn;
    @FXML
    TextField NumberOfPlayersTextField;
    @FXML
    TextField StartCreditTextField;
    @FXML
    TextField MinBetTextField;
    @FXML
    TextField MaxBetTextField;

    @FXML
    public void PlayBJ() throws Exception{
        Model.setNumberOfPlayers(Integer.parseInt(NumberOfPlayersTextField.getText()));
        Model.setStartCredit(Double.parseDouble(StartCreditTextField.getText()));
        Model.setMinBet(Integer.parseInt(MinBetTextField.getText()));
        Model.setMaxBet(Integer.parseInt(MaxBetTextField.getText()));
        Model.setGamestatus(0);
        Model.setCardCounter(0);
        Model.setNumberOfCardDecks(6);
        BlackJackView BJView = new BlackJackView();
        BJView.start(Model);
    }

    @FXML
    Button QuitBtn;

    @FXML
    public void Quit(){
        System.exit(0);
    }
}
