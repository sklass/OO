

import Game.Controller.MainMenuController;
import Game.View.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        MainMenuView MainMenu = new MainMenuView();
        MainMenu.start(primaryStage);

        //Model erzeugen und Stage übergeben
        //BoardModel myBoardModel = new BoardModel(primaryStage);
        //Controller erzeugen und Model übergeben
        //controller myController = new controller(myBoardModel);

        //Controller-Methode zur Anzeige des Views aufrufen
        //myController.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
