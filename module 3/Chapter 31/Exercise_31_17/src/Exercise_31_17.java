import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class Exercise_31_17 extends Application{
	Pane pane = new Pane();
	Button btC = new Button("Calculate");
	MenuBar menuB = new MenuBar();
	Menu menuOp = new Menu("Operation");
	MenuItem menuCal = new Menu("Calulate");
	MenuItem menuEx = new Menu("Exit");
	TextField invest = new TextField();
	TextField years = new TextField();
	TextField AIR = new TextField();
	TextField fValue = new TextField();
	Label inv = new Label("Investment Amount:");
	Label nOY = new Label("\nNumber of Years:");
	Label rate = new Label("\nAnnual Interest Rate:");
	Label value = new Label("\nFuture Value:");
	VBox vBox1 = new VBox();
	VBox vBox2 = new VBox();
	HBox hBox = new HBox();
	
	public void start(Stage primaryStage) {
		
		pane.getChildren().addAll(vBox1,vBox2,hBox,btC);
		hBox.getChildren().add(menuB);
		hBox.setAlignment(Pos.TOP_CENTER);
		menuB.getMenus().addAll(menuOp);
		menuOp.getItems().addAll(menuCal,menuEx);
		vBox1.getChildren().addAll(inv,nOY,rate,value);
		vBox1.setTranslateY(30);
		vBox2.getChildren().addAll(invest,years,AIR,fValue);
		vBox2.setTranslateX(138);
		vBox2.setTranslateY(30);
		btC.setTranslateY(140);
		btC.setTranslateX(230);
		fValue.setEditable(false);
		btC.setOnMouseClicked(e -> {
			Calculate();
		});
		
		menuCal.setOnAction(e -> {
			Calculate();
		});
		menuEx.setOnAction(e -> {
			System.exit(0);
		});
		
		Scene scene = new Scene(pane,300,300);
		primaryStage.setTitle("Ex-31-17");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void Calculate(){
		double investment = 0;
		double numOfYears = 0;
		double interest = 0;
		double fVal = 0;
		String s = "";
		investment = Double.parseDouble(invest.getText());
		numOfYears = Double.parseDouble(years.getText());
		interest = Double.parseDouble(AIR.getText());
		double mRate = interest / 1200;
		fVal = investment * Math.pow(1 + mRate, numOfYears *12);
		//s = fVal + "";
		fValue.setText(String.format("$%.2f", fVal));
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
