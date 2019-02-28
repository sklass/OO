
import Game.Controller.MainMenuController;
import Game.Model.MainMenuModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Model erzeugen und Stage darin speichern
        MainMenuModel MainMenuModel = new MainMenuModel(primaryStage);
        //Controller erzeugen und Model übergeben
        MainMenuController MainMenuController = new MainMenuController();
        MainMenuController.setModel(MainMenuModel);
        //Controller-Methode zur Anzeige des Views aufrufen
        MainMenuController.showView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
