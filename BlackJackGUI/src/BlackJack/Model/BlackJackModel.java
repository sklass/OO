package BlackJack.Model;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackModel {
    Stage Stage;
    private int gamestatus;         //Kontrolliert den Spielablauf in GameStateHandler()
    private int numberOfPlayers;
    private ArrayList<Player> Players = new ArrayList<>(); //Array List mit allen vorhandenen Spielern
    private Player Bank = new Player();            //Der Spieler der die Bank repäsentiert
    private CardDeck CardDeck;      //Das bzw. die Kartenspiele
    private int numberOfCardDecks;  //Anzahl der verwendeten Kartenspiele
    private int CardCounter;                //zählt mit jeder gezogenen karte eins hoch
    private int minBet;                     //minimaler Wetteinsatz
    private int maxBet;                     //maximaler Wetteinsatz

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

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }

    public Player getBank() {
        return Bank;
    }

    public void setBank(Player bank) {
        Bank = bank;
    }

    public void createCardDeck(int numberOfCardDecks){
        CardDeck = new CardDeck(numberOfCardDecks);
    }

    public BlackJack.Model.CardDeck getCardDeck() {
        return CardDeck;
    }

    public void setCardDeck(BlackJack.Model.CardDeck cardDeck) {
        CardDeck = cardDeck;
    }

    public int getNumberOfCardDecks() {
        return numberOfCardDecks;
    }

    public void setNumberOfCardDecks(int numberOfCardDecks) {
        this.numberOfCardDecks = numberOfCardDecks;
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
}
