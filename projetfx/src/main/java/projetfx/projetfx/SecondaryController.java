package projetfx.projetfx;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SecondaryController {
	
	@FXML
	private TextField pseudo;
	@FXML
	private ListView<String> activelist; 
	@FXML 
	private Text bonjourfield;
	private ObservableList<User> activeMembers = FXCollections.observableArrayList();
	private ObservableList<String> liste_intermediare = FXCollections.observableArrayList();
	
	@FXML
    private void initialize() throws ClassNotFoundException, SQLException {
		BonjourMessage();
		activeMembers = WindowModel.ActiveUsers ();
		// créer la liste de pseudos à afficher
		if (!activeMembers.isEmpty()) {
			for (User u : activeMembers) {
				liste_intermediare.add(u.pseudo);
				System.out.println("utilisateur "+ u.pseudo + " added");
		      }
			activelist.setItems(liste_intermediare);
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
		activeMembers.addListener(new ListChangeListener<User>() {
		    @Override
		    public void onChanged(ListChangeListener.Change<? extends User> change) {
		        // Rappel : plusieurs modifications peuvent être agrégées dans un seul événement.
		        while (change.next()) {
		            String changeLabel = "?"; // NOI18N.
		            if (change.wasReplaced()) {
		                changeLabel = "replaced"; // NOI18N.
		            } else if (change.wasAdded()) {
		                changeLabel = "added"; // NOI18N.
		            } else if (change.wasRemoved()) {
		                changeLabel = "removed"; // NOI18N.
		            } else if (change.wasPermutated()) {
		                changeLabel = "permutated"; // NOI18N.
		            } else if (change.wasUpdated()) {
		                changeLabel = "updated"; // NOI18N.
		            }
		            final String pattern = String.format("Element %s was %s%n", "%d", changeLabel); // NOI18N.
		            if (change.wasAdded() || change.wasReplaced() || change.wasUpdated()) {
		                // Parcours exclusif.
		                for (int index = change.getFrom(); index < change.getTo(); index++) {
		                    System.out.printf(pattern, index);
		                }
		            } else {
		                // Parcours inclusif.
		                for (int index = change.getFrom(); index <= change.getTo(); index++) {
		                    System.out.printf(pattern, index);
		                }
		            }
		        }
		    }
		});
	}
	
	@FXML
	private void StartChat() throws IOException {
		// étabissmenent connexion TCP
		App.setRoot("conversation");
		// pour l'utilisateur qui reçoit la demande de connexion,  le thread affiche la nouvelle interface
	}
	
	@FXML
	private void deconnexion() throws ClassNotFoundException, SQLException, IOException 
    {
		WindowModel.deconnexion ();
		//App.setRoot("primary");
    }
	
	
}

