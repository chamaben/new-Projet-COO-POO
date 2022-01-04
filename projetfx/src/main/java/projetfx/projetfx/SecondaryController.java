package projetfx.projetfx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	private Text bonjourfield;
	@FXML VBox conversation;
	@FXML
	private Label contenu;
	@FXML
	private Label date;
	Message message1;
	
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
	             String pseudo_destinataire = activelist.getItems().get(index).toString();

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
				System.out.println("1");
				FXMLLoader loader = new FXMLLoader(); 
				System.out.println("2");
		        AnchorPane pane = loader.load(getClass().getResource(chemin).openStream());
		        System.out.println("3");
		        VBox vbox = (VBox) pane.getChildren().get(0);
		        System.out.println("4");
		        
		        AnchorPane pane1 = (AnchorPane) vbox.getChildren().get(0);
		        System.out.println("5");
		        Label contenu1 = (Label) pane1.getChildren().get(0);
		        System.out.println("6");
		        contenu1.setText(message.contenu);
		        System.out.println("7");

		        Label date1 =  (Label) pane1.getChildren().get(1);
		        System.out.println("8");
		        date1.setText(strDate);
		        System.out.println("9");
		        
		        conversation.getChildren().add(pane);
		        System.out.println("10");
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
	public void Send() throws IOException {
		// creation d'une instance message
		// envoyer au user2
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

