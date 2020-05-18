package variables;

import objects.Object;
import scenes.Scene;

/**
 * Universal constants for easy access.
 * 
 * @author Dennis
 *
 */
public abstract class Constant {

	/**
	 * The width of {@linkplain Scene}.
	 */
	public static final int sceneWidth = 640;
	
	/**
	 * The height of {@linkplain Scene}.
	 */
	public static final int sceneHeight = 270;
	
	/**
	 * The y coordinate of the mathematical origin
	 */
	public static final int middleWindowX = 315;
	
	/**
	 * The x coordinate of the mathematical origin
	 */
	public static final int middleWindowY = 130;
	
	/**
	 * The horizontal amount of pixels in an image 
	 * to be converted to an {@linkplain Object}
	 */
	public static final int imageWidth = 128;
	
	/**
	 * The vertical amount of pixels in an image 
	 * to be converted to an {@linkplain Object}
	 */
	public static final int imageHeight = 54;
	
}
