package BlackJack.controller;

import BlackJack.Model.BlackJackModel;
import BlackJack.View.BlackJackView;
import BlackJack.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class MainMenuController {
    private BlackJackModel Model;

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
        boolean validInputs = true;

        //Anzahl spieler auf korrekte werte prüfen
        if(isInt(NumberOfPlayersTextField.getText())){
            if(Integer.parseInt(NumberOfPlayersTextField.getText()) > 0 && Integer.parseInt(NumberOfPlayersTextField.getText()) < 7){
                NumberOfPlayersTextField.setStyle("-fx-control-inner-background: white;");
                Model.setNumberOfPlayers(Integer.parseInt(NumberOfPlayersTextField.getText()));
            }else{
                validInputs = false;
                NumberOfPlayersTextField.setStyle("-fx-control-inner-background: red;");
            }

        }else {
            validInputs = false;
            NumberOfPlayersTextField.setStyle("-fx-control-inner-background: red;");
        }

        //MinBet auf korrekte werte prüfen
        if(isInt(MinBetTextField.getText())){                       //Ist es ein Integer
            if(Integer.parseInt(MinBetTextField.getText()) > 0 ){   //und größer null
                MinBetTextField.setStyle("-fx-control-inner-background: white;");
                Model.setMinBet(Integer.parseInt(MinBetTextField.getText()));   //wert in Model speichern
            }else{                                                              //falls eines der beiden nicht zutriff rot markieren
                validInputs = false;                                            //und spielstart verhindern
                MinBetTextField.setStyle("-fx-control-inner-background: red;");
            }

        }else {
            validInputs = false;
            MinBetTextField.setStyle("-fx-control-inner-background: red;");
        }
        //MaxBet auf korrekte werte prüfen
        if(isInt(MaxBetTextField.getText())){                       //Ist es ein Integer
            if(Integer.parseInt(MaxBetTextField.getText()) > 0 ){   //und größer null
                MaxBetTextField.setStyle("-fx-control-inner-background: white;");
                Model.setMaxBet(Integer.parseInt(MaxBetTextField.getText()));   //wert in Model speichern
            }else{                                                              //falls eines der beiden nicht zutriff rot markieren
                validInputs = false;                                            //und spielstart verhindern
                MaxBetTextField.setStyle("-fx-control-inner-background: red;");
            }

        }else {
            validInputs = false;
            MaxBetTextField.setStyle("-fx-control-inner-background: red;");
        }

        if(isDouble(StartCreditTextField.getText())){                                   //Ist es ein Double
            if(Double.parseDouble(StartCreditTextField.getText()) > Model.getMinBet()){     //und größer als der minimaleinsatz
                Model.setStartCredit(Double.parseDouble(StartCreditTextField.getText()));   //wert im modell speichern
                StartCreditTextField.setStyle("-fx-control-inner-background: white;");
            }else{
                StartCreditTextField.setStyle("-fx-control-inner-background: red;");        //falls eines der beiden nicht zutriff rot markieren
                validInputs = false;                                                        //und spielstart verhindern
            }
        }else{
            StartCreditTextField.setStyle("-fx-control-inner-background: red;");
            validInputs = false;
        }

        if(validInputs){
            Model.setGamestatus(0);
            Model.setCardCounter(0);
            BlackJackView BJView = new BlackJackView();
            BJView.start(Model);
        }

    }

    private boolean isDouble(String str) {           //Methode zur prüfung bo ein Double wert eingegeben wurde
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInt(String str){              //Methode zur prüfung ob ein integer eingegeben wurde
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    Button QuitBtn;

    @FXML
    public void Quit(){
        System.exit(0);
    }
}
