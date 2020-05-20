package scenes;

import java.util.ArrayList;

import objects.GameObject;
import objects.Object;
import scenes.Scene;

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

/*
 * scene will "scroll" across level of game
 */

/**
 * Base for all levels. Contains operations to be performed
 * on all levels.
 * 
 * @author Dennis
 *
 */
public class LevelScene extends Scene {
	
	/**
	 * List of children the level has.
	 * 
	 * <br><STRONG>Note:</STRONG> It is not sorted in any way. 
	 */
	private ArrayList<GameObject> children = new ArrayList<>();

	/**
	 * Add <i>child</i> as {@linkplain GameObject} to {@linkplain LevelScene#children}.
	 */
	@Override
	public void addObject(Object child, int importance) {
		
		children.add((GameObject) child);
		
		super.addObject(child, importance);

	}
	
	/**
	 * Decides which of the children on the level are to 
	 * be displayed by the scene.
	 */
	public void update() {
		
		/*
		 * logic for which game object is selected to be shown goes here
		 * currently displays all children
		 */
		
		ArrayList<Object> objs = getChildren();
		ArrayList<Object> childrenToDisplay = new ArrayList<>();
		
		
		for (int i = 0; i < objs.size(); i++) {
			
			childrenToDisplay.add(objs.get(i));
			
		}
		
		
		super.specificUpdate(childrenToDisplay);
		
	}
	
	/**
	 * @return returns {@linkplain LevelScene#children}
	 */
	public ArrayList<GameObject> getGameChildren() {
		
		return children;
		
	}
	
	/**
	 * NO LOGIC.
	 * <br> On hitting esc pops up exit menu
	 */
	public void exitMenu() {
		
	}
	
}
