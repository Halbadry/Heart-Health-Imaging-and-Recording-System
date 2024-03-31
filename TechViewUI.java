package HW4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

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
import java.io.*;
import java.util.*;

public class TechViewUI {
	private Button save;
	private Label error, patientIDLabel, vLevel, totalLabel, lmLabel, LADLabel, LCXLabel, RCALabel, PDALabel;
	private BorderPane techViewUI;
	private Scene accountScene;
	private VBox leftPane, middlePane, rightPane, topPane;
	private HBox topTopPane,bottomTopPane , bottomPane;
	private TextField patientIDTF, totalTF, LMTF, LADTF, LCXTF, RCATF, PDATF;
	
	public TechViewUI () {
		techViewUI = new BorderPane();
		accountScene = new Scene(techViewUI, 640, 360);
		top();
		left();
		middle();
		right();
		bottom();
	}
	// creates top of UI
	private void top() {
		topPane = new VBox();
		topTopPane = new HBox();
		bottomTopPane = new HBox();
		patientIDLabel = new Label("Patient ID:");
		patientIDTF = new TextField();
		totalTF = new TextField();
		totalLabel = new Label("The total Agatston CAC Score");
		vLevel = new Label("Vessel level Agatston CAC score");
		
		patientIDTF.setMinSize(300, 5);
		totalTF.setMinSize(300, 5);
		bottomTopPane.setMargin(totalLabel, new Insets(0,50,0,0));
		topTopPane.setMargin(patientIDLabel, new Insets(0,152,0,0));
		
		topTopPane.getChildren().add(patientIDLabel);
		topTopPane.getChildren().add(patientIDTF);
		bottomTopPane.getChildren().add(totalLabel);
		bottomTopPane.getChildren().add(totalTF);
		topPane.getChildren().add(topTopPane);
		topPane.getChildren().add(bottomTopPane);
		topPane.getChildren().add(vLevel);
		
		topPane.setMargin(topTopPane, new Insets(10,10,10,0));
		topPane.setMargin(bottomTopPane, new Insets(0,10,10,0));
		topPane.setMargin(vLevel, new Insets(0,10,10,0));
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
	//creates right of UI
	private void right() {
		rightPane = new VBox();
		save = new Button("Save");
		
		rightPane.setAlignment(Pos.BOTTOM_LEFT);
		rightPane.setMargin(save, new Insets(0,50,50,0));
		save.setPrefSize(120, 60);
		
		save.setOnAction(new ButtonHandler());
		rightPane.getChildren().add(save);
		techViewUI.setRight(rightPane);
	}
	//creates bottom of UI
	private void bottom() {
		bottomPane = new HBox();
		error = new Label();
		
		error.setTextFill(Color.RED);
		bottomPane.setAlignment(Pos.CENTER);
		bottomPane.getChildren().add(error);
		bottomPane.setMargin(error, new Insets(5,5,5,5));
		
		techViewUI.setBottom(bottomPane);
	}
	//collects information to be placed in a text file
	public String getInformation() {
		String information = patientIDTF.getText() + ":\n" + totalTF.getText() + "\n" + LMTF.getText() + 
		"\n" + LADTF.getText() + "\n" + LCXTF.getText() + "\n" + RCATF.getText() + "\n" + PDATF.getText();
		return information;
	}
	private void patientSaver() {
		String patientID = patientIDTF.getText();
		String patientInformation = getInformation();
		String dir = System.getProperty("user.dir");
		File file = new File(dir + "\\" + patientID + "CTResults.txt");
		
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(patientInformation);
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			String patientID = patientIDTF.getText();
			String dir = System.getProperty("user.dir")+ "\\" + patientID + "_PatientInfo.txt";
			
			Object source = e.getSource();
			if (source == save && patientIDTF.getText().length() > 0 && totalTF.getText().length() > 0
					&& LMTF.getText().length() > 0 && LADTF.getText().length() > 0 && LCXTF.getText().length() > 0&& RCATF.getText().length() > 0 && 
					PDATF.getText().length() > 0) {
				if(!new File(dir).exists()) {
					error.setText("Patient does not exist");
				}
				else {
					patientSaver();
					welcomeUI welcome = new welcomeUI();
					Window window = accountScene.getWindow();
					if(window instanceof Stage) {
						Stage newStage = (Stage) window;
						newStage.setScene(welcome.switchScene());
					}
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