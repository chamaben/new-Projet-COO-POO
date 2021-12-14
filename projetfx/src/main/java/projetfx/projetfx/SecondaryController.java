package projetfx.projetfx;

import java.io.IOException;
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
    private void initialize() throws ClassNotFoundException, SQLException {
		BonjourMessage();
		activelist.setItems(WindowModel.activeMembers);
		// créer la liste de pseudos à afficher
		if (!WindowModel.activeMembers.isEmpty()) {
			for (String p : WindowModel.activeMembers) {
				System.out.println("utilisateur "+ p + " added");
		      }
			
		}
		
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
	
	@FXML
	private void setListe() {
		
	}
	
	@FXML
	private void StartChat() throws IOException {
		// étabissmenent connexion TCP
		 if (activelist.getSelectionModel().getSelectedIndices().size() > 0){
	             int index = activelist.getSelectionModel().getSelectedIndices().get(0);
	             String pseudo_destinataire = activelist.getItems().get(index).toString();

	            System.out.println(pseudo_destinataire);
	            
	            FXMLLoader loader = new FXMLLoader();   
	            AnchorPane pane = loader.load(getClass().getResource("conversation.fxml").openStream());
	            VBox vbox = (VBox) pane.getChildren().get(0);

	            AnchorPane pane1 = (AnchorPane) vbox.getChildren().get(0);
	            Label messageLabel = (Label) pane1.getChildren().get(0);
	            //messageLabel.setText(content);

	            AnchorPane pane2 = (AnchorPane) vbox.getChildren().get(1);
	            Label dateLabel = (Label) pane2.getChildren().get(0);
	            //dateLabel.setText(date);
	            
	            //messageList.getChildren().add(pane);
	        } 
		
		// pour l'utilisateur qui reçoit la demande de connexion,  le thread affiche la nouvelle interface
	}
	
	@FXML
	private void deconnexion() throws ClassNotFoundException, SQLException, IOException 
    {
		WindowModel.deconnexion ();
		//App.setRoot("primary");
    }
	
	
}

