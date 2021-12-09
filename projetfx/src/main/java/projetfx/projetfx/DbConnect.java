package projetfx.projetfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

	public static Connection connection;
	public static Statement statement;
	
	public static void Connexion() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_002","tp_servlet_002","fi6Cho0e");
			if (connection == null) {
				System.out.println("Connexion fail");
			}
			System.out.println("Connexion ok");
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Erreur en se connectant à la database "+e);
		} catch (ClassNotFoundException c) {
			System.out.println("Erreur en se connectant à la database: classe non trouvée "+c);
		}
	}
	
	public static void FinConnexion() throws SQLException {
		connection.close();
	}

	/*public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			User.User("carina","caca","caca");
			
			User.UserExist("idio", "null");
			if (User.VerifPassword("carina","hello")) {
				System.out.println("Le password est bon");
			}else {
				System.out.println("Mauvais mot de passe");
			}
			Connexion();
			System.out.println("je rame pas");
			FinConnexion();
		} catch (SQLException e) {
			System.out.println("Erreur en se connectant à la database"+e);
		}
	}*/

}
