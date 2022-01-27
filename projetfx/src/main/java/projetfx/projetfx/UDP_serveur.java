package projetfx.projetfx;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;

public class UDP_serveur {
	static DatagramSocket dgramSocket;
	private static boolean running = true;
	private byte[] buffer = new byte[256];
	public static  int num_udp = 0;
	static int etat1= 0;
	
	public UDP_serveur() throws SocketException {
		dgramSocket = new DatagramSocket(VarGlobal.portBroad);
	}
	
	@SuppressWarnings("unused")
	public void run() {
		
		while(running) {
			//crée un objet datagrampacket pour les datagrams entrants
			DatagramPacket inPacket = new DatagramPacket(this.buffer,this.buffer.length);
			
			//accepte un datagram entrant
				try {
					dgramSocket.receive(inPacket);
				} catch (IOException e1) {
					if (VarGlobal.ClosingApp)
					System.out.println("Erreur sur dgramSocket");
					break;
				}
				String message = new String(inPacket.getData());
				String[] recup = message.split(",");
			    String login = recup[0];
			    String etat = recup[1];
			    String ip  = recup[2];
			    
			    etat1= Integer.parseInt(etat); 
			    
				
				//accepte les infos du paquet
				InetAddress clientAddress = inPacket.getAddress();
				int clientPort = inPacket.getPort();
				
				inPacket = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);
				
				//récupère la donnée dans le buffer
				String mess = new String(inPacket.getData(),0,inPacket.getLength());
				try {
					WindowModel.secondarycontroller.RefreshPage();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				if (SecondaryController.login_destinataire!=null) {
					try {
						WindowModel.secondarycontroller.StartChat();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    } 
				

		}
	}
	

	
	public void receive() {
		Thread_UDP thread = new Thread_UDP (num_udp);
    	(Thread_UDP.Tab_u).add(thread);
	    Thread_UDP.Tab_u.get(num_udp).start();

	}
	
	public static void end_thread_udp() {
		for (Thread_UDP Tudp : Thread_UDP.Tab_u) {
			if (!Tudp.isInterrupted())
				Tudp.interrupt();
		}
		running=false;
		dgramSocket.close();
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		UDP_serveur udp = new UDP_serveur();
		//udp.run();
		udp.receive();
		//udp.close();
	}

}
