

import Game.Controller.MainMenuController;
import Game.Controller.TicTacToeController;
import Game.Model.BoardModel;
import Game.Model.MainMenuModel;
import Game.View.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
       // MainMenuView MyMainMenu = new MainMenuView();
       // MyMainMenu.start(primaryStage);


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
