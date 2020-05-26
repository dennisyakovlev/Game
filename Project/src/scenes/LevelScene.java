package scenes;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import objects.Object;
import objects.gameobjects.Character;
import objects.gameobjects.GameObject;
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

/*
 * 
 * have to do same thing for character as for scene but place
 * it on top of everything
 * 
 */

/**
 * Base for all levels. Contains operations to be performed
 * on all levels.
 * 
 * <br><br><STRONG>Usage</STRONG>
 * <br> See {@linkplain Scene}
 * <br> After completed steps must call {@linkplain LevelScene#setUpKeyListener()}.
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
	 * The background for the level.
	 */
	private GameObject background;
	
	private boolean isHidden = false;
	private KeyCode currentKey = null;
	private ArrayList<Object> childrenToDisplay = new ArrayList<>();
	private Character character;
	
	
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
	 * <br> Moves background slower than foreground objects.
	 */
	public void update() {

		childrenToDisplay = getChildren();
		
		if (currentKey == KeyCode.A) {
			
			backgroundUpdate(-1);
			
			character.moveLeft();
			
			for (int i = 0; i < children.size() - 1; i++) {
				
				children.get(i).translateX(3);
				
			}
			
		} else if (currentKey == KeyCode.D) {
			
			backgroundUpdate(1);
			
			character.moveRight();
			
			for (int i = 0; i < children.size() - 1; i++) {

				children.get(i).translateX(-3);
				
			}
			
		} else {
			
			character.stopMoving();
			
		}
		
		super.specificUpdate(childrenToDisplay);
				
		updateCharacter();
		
	}
	
	/**
	 * @return returns {@linkplain LevelScene#children}
	 */
	public ArrayList<GameObject> getGameChildren() {
		
		return children;
		
	}
	
	/**
	 * Adds the background to the level.
	 * 
	 * @param background A {@linkplain GameObject} to be added to the 
	 * level as the background.
	 */
	public void setBackgound(GameObject background) {
		
		background.setX(background.getX() - 1);
		
		children.add(background);
		
		this.background = background;
		
		super.addObject(background, 0);
		
	}
	
	/**
	 * Sets up the key listener for the level.
	 */
	public void setUpKeyListener() {
		
		this.getScene().setOnKeyPressed(e -> {
			
			if (!isHidden) { //if the level is on screen
				
				if (e.getCode() == KeyCode.A) {
					
					currentKey = KeyCode.A;
					
				} else if (e.getCode() == KeyCode.D) {
					
					currentKey = KeyCode.D;
					
				}
				
			}
			
		});
		
		this.getScene().setOnKeyReleased(e -> {
			
			if (!isHidden) {
				
				if (currentKey == KeyCode.A || currentKey == KeyCode.D) {
					
					currentKey = null;
					
				}
				
			}
			
		});

	}
	
	/**
	 * NO LOGIC.
	 * <br> On hitting the esc key a pop up exit menu appears.
	 */
	public void exitMenu() {
		
	}
	
	/**
	 * Update the background for the level.
	 * 
	 * @param direction An int of -1 or 1 determining the direction the background will
	 * move. 1 to move right and -1 to move left.
	 * 
	 */
	public void backgroundUpdate(int direction) {
		
		//gc.clearRect(0,	0, 300, 150);
		
		if (direction == -1) {
			
			background.translateX(1);
			
		} else {
			
			background.translateX(-1);
			
		}
		
		
		ArrayList<int[]> positions = background.getPositions();
		ArrayList<Color> colors = background.getColors();
		
		/*
		 * Move the scene left by taking the right colum of pixels and placing
		 * them as the very left colum.
		 */
		if (direction == -1) {
			
			for (int i = 0; i < 150; i++) {

				positions.add(300 * i, new int[] {0, i});
				colors.add(300 * i, colors.get(((300 * i) + 300) - 1));
				
				positions.remove((300 * i) + 300);
				colors.remove((300 * i) + 300);
				
			}
			
		} 
		
		/*
		 * Move the scene right by taking the left colum of pixels and placing 
		 * them as the very right colum.
		 */
		else {
			
			for (int i = 0; i < 150; i++) {

				positions.add((300 * i) + 300, new int[] {299, i});
				colors.add((300 * i) + 300, colors.get(300 * i));
				
				positions.remove((300 * i) );
				colors.remove((300 * i) );
				
			}
			
		}

	}
	
	@Override
	public void toFront() {
		
		isHidden = false;
		
		super.toFront();
		
	}
	
	@Override
	public void hide() {
		
		isHidden = true;
		
		super.hide();
		
	}
	
	public void setCharacter(Character character) {
		
		this.character = character;
		
	}
	
	public void updateCharacter() {
		
		ArrayList<int[]> positions = character.getPositions();
		ArrayList<Color> colors = character.getColors();
		GraphicsContext gc = getGraphicsContext2D();
		
		for (int currentPixel = 0; currentPixel < positions.size(); currentPixel ++) {
			
			gc.setFill(colors.get(currentPixel));
			gc.fillRect(positions.get(currentPixel)[0], positions.get(currentPixel)[1], 1, 1);

		}
		
		
	}
	
}

/*
 
 
 
 public void update() {

		
		ArrayList<Object> objs = getChildren();
		ArrayList<Object> childrenToDisplay = new ArrayList<>();
		
		
		for (int i = 0; i < objs.size(); i++) {
			
			childrenToDisplay.add(objs.get(i));
			
		}
		
		
		super.specificUpdate(childrenToDisplay);
		
	}
 
 
 */
