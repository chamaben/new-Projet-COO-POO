package projetfx.projetfx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.net.Inet4Address;

//change pseudo

public class UDP_client {
	private int port = 7908;
	private InetAddress host;
	private DatagramSocket dgramSocket;
	private static String Interface = "eth4";
	
	public UDP_client() throws SocketException {
		this.dgramSocket = new DatagramSocket();
	}

	public String sendBroadcast(String message) throws IOException {
		String line = "10.1.255.255";
		host = InetAddress.getByName(line);
		this.dgramSocket.setBroadcast(true);
		
		DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),host, port);
		
		
		//envoie le datagram
		this.dgramSocket.send(outPacket);
		//System.out.println("Datagram envoyPé");
		
		byte[] buf = new byte[256];
		//crée un objet datagrampacket pour les datagrams entrants
		outPacket = new DatagramPacket(buf,buf.length);
		//System.out.println("Datagrampacket créé");
		
		//accepte un datagram entrant
		this.dgramSocket.receive(outPacket);
		//récupère la donnée dans le buffer
		//System.out.println("Lecture dans le buffer");
		String response = new String(outPacket.getData(),0,outPacket.getLength());
		System.out.println("le message reçu est : "+response);
		
		return response;
		
	}
	
	public void close() {
		//fermeture datagramsocket
		this.dgramSocket.close();
		//System.out.println("Fermeture du datagramsocket");
	}
	
	public static String GetIP() throws SocketException {
		String RetAddr = null;
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
        	if (netint.getName().equals(Interface)) {
        		System.out.println(netint);
        		RetAddr=GrabIPbyName(netint);
        		
        	}
        return RetAddr;
            
    }

    public static String GrabIPbyName (NetworkInterface netint) throws SocketException {
    	String RetAddr = null;
        
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	if (inetAddress instanceof Inet4Address) {
        		RetAddr = inetAddress.toString().substring(1);
        		break;
        	}
        }
        return RetAddr;
     }
    
    public static void connexion(String pseudo) throws IOException {
    	UDP_client client = new UDP_client();
		client.sendBroadcast(pseudo+" vient de rejoindre le réseau ! ");
		client.close();
    }
    /*
    public static void changePseudo(String pseudo) throws IOException {
    	UDP_client client = new UDP_client();
		client.sendBroadcast(pseudo+" a changé de pseudo. Il s'appelle maintenant "+nv_pseudo);
		client.close();
    }
	*/
	public static void main(String args[]) throws IOException, InterruptedException {
		connexion("chouchou");
	}
}
