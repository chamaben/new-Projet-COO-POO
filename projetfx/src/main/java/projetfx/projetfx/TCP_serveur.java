package projetfx.projetfx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_serveur
{
	
	static BufferedWriter os = null;
	public static  int num = 0;
	static ServerSocket s;
	private static Thread ServerThread = null;
	
	
	public static void receive() throws IOException {
			
		try {
			//création du socket
			s = new ServerSocket(VarGlobal.portTCP);
		    System.out.println(s.getLocalPort());
		    Socket serviceSocket = null;
		    
		    //thread
		    Thread_serveur thread = new Thread_serveur(num,serviceSocket);
		    ServerThread=thread;
		    thread.start();
		    num++;
		      
		} catch (IOException e) {
			System.out.println("exception levée1");
	        e.printStackTrace();
	        s.close();

		}
		
	}
	
	
	
	public static void end_thread_tcp() {
		if (!ServerThread.isInterrupted())
			ServerThread.interrupt();
		try {
			s.close();
		} catch (IOException e) {
			 System.out.println("erreur fermeture serv socket");
		}
		
	}

	
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	/*public static void main(String[] args) throws IOException {		
		receive();
		//send("test","2022-01-12","cc");
		
	}*/
}
	