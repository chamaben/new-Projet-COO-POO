package projetfx.projetfx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;



public class TCP_serveur
{
	
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	public static void main(String[] args) throws IOException {		
		
		try {
			//création du socket
			ServerSocket s = new ServerSocket(7891);
		    System.out.println(s.getLocalPort());
		    
		    //while(true) {
		    	//Mise en attente du serveur
		      Socket serviceSocket =  s.accept();
		      
		    //Mise en place des échange de données en entrée et sortie
		      PrintStream output = new PrintStream(serviceSocket.getOutputStream());
		      
		   // Envoyer et recevoir de la donnée
		      output.println(new Date());
		      
		    //Fermer la connection
		      serviceSocket.close();
		    //}
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	}
	
		
}