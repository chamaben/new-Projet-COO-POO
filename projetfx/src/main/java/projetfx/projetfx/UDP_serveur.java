package projetfx.projetfx;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class UDP_serveur {
	static Message response;
	static int port = 7905;

	public static void main(String[] args) throws IOException {
		response = new Message("ordi1","ordi2","je suis le serveur",new Date());
		
		
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
		System.out.println("Donnée reçue : "+ new String(inPacket.getData()));
		System.out.println("Datagram entrant accepté");
		
		//accepte les infos du paquet
		InetAddress clientAddress = inPacket.getAddress();
		int clientPort = inPacket.getPort();
		System.out.println("Infos du paquet acceptées");
		
		
		inPacket = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);
		
		//récupère la donnée dans le buffer
		String message = new String(inPacket.getData(),0,inPacket.getLength());
		System.out.println("j'ai reçu le message (dans le buffer) : "+message);
		
		//crée le datagram réponse
		//DatagramPacket outPacket = new DatagramPacket(response.contenu.getBytes(),response.contenu.length(),clientAddress,clientPort);
		//System.out.println("le message réponse est : "+response.contenu);
		
		//envoie le datagram réponse
		//dgramSocket.send(outPacket);
		dgramSocket.send(inPacket);
		System.out.println("Envoi du datagram réponse");
		
		//fermeture datagramsocket
		dgramSocket.close();
		System.out.println("Fermeture du datagramsocket");
	}

}
