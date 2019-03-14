package BlackJack.controller;

import BlackJack.Model.BlackJackModel;
import BlackJack.Model.Card;
import BlackJack.Model.Player;
import BlackJack.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class BJController {
    private BlackJackModel Model;

    // Platzhalter attribute die im spielverlauf mit den GUI inhalten der Spieler befüllt werden
    private Pane playerPane = new Pane();
    private Pane cardPane = new Pane();
    private Label statusLabel = new Label();
    private Label actionLabel = new Label();
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

    public void GameStateHandler(){                                  //Zustandsautomat (ohne while schleife)

        switch (Model.getGamestatus()) {
            case 0:
                definePlayers();                                //Gewünschte anzahl Spieler erzeugen
                initGUI();                                      //Spielfelder der aktiven spieler einblenden
                Model.createCardDeck(6);      //neues kartendeck erstellen
                Model.setGamestatus(2);
                GameStateHandler();
                break;
            case 1:                                             //Wird beim ersten durchlauf übersprungen, hier wird bei start der 2ten runde begonnen
                unsetPlayerVars();                              //Alle Spielerwerte der letzten runde zurücksetzen (außer credits)
                Model.setGamestatus(2);
                GameStateHandler();
                break;
            case 2:
                Model.getCardDeck().shuffle();                  //Karten mischen
                Model.setGamestatus(3);
                GameStateHandler();
                break;
            case 3:                                          //Spieler können Ihre wetteinsätze machen
                                                             //Hier wird auf klicks der spieler gewartet, haben alle spieler geklickt, springt count moves in den nächsten gamestatus
                break;
            case 4:
                drawStartCards();                           //Zu beginn des Spiels werden 2 karten an alle Spieler vergeben und eine an die bank
                Model.setGamestatus(5);
                GameStateHandler();
                break;
            case 5:                                         //Nachdem die spieler ihre karten haben, können sie ihren einsatz verdoppeln
                showDoubleBet();                            //Hier wird auf klicks der spieler gewartet, haben alle spieler geklickt, springt count moves in den nächsten gamestatus
                break;
            case 6:                                         //Spieler können weitere karten ziehen
                anotherCard();                              //Hier wird auf klicks der spieler gewartet, haben alle spieler geklickt, springt count moves in den nächsten gamestatus
                break;
            case 7:
                BanksTurn();                                //Nachdem alle Spieler ihre karten erhalten haben ist die bank dran
                Model.setGamestatus(8);
                GameStateHandler();
                break;
            case 8:
                GameResult();                               //Ist die Bank fertig wird das Ergebnis der spielrunde geprüft und angezeigt
                Model.setGamestatus(9);
                GameStateHandler();
                break;
            case 9:                                        //Spieler könne  den tisch verlassen oder weiter spielen
                showPlayAgain();                           //Hier wird auf klicks der spieler gewartet, haben alle spieler geklickt, springt count moves in den nächsten gamestatus
                break;
            case 10:                                        //spieler die den tisch verlassen werden gelöscht
                removePlayer();
                if(playersLeft()) {                         //prüfung ob noch spieler vorhanden sind
                    Model.setGamestatus(1);                 //wenn ja, neue runde
                    GameStateHandler();
                }else {
                    try {
                        backToMainMenu();                   //wenn nein, zurück ins hauptmenü
                    }catch(Exception e){
                        System.out.println(e);
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

    private void initGUI(){     //Methode zum einblenden der benötigen GUI inhalte, nur die inhalte der vorhandenen spieler werden angezeigt
        addStatusEntry("A new game has started with " + Model.getNumberOfPlayers() + " players");
        addStatusEntry("Waiting for players to set their bet");
        for(Player player : Model.getPlayers()){
            setPlayerUI(player.getID());
            playerPane.setVisible(true);
            cardValueLabel.setVisible(false);
            actionLabel.setText("Place your bet and press OK");
            creditLabel.setText(String.valueOf(player.getCredit()));
        }
    }

    @FXML
    private void PlayerBets(MouseEvent event){  //Methode verarbeitet die Wetteingaben der Spieler. Über den MouseEvent wird die Quelle des Klicks bestimmt
        double bet;
        int playerID = whoClickedTheButton(event);                                                              //Aus der ID des Buttons die Id des spielers ableiten der geklickt hat
        setPlayerUI(playerID);                                                                                  //Festlegen welche Spieler GUi elemente genutzt werden

        if(isDouble(betField.getText())) {
            bet = Double.parseDouble(betField.getText());                                                       //Wert aus Textfeld des jeweiligem Spielers auslesen
        }else{                                                                                                  //und prüfen ob es sich um nummern handelt
            statusLabel.setText("Only numbers please!");                                                        //Wenn nicht meldung ausgeben
            betField.setStyle("-fx-control-inner-background: red;");                                            //Und Eingabefeld rot markieren
            return;
        }
        if (bet < Model.getMinBet() || bet > Model.getMaxBet()){                                                //Prüfen ob Wette nicht zu klein oder groß ist
            statusLabel.setText("Place a bet between " + Model.getMinBet() + " and "+ Model.getMaxBet());
            betField.setStyle("-fx-control-inner-background: red;");                                            //Meldung + hintergrund falls ungültige wette
        }else {

            betField.setStyle("-fx-control-inner-background: white;");                                           //bei gültiger wette hintergrund weiß setzen (falls vorher rot war)
            getPlayer(playerID).setBet(bet);                                                       //Wette des Spielers speichern
            getPlayer(playerID).setCredit(getPlayer(playerID).getCredit() - bet);     //Wette vom guthaben abziehen
            betField.setEditable(false);                                                                        //Button und textfeld deaktiveiren
            betButton.setDisable(true);
            creditLabel.setText(String.valueOf(getPlayer(playerID).getCredit()));                  //Und Anzeige des Guthabens aktualisieren
            actionLabel.setText("Please wait for the other Players");
            countMoves();                                                                                       //Methode zum zählen der gesetzen Wetten aufrufen
        }
    }

    private void drawStartCards() {                     //jeder spieler zieht 2 karten und die bank eine
        addStatusEntry("All players have set their bet. Dealing out start cards");
        drawCard(Model.getBank(),1);
        showPlayerCards(Model.getBank());               //Karten der Bank zeigen
        for(Player player: Model.getPlayers()){         //alle spieler durchlaufen
            drawCard(player, 2);          //2 karten ziehen
            checkCardValues(player);                    //kartenwert prüfen
            showPlayerCards(player);                    //karten anzeigen
        }
    }

    private void drawCard(Player player , int numberOfCards) {                  //methode um spielern neue karten zuzuweisen
        for(int i=0; i< numberOfCards; i++) {                                   //läuft so oft wie numberOfCards vorgibt
            player.setCard(Model.getCardDeck().drawCard(Model.getCardCounter()));   //spieler eine karte zuweisen
            Model.setCardCounter(Model.getCardCounter()+1);                         //globalen kartenzähler um ein erhöhen
        }
    }

    private void showPlayerCards(Player player){                //hand eines spielers in der gui anzeigen
        setPlayerUI(player.getID());                            //festlegen welche GUi inhalte zum aktuellen spieler gehören
            cardPane.getChildren().clear();                     //alle bisherigen karten aus der gui löschen
            int cardPos = 30;
            for (Card card : player.getHand()){                 //alle karten duchlaufen
                Rectangle GUIcard = new Rectangle(60,120); //Rectangle object erzeugen
                GUIcard.setX(cardPos);                            //position im pane festlegen
                GUIcard.setFill(new ImagePattern(card.getImg())); //Bild aus dem card object holen und dem rechteck zuweisen
                cardPane.getChildren().add(GUIcard);              //karte im pane hinzufügen
                cardPos += 30;
            }
    }

    private void showDoubleBet(){                                  //Buttons zum verdoppeln bzw nicht verdoppeln anzeigen
        addStatusEntry("Players may now double their bet");
        for(Player player : Model.getPlayers()){
            setPlayerUI(player.getID());
            if(player.getHand().size() == 2 && !player.BJ() && (player.getBet() <= player.getCredit())) {    //Spieler kann nur verdoppeln wenn: er 2 karten hat, keinen blackjack und noch genug credits
                actionLabel.setText("Double your bet?");
                doubleButton.setVisible(true);                                                  //Die Buttons zum verdoppeln einblenden
                notDoubleButton.setVisible(true);
            }else{
                actionLabel.setText("You cant double down");                      //kann ein spieler nicht verdoppeln wird nur eine meldung angezeigt
                if(player.BJ()) statusLabel.setText("You've got a BlackJack");
                if((player.getBet() > player.getCredit())) statusLabel.setText("Not enough Credits");
                countMoves();                                                                   //und der counter für nutzeraktionen um ein erhöht, da er nicht klicken kann
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

    private void anotherCard(){
        addStatusEntry("Players may now draw additional cards");
        for(Player player : Model.getPlayers()){                                                                //alle spieler durchlaufen
            setPlayerUI(player.getID());
            statusLabel.setText("");
            if(player.isDoubleBet()){                                                                           //hat ein spieler verdoppelt bekommt er eine weitere karte
                drawCard(player,1);
                showPlayerCards(player);
                checkCardValues(player);
            }else if (!player.BJ() && !player.isStand() && !player.isOut()){
                actionLabel.setText("Draw card or Stand");
                cardButton.setVisible(true);                                                  //Die Buttons zum karte ziehen anzeigen
                standButton.setVisible(true);
            }
            else{
                actionLabel.setText("You cant draw another card");
                countMoves();
            }
        }
    }

    @FXML
    private void takeCard(MouseEvent event){
        int playerID = whoClickedTheButton(event);
        drawCard(getPlayer(playerID),1);
        showPlayerCards(getPlayer(playerID));
        checkCardValues(getPlayer(playerID));
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

    private void checkCardValues(Player player){                //auswertung der karten eines spielers
        setPlayerUI(player.getID());
        cardValueLabel.setVisible(true);                        //kartenwert label einblenden
        int handValue = getHandValue(player);                   //kartenwerte zusammenrechnen und anschließend auswerten
        if(handValue == 21) {                                   //Kartenwert von 21
            if (player.getHand().size() < 3) {                  //und max. 2 karten
                player.setBJ(true);                             //BlackJack!
                cardValueLabel.setText("BlackJack!!");
            } else {
                player.setStand(true);                          //Kartenwert 21 und mehr als 3 karten -> stand
                cardButton.setVisible(false);                   //Buttons zum ziehen oder stehen ausblenden
                standButton.setVisible(false);
                cardValueLabel.setText("Hand Value: " + handValue);     //Kartenwert anzeigen
                actionLabel.setText("You've reached 21! Autostand!");   //autostand info bei 21 punkten
                countMoves();                                           //bei autostand muss der nutzer nicht mehr klicken, daher werden die nutzeraktionen um eins hoch gezählt
            }
        }else if(player.isDoubleBet() && handValue < 21){
            cardValueLabel.setText("Hand Value: " + handValue);
            statusLabel.setText("You doubled down");
            actionLabel.setText("You cant draw another card");
            countMoves();
        }else if (handValue > 21) {                                     //kartenwert größer 21
            player.setOut(true);                                        //spieler ist raus
            cardValueLabel.setText("Hand Value: " + handValue);         //info anzeigen
            actionLabel.setText("You're out.More then 21!");
            cardButton.setVisible(false);                                  //Buttons ausblenden
            standButton.setVisible(false);
            countMoves();                                               //spieler kann nicht mehr klicken, daher werden die nutzeraktionen um eins erhöht
        }else {
            cardValueLabel.setText("Hand Value: " + handValue);         //trifft keine der obigen bedingungen zu wird nur der kartenwert angezeigt
        }

    }

    private void BanksTurn(){
        addStatusEntry("Banks turn begins");
        drawCard(Model.getBank(),1);                                  //zunächst zieht die Bank eine weitere Karte
        showPlayerCards(Model.getBank());                                           //Die karten der Bank werden angezeigt
        int handValue;
        handValue = getHandValue(Model.getBank());
        BankCardValueLabel.setVisible(true);

        while(!Model.getBank().BJ() && !Model.getBank().isStand() && !Model.getBank().isOut()){
            if(handValue > 21){                                                     //hat die Bank mehr als 21 ist sie raus
                Model.getBank().setOut(true);
                addStatusEntry("The Bank has more the 21 points. The Bank has lost");
            }else if(handValue == 21 && Model.getBank().getHand().size() < 3) {              //bei 21 Blackjack
                Model.getBank().setBJ(true);
                addStatusEntry("The Bank has BlackJack");
            }else if(handValue == 21 && Model.getBank().getHand().size() > 2){              //bei 21 Blackjack
                    addStatusEntry("The Bank stands with 21 points");
                    Model.getBank().setStand(true);
            }else if(handValue < 17){                                                       //bei weniger als 17 -> karte ziehen
                 drawCard(Model.getBank(),1);
                handValue = getHandValue(Model.getBank());                                  //und hand wert berechnen
                addStatusEntry("The Bank draws another Card");
            }else{                                                                          //ansonsten stehen
                addStatusEntry("The Bank stands with " + handValue + " points");
                Model.getBank().setStand(true);
            }
            BankCardValueLabel.setText("Hand Value: " + handValue);
            showPlayerCards(Model.getBank());                                               //Die karten der Bank werden angezeigt
        }
    }

    private void GameResult(){
        double winnings;

        for (Player player : Model.getPlayers()){
            setPlayerUI(player.getID());
            if(!player.isOut()){
                if(player.BJ() && Model.getBank().BJ()) {                                              //Spieler hat BlackJack und Bank auch
                    player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                    statusLabel.setText("Draw. You got your bet back");
                }else if(player.BJ() && !Model.getBank().BJ()) {                 //Spieler hat BJ aber bank nicht
                    winnings = player.getBet() * 2.5;                           //Spieler erhält 2,5fachen einsatz
                    player.setCredit(player.getCredit() + winnings);
                    statusLabel.setText("You won " + winnings + " credits!");
                }else if(!player.BJ() && Model.getBank().BJ()){                 //Bank hat Bj aber spieler nicht
                    statusLabel.setText("You lost your bet!");                  //spieler verliert einsatz
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
    private void showPlayAgain(){                                            //nachfrage buttons zum nochmal spielen einblenden
        addStatusEntry("Players may now choose to stay or leave the table");
        for(Player player : Model.getPlayers()){                            //Alle spieler durchlaufen
            setPlayerUI(player.getID());
            if(player.getCredit() < Model.getMinBet()){                     //wer zu wenig credits hat, bekommt keine buttons angezeigt
                playerPane.setVisible(false);
                player.setQuit(true);
                countMoves();
            }else {
                actionLabel.setText("Play again?");                         //alle anderen können wählen ob sie noch eine runde spielen wollen oder nicht
                playAgainButton.setVisible(true);
                quitButton.setVisible(true);
            }
        }
    }

    @FXML
    private void PlayAgain(MouseEvent event){                               //Methode die beim klick auf nochmal spielen aufgerufen wird
        int playerID = whoClickedTheButton(event);                          //wer hat geklickt
        setPlayerUI(playerID);                                              //spieler gui inhalte definieren
        playAgainButton.setVisible(false);                                  //Buttons ausblenden
        quitButton.setVisible(false);
        countMoves();                                                       //anzahl der der nutzer zählen die eine aktion getätigt haben
    }

    @FXML
    private void QuitPlayer(MouseEvent event){                              //methode die beim klick auf nicht nochmal spielen aufgerufen wird
        int playerID = whoClickedTheButton(event);                          //wer hat geklickt
        setPlayerUI(playerID);                                              //spieler gui inahlte definieren
        playerPane.setVisible(false);                                       //spieler spielfläche ausblenden
        getPlayer(playerID).setQuit(true);                                  //im spieler speichern das er nicht mehr mitspielt
        countMoves();                                                       //anzahl der der nutzer zählen die eine aktion getätigt haben
    }

    private void removePlayer(){                                            //methode zum entfernen von spielern die nicht genug credits haben oder das speil beenden wollten
        ArrayList <Player> remPlayers = new ArrayList<>();                  //array liste die die zu löschenden spieler beinhaltet
        for(Player player : Model.getPlayers()){
            if(player.isQuit() || player.getCredit() < Model.getMinBet()){
                remPlayers.add(player);
                addStatusEntry("Player " + (player.getID()+1) + " left the table");
            }
        }
        for(Player player : remPlayers){                                    //liste der zu löschenden spieler durchlaufen und aus der aktiven spieler arrayListe löschen
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
        return Model.getPlayers().size() != 0; //true wenn ungleich null -> false bei null
    }

    private void unsetPlayerVars(){                                                             //Methode um Variablen beim start einer neuen runde zurückzusetzen

        addStatusEntry("Starting new round with " + Model.getPlayers().size() + " players");
        Model.setMovesThisTurn(0);
        for (Player player : Model.getPlayers()){
            setPlayerUI(player.getID());
            betField.setEditable(true);
            if(player.isDoubleBet()){
                betField.setText(String.valueOf(player.getBet()/2));
            }
            player.getHand().clear();
            player.setBet(0);
            player.setStand(false);
            player.setBJ(false);
            player.setOut(false);
            player.setDoubleBet(false);
            betButton.setDisable(false);
            cardPane.getChildren().clear();
            cardValueLabel.setVisible(false);
            statusLabel.setText("");
            actionLabel.setText("Set your bet and press OK");
        }
        BankCardValueLabel.setVisible(false);
        Model.getBank().getHand().clear();
        Model.getBank().setStand(false);
        Model.getBank().setBJ(false);
        Model.getBank().setOut(false);

    }

    private boolean isDouble(String str) {           //Methode zur prüfung bo ein Double wert eingegeben wurde
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void addStatusEntry(String Text){               //Methode zum hinzufügen von einträgen im GameStatus ListView
        Label Info = new Label();                           //Jeder Eintrag ist ein Label
        Info.setText(Text);
        GameStatusListView.getItems().add(Info);
        int index = GameStatusListView.getItems().size();
        GameStatusListView.scrollTo(index);                 //Autoamtisch zum neuen Eintrag scrollen
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

    private Player getPlayer(int playerID){         //durchsucht die Spieler array list nach dem spieler mit der ID playerID
        for(Player player : Model.getPlayers()){
            if(player.getID() == playerID){
                return player;
            }
        }
        return Model.getPlayers().get(0);         //wird kein spieler gefunden, geben wir als default den ersten spieler zurück
    }

    @FXML
    private void backToMainMenu() throws Exception{     //zurück zum Hauptmenü
        unsetPlayerVars();
        Model.getPlayers().clear();
        MainMenuView MainMenu = new MainMenuView(Model);
    }

    private void setPlayerUI(int playerID){     //die methode ordnet anhand der spieler id die spielfläche einem spieler zu

        switch(playerID){
            case 0:
                playerPane = Player1Pane;
                cardPane = Player1CardPane;
                creditLabel = Player1CreditLabel;
                cardValueLabel = Player1CardValueLabel;
                statusLabel = Player1StatusLabel;
                actionLabel = Player1ActionLabel;
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
                actionLabel = Player2ActionLabel;
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
                actionLabel = Player3ActionLabel;
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
                actionLabel = Player4ActionLabel;
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
                actionLabel = Player5ActionLabel;
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
                actionLabel = Player6ActionLabel;
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
    ListView GameStatusListView;
    @FXML
    Label BankCardValueLabel;

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
    Label Player1ActionLabel;
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
    Label Player2ActionLabel;
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
    Label Player3ActionLabel;
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
    Label Player4ActionLabel;
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
    Label Player5ActionLabel;
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
    Label Player6ActionLabel;
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
