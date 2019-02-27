//Modell Klasse die als Grundlage für die Brettspiele TicTacToe und VierGewinnt dient
//Im Modell werden nur Daten gespeichert - keine logischen methoden - nur setter und getter
package Game.Model;

import javafx.stage.Stage;

public class BoardGame {

    public int GameStatus;
    protected Player player1;
    protected Player player2;
    protected Player activePlayer;
    protected Player winner;
    protected String WinCondition;
    protected int[][] WinCoordinates;
    protected Board Board;
    protected Stage primaryStage;

    public BoardGame() {
        player1 = new Player();
        player2 = new Player();
        activePlayer = new Player();
        winner = new Player();
        GameStatus = 0;
        Board = new Board();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public int getGameStatus() {
        return GameStatus;
    }

    public void setGameStatus(int gameStatus) {
        GameStatus = gameStatus;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setWinner(Player winner, int[][] WinCoordinates) {
        this.winner = winner;
       // this.WinCondition = WindCondition;
        this.WinCoordinates = new int[2][4];
        this.WinCoordinates = WinCoordinates;

    }

    public String getWinCondition() {
        return WinCondition;
    }

    public void setWinCondition(String winCondition) {
        WinCondition = winCondition;
    }

    public int[][] getWinCoordinates() {
        return WinCoordinates;
    }

    public void setWinCoordinates(int[][] winCoordinates) {
        WinCoordinates = winCoordinates;
    }

    public Game.Model.Board getBoard() {
        return Board;
    }

    public void setBoard(Game.Model.Board board) {
        Board = board;
    }
}