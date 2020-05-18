package preinit;

import java.io.IOException;

import init.Start;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import variables.InitValue;

public class Window extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Document.fxml"));

		AnchorPane pane = loader.load();
		
		InitValue.setMainPane(pane);
		
		pane.setPrefSize(630, 260);
		
        primaryStage.setTitle("Game");
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(pane);
        
        primaryStage.setScene(scene);  
        
        Start start = new Start();
        start.firstMethod(primaryStage);
	}


	public static void startApplication(String[] args) {
		launch(args);
	}
}
