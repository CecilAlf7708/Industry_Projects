import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

public class Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataOutputStream toServer = null;
  DataInputStream fromServer = null;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    taServer.setEditable(false);
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later
   
    taClient.setOnKeyPressed(e -> {
    	if(e.getCode() == KeyCode.ENTER) {
    		
    	
    	try {
    	String c = "C: " + taClient.getText().trim() + '\n';
    	taServer.appendText(c);
    	toServer.writeUTF(c);
    	taClient.clear();
    	
    	}
    
    	catch(IOException ex) {
    	System.err.println(ex);
    	}
    
    }
  });
    
    
    
    try {
    	Socket socket = new Socket("localHost", 8000);
    	
    	fromServer = new DataInputStream(socket.getInputStream());
    	toServer = new DataOutputStream(socket.getOutputStream());
    }
    
    catch(IOException ex) {
    	taClient.appendText(ex.toString() + '\n');
    }
    new Thread(() -> {
    	
    	try {
    		while(true) {
    	String s = fromServer.readUTF();
    	taServer.appendText(s);
    		}
    }
    	
    catch(IOException ex) {
    	System.err.println(ex);
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
