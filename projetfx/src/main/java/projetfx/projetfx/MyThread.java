package projetfx.projetfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MyThread extends Thread

{
	//attributs
	static int port = 7899;
	public static  int num = 0;
	static String line;
	public static ArrayList<MyThread> Tab = new ArrayList<MyThread>(); 
	Socket service; 


	//constructeur
	MyThread(int num, Socket serviceassocie) {
		super(Integer.toString(num));
		service = serviceassocie  ;
	}


	// créer un thread qui boucle sur le accept du receive de TCP serveur et qui dès qu'il reçoit un truc, crée un thread et continue après d'attendre


	@SuppressWarnings("unused")
	@Override
	public void run() {
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(service.getInputStream()));
			System.out.println("br fait");

			//Mise en place des échange de données en entrée et sortie
			PrintStream output = new PrintStream(service.getOutputStream());
			System.out.println("échange de données mis en place ");

			// Recevoir de la donnée

			//message+date+pseudo
			line = br.readLine();
			System.out.println(line);
			String[] recup = line.split(",");
			String message = recup[0];
			System.out.println(message);
			String sdate = recup[1];
			System.out.println(sdate);
			String login  = recup[2];
			System.out.println(login);
			Timestamp date= Timestamp.valueOf(sdate);
			System.out.println(date+"\n");
			System.out.println("donnée récupérée ");


			//Reconstitution du message pour affichage
			Message message2 = new Message(login,WindowModel.user.login, message, date);
			System.out.println(WindowModel.user.login);

			//Fermer la connection
			br.close();
			service.close();
			System.out.println("fermeture de connection ");

			// rajouter un if la fenêtre est ouverte / sinon notification
			if (login.equals(VarGlobal.current_active_user)) 
				WindowModel.secondarycontroller.DisplayMessage(message2);
			System.out.println("message reconstitué");
		} 
		catch (IOException e) {
			System.out.println("exception levée");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interrupt() {

		try {
			service.close();
		} catch (IOException e) {
			System.out.println("erreur fermeture socket");
		}
		super.interrupt();
	}

	public static void send(String message, String date, String login) {

		try {
			//création du socket
			ServerSocket s = new ServerSocket(port);
			System.out.println(s.getLocalPort());


			//Mise en place des échange de données en entrée et sortie
			Socket serviceSocket =  s.accept();

			PrintStream output = new PrintStream(serviceSocket.getOutputStream());
			System.out.println("échange de données mit en place ");

			// Envoyer et recevoir de la donnée
			output.println(message+","+date+","+login);
			System.out.println("envoi et réception de donnée ");

			//Fermer la connection
			serviceSocket.close();
			s.close();
			System.out.println("fermeture de connection ");

		} catch (IOException e) {
			System.out.println("exception levée");
			e.printStackTrace();
		}
	}

	//public static void main() {

	//remplissage des index de la liste avec des threads
	//Tab.get(num).start();
	// num ++;

	//}
}