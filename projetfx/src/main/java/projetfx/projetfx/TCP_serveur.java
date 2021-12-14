package projetfx.projetfx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;



public class TCP_serveur
{
	
	BufferedWriter os = null;
	String line;
	
	public void receive() {
			
		try {
			//création du socket
			ServerSocket s = new ServerSocket(7892);
		    System.out.println(s.getLocalPort());
		    
		    //while(true) {
		    	//Mise en attente du serveur
		      Socket serviceSocket =  s.accept();
		      BufferedReader br = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));  
		      System.out.println("accept fait ");
		      
		    //Mise en place des échange de données en entrée et sortie
		      PrintStream output = new PrintStream(serviceSocket.getOutputStream());
		      System.out.println("échange de données mit en place ");
		      
		   // Recevoir de la donnée
		      
		      line = br.readLine();
		      System.out.println(line+"\n");
		      
		    //Fermer la connection
		      serviceSocket.close();
		      System.out.println("fermeture de connection ");
		    //}
		} catch (IOException e) {
			 System.out.println("exception levée");
	        e.printStackTrace();

<<<<<<< HEAD
	}
		
		}
		
	public void send() {
			
		try {
			//création du socket
			ServerSocket s = new ServerSocket(7892);
		    System.out.println(s.getLocalPort());
		    
		    
		    //Mise en place des échange de données en entrée et sortie
		      Socket serviceSocket =  s.accept();
		      PrintStream output = new PrintStream(serviceSocket.getOutputStream());
		      System.out.println("échange de données mit en place ");
		      
		   // Envoyer et recevoir de la donnée
		      output.println(new Date());
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
		
		
=======
	/*	//création du socket
		ServerSocket s = new ServerSocket(7884);
	    System.out.println(s.getLocalPort());
	    
	    while(true) {
	    System.out.println("Début while");
	    	//Mise en attente du serveur
	      Socket serviceSocket =  s.accept();
	      System.out.println("Serveur en attente");
	      
	    //Mise en place des échange de données en entrée et sortie
	      PrintStream output = new PrintStream(serviceSocket.getOutputStream());
	      System.out.println("Flux de données");
	    //Envoyer et recevoir de la donnée
	      output.println(new Date());
	      
	    //Fermer la connection
	      serviceSocket.close();
	      
	    }
	    */
	}
		//System.out.println("Fermeture de connexion");
>>>>>>> f5badefc4c3b693cb67a3cd8f0f796e3bf4e434d
	}
}
	