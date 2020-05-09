package init;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import objects.Object;
import scene.Scene;

public abstract class Start {

	public static void firstMethod() {
		
		/*
		Object a = new Object(0, 0, "background.png", "bg");
		a.setImage();
		a.removeBackground(Color.rgb(0, 0, 0));
		a.process();

		Object b = new Object(0, 0, "background.png", "bg2");
		b.setImage();
		b.removeBackground(Color.rgb(0, 0, 0));
		b.process();
		
		Object c = new Object(0, 0, "background.png", "bg3");
		c.setImage();
		c.removeBackground(Color.rgb(0, 0, 0));
		c.process();
		
		Object d = new Object(0, 0, "background.png", "bg4");
		d.setImage();
		d.removeBackground(Color.rgb(0, 0, 0));
		d.process();
		*/
		
		Object tree = new Object(100, 100, "background.png", "bg4");
		tree.setImage();
		tree.removeBackground(null);
		tree.process();
		
		
		
		
		Scene scene = new Scene();
		scene.addObject(tree, 5);
		
		scene.update();
		
		//scene.initialUpdate();
		

		
		
		
		scene.add();
	}
	
}
