package projetfx.projetfx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SecondaryController {
	
	@FXML
	private TextField pseudo;
	
	@FXML
	private ListView<String> activelist; 
	
	@FXML 
	private Text bonjourfield;
	
	@FXML
	private TextField message;
	Message message1;
	
	@FXML
    private void initialize() throws ClassNotFoundException, SQLException {
		BonjourMessage();
		// créer la liste de pseudos à afficher
		activelist.setItems(WindowModel.activeMembers);
    }
	

	@FXML
	private void handler() throws ClassNotFoundException, SQLException 
    {
    	String pseudo1= pseudo.getText();
    	if (!User.PseudoValide(WindowModel.user.login, pseudo1)) {
    		// message d'erreur: pseudo non valide
    		System.out.println("pseudo non valide 2");
    	} else {
    		// set pseudo pour le user
    		WindowModel.user.modifyPseudo(WindowModel.user, pseudo1);
    	}
        //outputText.setText(inputText.getText());*/
    }
	
	@FXML
	private void BonjourMessage() {
		bonjourfield.setText("Bonjour " + WindowModel.user.pseudo);
	}
	
	// actualise la liste des users actifs
	@FXML
	private void RefreshPage() throws ClassNotFoundException, SQLException {
		WindowModel.activeMembers.clear();
		WindowModel.activeMembers = WindowModel.ActiveUsers();
		// supprimer tous les éléments de la liste
		activelist.refresh();
		// créer la liste de pseudos à afficher
		activelist.setItems(WindowModel.activeMembers);
	}
	
	
	// Start chat
	// a voir: est-ce qu'on envoie une demande de connexion TCP à cette étape
	@FXML
	private void StartChat() throws IOException, ClassNotFoundException, SQLException {
		// étabissmenent connexion TCP
		 if (activelist.getSelectionModel().getSelectedIndices().size() > 0){
	             int index = activelist.getSelectionModel().getSelectedIndices().get(0);
	             String pseudo_destinataire = activelist.getItems().get(index).toString();

	            System.out.println(pseudo_destinataire);
	            
	            getHistory(pseudo_destinataire);
	            //dateLabel.setText(date);
	            
	            //messageList.getChildren().add(pane);
	        } 
		
		// pour l'utilisateur qui reçoit la demande de connexion,  le thread affiche la nouvelle interface
	}
	
	// affiche l'historique si on clique sur une personne
	@FXML
	private void getHistory(String pseudo_destinataire) throws SQLException, ClassNotFoundException {
		// a faire : récupérer les messages dans la base de donnée
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM message WHERE (emetteur='"+pseudo_destinataire+"' AND recepteur='"+WindowModel.user.pseudo+"') OR (emetteur='"+WindowModel.user.pseudo+"' AND recepteur='"+pseudo_destinataire+"')");
		// '"+user1.login+"'
		if (!rs.next()) {
			// afficher que la conversation est vide
		}
		else {
			while (rs.next()) {
			message1= new Message(rs.getString(1), rs.getString(2), rs.getString(4), rs.getDate(3));
			message1.DisplayMessage();
			}
		}
		DbConnect.FinConnexion();
	}
	
	
	@FXML
	public void Send(Message message) {
		// creation d'une instance message
		// envoyer au user2
		// afficher message sur l'écran
		message.DisplayMessage();
	}
	
	// affiche le message reçu à l'écran
	@FXML
	public void recieve_message(Message message) {
		// affiche le message reçu à l'écran
	}
	
	// 
	@FXML
	public void EndChat() {
		// terminer la connexion TCP
	}
	
	// déconnexion
	@FXML
	private void deconnexion() throws ClassNotFoundException, SQLException, IOException 
    {
		WindowModel.user.etat=0;
		DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "UPDATE user SET etat='0' WHERE login='"+WindowModel.user.login+"'";
		stmt.executeUpdate(query);
		DbConnect.FinConnexion();
		// supprime le user de la liste active members
		boolean rem = WindowModel.activeMembers.remove(WindowModel.user);
			
    }
	
	
}

