package BlackJack.controller;

import BlackJack.Model.BlackJackModel;
import BlackJack.Model.Card;
import BlackJack.Model.Player;
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
                    Model.setGamestatus(2);
                    GameStateHandler();
                    break;
                case 1:
                    unsetPlayerVars();       //unset all player vars
                    Model.setGamestatus(2);
                    break;
                case 2:
                    //CardDeck = new CardDeck(Model.getNumberOfCardDecks()); //kartendeck erstellen
                    Model.createCardDeck(6);
                    Model.setGamestatus(3);
                    GameStateHandler();
                    break;
                case 3:
                    GameStatusLabel.setText("Please place your bets and press ok!");
                    break;
                case 4:
                    System.out.println("draw Cards");
                    drawStartCards();    //Zu beginn des Spiels werden 2 karten an alle Spieler vergeben und eine an die bank
                    Model.setGamestatus(5);
                    GameStateHandler();
                    break;
                case 5:
                    doubleBet();
                   // anotherCard();    //Jeden spieler fragen ob er weitere Karten haben möchte
                    Model.setGamestatus(6);
                    break;
                case 6:
                    System.out.println("another card");
                  //  BanksTurn();    //Nachdem alle Spieler ihre karten erhalten haben ist die bank dran
                    Model.setGamestatus(7);
                    break;
                case 7:
                    //GameResult();  //Ist die Bank fertig wird das Ergebnis der spielrunde geprüft und angezeigt
                    Model.setGamestatus(8);
                    break;
                case 8:
                    continueGame();  //Ist die Bank fertig wird das Ergebnis der spielrunde geprüft und angezeigt
                    if(playersLeft()) {
                        Model.setGamestatus(1);
                    }else Model.setGamestatus(9);
                    break;
            }
        }


    private void definePlayers(){               //Anzahl der Spieler abfragen, sowie deren namen

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
                    newPlayer.setDoubleButton(Player1DoubleBtn);
                    newPlayer.setNotDoubleButton(Player1NotDoubleBtn);
                    newPlayer.setCreditLabel(Player1CreditLabel);
                    break;
                case 1:
                    newPlayer.setCardPane(Player2CardPane);
                    newPlayer.setBetField(Player2BetField);
                    newPlayer.setBetButton(Player2BetBtn);
                    newPlayer.setTakeCardButton(Player2CardBtn);
                    newPlayer.setDoubleButton(Player2DoubleBtn);
                    newPlayer.setNotDoubleButton(Player2NotDoubleBtn);
                    newPlayer.setCreditLabel(Player2CreditLabel);
                    break;
                case 2:
                    newPlayer.setCardPane(Player3CardPane);
                    newPlayer.setBetField(Player3BetField);
                    newPlayer.setBetButton(Player3BetBtn);
                    newPlayer.setTakeCardButton(Player3CardBtn);
                    newPlayer.setDoubleButton(Player3DoubleBtn);
                    newPlayer.setNotDoubleButton(Player3NotDoubleBtn);
                    newPlayer.setCreditLabel(Player3CreditLabel);
                    break;
                case 3:
                    newPlayer.setCardPane(Player4CardPane);
                    newPlayer.setBetField(Player4BetField);
                    newPlayer.setBetButton(Player4BetBtn);
                    newPlayer.setTakeCardButton(Player4CardBtn);
                    newPlayer.setDoubleButton(Player4DoubleBtn);
                    newPlayer.setNotDoubleButton(Player4NotDoubleBtn);
                    newPlayer.setCreditLabel(Player4CreditLabel);
                    break;
                case 4:
                    newPlayer.setCardPane(Player5CardPane);
                    newPlayer.setBetField(Player5BetField);
                    newPlayer.setBetButton(Player5BetBtn);
                    newPlayer.setTakeCardButton(Player5CardBtn);
                    newPlayer.setDoubleButton(Player5DoubleBtn);
                    newPlayer.setNotDoubleButton(Player5NotDoubleBtn);
                    newPlayer.setCreditLabel(Player5CreditLabel);
                    break;
                case 5:
                    newPlayer.setCardPane(Player6CardPane);
                    newPlayer.setBetField(Player6BetField);
                    newPlayer.setBetButton(Player6BetBtn);
                    newPlayer.setTakeCardButton(Player6CardBtn);
                    newPlayer.setDoubleButton(Player6DoubleBtn);
                    newPlayer.setNotDoubleButton(Player6NotDoubleBtn);
                    newPlayer.setCreditLabel(Player6CreditLabel);
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

    private void drawStartCards() { //jeder spieler zieht 2 karten
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
        for(Player player : Model.getPlayers()){    //Die Buttons zum verdoppeln einblenden
            if(player.getHand().size() == 2 && !player.BJ()) {
                player.getDoubleButton().setVisible(true);
                player.getNotDoubleButton().setVisible(true);
            }
        }

    /*    int[] validAnswers = new int[]{0,1};
        int answer = getUserInput("Do you want to double your bet? ", validAnswers );
        if(answer == 1){
            if((player.getBet()) > player.getCredit()){
                System.out.println("Sorry " + player.getName() + " you dont have enough credit to double up");
            }else {
                System.out.println("OK " + player.getName() + " your bet is doubled");
                player.setCredit(player.getCredit()-player.getBet()); //Credit des spielers um den erhöhten einsatz verringern
                player.setBet(player.getBet()*2);                     //Einsatz des spielers verdoppeln
                player.setDoubleBet(true);                            //Flag setzen das spieler verdoppelt hat
                drawCard(player, 1);                    //Spieler zieht karte
                showPlayerCards(player);                                //Karten des spielers zeigen
            }
        }
        */
    }

    private void anotherCard(){      //fragt die spieler solange nach weiteren karten wie sie nicht gewonnen, verloren haben oder keine weiteren karten haben wollen
        int[] validAnswers = {0,1};
        for(Player player : Model.getPlayers()){
       //     showPlayerCards(Model.getBank());            //karte der Bank anzeigen
       //     showPlayerCards(player);            //Karten des aktuellen spielers zeigen

            if(player.getHand().size() == 2 && !player.BJ()) {  //der Spieler kann nur nach den ersten beiden karten verdoppeln und wenn er keinen blackjack hat
             //   doubleBet(player);
            }
  /*
            while(!player.isOut() && !player.isStand() && !player.BJ() && !player.DoubleBet()){
                int answer = getUserInput(player.getName() + " do you want another card?",validAnswers);
                if(answer == 1){
                    drawCard(player,1);
                    showPlayerCards(player);            //Karten des aktuellen spielers zeigen
                }else{
                    player.setStand(true);
                }
            }
            */
        }
    }

    private void BanksTurn(){
        drawCard(Model.getBank(),1);                   //zunächst zieht die Bank eine weitere Karte
       // showPlayerCards(Model.getBank());            //Die karten der Bank werden angezeigt
        int handValue = 0;
        handValue = getHandValue(Model.getBank());

        while(!Model.getBank().BJ() && !Model.getBank().isStand() && !Model.getBank().isOut()){
            if(handValue > 21){                     //hat die Bank mehr als 21 ist sie raus
                Model.getBank().setOut(true);
                System.out.println("The bank is out");
            }else if(handValue == 21){              //bei 21 Blackjack
                Model.getBank().setBJ(true);
                System.out.println("The bank has BlackJack");
            }else if(handValue < 17){                     //bei weniger als 17 -> karte ziehen
                System.out.println("The bank takes another Card");
                drawCard(Model.getBank(),1);
                //showPlayerCards(Model.getBank());            //Die karten der Bank werden angezeigt
                handValue = getHandValue(Model.getBank());     //und hand wert berechnen
            }else{                                  //ansonsten stehen
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
                        System.out.println(player.getName() + "gets back his bet of " + player.getBet());
                    }else{                                                      //Spieler hat BJ aber bank nicht
                        winnings = player.getBet()*2.5;                           //Spieler erhält dreifachen einsatz
                        player.setCredit(player.getCredit() + winnings);
                        System.out.println(player.getName() + " wins " + winnings);
                    }
                }else if(Model.getBank().isOut()){                                         //Ist die Bank über 21 und der spieler nicht
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    System.out.println(player.getName() + " wins " + winnings);
                }else if(getHandValue(player) == getHandValue(Model.getBank())){           //Spieler handwert = Bank
                    player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                    System.out.println(player.getName() + " gets back his bet of " + player.getBet());
                }else if(getHandValue(player) > getHandValue(Model.getBank()) ){
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    System.out.println(player.getName() + " wins " + winnings);
                }
            }
        }
    }

    private void continueGame(){
        int[] validAnswers = {0,1};
        ArrayList <Player> remPlayers = new ArrayList<>();
        for(Player player : Model.getPlayers()){
            if(player.getCredit() == 0){
                remPlayers.add(player);
                System.out.println("Sorry " + player.getName() + " your out. You have no money to play another round");
            }
/*           else if(getUserInput(player.getName() + " do you want to play another round?", validAnswers) == 0){
                remPlayers.add(player);
            }
*/
        }
        for(Player player : remPlayers){
            Model.getPlayers().remove(player);
        }
    }
    private void unsetPlayerVars(){
        for (Player player : Model.getPlayers()){
            player.getHand().clear();
            player.setBet(0);
            player.setStand(false);
            player.setBJ(false);
            player.setOut(false);
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
                System.out.println(player.getName() + " has reached 21 points. stand!");
            }

        }else if (handValue > 21) {
            System.out.println(player.getName() + " has more than 21 Points, hes out");
            player.setOut(true);
        }else{
            System.out.println(player.getName() + "'s cards total value: " + handValue);
        }
        System.out.println();
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
            System.out.println(playerID);
            System.out.println(bet);
            Model.getPlayers().get(playerID).getBetField().setStyle("-fx-control-inner-background: white;");    //bei gültiger wette hintergrund weiß setzen (falls vorher rot war)
            Model.getPlayers().get(playerID).setBet(bet);                                                       //Wette des Spielers speichern
            Model.getPlayers().get(playerID).setCredit(Model.getPlayers().get(playerID).getCredit() - bet);     //Wette vom guthaben abziehen
            Model.getPlayers().get(playerID).getBetField().setEditable(false);                                  //Button und textfeld deaktiveiren
            Model.getPlayers().get(playerID).getBetButton().setDisable(true);
            Model.getPlayers().get(playerID).getCreditLabel().setText(String.valueOf(Model.getPlayers().get(playerID).getCredit()));    //Und Anzeige des Guthabens aktualisieren
            betCounter();                                                                                       //Methode zum zählen der gesetzen Wetten aufrufen
        }
    }

    private int whoClickedTheButton(MouseEvent event){
        int playerID = 0;

        Button btn = (Button) event.getSource();    //Der MouseEvent gibt die ID des Buttons zurück der geklickt wurde
        String ButtonID = btn.getId();
        ButtonID = ButtonID.substring(0,7);
        System.out.println(ButtonID);
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

    public void betCounter(){
        int betCounter = 0;
        for(Player player : Model.getPlayers()){
            if(player.getBet() >= 10){
                betCounter++;
            }
        }
        if(betCounter == Model.getPlayers().size()) Model.setGamestatus(Model.getGamestatus()+1);;
        GameStateHandler();
    }

    public void doubleCounter(){
        int doubleCounter = 0;
        for(Player player : Model.getPlayers()){
            if(player.DoubleBet()){
                doubleCounter++;
            }
        }
        if(doubleCounter == Model.getPlayers().size()) Model.setGamestatus(Model.getGamestatus()+1);;
        GameStateHandler();
    }



    @FXML
    Pane BankCardPane;
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
}
