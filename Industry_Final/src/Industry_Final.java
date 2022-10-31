import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.animation.PathTransition;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.input.KeyCode;

public class Industry_Final extends Application{
	//getButton(): MouseButton = L/R clicks
	//Timeline.INDEFINTE = endless loop
	//PathTransition
	//FadeTransition
	//pt.setInterpolator(Interpolator.LINEAR); = non slowing loop
	
	boolean t = false;
	int score = 0;
	double d = 1.0;
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		HBox hBox = new HBox();
		HBox hBox2 = new HBox();
		Label s = new Label("Score:");
		TextField text = new TextField();
		text.setEditable(false);
		Rectangle rec = new Rectangle(1, 10, 20, 30);
		rec.setTranslateX(290);
		rec.setTranslateY(65);
		Circle circle = new Circle(200,230,150);
		Circle mark = new Circle(95,95,15);
		
		pane.getChildren().addAll(hBox,hBox2);
		
		
		hBox2.setTranslateY(20);
		RadioButton cbO = new RadioButton("C:Orange");
		RadioButton cbR = new RadioButton("C:Red");
		RadioButton cbB = new RadioButton("C:Blue");
		RadioButton cbG = new RadioButton("C:Green");
		RadioButton cbP = new RadioButton("C:Purple");
		RadioButton rbBk = new RadioButton("R:Black");
		RadioButton rbY = new RadioButton("R:Yellow");
		RadioButton rbW = new RadioButton("R:White");
		RadioButton rbGy = new RadioButton("R:Gray");
		RadioButton rbM = new RadioButton("R:Maroon");
		ToggleGroup groupC = new ToggleGroup();
		ToggleGroup groupR = new ToggleGroup();
		cbO.setToggleGroup(groupC);
		cbR.setToggleGroup(groupC);
		cbB.setToggleGroup(groupC);
		cbG.setToggleGroup(groupC);
		cbP.setToggleGroup(groupC);
		rbBk.setToggleGroup(groupR);
		rbY.setToggleGroup(groupR);
		rbW.setToggleGroup(groupR);
		rbGy.setToggleGroup(groupR);
		rbM.setToggleGroup(groupR);
		rec.setStroke(Color.BLACK);
		rec.setFill(Color.BLACK);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
		mark.setStroke(Color.BLACK);
		mark.setFill(Color.ORANGE);
		Button start = new Button("Start");
		pane.getChildren().add(circle);
		pane.getChildren().add(start);
		
		pane.getChildren().add(mark);
		pane.getChildren().add(rec);
		hBox.getChildren().addAll(cbO,cbR,cbB,cbG,cbP);
		hBox2.getChildren().addAll(rbBk,rbY,rbW,rbGy,rbM);
	
		cbO.setOnAction(e ->{
			if(cbO.isSelected()) {
				mark.setFill(Color.ORANGE);
			}
		});
		cbR.setOnAction(e ->{
			if(cbR.isSelected()) {
				mark.setFill(Color.RED);
			}
		});
		cbB.setOnAction(e ->{
			if(cbB.isSelected()) {
				mark.setFill(Color.BLUE);
			}
		});
		cbG.setOnAction(e ->{
			if(cbG.isSelected()) {
				mark.setFill(Color.GREEN);
			}
		});
		cbP.setOnAction(e ->{
			if(cbP.isSelected()) {
				mark.setFill(Color.PURPLE);
			}
		});
		rbBk.setOnAction(e ->{
			if(rbBk.isSelected()) {
				rec.setFill(Color.BLACK);
			}
		});
		rbY.setOnAction(e ->{
			if(rbY.isSelected()) {
				rec.setFill(Color.YELLOW);
			}
		});
		rbW.setOnAction(e ->{
			if(rbW.isSelected()) {
				rec.setFill(Color.WHITE);
			}
		});
		rbGy.setOnAction(e ->{
			if(rbGy.isSelected()) {
				rec.setFill(Color.GREY);
			}
		});
		rbM.setOnAction(e ->{
			if(rbM.isSelected()) {
				rec.setFill(Color.MAROON);
			}
		});
		cbO.setSelected(true);
		rbBk.setSelected(true);
		
		start.setTranslateX(175);
		start.setTranslateY(50);
		start.setOnAction(E -> {
			pane.getChildren().removeAll(hBox,hBox2,start);
		
		
		
		pane.getChildren().add(s);
		pane.getChildren().add(text);
		text.setTranslateX(35);
		text.setText("0");
		//setRate = animation speed, -rate = reverse
		PathTransition pt = new PathTransition();
		PathTransition pt2 = new PathTransition();
		pt.setDuration(Duration.millis(4000));
		pt2.setDuration(Duration.millis(4000));
		pt.setPath(circle);
		pt2.setPath(circle);
		pt.setNode(rec);
		pt2.setNode(mark);
		Timeline tl = new Timeline();
		pt.setOrientation(
		PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setCycleCount(Timeline.INDEFINITE);
		pt.setAutoReverse(false);
		pt.setInterpolator(Interpolator.LINEAR);
		pt2.setOrientation(
		PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pt2.setCycleCount(Timeline.INDEFINITE);
		pt2.setAutoReverse(false);
		pt2.setInterpolator(Interpolator.LINEAR);
		final KeyValue kv = new KeyValue(circle.centerXProperty(),300,Interpolator.LINEAR);
		final KeyFrame kf = new KeyFrame(Duration.millis(1000),kv);
		tl.getKeyFrames().add(kf);
		//100 millis is range for points
		pt.jumpTo(Duration.millis(3000));
		pt2.jumpTo(Duration.millis(Math.random() * 4000));
		pt2.setRate(0.001);
		pt.play(); // Start animation 
		pt2.play();
		
		pane.setOnKeyPressed(e  -> {
			if (e.getCode() == KeyCode.SPACE) {
				Duration recD = pt.getCurrentTime();
				Duration markD = pt2.getCurrentTime();
				
				double r = recD.toMillis();
				double m = markD.toMillis();
				if(r < m - 101 || r > m + 101) {
					Label l = new Label("GAME OVER");
					pane.getChildren().add(l);
					l.setTranslateX(150);
					l.setTranslateY(40);
					l.setFont(Font.font("arial",FontWeight.NORMAL,FontPosture.REGULAR, 16));
					pt.stop();
					pt2.stop();
					
				}
				else {
					score++;
					text.setText(score + "");
					pt2.jumpTo(Duration.millis(Math.random() * 4000));
					double c = pt.getRate();
					if(c > 0) {
					d = (c * -1) - 0.1;	
					pt.setRate(d);
					}
					else {
						d = (c * -1) + 0.1;
						pt.setRate(d);
					}
				}
			}
		});
		pane.requestFocus();
		});
		
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Industry Final");
		primaryStage.setScene(scene); 
		primaryStage.show(); 
		
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}