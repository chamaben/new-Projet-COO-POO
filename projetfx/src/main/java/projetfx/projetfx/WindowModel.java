package projetfx.projetfx;

import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WindowModel {
	
	
	public static User user;
	public static ObservableList<String> activeMembers = FXCollections.observableArrayList();
	static SecondaryController secondarycontroller;
	
	
	public static TCP_serveur serveur;
	public static UDP_serveur serveur_udp;
	
	public WindowModel(String login1, String password1, String pseudo1) throws ClassNotFoundException, SQLException, SocketException {
		user = new User(login1, password1, pseudo1);
		activeMembers = ActiveUsers ();
	}
	

	
	public static ObservableList<String> ActiveUsers () throws SQLException, ClassNotFoundException{
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT pseudo FROM user WHERE etat='1'");
		while (rs.next()) {
			if (!(rs.getString(1).equals(WindowModel.user.pseudo))) {
				activeMembers.add(rs.getString(1));
			}
		}
		DbConnect.FinConnexion();
		return activeMembers;
	}
	
	
	
}
