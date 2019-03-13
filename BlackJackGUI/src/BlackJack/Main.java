package BlackJack;

import BlackJack.Model.BlackJackModel;
import BlackJack.controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        BlackJackModel Model = new BlackJackModel();
        Model.setStage(primaryStage);
        MainMenuController Controller = new MainMenuController();
        Controller.setModel(Model);
        Controller.shoView();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
