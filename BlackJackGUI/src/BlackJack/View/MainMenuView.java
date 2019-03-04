package BlackJack.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuView {

    public MainMenuView(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/BlackJack/View/MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("BlackJack v0.1");
        stage.setScene(scene);
        stage.show();
    }
}
