package projetfx.projetfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

	@SuppressWarnings("exports")
	public static Connection connection;
	@SuppressWarnings("exports")
	public static Statement statement;
	
	public static void Connexion() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_002","tp_servlet_002","fi6Cho0e");
			if (connection == null) {
				System.out.println("Connexion fail");
			}
			System.out.println("Connexion ok à la bdd");
			statement = connection.createStatement();
			System.out.println("statement bdd créé");
		} catch (SQLException e) {
			System.out.println("Erreur en se connectant à la database "+e);
		} catch (ClassNotFoundException c) {
			System.out.println("Erreur en se connectant à la database: classe non trouvée "+c);
		}
	}
	
	public static void FinConnexion() throws SQLException {
		connection.close();
	}



}
