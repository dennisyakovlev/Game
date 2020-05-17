package init;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import levels.one.Level;
import objects.GameObject;
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

	public static void firstMethod(Stage primaryStage) {
		
		/*
		 * for Mekael
		 * 
		 * you don't currently care about everything ive added other than
		 * what ill tell you if you dont wake up ill leave it here
		 */

		/*
		GameObject background = new GameObject(0, 0, "background.png", "background");
		background.setImage();
		background.removeBackground(null);
		background.process();
		
		GameObject tree = new GameObject(0, 0, "tree2.png", "tree");
		tree.setImage();
		tree.removeBackground(Color.BLACK);
		tree.process();
		
		ArrayList<GameObject> t = new ArrayList<>();
		t.add(tree);
		
		
		Scene scene = new Scene();
		scene.addObject(background, 1);
		//scene.addObject(tree, 2);
		scene.update(t);
		scene.add();
		*/
		
		GameObject tree = new GameObject(0, 0, "tree2.png", "tree");
		tree.setImage();
		tree.removeBackground(Color.BLACK);
		tree.process();
		
		Level levelOne = new Level();
		levelOne.addObject(tree, 1);
		levelOne.add();
		//levelOne.setScene(); //called to set scene and do everything necessary to show scene. should be done on program startup
		levelOne.startLevel();
		
		
		 primaryStage.setOnShowing(e -> {
			 
			 AnimationTimer anim = new AnimationTimer() {
				
				 long start = System.nanoTime();
				 
				@Override
				public void handle(long now) {
					
					if ((now - start) / 1000000 > 3000) {
						
						levelOne.setScene();
						
					}
					
				}
			};
				
			anim.start();	
		});
		
		
		 primaryStage.show();
		
	}
	
}
