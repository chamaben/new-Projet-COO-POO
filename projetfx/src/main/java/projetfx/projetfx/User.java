package projetfx.projetfx;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class User {
	
	public String login;
	public String password;
	public String pseudo;
	public int etat;
	public static int id=0;
	public InetAddress adIP;
	
	User(String login1,String password1,String pseudo1,InetAddress adIP){
		this.login = login1;
		this.password = password1;
		this.pseudo = pseudo1;
		this.etat = 1;
		this.adIP = adIP;
	}
	
	public void setPseudo(String pseudo){
		this.pseudo = pseudo;
	}
	
	public String getPseudo(){
		return this.pseudo;
	}
	
	public int getEtat(){
		return this.etat;
	}
	
	public static boolean PseudoValide(String login, String pseudo) throws SQLException, ClassNotFoundException{
		//doit regarder dans la BDD tous les pseudos
		// Si le pseudo existe renvoie faux sinon renvoie vrai
		boolean found = true;
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM user");
		while (rs.next()) {
			if (pseudo.equals(rs.getString(4)) && !login.equals(rs.getString(2))){
			found= false;
			}
			
		}
		DbConnect.FinConnexion();
		return found;
	}
	
	public void modifyPseudo(User user1, String pseudo1) throws ClassNotFoundException, SQLException{
		DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "UPDATE user SET pseudo='"+pseudo1+"' WHERE login='"+user1.login+"'";
		stmt.executeUpdate(query);
		DbConnect.FinConnexion();
		user1.pseudo=pseudo1;
	}
	
	
	public static boolean UserExist(String user,String p) throws SQLException, ClassNotFoundException {
		boolean found = false;
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM user");
		while (rs.next()) {
			if (user.equals(rs.getString(2))){
			found= true;
			}
		}
		DbConnect.FinConnexion();
		return found;
	}
	
	public static void CreateUser(String login, String password, String pseudo, int etat) throws SQLException, ClassNotFoundException {
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM user");
		id++;
		int c = DbConnect.statement.executeUpdate("INSERT INTO `tp_servlet_002`.`user` (`iduser`, `login`, `password`, `pseudo`, `etat`) VALUES ('"+Integer.toString(id)+"', '"+login+"','"+password+"', '"+pseudo+"', '"+Integer.toString(etat)+"')");
		System.out.println("user ajouté");
	}
		
		
	
	
	
	public static boolean VerifPassword(String user,String pswd) throws SQLException, ClassNotFoundException {
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM user WHERE login='"+user+"'");
		if (rs.next()) {
			if (user.equals(rs.getString(2))) {
			}else {
				return false;
			}
			if (pswd.equals(rs.getString(3))) {
				return true;
			}else {
				return false;
			}
		}
		DbConnect.FinConnexion();
		return false;
	}
	

		
	
}

