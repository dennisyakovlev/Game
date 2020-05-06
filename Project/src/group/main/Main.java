package group.main;

import java.awt.Color;

import javafx.scene.layout.AnchorPane;
import master.image.Image;

public class Main {

	private AnchorPane mainPane;
	
	public Main(AnchorPane mainPane) {
		this.mainPane = mainPane;
		
		Image img = new Image("background.png");
		img.convertToUsable(new Color(104, 58, 183));
		img.test();
	}
}
