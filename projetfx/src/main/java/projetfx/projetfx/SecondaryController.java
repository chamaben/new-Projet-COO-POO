package projetfx.projetfx;

import java.io.IOException;
import java.net.SocketException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
	private TextField historique;
	@FXML
	private ListView<String> activelist; 
	
	@FXML
	private TextField message_ecrit;
	
	@FXML 
	private Text bonjourfield;
	@FXML  VBox conversation; 
	@FXML
	private Label contenu;
	@FXML
	private Label date;
	Message message1;
	public static String pseudo_destinataire= null;
	static String login_destinataire=null;
	
	@FXML
    private void initialize() throws ClassNotFoundException, SQLException, IOException {
		BonjourMessage();
		// créer la liste de pseudos à afficher
		activelist.setItems(WindowModel.activeMembers);
		WindowModel.serveur.receive();
		System.out.println("serveur ouvert");
		WindowModel.user.adIP = UDP_client.GetIP();
		DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "UPDATE user SET adIP='"+WindowModel.user.adIP+"'WHERE login='"+WindowModel.user.login+"'";
		stmt.executeUpdate(query);
		DbConnect.FinConnexion();
		
		UDP_client.connexion(WindowModel.user.login, "1", WindowModel.user.adIP);
    }
	
	
	// modifier le pseudo
	@FXML
	private void modifypseudo() throws ClassNotFoundException, SQLException, IOException 
    {
    	String pseudo1= pseudo.getText();
    	if (!User.PseudoValide(WindowModel.user.login, pseudo1)) {
    		// message d'erreur: pseudo non valide
    	} else {
    		// set pseudo pour le user
    		WindowModel.user.modifyPseudo(WindowModel.user, pseudo1);
    		BonjourMessage();
    		if (login_destinataire!=null) {
    			StartChat();
    		}
    	}
        //outputText.setText(inputText.getText());*/
    }
	
	@FXML
	private void BonjourMessage() {
		bonjourfield.setText("Bonjour " + WindowModel.user.pseudo);
	}
	
	// actualise la liste des users actifs
	@FXML void RefreshPage() throws ClassNotFoundException, SQLException {
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
	             DbConnect.Connexion();
	             ResultSet rs = DbConnect.statement.executeQuery("SELECT login FROM user WHERE (pseudo='"+pseudo_destinataire+"')");
	             if (rs.next()) {
	            	 login_destinataire= rs.getString(1);
	 	            getHistory(login_destinataire);
	             }
	            
	            DbConnect.FinConnexion();
	            //dateLabel.setText(date);
	            
	            //messageList.getChildren().add(pane);
	        } 
		
		// pour l'utilisateur qui reçoit la demande de connexion,  le thread affiche la nouvelle interface
	}
	
	// affiche l'historique si on clique sur une personne
	private void getHistory(String pseudo_destinataire) throws SQLException, ClassNotFoundException, IOException {
		// a faire : récupérer les messages dans la base de donnée
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM message WHERE (emetteur='"+login_destinataire+"' AND recepteur='"+WindowModel.user.login+"') OR (emetteur='"+WindowModel.user.login+"' AND recepteur='"+login_destinataire+"') ORDER BY date");

			while (rs.next()) {
			message1= new Message(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
			DisplayMessage(message1);
			}
		DbConnect.FinConnexion();
	}
	
	// affiche le message 
		public void DisplayMessage(Message message) throws IOException, ClassNotFoundException, SQLException {
			String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(message.time);
			String chemin = null;
			DbConnect.Connexion();
			String pseudo_dest = null;
            ResultSet rs = DbConnect.statement.executeQuery("SELECT pseudo FROM user WHERE (login='"+message.emetteur+"')");
            if (rs.next()) {
            	pseudo_dest= rs.getString(1);
            }
           
           DbConnect.FinConnexion();
			if (message.emetteur.equals(WindowModel.user.login)) {
				chemin = "sent_message.fxml";
				FXMLLoader loader = new FXMLLoader();  
		        AnchorPane pane = loader.load(getClass().getResource(chemin).openStream());
		        VBox vbox = (VBox) pane.getChildren().get(0);
		        
		        AnchorPane pane1 = (AnchorPane) vbox.getChildren().get(0);
		        Label contenu1 = (Label) pane1.getChildren().get(0);
		        contenu1.setText(message.contenu);
		        Label date1 =  (Label) pane1.getChildren().get(1);
		        date1.setText(s);
		        Label pseudo1 = (Label) pane1.getChildren().get(2);
		        pseudo1.setText("Moi");
		        
		        conversation.getChildren().add(pane);
			}
			else if (message.recepteur.equals(WindowModel.user.login)) {
				chemin = "received_message.fxml";
				FXMLLoader loader = new FXMLLoader();  
		        AnchorPane pane = loader.load(getClass().getResource(chemin).openStream());
		        VBox vbox = (VBox) pane.getChildren().get(0);
		        
		        AnchorPane pane1 = (AnchorPane) vbox.getChildren().get(0);
		        Label contenu1 = (Label) pane1.getChildren().get(0);
		        contenu1.setText(message.contenu);
		        Label date1 =  (Label) pane1.getChildren().get(1);
		        date1.setText(s);
		        Label pseudo1 = (Label) pane1.getChildren().get(2);
		        pseudo1.setText(pseudo_dest);
		        
		        conversation.getChildren().add(pane);
			}
			
		}
	
	
	
	
	@FXML
	public void Send() throws IOException, SQLException, ClassNotFoundException {
		// creation d'une instance message
		String contenu1= message_ecrit.getText(); 
		java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
		Message message = new Message(WindowModel.user.login, login_destinataire, contenu1, (Timestamp) ts);
		// mettre le message dans la bdd
		DbConnect.Connexion();
		PreparedStatement stmt = DbConnect.connection.prepareStatement("INSERT INTO message VALUES (?, ?, ?, ?)");
		stmt.setString(1,WindowModel.user.login); 
		stmt.setString(2,login_destinataire); 
		stmt.setString(3,contenu1); 
		stmt.setTimestamp(4,ts); 
		stmt.executeUpdate();
		DbConnect.FinConnexion();
		// envoyer au user2
		// faire la difference entre tcp serveur et tcp client avec if
		TCP_client.send(contenu1, ts.toString(), WindowModel.user.login);
		// clear le textfield message
		message_ecrit.clear();
		// afficher message sur l'écran
		DisplayMessage(message);
	}
	
	// affiche le message reçu à l'écran
	@FXML
	public void Rechercher() throws SQLException, ClassNotFoundException, IOException {
		String recherche= historique.getText();
		conversation.getChildren().clear();
		DbConnect.Connexion();
		ResultSet rs = DbConnect.statement.executeQuery("SELECT * FROM message WHERE ((emetteur='"+login_destinataire+"' AND recepteur='"+WindowModel.user.login+"') OR (emetteur='"+WindowModel.user.login+"' AND recepteur='"+login_destinataire+"')) AND (contenu='"+recherche+"')  ORDER BY date");
		while (rs.next()) {
			message1= new Message(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
			DisplayMessage(message1);
		}
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

