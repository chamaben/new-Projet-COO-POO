package projetfx.projetfx;

public class MyThread extends Thread

{
	//attributs
		static int port = 7894;
		public static  int num = 1;
		public static MyThread[] Tab; 
	
	//constructeur
	MyThread(int num) {
		super(Integer.toString(num));
		start();
	}
	
	
// créer un thread qui boucle sur le accept du receive de TCP serveur et qui dès qu'il reçoit un truc, crée un thread et continue après d'attendre
	
	
	public void run() {
		TCP_serveur.receive();
		num ++;
	}
	
	public static void main() {
		
		//avoir des noms uniques
	    Tab[num] = new MyThread (num);
	    Tab[num].start();
		    
	}
}