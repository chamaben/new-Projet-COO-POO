package projetfx.projetfx;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

//A faire: Séparer les méthodes receive et send et dans le receive faire un thread qui boucle pour attendre les messsages
//Chaque client/serveur a sa méthode send et receive

public class TCP_client {
	
	static BufferedWriter os = null;
    static BufferedReader is = null;
	String[] words = null;
	static int port = 7899;
	
	//le destinataire reçoit la requête TCP, il l'accepte et lance un thread puis revient sur accept (boucle infinie)
	//initialiser la connexion TCP
	
	public static void receive() {
        
        try {
        	//récupérer le port sur lequel on va écouter 
    		Socket socketOfClient = new Socket("host",port);
    		System.out.println("sock créé");

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            String responseLine = is.readLine();
            
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
	}
	
public static void send(String message, String date, String pseudo) {
		
	String[] words = null;
	
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
        
        System.out.println(line);
        sb.append("\n");   
        words = line.split(",");
	    //System.out.println(words[1]);  
      }
      fr.close();    
      //System.out.println("Contenu du fichier: ");
      //System.out.println(sb.toString());  
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    
    
    try {
    	System.out.println("avant création socket");
    	//System.out.println(InetAddress.getByName(words[1]));
    	//Socket socketOfClient = new Socket("host",Integer.parseInt(words[2]));
    	Socket socketOfClient = new Socket(InetAddress.getByName(words[1]),Integer.parseInt(words[2]));
    	System.out.println("sock créé");
    	// Create output stream at the client (to send data to the server)
        //os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
        
        PrintStream output = new PrintStream(socketOfClient.getOutputStream());
	    System.out.println("échange de données mit en place ");
	    
        output.println(message+date+pseudo);
	    System.out.println("envoi et réception de donnée ");
    	
        output.close();
        socketOfClient.close();
    } catch (IOException e) {
        System.err.println("IOException:  " + e);
    }
	
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		//receive();
		send("hello","ee","rr");
		
	}
}
