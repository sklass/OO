package ChatServer.controller;

import ChatServer.model.ServerModel;
import ChatServer.view.ServerView;
import javafx.application.Application;

import javafx.stage.Stage;

public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Model erzeugen und primary Stage darin speichern
        ServerModel Model = new ServerModel();
        Model.setPrimaryStage(primaryStage);

        //View erzeugen und Model übergeben
        ServerView View = new ServerView();
        View.start(Model);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
