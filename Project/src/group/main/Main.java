package group.main;

import java.awt.Color;

import javafx.scene.layout.AnchorPane;
import master.image.Image;
import master.scene.Scene;

public class Main {

	private AnchorPane mainPane;
	
	public Main(AnchorPane mainPane) {
		this.mainPane = mainPane;
		
		Image a = new Image("background.png", "a");
		a.convertToUsable(null);
		
		Image b = new Image("peepee.png", "b");
		b.convertToUsable(new Color(0, 0, 0));

		/*
		 * 
		Image c = new Image("background.png", "c");
		c.convertToUsable(new Color(104, 58, 183));
		
		Image d = new Image("background.png", "d");
		d.convertToUsable(new Color(104, 58, 183));

		Image e = new Image("background.png", "e");
		e.convertToUsable(new Color(104, 58, 183));
		*/
		
		Scene scene = new Scene("blue", "testscene");
		
		scene.addChild(a, 2);
		scene.addChild(b, 3);
		//scene.addChild(c, 2);
		//scene.addChild(d, 17);
		//scene.addChild(e, 5);
		
		scene.update();
		
		scene.show();
		
		//t.show();
		//t.hide();
		
	}
}
