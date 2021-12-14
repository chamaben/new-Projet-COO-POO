package projetfx.projetfx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;


public class UDP_client {
	private static Message message;
	private static SocketAddress port;
	private static int host;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//crée un objet datagramsocket
		DatagramSocket dgramSocket = new DatagramSocket();
		
		//crée le datagramme sortant
		DatagramPacket outPacket = new DatagramPacket(message.getBytes(),message.length(),host, port);
		//envoie le datagram
		dgramSocket.send(outPacket);
		//crée un buffer pour les datagrams entrants
		byte[] buffer = new byte[256];
		//crée un objet datagrampacket pour les datagrams entrants
		DatagramPacket inPacket = new DatagramPacket(buffer,buffer.length);
		//accepte un datagram entrant
		dgramSocket.receive(inPacket);
		//récupère la donnée dans le buffer
		String response = new String(inPacket.getData(),0,inPacket.getLength());
		//fermeture datagramsocket
		dgramSocket.close();
	}

}
