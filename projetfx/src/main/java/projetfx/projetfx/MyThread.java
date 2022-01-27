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


	@SuppressWarnings("unused")
	@Override
	public void run() {
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(service.getInputStream()));

			//Mise en place des échange de données en entrée et sortie
			PrintStream output = new PrintStream(service.getOutputStream());

			// Recevoir de la donnée

			//message+date+pseudo
			line = br.readLine();
			String[] recup = line.split(",");
			String message = recup[0];
			String sdate = recup[1];
			String login  = recup[2];
			Timestamp date= Timestamp.valueOf(sdate);


			//Reconstitution du message pour affichage
			Message message2 = new Message(login,WindowModel.user.login, message, date);

			//Fermer la connection
			br.close();
			service.close();

			// rajouter un if la fenêtre est ouverte / sinon notification
			if (login.equals(VarGlobal.current_active_user)) 
				WindowModel.secondarycontroller.DisplayMessage(message2);
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


			//Mise en place des échange de données en entrée et sortie
			Socket serviceSocket =  s.accept();

			PrintStream output = new PrintStream(serviceSocket.getOutputStream());

			// Envoyer et recevoir de la donnée
			output.println(message+","+date+","+login);

			//Fermer la connection
			serviceSocket.close();
			s.close();

		} catch (IOException e) {
			System.out.println("exception levée");
			e.printStackTrace();
		}
	}
}