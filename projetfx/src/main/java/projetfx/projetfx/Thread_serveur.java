package projetfx.projetfx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;

public class Thread_serveur extends Thread {

	Socket service; 
	TCP_serveur serveur;
	public static  int num = 0;
	public static ArrayList<Thread_serveur> Tab_s = new ArrayList<Thread_serveur>(); 
	
	Thread_serveur(int num, Socket serviceassocie) {
		super(Integer.toString(num));
		service = serviceassocie  ;
	}
	
	
	public void run() {
		while(true) {
			try {
	    	//Mise en attente du serveur On associe au thread son service socket 
		      Socket serviceSocket =  serveur.s.accept();
		      
		      //créer un élément de la liste qui va ensuite se lance de son côté: le thread
		      MyThread thread = new MyThread (num,serviceSocket);
		      (MyThread.Tab).add(thread);
		      MyThread.Tab.get(num).start();
		      num++;
	
		      System.out.println("accept fait ");
		      
			}catch (IOException e) {
				 System.out.println("exception levée");
			        e.printStackTrace();

				}
		    }
	}
	//crée un thread pour conversation
}
