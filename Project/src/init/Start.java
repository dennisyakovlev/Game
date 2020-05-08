package init;

import javafx.scene.paint.Color;
import objects.Object;
import scene.Scene;

public abstract class Start {

	public static void firstMethod() {
		
		Object object = new Object(0, 0, "background.png", "bg");
		object.setImage();
		object.removeBackground(null);

		Scene scene = new Scene();
		scene.addObject(object);
		scene.display();
		scene.add();
		
	}
	
}
