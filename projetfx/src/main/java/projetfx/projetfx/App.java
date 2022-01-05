package projetfx.projetfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

}