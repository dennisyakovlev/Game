package scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import objects.Object;
import variables.Program;

/*
 * 
 * some notes
 * - if an object has height 100 and y positions of < -95 then the bottom pixels will not display
 * - if an object has width 100 and x positions of > 540 then the pixels of ONLY the square that go off the screen will be displayed off the screen
 * 
*/
public class Scene extends Canvas {
	
	private GraphicsContext gc;
	
	//list of children with highest importance (at front of scene) descending to lowst importance (at back of scene)
	private ArrayList<Object> children = new ArrayList<>();
	private int[] pixelAssist = new int[6912];
	
	public Scene() {
		
		gc = this.getGraphicsContext2D();
		
		setWidth(640);
		setHeight(270);

		setLayoutX(-5);
		setLayoutY(-5);
	}
	
	public void addObject(Object child, int importance) {

		child.check();
		
		child.setImportance(importance);

		boolean broken = false;
		
		if (children.size() != 0) {

			for (int i = 0; i < children.size(); i++) {
				
				if (children.get(i).getImportance() <= importance) {
					
					children.add(i, child);
					broken = true;
					break;
					
				} 
				
			}
			
		}
		
		if (broken == false) {
			
			children.add(child);
			
		}
		
	}
	
	public void update() {
		
		gc.clearRect(0,	0, 640, 270);

		for (int i = children.size() - 1; i >= 0; i--) {
			
			final Object child = children.get(i);
			final int x = child.getX();
			final int y = child.getY();
			final int width = child.getGameWidth();
			final int height = child.getGameHeight();
			

			/*
			 * each variable must be independant
			 * 
			 * -1000000 is a value which no object position will ever have, thus,
			 * used to show object is fully of scene in one dimension
			 * 
			 * EITHER startX or endX will have value of -1000000 if object is
			 * off scene in X direction. Same for startY and endY
			*/
			int startX = x < 0 ? -x : (x > 640 ? -1000000 : 0);
			int endX = x + width > 640 ? 640 - x : (x + width <= 0 ? -1000000 : width);
			
			int startY = y < 0 ? -y : (y > 270 ? -1000000 : 0);
			int endY = y + height > 270 ? 270 - y : (y + height <= 0 ? -1000000 : height);
			
			if (startX != -1000000 && startY != -1000000 && endX != -1000000 && endY != -1000000) {
				
				ArrayList<int[]> positions = new ArrayList<>();
				ArrayList<Color> colors = new ArrayList<>();
				Pair<ArrayList<int[]>, ArrayList<Color>> p = new Pair<ArrayList<int[]>, ArrayList<Color>>(positions, colors);
				
				p = child.test(startX, startY, endX, endY);
				
				positions = p.getKey();
				colors = p.getValue();
				
				for (int currentPixel = 0; currentPixel < positions.size(); currentPixel ++) {
					
					/*
					 * update the x and y here so that the positions array dont have to be updated in a seperate loop
					*/
					gc.setFill(colors.get(currentPixel));
					gc.fillRect(positions.get(currentPixel)[0], positions.get(currentPixel)[1], 5, 5);

				}
				
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
