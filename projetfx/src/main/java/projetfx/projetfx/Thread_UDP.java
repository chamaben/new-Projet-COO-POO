package projetfx.projetfx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Thread_UDP extends Thread {
	
	public static ArrayList<Thread_UDP> Tab_u = new ArrayList<Thread_UDP>(); 
	
	Thread_UDP(int num) {
		super(Integer.toString(num));
		System.out.println("thread construit");
	}
	
	
	public void run() {
		for (int i=0;i<2;i++) { {
			//créer un élément de la liste qui va ensuite se lance de son côté: le thread
			//UDP_serveur udp = new UDP_serveur();
			if (!VarGlobal.ClosingApp)
				WindowModel.serveur_udp.run();
			//WindowModel.serveur_udp.close();
			//udp.close();
		}
		}



	}
}
