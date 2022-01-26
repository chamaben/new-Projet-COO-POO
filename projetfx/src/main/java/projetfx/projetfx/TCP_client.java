package projetfx.projetfx;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

//A faire: généraliser l'ad ip avec bdd
//s'assurer que tcp fonctionne dans les 2 sens

public class TCP_client {

	static BufferedWriter os = null;
	static BufferedReader is = null;
	String[] words = null;

	//le destinataire reçoit la requête TCP, il l'accepte et lance un thread puis revient sur accept (boucle infinie)
	//initialiser la connexion TCP
	/*
	public static void receive() {

        try {
        	//récupérer le port sur lequel on va écouter 
        	System.out.println("receive client");
    		Socket socketOfClient = new Socket("10.1.5.82",port);
    		System.out.println("sock créé");

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            String responseLine = is.readLine();
            System.out.println(responseLine);

            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
	}
	 */
	public static void send(String message, String date, String pseudo) throws ClassNotFoundException, SQLException {

		try {
			String dest = VarGlobal.current_active_user;
			if (dest==null)
				dest="aa";
			System.out.println(dest);
			String line;
			DbConnect.Connexion();
			System.out.println(dest);
			ResultSet rs = DbConnect.statement.executeQuery("SELECT adIP FROM tp_servlet_002.user WHERE login='"+dest+"'");
			System.out.println(rs);
			if (rs.next()) {
				line = rs.getString(1);
				System.out.println("line="+line+"\n"); 

				DbConnect.FinConnexion();

				System.out.println("avant création socket");
				System.out.println(line);
				//Socket socketOfClient = new Socket("host",Integer.parseInt(words[2]));
				Socket socketOfClient = new Socket(InetAddress.getByName(line),VarGlobal.portTCP);
				System.out.println("sock créé");
				// Create output stream at the client (to send data to the server)
				//os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

				PrintStream output = new PrintStream(socketOfClient.getOutputStream());
				System.out.println("échange de données mit en place ");

				output.println(message+","+date+","+pseudo);
				System.out.println("envoi et réception de donnée ");

				output.close();
				socketOfClient.close(); }
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}

	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, SQLException {

		send("hello","2022-01-12","chama");
		//receive();

	}
}
