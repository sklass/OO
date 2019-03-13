package BlackJack.Model;

import javafx.stage.Stage;

import java.util.ArrayList;

public class BlackJackModel {
    Stage Stage;
    private int gamestatus;         //Kontrolliert den Spielablauf in GameStateHandler()
    private int numberOfPlayers;
    private int MovesThisTurn;
    private ArrayList<Player> Players = new ArrayList<>(); //Array List mit allen vorhandenen Spielern
    private Player Bank = new Player(0);            //Der Spieler der die Bank repäsentiert
    private CardDeck CardDeck;      //Das bzw. die Kartenspiele
   // private int numberOfCardDecks;  //Anzahl der verwendeten Kartenspiele
    private int CardCounter;                //zählt mit jeder gezogenen karte eins hoch
    private int minBet;                     //minimaler Wetteinsatz
    private int maxBet;                     //maximaler Wetteinsatz
    private double startCredit;

    public Stage getStage() {
        return Stage;
    }

    public void setStage(Stage Stage) {
        this.Stage = Stage;
    }

    public int getGamestatus() {
        return gamestatus;
    }

    public void setGamestatus(int gamestatus) {
        this.gamestatus = gamestatus;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public Player getBank() {
        return Bank;
    }

    public void createCardDeck(int numberOfCardDecks){
        CardDeck = new CardDeck(numberOfCardDecks);
    }

    public BlackJack.Model.CardDeck getCardDeck() {
        return CardDeck;
    }

    public int getCardCounter() {
        return CardCounter;
    }

    public void setCardCounter(int cardCounter) {
        CardCounter = cardCounter;
    }

    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(int maxBet) {
        this.maxBet = maxBet;
    }

    public double getStartCredit() {
        return startCredit;
    }

    public void setStartCredit(double startCredit) {
        this.startCredit = startCredit;
    }

    public int getMovesThisTurn() {
        return MovesThisTurn;
    }

    public void setMovesThisTurn(int movesThisTurn) {
        MovesThisTurn = movesThisTurn;
    }
}
