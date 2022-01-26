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
	
	public void run() {
		
		//this.running = true;
		
		while(running) {
			//crée un objet datagrampacket pour les datagrams entrants
			DatagramPacket inPacket = new DatagramPacket(this.buffer,this.buffer.length);
			System.out.println("Objet datagram créé");
			
			//accepte un datagram entrant
				try {
					dgramSocket.receive(inPacket);
				} catch (IOException e1) {
					if (VarGlobal.ClosingApp)
						System.out.println("tout va bien");
					System.out.println("Erreur sur dgramSocket");
					break;
				}
				String message = new String(inPacket.getData());
				String[] recup = message.split(",");
			    String login = recup[0];
			    //System.out.println(login+"\n");
			    String etat = recup[1];
			    //System.out.println(etat+"\n");
			    String ip  = recup[2];
			    //System.out.println(ip+"\n");
			    
			    etat1= Integer.parseInt(etat); 
			    
			    
			    //System.out.println(new String(inPacket.getData()));
				System.out.println("Datagram entrant accepté");
				
				//accepte les infos du paquet
				InetAddress clientAddress = inPacket.getAddress();
				int clientPort = inPacket.getPort();
				//System.out.println("Infos du paquet acceptées");
				
				inPacket = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);
				
				//récupère la donnée dans le buffer
				String mess = new String(inPacket.getData(),0,inPacket.getLength());
				System.out.println("j'ai reçu le message (dans le buffer) : "+mess);
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
	    System.out.println("thread ajouté");
	    Thread_UDP.Tab_u.get(num_udp).start();
	    System.out.println("thread lancé ");
	    //num_udp++;

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
