package projetfx.projetfx;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {
	
	@FXML
	private TextField pseudo;

	private User user;
	
	
	public void connected(User user1) {
		user= new User (user1.login, user1.password, user1.pseudo);
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
		App.setRoot("primary");
    }
}

