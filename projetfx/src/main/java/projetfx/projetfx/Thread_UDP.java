package projetfx.projetfx;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Thread_UDP extends Thread {
	
	public static ArrayList<Thread_UDP> Tab_u = new ArrayList<Thread_UDP>(); 
	
	Thread_UDP(int num) {
		super(Integer.toString(num));
		System.out.println("thread construit");
	}
	
	
	public void run() {
		while(true) {
			try {
		      //créer un élément de la liste qui va ensuite se lance de son côté: le thread
		      UDP_serveur.run();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (IOException e) {
				 System.out.println("exception levée");
				 e.printStackTrace();
				 }
			}
	}
}