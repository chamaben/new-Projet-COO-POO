package projetfx.projetfx;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;

public class UDP_serveur {
	private int port = 7907;
	private DatagramSocket dgramSocket;
	//private boolean running;
	private byte[] buffer = new byte[256];
	public static  int num_udp = 0;
	static int etat1= 0;
	
	public UDP_serveur() throws SocketException {
		dgramSocket = new DatagramSocket(this.port);
	}
	
	public void run() throws IOException, ClassNotFoundException, SQLException {
		
		//this.running = true;
		
		for (int i=0;i<1;i++) {
		//while(true) {
			//crée un objet datagrampacket pour les datagrams entrants
			DatagramPacket inPacket = new DatagramPacket(this.buffer,this.buffer.length);
			System.out.println("Objet datagram créé");
			
			//accepte un datagram entrant
				this.dgramSocket.receive(inPacket);
				String message = new String(inPacket.getData());
				String[] recup = message.split(",");
			    String login = recup[0];
			    //System.out.println(login+"\n");
			    String etat = recup[1];
			    //System.out.println(etat+"\n");
			    String ip  = recup[2];
			    //System.out.println(ip+"\n");
			    
			    etat1= Integer.parseInt(etat); 
			    
			    
			    //System.out.println(new String(inPacket.getData()));
				System.out.println("Datagram entrant accepté");
				
				//accepte les infos du paquet
				InetAddress clientAddress = inPacket.getAddress();
				int clientPort = inPacket.getPort();
				//System.out.println("Infos du paquet acceptées");
				
				inPacket = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);
				
				//récupère la donnée dans le buffer
				String mess = new String(inPacket.getData(),0,inPacket.getLength());
				System.out.println("j'ai reçu le message (dans le buffer) : "+mess);
				

		}
	}
	
	public void close() throws ClassNotFoundException, IOException, SQLException {
		// fermeture du thread udp
	    Thread_UDP.Tab_u.get(num_udp).interrupt();
		System.out.println("Fermeture du thread");
		//fermeture datagramsocket
		//this.dgramSocket.close();
		//System.out.println("Fermeture du datagramsocket");
		
	    // après fermeture du thread
	    try {
			WindowModel.secondarycontroller.RefreshPage();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (SecondaryController.login_destinataire!=null) {
			WindowModel.secondarycontroller.StartChat();
	    } 
	}
	
	public void receive() {
		Thread_UDP thread = new Thread_UDP (num_udp);
    	(Thread_UDP.Tab_u).add(thread);
	    System.out.println("thread ajouté");
	    Thread_UDP.Tab_u.get(num_udp).start();
	    System.out.println("thread lancé ");
	    //num_udp++;

	}
	
	public void end_thread_udp() {
		for (int i=0;i<=num_udp;i++) {
			Thread_UDP.Tab_u.get(i).interrupt();
		}
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		UDP_serveur udp = new UDP_serveur();
		//udp.run();
		udp.receive();
		//udp.close();
	}

}
