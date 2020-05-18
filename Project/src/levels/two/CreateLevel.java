package levels.two;

import javafx.scene.paint.Color;
import objects.GameObject;
import utils.LevelCreationInterface;

public class CreateLevel implements LevelCreationInterface {

	private static Level levelTwo;
	
	public CreateLevel() {
		
		levelTwo = new Level();
		
		createObjects();
		
		levelTwo.add();
		levelTwo.update();
		levelTwo.hide();
		
	}
	
	@Override
	public void createObjects() {
		
		GameObject background = new GameObject(0, 0, "two", "testFile.png", "bg");
		background.setImage();
		background.removeBackground(Color.rgb(0, 0, 0));
		background.process();
		levelTwo.addObject(background, 1);
		
		GameObject tree1 = new GameObject(-250, 0, "two", "tree.png", "tree1");
		tree1.setImage();
		tree1.removeBackground(Color.rgb(0, 0, 0));
		tree1.process();
		levelTwo.addObject(tree1, 2);
		
	}
	
	public static void requestLevel() {
		
		levelTwo.startLevel();
		
	}
	
}
