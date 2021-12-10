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
	
	
	public User user;
	public static ObservableList<User> activeMembers = FXCollections.observableArrayList();
	public static ArrayList<User> userList;
	
	public WindowModel(String login1, String password1, String pseudo1) {
		user = new User(login1, password1, pseudo1);
		activeMembers.add(user);
		userList.add(user);
	}
	
	public void deconnexion (User user1) throws ClassNotFoundException, SQLException {
		user1.etat=0;
		DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "UPDATE user SET etat='1' WHERE login='"+user.login+"'";
		stmt.executeUpdate(query);
		DbConnect.FinConnexion();
		// supprime le user de la liste active members
		boolean rem = activeMembers.remove(user1);
		
	}
	
	public static ArrayList<User> ActiveUsers () throws SQLException, ClassNotFoundException{
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM user WHERE etat='1'");
		ArrayList<User> liste = new ArrayList<User>(User.id);
		if (rs.next()) {
			liste.add((User) rs);
		}else {
				return liste;
		}
		DbConnect.FinConnexion();
		return liste;
	}
}
