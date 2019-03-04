package ChatServer.view;

import ChatServer.controller.ServerController;
import ChatServer.model.ServerModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServerView {

    public void start (ServerModel Model) throws Exception {
        //Stage aus Modell auslesen
        Stage stage = Model.getPrimaryStage();
        //Oberflächeninhalte in den FXML Loader laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChatServer/view/ServerView.fxml"));
        //Oberflächeninhalte in einem Pane platzieren
        Pane root = loader.load();
        //Der TTT ServerController ist in TicTacToe.fxml definiert, hier wird er abgefragt
        ServerController Controller = loader.getController();
        //Dem ServerController wird das Model zugewiesen. Auf diesem Weg sind dem ServerController und der View das Modell bekannt
        Controller.setModel(Model);

        Scene scene = new Scene(root);
        stage.setTitle("ChatServer v0.1Alpha");
        stage.setScene(scene);
        stage.show();
    }
}
