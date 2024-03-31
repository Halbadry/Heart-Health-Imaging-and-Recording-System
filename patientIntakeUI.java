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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.*;
import java.util.*;

public class patientIntakeUI {
	private Button save;
	private Label title, fNameLabel, lNameLabel, emailLabel, phoneNumberLabel, healthHistoryLabel, insuranceIDLabel, error;
	private BorderPane patientIntakeUI;
	private Scene accountScene;
	private VBox leftPane, middlePane, rightPane;
	private HBox topPane, bottomPane;
	private TextField fNameTF, lNameTF, emailTF, phoneNumberTF, healthHistoryTF, insuranceIDTF;
	
	public patientIntakeUI () {
		patientIntakeUI = new BorderPane();
		accountScene = new Scene(patientIntakeUI, 640, 360);
		top();
		left();
		middle();
		right();
		bottom();
	}
	//creates top of UI
	private void top() {
		topPane = new HBox();
		title = new Label("Patient Intake Form");
		topPane.getChildren().add(title);
		
		topPane.setAlignment(Pos.CENTER);
		topPane.setMargin(title, new Insets(10,10,10,0));
		patientIntakeUI.setTop(topPane);
		
		
	}
	//creates left side of UI
	private void left() {
		leftPane = new VBox();
		fNameLabel = new Label("First Name:");
		lNameLabel = new Label("Last Name:");
		emailLabel = new Label("email:");
		phoneNumberLabel = new Label("Phone Number:");
		healthHistoryLabel = new Label("Health History:");
		insuranceIDLabel = new Label("Insurance ID:");
		
		leftPane.setMargin(fNameLabel, new Insets(10,10,10,0));
		leftPane.setMargin(lNameLabel, new Insets(10,10,10,0));
		leftPane.setMargin(emailLabel, new Insets(5,10,10,0));
		leftPane.setMargin(phoneNumberLabel, new Insets(5,10,10,0));
		leftPane.setMargin(healthHistoryLabel, new Insets(5,10,10,0));
		leftPane.setMargin(insuranceIDLabel, new Insets(10,10,10,0));
		leftPane.setAlignment(Pos.TOP_RIGHT);
		
		patientIntakeUI.setLeft(leftPane);
		leftPane.getChildren().add(fNameLabel);
		leftPane.getChildren().add(lNameLabel);
		leftPane.getChildren().add(emailLabel);
		leftPane.getChildren().add(phoneNumberLabel);
		leftPane.getChildren().add(healthHistoryLabel);
		leftPane.getChildren().add(insuranceIDLabel);
	}
	//creates middle of UI
	private void middle() {
		middlePane = new VBox();
		fNameTF = new TextField();
		lNameTF = new TextField();
		emailTF = new TextField();
		phoneNumberTF = new TextField();
		healthHistoryTF = new TextField();
		insuranceIDTF = new TextField();
		
		fNameTF.setPrefSize(300, 5);
		lNameTF.setPrefSize(300, 5);
		emailTF.setPrefSize(300, 5);
		phoneNumberTF.setPrefSize(300, 5);
		healthHistoryTF.setPrefSize(300, 5);
		insuranceIDTF.setPrefSize(300, 5);
		fNameTF.setMaxSize(300, 5);
		lNameTF.setMaxSize(300, 5);
		emailTF.setMaxSize(300, 5);
		phoneNumberTF.setMaxSize(300, 5);
		healthHistoryTF.setMaxSize(300, 5);
		insuranceIDTF.setMaxSize(300, 5);
		
		middlePane.setMargin(fNameTF, new Insets(5,5,5,0));
		middlePane.setMargin(lNameTF, new Insets(5,5,5,0));
		middlePane.setMargin(emailTF, new Insets(5,5,5,0));
		middlePane.setMargin(phoneNumberTF, new Insets(5,5,5,0));
		middlePane.setMargin(healthHistoryTF, new Insets(5,5,5,0));
		middlePane.setMargin(insuranceIDTF, new Insets(5,5,5,0));
		
		
		patientIntakeUI.setCenter(middlePane);
		middlePane.getChildren().add(fNameTF);
		middlePane.getChildren().add(lNameTF);
		middlePane.getChildren().add(emailTF);
		middlePane.getChildren().add(phoneNumberTF);
		middlePane.getChildren().add(healthHistoryTF);
		middlePane.getChildren().add(insuranceIDTF);
		
		
	}
	//creates right of UI
	private void right() {
		rightPane = new VBox();
		save = new Button("Save");
		
		rightPane.setAlignment(Pos.BOTTOM_LEFT);
		rightPane.setMargin(save, new Insets(0,50,50,0));
		save.setPrefSize(120, 60);
		
		save.setOnAction(new ButtonHandler());
		rightPane.getChildren().add(save);
		patientIntakeUI.setRight(rightPane);
	}
	//creates bottom of UI
	private void bottom() {
		bottomPane = new HBox();
		error = new Label();
		
		error.setTextFill(Color.RED);
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.getChildren().add(error);
		bottomPane.setMargin(error, new Insets(5,5,5,0));
		
		patientIntakeUI.setBottom(bottomPane);
	}
	 //gets info from login page
	private String getPatient() {
		String patient = fNameTF.getText() + "\n" +  lNameTF.getText() + "\n" +
				emailTF.getText() + "\n" + phoneNumberTF.getText() + "\n" + healthHistoryTF.getText()
				+ "\n" + insuranceIDTF.getText() + "\n";
		return patient;
	}
	//saves patient information in a txt file
	private void patientSaver() {
		Random rand = new Random();
		int patientID = rand.nextInt(99999 - 10000 + 1) + 10000;
		String patientInformation = getPatient();
		String dir = System.getProperty("user.dir");
		File file = new File(dir + "\\" + patientID + "_PatientInfo.txt");
		
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(patientInformation);
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//button handler with error handling
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			Object source = e.getSource();
			if (source == save && fNameTF.getText().length() > 0 && lNameTF.getText().length() > 0 && emailTF.getText().length() > 0 && phoneNumberTF.getText().length() > 0 && healthHistoryTF.getText().length() > 0 && insuranceIDTF.getText().length() > 0) {
				patientSaver();
				System.out.print(getPatient());
				welcomeUI welcome = new welcomeUI()	;
				Window window = accountScene.getWindow();
				if(window instanceof Stage) {
					Stage newStage = (Stage) window;
					newStage.setScene(welcome.switchScene());
					}
				}
			else {
				error.setText("A box is empty");
			}
		}
	}
	
	public Scene getScene() {
		return accountScene;
	}
	
}