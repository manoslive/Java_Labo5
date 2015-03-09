/**
 * Created by Emmanuel on 2015-02-18.
 */

import java.io.*;

public class Terminateur implements Runnable {
    public void run() {
        String ligneSansTrim, ligne;
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(System.in));
            do {
                ligneSansTrim = reader.readLine();
                // On trim la scrap
                ligne = ligneSansTrim.trim();
            }
            while (!ligne.toLowerCase().equals("q"));

        } catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
        }
    }
}
