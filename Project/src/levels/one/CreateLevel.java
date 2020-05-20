package levels.one;

import javafx.scene.paint.Color;
import objects.GameObject;
import objects.Vine;
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
		
		/*
		GameObject background = new GameObject(50, 50, "one", "line.png", "bg");
		background.setImage();
		background.removeBackground(Color.WHITE);
		background.process();
		//background.rotate(0, 10);
		//System.out.println("                        sa");
		//background.rotate(0, 10);
		//System.out.println("                        sa");
		//background.rotate(0, 10);
		//System.out.println("                        sa");
		//background.rotate(0, 10);
		levelOne.addObject(background, 1);
		*/
		
		Vine vine = new Vine(100, 0, "one", "vine.png", "bg");
		vine.setImage();
		vine.removeBackground(Color.WHITE);
		vine.process();
		vine.setOriginal();
		levelOne.addObject(vine, 1);
		
		
		
		
	}
	
	public static void requestLevel() {
		
		levelOne.startLevel();
		
	}
	

	
}
