//Der TictacToeController wird vom BoardGameController abgeleitet
// und beinhaltet die logik welche sich von der allgemeinen BoardGameLogik unterscheidet
//Das Model wird dem Controller beim erzeugen des TTTViewszugewiesen

package Game.Controller;

import Game.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class TicTacToeController extends BoardGameController{

  /*  @FXML
    public void initialize() {
        GameStateHandler();
    }
*/
    public void GameStateHandler(){

        switch(Model.getGameStatus()){
            case 0:
                initGame(3,3,'X','O');
                Model.setGameStatus(1);
                break;
            case 1:
                toggleActivePlayer();
                Model.setGameStatus(2);
                break;
            case 2:
                checkRow(3);
                checkCol(3);
                checkDraw();
                break;
            case 3:
                System.out.println("Winner!");
                break;
            case 4:
                System.out.println("Draw");
                break;


        }
    }

    //Methode um neuen Spielstein aufs Feld zu setzen
    public boolean makeAMove(int rowIndex,int colIndex){
        int[][] coordinates = Model.getBoard().getCoordinates();  //Array mit bisherigen Spielsteinen holen
        if(checkField(rowIndex,colIndex)) {                //Ist der Eintrag im Koordianten Array leer
            coordinates[rowIndex][colIndex] = Model.getActivePlayer().getPlayerID();     //Wird das Zeichen des Spieler eingetragen
            Model.getBoard().setCoordinates(coordinates); //Das aktuelle Koordinaten array wird ans Board zurückgegeben
            return true;
        }
        return false;
    }
/*
    void checkDiagonale(){
        int rows = Model.getBoard().getRows();
        int cols = Model.getBoard().getCols();
        int coordinates[][] = Model.getBoard().getCoordinates();
        //drei diagonale von links unten nach rechts oben
        int Points1 = 0;
        int Points2 = 0;
        int[][] WinCoordinates1 = new int[2][4];       //Speichert die position des Spielsteins in einem Array
        int[][] WinCoordinates2 = new int[2][4];

        for(int i = 1; i <=3; i++) {
            if(coordinates[i][i] == this.activePlayer.getPlayerID()) { //1-1 2-2 3-3 -> Von oben links nach unten rechts
                WinCoordinates1[0][Points1] = i;
                WinCoordinates1[1][Points1] = i;
                Points1++;
            }else Points1 = 0;

            if(Points1 == 3 ){                   //Wurden 3 gleiche Zeichen in folge gefunden
                this.WinCondition = "Three diagonal -> upper left to lower right";
                setWinner(this.activePlayer,this.WinCondition,WinCoordinates1);           //setzen wir einen Sieger
                break;
            }

            int y = 4-i;
            int x = i;
            if(coordinates[y][x] == activePlayer.getPlayerID()) { //3-1 2-2 1-3 -> Von oben links nach unten rechts
                WinCoordinates2[0][Points2] = y;
                WinCoordinates2[1][Points2] = x;
                Points2++;
            }else Points2 = 0;

            if(Points2 == 3 ){                   //Wurden 3 gleiche Zeichen in folge gefunden
                WinCondition = "Three diagonal -> lower left to upper right";
                setWinner(this.activePlayer,this.WinCondition,WinCoordinates2);           //setzen wir einen Sieger
                break;
            }
        }

    }
*/
/*
    public void showView() throws Exception{
        TicTacToeView TicTacToeView = new TicTacToeView();                     //TicTacToeView erzeugen
        TicTacToeView.start(Model.getPrimaryStage());
        //Model.GameStateHandler();
    }
*/

    @FXML
    private AnchorPane TicTacToePane;

        @FXML
        private GridPane TicTacToeGrid;

        @FXML
        private void HandleGridClick(javafx.scene.input.MouseEvent event){
            Node clickedNode = event.getPickResult().getIntersectedNode();
            if (clickedNode != TicTacToeGrid) {
                Node parent = clickedNode.getParent();
                while (parent != TicTacToeGrid) {
                    clickedNode = parent;
                    parent = clickedNode.getParent();
                }

            Integer colIndex = TicTacToeGrid.getColumnIndex(clickedNode) == null ? 0 : TicTacToeGrid.getColumnIndex(clickedNode);
            Integer rowIndex = TicTacToeGrid.getRowIndex(clickedNode) == null ? 0 :  TicTacToeGrid.getRowIndex(clickedNode);

           if(makeAMove(rowIndex,colIndex)){
                addCircle(colIndex,rowIndex);
                System.out.println("ColIndex: " + colIndex);
                System.out.println("RowIndex: " + rowIndex);
                Model.setGameStatus(2); //Prüfe gewinner
            }

            }
            GameStateHandler();
        }

    @FXML
    private void addCircle(int colIndex, int rowIndex){

        Color CircleColor = Model.getActivePlayer().getPlayerID() == 1 ? Color.RED : Color.YELLOW;
        Circle MyCircle = new Circle(200, 200, 10, CircleColor);
        TicTacToeGrid.add(MyCircle,colIndex,rowIndex);
    }

    @FXML
    private void backToMainMenu() throws Exception
    {
        System.out.println("MainMenu");
        MainMenuView MainMenuView = new MainMenuView();                      //TicTacToeView erzeugen
        Stage primaryStage = (Stage)TicTacToePane.getScene().getWindow();   //primaryStage ermitteln
        MainMenuView.start(primaryStage);                                      //TicTacToe auf PrimaryStage anzeigen
    }


}