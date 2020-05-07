package init;

import objects.Object;

public abstract class Start {

	public static void firstMethod() {
		
		Object object = new Object(0, 0, "background.png", "bg");
		object.getImage();
		object.removeBackground(null);
		
	}
	
}
