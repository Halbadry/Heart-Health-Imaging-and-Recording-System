package HW4;

import javafx.application.Application;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.*;
import java.io.*;

public class PatientViewUI {
	private Button check;
	private Label error, patientIDLabel, title, totalLabel, lmLabel, LADLabel, LCXLabel, RCALabel, PDALabel;
	private BorderPane techViewUI;
	private Scene accountScene;
	private VBox leftPane, middlePane, rightPane, topPane;
	private HBox topTopPane,bottomTopPane, titlePane, bottomPane;
	private TextField patientIDTF,totalTF, LMTF, LADTF, LCXTF, RCATF, PDATF;
	
	// creates patient view UI
	public PatientViewUI () {
		techViewUI = new BorderPane();
		accountScene = new Scene(techViewUI, 640, 360);
		top();
		left();
		middle();
		right();
		bottom();
	}
	//creates top of patient view ui also calls all nodes need for top of ui
	private void top() {
		topPane = new VBox();
		topTopPane = new HBox();
		bottomTopPane = new HBox();
		titlePane = new HBox();
		
		title = new Label("Hello <Patient Name>");
		patientIDLabel = new Label("Patient ID:");
		patientIDTF = new TextField();
		totalTF = new TextField();
		totalLabel = new Label("The total Agatston CAC Score");
		
		patientIDTF.setMinSize(300, 5);
		totalTF.setMinSize(300, 5);
		bottomTopPane.setMargin(totalLabel, new Insets(0,50,0,0));
		topTopPane.setMargin(patientIDLabel, new Insets(0,152,0,0));
		titlePane.setAlignment(Pos.CENTER);
		
		titlePane.getChildren().add(title);
		topTopPane.getChildren().add(patientIDLabel);
		topTopPane.getChildren().add(patientIDTF);
		bottomTopPane.getChildren().add(totalLabel);
		bottomTopPane.getChildren().add(totalTF);
		topPane.getChildren().add(titlePane);
		topPane.getChildren().add(topTopPane);
		topPane.getChildren().add(bottomTopPane);
		
		topPane.setMargin(topTopPane, new Insets(10,10,10,0));
		topPane.setMargin(bottomTopPane, new Insets(0,10,10,0));
		topPane.setMargin(title, new Insets(0,10,10,0));
		techViewUI.setTop(topPane);
		
		
	}
	//creates left side of UI
	private void left() {
		leftPane = new VBox();
		lmLabel = new Label("LM:");
		LADLabel = new Label("LAD:");
		LCXLabel = new Label("LCX:");
		RCALabel = new Label("RCA:");
		PDALabel = new Label("PDA:");
		
		leftPane.getChildren().add(lmLabel);
		leftPane.getChildren().add(LADLabel);
		leftPane.getChildren().add(LCXLabel);
		leftPane.getChildren().add(RCALabel);
		leftPane.getChildren().add(PDALabel);
		
		leftPane.setMargin(lmLabel, new Insets(0,10,10,30));
		leftPane.setMargin(LADLabel, new Insets(0,10,10,30));
		leftPane.setMargin(LCXLabel, new Insets(0,10,10,30));
		leftPane.setMargin(RCALabel, new Insets(0,10,10,30));
		leftPane.setMargin(PDALabel, new Insets(0,10,10,30));
		
		techViewUI.setLeft(leftPane);
	}
	//creates middle of UI
	private void middle() {
		middlePane = new VBox();
		
		LMTF = new TextField();
		LADTF = new TextField();
		LCXTF = new TextField();
		RCATF = new TextField();
		PDATF = new TextField();
		
		middlePane.getChildren().add(LMTF);
		middlePane.getChildren().add(LADTF);
		middlePane.getChildren().add(LCXTF);
		middlePane.getChildren().add(RCATF);
		middlePane.getChildren().add(PDATF);
		
		techViewUI.setCenter(middlePane);
	}
	private void right() {
		rightPane = new VBox();
		check = new Button("check");
		
		rightPane.setAlignment(Pos.BOTTOM_LEFT);
		rightPane.setMargin(check, new Insets(0,50,50,0));
		check.setPrefSize(120, 60);
		
		check.setOnAction(new ButtonHandler());
		rightPane.getChildren().add(check);
		techViewUI.setRight(rightPane);
	}
	
	private void bottom() {
		bottomPane = new HBox();
		error = new Label();
		
		error.setTextFill(Color.RED);
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.getChildren().add(error);
		bottomPane.setMargin(error, new Insets(5,5,5,5));
		
		techViewUI.setBottom(bottomPane);
	}
	//
	private void getInformation() throws FileNotFoundException {
		// creates a scanner to collect the data from both txt files
		String patientID = patientIDTF.getText();
		File patient = new File(patientID + "_PatientInfo.txt");
		File patientData = new File(patientID + "CTResults.txt");
		Scanner reader = new Scanner(patient);
		String name  = reader.nextLine();
		title.setText("Hello " + name);
		Scanner data = new Scanner(patientData);
		data.nextLine();
		
		// collects the data from each file
		totalTF.setText(data.nextLine());
		LMTF.setText(data.nextLine());
		LADTF.setText(data.nextLine());
		LCXTF.setText(data.nextLine());
		RCATF.setText(data.nextLine());
		PDATF.setText(data.nextLine());
	}
	// button handler also checks for errors like missing file or recor not being complete
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			String patientID = patientIDTF.getText();
			String dir = System.getProperty("user.dir")+ "\\" + patientID + "_PatientInfo.txt";
			String dir2 = System.getProperty("user.dir")+ "\\" + patientID + "CTResults.txt";
			Object source = e.getSource();
			if (source == check && new File(dir).exists() && new File(dir2).exists() ) {
				try {
					getInformation();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			else if(!new File(dir).exists()) {
				error.setText("patient does not exist");
			}
			// if test were not done sends an error and inputs not applicable in boxes
			else if(!new File(dir2).exists()) {
				error.setText("Record in progress");
				LMTF.setText("Not Applicable");
				LADTF.setText("Not Applicable");
				LCXTF.setText("Not Applicable");
				RCATF.setText("Not Applicable");
				PDATF.setText("Not Applicable");
				totalTF.setText("Not Applicable");
			}
		}
	}
	
	public Scene getScene() {
		return accountScene;
	}
	
}