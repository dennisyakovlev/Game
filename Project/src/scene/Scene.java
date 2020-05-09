package scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import objects.Object;
import variables.Program;

public class Scene extends Canvas {
	
	private GraphicsContext gc;
	
	//list of children with highest importance (at front of scene) descending to lowst importance (at back of scene)
	private ArrayList<Object> children = new ArrayList<>();
	private int[] pixelAssist = new int[6912];
	
	public Scene() {
		
		gc = this.getGraphicsContext2D();
		
		setWidth(670);
		setHeight(270);

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
	
	public void test() {
		
		for (Object o : children) {
			System.out.println(o.getImportance());
		}
		System.out.println();
		
	}
	
	//6912 pixels to go through, when a pixel is plotted it isnt plotted again **note** shouldnt need as that would create gaps, just draw the specified part of image
	//int array of 1 and 0, if the position is a 1 it means pixel is free to plot, if 0 it means pixel is plotted and no need to re plot
	public void update() {
		
		gc.clearRect(0,	0, 640, 270);
		
		for (int i = 0; i < children.size(); i++) {
			
			final Object child = children.get(i);
			final int x = child.getX();
			final int y = child.getY();
			final int width = child.getGameWidthInPixels();
			final int height = child.getGameHeightInPixels();

			/*
			 * check1 ? (check2 ? true1 : check3 ? true2 : false1) : false2
			 * 
			 * check1 - is any part of X of image on screen?
			 * check1 - is any part of X of image on screen?
			 * check3 - is part of the image off the window?
			 * 
			 * true1 - image flows past widnow to left - return the width that be drawn (-return) to end of imageX
			 * true2 - image flows past window to right - return the width that should be drawn 0 to return
			 * false1 - object is fully inside window
			 * false2 - object is fully outside window 
			 * 
			 * between 1 inclusive and 640 inclusive
			 */
			final int visibleX = (x < 640) && (x > -width) ? (x < 0 ? -(width - (width + x)) : (x + width) > 640 ? 640 - x : width) : 0; 
			
			/*
			 * same properties as visibleX but for y
			 * 
			 * true1 - image flows past window to top - returns negative
			 * true2 - image flows past window to bottom
			 */
			final int visibleY = (y < 270) && (y > -height) ? (y < 0 ? -(height - (height + y)) : (y + height) > 270 ? height - y : height) : 0;
			
			//System.out.println(visibleX + " " + visibleY);
			//have to get position and color pixels only on screen
			
			ArrayList<int[]> positions = new ArrayList<>();
			ArrayList<Color> colors = new ArrayList<>();
			
			if (visibleX < 0 && visibleY < 0) { //flows off top and left of screen (100% sure for both)
				
				
				
			} else if (visibleX < 0 && visibleY > 0) { 
				
				
				
			} else if (visibleX > 0 && visibleY < 0) {
				
				
				
			} else if (visibleX > 0 && visibleY > 0) {

				System.out.println(visibleX + " " + visibleY);
				 positions = child.getSpecificPixels(0, 0, visibleX, visibleY);
				 colors = child.getSpecificColors(0, 0, visibleX, visibleY);
				
			}
			
			//System.out.println(positions.size());
			for (int currentPixel = 0; currentPixel < positions.size(); currentPixel ++) {
				//System.out.println(visibleX + " " + visibleY + " " + currentPixel);
				gc.setFill(colors.get(currentPixel));
				gc.fillRect(positions.get(currentPixel)[0], positions.get(currentPixel)[1], 5, 5);

			}
			
		}
		
	}
	
	public void initialUpdate() {
		
		
		
		int[][] positions = children.get(0).getInitialGamePixelPositions();
		Color[] colors = children.get(0).getInitialPixelColor();

		for (int currentPixel = 0; currentPixel < positions.length; currentPixel ++) {

			gc.setFill(colors[currentPixel]);
			gc.fillRect(positions[currentPixel][0], positions[currentPixel][1], 5, 5);

		}
		
	}

	
	/*
	public void initalUpdate() {

		int[][] positions = child.getInitialGamePixelPositions();
		Color[] colors = child.getInitialPixelColor();

		for (int currentPixel = 0; currentPixel < positions.length; currentPixel ++) {

			gc.setFill(colors[currentPixel]);
			gc.fillRect(positions[currentPixel][0], positions[currentPixel][1], 5, 5);

		}

    }
	*/
	
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
