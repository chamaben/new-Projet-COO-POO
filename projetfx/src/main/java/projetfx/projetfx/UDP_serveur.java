package projetfx.projetfx;

import java.io.IOException;
import java.net.*;

public class UDP_serveur {
	static String response;
	static int port = 7896;

	public static void main(String[] args) throws IOException {
		//crée un object datagramsocket
		System.out.println("Création du datagramsocket");
		DatagramSocket dgramSocket = new DatagramSocket(port);
		System.out.println("Datagramsocket créé");
		
		//crée un buffer pour les datagrams entrants
		System.out.println("Création du buffer");
		byte[] buffer = new byte[256];
		System.out.println("Buffer créé");
		
		//crée un objet datagrampacket pour les datagrams entrants
		System.out.println("Création de l'objet datagram");
		DatagramPacket inPacket = new DatagramPacket(buffer,buffer.length);
		System.out.println("Objet datagram créé");
		
		//accepte un datagram entrant
		dgramSocket.receive(inPacket);
		System.out.println("Datagram entrant accepté");
		
		//accepte les infos du paquet
		InetAddress clientAddress = inPacket.getAddress();
		int clientPort = inPacket.getPort();
		System.out.println("Infos du paquet acceptées");
		
		//récupère la donnée dans le buffer
		String message = new String(inPacket.getData(),0,inPacket.getLength());
		System.out.println("j'ai reçu le message (dans le buffer) : "+message);
		
		//crée le datagram réponse
		DatagramPacket outPacket = new DatagramPacket(response.getBytes(),response.length(),clientAddress,clientPort);
		System.out.println("le message réponse est : "+response);
		
		//envoie le datagram réponse
		dgramSocket.send(outPacket);
		System.out.println("Envoi du datagram réponse");
		
		//fermeture datagramsocket
		dgramSocket.close();
		System.out.println("Fermeture du datagramsocket");
	}

}
