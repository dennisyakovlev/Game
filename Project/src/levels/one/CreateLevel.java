package levels.one;

import objects.GameObject;
import utils.LevelCreationInterface;

public class CreateLevel implements LevelCreationInterface {

	private static Level levelOne;
	
	public CreateLevel() {
		
		levelOne = new Level();
		
		createObjects();
		
		levelOne.add();
		levelOne.update();
		levelOne.hide();
		
	}
	
	@Override
	public void createObjects() {
		
		GameObject background = new GameObject(0, 0, "one", "testFile.png", "bg");
		background.setImage();
		background.removeBackground(null);
		background.process();
		levelOne.addObject(background, 1);
		
	}
	
	public static void requestLevel() {
		
		levelOne.startLevel();
		
	}
	

	
}
