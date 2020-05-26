package objects.gameobjects;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class TwoCircles extends GameObject {

	private ArrayList<int[]> positions = new ArrayList<>();
	private ArrayList<Color> colors = new ArrayList<>();
	
	public TwoCircles() {
		
		GameObject left = new GameObject(0, 0, "one", "vine.png", "left");
		left.setImage();
		left.removeBackground(Color.WHITE);
		left.process();
		left.setOutside();
		
		
		GameObject right = new GameObject(100, 0, "one", "vine.png", "left");
		right.setImage();
		right.removeBackground(Color.WHITE);
		right.process();
		right.setOutside();
		

		positions.addAll(left.getPositions());
		positions.addAll(right.getPositions());

		colors.addAll(left.getColors());
		colors.addAll(right.getColors());
		
		createMadeObject(0, 0, "test", positions, colors);
		
	}
	
}
