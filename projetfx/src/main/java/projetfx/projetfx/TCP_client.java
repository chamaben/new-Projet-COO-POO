package projetfx.projetfx;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TCP_client {

	static BufferedWriter os = null;
	static BufferedReader is = null;
	String[] words = null;


	public static void send(String message, String date, String pseudo) throws ClassNotFoundException, SQLException {

		try {
			String dest = VarGlobal.current_active_user;
			String line;
			DbConnect.Connexion();
			ResultSet rs = DbConnect.statement.executeQuery("SELECT adIP FROM tp_servlet_002.user WHERE login='"+dest+"'");
			if (rs.next()) {
				line = rs.getString(1);

				DbConnect.FinConnexion();
				Socket socketOfClient = new Socket(InetAddress.getByName(line),VarGlobal.portTCP);

				PrintStream output = new PrintStream(socketOfClient.getOutputStream());

				output.println(message+","+date+","+pseudo);

				output.close();
				socketOfClient.close(); }
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}

	}


}
