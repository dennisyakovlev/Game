package scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import objects.Object;
import variables.Program;

public class Scene extends Canvas {
	
	private GraphicsContext gc;
	private Object child;
	
	public Scene() {
		
		gc = this.getGraphicsContext2D();
		
		setWidth(670);
		setHeight(270);



		
	}
	
	public void addObject(Object object) {

		child = object;

	}
	
	public void initalUpdate() {

		int[][] positions = child.getInitialGamePixelPositions();
		Color[] colors = child.getInitialPixelColor();

		for (int currentPixel = 0; currentPixel < positions.length; currentPixel ++) {

			gc.setFill(colors[currentPixel]);
			gc.fillRect(positions[currentPixel][0], positions[currentPixel][1], 5, 5);

		}

    }
	
	public void fill(Color color) {
		
		gc.setFill(color);

		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < 640; j++) {
				
				gc.fillRect(j, i, 1, 1);

			}
			
		}
		
	}
	
	public void add() {
		Program.mainPane.getChildren().add(this);

    }
	
	public void toFront() {
		
	}
	
	public void exitMenu() {
		
	}
	
	
}
