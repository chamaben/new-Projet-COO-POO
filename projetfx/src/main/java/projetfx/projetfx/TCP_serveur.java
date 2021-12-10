package projetfx.projetfx;
import java.net.*;
import java.io.*;
import javax.swing.*;


public class TCP_serveur
{
	// le serveur est en état accept, quand il reçoit la requête, il accepte la connexion, lance un thread qui va envoyer le messages, et rebouble sur accept
	public static void main(String[] args) throws IOException { 
	
	//Find an IP address:class ip finder à faire à part 
	String host;
	host = JOptionPane.showInputDialog("Please input the server's name");
	try
		{InetAddress address = InetAddress.getByName(host); JOptionPane.showMessageDialog(null,"IP address: " + address.toString());
		}
	catch (UnknownHostException e) 
		{JOptionPane.showMessageDialog(null,"Could not find " + host); 
		}
	
	
	//récupérer l'IP de la machine locale
	try
		{ InetAddress address = InetAddress.getLocalHost();
	System.out.println (address); 
		}
	catch (UnknownHostException e)
		{
		System.out.println ("Could not find local address!");
		}
	
	}
	
		
}