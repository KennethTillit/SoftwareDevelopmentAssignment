package primeNumbers;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {

	@Override
	public void start(Stage primaryStage) {
		TextArea text = new TextArea();
		
		Scene scene = new Scene(new ScrollPane(text), 450, 200);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread( () -> {
			try { 
				ServerSocket socket = new ServerSocket(8000);
				Platform.runLater(() ->
					text.appendText("Server Opened as of " + new Date() + '\n'));
				
				Socket socket2 = socket.accept();
				
				DataInputStream clientInput = new DataInputStream(socket2.getInputStream());
				DataOutputStream clientOutput = new DataOutputStream(socket2.getOutputStream());
				
				while (true) {
					double number = clientInput.readDouble();
					boolean prime = isPrime(number);
					
					clientOutput.writeBoolean(prime);
					
					Platform.runLater( () -> {
						text.appendText("Number Received from Client: " + number + '\n');
						if (prime == true) {
							text.appendText(number + " is a prime number" + '\n');
						} else {
							text.appendText(number + " is not a prime number" + '\n');
						}
					});
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}		
		}).start();
	}
	
	static boolean isPrime(double n)
    {
        // Corner case
        if (n <= 1)
            return false;
  
        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
  
        return true;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}

