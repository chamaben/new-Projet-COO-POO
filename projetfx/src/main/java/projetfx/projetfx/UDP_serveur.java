package projetfx.projetfx;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;

public class UDP_serveur {
	private int port = 7907;
	private DatagramSocket dgramSocket;
	//private boolean running;
	private byte[] buffer = new byte[256];
	public static  int num = 0;
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
			//try {
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
				
				//crée le datagram réponse
				/*String response = "je suis le serveur et je parle";
				DatagramPacket outPacket = new DatagramPacket(response.getBytes(),response.length(),clientAddress,clientPort);
				/*
				//System.out.println("le message réponse est : "+response);
				/*
				if (message.equals("fin")) {
					this.running = false;
				}*/
				//envoie le datagram réponse
				/*dgramSocket.send(outPacket);
				dgramSocket.send(inPacket);
				*/
				//System.out.println("Envoi du datagram réponse");
			//} catch (Exception e) {
				//e.printStackTrace();
			//}
		}
		//fermeture datagramsocket
		//dgramSocket.close();
	}
	
	public void close() {
		//fermeture datagramsocket
		this.dgramSocket.close();
		System.out.println("Fermeture du datagramsocket");
		// fermeture du thread udp
		WindowModel.serveur_udp.close();
	    // après fermeture du thread
	    try {
			WindowModel.secondarycontroller.RefreshPage();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (WindowModel.user.pseudo!=null) {
			//WindowModel.secondarycontroller.StartChat();
	    } 
	    else{
	    	
	    }
	}
	
	public void receive() {
		Thread_UDP thread = new Thread_UDP (num);
    	(Thread_UDP.Tab_u).add(thread);
	    System.out.println("thread ajouté");
	    Thread_UDP.Tab_u.get(num).start();
	    System.out.println("thread lancé ");
	    Thread_UDP.Tab_u.get(num).interrupt();
	    num++;

	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		UDP_serveur udp = new UDP_serveur();
		//udp.run();
		udp.receive();
		//udp.close();
	}

}
