package BlackJack.controller;

import BlackJack.Model.BlackJackModel;
import BlackJack.Model.Card;
import BlackJack.Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
                   // drawCard(Model.getBank(), 1); // Die Bank zieht nur eine Karte
                   // drawStartCards();    //Zu beginn des Spiels wwerden 2 karten an alle Spieler vergeben
                    Model.setGamestatus(5);
                    break;
                case 5:
                   // anotherCard();    //Jeden spieler fragen ob er weitere Karten haben möchte
                    Model.setGamestatus(6);
                    break;
                case 6:
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
        for(int i=0; i < Model.getNumberOfPlayers(); i++){
            Player newPlayer = new Player(Model.getStartCredit());
            newPlayer.setName("Player " + i);
            switch(i){
                case 0:
                    newPlayer.setBetField(Player1BetField);
                    newPlayer.setBetButton(Player1BetBtn);
                    newPlayer.setTakeCardButton(Player1CardBtn);
                    newPlayer.setDoubleButton(Player1DoubleBtn);
                    newPlayer.setNotDoubleButton(Player1NotDoubleBtn);
                    break;
                case 1:
                    newPlayer.setBetField(Player2BetField);
                    newPlayer.setBetButton(Player2BetBtn);
                    newPlayer.setTakeCardButton(Player2CardBtn);
                    newPlayer.setDoubleButton(Player2DoubleBtn);
                    newPlayer.setNotDoubleButton(Player2NotDoubleBtn);
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
        for(Player player: Model.getPlayers()){
            drawCard(player, 2);
        }
    }

    private void drawCard(Player player , int numberOfCards) {
        for(int i=0; i< numberOfCards; i++) {
            player.setCard(Model.getCardDeck().drawCard(Model.getCardCounter()));
            Model.setCardCounter(Model.getCardCounter()+1);
        }
    }

    private void showPlayerCards(Player player){
        System.out.println(player.getName() + "'s cards:");
        for(int j =0; j < player.getHand().size(); j++){
            System.out.println(player.getCard(j).getType() +" " + player.getCard(j).getName());
        }
        checkCardValues(player);
    }

    private void doubleBet(Player player){
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
            showPlayerCards(Model.getBank());            //karte der Bank anzeigen
            showPlayerCards(player);            //Karten des aktuellen spielers zeigen

            if(player.getHand().size() == 2 && !player.BJ()) {  //der Spieler kann nur nach den ersten beiden karten verdoppeln und wenn er keinen blackjack hat
                doubleBet(player);
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
        showPlayerCards(Model.getBank());            //Die karten der Bank werden angezeigt
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
                showPlayerCards(Model.getBank());            //Die karten der Bank werden angezeigt
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
    public void Player1Bet(){
        double bet = Double.parseDouble(Player1BetField.getText());
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){
            GameStatusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
        }else{
            Model.getPlayers().get(0).setBet(bet);
            Model.getPlayers().get(0).setCredit(Model.getPlayers().get(0).getCredit() - bet);
            Player1BetField.setEditable(false);
            Player1BetBtn.setDisable(true);
            Player1CreditLabel.setText(String.valueOf(Model.getPlayers().get(0).getCredit()));
        }
        betCounter();
    }

    @FXML
    public void Player2Bet(){
        double bet = Double.parseDouble(Player2BetField.getText());
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){
            GameStatusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
        }else{
            Model.getPlayers().get(1).setBet(bet);
            Model.getPlayers().get(1).setCredit(Model.getPlayers().get(1).getCredit() - bet);
            Player2BetField.setEditable(false);
            Player2BetBtn.setDisable(true);
            Player2CreditLabel.setText(String.valueOf(Model.getPlayers().get(1).getCredit()));
        }
        betCounter();
    }

    @FXML
    public void Player3Bet(){
        double bet = Double.parseDouble(Player3BetField.getText());
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){
            GameStatusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
        }else{
            Model.getPlayers().get(2).setBet(bet);
            Model.getPlayers().get(2).setCredit(Model.getPlayers().get(2).getCredit() - bet);
            Player3BetField.setEditable(false);
            Player3BetBtn.setDisable(true);
            Player3CreditLabel.setText(String.valueOf(Model.getPlayers().get(2).getCredit()));
        }
        betCounter();
    }

    @FXML
    public void Player4Bet(){
        double bet = Double.parseDouble(Player4BetField.getText());
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){
            GameStatusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
        }else{
            Model.getPlayers().get(3).setBet(bet);
            Model.getPlayers().get(3).setCredit(Model.getPlayers().get(3).getCredit() - bet);
            Player4BetField.setEditable(false);
            Player4BetBtn.setDisable(true);
            Player4CreditLabel.setText(String.valueOf(Model.getPlayers().get(3).getCredit()));
        }
        betCounter();
    }

    @FXML
    public void Player5Bet(){
        double bet = Double.parseDouble(Player5BetField.getText());
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){
            GameStatusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
        }else{
            Model.getPlayers().get(4).setBet(bet);
            Model.getPlayers().get(4).setCredit(Model.getPlayers().get(4).getCredit() - bet);
            Player5BetField.setEditable(false);
            Player5BetBtn.setDisable(true);
            Player5CreditLabel.setText(String.valueOf(Model.getPlayers().get(4).getCredit()));
        }
        betCounter();
    }

    @FXML
    public void Player6Bet(){
        double bet = Double.parseDouble(Player6BetField.getText());
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){
            GameStatusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
        }else{
            Model.getPlayers().get(5).setBet(bet);
            Model.getPlayers().get(5).setCredit(Model.getPlayers().get(5).getCredit() - bet);
            Player6BetField.setEditable(false);
            Player6BetBtn.setDisable(true);
            Player6CreditLabel.setText(String.valueOf(Model.getPlayers().get(5).getCredit()));
        }
        betCounter();
    }

    public void betCounter(){
        int betCounter = 0;
        for(Player player : Model.getPlayers()){
            if(player.getBet() >= 10){
                betCounter++;
            }
        }
        if(betCounter == Model.getPlayers().size()) Model.setGamestatus(4);;
        GameStateHandler();
    }

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
