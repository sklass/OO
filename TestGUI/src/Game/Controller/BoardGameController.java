//Beinhaltet die Logischen Funktionen der Model Klasse BoardGame
//Die Controller Klassen von TicTacToe und FourWins werden von dieser abgeleitet

package Game.Controller;

import Game.Model.BoardGame;

abstract class BoardGameController {
    BoardGame Model;

    //zwingt die instanzen der Klasse BoardGame die folgenden Methoden zu definierenk
    //abstract void GameStateHandler();
    abstract boolean makeAMove(int rowIndex, int colIndex);
   // abstract void checkDiagonale();


    public void setModel(BoardGame model) {
        Model = model;
    }

    protected void initGame(int rows, int cols, char Player1Symbol, char Player2Symbol) {

        Model.getPlayer1().setPlayerID(1);
        Model.getPlayer1().setPlayerSymbol(Player1Symbol);

        Model.getPlayer2().setPlayerID(-1);
        Model.getPlayer2().setPlayerSymbol(Player2Symbol);

        Model.getBoard().setSize(cols, rows);
        Model.getBoard().initialize(0);

        Model.setWinner(null);
        Model.setWinCoordinates(null);
    }

    //Methode zum wechseln des aktiven Spielers
    protected void toggleActivePlayer() {

        if (Model.getActivePlayer().getPlayerID() == Model.getPlayer1().getPlayerID()) {      //Wenn der aktive Spieler derzeit Spieler1 ist, setze Spieler2 als aktiven Spieler
            Model.getActivePlayer().setPlayerID(Model.getPlayer2().getPlayerID());
            Model.getActivePlayer().setPlayerSymbol(Model.getPlayer2().getPlayerSymbol());
        } else {                                                               //Wenn der aktive Spieler derzeit nicht Spieler1 ist, setze Spieler1 als aktiven Spieler
            Model.getActivePlayer().setPlayerID(Model.getPlayer1().getPlayerID());
            Model.getActivePlayer().setPlayerSymbol(Model.getPlayer1().getPlayerSymbol());
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
        //String WinCondition;
        //waagerecht
        for (int y = rows; y > 0; y--) {             //Läuft von der untersten bis zur obersten Reihe (6-1)
            int[][] WinCoordinates = new int[2][4];       //Speichert die position des Spielsteins in einem Array
            for (int x = 1; x <= cols; x++) {       //für jedes Feld in der Reihe
                if (coordinates[y][x] == Model.getActivePlayer().getPlayerID()) {    //Prüfung ob sich darin das Zeichen des Spielers befindet der gerade dran ist
                    Points++;                                     //Für jedes aufeinenderfolgende gleiche Zeiche gibt es einen Punkt
                    WinCoordinates[0][Points - 1] = y;
                    WinCoordinates[1][Points - 1] = x;
                }
                if (coordinates[y][x] != Model.getActivePlayer().getPlayerID()) {    //Findet die Schleife ein anderes Zeichen als das des Spielers der and er Reihe sit
                    Points = 0;                     // Punktezähler wieder auf 0 setzen
                }
                if (Points == AmountofSymbolsToWin) {                   //Wurden vier gleiche Zeichen in folge gefunden

                    Model.setWinner(Model.getActivePlayer(),WinCoordinates);                   // Gibt die Schleife den Gewinner zurück
                    Model.setGameStatus(3);
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
        for (int x = cols; x > 0; x--) {             //Läuft von Spalte ganz rechts bis zum anfang
            int[][] WinCoordinates = new int[2][4];       //Speichert die position des Spielsteins in einem Array
            for (int y = 1; y <= rows; y++) {       //Prüft jedes Feld in der Spalte
                if (coordinates[y][x] == Model.getActivePlayer().getPlayerID()) {    //ob sich darin das Zeichen des Spielers befindet der gerade dran ist
                    Points++;                       //Für jedes aufeinenderfolgende gleiche Zeiche gibt es einen Punkt
                    WinCoordinates[0][Points - 1] = y;
                    WinCoordinates[1][Points - 1] = x;
                }
                if (coordinates[y][x] != Model.getActivePlayer().getPlayerID()) {    //Findet die Schleife ein anderes Zeichen als das des Spielers der and er Reihe sit
                    Points = 0;                     // Punktezähler wieder auf 0 srtzen
                }
                //System.out.print(Points);
                if (Points == AmountofSymbolsToWin) {                   //Wurden vier gleiche Zeichen in folge gefunden
                    //WinCondition = AmountofSymbolsToWin + " in one Column";
                    Model.setWinner(Model.getActivePlayer(),WinCoordinates);                   // Gibt die Schleife den Gewinner zurück
                    Model.setGameStatus(3);
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

        for(int y = 1; y <= rows; y++){    //Alle Zeilen
            for(int x = 1; x <= cols; x++){    // Und alle Felder in der Reihe abarbeiten
                if (coordinates[y][x] == 0){ //Prüfen ob leer
                    Model.setGameStatus(1);
                    return;                      //Wenn leer, kein unentschieden
                }
            }
        }
        Model.setGameStatus(4); //Wird kein freies feld gefunden endet das Spiel mit unentschieden
    }

    protected void checkWinner(){
        if(Model.getWinner() != null){
            this.increaseWinningPlayerPoints();       //Punkt des Spielers der gewonnen hat um eins erhöhen
            //this.changeGameState(8);    //Spielstatus 8 -> Gewinner anzeigen
        }else {
            //this.changeGameState(7); //auf Unentschieden prüfen

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
