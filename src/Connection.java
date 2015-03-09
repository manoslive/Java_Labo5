/**
 * Created by Emmanuel on 2015-02-25.
 */

import java.io.*;
import java.net.*;

public class Connection implements Runnable {
    public Socket socket = null;

    public Connection(Socket leSocket) {
        socket = leSocket;
    }

    public void run() {
        String ligne = new String();
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));

            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            do {
                ligne = reader.readLine();
                writer.println(ligne);
                writer.flush();
            } while (!ligne.isEmpty());
            socket.close();
            writer.close();
            reader.close();
        } catch (IOException ioe) {
            System.exit(1);
        }
    }
}
