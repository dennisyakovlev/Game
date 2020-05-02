package initialize;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Window extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Document.fxml"));

		AnchorPane pane = loader.load();
		pane.setPrefSize(640, 270);
		
        primaryStage.setTitle("Game");
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(pane);
        
        primaryStage.setScene(scene);  
        primaryStage.show();

             
        Main val = new Main(pane);
	}


	public static void startApplication(String[] args) {
		launch(args);
	}
}
