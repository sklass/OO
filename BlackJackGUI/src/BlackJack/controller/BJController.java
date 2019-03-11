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

    private Pane playerPane = new Pane();
    private Pane cardPane = new Pane();
    private Label statusLabel = new Label();
    private Label creditLabel = new Label();
    private Label cardValueLabel = new Label();
    private TextField betField = new TextField();
    private Button betButton = new Button();
    private Button cardButton = new Button();
    private Button standButton = new Button();
    private Button doubleButton = new Button();
    private Button notDoubleButton = new Button();
    private Button playAgainButton = new Button();
    private Button quitButton = new Button();


    public void setModel(BlackJackModel Model){
       this.Model = Model;
    }

    public void GameStateHandler(){

            switch (Model.getGamestatus()) {
                case 0:
                    definePlayers();    //Spieler festlegen
                    initGUI();
                    Model.createCardDeck(6);    //neues kartendeck erstellen
                    Model.setGamestatus(2);
                    GameStateHandler();
                    break;
                case 1:
                    unsetPlayerVars();       //unset all player vars
                    Model.setGamestatus(2);
                    GameStateHandler();
                    break;
                case 2:
                    Model.getCardDeck().shuffle();
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
                    showDoubleBet();
                    break;
                case 6:
                    GameStatusLabel.setText("Do you want another Card?");
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
                    showPlayAgain();
                    break;
                case 10:
                    removePlayer();
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
        Model.getBank().setID(6);
        for(int i=0; i < Model.getNumberOfPlayers(); i++){
            Player newPlayer = new Player(Model.getStartCredit());
            newPlayer.setID(i);
            Model.getPlayers().add(newPlayer);
        }
    }

    private void initGUI(){
        for(int i=0; i < Model.getNumberOfPlayers(); i++){
            switch(i) {
                case 0:
                    Player1Pane.setVisible(true);
                    Player1StatusLabel.setText("Place your bet and press OK");
                    Player1CreditLabel.setText(String.valueOf(getPlayer(i).getCredit()));
                    break;
                case 1:
                    Player2Pane.setVisible(true);
                    Player2StatusLabel.setText("Place your bet and press OK");
                    Player2CreditLabel.setText(String.valueOf(getPlayer(i).getCredit()));
                    break;
                case 2:
                    Player3Pane.setVisible(true);
                    Player3StatusLabel.setText("Place your bet and press OK");
                    Player3CreditLabel.setText(String.valueOf(getPlayer(i).getCredit()));

                    break;
                case 3:
                    Player4Pane.setVisible(true);
                    Player4StatusLabel.setText("Place your bet and press OK");
                    Player4CreditLabel.setText(String.valueOf(getPlayer(i).getCredit()));
                    break;
                case 4:
                    Player5Pane.setVisible(true);
                    Player5StatusLabel.setText("Place your bet and press OK");
                    Player5CreditLabel.setText(String.valueOf(getPlayer(i).getCredit()));
                    break;
                case 5:
                    Player6Pane.setVisible(true);
                    Player6StatusLabel.setText("Place your bet and press OK");
                    Player6CreditLabel.setText(String.valueOf(getPlayer(i).getCredit()));
                    break;
            }
        }
    }

    @FXML
    private void PlayerBets(MouseEvent event){  //Methode verarbeitet die Wetteingaben der Spieler. Über den MouseEvent wird die Quelle des Klicks bestimmt
        double bet = 0;
        int playerID = whoClickedTheButton(event);
        setPlayerUI(playerID);

        if(isDouble(betField.getText())) {
            bet = Double.parseDouble(betField.getText());                                                       //Wert aus Textfeld des jeweiligem Spielers auslesen
        }else{                                                                                                  //und prüfen ob es sich um nummern handelt
            statusLabel.setText("Only numbers please!");                                                        //Wenn nicht meldung ausgeben
            betField.setStyle("-fx-control-inner-background: red;");                                            //Und Eingabefeld rot markieren
            return;
        }
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){                                                //Prüfen ob Wette nicht zu klein oder groß ist
            betField.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
            betField.setStyle("-fx-control-inner-background: red;");                                            //Meldung + hintergrund falls ungültige wette
        }else {

            betField.setStyle("-fx-control-inner-background: white;");                                           //bei gültiger wette hintergrund weiß setzen (falls vorher rot war)
            getPlayer(playerID).setBet(bet);                                                       //Wette des Spielers speichern
            getPlayer(playerID).setCredit(getPlayer(playerID).getCredit() - bet);     //Wette vom guthaben abziehen
            betField.setEditable(false);                                                                        //Button und textfeld deaktiveiren
            betButton.setDisable(true);
            creditLabel.setText(String.valueOf(getPlayer(playerID).getCredit()));                  //Und Anzeige des Guthabens aktualisieren
            statusLabel.setText("Please wait for the other Players");
            countMoves();                                                                                       //Methode zum zählen der gesetzen Wetten aufrufen
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
        setPlayerUI(player.getID());
            cardPane.getChildren().clear();
            int cardPos = 30;
            for (Card card : player.getHand()){
                Rectangle GUIcard = new Rectangle(60,120);
                GUIcard.setX(cardPos);
                GUIcard.setFill(new ImagePattern(card.getImg()));
                cardPane.getChildren().add(GUIcard);
                cardPos += 30;
            }
    }

    private void showDoubleBet(){
        GameStatusLabel.setText("Do you want to double your bet?");
        for(Player player : Model.getPlayers()){
            if(player.getHand().size() == 2 && !player.BJ() && (player.getBet() < player.getCredit())) {    //Spieler kann nur verdoppeln wenn: er 2 karten hat, keinen blackjack und noch genug credits
                setPlayerUI(player.getID());
                doubleButton.setVisible(true);                                                  //Die Buttons zum verdoppeln einblenden
                notDoubleButton.setVisible(true);
            }else{
                //Model.setMovesThisTurn(Model.getMovesThisTurn()+1);                                         //Kann ein spieler nicht verdoppeln, wird die Anzahl der Spielzüge der runde um eins erhöht, da der spieler automatisch nicht verdoppelt
                countMoves();
                System.out.println(Model.getMovesThisTurn());
            }
        }
    }

    @FXML
    private void doublePlayerBet(MouseEvent event){                            //Methode verarbeitet die Wetteingaben der Spieler. Über den MouseEvent wird die Quelle des Klicks bestimmt
        int playerID = whoClickedTheButton(event);
        double bet = getPlayer(playerID).getBet();
        setPlayerUI(playerID);
        getPlayer(playerID).setCredit(getPlayer(playerID).getCredit() - bet);     //Wette vom guthaben abziehen
        getPlayer(playerID).setBet(bet * 2);                                                   //Wette verdoppeln
        getPlayer(playerID).setDoubleBet(true);
        betField.setText(String.valueOf(getPlayer(playerID).getBet()));
        doubleButton.setVisible(false);                                  //Button und textfeld deaktiveiren
        notDoubleButton.setVisible(false);
        creditLabel.setText(String.valueOf(getPlayer(playerID).getCredit()));    //Und Anzeige des Guthabens aktualisieren
        countMoves();                                                                                       //Methode zum zählen der gesetzen Wetten aufrufen
    }

    @FXML
    private void notDoublePlayerBet(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        setPlayerUI(playerID);
        doubleButton.setVisible(false);                                  //Button und textfeld deaktiveiren
        notDoubleButton.setVisible(false);
        countMoves();
    }

    private void hideDoubleBet(){
        for(Player player : Model.getPlayers()) {
            setPlayerUI(player.getID());
           doubleButton.setVisible(false);                                                  //Die Buttons zum verdoppeln ausblenden
            notDoubleButton.setVisible(false);
        }
    }

    private void anotherCard(){
        for(Player player : Model.getPlayers()){                                                                //alle spieler durchlaufen
            if(!player.isStand() && !player.BJ() && !player.isOut()){                                           //bei NICHT -> Stand,Blackjack oder verloren
                setPlayerUI(player.getID());
                cardButton.setVisible(true);                                                  //Die Buttons zum karte ziehen anzeigen
                standButton.setVisible(true);
            }else{
                countMoves();
            }
        }
    }

    @FXML
    private void takeCard(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        drawCard(getPlayer(playerID),1);
        checkCardValues(getPlayer(playerID));
        showPlayerCards(getPlayer(playerID));
    }

    @FXML
    private void stand(MouseEvent event){
        int playerID = whoClickedTheButton(event);                          //ID des Spielers anhand der ButtonID ermitteln
        setPlayerUI(playerID);                                              //Ui komponenten des spielers laden
        getPlayer(playerID).setStand(true);                   //Spieler auf Stand setzen
        cardButton.setVisible(false);                                      //Buttons ausblenden
        standButton.setVisible(false);
        countMoves();                                                      //Anzahl der Spieler zählen die eine Auswahl getroffen haben
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
        setPlayerUI(player.getID());
        int handValue = getHandValue(player);
        if(handValue == 21){                    //Kartenwert von 21
            if(player.getHand().size() < 3){    //und max. 2 karten
                player.setBJ(true);             //BlackJack!
                cardValueLabel.setText("You have got a BlackJack!");
            }else{
                player.setStand(true);          //Kartenwert 21 und mehr als 3 karten -> stand
                cardButton.setVisible(false);                                  //Buttons ausblenden
                standButton.setVisible(false);
                countMoves();
                cardValueLabel.setText("You reached 21 points. Stand!");
            }
        }else if (handValue > 21) {
            player.setOut(true);
            cardValueLabel.setText("More than 21 Points, your out");
            cardButton.setVisible(false);                                  //Buttons ausblenden
            standButton.setVisible(false);
            countMoves();
        }else{
            cardValueLabel.setText("Total card value: " + handValue);
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

                handValue = getHandValue(Model.getBank());     //und hand wert berechnen
            }else{                                  //ansonsten stehen
                Label Info = new Label();
                Info.setText("The Bank stands with " + handValue + " points");
                GameStatusPane.getChildren().add(Info);
                System.out.println("The bank stands");
                Model.getBank().setStand(true);
            }
            showPlayerCards(Model.getBank());            //Die karten der Bank werden angezeigt
        }
    }

    private void GameResult(){
        double winnings;

        for (Player player : Model.getPlayers()){
            setPlayerUI(player.getID());
            if(!player.isOut()){

                if(player.BJ()){                                              //Spieler hat BlackJack
                    if(Model.getBank().BJ()){                                      //und Bank auch
                        player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                        statusLabel.setText("Draw. You got your bet back");
                    }else{                                                      //Spieler hat BJ aber bank nicht
                        winnings = player.getBet()*2.5;                           //Spieler erhält 2,5fachen einsatz
                        player.setCredit(player.getCredit() + winnings);
                        statusLabel.setText("You won " + winnings + " credits!");
                    }
                }else if(Model.getBank().isOut()){                                         //Ist die Bank über 21 und der spieler nicht
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    statusLabel.setText("You won " + winnings + " credits!");
                }else if(getHandValue(player) == getHandValue(Model.getBank())){           //Spieler handwert = Bank
                    player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                    statusLabel.setText("Draw. You got your bet back");
                }else if(getHandValue(player) > getHandValue(Model.getBank()) ){
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    statusLabel.setText("You won " + winnings + " credits!");
                }else if(getHandValue(player) < getHandValue(Model.getBank())){
                    statusLabel.setText("You lost your bet!");
                }
                creditLabel.setText(String.valueOf(player.getCredit()));
            }else{
                statusLabel.setText("You lost your bet!");
            }
        }
    }
    private void showPlayAgain(){
        for(Player player : Model.getPlayers()){        //Alle spieler durchlaufen
            setPlayerUI(player.getID());
            playAgainButton.setVisible(true);
            quitButton.setVisible(true);
        }
    }

    @FXML
    private void PlayAgain(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        setPlayerUI(playerID);
        playAgainButton.setVisible(false);                                  //Buttons ausblenden
        quitButton.setVisible(false);
        countMoves();
    }

    @FXML
    private void QuitPlayer(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        setPlayerUI(playerID);
        playerPane.setVisible(false);
        getPlayer(playerID).setQuit(true);
        countMoves();                                                                                               //anzahl der der nutzer zählen die eine aktion getätigt haben
    }

    private void removePlayer(){
        ArrayList <Player> remPlayers = new ArrayList<>();
        for(Player player : Model.getPlayers()){
            if(player.isQuit() || player.getCredit() == 0){
                remPlayers.add(player);
            }
        }
        for(Player player : remPlayers){
            Model.getPlayers().remove(player);
        }
    }

    private void countMoves(){                                              //Anzahl der Spieleraktionen pro zug zählen (wird bei jedem Klick auf einen Button aufgerufen)
        Model.setMovesThisTurn(Model.getMovesThisTurn() +1);
        if(Model.getMovesThisTurn() == Model.getPlayers().size()) {         //Prüfen ob die anzahl der aktionen der anzahl der vorhandenen Spieler entspricht
            Model.setMovesThisTurn(0);                                      //wenn ja, Anzahl aktionen wieder auf 0 setzen
            Model.setGamestatus(Model.getGamestatus()+1);                   //und den Spielstatus um eins erhöhen, sodass im Zustandsautomat der nächste Schritt erfolgen kann
            GameStateHandler();                                             //Zustandasautomaten aufrufen
        }
    }

    private boolean playersLeft(){
        System.out.println(Model.getPlayers().size());
        if(Model.getPlayers().size() == 0) return false;
        return true;
    }

    private void unsetPlayerVars(){

        for (Player player : Model.getPlayers()){
            setPlayerUI(player.getID());
            player.getHand().clear();
            player.setBet(0);
            player.setStand(false);
            player.setBJ(false);
            player.setOut(false);
            betButton.setDisable(false);
            betField.setEditable(true);
            cardPane.getChildren().clear();
            statusLabel.setText("Set your bet and press OK");
        }
        Model.getBank().getHand().clear();
        Model.getBank().setStand(false);
        Model.getBank().setBJ(false);
        Model.getBank().setOut(false);

    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
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

    private Player getPlayer(int playerID){
        for(Player player : Model.getPlayers()){
            if(player.getID() == playerID){
                return player;
            }
        }
        return null;
    }

    @FXML
    private void backToMainMenu() throws Exception{
        unsetPlayerVars();
        Model.getPlayers().clear();
        MainMenuView MainMenu = new MainMenuView(Model);
    }

    private void setPlayerUI(int playerID){

        switch(playerID){
            case 0:
                playerPane = Player1Pane;
                cardPane = Player1CardPane;
                creditLabel = Player1CreditLabel;
                cardValueLabel = Player1CardValueLabel;
                statusLabel = Player1StatusLabel;
                betField = Player1BetField;
                betButton = Player1BetBtn;
                cardButton = Player1CardBtn;
                standButton = Player1StandBtn;
                doubleButton = Player1DoubleBtn;
                notDoubleButton = Player1NotDoubleBtn;
                playAgainButton = Player1PlayAgainBtn;
                quitButton = Player1QuitBtn;
                break;
            case 1:
                playerPane = Player2Pane;
                cardPane = Player2CardPane;
                creditLabel = Player2CreditLabel;
                cardValueLabel = Player2CardValueLabel;
                statusLabel = Player2StatusLabel;
                betField = Player2BetField;
                betButton = Player2BetBtn;
                cardButton = Player2CardBtn;
                standButton = Player2StandBtn;
                doubleButton = Player2DoubleBtn;
                notDoubleButton = Player2NotDoubleBtn;
                playAgainButton = Player2PlayAgainBtn;
                quitButton = Player2QuitBtn;
                break;
            case 2:
                playerPane = Player3Pane;
                cardPane = Player3CardPane;
                creditLabel = Player3CreditLabel;
                cardValueLabel = Player3CardValueLabel;
                statusLabel = Player3StatusLabel;
                betField = Player3BetField;
                betButton = Player3BetBtn;
                cardButton = Player3CardBtn;
                standButton = Player3StandBtn;
                doubleButton = Player3DoubleBtn;
                notDoubleButton = Player3NotDoubleBtn;
                playAgainButton = Player3PlayAgainBtn;
                quitButton = Player3QuitBtn;
                break;
            case 3:
                playerPane = Player4Pane;
                cardPane = Player4CardPane;
                creditLabel = Player4CreditLabel;
                cardValueLabel = Player4CardValueLabel;
                statusLabel = Player4StatusLabel;
                betField = Player4BetField;
                betButton = Player4BetBtn;
                cardButton = Player4CardBtn;
                standButton = Player4StandBtn;
                doubleButton = Player4DoubleBtn;
                notDoubleButton = Player4NotDoubleBtn;
                playAgainButton = Player4PlayAgainBtn;
                quitButton = Player4QuitBtn;
                break;
            case 4:
                playerPane = Player5Pane;
                cardPane = Player5CardPane;
                creditLabel = Player5CreditLabel;
                cardValueLabel = Player5CardValueLabel;
                statusLabel = Player5StatusLabel;
                betField = Player5BetField;
                betButton = Player5BetBtn;
                cardButton = Player5CardBtn;
                standButton = Player5StandBtn;
                doubleButton = Player5DoubleBtn;
                notDoubleButton = Player5NotDoubleBtn;
                playAgainButton = Player5PlayAgainBtn;
                quitButton = Player5QuitBtn;
                break;
            case 5:
                playerPane = Player6Pane;
                cardPane = Player6CardPane;
                creditLabel = Player6CreditLabel;
                cardValueLabel = Player6CardValueLabel;
                statusLabel = Player6StatusLabel;
                betField = Player6BetField;
                betButton = Player6BetBtn;
                cardButton = Player6CardBtn;
                standButton = Player6StandBtn;
                doubleButton = Player6DoubleBtn;
                notDoubleButton = Player6NotDoubleBtn;
                playAgainButton = Player6PlayAgainBtn;
                quitButton = Player6QuitBtn;
                break;
            case 6:
                cardPane = BankCardPane;
                break;
        }


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
    Label Player1CardValueLabel;
    @FXML
    Label Player1StatusLabel;
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
    Label Player2StatusLabel;
    @FXML
    Label Player2CardValueLabel;
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
    Label Player3CardValueLabel;
    @FXML
    Label Player3StatusLabel;
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
    Label Player4CardValueLabel;
    @FXML
    Label Player4StatusLabel;
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
    Label Player5CardValueLabel;
    @FXML
    Label Player5StatusLabel;
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
    Label Player6CardValueLabel;
    @FXML
    Label Player6StatusLabel;
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
}
