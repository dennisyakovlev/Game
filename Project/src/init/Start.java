package init;

import javafx.scene.paint.Color;
import objects.Object;
import scene.Scene;

/*
 * 
 * Really important note:
 * 
 * the pane that is part of window has size of 630 by 260
 * the scene which you draw on is 640 by 270
 * 
 * this is because each pixel is translated into a 5 by 5 square. When you have last square of an image paritally off the screen
 * to the left, the entire square cannot be drawn because it is viewed by javafx to be off the screen.
 * 
 * To fix this would not be hard but would slow the loops down immensily
 * 
 * My fix instead was to make the part of the scene that we view be smaller by one square of each side
 * the same error exists where if an image is entering the scene from the left (last sqaure is partially displayed)
 * it will not display until the sqaure is fully on the screen. 
 * However, since the actual view is smaller you cant see this glitch, giving the illusions the square smoothly enters
 * the scene pixel by pixel
 * 
 * this also happen when the image enters from the top
 * 
 * we cannot fix this properly, do not try to, it is a waste of time.
 * 
 * we just have to code with this in mind "an image may be on the scene but not in view"
 * 
 */

public abstract class Start {

	public static void firstMethod() {
		

		Object e = new Object(-634, 0, "background.png", "bg4");
		e.setImage();
		e.removeBackground(Color.BLACK);
		e.process();

		Object b = new Object(-100, 100, "tree2.png", "tree");
		b.setImage();
		b.removeBackground(Color.rgb(0, 0, 0));
		b.process();

		Object c = new Object(0, 0, "test background.png", "Background - test 1");
		c.setImage();
		c.removeBackground(Color.rgb(0, 0, 0));
		c.process();


		// this can be deleted
		Object d = new Object(30, 120, "character test.png", "character - test 1");
		d.setImage();
		d.removeBackground(Color.rgb(255, 255, 255));
		d.process();

		
		Scene scene = new Scene();
		scene.addObject(b, 4);
		scene.addObject(e, 1);
		scene.addObject(c, 0);
		scene.addObject(d, 5);
		scene.update();
		scene.add();
	}
	
}
