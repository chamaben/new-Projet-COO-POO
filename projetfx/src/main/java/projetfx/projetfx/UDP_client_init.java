package projetfx.projetfx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_client_init {

	private int port = 7907;
	private InetAddress host;
	private DatagramSocket dgramSocket;
	
	public UDP_client_init() throws SocketException {
		this.dgramSocket = new DatagramSocket();
	}
	
	//envoyer un message à mla connexiin avec pseudo et ad ip

	public void sendBroadcast(String message) throws IOException {
		String line = "10.1.255.255";
		host = InetAddress.getByName(line);
		this.dgramSocket.setBroadcast(true);
DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),host, port);
		
		
		//envoie le datagram
		this.dgramSocket.send(outPacket);
		System.out.println("Datagram envoyé");
		/*
		byte[] buf = new byte[256];
		//crée un objet datagrampacket pour les datagrams entrants
		outPacket = new DatagramPacket(buf,buf.length);
		System.out.println("Datagrampacket créé");
		
		//accepte un datagram entrant
		this.dgramSocket.receive(outPacket);
		//récupère la donnée dans le buffer
		System.out.println("Lecture dans le buffer");
		String response = new String(outPacket.getData(),0,outPacket.getLength());
		System.out.println("le message reçu est : "+response);
		
		return response;
		*/
	}
	
	public void close() {
		//fermeture datagramsocket
		this.dgramSocket.close();
		System.out.println("Fermeture du datagramsocket");
	}
	
	public static void main(String[] args) throws IOException {
		UDP_client_init client = new UDP_client_init();
		client.sendBroadcast("je suis le client");
		client.sendBroadcast("je parle au serveur");
		client.sendBroadcast("fin");
		client.close();
	}

}
