package ChatServer.controller.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Thread{
    private int clientNumber;
    private Socket connection;
    private Scanner sc;
    private String input;
    private String output;

    public Connection(Socket connection, int clientNumber) {
        this.clientNumber = clientNumber;
        this.connection = connection;

      //  System.out.println("New connection with client " + Colors.CYAN + this.clientNumber + Colors.RESET + " at " + Colors.CYAN + connection.getInetAddress().getHostAddress() + Colors.RESET);
    }

    @Override
    public void run() {
        try {
            // Open input and output streams
            new Receiver(new BufferedReader(new InputStreamReader(connection.getInputStream()))).start();
            new Transmitter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))).start();
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(4);
        }
    }

}