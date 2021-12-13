package projetfx.projetfx;
import java.net.*;


public class LocalIP
{
	
	//classe pour lier une adresse IP à une machine
	public static void localip() {
		
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