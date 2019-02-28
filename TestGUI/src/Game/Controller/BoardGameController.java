//Beinhaltet die Logischen Funktionen der Model Klasse BoardGame
//Die Controller Klassen von TicTacToe und FourWins werden von dieser abgeleitet

package Game.Controller;

import Game.Model.BoardGame;
import javafx.scene.paint.Color;

abstract class BoardGameController {
    BoardGame Model;

    //zwingt die instanzen der Klasse BoardGame die folgenden Methoden zu definierenk
    abstract void GameStateHandler();
    abstract boolean makeAMove(int rowIndex, int colIndex);
    abstract void checkDiagonale();


    public void setModel(BoardGame model) {
        Model = model;
    }

    protected void initGame(int rows, int cols, String Player1Name, String Player2Name, Color Player1Color, Color Player2Color) {

        Model.getPlayer1().setPlayerID(1);
        Model.getPlayer1().setPlayerColor(Player1Color);

        Model.getPlayer2().setPlayerID(-1);
        Model.getPlayer2().setPlayerColor(Player2Color);

        Model.getPlayer1().setName(Player1Name);
        Model.getPlayer2().setName(Player2Name);

        Model.getBoard().setSize(cols, rows);
        Model.getBoard().initialize(0);
        Model.setWinner(null);
    }

    //Methode zum wechseln des aktiven Spielers
    protected void toggleActivePlayer() {

        if (Model.getActivePlayer().getPlayerID() == Model.getPlayer1().getPlayerID()) {      //Wenn der aktive Spieler derzeit Spieler1 ist, setze Spieler2 als aktiven Spieler
            Model.getActivePlayer().setPlayerID(Model.getPlayer2().getPlayerID());
            Model.getActivePlayer().setPlayerColor(Model.getPlayer2().getPlayerColor());
            Model.getActivePlayer().setName(Model.getPlayer2().getName());
        } else {                                                               //Wenn der aktive Spieler derzeit nicht Spieler1 ist, setze Spieler1 als aktiven Spieler
            Model.getActivePlayer().setPlayerID(Model.getPlayer1().getPlayerID());
            Model.getActivePlayer().setPlayerColor(Model.getPlayer1().getPlayerColor());
            Model.getActivePlayer().setName(Model.getPlayer1().getName());
        }
    }
    protected boolean checkField(int row, int col) {  //Methode um zu prüfen ob ein einzelnes Spielfeld noch frei ist. Die position des zu setzenden Spielsteins sowie die bisher gesetzen Spielsteine werden übergeben
        int[][] coordinates = Model.getBoard().getCoordinates();
        if (coordinates[row][col] == 0) {                               //Befindet sich im angegebenen Feld eine 0 ist das Feld leer
            return true;
        }
        return false;
    }

    protected void checkRow(int AmountofSymbolsToWin) {
        int rows = Model.getBoard().getRows();
        int cols = Model.getBoard().getCols();
        int coordinates[][] = Model.getBoard().getCoordinates();
        int Points = 0;

        //waagerecht
        for (int y = rows-1; y >= 0; y--) {             //Läuft von der untersten bis zur obersten Reihe (6-1)
            for (int x = 0; x < cols; x++) {       //für jedes Feld in der Reihe
                if (coordinates[y][x] == Model.getActivePlayer().getPlayerID()) {    //Prüfung ob sich darin das Zeichen des Spielers befindet der gerade dran ist
                    Points++;                       //Für jedes aufeinenderfolgende gleiche Zeiche gibt es einen Punkt
                }
                if (coordinates[y][x] != Model.getActivePlayer().getPlayerID()) {    //Findet die Schleife ein anderes Zeichen als das des Spielers der and er Reihe sit
                    Points = 0;                     // Punktezähler wieder auf 0 setzen
                }
                if (Points == AmountofSymbolsToWin) {                   //Wurden vier gleiche Zeichen in folge gefunden

                    Model.setWinner(Model.getActivePlayer());                   // Gibt die Schleife den Gewinner zurück
                    return;
                }
            }
            Points = 0;
        }
    }
    protected void checkCol(int AmountofSymbolsToWin) {
        int rows = Model.getBoard().getRows();
        int cols = Model.getBoard().getCols();
        int coordinates[][] = Model.getBoard().getCoordinates();
        //Senkrecht
        int Points = 0;
        for (int x = cols-1; x >= 0; x--) {             //Läuft von Spalte ganz rechts bis zum anfang
             for (int y = 0; y < rows; y++) {       //Prüft jedes Feld in der Spalte
                if (coordinates[y][x] == Model.getActivePlayer().getPlayerID()) {    //ob sich darin das Zeichen des Spielers befindet der gerade dran ist
                    Points++;                       //Für jedes aufeinenderfolgende gleiche Zeiche gibt es einen Punkt
                }
                if (coordinates[y][x] != Model.getActivePlayer().getPlayerID()) {    //Findet die Schleife ein anderes Zeichen als das des Spielers der and er Reihe sit
                    Points = 0;                     // Punktezähler wieder auf 0 srtzen
                }
                if (Points == AmountofSymbolsToWin) {                   //Wurden vier gleiche Zeichen in folge gefunden
                    Model.setWinner(Model.getActivePlayer());                   // Gibt die Schleife den Gewinner zurück
                    return;
                }
            }
            Points = 0;                           //Nach jeder Spalte wird der Punktezähler zurückgesetzt
        }
    }

    protected void checkDraw(){            //Methode zur Prüfung bo es noch freie felder auf dem spielfeld gibt -> True bei unentschieden
        int rows = Model.getBoard().getRows();
        int cols = Model.getBoard().getCols();
        int[][] coordinates = Model.getBoard().getCoordinates();

        for(int y = 0; y < rows; y++){    //Alle Zeilen
            for(int x = 0; x < cols; x++){    // Und alle Felder in der Reihe abarbeiten
                if (coordinates[y][x] == 0){ //Prüfen ob leer
                    Model.setGameStatus(1);
                    return;                      //Wenn leer, kein unentschieden
                }
            }
        }
        Model.setGameStatus(5); //Wird kein freies feld gefunden endet das Spiel mit unentschieden
    }

    protected void checkWinner(){
        if(Model.getWinner() != null){
            this.increaseWinningPlayerPoints();       //Punkt des Spielers der gewonnen hat um eins erhöhen
            Model.setGameStatus(4);    //Spielstatus 8 -> Gewinner anzeigen
        }else {
            Model.setGameStatus(3); //auf Unentschieden prüfen

        }
    }

    protected void increaseWinningPlayerPoints(){
        if(Model.getWinner().getPlayerID() == 1){
            Model.getPlayer1().increasePoints();
        }else{
            Model.getPlayer2().increasePoints();
        }
    }
}
