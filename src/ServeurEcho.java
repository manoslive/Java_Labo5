/**
 * Created by Emmanuel on 2015-02-25.
 */

import java.io.*;
import java.net.*;

public class ServeurEcho {
    public void run(int port) {
        try {
            Socket socket = new Socket();
            ServerSocket socketServeur = new ServerSocket(port);
            PrintWriter writer;
            BufferedReader reader;
            Thread francois = new Thread(new Terminateur());
            francois.start();

            writer = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));

            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));


            System.out.println("Client connecté!");

            writer.println("Entrer \"Q\" pour terminer!");
            writer.flush();
            boolean fini = false;
            String ligne = null;

            while (francois.isAlive()) {

                socket = socketServeur.accept();
                Connection client = new Connection();
            }
                writer.close();
                reader.close();
                socket.close();
                socketServeur.close();

        }
        catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
        }
    }
    public void main()
    {
        ServeurEcho serveur = new ServeurEcho();
        serveur.run(666);
    }
}
