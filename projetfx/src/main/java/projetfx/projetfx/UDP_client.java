package projetfx.projetfx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDP_client {
	private int port = 7906;
	private InetAddress host;
	private DatagramSocket dgramSocket;
	
	public UDP_client() throws SocketException {
		this.dgramSocket = new DatagramSocket();
	}

	public String sendBroadcast(String message) throws IOException {
		String line = "10.1.5.255";
		host = InetAddress.getByName(line);
		this.dgramSocket.setBroadcast(true);
		
		DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),host, port);
		
		
		//envoie le datagram
		this.dgramSocket.send(outPacket);
		System.out.println("Datagram envoyé");
		
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
		
	}
	
	public void close() {
		//fermeture datagramsocket
		this.dgramSocket.close();
		System.out.println("Fermeture du datagramsocket");
	}

	public static void main(String args[]) throws IOException {
		UDP_client client = new UDP_client();
		String envoi = client.sendBroadcast("je suis le client");
		if (envoi.equals("je suis le client")) {
			System.out.println("envoi OK");
		} else {
			System.out.println("attention erreur");
		}
		envoi = client.sendBroadcast("je parle au serveur");
		if (envoi.equals("je suis le client")) {
			System.out.println("envoi N OK");
		} else {
			System.out.println("attention erreur");
		}
		client.close();
	}
}
