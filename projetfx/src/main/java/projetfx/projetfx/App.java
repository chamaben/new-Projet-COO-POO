package projetfx.projetfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JavaFX App
 */
public class App extends Application {

    protected static Scene scene;
    protected static Stage stage;
    protected User user;
    

   @SuppressWarnings("exports")
@Override
    public void start(Stage primarystage) throws IOException {
        scene = new Scene(loadFXML("primary"), 234, 234);
        stage= primarystage;
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.show();
        stage.setOnCloseRequest(event -> {
        	try {
				deconnexion();
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
   
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    protected static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
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