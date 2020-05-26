package levels.one;

import javafx.scene.paint.Color;
import objects.gameobjects.Character;
import objects.gameobjects.GameObject;
import objects.gameobjects.Vine;
import utils.LevelCreationInterface;

public class CreateLevel implements LevelCreationInterface {

	private static Level levelOne;
	
	public CreateLevel() {
		
		levelOne = new Level();
		
		createObjects();
		
		levelOne.add();
		levelOne.update();
		levelOne.hide();
		levelOne.setUpKeyListener();
		
	}
	
	@Override
	public void createObjects() {

		GameObject test = new GameObject(100, 50, "one", "square.png", "square");
		test.setImage();
		test.removeBackground(Color.WHITE);
		test.process();
		levelOne.addObject(test, 1);
		
		Vine vine = new Vine(200, 0, "one", "vine.png", "vine");
		vine.setImage();
		vine.removeBackground(Color.WHITE);
		vine.process();
		vine.setOriginal();
		levelOne.addObject(vine, 2);
		
		GameObject bg = new GameObject(0, 0, "one", "background.png", "bg");
		bg.setImage();
		bg.removeBackground(null);
		bg.process();
		levelOne.setBackgound(bg);
		
		Character character = new Character(levelOne);
		character.createBodyParts();
		character.setPositions();
		levelOne.setCharacter(character);
		
	}
	
	public static void requestLevel() {
		
		levelOne.startLevel();
		
	}
	

	
}
