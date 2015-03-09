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

            System.out.println("Serveur echo en attente de connection!");
            socket = socketServeur.accept();

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

            while (!fini) {
                ligne = reader.readLine();
                if (ligne != null) {
                    writer.println(ligne);
                    writer.flush();

                    if (ligne.trim().equalsIgnoreCase("Q")) {
                        System.out.println("Client deconnecte!");
                        fini = true;
                    }
                }
                writer.close();
                reader.close();

                socket.close();
                socketServeur.close();

            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
        }
    }
}