package Game.View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FourWinsView {

    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Game/View/FourWins.fxml"));
        //Scene scene = new Scene(root, 800, 600);
        Scene scene = new Scene(root);
        //Pane FourWinsPane = (AnchorPane) scene.lookup("#FourWinsPane");
        //FourWinsPane.setMaxSize(800,600);
        stage.setTitle("FourWins");
        stage.setScene(scene);
        stage.show();
    }

}
