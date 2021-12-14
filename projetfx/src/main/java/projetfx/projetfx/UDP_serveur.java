package projetfx.projetfx;

import java.io.IOException;
import java.net.*;

public class UDP_serveur {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//crée un object datagramsocket
		DatagramSocket dgramSocket = new DatagramSocket(7892);
		//crée un buffer pour les datagrams entrants
		byte[] buffer = new byte[256];
		//crée un objet datagrampacket pour les datagrams entrants
		DatagramPacket inPacket = new DatagramPacket(buffer,buffer.length);
		//accepte un datagram entrant
		dgramSocket.receive(inPacket);
		//accepte les infos du paquet
		InetAddress clientAddress = inPacket.getAddress();
		int clientPort = inPacket.getPort();
		//récupère la donnée dans le buffer
		String response = new String(inPacket.getData(),0,inPacket.getLength());
		//crée le datagram réponse
		DatagramPacket outPacket = new DatagramPacket(response.getBytes(),response.length(),clientAddress,clientPort);
		//envoie le datagram réponse
		dgramSocket.send(outPacket);
		//fermeture datagramsocket
		dgramSocket.close();
	}

}
