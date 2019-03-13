package BlackJack.View;

import BlackJack.Model.BlackJackModel;
import BlackJack.controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuView {

    public MainMenuView(BlackJackModel Model) throws Exception{
        Stage stage = Model.getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BlackJack/View/MainMenu.fxml"));
        Pane root = loader.load();
        MainMenuController Controller = loader.getController();
        Controller.setModel(Model);
        Scene scene = new Scene(root);
        stage.setTitle("BlackJack v1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
