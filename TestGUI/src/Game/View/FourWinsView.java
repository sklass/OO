package Game.View;


import Game.Controller.FourWinsController;
import Game.Model.BoardGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FourWinsView {

    public void start (BoardGame Model) throws Exception {
        //Stage aus Modell auslesen
        Stage stage = Model.getPrimaryStage();
        //Oberflächeninhalte in den FXML Loader laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game/View/FourWins.fxml"));
        //Oberflächeninhalte in einem Pane platzieren
        Pane root = loader.load();
        //Der TTT Controller ist in TicTacToe.fxml definiert, hier wird er abgefragt
        FourWinsController Controller = loader.getController();
        //Dem Controller wird das TTT Model zugewiesen. Auf diesem Weg sind dem Controller und der View das Modell bekannt
        Controller.setModel(Model);
        //Aufruf Gamestatehandler um das Soiel zu initialisieren
        Controller.GameStateHandler();
        //Scene scene = new Scene(root, 800, 600);
        Scene scene = new Scene(root);
        stage.setTitle("FourWins");
        stage.setScene(scene);
        stage.show();
    }

}
