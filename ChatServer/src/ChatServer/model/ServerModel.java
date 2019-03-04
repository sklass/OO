package ChatServer.model;

import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerModel {
    Stage primaryStage;
    BufferedReader receiver;
    BufferedWriter transmitter;
    ArrayList <Client> clients;
    int ServerPort;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public int getServerPort() {
        return ServerPort;
    }

    public void setServerPort(int serverPort) {
        ServerPort = serverPort;
    }
}
