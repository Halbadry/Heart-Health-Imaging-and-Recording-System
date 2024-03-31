package HW4;

import javafx.application.Application;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class welcomeUI {
	private Button patientIntake, patientView, CTView;
	private Label title;
	private Scene accountScene;
	private VBox welcomeUI;
	
	public welcomeUI () {
		welcomeUI = new VBox();
		accountScene = new Scene(welcomeUI, 640, 360);
		stuff();
		
	}
	// creates buttons and labeslf for mainUI welcome page
	 private void stuff() {
		 patientIntake = new Button("Patient Intake");
		 patientView = new Button("Patient View");
		 CTView = new Button("CT Scan Tech View");
		 title = new Label("Welcome to Heart Health Imaging and Recording System");
		 
		 welcomeUI.setAlignment(Pos.CENTER);
		 patientIntake.setPrefSize(200, 60);
		 patientIntake.setStyle("-fx-background-color: #0000FF");
		 patientView.setPrefSize(200, 60);
		 patientView.setStyle("-fx-background-color: #0000FF");
		 CTView.setPrefSize(200, 60);
		 CTView.setStyle("-fx-background-color: #0000FF");
		 welcomeUI.setMargin(title, new Insets(10,10,10,10));
		 welcomeUI.setMargin(CTView, new Insets(10,10,10,10));
		 welcomeUI.setMargin(patientView, new Insets(10,10,10,10));
		 welcomeUI.setMargin(patientIntake, new Insets(10,10,10,10));
		 
		 patientIntake.setOnAction(new ButtonHandler());
		 CTView.setOnAction(new ButtonHandler());
		 patientView.setOnAction(new ButtonHandler());
		 welcomeUI.getChildren().add(title);
		 welcomeUI.getChildren().add(patientIntake);
		 welcomeUI.getChildren().add(patientView);
		 welcomeUI.getChildren().add(CTView);
		 
	 }
	
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			//if patient intake is pressed switches to patientIntakeUI
			if (source == patientIntake) {
				patientIntakeUI patientIntake = new patientIntakeUI();
				Window window = accountScene.getWindow();
				if(window instanceof Stage) {
					Stage newStage = (Stage) window;
					newStage.setScene(patientIntake.getScene());
				}
			}
			// if patientVIew is pressed switches to patientView UI
			else if(source == patientView) {
				PatientViewUI patient = new PatientViewUI();
				Window window = accountScene.getWindow();
				if(window instanceof Stage) {
					Stage newStage = (Stage) window;
					newStage.setScene(patient.getScene());
				}
			}
			// if CTView button is pressed switchs to TechVIewUI
			else if(source == CTView) {
				TechViewUI patientIntake = new TechViewUI();
				Window window = accountScene.getWindow();
				if(window instanceof Stage) {
					Stage newStage = (Stage) window;
					newStage.setScene(patientIntake.getScene());
				}
			}
		}
	}
	
	public Scene switchScene() {
		return accountScene;
	}
	

}
