package init;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import objects.Object;
import scene.Scene;
import variables.Program;

public abstract class Start {

	public static Node bg;

	public static void firstMethod() {
		Object object = new Object(0, 0, "yurr.png", "bg");

		object.setImage();
		object.removeBackground(null);








		Scene scene = new Scene();
		scene.addObject(object);
		scene.display();
		scene.add();




	}
	
}
