package Game.View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TicTacToeView {

    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Game/View/TicTacToe.fxml"));
        //Scene scene = new Scene(root, 300, 275);
        Scene scene = new Scene(root);
        Pane TicTacToePane = (AnchorPane) scene.lookup("#TicTacToePane");
        //TicTacToePane.setMaxSize(300,275);
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

}
