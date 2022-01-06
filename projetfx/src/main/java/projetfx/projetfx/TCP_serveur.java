package projetfx.projetfx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;



public class TCP_serveur
{
	
	static BufferedWriter os = null;
	static int port = 7899;
	public static  int num = 0;
	static String line;
	public static Socket service; 
	public SecondaryController secondarycontroller;
	
	public static void receive() {
			
		try {
			//création du socket
			ServerSocket s = new ServerSocket(port);
		    System.out.println(s.getLocalPort());
		    
		    while(true) {
	    	//Mise en attente du serveur On associe au thread son service socket 
	      Socket serviceSocket =  s.accept();
	     
	      BufferedReader br = new BufferedReader(new InputStreamReader(service.getInputStream()));
			
			//Mise en place des échange de données en entrée et sortie
		      PrintStream output = new PrintStream(service.getOutputStream());
		      System.out.println("échange de données mis en place ");
		      
		   // Recevoir de la donnée
		      
		      //message+date+pseudo
		      line = br.readLine();
		      System.out.println(line+"\n");
		      String[] recup = line.split(",");
		      String message = recup[0];
		      String pseudo = recup[1];
		      String sdate = recup[2];
		      Timestamp date= Timestamp.valueOf(sdate);
		      
		      
		      //Reconstitution du message
		     Message message2 = new Message(pseudo,WindowModel.user.pseudo, message, date);
		     //secondarycontroller.DisplayMessage(message2);
		      
		    //Fermer la connection
		      service.close();
		      System.out.println("fermeture de connection ");    
	      
	      
	      //créer un élément de la liste qui va ensuite se lance de son côté: le thread
	      //MyThread thread = new MyThread (num,serviceSocket);
	      //(MyThread.Tab).add(thread);
	      //num++;

	      System.out.println("accept fait ");
	    }
		      
		} catch (IOException e) {
			 System.out.println("exception levée");
	        e.printStackTrace();

		}
		
	}
		
	public static void send(String message, String date, String pseudo) {
			
		try {
			//création du socket
			ServerSocket s = new ServerSocket(port);
		    System.out.println(s.getLocalPort());
		    
		    
		    //Mise en place des échange de données en entrée et sortie
		      Socket serviceSocket =  s.accept();
		      
		      PrintStream output = new PrintStream(serviceSocket.getOutputStream());
		      System.out.println("échange de données mit en place ");
		      
		   // Envoyer et recevoir de la donnée
		      output.println(message+","+date+","+pseudo);
		      System.out.println("envoi et réception de donnée ");
		      
		    //Fermer la connection
		      serviceSocket.close();
		      System.out.println("fermeture de connection ");
		    //}
		} catch (IOException e) {
			 System.out.println("exception levée");
	        e.printStackTrace();

	}
		
		}

	
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	public static void main(String[] args) throws IOException {		
		receive();
		//send("hello");
		
	}
}
	