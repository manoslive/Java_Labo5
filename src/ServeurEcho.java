/**
 * Created by Emmanuel on 2015-02-25.
 */

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeoutException;

public class ServeurEcho {
    public void run(int port) {
        try {
            Socket socket = new Socket();
            ServerSocket socketServeur = new ServerSocket(port);
            PrintWriter writer;
            Thread francois = new Thread(new Terminateur());
            francois.start();

            writer = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));


            writer.println("Entrer \"Q\" pour terminer!");
            writer.flush();
            boolean fini = false;
            String ligne = null;
            socket.setSoTimeout(500);

            while (francois.isAlive()) {
                try {
                    socket = socketServeur.accept();
                    System.out.println("Client connecté!");
                    Thread client = new Thread(new Connection(socket));
                    client.setDaemon(true);
                    client.start();
                } catch (SocketTimeoutException ste) {

                }
            }
            writer.close();
            socket.close();
            socketServeur.close();

        } catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
        }
    }

    public void main() {
        ServeurEcho serveur = new ServeurEcho();
        serveur.run(666); // Doom ;)
    }
}
