package projetfx.projetfx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WindowModel {
	
	
	public static User user;
	//public static ObservableList<User> activeMembers = FXCollections.observableArrayList();
	public static ArrayList<User> activeMembers;
	public static ArrayList<User> userList;
	
	public WindowModel(String login1, String password1, String pseudo1) throws ClassNotFoundException, SQLException {
		user = new User(login1, password1, pseudo1);
		activeMembers = ActiveUsers ();
		userList = UserList ();
	}
	
	public static void deconnexion () throws ClassNotFoundException, SQLException {
		user.etat=0;
		DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "UPDATE user SET etat='0' WHERE login='"+user.login+"'";
		stmt.executeUpdate(query);
		DbConnect.FinConnexion();
		// supprime le user de la liste active members
		boolean rem = activeMembers.remove(user);
		
	}
	
	public static ArrayList<User> UserList () throws SQLException, ClassNotFoundException{
		User user_ajoute;
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM user");
		ArrayList<User> liste = new ArrayList<User>(User.id);
		if (rs.next()) {
			user_ajoute= new User(rs.getString(2),rs.getString(3),rs.getString(4));
			liste.add(user_ajoute);
		}else {
				return liste;
		}
		DbConnect.FinConnexion();
		return liste;
	}
	
	public static ArrayList<User> ActiveUsers () throws SQLException, ClassNotFoundException{
		User user_ajoute;
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM user WHERE etat='1'");
		ArrayList<User> liste = new ArrayList<User>(User.id);
		if (rs.next()) {
			user_ajoute= new User(rs.getString(2),rs.getString(3),rs.getString(4));
			liste.add(user_ajoute);
		}else {
				return liste;
		}
		DbConnect.FinConnexion();
		return liste;
	}
}
