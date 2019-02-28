import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] argv) {

        try {
            String host = "localhost";
            Socket meinEchoSocket = new Socket(host,1234);

            OutputStream socketoutstr = meinEchoSocket.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter( socketoutstr );
            BufferedWriter bw = new BufferedWriter( osr );

            InputStream socketinstr = meinEchoSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader( socketinstr );
            BufferedReader br = new BufferedReader( isr );

            String anfrage = "Hallo";
            String antwort;

            bw.write(anfrage);
            bw.newLine();
            bw.flush();
            antwort = br.readLine();

            System.out.println("Host = "+host);
            System.out.println("Echo = "+antwort);

            bw.close();
            br.close();
            meinEchoSocket.close();
        }
        catch (UnknownHostException uhe) {
            System.out.println(uhe);
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }

    } // ende: main

} // Ende: public class MeinEchoClient 