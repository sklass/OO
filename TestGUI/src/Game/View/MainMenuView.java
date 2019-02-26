package Game.View;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuView {

    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Game/View/MainMenu.fxml"));

        Scene scene = new Scene(root, 300, 275);
        Pane MainMenuPane = (AnchorPane) scene.lookup("#MainMenuPane");
        MainMenuPane.setMaxSize(300,275);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
}
