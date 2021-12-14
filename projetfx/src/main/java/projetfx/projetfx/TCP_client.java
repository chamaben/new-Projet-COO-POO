package projetfx.projetfx;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_client {
	
	//le destinataire reçoit la requête TCP, il l'accepte et lance un thread puis revient sur accept (boucle infinie)
	//initialiser la connexion TCP
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		BufferedWriter os = null;
	    BufferedReader is = null;
	    String[] words = null;
		System.out.println("je suis ici1");
		
		//lire l'ad IP dans le fichier
	    try
	    {
	      // Le fichier d'entrée
	      File file = new File("/home/mallevil/Documents/POO/projetfx/src/main/java/projetfx/projetfx/liste.txt");    
	      // Créer l'objet File Reader
	      FileReader fr = new FileReader(file);  
	      // Créer l'objet BufferedReader        
	      BufferedReader br = new BufferedReader(fr);  
	      StringBuffer sb = new StringBuffer();    
	      String line;
	      while((line = br.readLine()) != null)
	      {
	        // ajoute la ligne au buffer
	        sb.append(line);      
	        sb.append("\n");   
	        words = line.split(",");
		    
	      }
	      fr.close();    
	      //System.out.println("Contenu du fichier: ");
	      //System.out.println(sb.toString());  
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }
	    
	    System.out.println("avant création socket\n");
		//Socket socketOfClient = new Socket("localhost",Integer.parseInt(words[2]));
	    System.out.println(words[1]); 
	    System.out.println(words[2]);
		Socket socketOfClient = new Socket(InetAddress.getByName(words[1]),Integer.parseInt(words[2]));
		System.out.println("sock créé");
		// Create output stream at the client (to send data to the server)
        os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
        System.out.println("output stream\n");
        
        // Input stream at Client (Receive data from the server).
        is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
        System.out.println("input stream\n");
        
        try {

            // Write data to the output stream of the Client Socket.
            os.write("HELO");
            System.out.println("write1\n");
  
            // End of line
            os.newLine();
    
            // Flush data.
            os.flush();
            
            // Read data sent from the server.
            // By reading the input stream of the Client Socket.
            String responseLine = is.readLine();
            System.out.println("read\n"+responseLine);
            /*while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.indexOf("OK") != -1) {
                    break;
                }
            }*/
            System.out.println("S");
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
		System.out.println("je suis ici");
	}
}
