package projetfx.projetfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MyThread extends Thread

{
	//attributs
		static int port = 7894;
		public static  int num = 0;
		static String line;
		public static ArrayList<MyThread> Tab = new ArrayList<MyThread>(); 
		Socket service; 
	
	//constructeur
	MyThread(int num, Socket serviceassocie) {
		super(Integer.toString(num));
		start();
		service = serviceassocie  ;
	}
	
	
// créer un thread qui boucle sur le accept du receive de TCP serveur et qui dès qu'il reçoit un truc, crée un thread et continue après d'attendre
	
	
	public void run() {
		try {
			
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
	      Timestamp date = recup[2];
	      
	      
	      //Reconstitution du message
	     Message message2 = new Message(pseudo,WindowModel.user.pseudo, message, date);
	      
	    //Fermer la connection
	      service.close();
	      System.out.println("fermeture de connection ");
		} 
		catch (IOException e) {
		 System.out.println("exception levée");
      e.printStackTrace();

		}
	}
	
	public static void main() {
		
		//remplissage des index de la liste avec des threads
	    Tab.get(num).start();
	    num ++;
		    
	}
}