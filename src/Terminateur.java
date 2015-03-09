/**
 * Created by Ninja et Shiawn Croopers on 2015-03-09.
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
            while (!ligne.equalsIgnoreCase("q"));
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
            System.exit(1);
        }
    }
}
