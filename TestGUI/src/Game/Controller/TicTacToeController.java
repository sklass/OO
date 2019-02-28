//Der TictacToeController wird vom BoardGameController abgeleitet
// und beinhaltet die logik welche sich von der allgemeinen BoardGameLogik unterscheidet
//Das Model wird dem Controller beim erzeugen des TTTViewszugewiesen

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


public class TicTacToeController extends BoardGameController{

    public void GameStateHandler(){

        switch(Model.getGameStatus()){
            case 0:
                initGame(3,3,"Player 1","Player 2", Color.RED,Color.GREEN);
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
                checkRow(3);
                checkCol(3);
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
    public boolean makeAMove(int rowIndex,int colIndex){
        int[][] coordinates = Model.getBoard().getCoordinates();  //Array mit bisherigen Spielsteinen holen
        if(checkField(rowIndex,colIndex)) {                //Ist der Eintrag im Koordianten Array leer
            coordinates[rowIndex][colIndex] = Model.getActivePlayer().getPlayerID();     //Wird das Zeichen des Spieler eingetragen
            Model.getBoard().setCoordinates(coordinates); //Das aktuelle Koordinaten array wird ans Board zurückgegeben
            return true;
        }
        return false;
    }

    void checkDiagonale(){

        int coordinates[][] = Model.getBoard().getCoordinates();
        int Points1 = 0;
        int Points2 = 0;

        for(int i = 0; i <3; i++) {
            if(coordinates[i][i] == Model.getActivePlayer().getPlayerID()) { //1-1 2-2 3-3 -> Von oben links nach unten rechts
                Points1++;
            }else Points1 = 0;

            if(Points1 == 3 ){                   //Wurden 3 gleiche Zeichen in folge gefunden
                Model.setWinner(Model.getActivePlayer());           //setzen wir einen Sieger
                break;
            }

            int y = 2-i;
            int x = i;
            if(coordinates[y][x] == Model.getActivePlayer().getPlayerID()) { //2-0 1-1 0-2 -> Von oben rechts nach unten links
                Points2++;
            }else Points2 = 0;

            if(Points2 == 3 ){                   //Wurden 3 gleiche Zeichen in folge gefunden
                Model.setWinner(Model.getActivePlayer());           //setzen wir einen Sieger
                break;
            }
        }

    }

    public void clearGrid(){
        TicTacToeGrid.getChildren().removeIf(n -> n instanceof Circle);
    }

    @FXML
    private AnchorPane TicTacToePane;
    @FXML
    private GridPane TicTacToeGrid;
    @FXML
    private Label InfoLabel;
    @FXML
    private Label Player1CounterLabel;
    @FXML
    private Label Player2CounterLabel;
    @FXML
    private Button PlayAgainButton;


    //Wird bei Klick im Raster ausgeführt
    @FXML
    private void HandleGridClick(javafx.scene.input.MouseEvent event){
        Node clickedNode = event.getPickResult().getIntersectedNode(); //Was wurde angeklickt
        if (clickedNode != TicTacToeGrid) {                             //sicherstellen das es sich nicht um das Elternelement handelt
            Node parent = clickedNode.getParent();
            while (parent != TicTacToeGrid) {                           //Falls es innerhalb des Elements bereits childs gibt, sicherstellen das es sich um ein direktes child vom GridPane handelt
                clickedNode = parent;
                parent = clickedNode.getParent();
            }

            //Zeilen und spalten index speichern, da bei Zeile und spalte 0 der Wert NULL zurückgegeben wird, wird bei Null eine 0 gespeichert
            Integer colIndex = TicTacToeGrid.getColumnIndex(clickedNode) == null ? 0 : TicTacToeGrid.getColumnIndex(clickedNode);
            Integer rowIndex = TicTacToeGrid.getRowIndex(clickedNode) == null ? 0 :  TicTacToeGrid.getRowIndex(clickedNode);

            //Prüfen ob das Feld noch Frei ist und noch kein gewinner feststeht
           if(makeAMove(rowIndex,colIndex) && Model.getWinner() == null){
                addCircle(colIndex,rowIndex);
                Model.setGameStatus(2); //Prüfe gewinner
            }

        }
        GameStateHandler();
    }

    public void HandlePlayAgainClick(javafx.scene.input.MouseEvent event){
        Model.setGameStatus(0);
        clearGrid();
        GameStateHandler();
    }

    @FXML
    private void addCircle(int colIndex, int rowIndex){

        Color CircleColor = Model.getActivePlayer().getPlayerColor();
        Circle MyCircle = new Circle(200, 200, 10, CircleColor);
        TicTacToeGrid.add(MyCircle,colIndex,rowIndex);
    }

    @FXML
    private void backToMainMenu() throws Exception
    {
        MainMenuView MainMenuView = new MainMenuView();                      //TicTacToeView erzeugen
        Stage primaryStage = (Stage)TicTacToePane.getScene().getWindow();   //primaryStage ermitteln
        MainMenuView.start(primaryStage);                                      //TicTacToe auf PrimaryStage anzeigen
    }


}