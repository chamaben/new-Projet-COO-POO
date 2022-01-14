package projetfx.projetfx;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Thread_UDP extends Thread {
	
	Thread_UDP(int num, Socket serviceassocie) {
		super(Integer.toString(num));
		System.out.println("thread construit");
	}
	
	
	public void run() {
		
	}
}
