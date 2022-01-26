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
	
	
	
	public void end_thread_tcp() {
		for (int i=0;i<=num;i++) {
			Thread_serveur.Tab_s.get(i).interrupt();
		}
	}

	
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	/*public static void main(String[] args) throws IOException {		
		receive();
		//send("test","2022-01-12","cc");
		
	}*/
}
	