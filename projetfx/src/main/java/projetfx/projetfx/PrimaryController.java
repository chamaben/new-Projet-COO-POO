package projetfx.projetfx;

import java.io.IOException;
//import java.sql.SQLException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {
	
	@FXML
	private TextField login;
	@FXML
	private TextField password;
	@FXML
	private TextField pseudo;
	protected User user;
	
	
	@FXML
	private void handler() throws IOException, ClassNotFoundException, SQLException 
    {
		
    	String pseudo1= pseudo.getText();
    	String login1= login.getText();
    	String password1= password.getText();
		
		user= new User(login1, password1, pseudo1);
		SecondaryController secondaryController = new SecondaryController();
    	
    	
    	if (!User.PseudoValide(login1, pseudo1)) {
    		// message d'erreur: pseudo non valide
    		System.out.println("pseudo non valide 1");
    		
    	} else {
    		System.out.println("pseudo valide 1");
    		if (User.UserExist(login1, password1)) {
    			System.out.println("Le user existe");
    			if (User.VerifPassword(login1,password1)) {
    				// connexion: passage à la page suivante
    				user.modifyPseudo(login1, pseudo1);
    				System.out.println("mot de passe ok");
    				App.setRoot("secondary");
    				secondaryController.connected(user);
    				System.out.println("User " + user.pseudo + " connecté");
    			}
    			else {
    				// message d'erreur: mot de passe erroné
    				System.out.println("mot de passe erroné");
    			}
    		} else {
    			System.out.println("Le user n'existe pas");
    			// création d'un nouveau user et passage à la page suivante
    			// la création de user est gérée par la fonction UserExist
				User.CreateUser(user.login, user.password, user.pseudo, user.etat);
				System.out.println("Nouveau user " + user.pseudo + " connecté");
				App.setRoot("secondary");
				secondaryController.connected(user);
    		}
    	}
        //outputText.setText(inputText.getText());
    }

}


