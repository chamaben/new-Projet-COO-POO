package projetfx.projetfx;

import java.io.*;
import java.net.ServerSocket;



public class TCP_serveur
{
	
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	public static void main(String[] args) throws IOException {

		//création du socket
		ServerSocket servSocket = new ServerSocket(7878);
	
	}
	
		
}