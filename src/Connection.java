/**
 * Created by Ninja et Shiawn Croopers on 2015-03-09.
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
            System.out.println("Client deconnecte!");
            socket.close();
            writer.close();
            reader.close();
        }
        catch (IOException ioe) {
            System.exit(1);
        } catch (NullPointerException npe) {
            System.err.println("Erreur: le client a quitter sans fermer la connection!");
        }
    }
}