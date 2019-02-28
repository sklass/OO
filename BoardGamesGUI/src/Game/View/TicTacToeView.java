package Game.View;
import Game.Controller.TicTacToeController;
import Game.Model.BoardGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TicTacToeView {

    public void start (BoardGame Model) throws Exception {
        //Stage aus Modell auslesen
        Stage stage = Model.getPrimaryStage();
        //Oberflächeninhalte in den FXML Loader laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game/View/TicTacToe.fxml"));
        //Oberflächeninhalte in einem Pane platzieren
        Pane root = loader.load();
        //Der TTT Controller ist in TicTacToe.fxml definiert, hier wird er abgefragt
        TicTacToeController Controller = loader.getController();
        //Dem Controller wird das TTT Model zugewiesen. Auf diesem Weg sind dem Controller und der View das Modell bekannt
        Controller.setModel(Model);
        //Aufruf Gamestatehandler um das Soiel zu initialisieren
        Controller.GameStateHandler();
        Scene scene = new Scene(root);
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

}
