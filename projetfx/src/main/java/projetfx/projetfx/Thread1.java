package projetfx.projetfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Thread1 extends Thread {
	
	//quand on clique sur une personne, le thread se lance et ouvre la conversation
	
	private static Scene scene;
	private static Stage stage;

	Thread1(String name){
		super(name);
		start();
	}
	
	public void run() {
		scene = new Scene(loadFXML("conversation"), 640, 480);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		
	}
}
