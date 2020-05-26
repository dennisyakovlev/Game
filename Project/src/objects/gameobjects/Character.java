package objects.gameobjects;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import scenes.LevelScene;

/*
 * should be game object, but his parts arent added to scene.
 * Rather, perform operations on the body parts (animate them in
 * animate method here), then update the "positions" array for the
 * character game object to be drawn. Use "that" array to
 * detect collisions.
 */

/*
 * to flip character find left or right most pixel of entire character
 * and flip around that vertical line
 */

/**
 * 
 * 
 * @author Dennis
 *
 */
public class Character {

	private LevelScene level;
	private ArrayList<int[]> positions = new ArrayList<>();
	private ArrayList<Color> colors = new ArrayList<>();
	private GameObject armFront, armBack, legFront, legBack, body;
	private int goingRight = 0;
	
	public Character(LevelScene level) {
		
		this.level = level;
		
	}

	public void createBodyParts() {
		
		armFront = new GameObject(50, 0, "charcter", "arm.png", "armFront");
		armFront.setImage();
		armFront.removeBackground(Color.WHITE);
		armFront.process();
		
		armBack = new GameObject(100, 0, "charcter", "arm.png", "armBack");
		armBack.setImage();
		armBack.removeBackground(Color.WHITE);
		armBack.process();
		
		legFront = new GameObject(50, 50, "charcter", "legF.png", "legFront");
		legFront.setImage();
		legFront.removeBackground(Color.WHITE);
		legFront.process();
		
		legBack = new GameObject(100, 50, "charcter", "legB.png", "legBack");
		legBack.setImage();
		legBack.removeBackground(Color.WHITE);
		legBack.process();

		body = new GameObject(200, 50, "charcter", "body.png", "front");
		body.setImage();
		body.removeBackground(Color.WHITE);
		body.process();
		
	}
	
	public void setPositions() {
		
		positions.addAll(legBack.getPositions());
		colors.addAll(legBack.getColors());
		
		positions.addAll(armBack.getPositions());
		colors.addAll(armBack.getColors());
		
		positions.addAll(legFront.getPositions());
		colors.addAll(legFront.getColors());
		
		positions.addAll(body.getPositions());
		colors.addAll(body.getColors());
		
		positions.addAll(armFront.getPositions());	
		colors.addAll(armFront.getColors());	
		
	}

	public void moveRight() {
		
		positions.clear();

		goingRight = 1;
		
		legBack.rotate(0, -3, Math.PI / 4);
		legFront.rotate(0, 3, Math.PI / 4);
		armBack.rotate(0, -3, Math.PI / 4);
		armFront.rotate(0, 3, Math.PI / 4);
		
		setPositions();
		
	}
	
	public void moveLeft() {
		
		positions.clear();

		goingRight = -1;
		
		legBack.rotate(0, 3, Math.PI / 4);
		legFront.rotate(0, -3, Math.PI / 4);
		armBack.rotate(0, 3, Math.PI / 4);
		armFront.rotate(0, -3, Math.PI / 4);
		
		setPositions();
		
	}
	
	public void stopMoving() {
		
		if (goingRight == 1) {
			
			positions.clear();
			
			legBack.rotate(0, 3, Math.PI / 4);
			legFront.rotate(0, -3, Math.PI / 4);
			armBack.rotate(0, 3, Math.PI / 4);
			armFront.rotate(0, -3, Math.PI / 4);

			if (legBack.getCurrentAngle() <= 0.01 && legBack.getCurrentAngle() >= -0.01) {
				
				goingRight = 0;
				
			}
			
			setPositions();
			
		} else if (goingRight == -1) {
			
			positions.clear();
			
			legBack.rotate(0, -3, Math.PI / 4);
			legFront.rotate(0, 3, Math.PI / 4);
			armBack.rotate(0, -3, Math.PI / 4);
			armFront.rotate(0, 3, Math.PI / 4);

			if (legBack.getCurrentAngle() <= 0.01 && legBack.getCurrentAngle() >= -0.01) {
				
				goingRight = 0;
				
			}
			
			setPositions();
			
		}
		
	}
	
	public ArrayList<int[]> getPositions() {
		
		return positions;
		
	}
	
	public ArrayList<Color> getColors() {
		
		return colors;
		
	}

}
