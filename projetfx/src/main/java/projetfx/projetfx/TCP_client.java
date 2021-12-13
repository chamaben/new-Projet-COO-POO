package projetfx.projetfx;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_client {
	
	//le destinataire reçoit la requête TCP, il l'accepte et lance un thread puis revient sur accept (boucle infinie)
	//initialiser la connexion TCP
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket link = new Socket(LocalIP.address,7878);
		PrintWriter out = new PrintWriter(link.getOutputStream(),true);
		link.close();
	}
}
