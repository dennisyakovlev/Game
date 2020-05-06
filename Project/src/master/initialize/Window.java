package master.initialize;

import java.io.IOException;

import group.main.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import master.variables.Constant;
import master.variables.InitValue;

public class Window extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Document.fxml"));

		AnchorPane pane = loader.load();
		
		InitValue.setMainPane(pane);
		
		Canvas c = new Canvas(Constant.sceneWidth, Constant.sceneHeight);
		pane.getChildren().add(c);
		InitValue.setCanvas(c);
		
		pane.setPrefSize(Constant.sceneWidth, Constant.sceneHeight);
		
        primaryStage.setTitle("Game");
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(pane);
        
        primaryStage.setScene(scene);  
        primaryStage.show();

             
        Main m = new Main(pane);
	}


	public static void startApplication(String[] args) {
		launch(args);
	}
}
