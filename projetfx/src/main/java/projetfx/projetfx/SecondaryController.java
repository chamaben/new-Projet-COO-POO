package projetfx.projetfx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SecondaryController {
	
	@FXML
	private TextField pseudo;
	
	@FXML
	private ListView<String> activelist; 
	
	@FXML
	private TextField message_ecrit;
	
	@FXML 
	private Text bonjourfield;
	@FXML VBox conversation;
	@FXML
	private Label contenu;
	@FXML
	private Label date;
	Message message1;
	String pseudo_destinataire= null;
	
	@FXML
    private void initialize() throws ClassNotFoundException, SQLException {
		BonjourMessage();
		// créer la liste de pseudos à afficher
		activelist.setItems(WindowModel.activeMembers);
    }
	
	
	// modifier le pseudo
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
		
		 conversation.getChildren().clear();
		 if (activelist.getSelectionModel().getSelectedIndices().size() > 0){
	             int index = activelist.getSelectionModel().getSelectedIndices().get(0);
	             pseudo_destinataire = activelist.getItems().get(index).toString();

	            System.out.println(pseudo_destinataire);
	            
	            getHistory(pseudo_destinataire);
	            //dateLabel.setText(date);
	            
	            //messageList.getChildren().add(pane);
	        } 
		
		// pour l'utilisateur qui reçoit la demande de connexion,  le thread affiche la nouvelle interface
	}
	
	// affiche l'historique si on clique sur une personne
	private void getHistory(String pseudo_destinataire) throws SQLException, ClassNotFoundException, IOException {
		// a faire : récupérer les messages dans la base de donnée
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM message WHERE (emetteur='"+pseudo_destinataire+"' AND recepteur='"+WindowModel.user.pseudo+"') OR (emetteur='"+WindowModel.user.pseudo+"' AND recepteur='"+pseudo_destinataire+"') ORDER BY date");
		// '"+user1.login+"'
		// if (!rs.next()) {
			//System.out.println("not: ");
			// afficher que la conversation est vide
		//}
		//else {
			System.out.println("yes ");
			while (rs.next()) {
			message1= new Message(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
			System.out.println("contenu: " + rs.getString(3));
			System.out.println("date: " + rs.getString(4));
			DisplayMessage(message1);
			}
		//}
		DbConnect.FinConnexion();
	}
	
	// affiche le message 
		public void DisplayMessage(Message message) throws IOException {
			// afficher le message à droite si le message est envoyé et à gauche s'il est reçu
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
			String strDate = dateFormat.format(message.date);
			String chemin = null;
			System.out.println("date:" + message.date);
			System.out.println("date stngdate:" + strDate);
			
			if (message.emetteur.equals(WindowModel.user.pseudo)) {
				chemin = "sent_message.fxml";
				FXMLLoader loader = new FXMLLoader();  
		        AnchorPane pane = loader.load(getClass().getResource(chemin).openStream());
		        VBox vbox = (VBox) pane.getChildren().get(0);
		        
		        AnchorPane pane1 = (AnchorPane) vbox.getChildren().get(0);
		        Label contenu1 = (Label) pane1.getChildren().get(0);
		        contenu1.setText(message.contenu);

		        Label date1 =  (Label) pane1.getChildren().get(1);
		        date1.setText(strDate);
		        
		        conversation.getChildren().add(pane);
			}
			else if (message.recepteur.equals(WindowModel.user.pseudo)) {
				chemin = "received_message.fxml";
				FXMLLoader loader = new FXMLLoader();  
		        AnchorPane pane = loader.load(getClass().getResource(chemin).openStream());
		        VBox vbox = (VBox) pane.getChildren().get(0);
		        AnchorPane pane1 = (AnchorPane) vbox.getChildren().get(0);
		        Label contenu1 = (Label) pane1.getChildren().get(0);
		        contenu1.setText(message.contenu);

		        Label date1 =  (Label) pane1.getChildren().get(1);
		        date1.setText(strDate);
		        
		        conversation.getChildren().add(pane);
			}
			
		}
	
	
	
	
	@FXML
	public void Send() throws IOException, SQLException, ClassNotFoundException {
		// creation d'une instance message
		String contenu1= message_ecrit.getText(); 
		Date now = new Date();
		Message message = new Message(WindowModel.user.pseudo, pseudo_destinataire, contenu1, now);
		// mettre le message dans la bdd
		DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "INSERT INTO message VALUES ('"+WindowModel.user.pseudo+"', '"+pseudo_destinataire+"', '"+contenu1+"', '"+now+"')";
		stmt.executeUpdate(query);
		DbConnect.FinConnexion();
		// envoyer au user2
		// faire la difference entre tcp serveur et tcp client avec if
		TCP_client.send(contenu1);
		// afficher message sur l'écran
		DisplayMessage(message1);
	}
	
	// affiche le message reçu à l'écran
	@FXML
	public void recieve_message() {
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
		@SuppressWarnings({ "unused", "unlikely-arg-type" })
		boolean rem = WindowModel.activeMembers.remove(WindowModel.user);
			
    }
	
	
}

