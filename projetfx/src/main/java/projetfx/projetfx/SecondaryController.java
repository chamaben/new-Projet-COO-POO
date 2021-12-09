package projetfx.projetfx;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {
	
	private TextField pseudo;
	private User user;
	
	public void connected(User user1) {
		user= user1;
	}
	
	@FXML
	private void handler() throws ClassNotFoundException, SQLException 
    {
    	String pseudo1= pseudo.getText();
    	if (!User.PseudoValide(user.login, pseudo1)) {
    		// message d'erreur: pseudo non valide
    		System.out.println("pseudo non valide 2");
    	} else {
    		// set pseudo pour le user
    		user.modifyPseudo(user.login, pseudo1);
    	}
        //outputText.setText(inputText.getText());*/
    }
	
	@FXML
	private void deconnexion() throws ClassNotFoundException, SQLException, IOException 
    {
    	user.etat=0;
    	DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "UPDATE user SET etat='0' WHERE login='"+user.login+"'";
		stmt.executeUpdate(query);
		System.out.println("user " + user.pseudo + " déconnecté");
		App.setRoot("primary");
    }
}

