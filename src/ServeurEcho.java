/**
 * Created by Ninja et Shiawn Croopers on 2015-03-09.
 */


import java.io.*;
import java.net.*;
import java.util.concurrent.TimeoutException;

public class ServeurEcho {
    public void run(int port) {
        try {
            Socket socket = null;
            ServerSocket socketServeur = new ServerSocket(port);
            PrintWriter writer;
            Thread francois = new Thread(new Terminateur());
            francois.start();

            boolean fini = false;
            String ligne = null;
            // socket.setSoTimeout(500);
            System.out.println("Entrer \"Q\" pour terminer!");
            while (francois.isAlive()) {
                try {
                    socket = socketServeur.accept();
                    System.out.println("Client connecte!");
                    Thread client = new Thread(new Connection(socket));
                    client.setDaemon(true);
                    client.start();
                } catch (SocketTimeoutException ste) {

                }
            }
            // writer.close();
            socket.close();
            socketServeur.close();

        } catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServeurEcho serveur = new ServeurEcho();
        serveur.run(666); // Doom ;)
    }
}
