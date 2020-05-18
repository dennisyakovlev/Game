package utils;

import javafx.animation.AnimationTimer;
import objects.GameObject;

/**
 * Interface needed to be able to update level and
 * perform necessary logic to operate level.
 * 
 * @author Dennis
 *
 */
public interface GameLoopInterface {

	/**
	 * Logic for any potential collisions between two
	 * {@linkplain GameObject} in the level
	 */
	public void collisionLogic();
	
	/**
	 * Moving of any {@linkplain GameObject} on the level
	 */
	public void animatations();
	
	/**
	 * Contains the {@linkplain AnimationTimer} to continuously update the 
	 * level and all operations associated with the level
	 */
	public void startLevel();
	
}
