package Game.Model;
//Das Model speichert alle Daten der Anwendung

import javafx.stage.Stage;

public class BoardModel {
    Stage primaryStage = null;

    public BoardModel(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
