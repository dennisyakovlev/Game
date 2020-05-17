package utils;

import java.util.ArrayList;

import levels.one.Level;
import objects.GameObject;
import scene.Scene;

/*
 * governing class for ALL levels, and only levels, in game
 * 
 * all levels will have animation of background moving slowing than foreground
 * they will also have other things in common that I dont know yet
 * this class will take care of those commonalities
 */

/*
 * need array list of game objects that are near the scene
 */
public class LevelScene extends Scene {

	private int positionOnLevel = 0;
	private ArrayList<GameObject> allChildren = new ArrayList<>();
	private Level oof;
	
	public void update() {
		
		/*
		 * logic for which game object is selected to be shown goes here
		 */
		
		ArrayList<Integer> objectsToShow = new ArrayList<>();
		objectsToShow.add(0);
		super.update(objectsToShow);
		
		/*
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oof.startLevel();
		*/
	}
	
	public void set(Level l) {
		
		oof = l;
		
	}
	
}
