package HW4;
//main file that shows the stage and scene

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.stage.Window;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		welcomeUI welcomeUI = new welcomeUI();
		Scene scene = welcomeUI.switchScene();
		
		primaryStage.setTitle("Your Pediatric Portal");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	

	public static void main(String[] args) {	
		Application.launch(args);
	}

	
}