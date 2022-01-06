package projetfx.projetfx;

import java.io.IOException;
import java.net.*;

public class UDP_serveur {
	private int port = 7908;
	private DatagramSocket dgramSocket;
	//private boolean running;
	private byte[] buffer = new byte[256];
	
	public UDP_serveur() throws SocketException {
		this.dgramSocket = new DatagramSocket(this.port);
	}

	public void run() throws IOException {
		
		//this.running = true;
		
		for (int i=0;i<2;i++) {
			
			//crée un objet datagrampacket pour les datagrams entrants
			DatagramPacket inPacket = new DatagramPacket(this.buffer,this.buffer.length);
			System.out.println("Objet datagram créé");
			
			//accepte un datagram entrant
			this.dgramSocket.receive(inPacket);
			System.out.println("Donnée reçue : "+ new String(inPacket.getData()));
			System.out.println("Datagram entrant accepté");
			
			//accepte les infos du paquet
			InetAddress clientAddress = inPacket.getAddress();
			int clientPort = inPacket.getPort();
			System.out.println("Infos du paquet acceptées");
			
			inPacket = new DatagramPacket(this.buffer,this.buffer.length,clientAddress,clientPort);
			
			//récupère la donnée dans le buffer
			String message = new String(inPacket.getData(),0,inPacket.getLength());
			System.out.println("j'ai reçu le message (dans le buffer) : "+message);
			
			//crée le datagram réponse
			String response = "je suis le serveur et je parle";
			DatagramPacket outPacket = new DatagramPacket(response.getBytes(),response.length(),clientAddress,clientPort);
			System.out.println("le message réponse est : "+response);
			
			/*if (message.equals("fin")) {
				this.running = false;
			}*/
			//envoie le datagram réponse
			dgramSocket.send(outPacket);
			this.dgramSocket.send(inPacket);
			System.out.println("Envoi du datagram réponse");
		}
		//fermeture datagramsocket
		this.dgramSocket.close();
		System.out.println("Fermeture du datagramsocket");
	}
	
	public static void main(String args[]) throws IOException {
		UDP_serveur udp = new UDP_serveur();
		udp.run();
	}

}
