package primeNumbers;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
	DataOutputStream toServer;
	DataInputStream fromServer;

	@Override
	public void start(Stage primaryStage) {
		BorderPane textFieldPane = new BorderPane();
		textFieldPane.setPadding(new Insets(5, 5, 5, 5));
		textFieldPane.setStyle("-fx-border-color: blue");
		textFieldPane.setLeft(new Label("Enter a number: "));
		
		TextField textF = new TextField();
		textF.setAlignment(Pos.BOTTOM_RIGHT);
		textFieldPane.setCenter(textF);
		
		BorderPane mainPane = new BorderPane();
		TextArea textA = new TextArea();
		mainPane.setCenter(new ScrollPane(textA));
		mainPane.setBottom(textFieldPane);
		
		Scene scene = new Scene (mainPane, 450, 200);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		textF.setOnAction(e -> {
			try {
				double number = Double.parseDouble(textF.getText().trim());
				
				toServer.writeDouble(number);
				toServer.flush();
				
				boolean prime = fromServer.readBoolean();
				
				textA.appendText("You Entered: " + number + '\n');
				if (prime == true) {
					textA.appendText(number + " is a prime number" + '\n');
				} else {
					textA.appendText(number + " is not a prime number" + '\n');
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		});
		
		try {
			Socket socket = new Socket("localhost", 8000);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			textA.appendText(ex.toString() + '\n');
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

