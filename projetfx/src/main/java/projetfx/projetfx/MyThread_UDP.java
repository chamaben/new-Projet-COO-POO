package projetfx.projetfx;


import java.io.IOException;
import java.util.ArrayList;

public class MyThread_UDP extends Thread{
	
	public static  int num = 0;
	
	
	public static ArrayList<MyThread_UDP> Tab = new ArrayList<MyThread_UDP>(); 
	
	MyThread_UDP(int num) {
		super(Integer.toString(num));
		System.out.println("thread construit");
	}
	
	
	public void run() {
		//thread
		while(true) {
			Thread_UDP thread = new Thread_UDP(num);
			(Thread_UDP.Tab_u).add(thread);
			Thread_UDP.Tab_u.get(num).start();
			num++;
		    }
	}
}
