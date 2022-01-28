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
        scene = new Scene(loadFXML("primary"), 240, 235);
        stage= primarystage;
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.show();
        stage.setOnCloseRequest(event -> {
        	try {
        		if (WindowModel.user!=null)
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/resources/projetfx/projetfx/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void deconnexion() throws ClassNotFoundException, SQLException, IOException 
    {
    	VarGlobal.ClosingApp=true;
    	WindowModel.user.etat=0;
		DbConnect.Connexion();
		Statement stmt = DbConnect.connection.createStatement();
		String query = "UPDATE user SET etat='0' WHERE login='"+WindowModel.user.login+"'";
		stmt.executeUpdate(query);
		DbConnect.FinConnexion();
		UDP_client.connexion(WindowModel.user.login, "0", WindowModel.user.adIP);
		App.stage.setWidth(240);
		App.stage.setHeight(235);
		App.setRoot("primary");
		TCP_serveur.end_thread_tcp();
		UDP_serveur.end_thread_udp();
		VarGlobal.ClosingApp=false;
			
    }
    @Override
    public void stop(){
    	try {
    		if (WindowModel.user!=null)
    			deconnexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}