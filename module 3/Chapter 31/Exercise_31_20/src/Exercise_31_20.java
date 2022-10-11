import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_31_20 extends Application {   
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    TabPane tabPane = new TabPane();
    Tab tab1 = new Tab("Line");
    StackPane pane1 = new StackPane();
    BorderPane pane2 = new BorderPane();
    pane1.getChildren().add(new Line(10, 10, 80, 80));
    tab1.setContent(pane1);
    Tab tab2 = new Tab("Rectangle");
    tab2.setContent(new Rectangle(10, 10, 200, 200));
    Tab tab3 = new Tab("Circle");
    tab3.setContent(new Circle(50, 50, 20));    
    Tab tab4 = new Tab("Ellipse");
    tab4.setContent(new Ellipse(10, 10, 100, 80));
    tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
    pane2.setCenter(tabPane);
    HBox h = new HBox();
    pane2.setBottom(h);
    RadioButton up = new RadioButton("Up ");
    RadioButton down = new RadioButton("Down ");
    RadioButton left = new RadioButton("Left ");
    RadioButton right = new RadioButton("Right ");
    h.getChildren().addAll(up,down,left,right);
    ToggleGroup g = new ToggleGroup();
    up.setToggleGroup(g);
    down.setToggleGroup(g);
    left.setToggleGroup(g);
    right.setToggleGroup(g);
    up.setOnAction(e -> {
    	tabPane.setRotate(0);
    });
    down.setOnAction(e -> {
    	tabPane.setRotate(180);
    });
    right.setOnAction(e -> {
    	tabPane.setRotate(90);
    });
    left.setOnAction(e -> {
    	tabPane.setRotate(-90);
    });
    
    
    Scene scene = new Scene(pane2, 300, 250);  
    primaryStage.setTitle("DisplayFigure"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   * line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}