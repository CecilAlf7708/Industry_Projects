// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  
    new Thread(() -> {
    	try {
    		ServerSocket serverSocket = new ServerSocket(8000);
    		Platform.runLater(() ->
    		ta.appendText("Server started at " + new Date() + '\n'));
    		Socket socket = serverSocket.accept();
    		Platform.runLater(() ->
    		ta.appendText("Client connected at " + new Date() + '\n'));
    		DataInputStream iFC = new DataInputStream(socket.getInputStream());
    		DataOutputStream oTC = new DataOutputStream(socket.getOutputStream());
    		
    		while(true) {
    			
    			double rate = iFC.readDouble();
    			int years = iFC.readInt();
    			double loan = iFC.readDouble();
    			Loan loans = new Loan(rate,years,loan);
    			double totalP = loans.getTotalPayment();
    			double monthly = loans.getMonthlyPayment();
    			
    			oTC.writeDouble(monthly);
    			oTC.writeDouble(totalP);
    			
    			Platform.runLater(() -> {
    				ta.appendText("Rate received from client: " + rate + '\n');
    				ta.appendText("Years received from client: " + years + '\n');
    				ta.appendText("Loan received from client: " + loan + '\n');
    			});
    			
    		}
    	}
    	catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }).start();
  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
