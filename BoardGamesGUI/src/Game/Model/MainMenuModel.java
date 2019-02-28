package Game.Model;

import javafx.stage.Stage;

public class MainMenuModel {
    Stage primaryStage;// = null;

    public MainMenuModel(Stage primaryStage){
        this.primaryStage = primaryStage;
        System.out.println(primaryStage);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
