package Game.Controller;

import Game.View.MainMenuView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class TicTacToeController {

    public TicTacToeController(){

    }


    @FXML
    private AnchorPane TicTacToePane;

    @FXML
    private GridPane TicTacToeGrid;

    @FXML
    private void HandleGridClick(javafx.scene.input.MouseEvent event){
        Node clickedNode = event.getPickResult().getIntersectedNode();
        //Integer colIndex = TicTacToeGrid.getColumnIndex(clickedNode);
        if (clickedNode != TicTacToeGrid) {
            Node parent = clickedNode.getParent();
            while (parent != TicTacToeGrid) {
                clickedNode = parent;
                parent = clickedNode.getParent();
            }

        Integer colIndex = TicTacToeGrid.getColumnIndex(clickedNode);
        Integer rowIndex = TicTacToeGrid.getRowIndex(clickedNode);
        //System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
        System.out.println("ColIndex: " + colIndex);
        System.out.println("RowIndex: " + rowIndex);

        }
    }

    @FXML
    private void addCircle(){
        Circle MyCircle = new Circle(200, 200, 10, Color.BLACK);
        TicTacToeGrid.getChildren().add(MyCircle);
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
