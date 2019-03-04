package BlackJack;

import BlackJack.View.MainMenuView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainMenuView MainMenu = new MainMenuView(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}