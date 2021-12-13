package projetfx.projetfx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;



public class TCP_serveur
{
	
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	public static void main(String[] args) throws IOException {		
		
		//création du socket
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
	      System.out.println("Fermeture de connexion");
	    }
	    
	}
	
		
}