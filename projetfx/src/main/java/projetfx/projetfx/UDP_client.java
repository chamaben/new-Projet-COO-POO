package projetfx.projetfx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;


public class UDP_client {
	private static Message message;
	private static int port = 7905;
	private static InetAddress host;

	public static void main(String[] args) throws IOException {
		message = new Message("ordi1","ordi2","j'envoie en UDP",new Date());
		String line = "10.1.5.255";
		host = InetAddress.getByName(line);
		
		//crée un objet datagramsocket
		DatagramSocket dgramSocket = new DatagramSocket();
		System.out.println("Objet datagramsocket créé");		
		
		//crée le datagramme sortant
		DatagramPacket outPacket = new DatagramPacket(message.contenu.getBytes(),message.contenu.length(),host, port);
		System.out.println("le message envoyé est : "+message.contenu);
		
		//envoie le datagram
		dgramSocket.send(outPacket);
		System.out.println("Datagram envoyé");
		
		//crée un buffer pour les datagrams entrants
		System.out.println("Création du buffer");
		byte[] buffer = new byte[256];
		System.out.println("Buffer créé");
		
		//crée un objet datagrampacket pour les datagrams entrants
		System.out.println("Création du datagrampacket");
		DatagramPacket inPacket = new DatagramPacket(buffer,buffer.length);
		System.out.println("Datagrampacket créé");
		
		//accepte un datagram entrant
		dgramSocket.receive(inPacket);
		//récupère la donnée dans le buffer
		System.out.println("Lecture dans le buffer");
		String response = new String(inPacket.getData(),0,inPacket.getLength());
		System.out.println("le message reçu est : "+response);
		
		//fermeture datagramsocket
		dgramSocket.close();
		System.out.println("Fermeture du datagramsocket");
	}

}
