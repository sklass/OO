package ChatServer.controller;

import ChatServer.model.ServerModel;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    ServerModel Model;
    ServerSocket listener = null;
    Socket connection;

    public ServerController(ServerModel Model){
        this.Model = Model;
    }

    private void startServer(){

        // Try to open a new socket
        try {
            listener = new ServerSocket(8080);
        }
        catch (Exception e) {

            System.exit(1);
        }

        // Try to accept new requests
        try {


            // Wait for a connection and accept it
            // And open a new thread
            while(true) {
                connection = listener.accept();
                new Connection(connection, clientNumber++).start();
            }
        }
        catch (Exception e) {
            out.println(e);
            System.exit(1);
        }
    }


    public void setModel(ServerModel model) {
        Model = model;
    }
}
