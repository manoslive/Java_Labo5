/**
 * Created by Emmanuel on 2015-02-25.
 */
import java.io.*;
import java.net.*;

public class Connection{
    public void run(String adresse, int port)
    {
        InetSocketAddress adresseSocket = null;
        Socket socket = null;

        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            adresseSocket= new InetSocketAddress(adresse,port);
            socket = new Socket();
            socket.connect(adresseSocket);

            writer = new PrintWriter(
                     new OutputStreamWriter(
                     socket.getOutputStream()));

            reader = new BufferedReader(
                     new InputStreamReader(
                     socket.getInputStream()));

            BufferedReader clavier = new BufferedReader(
                                     new InputStreamReader(System.in));

            boolean fini = false;
            String texte = null;

            System.out.println(reader.readLine());

            while(!fini)
            {
                texte = clavier.readLine();

                if(texte !=null)
                {
                    writer.println(texte);
                    writer.flush();

                    if(texte.trim().equalsIgnoreCase("Q"))
                    {
                        fini = true;
                    }
                    System.out.println(reader.readLine());
                }
                else
                {
                    fini = true;
                }
            }
            writer.close();
            reader.close();
            clavier.close();
            socket.close();
        }
        catch(IOException ioe)
        {
            System.err.println(ioe);
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    public void main(String[] args) throws IOException
    {
        Connection app = new Connection();
        app.run("localhost", 7);
    }
}
