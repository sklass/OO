package Game.Controller;

import Game.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FourWinsController extends  BoardGameController{

    public void GameStateHandler(){

        switch(Model.getGameStatus()){
            case 0:
                initGame(6,7,"Player 1","Player 2", Color.RED,Color.GREEN);
                InfoLabel.setVisible(false);
                PlayAgainButton.setVisible(false);
                Model.setGameStatus(1);
                GameStateHandler();
                break;
            case 1:
                toggleActivePlayer();
                Model.setGameStatus(2);
                break;
            case 2:
                checkRow(4);
                checkCol(4);
                checkDiagonale();
                checkWinner();
                GameStateHandler();
                break;
            case 3:
                checkDraw();
                GameStateHandler();
                break;
            case 4:
                InfoLabel.setText(Model.getActivePlayer().getName() + " has won!");
                InfoLabel.setTextFill(Model.getActivePlayer().getPlayerColor());
                InfoLabel.setVisible(true);
                if(Model.getWinner().getPlayerID() == 1){
                    Player1CounterLabel.setText(Integer.toString(Model.getPlayer1().getPoints()));
                }else{
                    Player2CounterLabel.setText(Integer.toString(Model.getPlayer2().getPoints()));
                }
                PlayAgainButton.setVisible(true);
                break;
            case 5:
                System.out.println("Draw");
                InfoLabel.setText("Game over! Draw!");
                InfoLabel.setVisible(true);
                PlayAgainButton.setVisible(true);
                break;
        }
    }

    //Methode um neuen Spielstein aufs Feld zu setzen
    public boolean makeAMove(int rowIndex, int colIndex){
        int rows = Model.getBoard().getRows();
        int[][] coordinates = Model.getBoard().getCoordinates();
        for(int row = rows-1; row >=0; row--){            //von der untersten bis zur obersten reihe durchlaufen
            if(checkField(row,colIndex)){                //Ist der Eintrag im Koordianten Array leer
                coordinates[row][colIndex] = Model.getActivePlayer().getPlayerID();     //Wird das Zeichen des Spieler eingetragen
                addCircle(colIndex, row);
                break;                             //Schleife unterbrechen
            }
        }
        return false;
    }

    //prüfung ob oberstes reihe einer spalte noch frei
    private boolean checkColumn(int col){
        int[][]coordinates = Model.getBoard().getCoordinates();
        if(coordinates[0][col] == 0){
            return true;
        }else {
            return false;
        }
    }

    void checkDiagonale(){
        int rows = Model.getBoard().getRows();
        int[][] coordinates = Model.getBoard().getCoordinates();
        int Points;
        //vier diagonale von links unten nach rechts oben
        Points = 0;
        for(int x = 0; x  < 4; x++ ){              //Von Spalte eins bis 4 durchlaufen
            for(int y = rows-1; y >= 3; y--){             //In jeder Spalte Von der untersten Reihe bis zur 4. von unten durchlaufen
                if(coordinates[y][x] == Model.getActivePlayer().getPlayerID()){    //Prüfen ob im feld das zeichen des Spielers ist
                    Points++;                       //Einen punkt für das erste zeichen gutschreiben
                    int row = y;                            //aktuelle Reihennummer in row speichern
                    for (int col = x+1; col <= x+3; col++) { //aktuelle spaltennummer in x speichern und per for immer um eins erhöhen bis drei spalten weiter rechts erreicht wurden
                        row--;                              //reihennummer mit jedem durchlauf um eins verringern
                        if (coordinates[row][col] == Model.getActivePlayer().getPlayerID()) {    //ob sich darin das Zeichen des Spielers befindet der gerade dran ist
                            Points++;
                        }else{ //Points = 0;
                            break;
                        }
                        if(Points == 4 ){                   //Wurden vier gleiche Zeichen in folge gefunden
                            Model.setWinner(Model.getActivePlayer());// Gibt die Schleife den Gewinner zurück
                            return;
                        }
                    }
                }
                Points = 0; //Nach jeder Zeile wird der Punktezähler zurückgesetzt
            }
            Points = 0; //Nach jeder Spalte wird der Punktezähler zurückgesetzt
        }

        //vier diagonale von links oben nach rechts unten
        Points = 0;
        //int x = 1;
        for(int x = 0; x  < 4; x++ ){              //Von Spalte eins bis 4 durchlaufen
            for(int y = 0; y < 3; y++){             //In jeder Spalte Von der obersten Reihe bis zur 3. von oben durchlaufen

                if(coordinates[y][x] == Model.getActivePlayer().getPlayerID()){    //Prüfen ob im feld das zeichen des Spielers ist
                    Points++;                       //Einen punkt für das erste zeichen gutschreiben
                    int row = y;                            //aktuelle Reihennummer in row speichern
                    for (int col = x+1; col <= x+3; col++) { //aktuelle spaltennummer in x speichern und per for immer um eins erhöhen
                        row++;                              //reihennummer mit jedem durchlauf um eins verringern
                        if (coordinates[row][col] == Model.getActivePlayer().getPlayerID()) {    //Prüfen ob sich darin das Zeichen des Spielers befindet der gerade dran ist
                            Points++;                             //Einen punkt für das zeichen gutschreiben
                        }else {//Points = 0;
                            break;
                        }
                        if(Points == 4 ){                   //Wurden vier gleiche Zeichen in folge gefunden
                            Model.setWinner(Model.getActivePlayer());                   // Gibt die Schleife den Gewinner zurück
                        }
                    }
                }
                Points = 0; //Nach jeder Zeile wird der Punktezähler zurückgesetzt
            }
            Points = 0; //Nach jeder Spalte wird der Punktezähler zurückgesetzt
        }
    }

    @FXML
    private AnchorPane FourWinsPane;
    @FXML
    private GridPane FourWinsGrid;
    @FXML
    private Label InfoLabel;
    @FXML
    private Label Player1CounterLabel;
    @FXML
    private Label Player2CounterLabel;
    @FXML
    private Button PlayAgainButton;

    @FXML
    private Button backBtn;

    @FXML
    private void HandleGridClick(javafx.scene.input.MouseEvent event){
        Node clickedNode = event.getPickResult().getIntersectedNode(); //Was wurde angeklickt
        if (clickedNode != FourWinsGrid) {                             //sicherstellen das es sich nicht um das Elternelement handelt
            Node parent = clickedNode.getParent();
            while (parent != FourWinsGrid) {                           //Falls es innerhalb des Elements bereits childs gibt, sicherstellen das es sich um ein direktes child vom GridPane handelt
                clickedNode = parent;
                parent = clickedNode.getParent();
            }

            //Zeilen und spalten index speichern, da bei Zeile und spalte 0 der Wert NULL zurückgegeben wird, wird bei Null eine 0 gespeichert
            Integer colIndex = FourWinsGrid.getColumnIndex(clickedNode) == null ? 0 : FourWinsGrid.getColumnIndex(clickedNode);
            Integer rowIndex = FourWinsGrid.getRowIndex(clickedNode) == null ? 0 :  FourWinsGrid.getRowIndex(clickedNode);

            //System.out.println("col: " + colIndex);
            //System.out.println("row: " + rowIndex);
            //Prüfen ob in der zeile noch ein feld frei ist und kein gewinner feststeht
            if(checkColumn(colIndex)&& Model.getWinner() == null){
                makeAMove(rowIndex,colIndex);
                Model.setGameStatus(2); //Prüfe gewinner
            }

        }
        GameStateHandler();
    }

    @FXML
    private void addCircle(int colIndex, int rowIndex){

        Color CircleColor = Model.getActivePlayer().getPlayerColor();
        Circle MyCircle = new Circle(200, 200, 10, CircleColor);
        FourWinsGrid.add(MyCircle,colIndex,rowIndex);
    }

    public void clearGrid(){
        FourWinsGrid.getChildren().removeIf(n -> n instanceof Circle);
    }

    public void HandlePlayAgainClick(javafx.scene.input.MouseEvent event){
        Model.setGameStatus(0);
        clearGrid();
        GameStateHandler();
    }

    @FXML
    private void backToMainMenu() throws Exception
    {
        System.out.println("MainMenu");
        MainMenuView MainMenu = new MainMenuView();                      //TicTacToeView erzeugen
        Stage primaryStage = (Stage)FourWinsPane.getScene().getWindow();   //primaryStage ermitteln
        MainMenu.start(primaryStage);                                      //TicTacToe auf PrimaryStage anzeigen
    }
}
