package projetfx.projetfx;

import java.io.IOException;
//import java.sql.SQLException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController{
	
	
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
    	
    	windowmodel = new WindowModel(login1, password1, pseudo1);
		
		// windowmodel.user= new User(login1, password1, pseudo1);
		// SecondaryController secondaryController = new SecondaryController();
    	
    	
    	if (!User.PseudoValide(login1, pseudo1)) {
    		// message d'erreur: pseudo non valide
    		
    	} else {
    		System.out.println("pseudo valide 1");
    		if (User.UserExist(login1, password1)) {
    			if (User.VerifPassword(login1,password1)) {
    				// connexion: passage à la page suivante
    				WindowModel.user.modifyPseudo(WindowModel.user, pseudo1);
    				WindowModel.user.setEtat(1);
    				App.stage.setWidth(653);
    				App.stage.setHeight(610);
    				App.setRoot("secondary");
    			}
    			else {
    				// message d'erreur: mot de passe erroné
    			}
    		} else {
    			// création d'un nouveau user et passage à la page suivante
    			// la création de user est gérée par la fonction UserExist
				User.CreateUser(WindowModel.user.login, WindowModel.user.password, WindowModel.user.pseudo, WindowModel.user.etat);
				System.out.println("Nouveau user " + WindowModel.user.pseudo + " connecté");
				App.stage.setWidth(660);
				App.stage.setHeight(579);
				App.setRoot("secondary");
				WindowModel.serveur.receive();
    		}
    	}
        //outputText.setText(inputText.getText());
    }



		
		

}

