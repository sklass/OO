package ChatServer.controller.IO;

import java.io.BufferedReader;

public class Receiver extends Thread {
    private BufferedReader receiver;
    private String input;

    public Receiver(BufferedReader receiver) {
        this.receiver = receiver;
    }
    @Override
    public void run() {
        try {
            while(true) {
                // Read the recieved data
                input = receiver.readLine();

            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(5);
        }
    }
}
