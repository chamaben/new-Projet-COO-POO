package projetfx.projetfx;

import java.io.IOException;
import java.net.URL;
//import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable{
	
	
	@FXML
	private TextField login;
	@FXML
	private TextField password;
	@FXML
	private TextField pseudo;
	
	String pseudo1;
	String login1;
	String password1;
	
	
	WindowModel windowmodel;
	
	@FXML
	private void handler() throws IOException, ClassNotFoundException, SQLException 
    {
		
    	pseudo1= pseudo.getText();
    	login1= login.getText();
    	password1= password.getText();
		
		windowmodel.user= new User(login1, password1, pseudo1);
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
    				windowmodel.user.modifyPseudo(login1, pseudo1);
    				System.out.println("mot de passe ok");
    				App.setRoot("secondary");
    				secondaryController.connected(windowmodel.user);
    				System.out.println("User " + windowmodel.user.pseudo + " connecté");
    			}
    			else {
    				// message d'erreur: mot de passe erroné
    				System.out.println("mot de passe erroné");
    			}
    		} else {
    			System.out.println("Le user n'existe pas");
    			// création d'un nouveau user et passage à la page suivante
    			// la création de user est gérée par la fonction UserExist
				User.CreateUser(windowmodel.user.login, windowmodel.user.password, windowmodel.user.pseudo, windowmodel.user.etat);
				System.out.println("Nouveau user " + windowmodel.user.pseudo + " connecté");
				App.setRoot("secondary");
				secondaryController.connected(windowmodel.user);
    		}
    	}
        //outputText.setText(inputText.getText());
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// get model
		windowmodel = new WindowModel(login1, password1, pseudo1);
		
		// link model with view
		
		// link controller to view
		
		
		
	}

}


