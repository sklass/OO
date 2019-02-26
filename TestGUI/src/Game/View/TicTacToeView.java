package Game.View;


import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TicTacToeView {

    public void start (Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("/Game/View/TicTacToe.fxml"));
        //Scene scene = new Scene(root, 300, 275);
        Scene scene = new Scene(root);
        Pane TicTacToePane = (AnchorPane) scene.lookup("#TicTacToePane");
        //TicTacToePane.setMaxSize(300,275);
        stage.setTitle("TicTacToe");
/*
        GridPane TicTacToeGrid = new GridPane();
        TicTacToeGrid.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Label label = new Label("Label " + i + "/" + j);
                label.setMouseTransparent(true);
                TicTacToeGrid.setRowIndex(label, i);
                TicTacToeGrid.setColumnIndex(label, j);

                TicTacToeGrid.getChildren().add(label);
            }
        }
        root.getChildren().add( TicTacToeGrid);

*/
        stage.setScene(scene);
        stage.show();
/*
        TicTacToeGrid.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                for( Node node: TicTacToeGrid.getChildren()) {

                    if( node instanceof Label) {
                        if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                            System.out.println( "Node: " + node + " at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
                        }
                    }
                }
            }
        });
        */
    }

}
