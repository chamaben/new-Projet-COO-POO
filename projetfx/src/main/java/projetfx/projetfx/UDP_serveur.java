package projetfx.projetfx;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;

public class UDP_serveur {
	private int port = 7908;
	private static DatagramSocket dgramSocket;
	//private boolean running;
	private static byte[] buffer = new byte[256];
	
	public UDP_serveur() throws SocketException {
		this.dgramSocket = new DatagramSocket(this.port);
	}
	
	public static void run() throws IOException, ClassNotFoundException, SQLException {
		
		//this.running = true;
		
		for (int i=0;i<1;i++) {
			
			//crée un objet datagrampacket pour les datagrams entrants
			DatagramPacket inPacket = new DatagramPacket(buffer,buffer.length);
			//System.out.println("Objet datagram créé");
			
			//accepte un datagram entrant
			try {
				dgramSocket.receive(inPacket);
				String message = new String(inPacket.getData());
				String[] recup = message.split(",");
			    String login = recup[0];
			    System.out.println(login+"\n");
			    String etat = recup[1];
			    System.out.println(etat+"\n");
			    String ip  = recup[2];
			    System.out.println(ip+"\n");
			    
			    int etat1= Integer.parseInt(etat); 
			    if (etat1==1) {
			    	//on récupère l'adresse ip
			    	// 
			    	WindowModel.secondarycontroller.RefreshPage();
			    } 
			    else if (etat1==0) {
			    	
			    }
			    
			 // System.out.println(new String(inPacket.getData()));
				//System.out.println("Datagram entrant accepté");
				
				//accepte les infos du paquet
				/*InetAddress clientAddress = inPacket.getAddress();
				int clientPort = inPacket.getPort();
				//System.out.println("Infos du paquet acceptées");
				
				inPacket = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);
				
				//récupère la donnée dans le buffer
				//String message = new String(inPacket.getData(),0,inPacket.getLength());
				
				//System.out.println("j'ai reçu le message (dans le buffer) : "+message);
				
				//crée le datagram réponse
				String response = "je suis le serveur et je parle";
				DatagramPacket outPacket = new DatagramPacket(response.getBytes(),response.length(),clientAddress,clientPort);
				//System.out.println("le message réponse est : "+response);
				
				if (message.equals("fin")) {
					this.running = false;
				}
				//envoie le datagram réponse
				dgramSocket.send(outPacket);
				dgramSocket.send(inPacket);*/
				//System.out.println("Envoi du datagram réponse");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
			
		}
		//fermeture datagramsocket
		dgramSocket.close();
		//System.out.println("Fermeture du datagramsocket");
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		UDP_serveur udp = new UDP_serveur();
		udp.run();
	}

}
