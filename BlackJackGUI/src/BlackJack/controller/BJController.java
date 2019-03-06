package BlackJack.controller;

import BlackJack.Model.BlackJackModel;
import BlackJack.Model.Card;
import BlackJack.Model.Player;
import BlackJack.View.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BJController {
    private BlackJackModel Model;

    public void setModel(BlackJackModel Model){
       this.Model = Model;
    }

    public void GameStateHandler(){

            switch (Model.getGamestatus()) {
                case 0:
                    definePlayers();    //Spieler festlegen
                    initGUI();
                    Model.setMovesThisTurn(0);
                    Model.setGamestatus(2);
                    GameStateHandler();
                    break;
                case 1:
                    unsetPlayerVars();       //unset all player vars

                    Model.setGamestatus(2);
                    GameStateHandler();
                    break;
                case 2:
                    Model.createCardDeck(6);    //neues kartendeck erstellen
                    Model.setGamestatus(3);
                    GameStateHandler();
                    break;
                case 3:                                          //Spieler auffordern ihre einsätze zu machen
                    GameStatusLabel.setText("Please place your bets and press ok!");
                    Model.setMovesThisTurn(0);
                    break;
                case 4:
                    drawStartCards();    //Zu beginn des Spiels werden 2 karten an alle Spieler vergeben und eine an die bank
                    Model.setGamestatus(5);
                    GameStateHandler();
                    break;
                case 5:                 //Nachdem die spieler ihre karten haben, können sie ihren einsatz verdoppeln
                    doubleBet();
                    break;
                case 6:
                    GameStatusLabel.setText("Do you want antoher Card?");
                    Model.setMovesThisTurn(0);
                    hideDoubleBet();
                    anotherCard();
                    break;
                case 7:
                    GameStatusLabel.setText("Banks Turn!");
                    BanksTurn();    //Nachdem alle Spieler ihre karten erhalten haben ist die bank dran
                    Model.setGamestatus(8);
                    GameStateHandler();
                    break;
                case 8:
                    GameResult();  //Ist die Bank fertig wird das Ergebnis der spielrunde geprüft und angezeigt
                    Model.setGamestatus(9);
                    GameStateHandler();
                    break;
                case 9:
                    GameStatusLabel.setText("Do you want to play another round?");
                    Model.setMovesThisTurn(0);
                    removeBrokePlayer();  //Ist die Bank fertig wird das Ergebnis der spielrunde geprüft und angezeigt
                    showPlayAgain();
                    break;
                case 10:
                    if(playersLeft()) {
                        Model.setGamestatus(1);
                        GameStateHandler();
                    }else {
                        try {
                            backToMainMenu();
                        }catch(Exception e){

                        }
                    }
                    break;
            }
        }


    private void definePlayers(){               //Ausgewählte anzahl an spielern erzeugen und ihnen die jeweiligen Panes,Buttons, TextFields und Co zuordnen
        Model.getBank().setName("Bank");
        Model.getBank().setCardPane(BankCardPane);
        for(int i=0; i < Model.getNumberOfPlayers(); i++){
            Player newPlayer = new Player(Model.getStartCredit());
            newPlayer.setName("Player " + i);
            switch(i){
                case 0:
                    newPlayer.setCardPane(Player1CardPane);
                    newPlayer.setBetField(Player1BetField);
                    newPlayer.setBetButton(Player1BetBtn);
                    newPlayer.setTakeCardButton(Player1CardBtn);
                    newPlayer.setStandButton(Player1StandBtn);
                    newPlayer.setDoubleButton(Player1DoubleBtn);
                    newPlayer.setNotDoubleButton(Player1NotDoubleBtn);
                    newPlayer.setCreditLabel(Player1CreditLabel);
                    newPlayer.setPlayAgainButton(Player1PlayAgainBtn);
                    newPlayer.setQuitButton(Player1QuitBtn);
                    break;
                case 1:
                    newPlayer.setCardPane(Player2CardPane);
                    newPlayer.setBetField(Player2BetField);
                    newPlayer.setBetButton(Player2BetBtn);
                    newPlayer.setTakeCardButton(Player2CardBtn);
                    newPlayer.setStandButton(Player2StandBtn);
                    newPlayer.setDoubleButton(Player2DoubleBtn);
                    newPlayer.setNotDoubleButton(Player2NotDoubleBtn);
                    newPlayer.setCreditLabel(Player2CreditLabel);
                    newPlayer.setPlayAgainButton(Player2PlayAgainBtn);
                    newPlayer.setQuitButton(Player2QuitBtn);
                    break;
                case 2:
                    newPlayer.setCardPane(Player3CardPane);
                    newPlayer.setBetField(Player3BetField);
                    newPlayer.setBetButton(Player3BetBtn);
                    newPlayer.setTakeCardButton(Player3CardBtn);
                    newPlayer.setStandButton(Player3StandBtn);
                    newPlayer.setDoubleButton(Player3DoubleBtn);
                    newPlayer.setNotDoubleButton(Player3NotDoubleBtn);
                    newPlayer.setCreditLabel(Player3CreditLabel);
                    newPlayer.setPlayAgainButton(Player3PlayAgainBtn);
                    newPlayer.setQuitButton(Player3QuitBtn);
                    break;
                case 3:
                    newPlayer.setCardPane(Player4CardPane);
                    newPlayer.setBetField(Player4BetField);
                    newPlayer.setBetButton(Player4BetBtn);
                    newPlayer.setTakeCardButton(Player4CardBtn);
                    newPlayer.setStandButton(Player4StandBtn);
                    newPlayer.setDoubleButton(Player4DoubleBtn);
                    newPlayer.setNotDoubleButton(Player4NotDoubleBtn);
                    newPlayer.setCreditLabel(Player4CreditLabel);
                    newPlayer.setPlayAgainButton(Player4PlayAgainBtn);
                    newPlayer.setQuitButton(Player4QuitBtn);
                    break;
                case 4:
                    newPlayer.setCardPane(Player5CardPane);
                    newPlayer.setBetField(Player5BetField);
                    newPlayer.setBetButton(Player5BetBtn);
                    newPlayer.setTakeCardButton(Player5CardBtn);
                    newPlayer.setStandButton(Player5StandBtn);
                    newPlayer.setDoubleButton(Player5DoubleBtn);
                    newPlayer.setNotDoubleButton(Player5NotDoubleBtn);
                    newPlayer.setCreditLabel(Player5CreditLabel);
                    newPlayer.setPlayAgainButton(Player5PlayAgainBtn);
                    newPlayer.setQuitButton(Player5QuitBtn);
                    break;
                case 5:
                    newPlayer.setCardPane(Player6CardPane);
                    newPlayer.setBetField(Player6BetField);
                    newPlayer.setBetButton(Player6BetBtn);
                    newPlayer.setTakeCardButton(Player6CardBtn);
                    newPlayer.setStandButton(Player6StandBtn);
                    newPlayer.setDoubleButton(Player6DoubleBtn);
                    newPlayer.setNotDoubleButton(Player6NotDoubleBtn);
                    newPlayer.setCreditLabel(Player6CreditLabel);
                    newPlayer.setPlayAgainButton(Player6PlayAgainBtn);
                    newPlayer.setQuitButton(Player6QuitBtn);
                    break;
            }
            Model.getPlayers().add(newPlayer);
        }
    }

    private void initGUI(){
        for(int i=0; i < Model.getNumberOfPlayers(); i++){
            switch(i) {
                case 0:
                    Player1Pane.setVisible(true);
                    Player1CreditLabel.setText(String.valueOf(Model.getPlayers().get(i).getCredit()));
                    break;
                case 1:
                    Player2Pane.setVisible(true);
                    Player2CreditLabel.setText(String.valueOf(Model.getPlayers().get(i).getCredit()));
                    break;
                case 2:
                    Player3Pane.setVisible(true);
                    Player3CreditLabel.setText(String.valueOf(Model.getPlayers().get(i).getCredit()));

                    break;
                case 3:
                    Player4Pane.setVisible(true);
                    Player4CreditLabel.setText(String.valueOf(Model.getPlayers().get(i).getCredit()));
                    break;
                case 4:
                    Player5Pane.setVisible(true);
                    Player5CreditLabel.setText(String.valueOf(Model.getPlayers().get(i).getCredit()));
                    break;
                case 5:
                    Player6Pane.setVisible(true);
                    Player6CreditLabel.setText(String.valueOf(Model.getPlayers().get(i).getCredit()));
                    break;
            }
        }
    }

    private void drawStartCards() { //jeder spieler zieht 2 karten und die bank eine
        drawCard(Model.getBank(),1);
        showPlayerCards(Model.getBank());
        for(Player player: Model.getPlayers()){
            drawCard(player, 2);
            checkCardValues(player);
            showPlayerCards(player);
        }
    }

    private void drawCard(Player player , int numberOfCards) {
        for(int i=0; i< numberOfCards; i++) {
            player.setCard(Model.getCardDeck().drawCard(Model.getCardCounter()));
            Model.setCardCounter(Model.getCardCounter()+1);
        }
    }

    private void showPlayerCards(Player player){
            player.getCardPane().getChildren().clear();
            int cardPos = 30;
            for (Card card : player.getHand()){
                Rectangle GUIcard = new Rectangle(60,120);
                GUIcard.setX(cardPos);
                GUIcard.setFill(new ImagePattern(card.getImg()));
                player.getCardPane().getChildren().add(GUIcard);
                cardPos += 30;
            }
    }

        private void doubleBet(){
        GameStatusLabel.setText("Do you want to double your bet?");
        for(Player player : Model.getPlayers()){
            if(player.getHand().size() == 2 && !player.BJ() && (player.getBet() < player.getCredit())) {    //Spieler kann nur verdoppeln wenn: er 2 karten hat, keinen blackjack und noch genug credits
                player.getDoubleButton().setVisible(true);                                                  //Die Buttons zum verdoppeln einblenden
                player.getNotDoubleButton().setVisible(true);
            }else{
                Model.setMovesThisTurn(Model.getMovesThisTurn()+1);                                         //Kann ein spieler nicht verdoppeln, wird die Anzahl der Spielzüge der runde um eins erhöht, da der spieler automatisch nicht verdoppelt
                System.out.println(Model.getMovesThisTurn());
            }
        }
    }

    private void hideDoubleBet(){
        for(Player player : Model.getPlayers()) {
            player.getDoubleButton().setVisible(false);                                                  //Die Buttons zum verdoppeln ausblenden
            player.getNotDoubleButton().setVisible(false);
        }
    }

    private void anotherCard(){
        for(Player player : Model.getPlayers()){
            if(!player.isStand() && !player.BJ() && !player.isOut()){
                player.getTakeCardButton().setVisible(true);                                                  //Die Buttons zum karte ziehen anzeigen
                player.getStandButton().setVisible(true);
            }else{
                countMoves();
            }
        }
    }

    private int getHandValue(Player player){
        int handValue = 0;                      //HandWert init
        int aces = 0;
        for(Card card : player.getHand()){      //alle Karten eines spieler durchlaufen
            handValue += card.getValue();       //kartenwerte addieren
            if(card.getValue() == 1) aces++; //Anzahl asse zählen
        }
        if((aces == 1) && (handValue + 10) <= 21){                           //Wenn nur ein ass & und der Handwert +10 kleiner 21
            handValue += 10;                                                //Addiere für das ass 10 auf den Handwert (Ass ist entweder 1 oder 11 -> 1 ist der defaultwert für ein ass welcher in der for schleife bereits einberechnet wurde, daher nur +10)
        }
        return handValue;
    }

    private void checkCardValues(Player player){
        int handValue = getHandValue(player);
        if(handValue == 21){                    //Kartenwert von 21
            if(player.getHand().size() < 3){    //und max. 2 karten
                player.setBJ(true);             //BlackJack!
                System.out.println(player.getName() + " has a BlackJack!");
            }else{
                player.setStand(true);          //Kartenwert 21 und mehr als 3 karten -> stand
                player.getTakeCardButton().setVisible(false);                                  //Buttons ausblenden
                player.getStandButton().setVisible(false);
                countMoves();
                System.out.println(player.getName() + " has reached 21 points. stand!");
            }

        }else if (handValue > 21) {
            System.out.println(player.getName() + " has more than 21 Points, hes out");
            player.setOut(true);
            player.getTakeCardButton().setVisible(false);                                  //Buttons ausblenden
            player.getStandButton().setVisible(false);
            countMoves();
        }else{
            System.out.println(player.getName() + "'s cards total value: " + handValue);
        }
        System.out.println();
    }

    private void BanksTurn(){
        drawCard(Model.getBank(),1);                   //zunächst zieht die Bank eine weitere Karte
        showPlayerCards(Model.getBank());            //Die karten der Bank werden angezeigt
        int handValue = 0;
        handValue = getHandValue(Model.getBank());

        while(!Model.getBank().BJ() && !Model.getBank().isStand() && !Model.getBank().isOut()){
            if(handValue > 21){                     //hat die Bank mehr als 21 ist sie raus
                Model.getBank().setOut(true);
                System.out.println("The bank is out");
                Label Info = new Label();
                Info.setText("The Bank has more the 21 points. The Bank has lost");
                GameStatusPane.getChildren().add(Info);
            }else if(handValue == 21){              //bei 21 Blackjack
                Model.getBank().setBJ(true);
                Label Info = new Label();
                Info.setText("The Bank has  21 points");
                GameStatusPane.getChildren().add(Info);
                System.out.println("The bank has BlackJack");
            }else if(handValue < 17){                     //bei weniger als 17 -> karte ziehen
                System.out.println("The bank takes another Card");
                drawCard(Model.getBank(),1);
                showPlayerCards(Model.getBank());            //Die karten der Bank werden angezeigt
                handValue = getHandValue(Model.getBank());     //und hand wert berechnen
            }else{                                  //ansonsten stehen
                Label Info = new Label();
                Info.setText("The Bank stands with " + handValue + " points");
                GameStatusPane.getChildren().add(Info);
                System.out.println("The bank stands");
                Model.getBank().setStand(true);
            }
        }
    }

    private void GameResult(){
        double winnings;
        for (Player player : Model.getPlayers()){
            if(!player.isOut()){

                if(player.BJ()){                                              //Spieler hat BlackJack
                    if(Model.getBank().BJ()){                                      //und Bank auch
                        player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                        //System.out.println(player.getName() + "gets back his bet of " + player.getBet());
                    }else{                                                      //Spieler hat BJ aber bank nicht
                        winnings = player.getBet()*2.5;                           //Spieler erhält dreifachen einsatz
                        player.setCredit(player.getCredit() + winnings);
                       // System.out.println(player.getName() + " wins " + winnings);
                    }
                }else if(Model.getBank().isOut()){                                         //Ist die Bank über 21 und der spieler nicht
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    //System.out.println(player.getName() + " wins " + winnings);
                }else if(getHandValue(player) == getHandValue(Model.getBank())){           //Spieler handwert = Bank
                    player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                    //System.out.println(player.getName() + " gets back his bet of " + player.getBet());
                }else if(getHandValue(player) > getHandValue(Model.getBank()) ){
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    //System.out.println(player.getName() + " wins " + winnings);
                }
                player.getCreditLabel().setText(String.valueOf(player.getCredit()));
            }
        }
    }
    private void showPlayAgain(){
        for(Player player : Model.getPlayers()){
            player.getPlayAgainButton().setVisible(true);
            player.getQuitButton().setVisible(true);
        }
    }

    private void removeBrokePlayer(){

        ArrayList <Player> remPlayers = new ArrayList<>();
        for(Player player : Model.getPlayers()){
            if(player.getCredit() == 0){
                remPlayers.add(player);
            }
        }
        for(Player player : remPlayers){
            Model.getPlayers().remove(player);
        }
    }

    @FXML
    private void PlayAgain(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        Model.getPlayers().get(playerID).getPlayAgainButton().setVisible(false);                                  //Buttons ausblenden
        Model.getPlayers().get(playerID).getQuitButton().setVisible(false);
        countMoves();
    }

    @FXML
    private void QuitPlayer(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        Model.getPlayers().get(playerID).getPlayAgainButton().setVisible(false);                                  //Buttons ausblenden
        Model.getPlayers().get(playerID).getQuitButton().setVisible(false);
        Player removeMe = Model.getPlayers().get(playerID);
        Model.getPlayers().remove(removeMe);
        countMoves();
    }

    @FXML
    private void takeCard(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        drawCard(Model.getPlayers().get(playerID),1);
        checkCardValues(Model.getPlayers().get(playerID));
        showPlayerCards(Model.getPlayers().get(playerID));
    }

    @FXML
    private void stand(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        Model.getPlayers().get(playerID).setStand(true);                                                          //Spieler auf Stand setzen
        Model.getPlayers().get(playerID).getTakeCardButton().setVisible(false);                                  //Buttons ausblenden
        Model.getPlayers().get(playerID).getStandButton().setVisible(false);
        countMoves();
        System.out.println(Model.getMovesThisTurn());
    }

    @FXML
     private void doublePlayerBet(MouseEvent event){  //Methode verarbeitet die Wetteingaben der Spieler. Über den MouseEvent wird die Quelle des Klicks bestimmt
        int playerID = whoClickedTheButton(event);
        double bet = Model.getPlayers().get(playerID).getBet();

            Model.getPlayers().get(playerID).setCredit(Model.getPlayers().get(playerID).getCredit() - bet);     //Wette vom guthaben abziehen
            Model.getPlayers().get(playerID).setBet(bet * 2);                                                   //Wette verdoppeln
            Model.getPlayers().get(playerID).setDoubleBet(true);
            Model.getPlayers().get(playerID).getBetField().setText(String.valueOf(Model.getPlayers().get(playerID).getBet()));
            Model.getPlayers().get(playerID).getDoubleButton().setVisible(false);                                  //Button und textfeld deaktiveiren
            Model.getPlayers().get(playerID).getNotDoubleButton().setVisible(false);
            Model.getPlayers().get(playerID).getCreditLabel().setText(String.valueOf(Model.getPlayers().get(playerID).getCredit()));    //Und Anzeige des Guthabens aktualisieren
            countMoves();                                                                                       //Methode zum zählen der gesetzen Wetten aufrufen
    }

    @FXML
    private void notDoublePlayerBet(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        Model.getPlayers().get(playerID).getDoubleButton().setVisible(false);                                  //Button und textfeld deaktiveiren
        Model.getPlayers().get(playerID).getNotDoubleButton().setVisible(false);
        countMoves();
    }

    @FXML
    private void PlayerBets(MouseEvent event){  //Methode verarbeitet die Wetteingaben der Spieler. Über den MouseEvent wird die Quelle des Klicks bestimmt
        int playerID = whoClickedTheButton(event);
        double bet = 0;

        bet = Double.parseDouble(Model.getPlayers().get(playerID).getBetField().getText());                     //Wert aus Textfeld jeweiligem auslesen
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){                                                //Prüfen ob Wette nicht zu klein oder groß ist
            GameStatusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
            Model.getPlayers().get(playerID).getBetField().setStyle("-fx-control-inner-background: red;");      //Meldung + hintergrund falls ungültige wette
        }else {

            Model.getPlayers().get(playerID).getBetField().setStyle("-fx-control-inner-background: white;");    //bei gültiger wette hintergrund weiß setzen (falls vorher rot war)
            Model.getPlayers().get(playerID).setBet(bet);                                                       //Wette des Spielers speichern
            Model.getPlayers().get(playerID).setCredit(Model.getPlayers().get(playerID).getCredit() - bet);     //Wette vom guthaben abziehen
            Model.getPlayers().get(playerID).getBetField().setEditable(false);                                  //Button und textfeld deaktiveiren
            Model.getPlayers().get(playerID).getBetButton().setDisable(true);
            Model.getPlayers().get(playerID).getCreditLabel().setText(String.valueOf(Model.getPlayers().get(playerID).getCredit()));    //Und Anzeige des Guthabens aktualisieren
            countMoves();                                                                                       //Methode zum zählen der gesetzen Wetten aufrufen
        }
    }

    private int whoClickedTheButton(MouseEvent event){
        int playerID = 0;

        Button btn = (Button) event.getSource();    //Der MouseEvent gibt die ID des Buttons zurück der geklickt wurde
        String ButtonID = btn.getId();
        ButtonID = ButtonID.substring(0,7);

        switch (ButtonID){                      //Anhand der ID wird festgelegt welcher SPieler geklickt hat
            case "Player1":
                playerID = 0;
                break;
            case "Player2":
                playerID = 1;
                break;

            case "Player3":
                playerID = 2;
                break;

            case "Player4":
                playerID = 3;
                break;

            case "Player5":
                playerID = 4;
                break;

            case "Player6":
                playerID = 5;
                break;
        }
        return playerID;
    }

    private void countMoves(){
        Model.setMovesThisTurn(Model.getMovesThisTurn() +1);
        System.out.println(Model.getMovesThisTurn());
        System.out.println(Model.getNumberOfPlayers());
        if(Model.getMovesThisTurn() == Model.getNumberOfPlayers()) {
            Model.setMovesThisTurn(0);
            Model.setGamestatus(Model.getGamestatus()+1);
            GameStateHandler();
        }
    }

    @FXML
    private void backToMainMenu() throws Exception{
        unsetPlayerVars();
        Model.getPlayers().clear();
        MainMenuView MainMenu = new MainMenuView(Model);
    }

    @FXML
    Pane BankCardPane;
    @FXML
    Pane GameStatusPane;
    @FXML
    Label GameStatusLabel;
    @FXML
    Pane Player1Pane;
    @FXML
    Pane Player1CardPane;
    @FXML
    Label Player1CreditLabel;
    @FXML
    TextField Player1BetField;
    @FXML
    Button Player1BetBtn;
    @FXML
    Button Player1CardBtn;
    @FXML
    Button Player1StandBtn;
    @FXML
    Button Player1DoubleBtn;
    @FXML
    Button Player1NotDoubleBtn;
    @FXML
    Button Player1PlayAgainBtn;
    @FXML
    Button Player1QuitBtn;


    @FXML
    Pane Player2Pane;
    @FXML
    Pane Player2CardPane;
    @FXML
    Label Player2CreditLabel;
    @FXML
    TextField Player2BetField;
    @FXML
    Button Player2BetBtn;
    @FXML
    Button Player2CardBtn;
    @FXML
    Button Player2StandBtn;
    @FXML
    Button Player2DoubleBtn;
    @FXML
    Button Player2NotDoubleBtn;
    @FXML
    Button Player2PlayAgainBtn;
    @FXML
    Button Player2QuitBtn;

    @FXML
    Pane Player3Pane;
    @FXML
    Pane Player3CardPane;
    @FXML
    Label Player3CreditLabel;
    @FXML
    TextField Player3BetField;
    @FXML
    Button Player3BetBtn;
    @FXML
    Button Player3CardBtn;
    @FXML
    Button Player3StandBtn;
    @FXML
    Button Player3DoubleBtn;
    @FXML
    Button Player3NotDoubleBtn;
    @FXML
    Button Player3PlayAgainBtn;
    @FXML
    Button Player3QuitBtn;

    @FXML
    Pane Player4Pane;
    @FXML
    Pane Player4CardPane;
    @FXML
    Label Player4CreditLabel;
    @FXML
    TextField Player4BetField;
    @FXML
    Button Player4BetBtn;
    @FXML
    Button Player4CardBtn;
    @FXML
    Button Player4StandBtn;
    @FXML
    Button Player4DoubleBtn;
    @FXML
    Button Player4NotDoubleBtn;
    @FXML
    Button Player4PlayAgainBtn;
    @FXML
    Button Player4QuitBtn;

    @FXML
    Pane Player5Pane;
    @FXML
    Pane Player5CardPane;
    @FXML
    Label Player5CreditLabel;
    @FXML
    TextField Player5BetField;
    @FXML
    Button Player5BetBtn;
    @FXML
    Button Player5CardBtn;
    @FXML
    Button Player5StandBtn;
    @FXML
    Button Player5DoubleBtn;
    @FXML
    Button Player5NotDoubleBtn;
    @FXML
    Button Player5PlayAgainBtn;
    @FXML
    Button Player5QuitBtn;

    @FXML
    Pane Player6Pane;
    @FXML
    Label Player6CreditLabel;
    @FXML
    Pane Player6CardPane;
    @FXML
    TextField Player6BetField;
    @FXML
    Button Player6BetBtn;
    @FXML
    Button Player6CardBtn;
    @FXML
    Button Player6StandBtn;
    @FXML
    Button Player6DoubleBtn;
    @FXML
    Button Player6NotDoubleBtn;
    @FXML
    Button Player6PlayAgainBtn;
    @FXML
    Button Player6QuitBtn;

    @FXML
    Button backToMainMenuBtn;

    private void unsetPlayerVars(){
        for (Player player : Model.getPlayers()){
            player.getHand().clear();
            player.setBet(0);
            player.setStand(false);
            player.setBJ(false);
            player.setOut(false);
            player.getBetButton().setDisable(false);
            player.getBetField().setEditable(true);
            player.getCardPane().getChildren().clear();
        }
        Model.getBank().getHand().clear();
        Model.getBank().setStand(false);
        Model.getBank().setBJ(false);
        Model.getBank().setOut(false);

    }

    private boolean playersLeft(){
        if(Model.getPlayers().size() == 0) return false;
        return true;
    }







/*
    private void anotherCard(){      //fragt die spieler solange nach weiteren karten wie sie nicht gewonnen, verloren haben oder keine weiteren karten haben wollen
        int[] validAnswers = {0,1};
        for(Player player : Model.getPlayers()){
            //     showPlayerCards(Model.getBank());            //karte der Bank anzeigen
            //     showPlayerCards(player);            //Karten des aktuellen spielers zeigen

            if(player.getHand().size() == 2 && !player.BJ()) {  //der Spieler kann nur nach den ersten beiden karten verdoppeln und wenn er keinen blackjack hat
                //   doubleBet(player);
            }

            while(!player.isOut() && !player.isStand() && !player.BJ() && !player.DoubleBet()){
                int answer = getUserInput(player.getName() + " do you want another card?",validAnswers);
                if(answer == 1){
                    drawCard(player,1);
                    showPlayerCards(player);            //Karten des aktuellen spielers zeigen
                }else{
                    player.setStand(true);
                }
            }

        }
    } */
}
