package projetfx.projetfx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_serveur
{
	
	static BufferedWriter os = null;
	static int port = 7899;
	public static  int num = 0;
	static ServerSocket s;
	
	
	public static void receive() {
			
		try {
			//création du socket
			s = new ServerSocket(port);
		    System.out.println(s.getLocalPort());
		    Socket serviceSocket = null;
		    
		    //thread
		    Thread_serveur thread = new Thread_serveur(num,serviceSocket);
		    (Thread_serveur.Tab_s).add(thread);
		    Thread_serveur.Tab_s.get(num).start();
		    num++;
		      
		} catch (IOException e) {
			System.out.println("exception levée1");
	        e.printStackTrace();

		}
		
	}
	/*
	public static void send(String message, String date, String login) {
			
		try {
			
			//création du socket
			ServerSocket s = new ServerSocket(port);
		    System.out.println(s.getLocalPort());
		    
		    
		    //Mise en place des échange de données en entrée et sortie
		    Socket serviceSocket =  s.accept();
		      
		    PrintStream output = new PrintStream(serviceSocket.getOutputStream());
		    System.out.println("échange de données mit en place ");
		      
		    // Envoyer et recevoir de la donnée
		    output.println(message+","+date+","+login);
		    System.out.println("envoi et réception de donnée ");
		      
		    //Fermer la connection
		    serviceSocket.close();
		    s.close();
		    System.out.println("fermeture de connection ");
		    
		} catch (IOException e) {
			System.out.println("exception levée");
	        e.printStackTrace();
		}
	}
*/
	
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	public static void main(String[] args) throws IOException {		
		//receive();
		//send("test","2022-01-12","cc");
		
	}
}
	