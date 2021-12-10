package projetfx.projetfx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConversationController {
	Message message1;
	
	@FXML
	private TextField message;
	
	@FXML
	public void Send() {
		// creation d'une instance message
		// envoyer au user2
		// afficher message sur l'écran
	}
	
	@FXML
	public void recieve_message() {
		// affiche le message reçu à l'écran
	}
	
	@FXML
	public void EndChat() {
		// terminer la connexion TCP
	}
}
