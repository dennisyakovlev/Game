package objects;

import javafx.scene.input.MouseEvent;
import scenes.Scene;

/**
 * Used when a mouse click is needed to be detected
 * on an {@linkplain Object} displayed in a {@linkplain Scene}.
 * 
 * @author Dennis
 *
 */
public class Button extends Object {

	/**
	 * See {@linkplain Object#Object(int, int, String, String, String)}
	 */
	public Button(int x, int y, String sectionName, String fileName, String objectName) {
		
		super(x, y, sectionName, fileName, objectName);

	}

	/**
	 * Checks whether the click of the mouse was within bounds of the Button.
	 * Bounds are an imaginary square that encompasses every pixel of the 
	 * Button in a rectangular shape.
	 * 
	 * @param mouseX see {@linkplain MouseEvent#getX()}
	 * @param mouseY see {@linkplain MouseEvent#getY()}
	 * 
	 * @return true is click was within button bounds, false
	 * if not
	 */
	public boolean isClickedOn(int mouseX, int mouseY) {
		
		return mouseX >= getX() && mouseX <= getX() + getGameWidth() && mouseY >= getY() && mouseY <= getY() + getGameHeight() ? true : false;
		
	}



}
