package Game.Model;

import javafx.stage.Stage;

public class MainMenuModel {
    Stage primaryStage;
    public MainMenuModel(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
