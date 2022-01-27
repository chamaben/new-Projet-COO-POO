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
	private InetAddress host;
	private DatagramSocket dgramSocket;
	
	public UDP_client() throws SocketException {
		this.dgramSocket = new DatagramSocket();
	}

	public void sendBroadcast(String login, String etat, String ip) throws IOException {
		
		host = InetAddress.getByName(VarGlobal.adBroad);
		this.dgramSocket.setBroadcast(true);
		String message = login + "," + etat + "," + ip;
		DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(),host, VarGlobal.portBroad);
		
		
		//envoie le datagram
		this.dgramSocket.send(outPacket);
		
	}
	
	public void close() {
		//fermeture datagramsocket
		this.dgramSocket.close();
	}
	
	public static String GetIP() throws SocketException {
		String RetAddr = null;
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
        	if (netint.getName().equals(VarGlobal.Interface)) {
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
    
    public static void connexion(String login, String etat, String ip) throws IOException {
    	UDP_client client = new UDP_client();
    	client.sendBroadcast(login,etat,ip);
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
		connexion("chama", "1", "100.00.00.00");
	}
}
