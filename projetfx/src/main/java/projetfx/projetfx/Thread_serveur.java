package projetfx.projetfx;

import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;

public class Thread_serveur extends Thread {

	Socket service; 
	TCP_serveur serveur;
	public static  int num = 0;
	public static ArrayList<Thread_serveur> Tab_s = new ArrayList<Thread_serveur>();
	private boolean running = true;


	
	Thread_serveur(int num, Socket serviceassocie) {
		super(Integer.toString(num));
		service = serviceassocie  ;
		System.out.println("thread construit");
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(running) {

			try {
	    	//Mise en attente du serveur On associe au thread son service socket 
		      Socket serviceSocket =  serveur.s.accept();
		      System.out.println("accept fait ");
		      //créer un élément de la liste qui va ensuite se lance de son côté: le thread
		      MyThread thread = new MyThread (num,serviceSocket);
		      (MyThread.Tab).add(thread);
		      System.out.println("thread ajouté");
		      MyThread.Tab.get(num).start();
		      System.out.println("thread lancé ");
		      num++;
		      
			}catch (IOException e) {
				 System.out.println("exception levée");
			        e.printStackTrace();

				}
		    }
	}
	

	@Override
	public void interrupt() {
		for (MyThread Ts : MyThread.Tab ) {
			if (!Ts.isInterrupted())
				Ts.interrupt();
		}
		running=false;
		super.interrupt();
	}

}

