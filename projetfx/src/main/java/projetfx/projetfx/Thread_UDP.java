package projetfx.projetfx;

import java.util.ArrayList;

public class Thread_UDP extends Thread {
	
	public static ArrayList<Thread_UDP> Tab_u = new ArrayList<Thread_UDP>(); 
	
	Thread_UDP(int num) {
		super(Integer.toString(num));
	}
	
	
	public void run() {
		for (int i=0;i<2;i++) { {
			if (!VarGlobal.ClosingApp)
				WindowModel.serveur_udp.run();
		}
		}



	}
}
